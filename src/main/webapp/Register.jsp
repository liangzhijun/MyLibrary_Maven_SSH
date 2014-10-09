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
<script type="text/javascript" src="js/jquery-2.0.2.js"></script>


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

.icon_delS {
background-position: 0 -50px;
background-position-x: 0px;
background-position-y: -50px;
}

</style>

<script type="text/javascript">
  		
var xmlHttpRequest = null; //声明一个空对象以接收XMLHttpRequest对象
var academy = null;
var profession = null;
var unit = null;

	var password;
	var isError = "";

	function ajaxSubmit()
	{
		isError = false;
		
		findUser();
		check_password();
		check_repassword();
		check_email();
		check_name();
		check_mobile();
		
		if(isError == true)
		{
			return false;
		}
		if(unit == null)
		{
			return false;
		}
			
		else//isError == false, 表示交注用户信息没有错误，提交注册表！
		{
			var gender = document.getElementsByName("gender");
			
			var username = $("#username").val();
			var password = $("#password").val();
			var email = ("#email").val();
			var name = $("#name").val();
			var mobilePhone = $("mobilePhone").val();
			if(gender[0].checked)
				gender = gender[0].value;
			else
				gender = gender[1].value;
			
			$.ajax({
				
				type: "POST",
				url: "register.htm",
				dateType: "html",
				data: {'username':username , 'password':password, 'email': email, 'name':name, 'unit':unit, 'mobilePhone': mobilePhone, 'gender': gender},
				success: function(uri){
					
					window.location = uri;
				}
			});
			
			window.location = "/register.htm";	
			return;
		}
		return true;
	}
		function findUser()
		{
			$.getJSON("ajaxFindUser.htm?username=" + $("#usernameID").val(),function(text)		//使用异步获得所有的书本的内容信息, 返回json格式的数据
					{
						var status = text.status; 	//status 作为状态码使用
						var message = text.uri;
						
						if(status == 200)							//验证通过！
						{
							$("#td1").html("");
							
							//显示图片
							$("#successImg").removeClass("hide");
							
							return;
						}
						
						if(status == 400)						//返回的是错误信息
						{
							//隐藏图片
							$("#successImg").addClass("hide");
							
							$("#td1").html(text.error);
							
							isError = true;
							return;
						}
					});
		};
  			
		function check_password()
		{
			//var passSize = $("#password").text();
			 password = document.getElementById("password");
			
			if(password.value.length >= 4 && password.value.length <= 10)							//验证通过！
			{
				$("#td2").text("");
				
				//显示图片
				$("#passwordImg").removeClass("hide");
			}
			else					//返回的是错误信息
			{
				//隐藏图片
				$("#passwordImg").addClass("hide");
				
				$("#td2").html("密码长度需要在4和10之间！");
				isError = true;
			}
		}
  				
		function check_repassword()
		{
			//var passSize = $("#password").text();
			 var repassword = document.getElementById("repassword");
			
			if(repassword.value.length < 4 || repassword.value.length > 10 || repassword.value != password.value)		//返回的是错误信息			
			{
				//隐藏图片
				$("#repasswordImg").addClass("hide");
				
				$("#td3").html("两次输入密码不一致");
				isError = true;
			}
			else						//验证通过！
			{
				$("#td3").text("");
				
				//显示图片
				$("#repasswordImg").removeClass("hide");
			}
		}
		
		function check_email()
		{
			var temp = document.getElementById("email");
			         //对电子邮件的验证
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!myreg.test(temp.value))
			{
			 	//隐藏图片
				$("#emailImg").addClass("hide");
				
				$("#td4").html("提示\n\n请输入有效的E_mail！");
				isError = true;
		    }
			else 
			{
				$("#td4").text("");
				
				//显示图片
				$("#emailImg").removeClass("hide");
			}
			
		}
		
		function check_name()
		{
			var name = $('#name').val();
			
		       if(name.search(/^[\u0391-\uFFE5\w]+$/) != -1)
		       {
		    	   $("#td5").text("");
					
					//显示图片
					$("#nameImg").removeClass("hide");   
		       }
		       else
		       {    
		    	 	//隐藏图片
					$("#nameImg").addClass("hide");
					$("#td5").html("姓名格式错误");
					isError = true;
		       }
		}
		
		function check_mobile()
		{
			var temp = document.getElementById("mobilePhone");
			
			var mobile=/^((13[0-9]{1})|159|153)+\d{8}$/;
			var mobile1=/^(13+\d{9})|(159+\d{8})|(153+\d{8})$/;
			
			if(!mobile.test(temp.value) || !mobile1.test(temp.value))
			{
			 	//隐藏图片
				$("#mobileImg").addClass("hide");
				
				$("#td6").html("请输入有效的手机号码！");
				isError = true;
		    }
			else 
			{
				$("#td6").text("");
				
				//显示图片
				$("#mobileImg").removeClass("hide");
			}
			  
			  
		}
		
	
	function Change_Select(){
	    //当第一个下拉框的选项发生改变时调用该函数
	      academy = document.getElementById('one').value;
	     
	      profession = document.getElementById('two');
	      
	      if(academy == "null")
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
	    function callback()
	    {
		      if(req.readyState ==4)
		      {
		        if(req.status ==200)
		        {
		          parseMessage();
		        }else
		        {
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
		      
		      var option = new Option("--请选择--", "null");
		      try{
		          street.add(option);
		        }
		        catch(e){
		        }
		    
		      for(var i = 0; i < size; i++)
		      {
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
	      
	      if(profession == "null")
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
	      
	      var option = new Option("--请选择--", "null");
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
		
		<form class="form-signin" onsubmit="ajaxSubmit(); return false;" method="post" >
		
  		
  		<table width="95%" border="0" cellpadding="0" cellspacing="1">
  		 <tbody>
  		 		 <tr>
            		<td align="left">用户名 </td>
            		<td align="left"><input class="input" type="text" name="username" id="usernameID" onblur="findUser()" size="20" style="width:200px"></td>
            		<td align="left" id="successImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: #e64141; font-size: 13px;"  id="td1"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">设置密码</td>
            		<td align="left"><input class="input" type="password" name="password" id="password" onblur="check_password()" size="20" style="width:200px"></td>
          			<td align="left" id="passwordImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" class="icon_delS" style="color: #e64141; font-size: 13px;"  id="td2"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">确认密码 </td>
            		<td align="left"><input class="input" type="password" name="repassword" id="repassword" onblur="check_repassword()" size="20" style="width:200px"></td>
          			<td align="left" id="repasswordImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: #e64141; font-size: 13px;"  id="td3"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">邮箱帐号 </td>
            		<td align="left"><input class="input" type="text" name="email" id="email" onblur="check_email()" size="20" style="width:200px"></td>
          			<td align="left" id="emailImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: #e64141; font-size: 13px;"  id="td4"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">姓 名 </td>
            		<td align="left"><input class="input" type="text" name="name" id="name" onblur="check_name()" size="20" style="width:200px"></td>
          			<td align="left" id="nameImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: #e64141; font-size: 13px;"  id="td5"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">性 别</td>
            		<td align="left">男<input type="radio" name="gender" value="男" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		 女<input type="radio" name= "gender" value="女" >
            		</td>
          		</tr>
          		
          		<tr>
            		<td align="left">手机号码 </td>
            		<td align="left"><input class="input" type="text" name="mobilePhone" id="mobilePhone" onblur="check_mobile()" size="20" style="width:200px"></td>
          			<td align="left" id="mobileImg" class="hide"><img src="/img/success.png"></td>
            		<td align="left" style="color: #e64141; font-size: 13px;"  id="td6"></td>
          		</tr>
  		 </tbody>
  		</table>
  		
  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr>
          			<td align="left">单 位 </td>
  					<td align="left"><select name="one" id="one" onChange="Change_Select()" style="width: 130px">
   					<!--第一个下拉菜单-->
   						<option value="null">请选择单位</option>
						<option value="1">计算机学院</option>
						<option value="2">土木工程学院</option>
						<option value="3">外国语学院</option>
  					</select> <select name="two" id="two" onChange="Change_Select2()" class="hide" style="width: 120px">
   					<!--第二个下拉菜单-->
   					<option value="null">--请选择--</option>
  					</select> 
  					<select name="three" id="three" onChange="Change_Select3()" class="hide" style="width: 120px">
   					<!--第三个下拉菜单-->
   					<option value="null">--请选择--</option>
  					</select></td>
 				</tr>
 		</table>		
  	 
		<div id="div1"></div>
  		<input class="btn btn-large btn-primary" type="submit" value="submit">
  		</form>
  		</div>
</body>
</html>
