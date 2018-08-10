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
			document.getElementById('workingConditionForm').action=id+".action";
			document.getElementById('workingConditionForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('workingConditionForm').action="updateInit.action?conditionId_q="+id;
			document.getElementById('workingConditionForm').submit();
		}
		
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('workingConditionForm').action="delete.action?conditionId_q="+id;
			document.getElementById('workingConditionForm').submit();
			});
		}
		
		function doview(id){
				document.getElementById('workingConditionForm').action="view.action?conditionId_q="+id;
				document.getElementById('workingConditionForm').submit();
			}
	</script>
	</head>
	<body >
	<s:form action="workingConditionAction" id="workingConditionForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" ></s:hidden>
		<div class="tagContent" id="tagContent2" style=" margin:0 auto; background-color:#fff;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
			<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
				   <tr>
					<td height="25" width="15%" align="right" style="border:0px">姓名：</td>
					<td height="25" width="15%" align="left" style="border:0px"><input name="name" type="text" value="<s:property value='name'/>"  /></td>
					<td height="25" width="15%" align="right" style="border:0px">所属部门：</td>
					<td height="25" width="15%" align="left" style="border:0px">
						<select id="dept" name="dept" style="width:155px">
							<option value="">--请选择--</option>
							<s:iterator value="depts" >
								<option value="<s:property value='deptId'/>" <s:if test="deptId==dept">selected="true"</s:if>><s:property value='deptname'/></option>
							</s:iterator>
						</select>
					</td>
					<td height="25" width="40%"  align="center" style="border:0px" ></td>
				  </tr>
				   <tr>
					 <td height="25" width="15%" align="right" style="border:0px">提交状态：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="status" name="status" style="width:155px">
									<option value="">--请选择--</option>
									<option value="1" <s:if test='status=="1"'>selected="true"</s:if>>已提交</option>
									<option value="0" <s:if test='status=="0"'>selected="true"</s:if>>未提交</option>
								</select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">审核状态：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="checkstatus" name="checkstatus" style="width:155px">
									<option value="">--请选择--</option>
									<option value="1" <s:if test='checkstatus=="1"'>selected="true"</s:if>>已审核</option>
									<option value="2" <s:if test='checkstatus=="2"'>selected="true"</s:if>>审核中</option>
									<option value="3" <s:if test='checkstatus=="3"'>selected="true"</s:if>>未审核</option>
								</select>
						</td>
						<td height="25" width="40%" align="right" style="border:0px"></td>
						</tr>
					  <tr>
					<td align="right" width="15%"><a href="#"  onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></img></a></td>
					<td align="left" colspan="6"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></img></a></td>
				  </tr>  
				</table>
				<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <thead height="33px">
						<tr>
						  <th width="8%" >姓名</th>
						  <th width="10%">所属部门</th>
						  <th width="10%">外出时间</th>
						  <th width="10%">返回时间</th>
						  <th width="15%">目的地</th>												 										
						  <th width="15%">外出简述</th>
						  <th width="8%">提交状态</th>
						   <th width="8%">审核状态</th>
						   <th width="16%">操作</th>	
						</tr>
					  </thead>
					  <tbody>
					  <s:iterator value="workingConditionList">
						<tr>
						  <td><s:property value="name"/></td>
						  <td><s:property value="dept"/></td>
						  <td><s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/></td>
						  <td><s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						  <td><s:property value="destination"/></td>
						  <td><s:property value="title"/></td>
						  <td>
						  		<s:if test="status==1">已提交</s:if>
						  		<s:if test="status==0">未提交</s:if>
						  </td>
						   <td>
						  		<s:if test="checkstatus==3">未审核</s:if>
						  		<s:if test="checkstatus==2">审核中</s:if>
						  		<s:if test="checkstatus==1">已审核</s:if>
						  </td>
						  <td >
						   <s:if test="status==0">
							<a href="javascript:void(0);" onclick="doupdate('<s:property value='conditionId'/>')" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
							 </s:if>
							 <s:if test="status==1">
							 <a href="javascript:void(0);" onclick="doview('<s:property value='conditionId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0" style="vertical-align:middle;">查看</a>
							 </s:if>
							 <a href="javascript:void(0);" onclick="dodelete('<s:property value='conditionId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
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
