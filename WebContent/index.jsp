<%@page import="com.dw.servlet.UploadServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<body>
    <h2>使用JSP+Servlet实现文件的上传下载</h2>
	<form action="uploadServlet" method="post" enctype="multipart/form-data" >
  		请选择文件：<input id="file" name="file" type="file" />
  		<input type="submit" value="上传"  />${result}
  	</form>
  	下载：<a href="downloadServlet?filename=FileTest.txt">fileTest.txt</a> ${errorResult} 
</body>
</html>