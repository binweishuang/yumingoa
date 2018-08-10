<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>参与投票</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/answerService.js'></script>
	<style>
		.newFile{ border:1px solid #333333; width:80px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#B0E0E6;cursor:pointer}	 
	</style>
	<script>
		function doCheck(){
			var questiontype = $('#questiontype').val();
			var loginname = $('#loginname').val();
			var surveyId = $('#surveyId').val();
			var flag=true;
			var flag2 ;
			DWREngine.setAsync(false);
			answerService.checkLoginname(loginname,surveyId,function(data){
				if(data>0){
					Dialog.alert('您已参与投票，不可重复投票！');
					flag=false;
				}
			});
		//	alert(flag);
			if(!flag){
				return;
			}
			if(questiontype=='1'){
				$('input[name="ck"]:checked').each(function() {
				var answerId = $(this).next().val();
				var uservotenum = $(this).next().next().val();
				var votepeoples = $(this).next().next().next().val();
				var str = answerId+"%"+uservotenum+"%"+votepeoples+"%"+loginname;	
				//	alert(answerId);
					DWREngine.setAsync(false);
					answerService.modifyAnswer(str,function(data){
						if(data==1){
							flag2=true;
						}else{
							flag2=false;
						}
					});
				});
			}else if(questiontype=='0'){
				$('input[name="ra"]:checked').each(function() {
					var answerId = $(this).next().val();
					var uservotenum = $(this).next().next().val();
					var votepeoples = $(this).next().next().next().val();
					var str = answerId+"%"+uservotenum+"%"+votepeoples+"%"+loginname;	
					DWREngine.setAsync(false);
					answerService.modifyAnswer(str,function(data){
						if(data==1){
							flag2=true;
						}else{
							flag2=false;
						}
						});
					});
			}
			return flag2;
		}
	</script>
</head>
<body >
<s:hidden name="questiontype" id="questiontype"></s:hidden>
<s:hidden name="loginname" id="loginname"></s:hidden>
<s:hidden name="surveyId" id="surveyId"></s:hidden>
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
      		  <tr>
       		  <td>      		  
				<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				  <tr>
				    <td width="15%" height="20" align="right" bgcolor="#f8f8f8">投票标题： </td>
					<td width="85%" height="20" bgcolor="#FFFFFF" style="border:0px;"><s:property value='title'/></td></td>
				  </tr>
				  <tr>						
				    <td  height="20" align="right" bgcolor="#f8f8f8">选项： </td>
					<td  height="180" bgcolor="#FFFFFF">
						<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							<s:iterator value="answerList" status="st">
							<tr height="32px">										 
							  <td align="right" style="border:0;width:10%"><s:property value="sortnum"/>、</td>
							  <td  style="width:70%;text-align:left;border:0;"><s:property value="content"/></td>
							  <td align="right" style="width:20%;border:0;"><s:if test='questiontype=="1"'><input name="ck" type="checkbox"/><s:hidden name="answerId"></s:hidden><s:hidden name="uservotenum"></s:hidden><s:hidden name="votepeoples"></s:hidden></s:if><s:elseif test='questiontype=="0"'><input name="ra" type="radio"/><s:hidden name="answerId"></s:hidden><s:hidden name="uservotenum"></s:hidden><s:hidden name="votepeoples"></s:hidden></s:elseif></td>
							</tr>
							</s:iterator>
									
			            </table>	
					</td>
				  </tr>					 

				</table>
			  </td>
	 	  </tr>
	  </table>
	</div>
</body>
</html>
