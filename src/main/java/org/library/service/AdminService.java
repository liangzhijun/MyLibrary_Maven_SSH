package org.library.service;

import org.library.model.Book;

public interface AdminService
{
	//接收一个book对象，将book信息保存到一个library数据库里；
	public void saveBook(Book book);
	//修改图书信息
	public void modifyBook(Book book, String oldBarcode);
	//删除书本
	public String deleteBook(String barcode);
}
