<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>

<script>

	function doCheck(){
		var attendId = document.getElementById('attendId').value;

		document.getElementById('attendForm').action="update.action?attendId_q="+attendId;
		document.getElementById('attendForm').submit();
	}
	

</script>
</head>
<body >
	<s:form id="attendForm" action="attendAction" method="post" >
	<s:hidden name="attendId" id="attendId"></s:hidden>
	<s:hidden name="flag" id="flag"></s:hidden>    
          <div  style="width:99%；margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
		       <tr>
		        <td>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改考勤</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">姓         名： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:property value='name'/>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属部门： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:property value='dept'/>
			            </td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">考勤日期： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
			            	<s:date name='attenddate' format="yyyy-MM-dd"/>
			            </td>
			           
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否休假： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:if test="vacationstatus==1">
										<input name="vacationstatus" type="radio" checked="checked" value="1"/>是&nbsp;&nbsp; 
										<input name="vacationstatus" type="radio" value="0"/>否
									</s:if>
									<s:else>
										<input name="vacationstatus" type="radio" value="1"/>是&nbsp;&nbsp; 
										<input name="vacationstatus" type="radio" value="0" checked="checked"/>否
									</s:else>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否外出： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:if test="gooutstatus==1">
										<input name="gooutstatus" type="radio" checked="checked" value="1"/>是&nbsp;&nbsp; 
										<input name="gooutstatus" type="radio" value="0"/>否
									</s:if>
									<s:else>
										<input name="gooutstatus" type="radio" value="1"/>是&nbsp;&nbsp; 
										<input name="gooutstatus" type="radio" value="0" checked="checked"/>否
									</s:else>
			            </td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否出差： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:if test="biztripstatus==1">
										<input name="biztripstatus" type="radio" checked="checked" value="1"/>是&nbsp;&nbsp; 
										<input name="biztripstatus" type="radio" value="0"/>否
									</s:if>
									<s:else>
										<input name="biztripstatus" type="radio" value="1"/>是&nbsp;&nbsp; 
										<input name="biztripstatus" type="radio" value="0" checked="checked"/>否
									</s:else>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否旷工：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<s:if test="absentstatus==1">
										<input name="absentstatus" type="radio" checked="checked" value="1"/>是&nbsp;&nbsp; 
										<input name="absentstatus" type="radio" value="0"/>否
									</s:if>
									<s:else>
										<input name="absentstatus" type="radio" value="1"/>是&nbsp;&nbsp; 
										<input name="absentstatus" type="radio" value="0" checked="checked"/>否
									</s:else>
			            </td>
			          </tr>         
			         
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
			                <input type="button" name="save" value="保存" class="buttons" onclick="doCheck();"/>
			                <input type="reset" name="cancle" class="buttons" value="重置" />
							<input type="button" name="back" class="buttons" value="返回" onclick="javascript:window.history.back(-1)"/>
			            </div></td>
			          </tr>
			        </table>
				</td>
  			</tr>
		</table>
	</div>
</s:form>
</body>
</html>