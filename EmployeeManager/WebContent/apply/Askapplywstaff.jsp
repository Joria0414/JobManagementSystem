<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——公告管理</title>
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
	$(function(){
		
			/** 获取上一次选中的部门数据 */
	 	   var boxs  = $("input[type='checkbox'][id^='box_']");
			
	 	  /** 给全选按钮绑定点击事件  */
	    	$("#checkAll").click(function(){
	    		// this是checkAll  this.checked是true
	    		// 所有数据行的选中状态与全选的状态一致
	    		boxs.attr("checked",this.checked);
	    	})
	    	
	 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	})
	    	
	    	
	 	   /** 批准公告绑定点击事件 */
	 	   $("#approve").click(function(){
	 		   /** 获取到公告选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一个批准通过的申请！");
	 		   }else{
	 			   /** 得到公告选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要批准通过吗?","批准通过",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					  window.location = "ApproveSvl?ids=" + ids.get()+"&applytype=2";
	 				   }
	 			   });
	 		   }
	 	   })
		
		 /** 给拒绝申请绑定点击事件 */
		$("a[id^='prev_'").click(function(){
			var applyid = this.id.replace('prev_','');
			$.ligerDialog.open({ 
				title:'拒绝申请',
				height: 500, 
				url: 'apply/RefuseReason.jsp?applyid='+applyid,
				width: 750, 
				showMax: true, 
				showToggle: true, 
				showMin: true, 
				isResize: true, 
				slide: false,
				});
		})
		 /** 给查看申请原因绑定点击事件 */
		$("a[id^='reason_'").click(function(){
			var applyid = this.id.replace('reason_','');
			$.ligerDialog.open({ 
				title:'申请原因',
				height: 500, 
				url: 'FindExactApplySvl?applyid='+applyid,
				width: 750, 
				showMax: true, 
				showToggle: true, 
				showMin: true, 
				isResize: true, 
				slide: false,
				});
		})
		
	 }) 
	 
	 
	 
	 
	 //跳转
	  function gotoPage(totalPage){
			var num=$("#pager_jump_page_size").val();
			var applystatus=$("#applystatus").val();
			var name=$("#name").val();
			//1.num>0 2 num<=totalPage
			if(num>0 && num<=totalPage){
				window.location.href="FindAllLeavemeSvl?current="+num+"&applystatus="+applystatus+"&name="+name+"&type=2";
			}
			else{
				alert("数字只能是1-"+totalPage+"之间！");
			}
		}
	 
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：外出申请管理 &gt; 查看我的外出情况</td>
		<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  		<form action="FindAllLeavemeSvl?type=2" method="post">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	<%-- 请假人：<input type="text" name="name"  id="name" value="${param.name}"> --%>
					    	<select name="applystatus" id="applystatus" value="${param.applystatus}" style="width:143px;">
					    			
					    			<c:if test="${param.applystatus ne 1&&param.applystatus ne 2&&param.applystatus ne 3}">
					    			<option value="0" >--请选择审核状态--</option>
					    			<option value="1" >已批准</option>
					    			<option value="2">未批准</option>
					    			<option value="3">待审核</option>
					    			</c:if>
					    			
					    			<c:if test="${param.applystatus eq 1}">
					    			<option value="0" >全部</option>
					    			<option value="1" selected="selected">已批准</option>
					    			<option value="2">未批准</option>
					    			<option value="3">待审核</option>
					    			</c:if>
					    			<c:if test="${param.applystatus eq 2}">
					    			<option value="0" >全部</option>
					    			<option value="1">已批准</option>
					    			<option value="2" selected="selected">未批准</option>
					    			<option value="3">待审核</option>
					    			</c:if>
					    			<c:if test="${param.applystatus eq 3}">
					    			<option value="0" >全部</option>
					    			<option value="1">已批准</option>
					    			<option value="2">未批准</option>
					    			<option value="3" selected="selected">待审核</option>
					    			</c:if>
					    			
					    		</select>
					    	 <input type="submit" value="搜索"/>
					    	<!-- <input id="approve" type="button" value="通过"/> -->
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tbody><tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>请假人</td>
			  <td>开始时间</td>
			  <td>结束时间</td>
			  <td>申请原因</td>		  
			  <td>审批状态</td>		   
			  <td>审批人</td>
			  <td>被拒原因</td>
			  <!-- <td>审批通过</td>
			  <td>拒绝申请</td> -->
			</tr>
			    <c:forEach var="apply" items="${requestScope.pageinfo.lists}">
				<tr id="data_0" align="center" class="main_trbg" style="background-color: rgb(255, 255, 255);">
					<td><input type="checkbox" id="box_0" value="${apply.applyid}"></td>
					 <td>${apply.empname}</td>
					  <td >
					  ${apply.starttime}
					  </td>
					  <td>
					  ${apply.endtime}
					  </td>
					  
					   <td align="center" width="40px;"><a id="reason_${apply.applyid}" value="${apply.applyid}">
							<img title="申请原因" src="${pageContext.request.contextPath}/images/prev.gif"></a>
					  </td> 
					   <td>
					   <c:if test="${apply.applystatus eq 2}">不通过</c:if>
					   <c:if test="${apply.applystatus eq 1}">通过</c:if>
					   <c:if test="${apply.applystatus eq 3}">待审核</c:if>
					   </td>
					  <td>
					  <c:if test="${apply.applystatus eq 2}"> ${apply.approvername}</c:if>
					  <c:if test="${apply.applystatus eq 1}">通过</c:if>
					   <c:if test="${apply.applystatus eq 3}">待审核</c:if>
					 </td>
					  
					  
					  <td>
					  <c:if test="${apply.applystatus eq 2}">${apply.refusereason}</c:if>
					  <c:if test="${apply.applystatus eq 1}">通过</c:if>
					   <c:if test="${apply.applystatus eq 3}">待审核</c:if>
					  </td>
					  
					  
					  <%-- <td align="center" width="40px;"><a href="${pageContext.request.contextPath}/ApproveSvl?ids=${apply.applyid}&applytype=2">
							<img title="审批通过" src="${pageContext.request.contextPath}/images/update.gif"></a> <!-- 有小弹窗 -->
					  </td>
					  
					   <td align="center" width="40px;"><a id="prev_${apply.applyid}" value="${apply.applyid}">
							<img title="拒绝申请" src="${pageContext.request.contextPath}/images/prev.gif"></a>
					  </td> 
 --%>					  
				</tr>
			    </c:forEach>
		  </tbody></table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
		<tr valign="top">
			<td align="center" class="font3">
				<table width="100%" align="center" style="font-size: 13px;"
					class="digg">
					<tbody>
						<tr>
							<td
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">

								<c:if test="${pageinfo.currentPage ne 1}">
									<span class="disabled"> <a
										href="FindAllLeavemeSvl?current=${pageinfo.currentPage-1}&applystatus=${param.applystatus}&name=${param.name}&type=2">上一页</a>
									</span>
								</c:if> <!-- <span class="current">1</span> --> 
								<c:if test="${pageinfo.currentPage!=pageinfo.totalPage}">
									<span class="disabled"> <a
										href="FindAllLeavemeSvl?current=${pageinfo.currentPage+1}&applystatus=${param.applystatus}&name=${param.name}&type=2">下一页</a>
									</span>
								</c:if> 
								&nbsp;跳转到&nbsp;&nbsp;<input
								style="text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none"
								type="text" size="2" id="pager_jump_page_size">&nbsp;<input
								type="button"
								style="text-align: center; BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none"
								value="确定" id="pager_jump_btn"
								onclick="gotoPage(${pageinfo.totalPage})">
							</td>
						</tr>
						<tr align="center">
							<td style="font-size: 13px;"></td>
						</tr>
						<tr>
							<td
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">总共<font
								color="red">${pageinfo.totalCount}</font>条记录,当前显示第${pageinfo.currentPage}页数据。
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>