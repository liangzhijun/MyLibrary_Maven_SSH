package org.library.serviceImpl;

import org.library.dao.Dao;
import org.library.model.Book;
import org.library.service.AdminService;

public class AdminServiceImpl implements AdminService
{
	/**
	 * 接收一个Book对象，将book信息保存到一个library数据库里；
	 * 
	 * @param book
	 */
	public void saveBook(Book book)
	{
		Dao.save(book);
	}

	/**
	 * 修改图书
	 * @param Book book
	 */
	@Override
	public void modifyBook(Book book, String oldBarcode)
	{
		Book b = new Book();
		b = (Book)Dao.get(b, oldBarcode);
		
		Dao.update(b);
		
	}

	@Override
	public String deleteBook(String barcode)	//删除一本图书
	{
		Book book = new Book();
		book = (Book)Dao.get(book, barcode);
		
		Dao.delete(book);
		
		return "删除成功";
	}	

}
