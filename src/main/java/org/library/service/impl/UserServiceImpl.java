package org.library.service.impl;

import javax.annotation.Resource;

import org.library.dao.Dao;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
	private Dao dao;
	
	@Resource(name="dao")
	public void setDao(Dao dao)
	{
		this.dao = dao;
	}

	/**
	 * 注册
	 * @param user 用户信息
	 */
	public void register(User user)
	{
		dao.save(user);
	}
	
	public void changePasswd(String username, String newPassword)
	{
		User user = (User)dao.get(User.class, username);
		
		user.setPassword(newPassword);
		dao.update(user);

	}

	/**
	 * 从数据库搜索到相应用户，修改用户个人信息，然后更新数据；
	 * @param 
	 */
	@Override
	public void modifyUserinfo(String username, String mobilePhone,
			String phone, String address, String email)
	{
		User user = (User)dao.get(User.class, username);
		
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		
		dao.update(user);
	}

	/**
	 * 接收一个用户名，搜索用户是否存在数据库里，若存在返回对应用户资料的一个User集合，否则返回空；
	 * 
	 * @param myUsername
	 * @return
	 */
	public User findUser(String username)
	{
		User user = (User)dao.get(User.class, username);
		
		if(user != null)
			return user;
	
		else return null;
	}
}
