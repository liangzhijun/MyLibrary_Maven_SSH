package org.library.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.library.dao.UserDao;
import org.library.model.Info;
import org.library.model.Profession;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class UserController
{	
	/**
	 * 用户登录验证
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ajaxLogin.htm",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String loginAjax(@RequestParam String username,		
			@RequestParam String password, HttpSession session)					
	{
		User user = UserDao.findUser(username);
		Gson gson = new Gson();
		Info info = new Info();
		String jsontext;

		if (user != null && password.equals(user.getPassword()))
		{
			session.setAttribute("user", user);
			session.setAttribute("status", "logined");// 用户登陆状态信息：已登陆
			
			if (user.getRole().equals("student"))
			{
				info.setStatus(200);
				info.setUri("/myLibrary.htm");
				info.setError("null");
				jsontext = gson.toJson(info);
				
				return jsontext;		  
			}
			else
			{
				info.setStatus(200);
				info.setUri("/adminLibrary.jsp");
				info.setError("null");
				jsontext = gson.toJson(info);
				
				return jsontext; 
			}
		}
		
		info.setStatus(400);
		info.setUri("null");
		info.setError("登陆名或密码错误");
		jsontext = gson.toJson(info);

		return jsontext; 
	}
	

	/**
	 * 用户退出，把user从session和model中删除，用户登陆状态信息"status"设为空。返回到登陆页面
	 * 
	 * @param model
	 * @param session
	 * @return redirect:/signIn.jsp
	 */
	@RequestMapping(value = "/exit.htm")
	public String exit(HttpSession session, ModelMap model)
	{
		session.removeAttribute("user");
		
		session.removeAttribute("status");

		return "redirect:/signIn.jsp";
	}

	/**
	 * 学生用户注册
	 * 
	 * @param username
	 * @param password
	 * @param repassword
	 * @param email
	 * @param name
	 * @param gender
	 * @param unit
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ajaxRegister.htm",produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String register(String username, String password,  String repassword,
			String email,  String name,String gender,  String unit,
			 String mobilePhone, HttpSession session, ModelMap model)
	{
		Gson gson = new Gson();
		Info info = new Info();
		String jsontext;
		
		String result = "";

		System.out.println("started register"); 
		
		if (null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		else if (username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		// 密码的长度均须在4---10之间
		if (!password.equals(repassword))
		{
			result += "密码不一致！";
		}
		if (password == null || password.length() < 4 || password.length() > 10)
		{
			result += "密码的长度须在4---10之间！";
		}
		if (null == email || "".equals(email))
		{
			result += "邮箱不可以为空！";
		}
		if (null == name || "".equals(name))
		{
			result += "姓名不可以为空！";
		}
		if(null == unit || "".equals(unit))
		{
			result += "单位必需要填写！";
		}

		if (result == "")
		{
			User user = new User();

			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setUnit(unit);
			user.setMobilePhone(mobilePhone);
			user.setRole("student");// 默认角色的参数

			UserService.register(user);// 注册

			session.setAttribute("result", "注册成功！");
			
			info.setStatus(200);
			info.setUri("/success.jsp");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}

		//session.setAttribute("result", result);

		info.setStatus(400);
		info.setError(result);
		jsontext = gson.toJson(info);
		
		return jsontext;
	}
	
	@RequestMapping(value = "/ajaxFindUser.htm",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String findUser(@RequestParam String username)
	{
		Gson gson = new Gson();
		Info info = new Info();
		String jsontext;
		
		if(username == null || "".equals(username))
		{
			info.setStatus(400);
			info.setError("用户名不可以为空");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		if (username.length() < 4 || username.length() > 10)
		{
			info.setStatus(400);
			info.setError("用户名长度应该在4到10之间");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		if(UserDao.findUser(username) == null)
		{
			info.setStatus(200);
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		
		info.setStatus(400);
		info.setError("该用户已存在");
		jsontext = gson.toJson(info);
		
		return jsontext;
	}
	
	/**
	 * 根据academy(学院ID)搜索学院，返回一个包含学院内所有专业名称专业ID和的集合
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="select.htm", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String select(String academy)
	{
		Gson gson = new Gson();
		String jsontext;
		
		Set<Profession> profession = UserDao.findAcademy(academy);
		int size = profession.size();
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("size", size);
		map.put("profession", profession);
		
		jsontext = gson.toJson(map);
		
		return jsontext;
	}
	
	/**
	 * 根据profession(专业ID)搜索，返回班级的数量
	 * @param academy
	 * @param profession
	 * @return
	 */
	@RequestMapping(value="select2.htm", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String select2(@RequestParam String academy, @RequestParam String profession)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		String jsontext;
		
		map = UserDao.findProfession(academy, profession);
		jsontext = gson.toJson(map);
		
		return jsontext;
	}

	/**
	 * 用户修改密码
	 * 
	 * @param password
	 * @param newPassword
	 * @param repassword
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePasswd.htm",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswd(@RequestParam String password,
			@RequestParam String newPassword, @RequestParam String repassword, HttpSession session,
			ModelMap model)
	{
		Gson gson = new Gson();
		Info info = new Info();
		String jsontext;
		
		User user = (User)session.getAttribute("user");

		System.out.println(user.getPassword());

		if (!password.equals(user.getPassword()))
		{
			info.setStatus(400);
			info.setError("原密码输入有误");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		else if (newPassword == null || newPassword.length() < 4
				|| newPassword.length() > 10)
		{
			info.setStatus(400);
			info.setError("新密码的长度须在4---10之间");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		else if (!newPassword.equals(repassword))
		{
			info.setStatus(400);
			info.setError("两次密码不一致");
			jsontext = gson.toJson(info);
			
			return jsontext;
		}
		else
		{
			String username = user.getUsername();
			UserDao.changePasswd(username, repassword);

			user.setPassword(newPassword);
		}

		model.addAttribute("result", "成功修改密码！");
		
		info.setStatus(200);
		info.setUri("/success.jsp");
		jsontext = gson.toJson(info);
		
		return jsontext;
	}

	/**
	 * 用户修改个人信息
	 * 
	 * @param mobilePhone
	 * @param phone
	 * @param address
	 * @param email
	 * @param model
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/modifyUserinfo.htm", method = RequestMethod.POST)
	public String modifyUserinfo(@RequestParam String mobilePhone,
			@RequestParam String phone, @RequestParam String address,
			@RequestParam String email, HttpSession session, ModelMap model, HttpServletResponse resp)
	{
		User user = (User) session.getAttribute("user");

		String username = user.getUsername();

		UserDao.modifyUserinfo(username, mobilePhone, phone, address, email);
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);

		model.addAttribute("result", "个人信息修改成功，");
		return "success";
	}
}
