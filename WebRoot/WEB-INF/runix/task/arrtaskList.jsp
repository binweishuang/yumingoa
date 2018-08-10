<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<!--右侧内容结束-->

	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		function doviewm(id){
			document.getElementById('taskForm').action="viewM.action?taskId_q="+id;
			document.getElementById('taskForm').submit();
		}
	</script>
	</head>
<body >    
<s:form action="taskAction" id="taskForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" ></s:hidden>
		<div class="tagContent" id="tagContent1" style="margin:0 auto; background-color:#fff;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
			<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				  <thead height="33px">
					<tr>
					  <th width="10%" >汇报人</th>
					  <th width="10%">执行人</th>
					  <th width="15%">任务标题</th>
					  <th width="10%">任务总进度</th>
					  <th width="10%">汇报时限</th>
					  <th width="10%">开始时间</th>
					  <th width="10%">结束时间</th>
					  <th width="10%">完成情况</th>												
					  <th>操作</th>														  
					</tr>
				  </thead>
				  <tbody>
				  	<s:if test="tasks.size()>0">
				  	<s:iterator value="tasks">
					<tr>										 
					  <td height="25">${REPORTTO }</td>
					  <td>${EXECUTER }</td>
					  <td>${TITLE }</td>
					  <td>${TOTALPROCESS } %</td>
					  <td>${TIMELIMIT }</td>
					  <td><s:date format="yyyy-MM-dd" name="STARTTIME"/></td>
					  <td><s:date format="yyyy-MM-dd" name="ENDTIME"/></td>
					  <td><s:if test="COMPLETION==0">未完成</s:if><s:elseif test="COMPLETION==1">已完成</s:elseif></td>												  
					  <td > <s:if test="readonly==true"></s:if><s:else>
					  <a href="updateTask.action?taskId=${TASK_ID}" ><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;"/>修改</a>
					</s:else>
					  <a  href="#" onclick="doviewm('<s:property value='TASK_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
					  </td>
					</tr>
					</s:iterator>
					</s:if>
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