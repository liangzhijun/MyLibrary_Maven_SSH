package org.library.service;

import java.util.Map;
import java.util.Set;

import org.library.model.Profession;
import org.library.model.User;

public interface UserService
{
	public void register(User user);
	
	public Set<Profession> findAcademy(String academyId);
	
	public Map<String, Object> findProfession(String academyID, String professionId);
	
	public User findUser(String username);
	
	public void changePasswd(String myUsername, String newPassword);
	
	public void modifyUserinfo(String username, String mobilePhone, String phone, String address, String email);
}
