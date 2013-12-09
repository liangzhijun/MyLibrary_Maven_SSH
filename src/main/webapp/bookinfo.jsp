<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大学图书馆书目检索系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/main.css">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	font-size: 13px;
	max-width: 800px;
	height: 1500px;
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

#content {
width: 800px;
background-color: #fff;
padding: 5px;
margin: 2px auto 0 auto;
clear: both;
}

</style>
</head>

<body>

<div class="container">

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
						<li><a href="#about">分类浏览</a></li>
						<li><a href="#about">期刊导航</a></li>
						<li><a href="#contact">新书通报</a></li>
						<li><a href="/getBooks.htm"
							>图书目库</a></li>
						<li><a href="#about">读者荐购</a></li>
						<li><a href="/myLibrary.jsp"
							>我的图书馆</a>
						</li>
					</ul>
					<div style="color:#FFF; float:right; padding:5px 20px 0 0px">
    					<c:if test="${sessionScope.status == null}"><a href="/signIn.jsp">登录</a></c:if>
    					<c:if test="${sessionScope.status == 'logined'}"><a href="/exit.htm">退出</a></c:if>
    				</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
		
		
<form class="form-signin">
	<div id="content">
		<div id="s_c_left">
			<div class="itemtag">
				<ul id="search_button">
					<li id="search_buttonblue"><a href="item.php?marc_no=0000194710">&nbsp;&nbsp;书目信息&nbsp;&nbsp;</a>
					</li>
				</ul>
				<div class="itemtag_num"></div>
			</div>

				<div>
				<div style="float:left;">
					<dl class="booklist">
						<dt>题名/责任者:</dt>
						<dd>
							<a href="">${requestScope.book.title}</a>/${requestScope.book.author}&nbsp;著
						</dd>
					</dl>
					
					<dl class="booklist">
						<dt>出版发行项:</dt>
						<dd>${requestScope.book.publisher}</dd>
					</dl>
					<dl class="booklist">
						<dt>ISBN及定价:</dt>
						<dd>${requestScope.book.ISBNandPricing}</dd>
					</dl>
					<dl class="booklist">
						<dt valign="top">内容简介:</dt>
						<dd style="padding-bottom: 8px;">${requestScope.book.content}</dd>
					</dl>
					<dl class="booklist">
						<dt valign="top">目录:</dt>
						<dd style="padding-bottom: 8px;">${requestScope.book.list}<br>&nbsp;(&nbsp;<a target="_blank"href="http://book.bookday.cn/7009841571859#catalogue">更多</a>&nbsp;)
						</dd>
					</dl>
					<dl class="booklist">
						<dt>载体形态项:</dt>
						<dd>${requestScope.book.page}</dd>
					</dl>
					<dl class="booklist">
						<dt>个人责任者:</dt>
						<dd>
							<a href="http://www.baidu.com/s?wd=${requestScope.book.author}">${requestScope.book.author}</a>&nbsp;编著
						</dd>
					</dl>
					<dl class="booklist">
						<dt>学科主题:</dt>
						<dd>
							<a href="openlink.php?keyword=%E5%BF%83%E7%90%86%E5%AD%A6">${requestScope.book.subject}</a>
						</dd>
					</dl>
					<dl class="booklist">
						<dt>责任者附注:</dt>
						<dd>封面题: ${requestScope.book.author}</dd>
					</dl>
					<dl class="booklist">
						<dt>提要文摘附注:</dt>
						<dd></dd>
					</dl>

				</div>
				</div>
				
		<div style="width:670px;">
       	<table width="670" border="1"  cellpadding="2" cellspacing="1" bgcolor="#d2d2d2">
       	 <tbody>
       	 <tr>
            <td align="center" bgcolor="#eeeeee" class="greytext1" width="20%">索书号</td>
            <td align="center" bgcolor="#eeeeee" class="greytext1" width="17%">条码号</td>
            <td align="center" bgcolor="#eeeeee" class="greytext1" width="15%">年卷期</td>
            <td align="center" bgcolor="#eeeeee" class="greytext1" width="23%">馆藏地</td>
            <td align="center" bgcolor="#eeeeee" class="greytext1" width="25%">书刊状态</td>
         </tr>
       	</tbody>
          </table>
    	</div>
		
				
		<div style="width:670px;">
       	<table width="670" border="1"  cellpadding="2" cellspacing="1" bgcolor="#d2d2d2">
       	
       	<c:forEach items="${list}" varStatus="i" var="item">
			<c:if test="${i.index % 2 == 0}">
				<tr align="center">
			</c:if>
			<c:if test="${i.index % 2 == 1}">
				<tr align="center">
			</c:if>
       	 <tbody>
          <tr>
            <td align="center" class="whitetext" width="20%" bgcolor="#FFFFFF">${requestScope.book.callNumber} </td>
            <td align="center" class="whitetext" width="17%" bgcolor="#FFFFFF">${item.barcode}</td>
            <td align="center" class="whitetext" width="15%" bgcolor="#FFFFFF">2008&nbsp;</td>
            <td align="center" class="whitetext" width="23%" bgcolor="#FFFFFF">${item.collection} </td>
            <td align="center" class="whitetext" width="25%" bgcolor="#FFFFFF"><font color="green">${item.status}</font></td>
          </tr>
          
          </tbody>
          </c:forEach>
          </table>
   		 </div>
	
		</div>
		</div>
	
		</form>

	</div>
	

</body>

</html>
