<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加部门</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	//添加部门信息
	
		function submit() {
			var name = $("#name");
			var remark = $("#remark");
			var msg = "";
			if ($.trim(name.val()) == "") {
				msg = "部门名称不能为空！";
				name.focus();
			} else if ($.trim(remark.val()) == "") {
				msg = "详细描述不能为空！";
				remark.focus();
			}
			if (msg != "") {
				$.ligerDialog.error(msg);
				//return false;
			} else {
				//return true;
			}
			$.ajax({
				type : "post",//发送请求的方式，默认请求方式为get
				url : "../AddDepSvl",//发送到什么位置
				data : {
					"depname" : name.val(),
					"depdetail" : remark.val()
				},//要传给服务器的参数,多个参数之间用逗号分割
				dataType : "json",//告知此次请求的返回值是json串
				success : function(data) {//请求成功时调用，data是请求成功时返回的数据

					if (data == true) {
						//alert(depname);
						alert("部门添加成功");
					} else {
						alert("部门添加失败");
					}
				}
			})
		}
		//检查部门名称是否重复
		function checkDepName() {
			$.ajax({
				type : "post",//发送请求的方式，默认请求方式为get
				url : "../CheckDepNameSvl",//发送到什么位置
				data : {
					"depname" : $("#name").val()
				},//要传给服务器的参数
				dataType : "json",//告知此次请求的返回值是json串
				success : function(data) {//请求成功时调用，data是请求成功时返回的数据
					//方法体：对返回值的操作
					//alert(data);
					if (data == true) {
						alert("没有注册过此部门");
					} else {
						alert("此部门已经添加过，无法再添加");
					}
				}
			})

		}
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：部门管理  &gt; 添加部门</td>
	<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">部门名称：<input type="text" name="name" id="name" size="20" onblur="checkDepName()"/></td>
		    			<td class="font3 fftd">详细描述：<input type="text" name="remark" id="remark" size="20"/></td>
		    		</tr>
		    			
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" onclick="submit()" value="添加 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>