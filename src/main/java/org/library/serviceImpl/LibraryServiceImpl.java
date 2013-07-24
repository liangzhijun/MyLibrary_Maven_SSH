package org.library.serviceImpl;
import java.util.List;

import org.library.dao.Dao;
import org.library.model.Book;
import org.library.model.BookData;
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
		String sql = "select * from book WHERE " + strSearchType + " LIKE '" + "%" + strText + "%" + "'";
		
		List<Book> list = (List<Book>)Dao.createSQLQuery(Book.class, sql);
		
		return list;
	}

	/**
	 * 接收一个String类型的书名，以书名做索引从library数据库遍历书籍，返回一本书籍的信息
	 * @param title
	 * @return
	 */
	@Override
	public Book getBookinfo(String callNumber)
	{
		Book book = new Book();
		book = (Book)Dao.get(book, callNumber);
		
		return book;
	}

	/**
	 * 从数据库遍历书籍，返回一书目的状态,数量等信息
	 * @return List<Book>
	 */
	@Override
	public List<BookData> bookinfos(String callNumber)
	{
		String sql = "select * from bookdata WHERE callNumber='" + callNumber + "'";
		
		List<BookData> lists = (List<BookData>)Dao.createSQLQuery(BookData.class, sql);
		
		return lists;
	}

	/**
	 * 从数据库遍历书籍，把书籍以一个book对象逐个放到一个List的集合,然后将List返回
	 * 
	 * @return
	 */
	@Override
	public List<Book> getAllBooks()
	{
		
		List<Book> books = (List<Book>)Dao.createSQLQuery(Book.class, "select * from book");
		
		return books;
	}

}
