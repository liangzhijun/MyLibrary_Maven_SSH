package org.library.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.library.dao.UriDao;
import org.library.model.User;

public class AccessControlFilter implements Filter
{
	private Map<String, String> uris;
	private Set<String> shareUris;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("Filter Started!");
		
		//初始化时取得需角色需求的包含地址与角色的一个集合，一个以uri为key，role(角色)为value的map
		uris = UriDao.findAll();
		
		//初始化时取得所有角色共享的全部uri的一个Set集合，
		shareUris = UriDao.findShareUris();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		String requestURI = req.getRequestURI();		//取得正在访问的URI
		
		if(!uris.containsKey(requestURI))				//无需验证的URI：不需要用户登录便可访问的URI
		{
			chain.doFilter(request,response);
			return;										//do not need to check
		}
		
		HttpSession session = req.getSession(false);	//验证需要用户登录访问的URI
	
		if(session == null)								//session == null 时重定向到登录页面
		{
			((HttpServletResponse)response).sendRedirect("/signIn.jsp");
			return;
		}
		
		User user = (User)session.getAttribute("user");
		
		if(user == null)								//未登录时重定向到登录页面
		{
			((HttpServletResponse)response).sendRedirect("/signIn.jsp");
			return;
		}	
		
		if(shareUris.contains(requestURI))	//已登录.如果访问的URI是共享的。无需指定用户角色，允许访问通过
		{	
			System.out.println("Share-URI");
			chain.doFilter(request,response);	
			return;
		}
		
		String role = uris.get(requestURI);				//方法返回请求uri相对role(角色)
		String userRole = user.getRole();
		
		//需指定用户角色URI地址,如果用户角色与访问的URI需求角色不一致时，重定向到登录页面
		if(!role.equals(userRole))
		{
			((HttpServletResponse)response).sendRedirect("/signIn.jsp");
			return;
		}
		
		//用户角色与访问URI地址所需角色角色一致，允许访问
		chain.doFilter(request,response);		
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
