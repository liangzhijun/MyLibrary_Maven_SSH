package org.library.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.service.LibraryService;

public class LibraryServiceImpl implements LibraryService
{

	/**
	 * 从数据库遍历书籍，检索。返回一本书籍的信息
	 * @return
	 */
	@Override
	public List<Book> searchBooks(String strText, String strSearchType)
	{
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from library WHERE " + strSearchType + " LIKE '" + "%" + strText + "%" + "'";
		
		ResultSet rs = BookDao.executeQuery(sql);
		
		try {
		
			while(rs.next())
			{
				// 从数据库遍历书籍	
				Book book = new Book();
				
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				list.add(book);;
			}
			
			if(list.isEmpty())
				return null;
			
			else return list;
	
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 接收一个String类型的书名，以书名做索引从library数据库遍历书籍，返回一本书籍的信息
	 * @param title
	 * @return
	 */
	@Override
	public Book getBookinfo(String barcode)
	{
		Book book = new Book();
		String sql = "select * from library WHERE barcode='" + barcode + "'";
		
		ResultSet rs = BookDao.executeQuery(sql);
		
		try {
			if(rs.next())
			{
				// 从数据库遍历书籍	
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
			}
			return book;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 从数据库遍历书籍，返回一书目的状态,数量等信息
	 * @return List<Book>
	 */
	@Override
	public List<Book> bookinfos(String title)
	{
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from library WHERE title='" + title + "'";
		
		ResultSet rs = BookDao.executeQuery(sql);
		
		try {
		
			while (rs.next())
			{
				Book book = new Book();
				
				// 从数据库遍历书籍	
				String callNumber = rs.getString("callNumber");
				System.out.println(callNumber);
				book.setCallNumber(rs.getString("callNumber"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				list.add(book);
			}
			
			return list;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 从数据库遍历书籍，把书籍以一个book对象逐个放到一个List的集合,然后将List返回
	 * 
	 * @return
	 */
	@Override
	public List<Book> getAllBooks()
	{
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from library";
		
		ResultSet rs = BookDao.executeQuery(sql);
		
		try {
		
			while (rs.next())
			{
				// 从数据库遍历书籍	
				Book book = new Book();
				String title = rs.getString("title");
				
				book.setTitle(title);
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				
				list.add(book);
			}
			
			return list;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

}
