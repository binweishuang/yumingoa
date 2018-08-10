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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"> 
		<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('dossierListForm').action=id+".action";
			document.getElementById('dossierListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('dossierListForm').action="updateInit.action?dossierId_q="+id;
			document.getElementById('dossierListForm').submit();
		}
		
		function doview(id){
			document.getElementById('dossierListForm').action="view.action?dossierId_q="+id;
			document.getElementById('dossierListForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('dossierListForm').action="delete.action?dossierId_q="+id;
			document.getElementById('dossierListForm').submit();
			});
		}

	//	function dodelete(id){
	//		parent.conferm(id,"2");
	//	}
	</script>
	</head>
   <body >
           <s:form action="dossierAction" id="dossierListForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
				<div class="tagContent" id="tagContent1" style="margin:0 auto; background-color:#fff;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
										<table id="chaxun_table"  width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
										  <tr>
											<td height="25" width="15%" align="right" style="border:0px">案卷状态：</td>
											<td height="25" width="15%" align="left" style="border:0px">
												<select id="status" name="status" style="width:155px">
													<option value="">--请选择--</option>
													<option value="1" <s:if test='status=="1"'>selected="true"</s:if>>拆卷</option>
													<option value="2" <s:if test='status=="2"'>selected="true"</s:if>>封卷</option>
												</select>
											</td>
											<td height="25" width="15%" align="right" style="border:0px">年度：</td>
											<td height="25" width="15%" align="left" style="border:0px"><input name="annual" id="annual" type="text" value="<s:property value='annual'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy',vel:'smonth',realDateFmt:'yyyy'})" class="Wdate"/></td>										
											<td height="25" width="40%" align="right" style="border:0px"></td>
										  </tr>	
											<tr>
												<td align="right"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else></div></td>
												<td align="left" colspan="4"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
											</tr>   										  
										   
										</table>
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="10%" >案卷状态</th>
												  <th width="10%">责任者</th>
												  <th width="12%">所属类目</th>	
												  <th width="15%">案卷标题</th>
													<th width="13%">档案室</th>	
													<th width="12%">盒号</th>	
													<th width="10%">年度</th>
												  <th width="17%">操作</th>													  
												</tr>
											  </thead>
											  <tbody>
											  <s:iterator value="dossierList">
												<tr height="32px">										 
												 <td ><s:if test='STATUS=="1"'>拆卷</s:if><s:elseif test='STATUS=="2"'>封卷</s:elseif></td>
												  <td ><s:property value='AUTHOR'/></td>
												  <td ><s:if test='CATEGORY=="1"'>一般档案</s:if><s:elseif test='CATEGORY=="2"'>重要档案</s:elseif></td>
												  <td ><s:property value='TITLLE'/></td>
												  <td ><s:property value='DOCROOM'/></td>
												  <td ><s:property value='BOXNUM'/></td>
												  <td ><s:property value='ANNUAL'/></td>
												  <td >
												  <s:if test="readonly==true">
												  <a  href="#" onclick="doview('<s:property value='DOSSIER_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
												  </s:if><s:else>
													 <a href="#" onclick="doupdate('<s:property value='DOSSIER_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
													<a  href="#" onclick="doview('<s:property value='DOSSIER_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
													<a  href="#" onclick="dodelete('<s:property value='DOSSIER_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
												</s:else>
												 </td>
											</tr>
											</s:iterator>
										 <tr height="32px">
											<td height="20" colspan="8" align="right">
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