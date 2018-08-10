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
			var starttime = $("#stime").val();
			var endtime = $("#etime").val();
			if(starttime > endtime){
				Dialog.alert("请选择正确的起始日期和终止日期！");
				return;
			}
			document.getElementById('attendCountForm').action=id+".action";
			document.getElementById('attendCountForm').submit();
		}

	</script>
	</head>
   <body >
           <s:form action="attendAction" id="attendCountForm" theme="simple"  method="post" >
	       <s:hidden name="currentPage" id="currentPage"></s:hidden>
	       <s:hidden name="flag" id="flag"></s:hidden>    
				<div class="tagContent selectTag" id="tagContent0" style=" margin:0 auto; background-color:#fff;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">姓名：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="name" type="text"  value="<s:property value='name'/>" /></td>
						<td height="25" width="15%" align="right" style="border:0px">所属部门：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="dept" name="dept" style="width:155px">
								<option value="">--请选择--</option>
								<s:iterator value="depts" >
									<option value="<s:property value='deptId'/>" <s:if test="deptId==dept">selected="true"</s:if>><s:property value='deptname'/></option>
								</s:iterator>
							</select>
						</td>
						<td height="25" width="40%" align="center" style="border:0px" ></td>
					</tr>
					<tr>
						<td height="25"  align="right" style="border:0px">起始日期：</td>
						<td height="25"  align="left" style="border:0px"><input name="stime" id="stime" class="Wdate" type="text"  value="<s:property value='stime'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'etime\')||\'2020-01-01\'}'})"/></td>
						<td height="25"  align="right" style="border:0px">终止日期：</td>
						<td height="25" align="left" style="border:0px"><input name="etime" id="etime" class="Wdate" type="text"  value="<s:property value='etime'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'stime\')}',maxDate:'2020-10-01'})" /></td>
						<td height="25" align="center" style="border:0px" ></td>
					  </tr>
					  <tr>
						<td align="right" width="15%">
							
						</td>
						<td align="left" colspan="4">
								<a href="#" onclick="doObject('query')">
									<img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0"/>
									</a>
						</td>
					  </tr> 
					</table>
						<table id="xinxiu_table"  width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							  <thead height="33px">
								<tr> 
								  <th width="10%">姓名</th>
								  <th width="10%">所属部门</th>
								  <th width="10%">迟到次数</th>
								  <th width="10%">早退次数</th>
								  <th width="10%">休假次数</th>
								  <th width="10%">外出次数</th>
								  <th width="10%">出差次数</th>
								  <th width="10%">旷工次数</th>
								 
								</tr>
							  </thead>
							   <tbody>
							  <s:iterator value="attendanceList">
								<tr height="32px">										 
								  <td><s:property value="NAME"/></td>
								  <td><s:property value="DEPT"/></th>
								  <td><s:property value="LATESTATUS"/></td>
								  <td><s:property value="LEAVESTATUS"/></td>
								  <td><s:property value="VACATIONSTATUS"/></td>
								  <td><s:property value="GOOUTSTATUS"/></td>
								  <td><s:property value="BIZTRIPSTATUS"/></th>
								  <td><s:property value="ABSENTSTATUS"/></td>
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