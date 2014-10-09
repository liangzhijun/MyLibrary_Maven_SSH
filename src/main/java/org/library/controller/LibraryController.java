package org.library.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.library.model.Book;
import org.library.model.BookData;
import org.library.model.SystemLog;
import org.library.model.User;
import org.library.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LibraryController
{	
	LibraryService libraryService;
	
	 @Resource
	public void setLibraryService(LibraryService libraryService)
	{
		this.libraryService = libraryService;
	}

	@RequestMapping(value = "/myLibrary.htm")
	public String myLibrary()
	{
		return "myLibrary";
	}

	/** 图书检索
	 * 
	 * @param strText
	 * @param strSearchType
	 * @param model
	 * @return "result.jsp"
	 */
	@RequestMapping(value = "/searchBooks.htm")
	public String searchBooks(@RequestParam String strText,
			@RequestParam String strSearchType, Model model)
	{
		//从数据访问层获得数据，通过搜索，没有数据返回null`
		List<Book> bookList = libraryService.searchBooks(strText, strSearchType);	

		if (bookList != null && bookList.size() != 0)
		{
			model.addAttribute("bookList", bookList);
			return "SearchResult";
		}
		else
			model.addAttribute("result", "图书馆没有你要找的" + strText + "书刊");

		return "result";
	}

	/** 取得书籍的集合
	 * 
	 * @param model
	 * @return "books.jsp"
	 */
	@RequestMapping(value = "/getBooks.htm")
	public String getBooks(HttpSession session, Model model)
	{
		//List<Book> bookList = libraryService.getAllBooks();
		//model.addAttribute("bookList", bookList);
		
		User user = (User)session.getAttribute("user");
		
		if(null == user)		//用户未登录时，返回的jsp页面
			return "books";
		
		else if(user.getRole().equals("student")) //student角色登录时，返回的jsp页面
			return "books";
		
		return "adminBooks";	//admin角色登录时，返回的jsp页面
	}
	
	/** 取得书籍的集合，以json格式返回集合信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/AjaxGetBooks.htm", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String AjaxBookInfos() throws Exception
	{	
		Set<Object> set = new HashSet<Object>();
		String jsontext;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	
		List<Book> bookList = libraryService.getAllBooks();
		
		for(Book b: bookList)
		{
			Map<Object, Object> map = new HashMap<Object, Object>();
			
			map.put("amount", b.getAmount());
			map.put("author", b.getAuthor());
			map.put("callNumber", b.getCallNumber());
			map.put("publisher", b.getPublisher());
			map.put("title", b.getTitle());
			
			set.add(map);
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("pageCount", set.size() / 2);
		map.put("books", set);
		
		
		jsontext = mapper.writeValueAsString(map);
		
		return jsontext;
	}

	/** 取得图书信息内容
	 * 
	 * @param title
	 * @param model
	 * @return "bookinfo.jsp"
	 */
	@RequestMapping(value = "/bookinfo.htm")
	public String bookinfo(@RequestParam String callNumber,HttpSession session, Model model)
	{
		Book book = libraryService.getBookinfo(callNumber);
		List<BookData> list = libraryService.bookinfos(callNumber);

		model.addAttribute("book", book);
		model.addAttribute("list", list);
		
		User user = (User)session.getAttribute("user");
		
		if(null == user)		//用户未登录时，返回的jsp页面
			return "bookinfo";
		
		else if(user.getRole().equals("student")) //student角色登录时，返回的jsp页面
			return "bookinfo";
		
		return "adminBookinfo";	//admin角色登录时，返回的jsp页面
	}
	
	@RequestMapping(value = "/saveLog.htm")
	public String saveLog(HttpSession session)
	{
		SystemLog log = (SystemLog)session.getAttribute("log");
		
		libraryService.saveLog(log);
		
		//return (String)session.getAttribute("requestURI");
		
		return null;
	}
}
