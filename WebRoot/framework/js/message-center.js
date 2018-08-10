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
 * 查找-信息
 */
function doSearchByCenter() {
	$("#form").attr("action", "query.action").submit();
}
/**
 * 查找-新闻
 */
function doSearchByNews() {
	$("#form").attr("action", "queryNews.action?info=news").submit();
}
/**
 * 查找-通知
 */
function doSearchByNotice() {
	$("#form").attr("action", "queryNotice.action?info=notice").submit();
}
/**
 * 查找-规章制度
 */
function doSearchByRules() {
	$("#form").attr("action", "queryRules.action?info=rules").submit();
}
/**
 * 校验新闻标题
 */
function checkNewsTitle() {
	var title = $("#title").val();
	//var tipe = $("#title").next();
	title = trim(title);
	if (title == null || title == "") {
		Dialog.alert("请输入新闻标题！");
		//tipe.html("请输入新闻标题！").css("color", "RED");
		return false;
	} else if(!typeflag){
		Dialog.alert("请选择新闻类型！");
		return false;
	} else{
		checkDept();
		//tipe.html("");
		return true;
	}
}
/**
 * 校验信息类型
 */
var typeflag = false;
function checkMesg(val, type) {
	var $type = $("#type option:selected").val();
	if ($type == -1) {
		$.ajax({
			url : 'checkmesg.action',
			data : 'type=' + type,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				if (result) {
					typeflag = false;
				} else {
					Dialog.alert("请在基础数据设置中添加" + val + "！");
					typeflag = false;
				}
			}
		});
	} else {
		typeflag = true;
	}
}
/**
 * 校验发布范围
 */
var deptflag = false;
function checkDept() {
	var dept = $("#dept option:selected").val();
	if (dept == -1) {
		$.ajax({
			url : 'checkdept.action',
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				if (result) {
					Dialog.alert("请选择发布范围！");
					deptflag = false;
				} else {
					Dialog.alert("请在部门中添加发布范围！");
					deptflag = false;
				}
			}
		});
	} else {
		deptflag = true;
	}
}
/**
 * 校验通知标题
 */
function checkNoticeTitle() {
	var title = $("#title").val();
	title = trim(title);
	if (title == null || title == "") {
		Dialog.alert("请输入通知标题！");
		return false;
	} else if(!typeflag){
		Dialog.alert("请选择通知类型！");
		return false;
	} else{
		checkDept();
		return true;
	}
}
/**
 * 校验规章制度标题
 */
function checkRulesTitle() {
	var title = $("#title").val();
	title = trim(title);
	if (title == null || title == "") {
		Dialog.alert("请输入规章制度主题！");
		return false;
	} else if(!typeflag){
		Dialog.alert("请选择规章制度类型！");
	}else{
		return true;
	}
}
/**
 * 保存新闻
 */
function doNewsCheck() {
	editer.sync();
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
	editer.sync();
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
	editer.sync();
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
	editer.sync();
	//checkDept();
	//checkMesg('新闻类型', 'NEWS');
	if (checkNewsTitle() && typeflag) {
		$("#form").attr("action", "releaseNews.action").submit();
	}
}
/**
 * 发布通知
 */
function doNoticeRelease() {
	editer.sync();
	//checkMesg('通知类型', 'NOTICE');
	if (checkNoticeTitle() && typeflag && deptflag) {
		$("#form").attr("action", "releaseNotice.action").submit();
	}
}
/**
 * 发布规章制度
 */
function doRulesRelease() {
	editer.sync();
	if (checkRulesTitle() && typeflag) {
		$("#form").attr("action", "releaseRules.action").submit();
	}
}

/**
 * 修改新闻
 */
function modifyNews() {
	editer.sync();
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
	editer.sync();
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
	editer.sync();
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
	editer.sync();
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
	editer.sync();
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
	editer.sync();
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

/**
 * 删除新闻
 */
function delNews(id) {
	Dialog.confirm("确定删除吗？",function(){
		location.href = "delNews.action?mage.messageId=" + id;
	});
}
/**
 * 删除通知
 */
function delNotice(id) {
	Dialog.confirm("确定删除吗？",function(){
		location.href = "delNotice.action?mage.messageId=" + id;
	});
}
/**
 * 删除规章制度
 */
function delRules(id) {
	Dialog.confirm("确定删除吗？",function(){
		location.href = "delRules.action?mage.messageId=" + id;
	});
}
