package org.library.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.library.dao.UserDao;
import org.library.model.Profession;
import org.library.model.User;
import org.library.service.UserService;


public class UserServiceImpl implements UserService
{
	ResultSet rs = null;
	
	/**
	 * 注册
	 * @param user 用户信息
	 */
	public void register(User user)
	{
		PreparedStatement pstmt = null;
		String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			pstmt = UserDao.prepareStatement(sql);

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
			
			pstmt.executeUpdate();			//将新用户注册信息保存更新到数据库；
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

	
	public void changePasswd(String username, String newPassword)
	{
		String sql = "select * from user WHERE username='" + username + "'";
		
		ResultSet rs = UserDao.executeQuery(sql);
		
		try {
			if(rs.next())
			{
				rs.updateString("password", newPassword);	//更新一行数据
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

	/**
	 * 从数据库搜索到相应用户，修改用户个人信息，然后更新数据；
	 * @param 
	 */
	@Override
	public void modifyUserinfo(String username, String mobilePhone,
			String phone, String address, String email)
	{
		String sql = "select * from user WHERE username='" + username + "'";
		
		ResultSet rs = UserDao.executeQuery(sql);
		
		try {
			if(rs.next())
			{
				//更新一行数据
				rs.updateString("mobilePhone", mobilePhone);
				rs.updateString("phone", phone);
				rs.updateString("address", address);
				rs.updateString("email", email);
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

	/**
	 * 接收一个用户名，搜索用户是否存在数据库里，若存在返回对应用户资料的一个User集合，否则返回空；
	 * 
	 * @param myUsername
	 * @return
	 */
	@Override
	public User findUser(String username)
	{
		String sql = "select * from user WHERE username='" + username + "'";
		
		ResultSet rs = UserDao.executeQuery(sql);
		
		try {
			if(rs.next())
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
	
		return null;
	}

	/**
	 * 根据academyId(学院ID)搜索学院，返回一个包含学院内所有专业名称专业ID和的集合
	 * @param academy
	 * @return
	 */
	@Override
	public Set<Profession> findAcademy(String academyId)
	{
		Set<Profession> set = new HashSet<Profession>();
		
		String sql = "select * from profession WHERE academyId='" + academyId + "'";
		ResultSet rs = UserDao.executeQuery(sql);
		
		try {
			if(rs.next())
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
		return null;
	}

	/**
	 * 根据professionId(专业ID)搜索，返回班级的数量
	 * @param professionId
	 * @return
	 */
	@Override
	public Map<String, Object> findProfession(String academyID,
			String professionId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		String sql = "select * from profession WHERE id='" + professionId + "'";
		ResultSet rs = UserDao.executeQuery(sql);
		
		try
		{
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
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
}
