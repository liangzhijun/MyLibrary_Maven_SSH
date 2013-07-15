package org.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.AdminService;
import org.library.service.LibraryService;
import org.library.service.UserService;
import org.library.serviceImpl.AdminServiceImpl;
import org.library.serviceImpl.LibraryServiceImpl;
import org.library.serviceImpl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController
{
	UserService userService = new UserServiceImpl();
	AdminService adminService = new AdminServiceImpl();
	LibraryService libraryService = new LibraryServiceImpl();
	
	/**
	 * 管理者注册
	 * @param username
	 * @param password
	 * @param repassword
	 * @param email
	 * @param name
	 * @param gender
	 * @param unit
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminRegister.htm")
	@ResponseBody
	public String adminRegister(@ModelAttribute User user, HttpSession session, Model model)
	{
		String result = "";

		String username = user.getUsername();
		String ps = user.getPassword();
		String reps = user.getRepassword();
		String email = user.getEmail();
		String name = user.getName();
		String unit = user.getUnit();
		
		if (null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		else if (username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		// 密码的长度均须在4---10之间
		if (!ps.equals(reps))
		{
			result += "密码不一致！";
		}
		if (ps == null || ps.length() < 4 || ps.length() > 10)
		{
			result += "密码的长度须在4---10之间";
		}
		if (null == email || "".equals(email))
		{
			result += "邮箱不可以为空! ";
		}
		if (null == name || "".equals(name))
		{
			result += "姓名不可以为空！";
		}
		if(null == unit || "".equals(unit))
		{
			result += "单位必需要填写！";
		}
		

		if (result == "")
		{
			user.setRole("admin");// 默认角色的参数

			userService.register(user);// 注册

			session.setAttribute("result", "注册成功！");
			
			return "200 /success.jsp";
		}

		session.setAttribute("result", result);

		return "400 " + result;
	}

	/**
	 * 图书录入
	 * 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param callNumber
	 * @param ISBNandPricing
	 * @param subject
	 * @param page
	 * @param list
	 * @param content
	 * @param barcode
	 * @param condition
	 * @param lib
	 * @param model
	 * @return "result";
	 */
	@RequestMapping(value = "/booksEntering.htm ", method = RequestMethod.POST)
	public String booksEntering(@ModelAttribute Book book,
			Model model)
	{
		String result = "";

		String title = book.getTitle();
		String author = book.getAuthor();
		String publisher = book.getPublisher();
		String callNumber = book.getCallNumber();
		String ISBNandPricing = book.getISBNandPricing();
		String subject = book.getSubject();
		String page = book.getPage();
		String list = book.getList();
		String content = book.getContent();
		
		if (null == title || "".equals(title))
		{
			result += "题名必须要填写! ";
		}
		if (null == author || "".equals(author))
		{
			result += "责任者必须要填写！";
		}
		if (null == publisher || "".equals(publisher))
		{
			result += "出版社必须要填写！";
		}
		if (null == callNumber || "".equals(callNumber))
		{
			result += "索引号必须要填写! ";
		}
		if (null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN及定价必须要填写！";
		}
		if (null == subject || "".equals(subject))
		{
			result += "科学主题必须要填写！";
		}
		if (null == page || "".equals(page))
		{
			result += "载体形态项必须要填写！";
		}
		if (null == list || "".equals(list))
		{
			result += "书目录必须要填写！";
		}
		if (null == content || "".equals(content))
		{
			result += "内容简介必须要填写！";
		}
		
		if (result == "")
		{
			adminService.saveBook(book);// 将书目信息存入数据库

			model.addAttribute("result", "成功录入书目");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
	
	/**
	 * 删除图书
	 * @param model
	 * @return  "books.htm";
	 */
	@RequestMapping("deleteBooks.htm")
	public String deleteBooks(Model model)
	{
		List<Book> list = BookDao.findBooks();
		model.addAttribute("list", list);
		
		return "deleteBooks";
	}
	
	/** 删除图书与检索
	 * 
	 * @param strText
	 * @param strSearchType
	 * @param model
	 * @return "result.jsp"
	 */
	@RequestMapping(value = "/SearchDeleteBooks.htm")
	public String SearchDeleteBooks(@RequestParam String strText,
			@RequestParam String strSearchType, Model model)
	{
		List<Book> list = libraryService.searchBooks(strText, strSearchType);

		if (list != null)
		{
			model.addAttribute("list", list);
			return "deleteBooks";
		}
		else
			model.addAttribute("result", "图书馆没有你要找的" + strText + "书刊");

		return "result";
	}
	
	/** 删除书本
	 * @param barcode
	 * @return
	 */
	@RequestMapping(value="deleteBook.htm", method = RequestMethod.GET)
	public String deleteBook(@RequestParam String barcode)
	{
		adminService.deleteBook(barcode);
		
		return "redirect:/deleteBooks.htm";
	}
	
	/**
	 * 修改图书信息
	 * @param barcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="modifyBooks.htm")
	public String modifyBooks(@RequestParam String barcode, HttpSession session, ModelMap model)
	{
		Book book = BookDao.getBookinfo(barcode);
		model.addAttribute("book", book);
		session.setAttribute("oldBarcode", barcode);	//将barcode以oldBarcode为name放到session中，作为修改书本的索引
		
		return "modifyBook";
	}
	
	/**
	 * 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param callNumber
	 * @param ISBNandPricing
	 * @param subject
	 * @param pages
	 * @param list
	 * @param content
	 * @param barcode
	 * @param condition
	 * @param lib
	 * @param model
	 * @return
	 */
	@RequestMapping(value="modifyBook.htm", method = RequestMethod.POST)
	public String modifyBook(@ModelAttribute Book book, HttpSession session, Model model)
	{
		String result = "";

		String title = book.getTitle();
		String author = book.getAuthor();
		String publisher = book.getPublisher();
		String callNumber = book.getCallNumber();
		String ISBNandPricing = book.getISBNandPricing();
		String subject = book.getSubject();
		String page = book.getPage();
		String list = book.getList();
		String content = book.getContent();
		String barcode = book.getBarcode();
		String condition = book.getCondition();
		String lib = book.getLib();
		
		if (null == title || "".equals(title))
		{
			result += "题名必须要填写! ";
		}
		if (null == author || "".equals(author))
		{
			result += "责任者必须要填写！";
		}
		if (null == publisher || "".equals(publisher))
		{
			result += "出版社必须要填写！";
		}
		if (null == callNumber || "".equals(callNumber))
		{
			result += "索引号必须要填写! ";
		}
		if (null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN及定价必须要填写！";
		}
		if (null == subject || "".equals(subject))
		{
			result += "科学主题必须要填写！";
		}
		if (null == page || "".equals(page))
		{
			result += "载体形态项必须要填写！";
		}
		if (null == list || "".equals(list))
		{
			result += "书目录必须要填写！";
		}
		if (null == content || "".equals(content))
		{
			result += "内容简介必须要填写！";
		}
		if(null == barcode || "".equals(barcode))
		{
			result += "条形码必须要填写！";
		}
		if (null == condition || "".equals(condition))
		{
			result += "书刊状态必须要填写！";
		}
		if(null == lib || "".equals(lib))
		{
			result += "馆藏地必须要填写！";
		}	

		if (result == "")
		{
			adminService.modifyBook(book, (String)session.getAttribute("oldBarcode"));// 将书目信息存入数据库

			model.addAttribute("result", "图书修改成功");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
}
