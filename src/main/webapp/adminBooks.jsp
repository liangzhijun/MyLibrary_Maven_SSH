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
<script type="text/javascript" src="js/jquery-2.0.2.js"></script>

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/library.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
.hide{
	display: none;
}
td.index {width: 3%;}
td.title {text-align:left; width: 25%;}
td.author {width: 15%;}
td.publisher {width: 20%;}
td.callNumber {width: 9%;}
td.amount {width: 5%;}
td.viewCount {width: 8%;}
</style>

<script type="text/javascript">

	var pageIndex = 1;	  //当前页数
	var pageCount = null;		  //总页数
	var books = null;
	var msg;
	//var data = null;	  //data用于接收json格式的书本信息集合
	//
	
	$(document).ready(function( ) 
	{
		$.getJSON("AjaxGetBooks.htm",function(result)		//使用异步获得所有的书本的内容信息, 返回json格式的数据
		{
			pageCount = result.pageCount;
			books = result.books;
			bind();
			//alert(pageCount);
			ChangeState();
					
			for(var i = 1; i <= pageCount; i++)
			{
				var option = "<option>" + i + "</option>";
						
				$("#pageselect").append(option);
			};
		});
		
		//ChangeState(-1, 1);
		
		$("#previous").click(function()		//上一页按钮click事件
		{
		     pageIndex -= 1;
		     ChangeState();  
		            
		     if(pageIndex < 1)
		     {
		        pageIndex = 1;
		        ChangeState();
		     }
		     else bind();   
		});
		        
		$("#next").click(function()			//下一页按钮click事件
		{
		     pageIndex += 1;
		            
		     ChangeState();
		     if(pageIndex > pageCount)
		     {
		         pageIndex = pageCount;
		         ChangeState();
		         alert("亲，这是最后一页了！");
		     }
		     else bind();           
		});
		        
	});
		
		function bind()
		{
			$("[id=ready]").remove();
			$("#template").removeClass("hide");
			
			for(var i = pageIndex*2 - 2; i < pageIndex*2 ; i++)
		    {
				var field = books[i];
				
		    		var row = $("#template").clone();
		        	row.find("#indexId").text(i + 1);
		        	row.find("#titleId").html('<a href="/bookinfo.htm?callNumber=' + field.callNumber + '">' + field.title +' </a>' );
		        	row.find("#authorId").text(field.author);
		        	row.find("#publisherId").text(field.publisher);
		        	row.find("#callNumberId").text(field.callNumber);
		        	row.find("#amountId").text(field.amount);
		        	row.find("#viewCountId").text(1573);
		        			                               
		        	row.attr("id","ready");//改变绑定好数据的行的id
		        	row.appendTo("#datas");//添加到模板的容器中
		    };
		    
		    $("#template").addClass("hide");
		};
		
	function ChangeState()	 //改变翻页按钮状态
    {
       	$("#pageinfo").html('<span id="pageinfo" style="color: red;"> ' + pageIndex + '</span>/'+ pageCount + '页&nbsp; ');
        	
    }
	function showpagefl(index)
	{
		pageIndex = index;
		ChangeState();
		bind();
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
						<c:if test="${sessionScope.status == null}">
							<a href="/signIn.jsp"><strong style="color:#fff;">登录</strong>
							</a>
						</c:if>
						<c:if test="${sessionScope.status == 'logined'}">
							<a href="/exit.htm"><strong style="color:#fff;">退出</strong>
							</a>
						</c:if>
					</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>


	<div id="content" style="width:930px;">
		<div class="wrap" style="width:930px;">
			<div id="underlinemenu" style="width:928px;">
				<ul>
					<li><a href="?cls_no=A">A 马列主义、毛泽东思想、邓小平理论</a>
					</li>
					<li><a href="?cls_no=B">B 哲学、宗教</a>
					</li>
					<li><a href="?cls_no=C">C 社会科学总论</a>
					</li>
					<li><a href="?cls_no=D">D 政治、法律</a>
					</li>
					<li><a href="?cls_no=E">E 军事</a>
					</li>
					<li><a href="?cls_no=F">F 经济</a>
					</li>
					<li><a href="?cls_no=G">G 文化、科学、教育、体育</a>
					</li>
					<li><a href="?cls_no=H">H 语言、文字</a>
					</li>
					<li><a href="?cls_no=I">I 文学</a>
					</li>
					<li><a href="?cls_no=J">J 艺术</a>
					</li>
					<li><a href="?cls_no=K">K 历史、地理</a>
					</li>
					<li><a href="?cls_no=N">N 自然科学总论</a>
					</li>
					<li><a href="?cls_no=O">O 数理科学与化学</a>
					</li>
					<li><a href="?cls_no=P">P 天文学、地球科学</a>
					</li>
					<li><a href="?cls_no=Q">Q 生物科学</a>
					</li>
					<li><a href="?cls_no=R">R 医药、卫生</a>
					</li>
					<li><a href="?cls_no=S">S 农业科学</a>
					</li>
					<li><a href="?cls_no=T">T 工业技术</a>
					</li>
					<li><a href="?cls_no=U">U 交通运输</a>
					</li>
					<li><a href="?cls_no=V">V 航空、航天</a>
					</li>
					<li><a href="?cls_no=X">X 环境科学,安全科学</a>
					</li>
					<li><a href="?cls_no=Z">Z 综合性图书</a>
					</li>
					<li><a href="?cls_no=ALL"><b class="red">总体排行</b>
					</a>
					</li>
				</ul>
			</div>
			<p class="box_bgcolor">
				分类：<font color="red">总体排行</font>
			</p>

			<table style="width: 100%; text-align: center; background: #d8d8d8;" border="0">
				<tr>
					<td width="3%">序号</td>
					<td width="25%">题名</td>
					<td width="15%">责任者</td>
					<td width="20%">出版信息</td>
					<td width="9%">索书号</td>
					<td width="5%">数量</td>
					<td width="8%">浏览次数</td>
				</tr>
				
				
			</table>

			<table id="datas" style="width: 100%;  text-align: center;" border="1" >
				<tr id="template">
					<td class="index" id="indexId"></td>
					<td class="title" id="titleId"></td>
					<td class="author" id="authorId"></td>
					<td class="publisher" id="publisherId"></td>
					<td class="callNumber" id="callNumberId"></td>
					<td class="amount" id="amountId"></td>
					<td class="viewCount" id="viewCountId"></td>
				</tr>
			</table>

		</div>
		
		<div class="pd tbr">
			第<span id="pageinfo" >1页</span> 
			  <a href="javascript:void(0)" id="previous">上一页</a>&nbsp; 
			  <a href="javascript:void(0)" id="next">下一页</a> &nbsp;
		转到第<select id="pageselect" onchange="showpagefl(this[this.selectedIndex].value)">
		</select>
		</div>
</body>
</html>
