<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
<script type="text/javascript"  src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script src='<%=request.getContextPath()%>/dwr/interface/folderService.js'></script>
<script type="text/javascript">
	var flag = true;
	function checkFolderName() {
		var newID = $("#foldername").val();
		dwr.engine.setAsync (false);
		folderService.checkID(newID,function(data){
			if(data == null){
				//Dialog.alert("此文件夹名可以使用");
				flag = true;
			}else{
				Dialog.alert("此文件夹名称已存在，请重新输入!");
				flag =  false;
			}
		});
	}
	function doCheck() {
		var name = $("#foldername").val();
		if (name == null || name == "") {
			Dialog.alert("请输入有效的文件名称!");
			return false;
		}else{
			return flag;
		}
	}
	
</script>
<title>新建文件夹</title>
</head>
<body>
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<form id="form" name="form" action="<%=request.getContextPath()%>/folder/insert.action" method="post" onsubmit="return doCheck();">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置：
									公共信息 &gt;&gt; 
									<span class="STYLE1">网络硬盘</span>&nbsp;&gt;&gt;<span class="STYLE1">新建文件夹</span>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5">
								<span class="STYLE1">新建文件夹</span>
							</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">文件夹名称：</td>
								<td height="20" bgcolor="#FFFFFF">
									<input name="foldername" id="foldername" type="text" size="20" onblur="checkFolderName(this.value);" />
								</td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF">
									<div align="center">
										<input type="submit" class="buttons" name="save" value="保存"/>
										<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)" />
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
