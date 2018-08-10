<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			var resumeId = document.getElementById('resumeId').value;
			var appdate = document.getElementById('appdate').value;
	//		document.getElementById('resumeAddForm').action="doInterview.action?resumeId_q="+resumeId;
	//		document.getElementById('resumeAddForm').submit();
	//		window.close();
	   return resumeId+"%"+appdate;
	}


</script>
</head>
<body >
	<s:form action="resumeAddAction" id="resumeAddForm" theme="simple"  method="post">
	<s:hidden name="resumeId" id="resumeId"></s:hidden>
         <div  style="width:99%; padding-top:50px; padding-bottom:33px;margin:0 auto; background-color:#fff;">
     		  
					<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
					  <tr>
						<td width="25%" height="20" align="right" bgcolor="#f8f8f8">应聘者： </td>
						<td width="75%" height="20" bgcolor="#FFFFFF" style="border:0px;"><s:property value="name"/></td></td>
					  </tr>
					  <tr>						
					   <td  height="20" align="right" bgcolor="#f8f8f8">预约时间： </td>
						<td  height="20" bgcolor="#FFFFFF">
							<input name="appdate" id="appdate" type="text" value="<s:date name='appdate' format='yyyy-MM-dd'/>"  onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})"/>	
						</td>
					  </tr>					 
					</table>



		</div>
</s:form>
</body>
</html>