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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
			<script>
			function doCheck(){
				var opinion = document.getElementById('opinion').value;
				if(opinion==''||opinion==null){
					Dialog.alert('请填写审批意见！');
					return;
				}
				document.getElementById('matterForm').action="doCheck.action";
				document.getElementById('matterForm').submit();
			}
	</script>
</head>
<body >
  <s:form action="matterAction" id="matterForm" theme="simple"  method="post" target="_self">
<s:hidden name="matterId" id="matterId"></s:hidden>
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			  <td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">待办事项</span>&gt;&gt; <span class="STYLE1">查看</span></td>
			  </tr>
			</table> 
			<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			  <tr>
				<td height="35" width="15%" align="right" >审批意见：</td>
				<td height="35" width="60%" align="left" style="border:0px"><textarea  style="width:95%;height:100%" name='opinion' id="opinion"><s:property value="opinion"/></textarea></td>
				<td height="35" width="25%" align="left" style="border:0px"><s:if test='handlestatus=="1"'></s:if><s:else><input name="submit1" type="button" onclick="doCheck()" class="buttons" value="审核" /></s:else>
				</td>
			  </tr> 
			</table> 
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看报销申请</span></td>
          </tr>
         <s:iterator value="reimbursementList">
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
            <td height="50" align="right" bgcolor="#f8f8f8">备    注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></td>
          </tr> 
          </s:iterator>		  
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