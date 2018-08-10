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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"> 
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('resumeForm').action=id+".action";
			document.getElementById('resumeForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('resumeForm').action="updateInit.action?resumeId_q="+id;
			document.getElementById('resumeForm').submit();
		}
		function doview(id){
			document.getElementById('resumeForm').action="view.action?resumeId_q="+id;
			document.getElementById('resumeForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('resumeForm').action="delete.action?resumeId_q="+id;
			document.getElementById('resumeForm').submit();
			});
		}
		
		function doInterview(id){
	//		var str=window.showModalDialog("../resume/doInterviewInit.action?resumeId_q="+id,"","dialogWidth=400px;dialogHeight=240px;dialogLeft=500px;dialogTop=280px;");
			 var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 400;
				diag.Height = 140;
				diag.Top=70;
				diag.Title = "预约面试";
				diag.URL = "<%=request.getContextPath() %>/resume/doInterviewInit.action?resumeId_q="+id;
				diag.OKEvent = function(){
					var str = diag.innerFrame.contentWindow.doCheck();
					if(str != "" && str != null){
						var arr = str.split("%");
						var resumeId =arr[0];
						var appdate = arr[1];
				
					$.ajax({
						type:"POST",
						url:"<%=request.getContextPath()%>/resume/doInterview.action",
						dataType:"json",
						data:"resumeId_q="+resumeId+"&appdate="+appdate,
						async:false,
						success:function(result){
							if(result){
								Dialog.alert('预约成功！');
							}else{
								Dialog.alert('预约失败！');
							}
						}
					});
					}
					diag.close();
				};	
				diag.show();
		}
	</script>
	</head>
<body >
	<s:form action="resumeAction" id="resumeForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" value="flag"></s:hidden>
	<div class="tagContent" id="tagContent1" style="margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
			<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
			  <tr>
				<td height="25" width="15%" align="right" style="border:0px">应聘岗位：</td>
				<td height="25" width="15%" align="left" style="border:0px">
					<select id="position" name="position" style="width:155px">
						<option value="">--请选择--</option>
						<s:iterator value="positionList">
						<option value="<s:property value='postname'/>" <s:if test="postname==position">selected="true"</s:if>><s:property value='postname'/></option>
					</s:iterator>
				</select>
				</td>
				<td height="25" width="15%" align="right" style="border:0px">面试状态：</td>
				<td height="25" width="15%" align="left" style="border:0px">
					<select id="interview" name="interview" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='interview=="1"'>selected="true"</s:if>>是</option>
					<option value="0" <s:if test='interview=="0"'>selected="true"</s:if>>否</option>
				</select>
				</td>
				<td height="25" width="40%" align="right" style="border:0px"></td>
			  </tr>
			  <tr>
				<td align="right"><div style="padding-top:4px"><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
				<td align="left" colspan="4"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
			</tr>  
			  
			</table>
			<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				  <thead height="33px">
					<tr>
					  <th width="10%" >应聘者</th>
					  <th width="10%">性别</th>
					  <th width="10%">年龄</th>		
						<th width="15%">联系电话</th>
						<th width="12%">应聘岗位</th>
						<th width="8%">是否录用</th>
						<th width="8%">面试状态</th>													
					  <th width="15%">操作</th>													  
					</tr>
				  </thead>
				  <tbody>
				  <s:iterator value="resumeList">
					<tr height="32px">										 
					  <td ><a href="#" onclick="doInterview('<s:property value='resumeId'/>');" title="预约面试" style="color:#49AFCB;"><s:property value='name'/></a></td>
					  <td ><s:property value='sex'/></td>
					  <td ><s:property value='age'/></td>
					  <td ><s:property value='tel'/></td>
					  <td ><s:property value='position'/></td>
					  <td ><s:if test='hire=="1"'>已录用</s:if><s:elseif test='hire=="0"'>未录用</s:elseif></td>
					  <td ><s:if test='interview=="1"'>已面试</s:if><s:elseif test='interview=="0"'>未面试</s:elseif></td>
					<td >
					 <a href="#" onclick="doupdate('<s:property value='resumeId'/>')" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
					<a  href="#" onclick="doview('<s:property value='resumeId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
					<a  href="#" onclick="dodelete('<s:property value='resumeId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
					 </td>	
					</tr>
				</s:iterator>
					 <tr height="32px">
						<td height="20" colspan="9" align="right">
							<!--分页开始-->
							<s:include value="/framework/include/page.jsp"/>
							<!--分页结束-->
						</td>
					</tr>
				  </tbody>
			</table></td></tr></table>	
		</div>
	</s:form>
</body>
</html>