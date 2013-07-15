package org.library.service;

import org.library.model.User;

public interface UserService
{
	public void register(User user);
	
	public void changePasswd(String myUsername, String newPassword);
}
