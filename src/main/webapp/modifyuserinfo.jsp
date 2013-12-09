<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/library.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	font-size: 15px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}

</style>

</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand">书目检索系统</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="/Retrieval.jsp">书目检索</a>
						</li>
						<li><a href="#about">分类浏览</a>
						</li>
						<li><a href="#about">期刊导航</a>
						</li>
						<li><a href="#contact">新书通报</a>
						</li>
						<li><a href="/getBooks.htm">图书目库</a>
						</li>
						<li><a href="#about">读者荐购</a>
						</li>
						<li><a href="/myLibrary.jsp">我的图书馆</a>
						</li>
					</ul>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<hr>
	
	<div class="UserMaster">
	
		<div id="UserMasterLeft">
                <div class="userinfo">欢迎您,${sessionScope.user.name}</div>
    
				<div id="userpagemenu">
         
                <ul>
                
                <li>
                    <a href="/myLibrary.jsp" class="select">个人信息</a>
                </li>
                
                <li>
                    <a href="/change_passwd.jsp">修改密码</a>
                </li>
                
                <li>
                    <a href="gscard.aspx">挂失或自停</a>
                </li>
                
                <li>
                    <a href="clscategory.aspx">我的需求</a>
                </li>
                
                <li class="lihr">
                </li>
                
                <li>
                    <a href="bookborrowed.aspx">当前借阅情况和续借</a>
                </li>
                <li>
                    <a href="chtsmessage.aspx">催还图书信息</a>
                </li>
                <li>
                    <a href="xstbmessage.aspx">新书通报信息</a>
                </li>
                <li>
                    <a href="resvp.aspx">预约图书信息</a>
                </li>
                <li class="lihr">
                </li>
                <li>
                    <a href="bookborrowedhistory.aspx">我的借书历史</a>
                </li>
                <li>
                    <a href="mybookshelf.aspx">我的收藏书架</a>
                </li>
                <li>
                    <a href="mysearchhistory.aspx">我的检索历史</a>
                </li>
                
                <li>
                    <a href="myrecommendlist.aspx">我的荐购表单</a>
                </li>
                <li class="lihr">
                </li>
                <li>
                    <a href="/exit.htm">退出登录</a>
                </li>
                </ul>
              
    </div>

            </div>
		<div id="UserMasterRight">
		<div class="container">
	
		<h3 style="color: rgb(226, 106, 39)">修改个人信息</h3>
		
		<form class="form-signin" action="/modifyUserinfo.htm" method="post">
  	
  	  	手机： <input type="text" name="mobilePhone" value="${
							sessionScope.user.mobilePhone}"> <br>
  	  	电话：<input type="text" name="phone" value="${
							sessionScope.user.phone}"> <br>
  		住址： <input type="text" name="address" value="${
							sessionScope.user.address}"> <br>
  		Email地址：<input type="text" name="email" value="${
							sessionScope.user.email}"> <br>
  		
  		<input class="btn btn-primary" type="submit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;
  		<a class="btn btn-primary" href="/myLibrary.jsp" >取消</a>
  		
  		</form>


		</div>
			<div class="clear">
            </div>
		</div>
		
</body>
</html>
