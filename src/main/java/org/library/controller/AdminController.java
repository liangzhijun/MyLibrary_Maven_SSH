package org.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController
{
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
	public String adminRegister(@RequestParam String username,
			@RequestParam String password, @RequestParam String repassword,
			@RequestParam String email, @RequestParam String name,
			@RequestParam String gender, @RequestParam String unit,
			@RequestParam String mobilePhone, HttpSession session, Model model)
	{
		String result = "";

		if (null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		else if (username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		// 密码的长度均须在4---10之间
		if (!password.equals(repassword))
		{
			result += "密码不一致！";
		}
		if (password == null || password.length() < 4 || password.length() > 10)
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

		if (result == "")
		{
			User user = new User();

			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setUnit(unit);
			user.setMobilePhone(mobilePhone);
			user.setRole("admin");// 默认角色的参数

			UserService.register(user);// 注册

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
	public String booksEntering(@RequestParam String title,
			@RequestParam String author, @RequestParam String publisher,
			@RequestParam String callNumber,
			@RequestParam String ISBNandPricing, @RequestParam String subject,
			@RequestParam String page, @RequestParam String list,
			@RequestParam String content, @RequestParam String barcode,
			@RequestParam String condition, @RequestParam String lib,
			Model model)
	{
		String result = "";

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
			Book book = new Book();

			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCallNumber(callNumber);
			book.setISBNandPricing(ISBNandPricing);
			book.setSubject(subject);
			book.setPage(page);			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);

			BookDao.modifyBook(book);// 将书目信息存入数据库

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
		List<Book> list = BookDao.search(strText, strSearchType);

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
	 * 
	 * @param barcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="deleteBook.htm", method = RequestMethod.GET)
	public String deleteBook(@RequestParam String barcode, Model model)
	{
		BookDao.deleteBook(barcode);
		
		return "redirect:/deleteBooks.htm";
	}
	
	/**
	 * 修改图书信息
	 * @param barcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="modifyBooks.htm")
	public String modifyBooks(@RequestParam String barcode, ModelMap model)
	{
		Book book = BookDao.getBookinfo(barcode);
		model.addAttribute("book", book);
		
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
	public String modifyBook(@RequestParam String title,
			@RequestParam String author, @RequestParam String publisher,
			@RequestParam String callNumber,
			@RequestParam String ISBNandPricing, @RequestParam String subject,
			@RequestParam String page, @RequestParam String list,
			@RequestParam String content, @RequestParam String barcode,
			@RequestParam String condition, @RequestParam String lib,
			Model model)
	{
		String result = "";

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
			Book book = new Book();

			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCallNumber(callNumber);
			book.setISBNandPricing(ISBNandPricing);
			book.setSubject(subject);
			book.setPage(page);
			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);

			BookDao.saveBook(book);// 将书目信息存入数据库

			model.addAttribute("result", "图书修改成功");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
}
