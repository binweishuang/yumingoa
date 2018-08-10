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
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('draftBoxForm').action=id+".action";
			document.getElementById('draftBoxForm').submit();
		}
		
		function doupdate(id){
				 document.getElementById('draftBoxForm').action="updateInit.action?emailId_q="+id;
				document.getElementById('draftBoxForm').submit();
		}
		

		function dodelete(id){
			parent.conferm(id,"draft");
		}
	</script>
	</head>
	<body >
	<s:form action="emailAction" id="draftBoxForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" ></s:hidden>
		<div class="tagContent" id="tagContent2" style="margin:0 auto; background-color:#fff;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
			<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				  <thead height="33px">
					<tr>
					  <th width="15%" >收件人</th>
					  <th width="25%">邮件主题</th>
					 
					<th width="20%">操作</th>													  
					</tr>
				  </thead>
				  <tbody>
				  <s:iterator value="emailList">
					<tr height="32px">										 
					  <td ><s:property value="RECEIVEPEOPLES"/></td>
					  <td ><s:property value="TITLE"/></td>
					  <td >
						 <a href="#" onclick="doupdate('<s:property value='EMAIL_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
						
						 <a href="#" onclick="dodelete('<s:property value='EMAIL_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a>  
					</td>
					</tr>
					</s:iterator>
					 <tr height="32px">
						<td height="20" colspan="3" align="right">
							<!--分页开始-->
							<s:include value="/framework/include/page.jsp"/>
							<!--分页结束-->
						</td>
					</tr>
				  </tbody>
			</table></td></tr></table>
		</div>
			</s:form>
		</body>
	</html>
