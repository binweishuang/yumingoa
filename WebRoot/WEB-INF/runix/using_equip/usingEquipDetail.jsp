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
   <s:form action="recruitmentAction" id="recruitmentDetailForm" theme="simple"  method="post" >
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看领用</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">领用人： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="applicant"/>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">领用部门： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="department"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">申请概述： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="title"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">使用情况： </td>
            <td height="20" bgcolor="#FFFFFF"><s:if test='usecondition=="1"'>领用</s:if><s:elseif test='usecondition=="2"'>借用</s:elseif></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">领用仓库： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="storage"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">物品类别：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="cateogory"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">物品名称：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="equipmentId"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">库存数量：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="storagenum"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">领用数量：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="applynum"/></td>   
			<td height="20" align="right" bgcolor="#f8f8f8">领用时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:date name="applytime" format="yyyy-MM-dd"/></td>  			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>   			
          </tr>
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">审核意见：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><pre ><s:property value="opinion"/></pre></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">领用备注：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></td>
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