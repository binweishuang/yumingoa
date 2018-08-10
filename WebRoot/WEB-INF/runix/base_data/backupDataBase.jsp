<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		function doCheck(){
			var path=document.getElementById('path').value;
			var fileName=document.getElementById('fileName').value;
			var reg1=/[C-Fc-f]:\/?[a-zA-Z0-9\_\-]*$/;
			var reg2=/^[a-zA-Z0-9\_\-]+$/;
			if(path==''||path==null){
				Dialog.alert('保存路径不能为空！');
				return;
			}else if(!reg1.test(path)){
				Dialog.alert('保存路径格式不正确！');
				return;
			}
			if(fileName==''||fileName==null){
				Dialog.alert('文件名不能为空！');
				return;
			}else if(!reg2.test(fileName)){
				Dialog.alert('文件名格式不正确！');
				return;
			}
			document.getElementById('backupForm').action="backup.action";
			document.getElementById('backupForm').submit();
			
		}
	</script>
</head>
<body >
	<s:form id="backupForm" method="post" theme="simple">
	<s:hidden name="setattendId" />
	<s:hidden name="addOrUpdate"/>
          <div class="tagContent" id="tagContent2" style=" margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
           <tr>
            <td>
			<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			  <tr>
				<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">备份数据库</span></td>
			  </tr>
			  <tr>
				<td width="12%" height="20" align="right" bgcolor="#f8f8f8">保存路径： </td>
				<td width="88%" height="20" align="left" bgcolor="#FFFFFF" style="border:0px;"><input id="path" name="path" type="text"  value="" size="30"/>  <span style="color:red">例如：d:/data</span></td>
			  </tr>	
			  <tr>
				<td width="12%" height="20" align="right" bgcolor="#f8f8f8">文件名： </td>
				<td width="88%" height="20" align="left" bgcolor="#FFFFFF" style="border:0px;">
							<input id="fileName" name="fileName" type="text" value=""
								size="30" />  <span style="color:red">例如：abc2013</span>
						</td>
			  </tr>										  
			  <tr>
				<td height="20" colspan="2" bgcolor="#FFFFFF">
				  <div align="center">
					<input type="button" name="save" value="执行备份" class="buttons" onclick="doCheck();"/>
				  </div>
				</td>
			  </tr>
				
			</table>
			</td>
			</tr>
			<tr >
					<td height="20" colspan="2" bgcolor="#FFFFFF" >
					  <div id="message_div" style="color:red;background: #FFEFD5;border:0px solid #FFDAB9;border-collapse: collapse;">
					     <s:actionmessage/>
				      </div>
					</td>
				  </tr>
			</table>
		</div>
</s:form>
</body>
</html>