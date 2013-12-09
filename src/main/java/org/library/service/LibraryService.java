package org.library.service;

import java.util.List;

import org.library.model.Book;
import org.library.model.BookData;

public interface LibraryService
{
	//从数据库遍历书籍，检索。返回一本书籍的信息
	public List<Book> searchBooks(String strText, String strSearchType);
	
	//修改图书,接收参数String callNumber(索书号)来获取书本的所有信息
	public Book getBookinfo(String callNumber);
	
	//从数据库遍历书籍，返回一书目的状态,数量等信息
	public List<BookData> bookinfos(String title);
	
	//从数据库遍历书籍，把书籍以一个book对象逐个放到一个List的集合,然后将List返回
	public List<Book> getAllBooks();
}
