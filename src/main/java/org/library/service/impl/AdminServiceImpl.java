package org.library.service.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.library.dao.Dao;
import org.library.model.Book;
import org.library.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService
{
	private Dao dao;
	
	@Resource(name="dao")
	public void setDao(Dao dao)
	{
		this.dao = dao;
	}
	
	/**
	 * 接收一个Book对象，将book信息保存到一个library数据库里；
	 * 
	 * @param book
	 */
	public void saveBook(Book book)
	{
		dao.save(book);
	}

	/**
	 * 修改图书
	 * @param Book book
	 */
	public void modifyBook(Book book, String oldCallNumber)
	{
		dao.update(book);
	}

	public String deleteBook(String callNumber)	//删除一本图书
	{
		
		String sql = "delete from book WHERE callNumber='" + callNumber + "'";
		
		Query query = dao.createSQLQuery(Book.class, sql);
		
		query.executeUpdate();
		
		return "删除成功";
	}	

}
