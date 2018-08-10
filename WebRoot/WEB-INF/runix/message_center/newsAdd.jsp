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
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/message-center.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	var editer;
	//配置编辑器
	KindEditor.ready(function(K) {
		editer = K.create("#content");
	});
</script>
<style>
.inp {
	width: 35%;
}

#type {
	width: 37%;
	height: 23px;
}

#dept {
	width: 37%;
	height: 23px;
}
</style>
</head>
<body>
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;
								当前位置:公共信息   &gt;&gt;<span class="STYLE1">新闻管理</span>&gt;&gt;<span class="STYLE1">新建新闻</span>
							</td>
						</tr>
					</table>
					<form id="form" method="post">
						<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5">
									<span class="STYLE1">新建新闻</span><input  type="hidden" name="mage.dept" value="${dept }"/>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">标题：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<input id="title" name="mage.title" type="text" style="height:17px; width:36%;" size="20" maxlength="50" />
									<span style="margin-left:10px;"></span>
								</td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">类型：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:select id="type" name="mage.type" headerKey="-1" headerValue="--请选择--" list="types" listKey="dataId" listValue="dataname" theme="simple"
										onblur="checkMesg('新闻类型','NEWS');" /> <span
									style="margin-left:10px;"></span></td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">关
									&nbsp;键&nbsp;字：</td>
								<td height="20" bgcolor="#FFFFFF"><input id="keywords"
									style="height:17px; width:36%;" name="mage.keywords"
									type="text" /></td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">发布范围：
								</td>
								<td width="38%" height="20" bgcolor="#FFFFFF">
									<s:select id="scope" name="mage.scope" headerKey="-1" headerValue="--所有部门--" list="depts" listKey="deptId" listValue="deptname" theme="simple"  />
									<span style="margin-left:10px;"></span>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">新闻内容: </td>
								<td width="38%" bgcolor="#FFFFFF" colspan="3">
									<textarea name="mage.content" style="width:100%;height:300px" id="content"></textarea>
								</td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF">
									<div align="center">
										<input type="button" name="release" value="发布" onclick="doNewsRelease();" class="buttons" />
										<input type="button" name="save" value="保存" onclick="doNewsCheck();" class="buttons" />
										<input type="reset" name="cancle" value="重置" class="buttons" />
										<input type="button" name="back" value="返回" onclick="javascript:window.history.back(-1)" class="buttons" />
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