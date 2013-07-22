package org.library.service;

import org.library.model.User;

public interface UserService
{
	public void register(User user);
	
	public User findUser(String username);
	
	public void changePasswd(String myUsername, String newPassword);
	
	public void modifyUserinfo(String username, String mobilePhone, String phone, String address, String email);
}
