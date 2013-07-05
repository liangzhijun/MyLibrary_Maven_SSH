<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>大学图书馆书目录入系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 500px;
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
</style>

<script type="text/javascript">
  		
	function validate()
	{		
		var title = document.getElementsByName("title")[0];
		var author = document.getElementsByName("author")[0];
		var publisher = document.getElementsByName("publisher");
		var callNumber = document.getElementsByName("callNumber");
		var ISBNandPricing = document.getElementsByName("ISBNandPricing");
		var subject = document.getElementsByName("subject");
		var list = document.getElementsByName("list");
		var content = document.getElementsByName("content");
		
		var result = "";
		
		if(title.value.length == 0)
		{
			result += "题名必须要填写！";
		}
		if(author.value == null)
		{
			result += "责任者必须要填写！";
		}
		
		if(publisher.value == "")
		{
			result += "出版信息必须要填写! ";
		}
		if(callNumber.value == "")
		{
			result += "索引号必须要填写! ";
		}
		if(ISBNandPricing.value == "")
		{
			result += "ISBN及定价必须要填写! ";
		}
		if(subject.value == "")
		{
			result += "学科主题必须要填写! ";
		}
		if(ISBNandPricing.value == "")
		{
			result += "书目录必须要填写! ";
		}
		if(subject.value == "")
		{
			result += "内容简介必须要填写! ";
		}
		
		if(result != "")
		{
			alert(result);
			return false;
		}
			
		return true;
	}
  		
  		
  		</script>
</head>

<body>

	<div class="container">
		<h2 style="color: rgb(226, 106, 39)">大学图书馆书目录入系统</h2>
		
		<form class="form-signin" action="booksEntering.htm" method="post">
  	
  	<table width="95%" border="0" cellpadding="3" cellspacing="1">
          <caption>录入我的图书馆 书目信息  </caption>
          
          <tbody>
          
          <tr>
            <td align="right">题名 </td>
            <td align="left"><input class="input" type="text" name="title" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="right">责任者 </td>
            <td align="left"><input class="input" type="text" name="author" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="right">出版信息 </td>
            <td align="left"><input class="input" type="text" name="publisher" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="right">索书号 </td>
            <td align="left"><input class="input" type="text" name="callNumber" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="right">ISBN及定价 </td>
            <td align="left"><input class="input" type="text" name="ISBNandPricing" size="20" style="width:300px"></td>
          </tr>
          <tr>
            <td align="right">学科主题 </td>
            <td align="left"><input class="input" type="text" name="subject" size="20" style="width:300px"></td>
          </tr>
          
           <tr>
            <td align="right">载体形态项: </td>
            <td align="left"><input class="input" type="text" name="pages" size="20" style="width:300px"></td>
          </tr>
          
          <tr>
            <td align="right">条码号</td>
            <td align="left"><input class="input" type="text" name="barcode" size="20" style="width:300px"></td>
          </tr>
          
          <tr>
            <td align="right">书刊状态 </td>
            <td align="left"><input class="input" type="text" name="condition" size="20" style="width:300px"></td>
          </tr>
          
          <tr>
            <td align="right">馆藏地 </td>
            <td align="left"><input class="input" type="text" name="lib" size="20" style="width:300px"></td>
          </tr>
          
          <tr>
            <td align="right">书目录: </td>
            <td align="left"><textarea name="list" style="width:300px"></textarea></td>
          </tr>
           <tr>
            <td align="right">内容简介: </td>
            <td align="left"><textarea name="content" style="width:300px"></textarea></td>
          </tr>
          
          <tr>
            <td align="right">&nbsp;</td>
            <td align="left"><input type="submit" value=" 提  交 ">&nbsp;&nbsp;
            <input type="button" value=" 退  出 " onclick="javascript:location.href='/Signin.jsp' ">			</td>
		  </tr>
		 
        </tbody></table>

  		</form>

		</div>



</body>
</html>