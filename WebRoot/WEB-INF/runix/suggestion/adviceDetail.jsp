<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css">
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/suggestion.js"></script>
<style>
	.inp{width:35%;}
</style>
</head>
<body >
<form id="form" method="post">
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">个人建议</span>&nbsp;&gt;&gt; <span class="STYLE1">查看建议</span></td>
				</tr>
			</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看建议</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议主题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	&nbsp;${suggestion.title }
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议类型： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	&nbsp;${suggesttype }
			</td>
          </tr>
          <tr>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议人员： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	&nbsp;${toperson }
            </td>
           <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否公开： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				&nbsp;
				<s:if test="suggestion.publicity==1">
					是
				</s:if>
				<s:else>
					否
				</s:else>
			</td>
          </tr>       
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">建议内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF"><textarea name="suggestion.content" cols ="50" rows = "5" readonly="readonly">&nbsp;${suggestion.content }</textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
				<input type="button" name="back" value="返回" onclick="javascript:window.history.back(-1)" class="buttons"/>
            </div></td>
          </tr>
        </table>
		</td>
  </tr>
</table>


</div>
</form>
</body>
</html>