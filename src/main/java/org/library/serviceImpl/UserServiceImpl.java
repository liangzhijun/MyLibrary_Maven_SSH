package org.library.serviceImpl;

import org.library.dao.Dao;
import org.library.model.User;
import org.library.service.UserService;


public class UserServiceImpl implements UserService
{
	
	/**
	 * 注册
	 * @param user 用户信息
	 */
	public void register(User user)
	{
		Dao.save(user);
	}
	
	public void changePasswd(String username, String newPassword)
	{
		User user = new User();
		user = (User)Dao.get(user, username);
		
		user.setPassword(newPassword);
		Dao.update(user);

	}

	/**
	 * 从数据库搜索到相应用户，修改用户个人信息，然后更新数据；
	 * @param 
	 */
	@Override
	public void modifyUserinfo(String username, String mobilePhone,
			String phone, String address, String email)
	{
		User user = new User();
		user = (User)Dao.get(user, username);
		
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		
		Dao.update(user);
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
		User user = new User();
		
		user = (User)Dao.get(user, username);
		
		if(user != null)
			return user;
	
		else return null;
	}
}
