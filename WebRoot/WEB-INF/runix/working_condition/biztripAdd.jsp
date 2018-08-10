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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
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
		
		
		function doCheck(){
			editer.sync();
			var starttime = document.getElementById('starttime').value;
			var biztriplimit = document.getElementById('biztriplimit').value;
			var destination = document.getElementById('destination').value;
			var traffic = document.getElementById('traffic').value;
			var title = document.getElementById('title').value;
			var reg = /^[1-9]+$/;
			if(starttime==''||starttime==null){
				Dialog.alert('出差时间不能为空！');
				return;
			}
			if(biztriplimit==''||biztriplimit==null){
				Dialog.alert('出差天数不能为空！');
				return;
			}else if(!reg.test(biztriplimit)){
				Dialog.alert('出差天数只能输入正整数！');
				return;
			}
			if(traffic==''||traffic==null){
				Dialog.alert('请选择交通工具！');
				return;
			}
			if(destination==''||destination==null){
				Dialog.alert('目的地不能为空！');
				return;
			}else if(destination.length>50){
				Dialog.alert('目的地长度不能超过50个字符！');
				return;
			}
			if(title==''||title==null){
				Dialog.alert('出差概述不能为空！');
				return;
			}else if(title.length>50){
				Dialog.alert('出差概述长度不能超过50个字符！');
				return;
			}
			document.getElementById('workingConditionForm').action='insert.action';
			document.getElementById('workingConditionForm').submit();
		}
		
		function selectPeople(){
	//		 var date = new Date();
			  var otherpeople = document.getElementById('otherpeople').value;

/*			 var str=window.showModalDialog("../selectPeople/query.action?random="+date.getTime()+"&people="+otherpeople,"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			   if(str!='' && str!=null){
				   var pple = str.split("%");
					$('#otherpeople').val(pple[0]);
			   }
	*/		   
				 var date = new Date();
				   var diag = new Dialog();
					diag.Width  = 700;
					diag.Height = 300;
					diag.Top=40;
					diag.Title = "选择同行人员";
					diag.URL = "<%=request.getContextPath() %>/selectPeople/query.action?random="+date.getTime()+"&people="+otherpeople;
					diag.OKEvent = function(){
						var str=diag.innerFrame.contentWindow.doSelect();
						 if(str!='' && str!=null){
							   var pple = str.split("%");
								$('#otherpeople').val(pple[0]);
						   }
						diag.close();
					};	
					diag.show();
		}
	</script>
</head>
<body >
       <div  style="width:99%；margin:0 auto; background-color:#fff;">
       <s:form id="workingConditionForm" action="workingConditionAction" method="post">
       <s:hidden name="flag" ></s:hidden>
        <s:hidden name="otherpeopleId" id="otherpeopleId" ></s:hidden>
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">新建出差信息</span></td>
          </tr> 
          <tr>  
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<select id="workflowId" name="workflowId" style="width:155px">
					<option value="BIZTRIP">出差管理</option>
				</select>
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">出 差 者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:property value="name"/>
            </td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">同行人员： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="otherpeople" id="otherpeople" type="text" size="30" onclick="selectPeople();"/></td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">出差时间： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="starttime" class="Wdate" id="starttime" type="text" value="<s:date name='starttime' format='yyyy-MM-dd'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" /></td>
          </tr> 
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">出差天数： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="biztriplimit" id="biztriplimit" type="text" /></td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">交通工具： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<select id="traffic" name="traffic" style="width:155px">
            	<option value="">--请选择--</option>
				<s:iterator value="trafficList" >
						<option value="<s:property value='dataId'/>" <s:if test="dataId==traffic">selected="true"</s:if>><s:property value='dataname'/></option>
					</s:iterator>
				</select></td>
          </tr> 
		   <tr>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">目 的 地： </td>
            <td height="20" bgcolor="#FFFFFF"><input id="destination" name="destination" type="text" size="30" /></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">出差概述： </td>
            <td height="20" bgcolor="#FFFFFF" ><input id="title" name="title" type="text" size="30" /></td>
          </tr> 
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">出差内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF">
            	<textarea id="content" name="content" style="width: 100%;height: 300px"></textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="下一步" class="buttons"  onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置" class="buttons" />
				<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
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