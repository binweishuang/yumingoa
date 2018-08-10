/**
 * 去掉首尾空格
 */
function trim(val) {
	var regx = /(^[\s]+|[\s]+$)/g;
	if (regx.test(val)) {
		val = val.replace(regx, "");
	}
	return val;
}

/**
 * 校验密码   修改功能中使用
 */
function checkEditPasswd() {
	var passwd = $("#password").val();
	var tipe = $("#password").next();
	var regx = /^[\w]{6,18}$/;
	if (passwd == null || passwd == "") {
		return true;
	} else if (passwd.length < 6) {
		tipe.html("密码至少为6位！").css("color", "RED");
		return false;
	} else if (!regx.test(passwd)) {
		tipe.html("密码格式不正确！").css("color", "RED");
		return false;
	} else {
		tipe.html("通过信息验证").css("color", "BLUE");
		return true;
	}

}
/**
 * 校验确认密码 修改
 * 
 */
function checkEditPwd() {
	var surepwd = $("#surepwd").val();
	var passwd = $("#password").val();
	var tipe = $("#surepwd").next();
	var regx = /^[\w]{6,18}$/;
	if (surepwd == null || surepwd == "") {
		return true;
	} else if (surepwd.length < 6) {
		tipe.html("密码至少为6位！").css("color", "RED");
		return false;
	} else if (!regx.test(passwd)) {
		tipe.html("密码格式不正确！").css("color", "RED");
		return false;
	} else if (surepwd != passwd) {
		tipe.html("密码不一致！").css("color", "RED");
		return false;
	} else {
		tipe.html("通过信息验证").css("color", "BLUE");
		return true;
	}

}

/**
 * 删除用户
 */
function doDelete(id) {
	if (Dialog.confirm("确定删除?",function(){
		location.href = "del.action?user.userId=" + id;
	})) ;
}
/**
 * 查找
 */
function doSearch() {
	$("#form").attr("action", "search.action").submit();
}