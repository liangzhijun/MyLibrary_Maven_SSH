<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8">
<title>登录我的图书馆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}
#submenu {
height: 20px;
font-size: 12px;
text-align: left;
background-color: #E2E2E2;
padding-top: 6px;
padding-left: 7px;
color: #cacaca;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

<script type="text/javascript">
		
	var xmlHttpRequest = null; //声明一个空对象以接收XMLHttpRequest对象
	
	function ajaxSubmit()
	{
		if(window.ActiveXObject) // IE浏览器
		{
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if(window.XMLHttpRequest) // 除IE外的其他浏览器实现
		{
			xmlHttpRequest = new XMLHttpRequest();
		}
		
		if(null != xmlHttpRequest)//处理发送
		{
			var username = document.getElementById("usernameID").value;
			var password = document.getElementById("passwordID").value;
			
			xmlHttpRequest.open("POST", "/ajaxLogin.htm", true);
			//关联好ajxa的回调函数
			xmlHttpRequest.onreadystatechange = ajaxCallback;
			
			//真正向服务器端发送数据
			// 使用post方式提交，必须要加上如下
			xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlHttpRequest.send("username="+ username + "&password=" + password);
			
		}
	}
	
	
	function ajaxCallback()//处理响应
	{
		if(xmlHttpRequest.readyState == 4)
		{
			if(xmlHttpRequest.status == 200)//Http响应的状态
			{
				var responseTest = xmlHttpRequest.responseText;
				var user = JSON.parse(responseTest);
				var status = user.status; 	//status 作为状态码使用
				var message = user.uri;
				
				if(status == 200)							//验证通过！返回的是登录用户角色相对应“URI”地址的字符串
				{
					window.location = user.uri;				//连接到用户验证通过相对应的uri
					return;
				}
				
				if(status == 400)						//返回的是错误
				{
					document.getElementById("div1").innerHTML = user.error;
					return;
				}
			}
		}
	}
	
	</script>

</head>

<body>

		<form class="form-signin" onsubmit="ajaxSubmit(); return false;" method="post">
			<h2 class="form-signin-heading">登录我的图书馆</h2>
			
			<input type="text" class="input-block-level"placeholder="Username" name="username" id="usernameID" > 
			
			<input type="password" class="input-block-level" placeholder="Password" name="password" id="passwordID">
			<div id="div1"></div>
			
			<button class="btn btn-large btn-primary" type="submit" >Sign in</button>
			
			<a href = "/Register.jsp" target= "_blank" class="btn btn-large btn-primary">Register</a><br>
		</form>



</body>
</html>
