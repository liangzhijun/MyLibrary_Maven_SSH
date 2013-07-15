package org.library.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.dao.UserDao;
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
		
		UserDao.saveUser(user);//将元素存入数据库
	}

	public void changePasswd(String myUsername, String newPassword)
	{
		String sql = "select * from user WHERE username='" + myUsername + "'";
		
		ResultSet rs = UserDao.changePasswd(sql);
		
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
}
