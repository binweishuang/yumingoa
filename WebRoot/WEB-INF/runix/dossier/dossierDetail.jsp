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
   <s:form action="dossierAction" id="dossierDetailForm" theme="simple"  method="post" >
     <div  style="width:99%">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看案卷</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">案卷题名： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="titlle"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">盒    号： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="boxnum"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">案卷年度： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="annual"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">全宗号： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="recgroupnum"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">保管期限： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="keeplimit"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">档案馆代号：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="archivesnum"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">档案室：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="docroom"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">责任者：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="author"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">类    目：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='category=="1"'>一般档案</s:if><s:elseif test='category=="2"'>重要档案</s:elseif></td>  
			<td height="20" align="right" bgcolor="#f8f8f8">页    数：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="pagenum"/></td> 			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="secret"/></td>  
			<td height="20" align="right" bgcolor="#f8f8f8">卷内文件起始时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:date name="starttime" format="yyyy-MM-dd"/></td> 			
          </tr>
		  <tr>
			<td height="20" align="right" bgcolor="#f8f8f8">卷内文件终止时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:date name="endtime" format="yyyy-MM-dd"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">案卷状态：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='status=="1"'>开卷</s:if><s:elseif test='status=="2"'>封卷</s:elseif></td>  			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备   注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></td>
          </tr>		       
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
				<div align="center">
					<input type="button" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
				</div>
			</td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</div>
</s:form>
</body>
</html>