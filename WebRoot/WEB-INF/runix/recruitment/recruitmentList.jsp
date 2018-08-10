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
			document.getElementById('recruitmentForm').action=id+".action";
			document.getElementById('recruitmentForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('recruitmentForm').action="updateInit.action?recruitmentId_q="+id;
			document.getElementById('recruitmentForm').submit();
		}
		function doview(id){
			document.getElementById('recruitmentForm').action="view.action?recruitmentId_q="+id;
			document.getElementById('recruitmentForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('recruitmentForm').action="delete.action?recruitmentId_q="+id;
			document.getElementById('recruitmentForm').submit();
			});
		}
	</script>
	</head>
<body >
	<s:form action="recruitmentAction" id="recruitmentForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" value="flag"></s:hidden>
	<div class="tagContent selectTag" id="tagContent0" style="margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
		<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
		  <tr>
			<td height="25" width="15%" align="right" style="border:0px">招聘岗位：</td>
			<td height="25" width="15%" align="left" style="border:0px">
				<select id="position" name="position" style="width:155px">
						<option value="">--请选择--</option>
						<s:iterator value="positionList">
						<option value="<s:property value='postname'/>" <s:if test="postname==position">selected="true"</s:if>><s:property value='postname'/></option>
					</s:iterator>
				</select>
			</td>
			<td height="25" width="15%" align="right" style="border:0px">招聘部门：</td>
			<td height="25" width="15%" align="left" style="border:0px">
				<select id="department" name="department" style="width:155px">
						<option value="">--请选择--</option>
						<s:iterator value="deptList">
						<option value="<s:property value='deptname'/>" <s:if test="deptname==department">selected="true"</s:if>><s:property value='deptname'/></option>
					</s:iterator>
				</select>
			</td>
			<td height="25" width="40%" align="right" style="border:0px"></td>
		  </tr>
		   <tr>
			<td height="25" align="right" style="border:0px">提交状态：</td>
			<td height="25" align="left" style="border:0px">
				<select id="status" name="status" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='status=="1"'>selected="true"</s:if>>已提交</option>
					<option value="0" <s:if test='status=="0"'>selected="true"</s:if>>未提交</option>
				</select>
			</td>
			<td height="25" align="right" style="border:0px">审核状态：</td>
			<td height="25"  align="left" style="border:0px">
				<select id="checkstatus" name="checkstatus" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='checkstatus=="1"'>selected="true"</s:if>>已审核</option>
					<option value="2" <s:if test='checkstatus=="2"'>selected="true"</s:if>>审核中</option>
					<option value="3" <s:if test='checkstatus=="3"'>selected="true"</s:if>>未审核</option>
				</select>
			</td>
			<td height="25"  align="right" style="border:0px"></td>
		  </tr>
		  <tr>
					<td align="right"><div style="padding-top:4px"><a href="#"  onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
					<td align="left" colspan="4"><div style="padding-top:4px"  ><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
			</tr> 
		    
		</table>
			<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				  <thead height="33px">
					<tr>
					  <th width="10%">用人部门</th>
					  <th width="10%">招聘岗位</th>
					  <th width="15%">招聘标题</th>
					  <th width="10%">招聘人数</th>
					  <th width="10%">招聘期限</th>
					  <th width="10%">提交人</th>
					  <th width="10%">提交状态</th>
					  <th width="10%">审核状态</th>
					  <th width="15%">操作</th>													  
					</tr>
				  </thead>
				  <tbody>
				  <s:iterator value="recruitmentList">
					<tr height="32px">										 
					  <td ><s:property value='department'/></td>
					  <td ><s:property value='position'/></td>
					  <td ><s:property value='title'/></td>
					  <td ><s:property value='peoplenum'/></td>
					  <td ><s:property value='limittime'/></td>
					  <td ><s:property value='submitter'/></td>
					  <td ><s:if test='status=="0"'>未提交</s:if><s:else>已提交</s:else></td>
					  <td ><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>
					<td >
					 <s:if test='status=="0"'><a href="#" onclick="doupdate('<s:property value='recruitmentId'/>')" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
					<s:if test='status=="1"'><a  href="#" onclick="doview('<s:property value='recruitmentId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
					<a  href="#" onclick="dodelete('<s:property value='recruitmentId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
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