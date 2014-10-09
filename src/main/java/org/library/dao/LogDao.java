package org.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.library.model.SystemLog;

public class LogDao
{
	private static Connection conn = null;

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
	
	public static void saveLog(SystemLog log)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 主键编号
	    int insertkey = 0;
	    // 查询序列
	    String sequence = "select S_TBL_NOTEMMS.nextval from systemlog";
	    // 插入语句        
	    String sql = "insert into systemlog(ID, SENDPHONE, RECEIVEPHONE, CREATETIME) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    		
		try
		{
		    pstmt = conn.prepareStatement(sequence);
	        rs = pstmt.executeQuery();
	        rs.next();
	        insertkey = rs.getInt(1);
		    
			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setInt(1, insertkey);
			pstmt.setString(2, log.getIp());
			pstmt.setString(3,log.getMkName());
			pstmt.setString(4, log.getName());
			pstmt.setDate(5, (Date)log.getNowtime());
			pstmt.setString(6, log.getRealname());
			pstmt.setString(7, log.getUnit());
			pstmt.setString(8, log.getName());
			pstmt.setString(9, log.getUsername());
			 
			 pstmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();				
		}
	
		finally
		{	
			try
			{
				if(pstmt != null)
				{	pstmt.close();
					pstmt = null;
				}		
					
				if(conn != null)
				{	conn.close();
					conn = null;
				}
					
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * 从数据库的filteruri表格以每行将share值为true加入到set，然后返回一个set
	 * @return set
	 */
	public static Set<String> findShareUris()
	{
		ResultSet rs = null;
		Statement stmt = null;
		
		Set<String> set = new HashSet<String>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from filteruri");

			while (rs.next())
			{
				 if(rs.getString("share").equals("true"))
				 {
					 set.add(rs.getString("URI"));
				 }
			}
			
			return set;
		}
		catch (SQLException e)
		{
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

				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
