<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix"></meta>
<meta name="author" content="Runix Inc."></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/dept.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
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
								class="STYLE1">添加部门</span>
							</td>
						</tr>
					</table>
					<form id="form" action="" method="post">
					<table width="100%" border="0" align="center" cellpadding="3"
						cellspacing="1" bgcolor="#CCCCCC">
						<tr>
							<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span
								class="STYLE1">添加部门</span>
							</td>
						</tr>
						<tr>
							<td width="43%" height="20" align="right" bgcolor="#FFFFFF">部门名称：
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<input id="deptname" name="dept.deptname" type="text" value="" size="20" maxlength="20" onblur="checkDeptName();" />
								<span style="margin-left:10px;"></span>
							</td>
						</tr>
						<tr>
							<td width="43%" height="20" align="right" bgcolor="#FFFFFF">联系电话：
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<input id="phone" name="dept.phone" type="text" value="" size="20" maxlength="11" onblur="checkPhone();" />
								<span style="margin-left:10px;"></span>
							</td>
						</tr>
						<tr>
							<td width="43%" height="20" align="right" bgcolor="#FFFFFF">上级机构：
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<input name="dept.parentid" type="text" value="某某信息科技有限公司" size="20" maxlength="20" />
								<span style="margin-left:10px;"></span>
							</td>
						</tr>
						<tr>
							<td width="43%" height="20" align="right" bgcolor="#FFFFFF">部门描述：
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<textarea name="dept.description" rows="5" cols="50"></textarea>
							</td>
						</tr>
						<tr>
							<td height="20" colspan="4" bgcolor="#FFFFFF">
								<div align="center">
									<input type="button" name="save" value="保存" onclick="doAdd();" class="buttons" /> 
									<input type="reset" name="cancle" value="重置" class="buttons" /> 
									<input type="button" name="back" value="返回" onclick="javascript:window.history.back(-1)" class="buttons" />
								</div>
							</td>
						</tr>
					</table></form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>