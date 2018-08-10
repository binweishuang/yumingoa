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
 * 查找-车辆
 */
function doSearchByCar() {
	$("#car").attr("action", "query.action").submit();
}
/**
 * 校验年检时间
 */
function check_time(){
	var time=$("#checktime").val();
	var tipe=$("#checktime").next();
	time=trim(time);
	if(time!=null && time!=""){
		tipe.html("");
		return true;
	}else{
		tipe.html("请输入年检时间！").css("color", "RED");
		return false;
	}
}
/**
 * 校验新闻标题
 */
function checkNewsTitle() {
	var title = $("#title").val();
	var tipe = $("#title").next();
	title = trim(title);
	if (title == null || title == "") {
		tipe.html("请输入新闻标题！").css("color", "RED");
		return false;
	} else {
		tipe.html("");
		return true;
	}
}
/**
 * 校验信息类型
 */
var typeflag = false;
function checkMesg(val, type) {
	var $type = $("#type option:selected").val();
	var tipe = $("#type").next();
	if ($type == -1) {
		$.ajax({
			url : 'checkmesg.action',
			data : 'type=' + type,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				if (result) {
					tipe.html("请选择类型！").css("color", "RED");
					typeflag = false;
				} else {
					tipe.html("请在基础数据设置中添加" + val + "！").css("color", "RED");
					typeflag = false;
				}
			}
		});
	} else {
		tipe.html("");
		typeflag = true;
	}
}
/**
 * 校验发布范围
 */
var deptflag = false;
function checkDept() {
	var dept = $("#dept option:selected").val();
	var tipe = $("#dept").next();
	if (dept == -1) {
		$.ajax({
			url : 'checkdept.action',
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				if (result) {
					tipe.html("请选择发布范围！").css("color", "RED");
					deptflag = false;
				} else {
					tipe.html("请在部门中添加发布范围！").css("color", "RED");
					deptflag = false;
				}
			}
		});
	} else {
		tipe.html("");
		deptflag = true;
	}
}
/**
 * 校验通知标题
 */
function checkNoticeTitle() {
	var title = $("#title").val();
	var tipe = $("#title").next();
	title = trim(title);
	if (title == null || title == "") {
		tipe.html("请输入通知标题！").css("color", "RED");
		return false;
	} else {
		tipe.html("").css("color", "BLUE");
		return true;
	}
}
/**
 * 校验规章制度标题
 */
function checkRulesTitle() {
	var title = $("#title").val();
	var tipe = $("#title").next();
	title = trim(title);
	if (title == null || title == "") {
		tipe.html("请输入规章制度标题！").css("color", "RED");
		return false;
	} else {
		tipe.html("").css("color", "BLUE");
		return true;
	}
}
/**
 * 保存新闻
 */
function doNewsCheck() {
	checkDept();
	checkMesg('新闻类型', 'NEWS');
	if (checkNewsTitle() && typeflag && deptflag) {
		$("#form").attr("action", "persistNews.action").submit();
	}
}
/**
 * 保存通知
 */
function doNoticeCheck() {
	checkDept();
	checkMesg('通知类型', 'NOTICE');
	if (checkNoticeTitle() && typeflag && deptflag) {
		$("#form").attr("action", "persistNotice.action").submit();
	}
}
/**
 * 保存规章制度
 */
function doRulesCheck() {
	checkDept();
	checkMesg('规章制度类型', 'RULES');
	if (checkRulesTitle() && typeflag && deptflag) {
		$("#form").attr("action", "persistRules.action").submit();
	}
}
/**
 * 发布新闻
 */
function doNewsRelease() {
	checkDept();
	checkMesg('新闻类型', 'NEWS');
	if (checkNewsTitle() && typeflag && deptflag) {
		$("#form").attr("action", "releaseNews.action").submit();
	}
}
/**
 * 发布通知
 */
function doNoticeRelease() {
	checkDept();
	checkMesg('通知类型', 'NOTICE');
	if (checkNoticeTitle() && typeflag && deptflag) {
		$("#form").attr("action", "releaseNotice.action").submit();
	}
}
/**
 * 发布规章制度
 */
function doRulesRelease() {
	checkDept();
	checkMesg('规章制度类型', 'RULES');
	if (checkRulesTitle() && typeflag && deptflag) {
		$("#form").attr("action", "releaseRules.action").submit();
	}
}

/**
 * 修改新闻
 */
function modifyNews() {
	checkDept();
	checkMesg('新闻类型', 'NEWS');
	if (checkNewsTitle() && typeflag && deptflag) {
		$("#form").attr("action", "modifyNews.action").submit();
	}
}
/**
 * 修改通知
 */
function modifyNotice() {
	checkDept();
	checkMesg('通知类型', 'NOTICE');
	if (checkNoticeTitle() && typeflag && deptflag) {
		$("#form").attr("action", "modifyNotice.action").submit();
	}
}
/**
 * 修改规章制度
 */
function modifyRules() {
	checkDept();
	checkMesg('规章制度类型', 'RULES');
	if (checkRulesTitle() && typeflag && deptflag) {
		$("#form").attr("action", "modifyRules.action").submit();
	}
}
/**
 * 发布新闻
 */
function releaseNews() {
	checkDept();
	checkMesg('新闻类型', 'NEWS');
	if (checkNewsTitle() && typeflag && deptflag) {
		$("#form").attr("action", "relNews.action").submit();
	}
}
/**
 * 发布通知
 */
function releaseNotice() {
	checkDept();
	checkMesg('通知类型', 'NOTICE');
	if (checkNoticeTitle() && typeflag && deptflag) {
		$("#form").attr("action", "relNotice.action").submit();
	}
}
/**
 * 发布规章制度
 */
function releaseRules() {
	checkDept();
	checkMesg('规章制度类型', 'RULES');
	if (checkRulesTitle() && typeflag && deptflag) {
		$("#form").attr("action", "relRules.action").submit();
	}
}
/**
 * 查询信息中心中的新闻、通知和规章制度等信息的详细信息
 * 
 * @param id
 * @param type
 * @param typeName
 * @param dept
 */
function detail(id, type, typeName, dept) {

	if (type == "NEWS") {
		location.href = "newsDetail.action?messageId=" + id + "&type="
				+ typeName + "&dept=" + dept;
	}
	if (type == "NOTICE") {
		location.href = "noticeDetail.action?messageId=" + id + "&type="
				+ typeName + "&dept=" + dept;
	}
	if (type == "RULES") {
		location.href = "rulesDetail.action?messageId=" + id + "&type="
				+ typeName + "&dept=" + dept;
	}
}
/**
 * 删除新闻
 */
function delNews(id) {
	if (confirm("确定删除?")) {
		location.href = "delNews.action?mage.messageId=" + id;
	}
}
/**
 * 删除通知
 */
function delNotice(id) {
	if (confirm("确定删除?")) {
		location.href = "delNotice.action?mage.messageId=" + id;
	}
}
/**
 * 删除规章制度
 */
function delRules(id) {
	if (confirm("确定删除?")) {
		location.href = "delRules.action?mage.messageId=" + id;
	}
}
