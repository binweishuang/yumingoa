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

	<meta charset="utf-8"></meta>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
</head>
<body >
   <s:form action="dossierAction" id="dossierDetailForm" theme="simple"  method="post" >
     <div style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看公文档案</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">责任者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="author"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="secret"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">成文日期： </td>
            <td height="20" bgcolor="#FFFFFF"><s:date name="createdate" format="yyyy-MM-dd"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">归档日期： </td>
            <td height="20" bgcolor="#FFFFFF"><s:date name="fillingdate" format="yyyy-MM-dd"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">件    号： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="partnum"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">档案年度：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="annual"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">文件字号：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="filesize"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">页    号：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="pagenum"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">档案室：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="archiveroom"/></td>  
			<td height="20" align="right" bgcolor="#f8f8f8">案    卷：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="dossierId"/></td> 			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">档案文种：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="archivetype"/></td>  
			<td height="20" align="right" bgcolor="#f8f8f8">文件标题：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="title"/></td> 			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备   注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">正   文：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><s:property value='content' escape="false"/></td>
          </tr>
		       
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
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