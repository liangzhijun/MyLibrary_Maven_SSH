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

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
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
var academy;
var profession;
var unit;

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
		var mobilePhone = document.getElementById("mobilePhoneID").value;
		var gender = document.getElementsByName("gender");
		
		if(gender[0].checked)
			gender = gender[0].value;
		else
			gender = gender[1].value;
			
		xmlHttpRequest.open("POST", "/ajaxRegister.htm", true);
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
				var text = JSON.parse(responseTest);
				var status = text.status; 	//status 作为状态码使用
				var message = text.uri;
				
				if(status == 200)							//验证通过！返回的是登录用户角色相对应“URI”地址的字符串
				{
					window.location = text.uri;				//连接到用户成功注册后的页面uri
					return;
				}
				
				if(status == 400)						//返回的是错误
				{
					document.getElementById("div1").innerHTML =text.error;
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
				var text = JSON.parse(responseTest);
				var status = text.status; 	//status 作为状态码使用
				var message = text.uri;
				
				if(status == 200)							//验证通过！
				{
					document.getElementById("td1").innerHTML = "";	
					
					//显示图片
					document.getElementById("successImg").className = "";
					return;
				}
				
				if(status == 400)						//返回的是错误信息
				{
					//隐藏图片
					document.getElementById("successImg").className = "hide";
					
					document.getElementById("td1").innerHTML = text.error;
					return;
				}
			}
		}
	}
	
	function Change_Select(){
	    //当第一个下拉框的选项发生改变时调用该函数
	      academy = document.getElementById('one').value;
	     
	      profession = document.getElementById('two');
	      
	      if(academy == 0)
	   	  {
	    	  document.getElementById("two").className = "hide"; //隐藏第二个下拉框
	    	  document.getElementById("three").className = "hide"; //隐藏第三个下拉框
	    	  return;
	   	  }
	      document.getElementById("two").className = "";	//把第二个下拉框显示
	      document.getElementById("three").className = "hide"; //隐藏第三个下拉框
	      
	      profession.options.length=0;
	      //每次获得新的数据的时候先把每二个下拉框架的长度清0
	      
	      if(window.XMLHttpRequest){
		        req = new XMLHttpRequest();
		  }else if(window.ActiveXObject){
		        req = new ActiveXObject("Microsoft.XMLHTTP");
		   }
		  if(req){
		        req.open("POST", "/select.htm", true);
		        req.onreadystatechange = callback;
		        
		        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		        req.send("academy=" + academy);
		      }
		  }
	    function callback(){
		      if(req.readyState ==4){
		        if(req.status ==200){
		          parseMessage();
		        }else{
		          alert("Not able to retrieve description" + req.statusText);
		        }
		      }
		    }
		    function parseMessage(){
		      var responseTest = req.responseText;
		      var test = JSON.parse(responseTest);
		      var profession = test.profession;
		      var size = test.size;
		      
		      
		      var street = document.getElementById('two');
		      street.options.length=0;
		      
		      var option = new Option("--请选择--", 0);
		      try{
		          street.add(option);
		        }
		        catch(e){
		        }
		    
		      for(var i = 0; i < size; i++){
		    	var profession2 = profession[i].profession;
		    	var id = profession[i].id;
		    	  
		        option = new Option(profession2, id);
		        try{
		          street.add(option);
		        }
		        catch(e){
		        }
		      }
		    }
	    
	     
	    //*************************************************************************
	      function Change_Select2(){
	      profession = document.getElementById('two').value;
	      
	      if(profession == 0)
	      {
	    	 document.getElementById("three").className = "hide"; //隐藏第三个下拉框
	    	 return;
	      }
	      
	      document.getElementById("three").className = "";	//把第三个下拉框显示
	      
	      if(window.XMLHttpRequest){
	        req = new XMLHttpRequest();
	      }else if(window.ActiveXObject){
	        req = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      if(req){
	        req.open("POST", "/select2.htm", true);
	        req.onreadystatechange = callback2;
	        
	        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        req.send("academy="+ academy + "&profession=" + profession);
	      }
	    }
	    function callback2(){
	      if(req.readyState ==4){
	        if(req.status ==200){
	          parseMessage2();
	        }else{
	          alert("Not able to retrieve description" + req.statusText);
	        }
	      }
	    }
	    function parseMessage2(){
	      var responseTest = req.responseText;
	      var test = JSON.parse(responseTest);
	      var classlist = test.classlist;
	      unit = test.unit;
	      
	      var street = document.getElementById('three');
	      street.options.length=0;
	      
	      var option = new Option("--请选择--", 0);
	      try{
	          street.add(option);
	        }
	        catch(e){
	        }
	    
	      for(var i = 1; i <= classlist; i++){
	    	  
	        option = new Option("（" + i + "）" + "  班", i);
	        try{
	          street.add(option);
	        }
	        catch(e){
	        }
	      }
	    }
	    
	    function Change_Select3(){
	    var classes = document.getElementById('three').value;
	    
	    unit = unit + "/（" + classes + "）班";
	    }
	    
	    
  		</script>
</head>

<body>

	<div class="container">
		<h2 style="color: rgb(226, 106, 39)">大学图书馆用户注册</h2>
		
		<form class="form-signin" onsubmit="ajaxSubmit(); return false;"  method="post">
		
  		
  		<table width="95%" border="0" cellpadding="0" cellspacing="1">
  		 <tbody>
  		 		 <tr>
            		<td align="left">用户名 </td>
            		<td align="left"><input class="input" type="text" name="username" id="usernameID" onblur="findUser()" size="20" style="width:200px"></td>
            		<td align="left" id="successImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: red; font-size: 13px;"  id="td1"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">密   码 </td>
            		<td align="left"><input class="input" type="password" name="password" id="passwordID" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">确认密码 </td>
            		<td align="left"><input class="input" type="password" name="repassword" id="repasswordID" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">邮箱帐号 </td>
            		<td align="left"><input class="input" type="text" name="email" id="emailID" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">姓 名 </td>
            		<td align="left"><input class="input" type="text" name="name" id="nameID" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">性 别</td>
            		<td align="left">男<input type="radio" name="gender" value="男" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		 女<input type="radio" name= "gender" value="女" >
            		</td>
          		</tr>
          		
          		<tr>
            		<td align="left">手机号码 </td>
            		<td align="left"><input class="input" type="text" name="mobilePhone" id="mobilePhoneID" size="20" style="width:200px"></td>
          		</tr>
  		 </tbody>
  		</table>
  		
  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr>
          			<td align="left">单 位 </td>
  					<td align="left"><select name="one" id="one" onChange="Change_Select()" style="width: 130px">
   					<!--第一个下拉菜单-->
   						<option value="0">请选择单位</option>
						<option value="1">计算机学院</option>
						<option value="2">土木工程学院</option>
						<option value="3">外国语学院</option>
  					</select> <select name="two" id="two" onChange="Change_Select2()" class="hide" style="width: 120px">
   					<!--第二个下拉菜单-->
   					<option value="0">--请选择--</option>
  					</select> <select name="three" id="three" onChange="Change_Select3()" class="hide" style="width: 120px">
   					<!--第三个下拉菜单-->
   					<option value="0">--请选择--</option>
  					</select></td>
 				</tr>
 		</table>		
  	 
		<div id="div1"></div>
  		<input class="btn btn-large btn-primary" type="submit" value="submit">
  		
</body>
</html>
