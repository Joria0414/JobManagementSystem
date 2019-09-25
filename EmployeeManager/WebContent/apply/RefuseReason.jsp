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
		<link href="../css/css.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="../js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
		<link href="../js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
	    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
		<script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="../js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
		<script src="../js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
		<script src="../js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
		<link href="../css/pager.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		
	function asubmit(){
	        
	        /** 表单提交的校验 */
	      // $("#noticeForm").submit(function(){
	        	var refusereason = $("#content");
	        	var applyid=$("#flag");
	        	var msg = "";
	        	if($.trim(refusereason.val()) == ""){
	        		msg = "驳回原因不能为空！";
	        		content.focus();
	        	}
	        	if (msg != ""){
					$.ligerDialog.error(msg);
					//return false;
				}else{
					//return true;
				}
	        	//alert("hahaha");
	        	//$("#noticeForm").submit();
	        	$.ajax({
				type : "post",//发送请求的方式，默认请求方式为get
				url : "../DisapproveSvl",//发送到什么位置
				data : {
					"refusereason" : refusereason.val(),
					"applyid":applyid.val()
				},//要传给服务器的参数,多个参数之间用逗号分割
				dataType : "json",//告知此次请求的返回值是json串
				success : function(data) {//请求成功时调用，data是请求成功时返回的数据

					if (data == true) {
						//alert(depname);
						alert("驳回成功");
					} else {
						alert("驳回失败");
					}
				}
			})
	        //});
	    }
			
			
		</script>
	</head>
	<body>
		 <!-- 请求异常错误  -->
		<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
		  	<tr valign="top">
			    <td>
			   
				  <!-- 隐藏表单，flag表示添加标记 -->
    	 			<input type="hidden" id="flag" name="flag" value="${param.applyid}">
				  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
						<tr><td class="main_tdbor"></td></tr>
						
						<tr><td class="font3 fftd">驳回原因：<br/>
							<textarea name="content" cols="88" rows="11" id="content"></textarea>
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
						
						<tr><td class="font3 fftd">
								<input type="button" onclick="asubmit()" value="添加">
								<input type="reset" value="重置">
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
					
				  </table>
				
				</td>
		  	</tr>
		</table>
		<div style="height:10px;"></div>
	</body>
</html>