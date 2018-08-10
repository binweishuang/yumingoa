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
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
			    
	</style>
</head>
<body >
   <s:form action="recruitmentAction" id="recruitmentDetailForm" theme="simple"  method="post" >
         <div  style="width:99%;margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看招聘信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">招聘管理</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">用人部门： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="department"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘岗位： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="position"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">提交人： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="submitter"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘期限： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="limittime"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘标题：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="title"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘人数：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="peoplenum"/></td> 
			<td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>			
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">审核意见：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><pre ><s:property value="opinion"/></pre></td>
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">详细说明：</td>
            <td height="300" bgcolor="#FFFFFF" colspan="3">
            	<s:property value='details' escape="false"/>
            </td>
          </tr>
		  <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
				<div align="center">
					<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
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