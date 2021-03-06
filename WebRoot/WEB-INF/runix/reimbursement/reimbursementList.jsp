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
			document.getElementById('reimbursementListForm').action=id+".action";
			document.getElementById('reimbursementListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('reimbursementListForm').action="updateInit.action?reimId_q="+id;
			document.getElementById('reimbursementListForm').submit();
		}
		
		function doview(id){
			document.getElementById('reimbursementListForm').action="view.action?reimId_q="+id;
			document.getElementById('reimbursementListForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('reimbursementListForm').action="delete.action?reimId_q="+id;
			document.getElementById('reimbursementListForm').submit();
			});
		}
	</script>
	</head>
   <body >
           <s:form action="reimbursementAction" id="reimbursementListForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
				<div class="tagContent selectTag" id="tagContent0" style=" margin:0 auto; background-color:#fff;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
				<table  id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
				  <tr>
				    <td height="25" width="15%" align="right" style="border:0px">报销者：</td>
					<td height="25" width="15%" align="left" style="border:0px"><input name="person" value="<s:property value='person'/>" type="text"   /></td>
					<td height="25" width="15%" align="right" style="border:0px">报销类型：</td>
					<td height="25" width="15%" align="left" style="border:0px">
							<select id="reimtype" name="reimtype" style="width:155px">
							    <option value="">--请选择--</option>
								<s:iterator value="reimtypeList">
									<option value="<s:property value='dataId'/>" <s:if test="reimtype==dataId">selected="true"</s:if>><s:property value='dataname'/></option>
								</s:iterator>
							</select>
					</td>
					<td height="25" width="15%" align="right" style="border:0px">审核状态：</td>
					<td height="25" width="15%" align="left" style="border:0px">
							<select id="checkstatus" name="checkstatus" style="width:155px">
									<option value="" >--请选择--</option>
									<option value="1" <s:if test="checkstatus==1">selected="true"</s:if>>已审核</option>
									<option value="2" <s:if test="checkstatus==2">selected="true"</s:if>>审核中</option>
									<option value="3" <s:if test="checkstatus==3">selected="true"</s:if>>未审核</option>
							</select>
					</td>
					<td height="25" width="10%" align="right" style="border:0px"></td>
				  </tr>
				   <tr>
					<td align="right"><div style="padding-top:4px"><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
					<td align="left" colspan="6"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
				</tr>  
				</table>
					<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						  <thead height="33px">
							<tr>
							  <th width="10%" >报销者</th>
							  <th width="10%">报销类型</th>
							  <th width="12%">内容摘要</th>
							  <th width="10%">单据票数</th>
							  <th width="10%">总金额</th>
							  <th width="10%">申请时间</th>
							  <th width="8%">当前状态</th>
							  <th width="8%">是否批准</th>
							<th width="20%">操作</th>													  
							</tr>
						  </thead>
						  <tbody>
						  <s:iterator value="reimbursementList">
							<tr height="32px">										 
							  <td ><s:property value="PERSON"/></td>
							  <td ><s:property value="REIMTYPE"/></td>				  
							  <td ><s:property value="TITLLE"/></td>
							  <td ><s:property value="TOTALDOC"/></td>
							  <td ><s:property value="TOTALMONEY"/></td>
							  <td ><s:property value="APPLYTIME"/></td>
							  <td ><s:if test='STATUS=="0"'>未提交</s:if><s:else>已提交</s:else></td>
							  <td ><s:if test='CHECKSTATUS=="1"'>已审核</s:if><s:elseif test='CHECKSTATUS=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>
							  <td >
								 <s:if test='STATUS=="0"'><a href="#" onclick="doupdate('<s:property value='REIM_ID'/>')" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
								<s:if test='STATUS=="1"'><a  href="#" onclick="doview('<s:property value='REIM_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
								<a  href="#" onclick="dodelete('<s:property value='REIM_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
								 </td>	
							  
							</tr>
							</s:iterator>
							 <tr height="32px">
								<td height="20" colspan="9" align="right">
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