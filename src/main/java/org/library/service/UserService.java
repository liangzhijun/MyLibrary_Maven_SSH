package org.library.service;

import org.library.dao.UserDao;
import org.library.model.User;

public class UserService
{
	/**
	 * 注册
	 * @param user 用户信息
	 */
	public static void register(User user)
	{
		UserDao.saveUser(user);//将元素存入数据库
	}

}
