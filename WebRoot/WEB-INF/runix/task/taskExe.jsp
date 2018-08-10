<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<% 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	 <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
		<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript">
		function uploadFile(t,path){
			//alert(t);
			window.open(t+path);	
		}
		var editer;
		var editer2;
		//配置编辑器
		  KindEditor.ready(function(K){
		  	editer=K.create("#content");
		  	editer2=K.create("#taskcomplete");
		  });
		function doCheck(){
			editer.sync();
			editer2.sync();
			var process = document.getElementById('process').value;
			var taskcomplete = editer2.html();
			if(process==''||process==null){
				Dialog.alert('任务进度不能为空！');
				return;
			}else if(parseInt(process)>100){
				Dialog.alert('请输入规范的任务进度（百分比）！');
				return;
			}
			if(taskcomplete==''||taskcomplete==null){
				Dialog.alert('完成情况不能为空！');
				return;
			}
			document.form.submit();
		}	
	</script>
</head>
<body >
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
       <s:form id="from" name="form" action="exe.action" method="post" target="_parent">   	
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>	  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">执行任务</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">任务标题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">${task.title}</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">汇报对象： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">${reportTo}</td>
          </tr>
          <tr>
            <td  height="20" align="right" bgcolor="#f8f8f8">附    件： </td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><a href="javascript:uploadFile('<%=basePath%>','${task.attachpath}');">${task.attachname }</a></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">执行人： </td>
            <td height="20" bgcolor="#FFFFFF">${task.executer}</td>
            <td height="20" align="right" bgcolor="#f8f8f8">任务时限： </td>
            <td height="20" bgcolor="#FFFFFF">${task.timelimit}</td>
          </tr>       
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
            <td bgcolor="#FFFFFF"  colspan="3"><textarea id='content' style="width: 100%;height: 300px" name='content' readonly="readonly">${task.content}</textarea></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">总进度： </td>
            <td height="20" bgcolor="#FFFFFF">
				${task.totalprocess}&nbsp;&nbsp;%
			</td>
			<td height="20" align="right" bgcolor="#f8f8f8">个人进度： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input type="text" id="process" name="process" value="${userTask.process}" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /> %
				<input type="hidden" name="taskId" value="${task.taskId}"/>
				<input type="hidden" name="utId" value="${userTask.utId}"/>
			</td>
          </tr> 
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">完成情况： </td>
            <td height="20" bgcolor="#FFFFFF"  colspan="3">
				<textarea id='taskcomplete' style="width: 100%;height: 300px" name='taskcomplete' >${userTask.taskcomplete}</textarea>
			</td>
          </tr> 
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center"> 
				<input type="button" class="buttons" name="save" value="保存" onclick="doCheck();"/>
                <input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</s:form>
</div>
</body>
</html>