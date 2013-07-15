<%@page import="org.library.dao.BookDao"%>
<%@page import="org.library.model.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>

  
<title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<link type="text/css" rel="stylesheet" href="css/base.css">
<link type="text/css" rel="stylesheet" href="css/main.css">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

<script type="text/javascript">

	function chkForm()
	{
		var strText = document.getElementById("strText");
		
		if(strText.value.length == 0)
		{
			alert("[ 查询内容 ]不能为空");
			return false;
		}
		
		return true;
	}

</script>

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
						<li><a href="/adminRetrieval.jsp">书目检索</a>
						</li>
						<li><a href="#about">分类浏览</a>
						</li>
						<li><a href="/deleteBooks.htm">删除图书or修改图书</a>
						</li>
						<li><a href="/getBooks.htm" >图书目库</a>
						</li>
						<li><a href="/booksEntering.jsp">图书录入</a></li>
						<li><a href="/adminLibrary.jsp">我的资料</a></li>
					</ul>
					<div style="color:#FFF; float:right; padding:5px 20px 0 0px">
    					<a href="/exit.htm"><strong style="color:#fff;">退出</strong></a>
    				</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	
<hr>

	<div id="content">
	
	<div>
			<form action="/searchBooks.htm" onsubmit="return chkForm()" method="get">
				<p style="text-align:center; padding:10px;">检索： <select class="option" name="strSearchType" size="1" style="width:100px; font-size:16px; vertical-align:middle;">
								<option value="title">题&nbsp;&nbsp;名</option>
								<option value="author">责任者</option>
								<option value="publisher">出版社</option>
							</select> 
								
								<input type="text" size="45" maxlength="250" name="strText" id="strText" value="" class="paddingleft" style="font-size:18px; width: 400px; vertical-align:middle;">&nbsp;&nbsp;
                        
                        	<input type="submit" value=" 检索 ">
						</p>
			 </form>
</div>
  <div class="wrap" style="width: 930px;">
		<div id="underlinemenu" style="width: 928px;"><ul><li><a href="?cls_no=A">A 马列主义、毛泽东思想、邓小平理论</a></li><li><a href="?cls_no=B">B 哲学、宗教</a></li><li><a href="?cls_no=C">C 社会科学总论</a></li><li><a href="?cls_no=D">D 政治、法律</a></li><li><a href="?cls_no=E">E 军事</a></li><li><a href="?cls_no=F">F 经济</a></li><li><a href="?cls_no=G">G 文化、科学、教育、体育</a></li><li><a href="?cls_no=H">H 语言、文字</a></li><li><a href="?cls_no=I">I 文学</a></li><li><a href="?cls_no=J">J 艺术</a></li><li><a href="?cls_no=K">K 历史、地理</a></li><li><a href="?cls_no=N">N 自然科学总论</a></li><li><a href="?cls_no=O">O 数理科学与化学</a></li><li><a href="?cls_no=P">P 天文学、地球科学</a></li><li><a href="?cls_no=Q">Q 生物科学</a></li><li><a href="?cls_no=R">R 医药、卫生</a></li><li><a href="?cls_no=S">S 农业科学</a></li><li><a href="?cls_no=T">T 工业技术</a></li><li><a href="?cls_no=U">U 交通运输</a></li><li><a href="?cls_no=V">V 航空、航天</a></li><li><a href="?cls_no=X">X 环境科学,安全科学</a></li><li><a href="?cls_no=Z">Z 综合性图书</a></li><li><a href="?cls_no=ALL"><b class="red">总体排行</b></a></li></ul></div>
			<p class="box_bgcolor">分类：<font color="red">总体排行</font></p>

			<table border="0" width="100%" align="center" cellpadding="5" cellspacing="0" bgcolor="#CCCCCC">
				<tr>
					<td bgcolor="#d8d8d8" class="greytext" width="3%"></td>
					<td bgcolor="#d8d8d8" class="greytext" width="25%">题名</td>
					<td bgcolor="#d8d8d8" class="greytext" width="15%">责任者</td>
					<td bgcolor="#d8d8d8" class="greytext" width="20%">出版信息</td>
					<td bgcolor="#d8d8d8" class="greytext" width="10%">索书号</td>
					<td bgcolor="#d8d8d8" class="greytext" width="6%">馆藏</td>
					<td bgcolor="#d8d8d8" class="greytext" width="6%">可借</td>
				</tr>
			</table>
				
			<table border="1" width="100%" align="center" cellpadding="5"
		cellspacing="0" bgcolor="#CCCCCC">

		<c:forEach items="${bookList}" varStatus="i" var="item">
			<c:if test="${i.index % 2 == 0}">
				<tr align="center">
			</c:if>
			<c:if test="${i.index % 2 == 1}">
				<tr align="center">
			</c:if>

			<tbody>

				<tr>
					<td bgcolor="#FFFFFF" class="whitetext" width="3%">${i.index + 1} </td>
					<td bgcolor="#FFFFFF" class="whitetext" width="25%"><a class="blue"
						href="BookinfoServlet?title=${item.title}" >${item.title}</a>
					</td>
					<td bgcolor="#FFFFFF" class="whitetext" width="15%">${item.author}</td>
					<td bgcolor="#FFFFFF" class="whitetext" width="20%">${item.publisher}</td>
					<td bgcolor="#FFFFFF" class="whitetext" width="10%">${item.callNumber}</td>
					<td bgcolor="#FFFFFF" class="whitetext" width="6%">3</td>
					<td bgcolor="#FFFFFF" class="whitetext" width="6%">3</td>
				</tr>
			<tbody>
		</c:forEach>
	</table>
	
  </div>
 
</div>

</body>
</html>
