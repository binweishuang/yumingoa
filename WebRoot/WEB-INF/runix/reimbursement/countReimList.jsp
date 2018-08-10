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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"> 
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('countReimForm').action=id+".action";
			document.getElementById('countReimForm').submit();
		}
		

	</script>
	</head>
   <body >
           <s:form action="countReimbursementAction" id="countReimForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
				<div class="tagContent" id="tagContent1" style=" margin:0 auto; background-color:#fff;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
									<table  id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
									  <tr>
										<td height="25" width="10%" align="right" style="border:0px">报销部门：</td>
										<td height="25" width="10%" align="left" style="border:0px">
												<select id="dept" name="dept" style="width:155px">
														<option value="">--请选择--</option>
														<s:iterator value="deptList">
														<option value="<s:property value='deptId'/>" <s:if test="deptId==dept">selected="true"</s:if>><s:property value='deptname'/></option>
													</s:iterator>
												</select>
										</td>
										<td height="25" width="10%" align="right" style="border:0px">报销类型：</td>
										<td height="25" width="10%" align="left" style="border:0px">
												<select id="reimtype" name="reimtype" style="width:155px">
												    <option value="">--请选择--</option>
													<s:iterator value="reimtypeList">
														<option value="<s:property value='dataId'/>" <s:if test="reimtype==dataId">selected="true"</s:if>><s:property value='dataname'/></option>
													</s:iterator>
												</select>
										</td>
										<td height="25" width="10%" align="right" style="border:0px">报销者：</td>
										<td height="25" width="10%" align="left" style="border:0px"><input name="person" value="<s:property value='person'/>" type="text"   /></td>
										<td height="25" width="20%" align="right" style="border:0px"></td>
									  </tr>
										<tr>
											<td align="right"></td>
											<td align="left" colspan="6"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
										</tr>   									  
 
									</table>
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="15%" >报销者</th>
												  <th width="15%">所属部门</th>
													<th width="20%">报销类型</th>	
													<th width="13%">报销金额</th>	
													<th width="12%">单据票数</th>													 												  
												</tr>
											  </thead>
											  <tbody>
											   <s:iterator value="reimbursementList">
												<tr height="32px">										 
												  <td ><s:property value='PERSON'/></td>
												  <td ><s:property value='DEPT'/></td>
												  <td ><s:property value='REIMTYPE'/></td>
												  <td ><s:property value='TOTALMONEY'/></td>
												  <td ><s:property value='TOTALDOC'/></td>
												</tr>
												</s:iterator>
												 <tr height="32px">
													<td height="20" colspan="5" align="right">
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