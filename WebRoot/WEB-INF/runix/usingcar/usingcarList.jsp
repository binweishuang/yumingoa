<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<script type="text/javascript" src="scripts/jquery.min.js"></script>    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css">
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		function doObject(id){
			document.getElementById('usingcarForm').action=id+".action";
			document.getElementById('usingcarForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('usingcarForm').action="updateInit.action?usingcarId_q="+id;
			document.getElementById('usingcarForm').submit();
		}
		function doview(id){
			document.getElementById('usingcarForm').action="view.action?usingcarId_q="+id;
			document.getElementById('usingcarForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('usingcarForm').action="delete.action?usingcarId_q="+id;
			document.getElementById('usingcarForm').submit();
			});
		}

	</script>
	</head>
<body >
      	<div class="tagContent" id="tagContent1" style="margin:0 auto; background-color:#fff;">
				<s:form  id="usingcarForm" theme="simple"  method="post" target="_self">
				<s:hidden name="currentPage" id="currentPage"></s:hidden> 
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
				<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0"  bordercolor="#f8de91" style="border-collapse:collapse;" >
				  <tr>

					<td height="25" width="15%" align="right" style="border:0px">车辆牌照：</td>
					<td height="25" width="15%" align="left" style="border:0px"><input name="carId" type="text" value="<s:property value='carId'/>"/></td>
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
					<td height="25" width="10%"  style="border:0px"></td>
				  </tr>
				  <tr>
					<td align="right" width="15%"><a href="#"   onclick="doObject('insertInit')"><img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"/></a></td>
					<td align="left" colspan="6"><a href="#"   onclick="doObject('query')"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" /></a></td>
				  </tr>  
				</table>
				<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0"  bordercolor="#f8de91" style="border-collapse:collapse;">
					   <thead style="height: 33px;">
						<tr>
						  <th width="9%" >使用者</th>
						  <th width="10%">用车部门</th>
						  <th width="10%">联系电话</th>		
						  <th width="9%">用车时间</th>
						  <th width="9%">返回时间</th>
						  <th width="10%">申请车辆</th>
						  <th width="9%">用车性质</th>	
						  <th width="9%">提交状态</th>
						  <th width="9%">审核状态</th>											
						  <th width="16%">操作</th>													  
						</tr>
					  </thead>
					  <tbody>
					  <s:iterator value="usingcarList">
						<tr style="height: 33px;">										 
						  <td ><s:property value="userId"/></td>
						  <td ><s:property value="dept"/></td>
						  <td ><s:property value="tel"/></td>
						  <td ><s:date name="usingtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						  <td ><s:date name="backtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						  <td ><s:property value="carId"/></td>
						  <td ><s:if test='usingproperty=="1"'>公用</s:if><s:else>私用</s:else></td>
						  <td ><s:if test='status=="1"'>已提交</s:if><s:else>未提交</s:else></td>
						  <td ><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>
						  <td >
							<s:if test='status=="0"'><a href="#" onclick="doupdate('<s:property value='usingcarId'/>')"><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;"/>修改</a></s:if>
							<s:if test='status=="1"'><a  href="#" onclick="doview('<s:property value='usingcarId'/>')"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0"  style="vertical-align:middle;"/>查看</a></s:if>
							<a  href="#" onclick="dodelete('<s:property value='usingcarId'/>')"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0"  style="vertical-align:middle;"/>删除</a>
						  </td>													
						</tr>
						</s:iterator>
						 <tr style="height: 33px;">
							<td height="20" colspan="10" align="right">
								 <!--分页开始-->
	                                <s:include value="/framework/include/page.jsp"/>
	                             <!--分页结束-->
							</td>
						</tr>
					  </tbody>
				</table></td></tr></table>
				</s:form>
			</div>
</body>
</html>