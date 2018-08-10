<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<style>
	.inp{width:35%;}
</style>
	<script type="text/javascript">
		function selectReporter(){
			var date = new Date();
			//弹出选择上报对象窗口
			var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			if(str != "" && str != null){
				var arr = str.split("%");
				$("#name").val(arr[0]);
				$("#userId").val(arr[1]);
			}
		}
		
		function doCheck(){
			editer.sync();
			var title = document.getElementById('title').value;
			if(title==''||title==null){
				Dialog.alert('概述不能为空！');
				return;
			}else if(title.length>50){
				Dialog.alert('概述长度不能超过50个字符！');
				return;
			}
			document.getElementById('personnelForm').action='insert.action';
			document.getElementById('personnelForm').submit();
		}
	</script>
<script type="text/javascript">
	var editer;
	//配置编辑器
	KindEditor.ready(function(K) {
		editer = K.create("#content");
	});
</script>
</head>
<body >
       <div  style="width:99%; margin:0 auto; background-color:#fff;">
       <s:form id="personnelForm" action="personnelAction" method="post">
       <s:hidden name="flag" ></s:hidden>
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">新建转正信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<select id="workflowId" name="personnel.workflowId" style="width:155px">
					<option value="ZHZH">转正管理</option>
				</select>
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">姓　　名： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:property value="name"/>
            </td>
          </tr>
          <tr>
           <td width="12%" height="20" align="right" bgcolor="#f8f8f8">概　　述： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<input type="text" id="title" name="title" size="30" />	
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">部　　门： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<s:property value="dept"/>
            </td>
          </tr> 
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">编辑内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF">
            	<textarea name="content" id="content" style="height: 300px;width: 100%"></textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
	            <div align="center">
	                <input type="button" class="buttons" name="save" value="下一步"  onclick="doCheck();"/>
	                <input type="reset" class="buttons" name="cancle" value="重置" />
					<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
	            </div>
            </td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</s:form>              
</div>
</body>
</html>