<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>修改调查项</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		function doCheck(){
			var title = $("#title").val();
			var surveypersonids = $("#surveypersonids").val();
			var starttime = $("#starttime").val();
			var endtime = $("#endtime").val();
			var content = document.getElementsByName('content');
			if(title==''||title==null){
				Dialog.alert('投票主题不能为空！');
				return ;
			}else if(title.length>50){
				Dialog.alert('投票主题不能超过50个字符！');
				return ;
			}
			if(surveypersonids==''||surveypersonids==null){
				Dialog.alert('请选择调查对象！');
				return ;
			}
			if(starttime==''||starttime==null){
				Dialog.alert('开始时间不能为空！');
				return ;
			}
			if(endtime==''||endtime==null){
				Dialog.alert('终止时间不能为空！');
				return ;
			}
			for(var i=0;i<content.length;i++){
				if(content[i].value==''||content[i].value==null){
					Dialog.alert('选项不能为空！');
					return ;
				}
			}
			var surveyId = document.getElementById('surveyId').value;
			document.getElementById('surveyEditForm').action="update.action?surveyId_q="+surveyId;
			document.getElementById('surveyEditForm').submit();
		}
		
		//选择调查对象
		function selectPerson(){
/*		   var date = new Date();
		   var str=window.showModalDialog("../selectPeople/query.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
		   var pple = str.split("%");
		   $('#surveypersonnames').val(pple[0]);
		   $('#surveypersonids').val(pple[1]);
*/
		   var date = new Date();
			var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择调查对象";
			diag.URL = "<%=request.getContextPath() %>/selectPeople/query.action?random="+date.getTime();
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				if(str != "" && str != null){
					var arr = str.split("%");
					 $('#surveypersonnames').val(arr[0]);
					 $('#surveypersonids').val(arr[1]);
				}
				diag.close();
			};	
			diag.show();
		}
		
		var i = '3';
		//添加选项
		function addline() {
			var str = "<tr id='answer"+i+"'><td ><input name='ck' id='ck"+i+"' type='checkbox'/></td><td  style='width:88%;text-align:left;' ><input name='content' id='content"+i+"' type='text' size='60' /></td></tr>";
			$('#xinxiu_table').append(str);
			i++;
		}
		
		//删除选项
		function delline() {
			var flag = false;
			$('input[name="ck"]:checked').each(function() {
				$(this).parent().parent().remove();
				flag = true;
			});
			if (!flag) {
				Dialog.alert('请先选择要删除的选项！');
			}
		}
	</script>
</head>
<body >
	<form action="surveyEditAction" id="surveyEditForm" method="post" >
	<s:hidden  name="surveyId" id="surveyId"></s:hidden>
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
        		<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网上调查</span>&gt;&gt; <span class="STYLE1">修改调查项</span></td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改调查项</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">投票主题： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
							<input type="text" name="title" id="title" size="100" value="<s:property value='title'/>"/>
							
						</td>
					</tr>
					<tr>
			            <td width="12%" height="60" align="right" bgcolor="#f8f8f8">调查对象： </td>
			             <td width="38%" height="60" bgcolor="#FFFFFF" colspan="3">
			            	<textarea id="surveypersonnames" name="peoplesname" style="width:80%;height:100%" onclick="selectPerson();"><s:property value="peoplesname"/></textarea>	
			            	<s:hidden name="surveyperson" id="surveypersonids" ></s:hidden>
			            </td>
			          </tr>
			          <tr>
			            <td height="20" width="12%" align="right" bgcolor="#f8f8f8">开始时间： </td>
			            <td height="20" width="38%" bgcolor="#FFFFFF">
							<input type="text" name="starttime" id="starttime" value="<s:date name='starttime' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})" class="Wdate"/>
						</td>
			            <td height="20" width="12%" align="right" bgcolor="#f8f8f8">终止时间： </td>
			            <td height="20" width="38%" bgcolor="#FFFFFF">
							<input type="text" name="endtime" id="endtime" value="<s:date name='endtime' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})" class="Wdate"/>
						</td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">是否公开： </td>
			            <td height="20" bgcolor="#FFFFFF">
				             <input type="radio" name="publicity" value="1" <s:if test='publicity=="1"'>checked="checked"</s:if>/> 公开
				            <input type="radio" name="publicity" value="0" <s:if test='publicity=="0"'>checked="checked"</s:if>/> 不公开
			            </td>
			            <td height="20" align="right" bgcolor="#f8f8f8">投票方式：</td>
			            <td height="20" bgcolor="#FFFFFF">
							<input type="radio" name="votingway" value="1" <s:if test='votingway=="1"'>checked="checked"</s:if>/> 记名
				            <input type="radio" name="votingway" value="0" <s:if test='votingway=="0"'>checked="checked"</s:if>/> 不记名
						</td>
			          </tr> 
					  <tr >										 
						<td height="20px" align="right" bgcolor="#f8f8f8">题    型：</td>
						<td height="20" bgcolor="#FFFFFF">
							<input type="radio" name="questiontype" value="0"  <s:if test='questiontype=="0"'>checked="checked"</s:if>/>单选
						    <input type="radio" name="questiontype" value="1"  <s:if test='questiontype=="1"'>checked="checked"</s:if>/>多选
						</td>
						<td height="20px" align="right" bgcolor="#f8f8f8">状    态：</td>
						<td height="20" bgcolor="#FFFFFF">
							<input type="radio" name="status" value="1"  <s:if test='status=="1"'>checked="checked"</s:if>/> 启用
						    <input type="radio" name="status" value="0" <s:if test='status=="0"'>checked="checked"</s:if>/> 禁用
						</td>
					  </tr>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="margin-top:4px;border-collapse:collapse;">			  
					  <thead>
						  <tr>
							<th align="center" style="width:12%">选  项</th>
							<td align="left" style="width:88%">
								<input name="submit1" type="button" class="newCreate" value="添加行" onclick="addline();" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="submit1" type="button" class="newCreate" value="删除行" onclick="delline();" />
							</td>
						  </tr> 
					  </thead> 
					</table>
					<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						<s:iterator value="answerList" status="st">
						  <tr height="32px">										 
						  <td align="right" style="width:12%"><s:if test="#st.index>1"><input name='ck' id='ck<s:property value='#st.index'/>' type='checkbox'/></s:if></td>
						  <td style="width:88%;text-align:left"><input name="content" id="content<s:property value='#st.index+1'/>" type="text" size="60" value="<s:property value='content'/>"/></td>
						</tr>
						</s:iterator>			
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
		                <input type="button" class="buttons" name="save" value="保存"  onclick="return doCheck();"/>
		                <input type="reset" class="buttons" name="cancle" value="重置" />
						<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
		            </div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>
