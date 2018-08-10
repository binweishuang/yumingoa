/**
	版本号		1.0
	创建日期	2013/2/22 星期五
	创建人		王志宏
	obj 是输入验证的值
	type是  提示方式,0代表 弹出  1代表非弹出型提示
	sysOutId  代表党type为1 时  提示的标签id
	备注	所有输入的int 类参数  都是字符格式的 需要使用""引起来
**/
//验证邮箱格式
function checkEmail(obj,type,sysOutId){
	var tempEmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!tempEmail.test(obj)){
		if(type == "0"){
			alert("电子邮箱格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="电子邮箱格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}
//验证密码格式
function checkPwd(obj,type,sysOutId){
	var tempPwd= /^[a-zA-Z][0-9a-zA-Z_]{5,11}$/;
	if(!tempPwd.test(obj)){
		if(type == "0"){
			alert("密码格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="密码格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}
//验证用户名格式
function checkUserId(obj,type,sysOutId){
	var tempUserId = /^[a-zA-Z][0-9a-zA-Z_]{5,11}$/;
	if(!tempUserId.test(obj)){
		if(type == "0"){
			alert("用户名格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="用户名格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}

//真实姓名
function checkRealName(obj,type,sysOutId){
	var tempRealName = /^[\u4e00-\u9fa5]{1,3}$/;
	if(!tempRealName.test(obj)){
		if(type == "0"){
			alert("真实姓名格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="真实姓名格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}



//验证出生日期格式
function checkDate(obj,type,sysOutId){
	var tempDate = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
	if(!tempDate.test(obj)){
		if(type == "0"){
			alert("出生日期格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="出生日期格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}
//验证身份证格式
function checkCard(obj,type,sysOutId){
	var tempCard = /^[1-9][0-9]{14,17}$/;
	if(!tempCard.test(obj)){
		if(type == "0"){
			alert("身份证格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="身份证格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}
//验证固话格式
function checkTel(obj,type,sysOutId){
	var tempTel = /^[5][0-9]{7}$/;
	if(!tempTel.test(obj)){
		if(type == "0"){
			alert("电话格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="电话格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
	
}
//验证手机格式
function checkPhone(obj,type,sysOutId){
	var tempPhone = /^[1][0-9]{10}$/;
	if(!tempPhone.test(obj)){
		if(type == "0"){
			alert("手机格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="手机格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}
//验证QQ格式
function checkQQ(obj,type,sysOutId){
	var tempQQ = /^[0-9]{4,10}$/;
	if(!tempQQ.test(obj)){
		if(type == "0"){
			alert("QQ格式不正确!");
		}else {
			document.getElementById(sysOutId).innerHTML="QQ格式不正确!";
		}
		return false;
	}else {
		if(type !="0"){
			document.getElementById(sysOutId).innerHTML="";
		}
	}
	return true;
}

/**
	版本号		1.0
	创建日期	2013/2/22 星期五
	创建人		王志宏
	checkList 是一个二维数组 例如 checkList[0] = [checkobj,outobj]
	type 是类型   0 代表弹出型提示 1代表非弹出型提示
	outMessageList 是一个二维数组,当type =1 时  代表将提示输出给那个元素  例如 outMessageList[0] = [outMessageId,outMessage]
	备注 当type = 1 时   checkList必须 跟outMessageList  对称
**/

//非空验证
function isNotNull(checkList,type,outMessageList){
	var childList;
	//弹出框类型提示
	if(type == "0") {
		for(var i = 0 ; i < checkList.length; i++){
			childList = checkList[i];
			//当要判定的输入参数为空
			if(childList[0] == "" || childList[0] == null){
				//输出提示
				alert(""+childList[1]+"");
				return false;
			}
		}
	}else {
		var messageList = null;
		//非弹出框型提示
		var count = 0;
		for(var i = 0; i < checkList.length;i++){
			childList = checkList[i];
			messageList = outMessageList[i];
			if(childList[0] == "" || childList[0] == null){
				document.getElementById(""+messageList[0]+"").innerHTML = ""+messageList[1]+"";
				count++;
			} else {
				//提示可以自定义
				document.getElementById(""+messageList[0]+"").innerHTML = "";
			}
		}
		//如果不通过数大于0  返回false
		if(count > 0){
			return false;
		}
	} 
	return true;
}

/***
版本号			1.0
创建日期		2013/2/22 星期五
创建人			王志宏
备注			javaScriptCheck的通用版,所有int型参数都是字符格式
inputList  输入参数是一个数组 例如 inputList = [inputobj,outobj]
inputELS  是验证的正则表达式
isNull    是否做非空验证   0做,1不做
showType  是消息提示类型  0代表为弹出框类型 1非弹出框类型
showMessageList  也是一个数组  例如 showMessageList = [outId,outMessage];
***/
//自定义 验证
function comCheck(inputList,inputELS,isNull,showType,showMessageList){
	var tempCheck = inputELS;
	//做非空验证
	if(isNull=="0"){
		if(null == inputList[0] || "" == inputList[0]){
			if(showType="0"){
				alert(""+inputList[1]+"");
			} else {
				document.getElementById(""+showMessageList[0]+"").innerHTML = ""+showMessageList[1]+"";
			}
			return false;
		}
		//格式验证
		if(!tempCheck.test(inputList[0])){
			if(showType=="0"){
				alert(""+inputList[1]+"");
			}else {
				document.getElementById(""+showMessageList[0]+"").innerHTML = ""+showMessageList[1]+"";
			}	
			return false;
		}
	} else {
		//可为空的情况下,若输入数据则验证格式
		if(inputList[0]!=null && inputList[0]!=""){
			if(!tempCheck.test(inputList[0])){
				if(showType=="0"){
					alert(""+inputList[1]+"");
					return false;
				}else {
					document.getElementById(""+showMessageList[0]+"").innerHTML = ""+showMessageList[1]+"";
					return false;
				}	
			} else {
				document.getElementById(""+showMessageList[0]+"").innerHTML = "";
			} 
		} else {
			document.getElementById(""+showMessageList[0]+"").innerHTML = "";
		}
	}
	return true;
}




