function keyPressed() {
		if( window.event.keyCode == 13 ) {
			doCommand();
		}
	}
    function init(){
        	document.body.onkeypress=keyPressed;
    		document.getElementsByName("userid")[0].focus();
	}
	function doCommand() {
		var isvalidate = document.getElementById("isvalidate").value;
		if(isvalidate) {
			doSubmit('validate');
		} else {
			doSubmit('');
		}
	}
	function doSubmit(o) {
		if(o=="validate") {
			if(checkWithoutValidate()) {
				document.getElementById("loginForm").submit();
			} else {
				return false;
			}
		} else {
			if(check()) {
				document.getElementById("loginForm").submit();
			} else {
				return false;
			}
		}
		
	}
	function checkWithoutValidate() {
		var str1 = document.all.userid.value;
		var str2 = document.all.pwd.value;
		var strRet = "";
		var ret = true;
		if(str1=="") {
			strRet = strRet + "用户名称 必须填写\n";
			ret = false;
		} else {
			if(matchRegExp("^[a-zA-Z0-9_]",str1,false) == false) {
				strRet = strRet + "用户名称 不符合格式，请使用字母、数字、下划线\n";
				ret = false;
			}
		}
		if(str2=="") {
			strRet = strRet + "登录密码 必须填写\n";
			ret = false;
		} else {
			if(matchRegExp("^[a-zA-Z0-9_]",str2,false) == false) {
				strRet = strRet + "登录密码 不符合格式，请使用字母、数字、下划线\n";
				ret = false;
			}
		}
		if(ret == false) {
			//Ext.MessageBox.alert("警告",strRet);
			alert(strRet);
		}
		return ret;
	}
	function check() {
		var str1 = document.all.userid.value;
		var str2 = document.all.pwd.value;
		var str3 = document.all.validateCode.value;
		var strRet = "";
		var ret = true;
		if(str1=="") {
			strRet = strRet + "用户名称 必须填写\n";
			ret = false;
		} else {
			if(matchRegExp("^[a-zA-Z0-9_]",str1,false) == false) {
				strRet = strRet + "用户名称 不符合格式，请使用字母、数字、下划线\n";
				ret = false;
			}
		}
		if(str2=="") {
			strRet = strRet + "登录密码 必须填写\n";
			ret = false;
		} else {
			if(matchRegExp("^[a-zA-Z0-9_]",str2,false) == false) {
				strRet = strRet + "登录密码 不符合格式，请使用字母、数字、下划线\n";
				ret = false;
			}
		}
		if(str3=="") {
			strRet = strRet + "验证码 必须填写\n";
			ret = false;
		} else {
			if(matchRegExp("^[a-zA-Z0-9_]",str3,false) == false) {
				strRet = strRet + "验证码 不符合格式，请使用字母和数字\n";
				ret = false;
			}
		}
		if(ret == false) {
			//Ext.MessageBox.alert("警告",strRet);
			alert(strRet);
		}
		return ret;
	}