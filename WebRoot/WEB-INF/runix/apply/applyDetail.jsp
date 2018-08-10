<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>查看请示</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript">
		$(document).ready(function() {
			var attachname = $("#attachname").val();
			var attachpath = $("#attachpath").val();
			var names = attachname.split(";");
	 		var paths = attachpath.split(";");
	 		for(var i = 0;i<names.length-1;i++){
	 			$('<li></li>').appendTo("#files").html("<a href='<%=path%>"+paths[i]+"' class='fil'>"+names[i]+"</a>");
	 		}
		});
	</script>
</head>
<body >
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
        		<td>
	       	 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 行政办公 &gt;&gt; <span class="STYLE1">请示管理</span>&nbsp;&gt;&gt; <span class="STYLE1">查看请示</span></td>
					  </tr>
					</table>		
					<s:iterator value="applyList">
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看请示</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请人： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${APPLICANTNAME}</td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请部门： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${DEPTNAME}</td>
			          </tr>
			          <tr>
					  <td height="20" align="right" bgcolor="#f8f8f8">请示类型： </td>
			            <td height="20" bgcolor="#FFFFFF">${TYPENAME}</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
			            <td height="20" bgcolor="#FFFFFF">${SECRETNAME}</td>        
			          </tr>
			          <tr>
			          	<td height="20" align="right" bgcolor="#f8f8f8">请示标题： </td>
			            <td height="20" bgcolor="#FFFFFF">${TITLE}</td>  
					  	<td height="20" align="right" bgcolor="#f8f8f8">紧急程度： </td>
			            <td height="20" bgcolor="#FFFFFF">${URGENCYNAME}</td>
			          </tr>         
			          <tr>
						<td height="20" align="right" bgcolor="#f8f8f8">请示金额：</td>
			            <td height="20" bgcolor="#FFFFFF">${MONEY}</td>
						<td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
			            <td height="20" bgcolor="#FFFFFF"><s:if test='CHECKSTATUS == "1"'>已审核</s:if><s:elseif test='CHECKSTATUS == "2"'>审核中</s:elseif><s:else>未审核</s:else></td>      
			          </tr>
					  <tr>
						<td height="20" align="right" bgcolor="#f8f8f8">审核意见：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3"><pre >${OPINION}</pre></td>
					  </tr>
					  <tr>
						<td height="20" align="right" bgcolor="#f8f8f8">附    件：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3">
			            	<input type="hidden" value="${ATTACHNAME}" id="attachname" />
			            	<input type="hidden" value="${ATTACHPATH}" id="attachpath" />
			            	<ol id="files"></ol>
			            </td>
					  </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
			            <td height="260" bgcolor="#FFFFFF" colspan="3">${CONTENT}</td>
			          </tr>
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
							<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
			            </div></td>
			          </tr>
			        </table>
			        </s:iterator>  
				</td>
  			</tr>
		</table>
	</div>
</body>
</html>
