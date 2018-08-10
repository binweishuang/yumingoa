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
	<title>查看合同</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css" />
</head>
<body >
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
        		<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 人力资源 &gt;&gt; <span class="STYLE1">合同管理</span>&nbsp;&gt;&gt; <span class="STYLE1">查看合同</span></td>
					  </tr>
					</table>	
					<s:iterator value="contractList">	  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看合同</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">签订人： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${SIGNEDPERSONNAME}</td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">相关部门： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${DEPTNAME}</td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同类型： </td>
			            <td height="20" bgcolor="#FFFFFF">${CONTRACTTYPENAME}</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同状态： </td>
			            <td height="20" bgcolor="#FFFFFF"><s:if test='STATUS == "1"'>已执行</s:if><s:elseif test='STATUS == "2"'>执行中</s:elseif><s:else>未执行</s:else></td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">客户单位： </td>
			            <td height="20" bgcolor="#FFFFFF">${CUSTOMECOMPANY}</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">联系人：</td>
			            <td height="20" bgcolor="#FFFFFF">${CONTACT}</td>
			          </tr>         
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">联系电话：</td>
			            <td height="20" bgcolor="#FFFFFF">${CONTACTTEL}</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">签订日期：</td>
			            <td height="20" bgcolor="#FFFFFF"><s:date format="yyyy-MM-dd" name="SIGNEDDATE" /></td>
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">终止日期：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3"><s:date format="yyyy-MM-dd" name="ENDDATE" /></td>          
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同说明：</td>
			            <td height="260" bgcolor="#FFFFFF" colspan="3">${details}</td>
			          </tr>
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF">
				            <div align="center">
								<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
				            </div>
			            </td>
			          </tr>
			        </table>
			        </s:iterator>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
