<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="height: auto;">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>CSIè¯­é³ ææ¯å§</title>

		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/metronic/plugins/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/metronic/plugins/ionicons/css/ionicons.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/metronic/plugins/adminlte/css/adminlte.css">

	</head>

	<body style="height: auto;">
		
		
		
		
		
		
		<!--<div id="ORG_ADD_DIV_ID" class="card card-info" style="display:none">  -->
		<div id="POST_ADD_DIV_ID" class="card card-info">
	        <input id="regUUID" type="hidden" value=""/>
	        
	        <div class="form-horizontal">
	        <!-- <form action="ChangePassWordSvl" method="post"> -->
				<div class="card-body">
			             <div class="form-group" >
			                 <label for="JI_JOB_NAME" class="col-sm-2 control-label">新的密码：</label>
			              	  <div class="col-sm-10" >
			                 	<input value="" type="password" name="newPassword" id="newPassword" class="form-control"  placeholder="请输入您的新密码" style="width:600px"  >
			                 </div>
			            </div>
			            <div class="form-group" >
			                 <label for="JI_JOB_NAME" class="col-sm-2 control-label">确认密码：</label>
			              	 <div class="col-sm-10" >
			                 	<input value="" type="password" name="newPasswordCon" id="newPasswordCon" class="form-control" placeholder="请确认您的新密码" style="width:600px"  >
			                 </div>
			            </div>
			           <div class="form-group" >
			                 <label for="JI_JOB_NAME" class="col-sm-2 control-label">当前密码：</label>
			              	 <div class="col-sm-10" >
			                 	<input value="" type="password" name="oldPassword" id="oldPassword" class="form-control"  placeholder="请输入您的当前密码" style="width:600px"  >
			                 </div>
			            </div>
			              <!-- /.card-body -->
	             <div class="card-footer col-md-3 col-md-offset-4" style="width:100%"align="center">
	               <button type="button" class="btn btn-info" onclick="subPassword()">提交</button>
	             </div>
	             <div class="card-footer col-md-3 col-md-offset-4" id="tishi" style="text-align: center;color: red;font-size: 15px">
									     
						</div>
	             <!-- /.card-footer -->
	             </div>
	             
	           <!-- </form> -->
	        </div>
	    </div>
		
		
		
		
		
		<script src="${pageContext.request.contextPath}/js/metronic/plugins/jquery/dist/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/metronic/plugins/jQuery-Storage-API/jquery.storageapi.js"></script>
		<script src="${pageContext.request.contextPath}/js/metronic/plugins/jquery.form/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/metronic/plugins/jquery/plugins/scrollbar/perfect-scrollbar.jquery.min.js"></script>
		

		<script>
		function subPassword(){
			var newPassword=$("#newPassword").val();
			var newPasswordCon=$("#newPasswordCon").val()
			var oldPassword=$("#oldPassword").val()
			
			
			if(typeof (newPassword) == 'undefined' || newPassword.trim()==""  ){
				$("#tishi").html("新密码不能为空");
				return;
			}
			if(newPassword.trim().length<6 || newPassword.trim().length>20){
				$("#tishi").html("新密码必须大于6位小于20位");
				return;
			}
			if(typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim()==""){
				$("#tishi").html("确认密码不能为空");
				return;
			}
			if(newPassword.trim()!=newPasswordCon.trim()){
				$("#tishi").html("新密码与确认密码必须保持一致"); //已经写好的功能
				return;
			}
			
			if(typeof (oldPassword) == 'undefined' || oldPassword.trim()==""  ){
				$("#tishi").html("当前密码不能为空");
				return;
			}
			
			/* window.parent.location.replace("${pageContext.request.contextPath}/loginForm.jsp"); */

			
			$.ajax({
		        type: "POST",
		        url: "../editPasswordSvl",
		        async: true,
		        data:{
		        	oldPassword:oldPassword.trim(),
		        	newPassword:newPassword.trim(),
		       	 },
		        dataType: "json",
		        /* error: function (XMLHttpRequest, textStatus, errorThrown) {
		        	alert("消息","出错了，请于管理员联系");
		        }, */
		        success: function (data) {

		        	if(data==true){
		        		alert("密码修改成功")
		        	}else{
		        		/* window.parent.location.replace("/hrm/");
		        		//alert(JSON.stringify(json)); */
		        		alert("原密码错误，密码修改失败")
		        	}
		        	
		        }
		    });
				
		}
		
	
		</script>

		
		<!--  
		<script src="/stmadc/stma/dc/include/js/jcommon.js"></script>
		
		<script language="JavaScript" src="/stmadc/jquery/jquery-ui-1.8.20.min.js"></script>
		<script src="/stmadc/static/comp/bootstrap/dist/js/bootstrap.js"></script>
		<script src="/stmadc/static/comp/adminlte/js/adminlte.min.js"></script>
		<script language="JavaScript" src="/stmadc/stma/dc/include/js/jcommon.js"></script>
	-->


</body></html>