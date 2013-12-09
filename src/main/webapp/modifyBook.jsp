<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <title>Library 'modifyBooks.jsp' starting page</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
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
						<li ><a href="/adminRetrieval.jsp">书目检索</a></li>
						<li><a href="#about">分类浏览</a></li>
						<li><a href="/deleteBooks.htm">删除图书or修改图书</a></li>
						<li><a href="/getBooks.htm">图书目库</a></li>
						<li><a href="/booksEntering.jsp">图书录入</a></li>
						<li><a href="/adminLibrary.jsp">个人资料</a></li>
					</ul>
					<div style="color:#FFF; float:right; padding:5px 20px 0 0px">
    				<a href="/exit.htm"><strong style="color:#fff;">退出</strong></a>
    				</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	
   <div class="container">
	
		<h3 style="color: rgb(226, 106, 39)">修改图书信息</h3>
		
	<form class="form-signin" action="modifyBook.htm" method="post">
	
		<table width="95%" border="0" cellpadding="0" cellspacing="0">
          
          <tbody>
          
          <tr>
            <td align="left">题&nbsp;&nbsp;名： </td>
            <td align="left"><input class="input" type="text" name="title" value="${book.title}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">责任者： </td>
            <td align="left"><input class="input" type="text" name="author" value="${book.author}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">出版信息 ：</td>
            <td align="left"><input class="input" type="text" name="publisher" value="${book.publisher}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">索书号 ：</td>
            <td align="left"><input class="input" type="text" name="callNumber" value="${book.callNumber}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">ISBN及定价 ：</td>
            <td align="left"><input class="input" type="text" name="ISBNandPricing" value="${book.ISBNandPricing}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">学科主题： </td>
            <td align="left"><input class="input" type="text" name="subject" value="${book.subject}" size="20" style="width:300px"></td>
          </tr>
          
           <tr>
            <td align="left">载体形态项： </td>
            <td align="left"><input class="input" type="text" name="page" value="${book.page}" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="left">书目录： </td>
            <td align="left"><textarea name="list" style="width:300px">${book.list}</textarea></td>
          </tr>
           <tr>
            <td align="left">内容简介： </td>
            <td align="left"><textarea name="content" style="width:300px">${book.content}</textarea></td>
          </tr>
          
          <tr>
            <td align="right">&nbsp;</td>
            <td align="left"><input type="submit" class="btn btn-primary" value=" 更  改  ">&nbsp;&nbsp;
            <input type="button"class="btn btn-primary"  value=" 取  消  " onclick="javascript:location.href='/deleteBooks.htm' ">			</td>
		  </tr>
		 
        </tbody></table>

  		</form>
  		
  		<div id="pagesubmenu">
        
                <ul>
            
                </ul>
              
    	</div>

		</div>
  </body>
</html>
