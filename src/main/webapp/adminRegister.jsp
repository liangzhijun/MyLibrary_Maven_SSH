<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Sign in &middot; Twitter Bootstrap</title>
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
	max-width: 520px;
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

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.hide{
	display: none;
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
		var repassword = document.getElementById("repasswordID").value;
		var email = document.getElementById("emailID").value;
		var name = document.getElementById("nameID").value;
		var unit = document.getElementById("unitID").value;
		var mobilePhone = document.getElementById("mobilePhoneID").value;
		var gender = document.getElementsByName("gender");
		
		if(gender[0].checked)
			gender = gender[0].value;
		else
			gender = gender[1].value;
			
		xmlHttpRequest.open("POST", "/adminRegister.htm", true);
		//关联好ajxa的回调函数
		xmlHttpRequest.onreadystatechange = ajaxCallback;
		
		//真正向服务器端发送数据
		// 使用post方式提交，必须要加上如下
		xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttpRequest.send("username="+ username + "&password=" + password + "&repassword=" + repassword + "&email=" + email + "&name=" + name + "&unit=" + unit + "&mobilePhone=" + mobilePhone + "&gender=" + gender);
		
	}
}	
  	
  	function ajaxCallback()
  	{		
  		if(xmlHttpRequest.readyState == 4)
		{
			if(xmlHttpRequest.status == 200)//Http响应的状态
			{
				var responseTest = xmlHttpRequest.responseText;
				var status = responseTest.substring(0, 3);
				var message = responseTest.substring(4);
				
				if(status == 200)							//验证通过！返回的是登录用户角色相对应“URI”地址的字符串
				{
					window.location = responseTest.substring(4);				//连接到用户角色相对应的主页面
					return;
				}
				
				if(status == 400)						//返回的是错误
				{
					document.getElementById("div1").innerHTML = responseTest.substring(4);
					return;
				}
			}
		}		
  	}
  	
	function findUser()
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
			
			xmlHttpRequest.open("POST", "/ajaxFindUser.htm", true);
			//关联好ajxa的回调函数
			xmlHttpRequest.onreadystatechange = ajaxCallback2;
			
			//真正向服务器端发送数据
			// 使用post方式提交，必须要加上如下
			xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlHttpRequest.send("username=" + username);
		}
	}
	
	function ajaxCallback2()
	{
		if(xmlHttpRequest.readyState == 4)
		{
			if(xmlHttpRequest.status == 200)//Http响应的状态
			{
				var responseTest = xmlHttpRequest.responseText;
				var status = responseTest.substring(0, 3);
				var message = responseTest.substring(4);
				
				if(status == 200)							//验证通过！
				{
					document.getElementById("td1").innerHTML = "";			
					
					//显示图片
					document.getElementById("successImg").className = "";
					return;
				}
				
				if(status == 400)						//返回的是错误信息
				{
					document.getElementById("td1").innerHTML = responseTest.substring(4);
					return;
				}
			}
		}
	}
  		
  		</script>
</head>

<body>

	<div class="container">
		<h2 style="color: rgb(226, 106, 39)">大学图书馆注册 管理员</h2>
		
		<form class="form-signin" onsubmit="ajaxSubmit(); return false;" action="adminRegister.htm" method="post">
  	
  		<table width="95%" border="0" cellpadding="0" cellspacing="1">
  		 <tbody>
  		 		 <tr>
            		<td align="left">用户名 </td>
            		<td align="left"><input class="input" type="text" name="username" id="usernameID" onblur="findUser()" size="20" style="width:210px"></td>
            		<td align="left" id="successImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: red; font-size: 13px;"  id="td1"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">密   码 </td>
            		<td align="left"><input class="input" type="password" name="password" id="passwordID" size="20" style="width:210px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">确认密码 </td>
            		<td align="left"><input class="input" type="password" name="repassword" id="repasswordID" size="20" style="width:210px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">邮箱帐号 </td>
            		<td align="left"><input class="input" type="text" name="email" id="emailID" size="20" style="width:210px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">姓 名 </td>
            		<td align="left"><input class="input" type="text" name="name" id="nameID" size="20" style="width:210px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">性 别</td>
            		<td align="left">男<input type="radio" name="gender" value="男" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		 女<input type="radio" name= "gender" value="女" >
            		</td>
          		</tr>
          		
          		<tr>
            		<td align="left">单 位 </td>
            		<td align="left"><input class="input" type="text" name="unit" id="unitID" size="20" style="width:210px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">手机号码 </td>
            		<td align="left"><input class="input" type="text" name="mobilePhone" id="mobilePhoneID" size="20" style="width:210px"></td>
          		</tr>
  		 
  		 </tbody>
  		</table>
  	 
		<div id="div1"></div>
  		<input class="btn btn-large btn-primary" type="submit" value="submit">
  		
  		</form>


		</div>





</body>
</html>
