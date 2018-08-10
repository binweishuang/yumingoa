<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<meta charset="utf-8">
	<title>查看便签</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
var editer;
//配置编辑器
  KindEditor.ready(function(K){
  	editer=K.create("#content");
  });
</script>
</head>
<body >
	<div  style="width:99%;margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
            	<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">我的便签</span>&gt;&gt; 
						<span class="STYLE1">查看便签</span></td>
					  </tr>
					</table>
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看便签</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">便签标题： </td>
			            <td height="20" bgcolor="#FFFFFF">${title}</td>
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">上报人： </td>
			            <td height="20" bgcolor="#FFFFFF">${reportperson}</td>
			          </tr> 
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">有效时间： </td>
			            <td height="20" bgcolor="#FFFFFF">
							<s:date format="yyyy-MM-dd" name="starttime"/> 至  <s:date format="yyyy-MM-dd" name="endtime"/>
						</td>
			          </tr>        
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">便签内容：</td>
			            <td height="20" bgcolor="#FFFFFF"><s:property value="content" escape="false"/></td>
			          </tr>     
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF">
			            	<div align="center">
								<input type="button" class="buttons" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
			            	</div>
			            </td>
			          </tr>
			        </table>
				</td>
  			</tr>
		</table>
	</div>
</body>
</html>
