package org.library.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.library.dao.Dao;
import org.library.model.Book;
import org.library.model.BookData;
import org.library.model.SystemLog;
import org.library.service.LibraryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("libraryService")
@Transactional
public class LibraryServiceImpl implements LibraryService
{
	private Dao dao;
	
	@Resource(name="dao")
	public void setDao(Dao dao)
	{
		this.dao = dao;
	}

	/**
	 * 从数据库遍历书籍，检索。返回一本书籍的信息
	 * @return
	 */
	@Override
	public List<Book> searchBooks(String strText, String strSearchType)
	{
		String sql = "select * from book WHERE " + strSearchType + " LIKE '" + "%" + strText + "%" + "'";
		
		List<Book> list = (List<Book>)dao.createSQLQuery(Book.class, sql).list();
		
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
		Book book = (Book)dao.get(Book.class, callNumber);
		
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
		
		List<BookData> lists = (List<BookData>)dao.createSQLQuery(BookData.class, sql).list();
		
		return lists;
	}

	/**
	 * 从数据库遍历书籍，把书籍以一个book对象逐个放到一个List的集合,然后将List返回
	 * 
	 * @return
	 */
	public List<Book> getAllBooks()
	{
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>)dao.createSQLQuery(Book.class, "select * from book").list();
		
		return books;
	}

	/**
	 * 保存系统日志信息
	 */
	@Override
	public void saveLog(SystemLog log)
	{
		dao.save(log);
	}

}
