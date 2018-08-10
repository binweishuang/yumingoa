<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">

</head>
<body >
   <s:form action="resumeAction" id="resumeDetailForm" theme="simple"  method="post" >
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看简历信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">应聘者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value='name'/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">性    别： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:if test='sex=="2"'>女</s:if><s:elseif test='sex=="1"'>男</s:elseif></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">年    龄： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='age'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">联系电话： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='tel'/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">应聘部门： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='department'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">应聘岗位：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='position'/></td>
          </tr>         
          <tr>
          <td height="20" align="right" bgcolor="#f8f8f8">是否面试：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='interview=="1"'>是</s:if><s:elseif test='interview=="0"'>否</s:elseif></td> 
            <td height="20" align="right" bgcolor="#f8f8f8">是否录用：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='hire=="1"'>是</s:if><s:elseif test='hire=="0"'>否</s:elseif></td> 
				
          </tr>
          <tr>
          <td height="20" align="right" bgcolor="#f8f8f8">招聘主题：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3" ><s:property value='recruitmentId'/></td>		
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">简历附件：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3">xxx.doc<input type="button" value="下载"/></td>
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">简历说明：</td>
            <td height="300" bgcolor="#FFFFFF" colspan="3"><s:property value='remark'/></td>
          </tr>
		      
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
			<div align="center">
				<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
        </table>
		</td>
  </tr>
</table>


</div>
</s:form>
</body>
</html>