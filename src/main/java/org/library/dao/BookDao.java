package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDao
{
	private static Connection conn = null;

	public static Connection getConn()
	{
		return conn;
	}

	public static void setConn(Connection conn)
	{
		BookDao.conn = conn;
	}

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			// new com.mysql.jdbc.Driver();

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mylibrary", "root", "root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param sql
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String sql)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * @param sql
	 * @return int
	 */
	public static int executeUpdate(String sql)
	{
		int rs = 0;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * @param sql
	 * @return PreparedStatement
	 */
	public static PreparedStatement prepareStatement(String sql)
	{
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return pstmt;
	}
	
}
