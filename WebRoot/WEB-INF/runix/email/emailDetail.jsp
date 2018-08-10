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

	<meta charset="utf-8"></meta>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript">
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
</head>
<body >
  <s:form id="emailAddForm" method="post" theme="simple" >
          <div  style="width:99%;margin:0 auto; background-color:#fff;" >
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看邮件</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">发件人： </td>
            <td width="85%" height="20" bgcolor="#FFFFFF"><s:property value="sender"/></td>
          </tr>
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">发送时间： </td>
            <td width="85%" height="20" bgcolor="#FFFFFF"><s:date name="sendtime" format="yyyy-MM-dd"/></td>
          </tr>
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">收件人： </td>
            <td width="85%" height="20" bgcolor="#FFFFFF"><s:if test='flag=="1"'><s:property value="receiver"/></s:if><s:elseif test='flag=="2"'><s:property value="receivePeoples"/></s:elseif></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">邮件主题： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="title"/></td>
           
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">附    件： </td>
            <td height="20" bgcolor="#FFFFFF" id="path">
            	<input type="hidden" value="<s:property value='attachpath'/>" id="attachpath"></input>
            	<input type="hidden" value="<s:property value='attachname'/>" id="attachname"></input>
            	<ol id="files"></ol>
            </td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
            <td height="300" bgcolor="#FFFFFF"><s:property value="content" escape="false"/></td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center"> 
                <input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
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