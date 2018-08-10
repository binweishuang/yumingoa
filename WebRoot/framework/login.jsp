<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%= SystemConfig.APP_NAME %></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/style-login.css">
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<script language="javascript">
function init() {
	document.body.onkeypress=keyPressed;
    document.getElementsByName("username")[0].focus();
}

function keyPressed() {
	if( window.event.keyCode == 13 ) {
		dosubmit();
	}
}

$(function(){
	init();
	var str="<%=request.getContextPath() %>/validateCodeAction.action?"+Math.random();
	$("#codeImg").attr("src",str);
	$("#username").focus();
});

/*用户名*/
function checkName(){
	var name = $("#username").val();
	if(name == "" || name == null){
		Dialog.alert("请填写用户名");
		return false;
	}
	return true;
}

/*密码*/
function checkPwd(){
	var pwd = $("#password").val();
	if(pwd == "" || pwd == null){
		Dialog.alert("请填写密码");
		return false;
	}else{
		return true;
	}
}

/*验证码*/
function checkCode(){
	var code = $("#validateCode").val();
	if(code == "" || code == null){
		Dialog.alert("请填写验证码");
		return false;
	}
	return true;
}

/*表单提交*/
function dosubmit(){
	var flag=false;
		if(checkName()&&checkPwd()&&checkCode()){
			var username = $("#username").val();
			var pwd = $("#password").val();
			var code = $("#validateCode").val();
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/loginAction!checkLogin.action",
				data:"username="+username+"&password="+pwd+"&validateCode="+code,
				dataType:"json",
				async:false,
				success:function(msg){
					if(msg == 1){
						Dialog.alert("用户不存在");
						$("#validateCode").val("");
						$("#codeImg").attr("src","<%=request.getContextPath() %>/validateCodeAction.action?"+Math.random());
					}
					if(msg == 2){
						Dialog.alert("密码不正确");
						$("#password").val("");
						$("#validateCode").val("");
						$("#codeImg").attr("src","<%=request.getContextPath() %>/validateCodeAction.action?"+Math.random());
					}
					if(msg == 3){
						Dialog.alert("验证码不正确");
						$("#password").val("");
						$("#validateCode").val("");
						$("#codeImg").attr("src","<%=request.getContextPath() %>/validateCodeAction.action?"+Math.random());
					}
					if(msg == 4){
						Dialog.alert("用户已离职");
					}
					if(msg == 5){
						flag=true;
					}
				}
			});
		}
		if(flag){
			var theform = document.getElementById('loginForm');
			theform.submit();
		}
		
}

</script>
</head>
<body onload="init();">
	<br><br><br><br>
	<div class="login-tit">OA办公管理系统</div>
	<!-- demo content -->
	<div class="loginform cf">
		<s:form id="loginForm" action="loginAction!login" method="post" theme="simple">
			<div class="login1">
				用户名：<input style=" width:150px; height:20px;"  name="username" id="username" type="text" />
			</div>
	 		<div class="login1">
	 			密　码：<input style=" width:150px; height:20px;" name="password" id="password" type="password" />
	 		</div>
     		<div class="login1">
     			验证码：<input style="width:70px; height:18px;" name="validateCode" id="validateCode" maxlength="4" type="text" />
				<img style="vertical-align: middle;height: 25px" id="codeImg" alt="看不清楚？换个图片" src="" onClick="this.src ='validateCodeAction.action?'+Math.random()" width="68" />	</div>
       		<div class="login2">
       			<a href="javascript:void(0);" onclick="dosubmit();"><img src="<%=request.getContextPath()%>/framework/img/login-dl.jpg" /></a>
                <a href="javascript:void(0);" ><img src="<%=request.getContextPath()%>/framework/img/login-qx.jpg" /></a>
            </div>
		</s:form>
	</div>
	<div style="text-align:center;font-size:13px; color:#5D5D5D; margin-top:70px;">
		<p>版权所有：某某信息科技有限公司</p>
	</div>
</body>

</html>
