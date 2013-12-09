<%@page import="org.library.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	font-size: 15px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}

.UserMaster {
min-height: 500px;
height: auto;
_height: 450px;
min-width: 780px;
width: 100%;
_width: expression(document.body.clientWidth < 800? "780px": "auto");
float: left;
}
#UserMasterLeft {
min-width: 200px;
width: 200px;
float: left;
border-right-style: solid;
border-right-width: 1px;
border-color: #ddd;
height: 500px;
background-color: #EBF5FB;
}
#UserMasterRight {
width: 76%;
_width: 74%;
float: left;
padding: 10px;
}
#userpagemenu {
width: 190px !important;
width: 160px;
line-height: 30px;
text-align: left;
padding-left: 10px;
padding-right: 10px;
font-size: 13px;
height: 100%;
}
#userpagemenu li {
border-bottom: 1px solid #ddd;
height: 28px;
padding-bottom: 1px;
margin-left: 30px;
}
.clear {
border-top: 1px solid;
clear: both;
font-size: 1px;
line-height: 0;
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
						<li ><a href="/adminRetrieval.jsp">书目检索</a></li>
						<li><a href="#about">分类浏览</a></li>
						<li><a href="/deleteBooks.htm">删除图书or修改图书</a></li>
						<li><a href="/getBooks.htm">图书目库</a></li>
						<li><a href="/booksEntering.jsp">图书录入</a></li>
						<li><a href="/adminLibrary.jsp">个人资料</a></li>
					</ul>
					<div style="color:#FFF; float:right; padding:5px 20px 0 0px; font-size: 14px;">
    					<a href="/exit.htm"><strong style="color:#fff;">退出</strong></a>
    				</div>
				</div>
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
                    <a href="/adminLibrary.jsp" class="select">个人信息</a>
                </li>
                
                <li>
                    <a href="/adminChange_passwd.jsp">修改密码</a>
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
		
			<div class="clear">
            </div>
		</div>
		
</body>
</html>
