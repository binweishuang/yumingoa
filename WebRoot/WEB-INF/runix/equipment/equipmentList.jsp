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
			document.getElementById('equipmentForm').action=id+".action";
			document.getElementById('equipmentForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('equipmentForm').action="updateInit.action?equipmentId_q="+id;
			document.getElementById('equipmentForm').submit();
		}
		
		function doview(id){
			document.getElementById('equipmentForm').action="view.action?equipmentId_q="+id;
			document.getElementById('equipmentForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('equipmentForm').action="delete.action?equipmentId_q="+id;
			document.getElementById('equipmentForm').submit();
			});
		}
	</script>
	</head>
   <body >
           <s:form action="equipmentAction" id="equipmentForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
<div class="tagContent" id="tagContent1" style="margin:0 auto; background-color:#fff;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
										<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
										  <tr>
											<td height="25" width="12%" align="right" style="border:0px">存放仓库：</td>
											<td height="25" width="12%" align="left" style="border:0px">
												<select id="storage" name="storage" style="width:155px">
													<option value="">--请选择--</option>
													<s:iterator value="storageList" >
													 <option value="<s:property value='dataId'/>" <s:if test="dataId==storage">selected="true"</s:if>><s:property value='dataname'/></option>
													</s:iterator>
												</select>
											</td>
											<td height="25" width="12%" align="right" style="border:0px">用品类别：</td>
											<td height="25" width="12%" align="left" style="border:0px">
												<select id="cateogory" name="cateogory" style="width:155px">
													<option value="">--请选择--</option>
													<s:iterator value="categoryList" >
												     <option value="<s:property value='dataId'/>" <s:if test="dataId==cateogory">selected="true"</s:if>><s:property value='dataname'/></option>
												    </s:iterator>
												</select>
											</td>
											<td height="25" width="12%" align="right" style="border:0px">用品名称：</td>
											<td height="25" width="12%" align="left" style="border:0px"><input name="goodsname" id="goodsname" type="text" value="<s:property value='goodsname'/>" size="20"/></td>
											<td height="25" width="28%" align="right" style="border:0px"></td>
										  </tr>
										  <tr>
											<td align="right"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="#" onclick="doObject('insertInit');" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else></div></td>
											<td align="left" colspan="6"><div style="padding-top:4px"><a href="#"  onclick="doObject('query');" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
										</tr>   
										   
										</table>
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="10%" >用品类型</th>
												  <th width="10%">用品名称</th>
												  <th width="10%">存放仓库</th>		
													<th width="8%">总数量</th>
													<th width="10%">单价</th>
													<th width="10%">总价</th>
													<th width="8%">购置人</th>
													<th width="8%">登记人</th>	
													<th width="10%">登记时间</th>													
												  <th width="16%">操作</th>													  
												</tr>
											  </thead>
											  <tbody>
											  <s:iterator value="equipmentList">
												<tr height="32px">										 
												  <td ><s:property value="CATEOGORY"/></td>
												  <td ><s:property value="GOODSNAME"/></td>
												  <td ><s:property value="STORAGE"/></td>
												  <td ><s:property value="GOODSNUM"/></td>
												  <td ><s:property value="UNITPRICE"/></td>
												  <td ><s:property value="TOTALPRICE"/></td>
												  <td ><s:property value="BUIER"/></td>
												  <td ><s:property value="REGISTRANTNAME"/></td>
													<td ><s:date name="REGISTTIME" format="yyyy-MM-dd"/></td>
													<td >
													<s:if test="userid==REGISTRANT"><a href="#" onclick="doupdate('<s:property value='EQUIPMENT_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
													<a  href="#" onclick="doview('<s:property value='EQUIPMENT_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
													<s:if test="userid==REGISTRANT"><a  href="#" onclick="dodelete('<s:property value='EQUIPMENT_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a></s:if>
													 </td>													
												  
												</tr>
											  </s:iterator>
										 <tr height="32px">
											<td height="20" colspan="10" align="right">
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