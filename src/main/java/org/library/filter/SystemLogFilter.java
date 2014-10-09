package org.library.filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.SessionFactory;
import org.library.model.SystemLog;
import org.library.model.User;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SystemLogFilter implements Filter
{
	//用map储存所有请求日志内容。请求地址为key、请求体现的信息为value;
	private Map<String, String> requestLogs;
	private SystemLog log;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("SystemLogFilter Started!");
		
		//初始化时获得系统日志配置文件所有请求对应的日志信息
		requestLogs = LogDomParse("cfg/systemLog-cfg.xml");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		User user = (User)session.getAttribute("user");
		String requestURI = req.getRequestURI();		//取得正在访问的URI
		
		log = new SystemLog();
		
		if(requestLogs.containsKey(requestURI))				
		{
			String content =  requestLogs.get(requestURI);
			String ip = getIpAddr(req);
			Date date = new Date();
			
			log.setName(content); log.setIp(ip); log.setNowtime(date); 
			
			System.out.println("日志输出：");
			if(user != null)
			{
				System.out.println("内容：" + content + "-----" + "用户名：" + user.getName() + "-----"
						+ "requestURI：" + requestURI + "-----" + "时间：" + date.toLocaleString() + "-----Ip: " +ip);
				log.setUsername(user.getUsername()); log.setRealname(user.getName()); log.setUnit(user.getUnit());
			}
			else
				System.out.println("内容：" + content + "-----" + "requestURI：" 
						+ requestURI + "-----" + "时间：" + date.toLocaleString()+ "-----IP:" +ip);
			
			saveLog(log);
		}
		
		chain.doFilter(request,response);
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}
	
	private String getIpAddr(HttpServletRequest request) 
	{
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return ip;
	} 
	
	private Map<String, String> LogDomParse(String xml)
	{
		 Map<String, String> map = new HashMap<String, String>();
		
		 DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

		 try 
		 {
			 DocumentBuilder dombuilder = domfac.newDocumentBuilder();

			 InputStream is = new FileInputStream(xml);

			 Document doc = dombuilder.parse(is);

			 Element root = doc.getDocumentElement();
			 NodeList logControl = root.getChildNodes();

			 if(logControl != null)
			 {
				 for(int i = 0; i < logControl.getLength(); i++)
				 {
					 Node logContext = logControl.item(i);

					 if(logContext.getNodeType() == Node.ELEMENT_NODE)
					 {
						 String path = logContext.getAttributes().getNamedItem("path").getNodeValue();
						 String context = logContext.getAttributes().getNamedItem("log-name").getNodeValue();
						 
						 map.put(path, context);
						 System.out.println("path: "+path+ "----------" +context);
					 }
				 }
			 }
		 } 
		 catch (ParserConfigurationException e) {
			 e.printStackTrace();
		 } 
		 catch (FileNotFoundException e) {

			 e.printStackTrace();
		 } 
		 catch (SAXException e) {

			 e.printStackTrace();
		 }
		 catch (IOException e) 
		 {
			 e.printStackTrace();
		 }
		 
		 return map;
	}
	
	private void saveLog(SystemLog log)
	{
		//SessionFactory sessionFactory = (SessionFactory)ContextLoaderListener.getCurrentWebApplicationContext().getBean("sessionFactory");
		SessionFactory sessionFactory = (SessionFactory)ContextLoader.getCurrentWebApplicationContext().getBean("sessionFactory");
		sessionFactory.openSession().save(log);
	}
}


