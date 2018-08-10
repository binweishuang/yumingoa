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
			document.getElementById('flowNodeForm').action=id+".action";
			document.getElementById('flowNodeForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('flowNodeForm').action="updateInit.action?flownodeId_q="+id;
			document.getElementById('flowNodeForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('flowNodeForm').action="delete.action?flownodeId_q="+id;
			document.getElementById('flowNodeForm').submit();
			});
		}
	</script>
	</head>
<body >
	<s:form action="flowNodeAction" id="flowNodeForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<div class="tagContent selectTag" id="tagContent0" style="margin:0 auto; background-color:#fff;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
		<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
		  <tr>
			<td height="25" width="15%" align="right" style="border:0px">节点名称：</td>
			<td height="25" width="85%" align="left" style="border:0px"><input name="nodename_q" type="nodename_q" value="<s:property value='nodename_q'/>"  /></td>		
		  </tr>
		  	<tr>
				<td align="right"><div style="padding-top:4px"><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
				<td align="left" ><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
			</tr> 
		</table>
		<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			  <thead height="33px">
				<tr>
				  <th width="20%" >节点名称</th>
				  <th width="60%">审批领导</th>	
				  <th width="20%">操作</th>													  
				</tr>
			  </thead>
			  <tbody>
			  <s:iterator  value="flowNodeList" status="s">
				<tr height="32px">										 
				  <td ><s:property value="nodename"/></td>
				  <td ><s:property value="approleaders"/></td>
				  <td > <a href="#" onclick="doupdate('<s:property value='flownodeId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a><a  href="#" onclick="dodelete('<s:property value='flownodeId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a></td>
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