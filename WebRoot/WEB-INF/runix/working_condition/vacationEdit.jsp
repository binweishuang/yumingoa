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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	var editer;
	//配置编辑器
	KindEditor.ready(function(K) {
		editer = K.create("#content");
	});
</script>
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
			var conditionId = document.getElementById('conditionId').value;
			var vacationtype =  document.getElementById('vacationtype').value;
			var starttime = document.getElementById('starttime').value;
			var endtime = document.getElementById('endtime').value;
			
			var title = document.getElementById('title').value;
			if(vacationtype==''||vacationtype==null){
				Dialog.alert('请选择休假类型！');
				return;
			}
			if(starttime==''||starttime==null){
				Dialog.alert('外出时间不能为空！');
				return;
			}
			if(endtime==''||endtime==null){
				Dialog.alert('返回时间不能为空！');
				return;
			}
			
			if(title==''||title==null){
				Dialog.alert('外出简述不能为空！');
				return;
			}else if(title.length>50){
				Dialog.alert('外出简述长度不能超过50个字符！');
				return;
			}
			document.getElementById('workingConditionForm').action='update.action?conditionId_q='+conditionId;
			document.getElementById('workingConditionForm').submit();
		}
		
	</script>
</head>
<body >
       <div  style="width:99%；margin:0 auto; background-color:#fff;">
       <s:form id="workingConditionForm" action="workingConditionAction" method="post">
       <s:hidden name="flag" ></s:hidden>
        <s:hidden name="conditionId" id="conditionId"></s:hidden>
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改休假信息</span></td>
          </tr> 
          <tr> 
             <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF" >
            	<select id="workflowId" name="workflowId" style="width:155px">
					<option value="GOOUT">休假管理</option>
				</select>
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申 请 人： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:property value="name"/>
            </td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">休假类型： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<select id="vacationtype" name="vacationtype" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="vacationList" >
						<option value="<s:property value='dataId'/>" <s:if test="dataId==vacationtype">selected="true"</s:if>><s:property value='dataname'/></option>
					</s:iterator>
				</select>
            </td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">起始时间： </td>
            <td height="20" bgcolor="#FFFFFF">
            <input name="starttime" class="Wdate" id="starttime" type="text" value="<s:date name='starttime' format='yyyy-MM-dd'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" />
            </td>
          </tr> 
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">终止时间： </td>
            <td height="20" bgcolor="#FFFFFF">
            <input name="endtime" class="Wdate" id="endtime" type="text" value="<s:date name='endtime' format='yyyy-MM-dd'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" />
            </td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">休假概述： </td>
            <td height="20" bgcolor="#FFFFFF"><input id="title" name="title" type="text" value="<s:property value='title'/>" size="30" /></td>
          </tr> 
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">休假内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF"><textarea id="content" name="content" style="width: 100%;height: 300px"><s:property value='content'/></textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" class="buttons" name="save" value="下一步"  onclick="doCheck();"/>
                <input type="reset" class="buttons" name="cancle" value="重置" />
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