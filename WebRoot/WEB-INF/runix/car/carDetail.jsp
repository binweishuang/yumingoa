<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix"></meta>
<meta name="author" content="Runix Inc."></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css"></link>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
<script type="text/javascript">
    	var index = 0;
		$(document).ready(function() {
			var attachname = $("#attachname").val();
			var attachpath = $("#attachpath").val();
			var names = attachname.split(";");
	 		var paths = attachpath.split(";");
	 		for(var i = 0;i<names.length-1;i++){
	 			$('<li></li>').appendTo("#files").html("<a href='<%=path%>"+paths[i]+"' class='fil'>"+names[i]+"</a>");
	 		}
		});
	</script>
<style>
.inp {
	width: 35%;
}
</style>
</head>
<body>
	<s:form id="carDetailForm">
		<div style="width:99%;margin:0 auto; background-color:#fff;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5">
									<span class="STYLE1">车辆详情</span>
								</td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">车型：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="cartype" /></td>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">排量：</td>
								<td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="capacity" /></td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">牌照：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF"><s:property value="license" /></td>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">载客数量：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF"><s:property value="passengernum" /></td>
							</tr>
							<tr>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">年检时间：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF">
									<s:date name="checktime" format="yyyy-MM-dd" />
								</td>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">投保时间：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF">
									<s:date name="insuretime" format="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">购买时间：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF">
									<s:date name="buytime" format="yyyy-MM-dd" />
								</td>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">行驶公里：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF">
									<s:property value="drivenkm" />
								</td>
							</tr>
							<tr>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">负责司机：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF"><s:property
										value="driver" /></td>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">是否可用：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF"><s:if
										test='usable=="1"'>可用</s:if> <s:else>不可用</s:else></td>
							</tr>
							<tr>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">附件：</td>
								<td width="38%" height=" 20" bgcolor="#FFFFFF" colspan="3">
									<input  type="hidden" name = "attachname"  id = "attachname" value = "<s:property value='attachname'/>"/>
									<input  type="hidden" name = "attachpath"  id = "attachpath" value = "<s:property value='attachpath'/>"/>
									<ol id="files"></ol>
								</td>
							</tr>
							<tr>
								<td width="12%" height=" 20" align="right" bgcolor="#f8f8f8">车辆描述：
								</td>
								<td width="38%" height=" 20" colspan="3" bgcolor="#FFFFFF"><s:property
										value="description" /></td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF"><div
										align="center">
										<input type="button" name="back" value="返回" class="buttons"
											onclick="javascript:window.history.back(-1)" />
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