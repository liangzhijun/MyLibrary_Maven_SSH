package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao
{
	private static Connection conn = null;

	public static Connection getConn()
	{
		return conn;
	}

	public static void setConn(Connection conn)
	{
		UserDao.conn = conn;
	}

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			// new com.mysql.jdbc.Driver();

			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mylibrary", "root", "root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sql
	 * @return
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
	 * 
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql)
	{
		int result = 0;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			result = stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
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
