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
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
</head>
<body >
   <s:form action="reimbursementAction" id="reimbursementDetailForm" theme="simple"  method="post" >
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看报销申请</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">报销申请</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">报销者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="person"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">报销类型： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="reimtype"/></td>
			<td height="20" align="right" bgcolor="#f8f8f8">报销概述：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="titlle"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">报销总金额： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="totalmoney"/>元</td>
            <td height="20" align="right" bgcolor="#f8f8f8">报销总单据：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="totaldoc"/></td>
          </tr>         
          <tr>
			<td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>
          </tr> 
		  <tr>
            <td height="50" align="right" bgcolor="#f8f8f8">审核意见：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><pre ><s:property value="opinion"/></pre></td>
          </tr> 
		<tr>
            <td height="50" align="right" bgcolor="#f8f8f8">备    注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></td>
          </tr> 		  
        </table>
		<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <thead height="33px">
						<tr>
						  <th width="15%">报销项目</th>
						  <th width="10%">何时</th>
						  <th width="10%">何因</th>
						  <th width="9%">报销日期</th>
						  <th width="8%">单据票数</th>
						  <th width="10%">报销金额(元)</th>
						  <th width="15%">其他项目</th>
						  <th width="8%">其他票数</th>
						<th width="10%">其他金额</th>													  
						</tr>
					  </thead>
					  <tbody>
					   <s:iterator value="reimbursementItemList" status="st">
						<tr height="32px">										 
						  <td ><s:property value='itemname'/></td>				  
						  <td ><s:date format="yyyy-MM-dd" name="time"/></td>
						  <td ><s:property value='reason'/></td>
						  <td ><s:date format="yyyy-MM-dd" name="reimdate"/></td>
						  <td ><s:property value='docpoll'/></td>
						  <td ><s:property value='reimmoney'/></td>
						  <td ><s:property value='otheritem'/></td>
						  <td ><s:property value='otherpoll'/></td>
						  <td ><s:property value='othermoney'/></td>
						</tr>	
						</s:iterator>				
					  </tbody>
				</table>
		</td>
  </tr>
  <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
			<div align="center">
				<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
</table>


</div>
</s:form>
</body>
</html>