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
         <c:if test="${apply.applytype eq 1}">
	     <center><h1>请假申请</h1></center>
	     </c:if>
	     <c:if test="${apply.applytype eq 2}">
	     <center><h1>外出申请</h1></center>
	     </c:if>		
		 <center><h2>申请人：${apply.empname}</h2></center>
		 <center><h3>申请请假时间：${apply.starttime}~${apply.endtime}</h3></center>
		 <br/>
		 申请原因：${apply.applyreason} 
		 
		 <!-- <a href="">通过</a>
		 <a href="">拒绝</a> -->
	</body>
</html>