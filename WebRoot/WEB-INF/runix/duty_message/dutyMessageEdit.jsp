<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>修改值班信息</title>
	<meta name="description" content="An admin template from Runix" />
	<meta name="author" content="Runix Inc." />
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<style>
.inp {
	width: 35%;
}
</style>
<script type="text/javascript">
		//初始化文本编辑器
		var editer;
		KindEditor.ready(function(K){
		  	editer=K.create("#content");
		  });
		//选择值班者
		function selectUser(){
/*			var date = new Date();
			//弹出选择值班者窗口
			var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			if(str != "" && str != null){
				var arr = str.split("%");
				$("#dutyPersonName").val(arr[0]);
				$("#dutyPersonId").val(arr[1]);
			}
			
	*/
			 var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 700;
				diag.Height = 300;
				diag.Top=40;
				diag.Title = "选择值班人员";
				diag.URL = "<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="
							+ date.getTime();
		diag.OKEvent = function() {
			var str = diag.innerFrame.contentWindow.doSelect();
			if (str != "" && str != null) {
				var arr = str.split("%");
				$("#dutyPersonName").val(arr[0]);
				$("#dutyPersonId").val(arr[1]);
			}
			diag.close();
		};
		diag.show();
	}

	function doCheck() {
		//取编辑器的值
		editer.sync();
		var dutyPerson = $("#dutyPersonId").val(), starttime = $("#starttime")
				.val(), endtime = $("#endtime").val(), dutyaddress = $(
				"#dutyaddress").val();
		if (dutyPerson == "" || dutyPerson == null) {
			Dialog.alert("请选择值班人！");
			return false;
		}
		if (starttime == "" || starttime == null) {
			Dialog.alert("请填写开始时间！");
			return false;
		}
		if (endtime == "" || endtime == null) {
			Dialog.alert("请填写结束时间！");
			return false;
		}
		if (dutyaddress == "" || dutyaddress == null) {
			Dialog.alert("请填写值班地点！");
			return false;
		}
		if (starttime > endtime) {
			Dialog.alert("请填写正确的开始时间和结束时间！");
			return false;
		}
		$("#form1").submit();
		return true;
	}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/dutyMessage/update.action" method="post" id="form1">
		<div style="width:99%; margin:0 auto; background-color:#fff;">
			<input type="hidden" name="dutyId_q" value="${dutyId_q}" />
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">
									&nbsp;&nbsp;当前位置:个人办公 &gt;&gt; <span class="STYLE1">值班信息</span>&gt;&gt; <span
									class="STYLE1">修改值班信息</span>
								</td>
							</tr>
						</table> <s:iterator value="dutyMessageList">
							<table width="100%" border="0" align="center" cellpadding="3"
								cellspacing="1" bgcolor="#CCCCCC">
								<tr>
									<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span
										class="STYLE1">修改值班信息</span></td>
								</tr>
								<tr>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">值
										班 者：</td>
									<td width="38%" height="20" bgcolor="#FFFFFF"><input
										id="dutyPersonName" type="text" value="${DUTYPERSONNAME}"
										onclick="selectUser();" size="20" /> <input type="hidden"
										name="dutyperson" id="dutyPersonId" value="${DUTYPERSON}" />
									</td>
									<td width="12%" height="20" align="right" bgcolor="#f8f8f8">班
										次：</td>
									<td width="38%" height="20" bgcolor="#FFFFFF"><input
										name="classes" type="text" value="${CLASSES}" size="20" /></td>
								</tr>
								<tr>
									<td width="12% height=" 20" align="right" bgcolor="#f8f8f8">开始时间：
									</td>
									<td height="20" bgcolor="#FFFFFF"><input class="Wdate"
										name="starttime" value="${STARTTIME}" id="starttime"
										type="text"
										onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})"
										size="20" /></td>
									<td width="12% height=" 20" align="right" bgcolor="#f8f8f8">结束时间：
									</td>
									<td height="20" bgcolor="#FFFFFF"><input class="Wdate"
										name="endtime" value="${ENDTIME}" id="endtime" type="text"
										onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})"
										size="20" /></td>
								</tr>
								<tr>
									<!-- <td width="12% height="20" align="right" bgcolor="#f8f8f8">值班日期： </td>
			            <td height="20" bgcolor="#FFFFFF"><input name="ZhiWu" type="text" width="15%"/></td> -->
									<td width="12% height=" 20" align="right" bgcolor="#f8f8f8">值班地点：
									</td>
									<td colspan="3" height="20" bgcolor="#FFFFFF"><input
										name="dutyaddress" id="dutyaddress" value="${DUTYADDRESS}"
										type="text" size="20" /></td>
								</tr>
								<tr>
									<td width="12%"  height="20" align="right" bgcolor="#f8f8f8">备
										注：</td>
									<td width="38%" height="20" colspan="3" bgcolor="#FFFFFF">
										<textarea name="remark" id = "content" style="width: 100%;height: 300px">${REMARK}</textarea></td>
								</tr>
								<tr>
									<td height="20" colspan="4" bgcolor="#FFFFFF"><div
											align="center">
											<input type="submit" class="buttons" name="save" value="修改" onclick="return doCheck();" />
											<input type="reset" class="buttons" name="cancle" value="还原" />
											<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)" />
										</div>
									</td>
								</tr>
							</table>
						</s:iterator>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
