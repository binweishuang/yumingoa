<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
		<meta name="author" content="Runix Inc.">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<script type="text/javascript" src="scripts/jquery.min.js"></script>
				<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css">
				<script language="javascript"
						src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
					<script language="javascript" src="<%=request.getContextPath()%>/framework/js/dept.js"></script>
					<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
					<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
					<script>
						$(document).ready(
								function() {
									$("table tbody tr").mouseover(
											"background-color", "");
								});
					</script>
</head>
<body>
	<s:form action="baseDataAction" id="baseDataForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" value="currentPage"></s:hidden>
	<!--右侧内容-->
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理
								&gt;&gt; <span class="STYLE1">部门管理</span></td>
						</tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4"
						cellspacing="0" bordercolor="#f8de91"
						style="border-collapse:collapse;">
						<tr height="25">
								<td width="15%" align="right">
							<div style="padding-top:4px">
									<a href="addIndex.action"><img src="<%=request.getContextPath()%>/framework/img/add.png"
										border="0">
									</a>
								</div>
						</td>
						<td width="85%" align="left">
							
						</td>
						</tr>
					</table>
					<table id="xinxiu_table" width="100%" border="1" align="center"
						cellpadding="4" cellspacing="0" bordercolor="#f8de91"
						style="border-collapse:collapse;">
						<thead height="33px">
							<tr>
								<th width="20%">部门名称</th>
								<th width="40%">部门描述</th>
								<th width="18%">联系电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:if test="depts!=null && !depts.isEmpty()">
							<s:iterator value="depts">
								<tr>
									<td>${deptname}</td>
									<td>${description }</td>
									<td>${phone}</td>
									<td><a href="editIndex.action?dept.deptId=${deptId }"><img
											src="<%=request.getContextPath()%>/framework/img/modify.png" border="0"
											style="vertical-align:middle;">修改 
									</a> <a href="detail.action?dept.deptId=${deptId }"><img src="<%=request.getContextPath()%>/framework/img/view.png"
											border="0" style="vertical-align:middle;">查看 
									</a> <a href="javascript:;" onclick="doDelete(${deptId });"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0"
											style="vertical-align:middle;">删除 
									</a>
									</td>
								</tr>
							</s:iterator>
							</s:if>

							<tr height="32px">
								<td height="20" align="right" colspan="4">
									<!--分页开始-->
									<s:include value="/framework/include/page.jsp"/>
									<!--分页结束-->
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!--右侧内容结束-->
	</s:form>
</body>
</html>