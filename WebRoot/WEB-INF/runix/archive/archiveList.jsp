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
			document.getElementById('archiveListForm').action=id+".action";
			document.getElementById('archiveListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('archiveListForm').action="updateInit.action?archiveId_q="+id;
			document.getElementById('archiveListForm').submit();
		}
		
		function doview(id){
			document.getElementById('archiveListForm').action="view.action?archiveId_q="+id;
			document.getElementById('archiveListForm').submit();
		}
		
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('archiveListForm').action="delete.action?archiveId_q="+id;
			document.getElementById('archiveListForm').submit();
			});
		}


	</script>
	</head>
   <body >
           <s:form action="archiveAction" id="archiveListForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
				<div id="tagContent" class="tagContent selectTag" style="margin:0 auto; background-color:#fff;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
									<td>
									<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
									  <tr>
										<td height="25" width="15%" align="right" style="border:0px">文件标题：</td>
										<td height="25" width="15%" align="left" style="border:0px"><input name="title" id="title" type="text"   /></td>
									  	<td height="25" width="15%" align="right" style="border:0px">文件类型：</td>
										<td height="25" width="15%" align="left" style="border:0px">
											<select id="filetype" name="filetype" style="width:155px">
												<option value="">--请选择--</option>
												<option value="1">收文</option>
												<option value="2">发文</option>
												<option value="3">直接归档</option>
											</select>
										</td>
										<td height="25" width="40%" align="right" style="border:0px"></td>
									  </tr>
									  <tr>
										<td align="right"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else></div></td>
										<td align="left" colspan="8"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
									</tr>   
									  
									</table>
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="10%" >文件类型</th>
												  <th width="15%">标题</th>
												  <th width="10%">责任者</th>
												  <th width="15%">所属档案室</th>
												  <th width="14%">所属案卷</th>
												  <th width="10%">文档年度</th>
												  <th width="10%">归档日期</th>
												<th width="16%">操作</th>													  
												</tr>
											  </thead>
											  <tbody>
											  <s:iterator value="archiveList">
												<tr height="32px">										 
												 <td ><s:if test='FILETYPE=="1"'>收文</s:if><s:elseif test='FILETYPE=="2"'>发文</s:elseif><s:elseif test='FILETYPE=="3"'>直接归档</s:elseif></td>
												  <td ><s:property value="TITLE"/></td>
												  <td ><s:property value="AUTHOR"/></td>
												  <td ><s:property value="ARCHIVEROOM"/></td>
												  <td ><s:property value="DOSSIERID"/></td>
												  <td ><s:property value="ANNUAL"/></td>
												  <td ><s:date name="FILLINGDATE" format="yyyy-MM-dd"/></td>
												  <td >
												  <s:if test="readonly==true">
												  <a  href="#" onclick="doview('<s:property value='ARCHIVE_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
												  </s:if><s:else>
													<a href="#" onclick="doupdate('<s:property value='ARCHIVE_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
													<a  href="#" onclick="doview('<s:property value='ARCHIVE_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
													 <s:if test='FILLINGSTATUS=="0"'><a  href="#" onclick="dodelete('<s:property value='ARCHIVE_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a></s:if>
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