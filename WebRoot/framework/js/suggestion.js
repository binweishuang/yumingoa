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
 * 查找-个人建议
 */
function doSearchBySuggestion() {
	$("#form").attr("action", "query.action").submit();
}

/**
 * 校验建议标题
 */
function checkSuggestionTitle() {
	var title = $("#title").val();
	title = trim(title);
	if (title == null || title == "") {
		Dialog.alert("请输入建议主题！");
		return false;
	} else {
		return true;
	}
}

/**
 * 校验信息类型
 */
var typeflag = false;
function checkMesg(val, type) {
	var $type = $("#suggesttype option:selected").val();
	if ($type == -1) {
		$.ajax({
			url : 'checkmesg.action',
			data : 'suggesttype=' + type,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				if (result) {
					Dialog.alert("请选择类型！");
					typeflag = false;
				} else {
					Dialog.alert("请在基础数据设置中添加建议类型！");
					typeflag = false;
				}
			}
		});
	} else {
		typeflag = true;
	}
}

/**
 *建议人员
 */
function checkUser() {
	var name=$("#username").val();
	name=trim(name);
	if(name!=null && name!=""){
		return true;
	}else{
		Dialog.alert("请选择建议人员！");
		return false;
	}
}

/**
 * 提交保存验证
 */
function doPersist() {
	var title=checkSuggestionTitle();
	if(title){
		checkMesg('建议类型','SUGGEST');
	}
	if(typeflag){
		var user=checkUser();
		if(user){
			$("#form").attr("action", "persist.action").submit();
		}
	}
}

/**
 * 提交修改表单验证
 */
function doModify() {
	var title=checkSuggestionTitle();
	if(title){
		checkMesg('建议类型','SUGGEST');
	}
	if(typeflag){
		var user=checkUser();
		if(user){
			$("#form").attr("action", "update.action").submit();
		}
	}
}

/**
 * 删除
 */
function del(id) {
	if (Dialog.confirm("确定删除?",function(){
		location.href = "del.action?suggestionId=" + id;
	}));
}

function selectUser() {
   var date = new Date();
   var diag = new Dialog();
		diag.Width  = 700;
		diag.Height = 300;
		diag.Top=40;
		diag.Title = "选择建议人员";
		diag.URL = "../selectPeople/query.action?random="+date.getTime();
		diag.OKEvent = function(){
			var str=diag.innerFrame.contentWindow.doSelect();
			if(str != "" && str != null){
				var array=str.split("%");
				$("#username").val(array[0]);
				$("#userId").val(array[1]);
				}
				diag.close();
			};	
			diag.show();
}