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
			var starttime = document.getElementById('starttime').value;
			var endtime = document.getElementById('endtime').value;
			var absentlimit = document.getElementById('absentlimit').value;
			var rest = document.getElementsByName('rest');
			
			var flag = false;
			var reg = /^([2][0-3])|([0-1][0-9])\:[0-5][0-9]$/;
			if(starttime==''||starttime==null){
				Dialog.alert('上班开始时间不能为空！');
				return;
			}else if(!reg.test(starttime)){
				Dialog.alert('上班开始时间格式不正确！注意：请使用英文状态下":"！');
				return;
			}
			if(endtime==''||endtime==null){
				Dialog.alert('上班结束时间不能为空！');
				return;
			}else if(!reg.test(endtime)){
				Dialog.alert('上班结束时间格式不正确！');
				return;
			}
			//sy 添加验证开始时间和结束时间
			if(starttime > endtime){
				Dialog.alert('上班开始时间不能小于结束时间！');
				return;
			}
		for(i=0;i<rest.length;i++){
				if(rest[i].checked){
				 flag=true;
				}
			}
			if(!flag){
				Dialog.alert('请选择休息设定！');
				return;
			}
			if(absentlimit!=''&&absentlimit!=null){
				if(!reg.test(absentlimit)){
				Dialog.alert('旷工限定时间格式不正确！');
				return;
				}
			}
			
			document.getElementById('setAttendForm').action="update.action";
			document.getElementById('setAttendForm').submit();
			
		}
	</script>
</head>
<body >
	<s:form id="setAttendForm" method="post" theme="simple">
	<s:hidden name="setattendId" />
	<s:hidden name="addOrUpdate"/>
          <div class="tagContent" id="tagContent1" style=" margin:0 auto; background-color:#fff;">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
             <tr>
               <td>
				<table width="100%"  align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC" style="border-bottom:solid 1px #FFF">
				  <tr>
					<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">考勤设置</span></td>
				  </tr>
				  <tr>
					<td width="15%" height="20" align="right" bgcolor="#f8f8f8">上班时间： </td>
					<td width="85%" height="20" align="left" bgcolor="#FFFFFF" style="border:0px;"> 从<input id="starttime" name="starttime" type="text"  value="<s:property value='starttime'/>" size="15"/> 到 <input id="endtime" name="endtime" type="text"  value="<s:property value='endtime'/>" size="15"/>
					<span style="color:red">例如：09:00</span></td>
				  </tr>	
				  <tr>
					<td width="15%" height="20" align="right" bgcolor="#f8f8f8">休息设定： </td>
					<td width="85%" height="20" align="left" bgcolor="#FFFFFF" style="border:0px;"><input name="rest" type="radio"  value="1" <s:if test='rest=="1"'  >checked="true"</s:if>/>一周双休<input name="rest" type="radio"  value="2" <s:if test='rest=="2"'  >checked="true"</s:if>/>一周单休<input name="rest" type="radio"  value="3" <s:if test='rest=="3"'  >checked="true"</s:if>/>不休息
					</td>
				  </tr>	
				<tr>
					<td width="15%" height="20" align="right" bgcolor="#f8f8f8">旷工限定时间： </td>
					<td width="85%" height="20" align="left" bgcolor="#FFFFFF" style="border:0px;">超过<input id="absentlimit" name="absentlimit" type="text"  value="<s:property value='absentlimit'/>" size="15"/> 签到算旷工，为空则不设定旷工时间（全天不签到的也算旷工，有假期的、出差除外）。
					<span style="color:red">例如：09:00</span></td>
				  </tr>											  
				  <tr>
					<td height="20" colspan="3" bgcolor="#FFFFFF">
					  <div align="center">
						<input type="button" name="save" value="设置" class="buttons"  onclick="doCheck();"/>
					  </div>
					</td>
				  </tr>
				  
				</table>
				</td>
				</tr>
				<tr >
					<td height="20" colspan="3" bgcolor="#FFFFFF" >
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