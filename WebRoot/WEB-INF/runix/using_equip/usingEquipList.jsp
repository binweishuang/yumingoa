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
			document.getElementById('usingEquipListForm').action=id+".action";
			document.getElementById('usingEquipListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('usingEquipListForm').action="updateInit.action?usingequipId_q="+id;
			document.getElementById('usingEquipListForm').submit();
		}
		
		function doview(id){
			document.getElementById('usingEquipListForm').action="view.action?usingequipId_q="+id;
			document.getElementById('usingEquipListForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('usingEquipListForm').action="delete.action?usingequipId_q="+id;
			document.getElementById('usingEquipListForm').submit();
			});
		}
	</script>
	</head>
   <body >
           <s:form action="usingEquipAction" id="usingEquipListForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" value="flag"></s:hidden>    
				<div class="tagContent selectTag" id="tagContent0" style=" margin:0 auto; background-color:#fff;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
									<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
									  <tr>
										<td height="25" width="15%" align="right" style="border:0px">使用方式：</td>
										<td height="25" width="15%" align="left" style="border:0px">
											<select id="usecondition" name="usecondition" style="width:155px">
												<option value="">--请选择--</option>
												<option value="1">领用</option>
												<option value="2">借用</option>
											</select>
										</td>
										<td height="25" width="15%" align="right" style="border:0px">物品类别：</td>
										<td height="25" width="15%" align="left" style="border:0px">
											<select id="cateogory" name="cateogory" style="width:155px" onchange="changeCategory();">
												<option value="" >--请选择--</option>
												<s:iterator value="categoryList" >
											     <option value="<s:property value='dataId'/>"><s:property value='dataname'/></option>
											    </s:iterator>
											</select>
										</td>
										<td height="25" width="40%" align="right" style="border:0px"></td>
										
									  </tr>
									 <tr>
									 <td height="25" width="15%" align="right" style="border:0px">提交状态：</td>
										<td height="25" width="15%" align="left" style="border:0px">
											<select id="status" name="status" style="width:155px">
													<option value="">--请选择--</option>
													<option value="1" <s:if test='status=="1"'>selected="true"</s:if>>已提交</option>
													<option value="0" <s:if test='status=="0"'>selected="true"</s:if>>未提交</option>
												</select>
										</td>
										<td height="25" width="15%" align="right" style="border:0px">审核状态：</td>
										<td height="25" width="15%" align="left" style="border:0px">
											<select id="checkstatus" name="checkstatus" style="width:155px">
													<option value="">--请选择--</option>
													<option value="1" <s:if test='checkstatus=="1"'>selected="true"</s:if>>已审核</option>
													<option value="2" <s:if test='checkstatus=="2"'>selected="true"</s:if>>审核中</option>
													<option value="3" <s:if test='checkstatus=="3"'>selected="true"</s:if>>未审核</option>
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
												  <th width="10%">领用人</th>
												  <th width="10%">领用部门</th>
												  <th width="10%">物品类别</th>
												  <th width="15%">物品名称</th>
												  <th width="7%">领用数量</th>
												  <th width="9%">申请时间</th>
												  <th width="8%">使用方式</th>
												  <th width="8%">提交状态</th>		
												  <th width="8%">审核状态</th>		
												  <th width="17%">操作</th>													  
												</tr>
											  </thead>
											  <tbody>
											  <s:iterator value="usingEquipList">
												<tr height="32px">										 
												  <td ><s:property value="APPLICANT"/></td>
												  <td ><s:property value="DEPT"/></td>
												  <td ><s:property value="CATEOGORY"/></td>
												  <td ><s:property value="GOODSNAME"/></td>
												  <td ><s:property value="APPLYNUM"/></td>
												  <td ><s:date name="APPLYTIME" format="yyyy-MM-dd"/></td>
												  <td ><s:if test='USECONDITION=="1"'>领用</s:if><s:if test='USECONDITION=="2"'>借用</s:if></td>
												   <td ><s:if test='STATUS=="1"'>已提交</s:if><s:if test='STATUS=="0"'>未提交</s:if></td>
												    <td ><s:if test='CHECKSTATUS=="1"'>已审核</s:if><s:elseif test='CHECKSTATUS=="2"'>审核中</s:elseif><s:elseif test='CHECKSTATUS=="3"'>未审核</s:elseif></td>
												  <td >
													 <s:if test='STATUS=="0"'><a href="#" onclick="doupdate('<s:property value='USINGEQUIP_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
													<s:if test='STATUS=="1"'><a  href="#" onclick="doview('<s:property value='USINGEQUIP_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
													<a  href="#" onclick="dodelete('<s:property value='USINGEQUIP_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
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