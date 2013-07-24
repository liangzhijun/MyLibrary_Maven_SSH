<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

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
    					<c:if test="${sessionScope.status == null}"><a href="/signIn.jsp"><strong style="color:#fff;">登录</strong></a></c:if>
    					<c:if test="${sessionScope.status == 'logined'}"><a href="/exit.htm"><strong style="color:#fff;">退出</strong></a></c:if>
    				</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<hr>

	<div id="content">
		<div id="searchmain" style="text-align:center;">
			<form action="/searchBooks.htm" method="get" 
				onsubmit="return chkForm()" >
				<div>
					<div id="saerch_center">
						<h3>馆藏书目简单检索</h3>

						<p style="text-align:center; padding:10px;">
							<select class="option" name="strSearchType" size="1"
								style="width:100px; font-size:16px; vertical-align:middle;">
								<option value="title">题&nbsp;&nbsp;名</option>
								<option value="author">责任者</option>
								<option value="publisher">出版社</option>
							</select> 
								
								<input type="text" size="45" maxlength="250"
								name="strText" id="strText" value="" class="paddingleft"
								style="font-size:18px; width:230px; vertical-align:middle;">&nbsp;&nbsp;
                        
                        	<input type="submit" value="检索">
						</p>
						<p style="padding-left:10px;">
							<input type="radio" name="doctype" value="ALL" checked="checked">
							所有书刊 <input type="radio" name="doctype" value="01"> 中文图书
							<input type="radio" name="doctype" value="02"> 西文图书 <input
								type="radio" name="doctype" value="11"> 中文期刊 <input
								type="radio" name="doctype" value="12"> 西文期刊
						</p>
						<p style="padding-left:10px;"></p>
					</div>
					<div id="hot_top_font">
						<span id="top_ten"> 热门检索： <a href="/searchBooks.htm?strText=中国通史&strSearchType=title"
							onclick="input_me('中国通史')">中国通史</a> | <a href="/searchBooks.htm?strText=营销&strSearchType=title"
							onclick="input_me('营销')">营销</a> | <a href="/searchBooks.htm?strText=中国建筑&strSearchType=title"
							onclick="input_me('中国建筑')">中国建筑</a> | <a href="/searchBooks.htm?strText=外贸&strSearchType=title"
							onclick="input_me('外贸')">外贸</a> | <a href="/searchBooks.htm?strText=摄影&strSearchType=title"
							onclick="input_me('摄影')">摄影</a> | <a href="/searchBooks.htm?strText=商务礼仪&strSearchType=title"
							onclick="input_me('商务礼仪')">商务礼仪</a> | <a href="/searchBooks.htm?strText=营销案例&strSearchType=title"
							onclick="input_me('营销案例')">营销案例</a> | 
							<a href="/searchBooks.htm?strText=郁达夫&strSearchType=title"onclick="input_me('郁达夫')">郁<br>达夫</a> | <a href="/searchBooks.htm?strText=读大学&strSearchType=title"
							onclick="input_me('读大学')">读大学</a> | <a href="/searchBooks.htm?strText=沉沦&strSearchType=title"
							onclick="input_me('沉沦')">沉沦</a> | <a href="/searchBooks.htm?strText=国画&strSearchType=title"
							onclick="input_me('国画')">国画</a> | <a href="/searchBooks.htm?strText=信心&strSearchType=title"
							onclick="input_me('信心')">信心</a> | <a href="/searchBooks.htm?strText=小说&strSearchType=title"
							onclick="input_me('小说')">小说</a> | <a href="/searchBooks.htm?strText=庄子&strSearchType=title"
							onclick="input_me('庄子')">庄子</a> | <a href="/searchBooks.htm?strText=战争论&strSearchType=title"
							onclick="input_me('战争论')">战争论</a> | <a href="/searchBooks.htm?strText=青春&strSearchType=title"
							onclick="input_me('青春')">青春</a> | <a href="/searchBooks.htm?strText=赢&strSearchType=title"
							onclick="input_me('赢')">赢</a> | <a href="/searchBooks.htm?strText=货币金融学&strSearchType=title"
							onclick="input_me('货币金融学')">货币金融学</a> | <a href="/searchBooks.htm?strText=艺术&strSearchType=title"
							onclick="input_me('艺术')"><br>艺术</a> | <a href="/searchBooks.htm?strText=电路与模拟电子技术&strSearchType=title"
							onclick="input_me('电路与模拟电子技术')">电路与模拟电子技术</a> | <a
							href="top100.php">more...</a>
						</span>
					</div>

				</div>
			</form>
		</div>
	</div>



</body>
</html>
