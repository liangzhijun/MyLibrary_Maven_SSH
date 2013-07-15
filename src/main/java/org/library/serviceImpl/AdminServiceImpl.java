package org.library.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.dao.BookDao;
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
		PreparedStatement pstmt = null;
		String sql = "insert into library values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try
		{
			pstmt = BookDao.prepareStatement(sql);

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getCallNumber());
			pstmt.setString(5, book.getISBNandPricing());
			pstmt.setString(6, book.getSubject());
			pstmt.setString(7, book.getPage());
			pstmt.setString(8, book.getList());
			pstmt.setString(9, book.getContent());
			pstmt.setString(10, book.getLib());
			pstmt.setString(11, book.getBarcode());
			pstmt.setString(12, book.getCondition());
			
			pstmt.executeUpdate();			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
					pstmt = null;
				}

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 修改图书
	 * @param Book book
	 */
	@Override
	public void modifyBook(Book book, String oldBarcode)
	{
		String sql = "select * from library WHERE barcode='" + oldBarcode + "'";
		
		ResultSet rs = BookDao.executeQuery(sql);
		
		try {
		
			if(rs.next())
			{
				//更新一行数据
				rs.updateString("title", book.getTitle());
				rs.updateString("author", book.getAuthor());
				rs.updateString("publisher", book.getPublisher());
				rs.updateString("callNumber", book.getCallNumber());
				rs.updateString("ISBNandPricing", book.getISBNandPricing());
				rs.updateString("subject", book.getSubject());
				rs.updateString("page", book.getPage());
				rs.updateString("barcode", book.getBarcode());
				rs.updateString("condition", book.getCondition());
				rs.updateString("lib", book.getLib());
				rs.updateString("list", book.getList());
				rs.updateString("content", book.getContent());
				rs.updateRow();
			}
	
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
		
	}

	@Override
	public String deleteBook(String barcode)	//删除一本图书
	{
		String sql = "delete from library WHERE barcode='" + barcode + "'";
		
		int rs = BookDao.executeUpdate(sql);
		System.out.println(rs);
		
		return "删除成功";
	}	

}
