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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/framework/css/system.css">
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/dept.js"></script>
</head>
<body>
	<div style="width:99%; margin:0 auto; background-color:#fff;">

		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理
								&gt;&gt; <span class="STYLE1">部门管理</span>&nbsp;&gt;&gt; <span
								class="STYLE1">查看部门</span></td>
						</tr>
					</table>
					<form id="form" action="" method="post">
						<input type="hidden" name="dept.deptId" value="${dept.deptId }">
						<table width="100%" border="0" align="center" cellpadding="3"
							cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span
									class="STYLE1">部门详细信息</span></td>
							</tr>
							<tr>
								<td width="43%" height="20" align="right" bgcolor="#FFFFFF">部门名称：
								</td>
								<td height="20" bgcolor="#FFFFFF">&nbsp;${dept.deptname }</td>
							</tr>
							<tr>
								<td width="43%" height="20" align="right" bgcolor="#FFFFFF">联系电话：
								</td>
								<td height="20" bgcolor="#FFFFFF">&nbsp;${dept.phone}</td>
							</tr>
							<tr>
								<td width="43%" height="20" align="right" bgcolor="#FFFFFF">上级机构：
								</td>
								<td height="20" bgcolor="#FFFFFF">&nbsp;${dept.parentid}</td>
							</tr>
							<tr>
								<td width="43%" height="20" align="right" bgcolor="#FFFFFF">部门描述：
								</td>
								<td height="20" bgcolor="#FFFFFF">&nbsp;${dept.description}
								</td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF"><div
										align="center">
										<input type="button" name="back" value="返回"
											onclick="javascript:window.history.back(-1)" class="buttons" />
									</div></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>
</body>
</html>