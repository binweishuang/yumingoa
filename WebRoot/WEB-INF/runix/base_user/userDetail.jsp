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
</head>
<body>
	<div style="width:99%; margin:0 auto; background-color:#fff;">

		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理
								&gt;&gt; <span class="STYLE1">用户管理</span>&nbsp;&gt;&gt; <span
								class="STYLE1">查看用户</span>
							</td>
						</tr>
					</table>
					<form id="form" method="post">
						<input type="hidden" name="user.userId" value="${user.userId }"/>
							<table width="100%" border="0" align="center" cellpadding="3"
								cellspacing="1" bgcolor="#CCCCCC">
								<tr>
									<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span
										class="STYLE1">用户详细信息</span>
									</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">登陆账号：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${user.username }</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">用户姓名：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${user.name }
									</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">性　　别：</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp; 
										<s:if test="user.sex==1">
											男
										</s:if> 
										<s:else>
											女
										</s:else>
									</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属机构：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${user.org
										}</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属部门：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${dept.deptname}
									</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">职　　位：</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${position.postname}
									</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">任职状态：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;
										<s:if test="user.state==0">
											离职
										</s:if> 
										<s:else>
											在职
										</s:else>
									</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">出生日期：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${date
										}</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">年　　龄：</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${user.age}</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">e-mail：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${user.email}</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">联系电话：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
										&nbsp;${user.phone }</td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">家庭住址：
									</td>
									<td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
										&nbsp;${user.address }
									</td>
								</tr>
								<tr>
									<td height="20" colspan="4" bgcolor="#FFFFFF"><div
											align="center">
											<input type="button" name="back" value="返回" class="buttons"
												onclick="javascript:window.history.back(-1)" />
										</div>
									</td>
								</tr>
							</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>