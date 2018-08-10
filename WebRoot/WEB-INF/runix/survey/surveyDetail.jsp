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
	<title>查看调查项</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
</head>
<body >
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
            	<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网上调查</span>&gt;&gt; <span class="STYLE1">查看调查项</span></td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看调查项</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">投票主题： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF" ><s:property value="title"/></td>
						<td width="12%" height="20" align="right" bgcolor="#f8f8f8">总投票数： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF" ><s:property value="totalnum"/>票</td>
			          </tr> 		
			        </table>
					<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="margin-top:4px;border-collapse:collapse;">			  
					  <thead>
						  <tr>
							<th align="center" style="width:12%">投票结果</td>
							<td align="left" style="width:88%"></td>
						  </tr> 
					  </thead> 
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						 <s:iterator value="answerList" status="st">
						<tr height="32px">										 
						  <td align="right" style="border:0;width:10%"><s:property value="sortnum"/>、</td>
						  <td  style="width:70%;text-align:left;border:0;"><s:property value="content"/></td>
						  <td align="right" style="width:20%;border:0;"><s:property value="uservotenum"/>票</td>
						</tr>
						</s:iterator>
														
					</table>	
				</td>
  			</tr>
  			<tr>
				<td>
					<div align="center">
						<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
            		</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>