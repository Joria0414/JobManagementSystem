<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——工作日报管理</title>
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
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script><link href="${pageContext.request.contextPath}/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
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
	    	
	    	
	 	    //  删除日报绑定点击事件 
	 	    $("#delete").click(function(){
	 		   /** 获取到日报选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一个需要删除的日报！");
	 		   }else{
	 			   /** 得到日报选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要删除吗?","删除日报",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					  window.location = "DelDReportSvl?ids=" + ids.get();
	 				   }
	 			   });
	 		   }
	 	   }) 
		
		 /** 给预览绑定点击事件 */
		$("a[id^='prev_'").click(function(){
			var reportId = this.id.replace('prev_','');
			//var notice=;
			//alert(notice);
			/* var noticecontent=this.val().noticecontent;
			var noticename=this.val().noticename; */
			$.ligerDialog.open({ 
				title:'预览日报',
				height: 500, 
				url: 'FindExactReportSvl?reportid='+reportId,
				//?noticename=${notice.noticename}&noticecontent=${notice.noticecontent}', 
				//url:'FindExactNoticeSvl?noticeid=${"#prev_19"}.val()',
				width: 750, 
				showMax: true, 
				showToggle: true, 
				showMin: true, 
				isResize: true, 
				slide: false,
				});
		})
		
		
		/* $("#cannotchange").click(function(){
		alert("非公告发布人不能修改公告")
	}) */
		
		
	 }) 
	 
	 
	 
	 
	 //跳转
	  function gotoPage(totalPage){
			var num=$("#pager_jump_page_size").val();
			var title=$("#title").val();
			var content=$("#content").val();
			var empname=$("#empname").val();
			var day=$("#day").val();
			/* var type="1"; */
			//1.num>0 2 num<=totalPage
			if(num>0 && num<=totalPage){
				window.location.href="FindAllDReportSvl?current="+num+"&title="+title+"&content="+content+"&empname="+empname+"&day"+day;
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
		<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：工作日报管理 &gt; 查看日报</td>
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
			  		<form action="FindAllDReportSvl" method="post">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  					<tr>
										<td class="font3">
											日报名称：<input type="text" name="title"
											id="title" value="${param.title}"> 											
											日报内容：<input type="text" name="content" id="content"
											value="${param.content}"> 
											日报汇报人：<input type="text"
											name="empname" id="empname" value="${param.empname}">
											日期：<input class="Wdate"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
											name="day" id="day" size="40"
											value="${param.day}"> 
											<input type="submit"
											value="搜索" /> 
											<!-- <input id="delete" type="button" value="删除" /> -->
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
			  <!-- <td><input type="checkbox" name="checkAll" id="checkAll"></td> -->
			  <td>日报名称</td>
			  <td>日报内容</td>
			  <td>日报时间</td>
			  <td>汇报人</td>
			  <td>预览</td>
			  <!-- <td>修改</td> -->
			   
			  
			</tr>
			    <c:forEach var="report" items="${requestScope.pageinfo.lists}">
				<tr id="data_0" align="center" class="main_trbg" style="background-color: rgb(255, 255, 255);">
					<%-- <td><input type="checkbox" id="box_0" value="${notice.noticeid}"></td> --%>
					 <td>${report.reporttitle}</td>
					  <td >
					  ${report.reportcontent}
					  </td>
					  <td>
					  ${report.reporttime}
					  </td>
					  <td>${report.empname}</td>
					  <td align="center" width="40px;"><a id="prev_${report.reportid}" value="${report.reportid}">
							<img title="预览" src="${pageContext.request.contextPath}/images/prev.gif"></a>
					  </td> 
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
										href="FindAllDReportSvl?current=${pageinfo.currentPage-1}&title=${param.title}&content=${param.content}&empname=${param.empname}&day=${param.day}">上一页</a>
									</span>
								</c:if> <!-- <span class="current">1</span> --> 
								<c:if test="${pageinfo.currentPage!=pageinfo.totalPage}">
									<span class="disabled"> <a
										href="FindAllDReportSvl?current=${pageinfo.currentPage+1}&title=${param.title}&content=${param.content}&empname=${param.empname}&day=${param.day}">下一页</a>
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