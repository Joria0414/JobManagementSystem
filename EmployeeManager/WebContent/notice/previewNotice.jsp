<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人事管理系统 ——后台管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
		
	</head>
	<body>
		 <center><h1>${notice.noticename}</h1></center>
		 <br/>
		 ${notice.noticecontent} 
		 
		  <%-- <center><h1>${noticename}</h1></center>
		 <br/>
		 ${noticecontent --%>
		
	</body>
</html>