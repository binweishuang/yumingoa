/**
 * 去掉首尾空格
 * 
 */
function trim(val) {
	var regx = /(^[\s]+|[\s]+$)/g;
	if (regx.test(val)) {
		val = val.replace(regx, "");
	}
	return val;
}
/**
 * 获取部门名称
 * 
 */
var oldDeptName = "";
$(function() {
	oldDeptName = $("#deptname").val();
});
/**
 * 校验部门名称
 * 
 */
var flag = false;
function checkDeptName() {
	var deptName = $("#deptname").val();
	//var tip = $("#deptname").next();
	deptName = trim(deptName);
	if (deptName == null || deptName == "") {
		Dialog.alert("部门名称不能为空！");
		//tip.html("部门名称不能为空！").css("color", "RED");
		flag = false;
	} else if(deptName!=oldDeptName){
		$.ajax({
			url : "check.action",
			data : "dept.deptname=" + deptName,
			type : "POST",
			dataType : "json",
			success : function(data) {
				if (data) {
					Dialog.alert("部门名称不能相同！");
					//tip.html("部门名称不能相同！").css("color", "RED");
					flag = false;
				} else {
					//tip.html("部门名称可用").css("color", "BLUE");
					flag = true;
				}
			}
		});
	}else{
		flag=true;
	}
}

/**
 * 校验部门电话
 * 
 */
function checkPhone() {
	var phone = $("#phone").val();
	//var tip = $("#phone").next();
	var regx = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
	phone = trim(phone);
	if (phone != null && phone != "") {
		if (!regx.test(phone)) {
			Dialog.alert("联系电话格式不正确！");
			//tip.html("联系电话格式不正确！").css("color", "RED");
			return false;
		}
		//tip.html("通过信息验证").css("color", "BLUE");
		return true;
	}
	return true;
}
/**
 * 校验部门的上级机构
 * 
 */
function checkParentOrganization() {
	var org = $("#parentid").val();
	//var tip = $("#parentid").next();
	if (org == -1) {
		//tip.html("请选择上级机构！").css("color", "RED");
		return false;
	}
	//tip.html("通过信息验证").css("color", "BLUE");
	return true;
}
/**
 * 提交添加表单
 */
function doAdd() {
	checkDeptName();
	var phone = checkPhone();
	//var org = checkParentOrganization();
	if (flag && phone) {
		$("#form").attr("action", "addOperate.action").submit();
	}
}
/**
 * 提交修改表单
 */
function doEdit() {
	checkDeptName();
	var phone = checkPhone();
	//var org = checkParentOrganization();
	if (flag && phone) {
		$("#form").attr("action", "EditOperate.action").submit();
	}
}
/**
 * 删除部门
 */
function doDelete(id) {
	if(confirm("确定删除?")){
		location.href="del.action?dept.deptId="+id;
	}
}
function dobody(){
	var result="${result}";
	if(result){
		alert("删除失败!");
	}
}