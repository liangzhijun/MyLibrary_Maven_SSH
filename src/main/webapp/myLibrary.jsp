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

<script type="text/javascript">
	
	function validate()
	{
		var file = document.getElementById("fileID").files[0];
		
		if(file == null)
		{
			alert("未选择上传的文件，请选择后再进行上传！");
				
				return false;
		}
		
		return true;
	}
	
</script>

</head>

<body>

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
		<form action="/user/upload.htm" method="post" enctype="multipart/form-data" onsubmit="return validate()">
		
			<table>
				<tr>
					<td align="left" id="successImg" class="whitetext" width="25%"><img src="http://localhost:8080/outputImage.htm"  style="max-width: 80%;"></td>
					<td align="left" > 
					<h3 style="color: rgb(226, 106, 39)">${sessionScope.user.name}：您好</h3> 
					</td>
				</tr>			
			</table>
			<table width="880" border="0" cellpadding="5" cellspacing="1"
				style="padding:5px">
				<tbody>
					
				
					<tr>
						<td bgcolor="#FFFFFF"  width="33%"><span class="bluetext">读者证件号：</span> ${
							sessionScope.user.username}</td>
						<td align="left" width="33%"><span class="bluetext">姓名：</span> ${
							sessionScope.user.name}</td>
						<td  width="34%"><span class="bluetext">性别：</span> ${
							sessionScope.user.gender}</td>
					</tr>
					<tr>
						<td><span class="bluetext">读者类型：</span>${
							sessionScope.user.type}</td>
						<td><span class="bluetext">出生日期：</span>
						</td>
						<td><span class="bluetext">身份证号： </span>${
							sessionScope.user.idCare}</td>
					</tr>
					<tr>
						<td><span class="bluetext">单位：</span>${
							sessionScope.user.unit}</td>
						<td><span class="bluetext">住址：</span>${
							sessionScope.user.address}</td>
						<td><span class="bluetext">Email：</span> ${
							sessionScope.user.email} <font color="green"></font></td>
					</tr>
					<tr>
						<td><span class="bluetext">电话：</span>${
							sessionScope.user.phone}</td>
						<td><span class="bluetext">手机号码：</span>${
							sessionScope.user.mobilePhone}</td>
						<td><span class="bluetext">累计借书：</span>48册次</td>
					</tr>
					<tr>
						<td><span class="bluetext">办证日期：</span>${
							sessionScope.user.time}</td>
						<td><span class="bluetext">生效日期：</span>${
							sessionScope.user.time}</td>
					</tr>

					<tr>
						<td align="left">Photo: <input type="file" name="photo" id="fileID" value="选择照片"> </td>
						<td> <input type="submit" value="上传"> </td>
						<td colspan="3" align="right">
						
						<a href = "/change_passwd.jsp" >
						<input type="button"
							class="select" value="修改个人密码"> 
						</a>
	
						<a href = "/modifyuserinfo.jsp" >
						<input type="button"
							class="select" value="修改联系信息"> 
						</a>
						
					</tr>
				</tbody>
			</table>

		</form>
			</div>
			<div class="clear">
            </div>
		</div>
		
</body>
</html>
