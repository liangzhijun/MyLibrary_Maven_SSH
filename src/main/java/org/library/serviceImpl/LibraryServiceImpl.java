package org.library.serviceImpl;
import java.util.List;

import org.hibernate.Query;
import org.library.dao.Dao;
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
		String ql = "from Book b where b." + strSearchType + " like '%" + strText + "%'";
		Query q = Dao.createQuery(ql);
		
		List<Book> list = (List<Book>)q.list();
		
		return list;
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
		book = (Book)Dao.get(book, barcode);
		
		return book;
	}

	/**
	 * 从数据库遍历书籍，返回一书目的状态,数量等信息
	 * @return List<Book>
	 */
	@Override
	public List<Book> bookinfos(String title)
	{
		String ql = "from Book b where b.title = " + title;
		
		Query q = Dao.createQuery(ql);
		List<Book> lists = (List<Book>)q.list();
		
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
		Query q = Dao.createQuery("from Book");
		List<Book> books = (List<Book>)q.list();
		
		return books;
	}

}
