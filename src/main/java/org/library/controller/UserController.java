package org.library.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.library.model.Info;
import org.library.model.Profession;
import org.library.model.User;
import org.library.service.ProfessionService;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController
{	
	UserService userService;
	ProfessionService professionService;
	
	@Resource
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	@Resource
	public void setProfessionService(ProfessionService professionService)
	{
		this.professionService = professionService;
	}

	/**
	 * 用户登录验证
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/ajaxLogin.htm",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String loginAjax(@RequestParam String username,		
			@RequestParam String password, HttpSession session) throws IOException				
	{
		User user = userService.findUser(username);
		//Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
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
				jsontext = mapper.writeValueAsString(info);
				
				return jsontext;		  
			}
			else
			{
				info.setStatus(200);
				info.setUri("/adminLibrary.jsp");
				info.setError("null");
				jsontext = mapper.writeValueAsString(info);
				
				return jsontext; 
			}
		}
		
		info.setStatus(400);
		info.setUri("null");
		info.setError("登陆名或密码错误");
		jsontext = mapper.writeValueAsString(info);

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
	@RequestMapping(value="/register.htm", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String register(@ModelAttribute User user, HttpSession session, ModelMap model)
	{
		
			user.setRole("student");// 默认角色的参数

			userService.register(user);// 注册

			session.setAttribute("result", "注册成功！");
		
			return "/myLibrary.jsp";
	}
	
	@RequestMapping(value = "/ajaxFindUser.htm",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findUser(@RequestParam String username) throws IOException
	{
		//Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		Info info = new Info();
		String jsontext;
		
		if(username == null || "".equals(username))
		{
			info.setStatus(400);
			info.setError("用户名不可以为空");
			//jsontext = gson.toJson(info);
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		if (username.length() < 4 || username.length() > 10)
		{
			info.setStatus(400);
			info.setError("用户名长度应该在4到10之间");
			//jsontext = gson.toJson(info);
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		if(userService.findUser(username) == null)
		{
			info.setStatus(200);
			//jsontext = gson.toJson(info);
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		
		info.setStatus(400);
		info.setError("该用户已存在");
		//jsontext = gson.toJson(info);
		jsontext = mapper.writeValueAsString(info);
		
		return jsontext;
	}
	
	/**
	 * 
	 * @param photo
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/user/upload.htm", method = RequestMethod.POST)
	public String upload(MultipartFile photo, HttpSession session, ModelMap model) throws IOException
	{
		User user = (User)session.getAttribute("user");
		
		System.out.println(photo.getContentType() + "、" + photo.getName() + "、 " + photo.getOriginalFilename());
		
		String realpath = "D:\\MyLibrary/upload/" + user.getUsername() + "/";
		System.out.println(realpath);
		
		String value = photo.getOriginalFilename();
		String fileName = "photo" + value.substring(value.length() - 4);
		
		FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realpath + fileName));
		
		//model.addAttribute("result", "头像上传成功!");
		if(user.getRole().equals("student"))
		{
			return "redirect:/myLibrary.jsp";
		}
		else	//==admin
			return  "redirect:/adminLibrary.jsp";
	}
	
	/**
	 * 根据academy(学院ID)搜索学院，返回一个包含学院内所有专业名称专业ID和的集合
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="select.htm", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String select(String academy) throws IOException
	{
		//Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		
		String jsontext;
		
		Set<Map<String, String>> profession = professionService.findAcademy(academy);
		int size = profession.size();
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("size", size);
		map.put("profession", profession);
		
		//jsontext = gson.toJson(map);
		jsontext = mapper.writeValueAsString(map);
		
		System.out.println(jsontext);
		
		return jsontext;
	}
	
	/**
	 * 根据profession(专业ID)搜索，返回班级的数量
	 * @param academy
	 * @param profession
	 * @return
	 */
	@RequestMapping(value="select2.htm",produces="application/json", method = RequestMethod.POST)
	@ResponseBody
	public Profession select2(@RequestParam String academy, @RequestParam String profession) throws IOException
	{
		/*ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		String jsontext; */
		 
		Profession pro = professionService.findProfession(academy, profession);
	/*	jsontext = mapper.writeValueAsString(pro);
		System.out.println(jsontext);*/ 
		
		return pro;
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
			@RequestParam String newPassword, @RequestParam String repassword, HttpSession session) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		
		Info info = new Info();
		String jsontext;
		
		User user = (User)session.getAttribute("user");

		System.out.println(user.getPassword());

		if (!password.equals(user.getPassword()))
		{
			info.setStatus(400);
			info.setError("原密码输入有误");
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		else if (newPassword == null || newPassword.length() < 4
				|| newPassword.length() > 10)
		{
			info.setStatus(400);
			info.setError("新密码的长度须在4---10之间");
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		else if (!newPassword.equals(repassword))
		{
			info.setStatus(400);
			info.setError("两次密码不一致");
			jsontext = mapper.writeValueAsString(info);
			
			return jsontext;
		}
		else
		{
			String username = user.getUsername();
			
			userService.changePasswd(username, repassword);

			user.setPassword(newPassword);
		}

		session.setAttribute("result", "成功修改密码！");
		
		info.setStatus(200);
		info.setUri("/success.jsp");
		jsontext = mapper.writeValueAsString(info);
		
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
	public String modifyUserinfo(@RequestParam String mobilePhone,@RequestParam String phone, @RequestParam String address,
			@RequestParam String email, HttpSession session, ModelMap model, HttpServletResponse resp)
	{
		User user = (User) session.getAttribute("user");

		String username = user.getUsername();

		userService.modifyUserinfo(username, mobilePhone, phone, address, email);
		
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);

		model.addAttribute("result", "个人信息修改成功，");
		return "success";
	}
	
	/**
	 * 输出图片
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping("/outputImage.htm")
	public void outputImage(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		User user = (User)req.getSession().getAttribute("user");
		
		String filePath = "D://MyLibrary/upload/" + user.getUsername() + "/photo.jpg";  
        
        FileInputStream is = new FileInputStream(filePath); // 以byte流的方式打开文件 d:\1.gif   
        
        resp.setContentType("image/*"); //设置返回的文件类型   
       
        resp.setHeader("Content-Disposition", "attachment; filename=\"photo.jpg\"");
        
        OutputStream os = resp.getOutputStream(); //得到向客户端输出二进制数据的对象   
        
        byte[] b = new byte[1024];
 		while (is.read(b) != -1)	//指定原始字节流大小
 		{
 			os.write(b);
 		}
        os.flush();  
        os.close();   
        is.close();  
	}
	
}
