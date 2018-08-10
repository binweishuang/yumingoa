<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>查看通讯录</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
</head>
<body >
    <div  style="width:99%;margin:0 auto; background-color:#fff;">
    	<s:iterator value="addressbookList">
        <table width="100%" border="0" cellspacing="0" cellpadding="5">
	       <tr>
	        <td>
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">通讯录管理</span>&gt;&gt; <span class="STYLE1">查看通讯录</span></td>
				  </tr>
				</table>		  
		        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
		          <tr>
		            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看通讯录</span></td>
		          </tr>
		          <tr>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">类型： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">${ADDRTYPENAME}</td>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属组别： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">${GROUPTYPENAME}</td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">姓   名： </td>
		            <td height="20" bgcolor="#FFFFFF">${NAME}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">英文名： </td>
		            <td height="20" bgcolor="#FFFFFF">${ENNAME}</td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">出生日期： </td>
		            <td height="20" bgcolor="#FFFFFF"><s:date format="yyyy-MM-dd" name='BIRTHDATE'/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">性    别：</td>
		            <td height="20" bgcolor="#FFFFFF"><s:if test='SEX == "0"'>男</s:if><s:else>女</s:else></td>
		          </tr>         
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单    位：</td>
		            <td height="20" bgcolor="#FFFFFF">${COMPANY}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">部    门：</td>
		            <td height="20" bgcolor="#FFFFFF">${DEPT}</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单位电话：</td>
		            <td height="20" bgcolor="#FFFFFF">${OFFICETEL}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">职    务：</td>
		            <td height="20" bgcolor="#FFFFFF">${POSITION}</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">移动电话：</td>
		            <td height="20" bgcolor="#FFFFFF">${MOBILEPHONE}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">家庭电话：</td>
		            <td height="20" bgcolor="#FFFFFF">${HOMEPHONE}</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">e-mail：</td>
		            <td height="20" bgcolor="#FFFFFF">${EMAIL}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">传    真：</td>
		            <td height="20" bgcolor="#FFFFFF">${FAX}</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">通讯地址：</td>
		            <td height="20" bgcolor="#FFFFFF">${ADDRESS}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">邮    编：</td>
		            <td height="20" bgcolor="#FFFFFF">${POSTCODE}</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">其他联系方式：</td>
		            <td height="20" bgcolor="#FFFFFF">${OTHERCONTACT}</td>
		            <td height="20" align="right" bgcolor="#f8f8f8">是否共享：</td>
		            <td height="20" bgcolor="#FFFFFF"><s:if test='FLAG == "1"'>是</s:if><s:else>否</s:else></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">联系记录：</td>
		            <td height="50" bgcolor="#FFFFFF" colspan="3">${CONTACTRECORD}</td>          
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
		            <td height="160" bgcolor="#FFFFFF" colspan="3">${REMARK}</td>
		          </tr>
				       
		          <tr>
		            <td height="20" colspan="4" bgcolor="#FFFFFF">
		            	<div align="center">
							<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
		            	</div>
		            </td>
		          </tr>
		        </table>
			</td>
		  </tr>
		</table>
		</s:iterator>
	</div>
</body>
</html>
