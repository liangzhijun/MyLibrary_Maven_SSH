package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.library.model.Profession;
import org.library.model.User;

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
	 * 将新用户注册信息保存到数据库；
	 * 
	 * @param user
	 */
	public static void saveUser(User user)
	{
		PreparedStatement pstmt = null;

		try
		{

			pstmt = conn
					.prepareStatement("insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getGender());
			pstmt.setString(6, user.getUnit());
			pstmt.setString(7, user.getPhone());
			pstmt.setString(8, new java.util.Date().toLocaleString());
			pstmt.setString(9, "");
			pstmt.setString(10, user.getMobilePhone());
			pstmt.setString(11, "2000030536");
			pstmt.setString(12, "本科生");
			pstmt.setString(13, user.getRole());
			pstmt.setString(14, null);
			
			
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
	 * 根据academyId(学院ID)搜索学院，返回一个包含学院内所有专业名称专业ID和的集合
	 * @param academy
	 * @return
	 */
	public static Set<Profession> findAcademy(String academyId)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Set<Profession> set = new HashSet<Profession>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from profession WHERE academyId='" + academyId + "'");

			while (rs.next())
			{
				Profession pro = new Profession();
				
				String profession = rs.getString("profession");
				String id = rs.getString("id");
				
				pro.setProfession(profession);
				pro.setId(id);
				
				set.add(pro);
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
	
	
	/**
	 * 根据professionId(专业ID)搜索，返回班级的数量
	 * @param professionId
	 * @return
	 */
	public static Map<String, Object> findProfession(String academyID, String professionId)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from profession WHERE id='" + professionId + "'");

			if(rs.next())
			{
				String classlist = rs.getString("classlist");
				String academy = rs.getString("academy");
				String profession = rs.getString("profession");
				String unit = academy + "/" + profession;
				
				map.put("classlist", classlist );
				map.put("unit", unit);
				
				return map;
			}
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

	/**
	 * 接收一个用户名，搜索用户是否存在数据库里，若存在返回对应用户资料的一个User集合，否则返回空；
	 * 
	 * @param myUsername
	 * @return
	 */
	public static User findUser(String myUsername)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user WHERE username='" + myUsername + "'");

			while (rs.next())
			{
				// 从数据库遍历用户
				String username = rs.getString("username");

				if (myUsername.equals(username))
				{
					User user = new User();
					
					user.setUsername(username);
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setGender(rs.getString("gender"));
					user.setUnit(rs.getString("unit"));
					user.setPhone(rs.getString("phone"));
					user.setMobilePhone(rs.getString("mobilePhone"));
					user.setAddress(rs.getString("address"));
					user.setIdCare(rs.getString("idCare"));
					user.setType(rs.getString("type"));
					user.setTime(rs.getString("date"));
					user.setRole(rs.getString("role"));
					
					return user;
				}
			}

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

	/**
	 * 
	 * @param changePasswd 
	 * @return
	 */
	public static ResultSet changePasswd(String sql)
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
	 * 从数据库搜索到相应用户，修改用户个人信息，然后更新数据；
	 * 
	 * @param modifyUserinfo 
	 * @return
	 */
	public static User modifyUserinfo(String myUsername, String mobilePhone, String phone, String address, String email)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from user WHERE username='" + myUsername + "'");

			while (rs.next())
			{		
				//更新一行数据
				rs.updateString("mobilePhone", mobilePhone);
				rs.updateString("phone", phone);
				rs.updateString("address", address);
				rs.updateString("email", email);
				rs.updateRow();
			}

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
