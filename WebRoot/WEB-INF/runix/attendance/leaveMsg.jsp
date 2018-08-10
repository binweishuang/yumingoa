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
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">

</head>
<body >
   <s:form action="attendAction" id="attendForm" theme="simple"  method="post" >
           <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>	  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">签退信息</span></td>
          </tr>
          <tr>
            <td width="25%" height="20" align="right" bgcolor="#f8f8f8">现在时间： </td>
            <td width="75%" height="20" bgcolor="#FFFFFF" ><s:property value="signouttime"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#FFFFFF" colspan="2"> </td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">上班时间： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="starttime"/>&nbsp;&nbsp;--&nbsp;&nbsp;<s:property value="endtime"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#FFFFFF" colspan="2"> </td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">早退状况：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="signoutMsg"/></td>
          </tr>
      <!--     <tr>
            <td height="20" colspan="2" bgcolor="#FFFFFF"><div align="center">
				<input type="button" name="back" value="关闭" class="buttons" onclick="javascript:window.close();"/>
            </div></td>
          </tr> -->
        </table>
		</td>
  </tr>
</table>


</div>
</s:form>
</body>
</html>