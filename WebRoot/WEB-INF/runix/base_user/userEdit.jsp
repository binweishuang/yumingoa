<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8"></meta>
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix"></meta>
<meta name="author" content="Runix Inc."></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<script type="text/javascript">
	/**
	 * 获取原账号和姓名
	 */
	var oldUserName = "";
	var oldName = "";
	$(document).ready(function(){
		oldUserName = $("#username").val();
		oldName = $("#name").val();
	});

	//去除首位空格
	function trim(val) {
		var regx = /(^[\s]+|[\s]+$)/g;
		if (regx.test(val)) {
			val = val.replace(regx, "");
		}
		return val;
	}
	
	/**
	 * 提交修改表单
	 */
	function doEdit() {
		var userName = $("#username").val();
		userName = trim(userName);
		var name = $("#name").val();
		name = trim(name);
		if (userName == null || userName == "") {
			Dialog.alert("登陆账号不能为空！");
			return;
		}else{
			if(userName != oldUserName){
				$.ajax({
					url : "check.action",
					data : "user.username=" + userName,
					type : "POST",
					dataType : "json",
					success : function(data) {
						if (data) {
							Dialog.alert("登陆账号已存在！");
							$("#username").val(trim(oldUserName));
						}else{
							if(name == null || name ==""){
								Dialog.alert("用户姓名不能为空！");
								return;
							}else{
								if(name != oldName){
									$.ajax({
										url : "checkName.action",
										data : "user.name=" + name,
										type : "POST",
										dataType : "json",
										success : function(data) {
											if (data) {
												Dialog.alert("用户名已存在，请重新输入用户姓名！");
												$("#name").val(trim(oldName));
											}else{
												var dept = $("#deptId").val();
												if (dept == -1) {
													Dialog.alert("请选择所属部门！");
													return;
												}
												var position = $("#positionId").val();
												if (position == -1) {
													Dialog.alert("请选择所属职位！");
													return;
												}
												var phone = $("#phone").val();
												var regx = /(^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$)/;
												phone = trim(phone);
												if (phone != null && phone != "") {
													if (!regx.test(phone)) {
														Dialog.alert("手机格式不正确！");
														return;
													}
												}
												var email = $("#email").val();
												var regx = /^[A-Za-z0-9\d]+([-_\.\+]*[A-Za-z0-9\d])*@([a-z0-9\d][a-z0-9\d-]{0,61}[a-z0-9\d]\.)+[a-z\d]{2,6}$/;
												email = trim(email);
												if (email != null && email != "") {
													if (!regx.test(email)) {
														Dialog.alert("email格式不正确！");
														return;
													}
												}
												$("#form").attr("action", "editOperate.action").submit();
											}
										}
									});
								}else{
									var dept = $("#deptId").val();
									if (dept == -1) {
										Dialog.alert("请选择所属部门！");
										return;
									}
									var position = $("#positionId").val();
									if (position == -1) {
										Dialog.alert("请选择所属职位！");
										return;
									}
									var phone = $("#phone").val();
									var regx = /(^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$)/;
									phone = trim(phone);
									if (phone != null && phone != "") {
										if (!regx.test(phone)) {
											Dialog.alert("手机格式不正确！");
											return;
										}
									}
									var email = $("#email").val();
									var regx = /^[A-Za-z0-9\d]+([-_\.\+]*[A-Za-z0-9\d])*@([a-z0-9\d][a-z0-9\d-]{0,61}[a-z0-9\d]\.)+[a-z\d]{2,6}$/;
									email = trim(email);
									if (email != null && email != "") {
										if (!regx.test(email)) {
											Dialog.alert("email格式不正确！");
											return;
										}
									}
									$("#form").attr("action", "editOperate.action").submit();
								}
							}
						}
					}
				});
			}else{
				if(name != oldName){
					$.ajax({
						url : "checkName.action",
						data : "user.name=" + name,
						type : "POST",
						dataType : "json",
						success : function(data) {
							if (data) {
								Dialog.alert("用户名已存在，请重新输入用户姓名！");
								$("#name").val(trim(oldName));
							}else{
								var dept = $("#deptId").val();
								if (dept == -1) {
									Dialog.alert("请选择所属部门！");
									return;
								}
								var position = $("#positionId").val();
								if (position == -1) {
									Dialog.alert("请选择所属职位！");
									return;
								}
								var phone = $("#phone").val();
								var regx = /(^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$)/;
								phone = trim(phone);
								if (phone != null && phone != "") {
									if (!regx.test(phone)) {
										Dialog.alert("手机格式不正确！");
										return;
									}
								}
								var email = $("#email").val();
								var regx = /^[A-Za-z0-9\d]+([-_\.\+]*[A-Za-z0-9\d])*@([a-z0-9\d][a-z0-9\d-]{0,61}[a-z0-9\d]\.)+[a-z\d]{2,6}$/;
								email = trim(email);
								if (email != null && email != "") {
									if (!regx.test(email)) {
										Dialog.alert("email格式不正确！");
										return;
									}
								}
								$("#form").attr("action", "editOperate.action").submit();
							}
						}
					});
				}else{
					var dept = $("#deptId").val();
					if (dept == -1) {
						Dialog.alert("请选择所属部门！");
						return;
					}
					var position = $("#positionId").val();
					if (position == -1) {
						Dialog.alert("请选择所属职位！");
						return;
					}
					var phone = $("#phone").val();
					var regx = /(^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$)/;
					phone = trim(phone);
					if (phone != null && phone != "") {
						if (!regx.test(phone)) {
							Dialog.alert("手机格式不正确！");
							return;
						}
					}
					var email = $("#email").val();
					var regx = /^[A-Za-z0-9\d]+([-_\.\+]*[A-Za-z0-9\d])*@([a-z0-9\d][a-z0-9\d-]{0,61}[a-z0-9\d]\.)+[a-z\d]{2,6}$/;
					email = trim(email);
					if (email != null && email != "") {
						if (!regx.test(email)) {
							Dialog.alert("email格式不正确！");
							return;
						}
					}
					$("#form").attr("action", "editOperate.action").submit();
				}
			}
		}
	}
</script>
</head>
<body>
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置：系统管理 &gt;&gt; 
								<span class="STYLE1">用户管理</span>&nbsp;&gt;&gt; 
								<span class="STYLE1">修改用户</span>
							</td>
						</tr>
					</table>
					<form id="form" method="post">
						<input type="hidden" name="user.userId" value="${user.userId }"></input>
						<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5">
									<span class="STYLE1">修改用户信息</span>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">登陆账号：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id = "password" type="hidden" name = "user.password" />
									<input id="username" name="user.username" type="text" value="${user.username }" size="20" class="inp"  maxlength="10" />
									<span style="margin-left:10px;"></span>
								</td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">用户姓名：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id="name" name="user.name" type="text" value="${user.name }" size="20" class="inp" maxlength="10"  />
									<span style="margin-left:10px;"></span></td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">性别：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:if test="user.sex==1">
										<input name="user.sex" type="radio" checked="checked" value="1" />男&nbsp;&nbsp; 
										<input name="user.sex" type="radio" value="2" />女
									</s:if> <s:else>
										<input name="user.sex" type="radio" value="1" />男&nbsp;&nbsp; 
										<input name="user.sex" type="radio" value="2" checked="checked" />女
									</s:else></td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属机构：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input name="user.org" type="text" value="${user.org }" size="20" class="inp" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属部门：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:select id="deptId" name="user.deptId" headerKey="-1" headerValue="--请选择--" list="depts" listKey="deptId" listValue="deptname" theme="simple" value="user.deptId" />
									<span style="margin-left:10px;"></span>
								</td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">职位：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:select id="positionId" name="user.positionId" headerKey="-1" headerValue="--请选择--" list="positions" listKey="positionId" listValue="postname" theme="simple" value="user.positionId" />
									<span style="margin-left:10px;"></span>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">任职状态：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:select id="state" name="user.state" list="#{'0':'离职', '1':'在职' }" listKey="key" listValue="value" value="user.state" theme="simple" />
								</td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">出生日期：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id="birthdate" name="user.birthdate" type="text" value="${date }" size="20" class="Wdate" style="width: 40%;height: 18px;" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" />
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">年龄：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id="age" name="user.age" type="text" value="${user.age }" size="20" class="inp" />
								</td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">e-mail：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id="email" name="user.email" type="text" value="${user.email }" size="20" class="inp" />
									<span style="margin-left:10px;"></span>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">手机号码：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
								<input id="phone" name="user.phone" type="text" size="20" class="inp" value="${user.phone }" maxlength="11" />
									<span style="margin-left:10px;"></span></td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否屏蔽：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF"><s:if
										test="user.flag==1">
										<input name="user.flag" type="radio" value="1"
											checked="checked" />是&nbsp;&nbsp;
										<input name="user.flag" type="radio" value="0" />否
									</s:if> <s:else>
										<input name="user.flag" type="radio" value="1" />是&nbsp;&nbsp;
										<input name="user.flag" type="radio" value="0"
											checked="checked" />否
									</s:else></td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">家庭住址：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
									<textarea name="user.address" cols="50" rows="3">${user.address }</textarea>
								</td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF">
									<div align="center">
										<input type="button" name="save" value="修改" onclick="return doEdit();" class="buttons" />
										<input type="reset" name="cancle" value="还原" class="buttons" />
										<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)" />
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