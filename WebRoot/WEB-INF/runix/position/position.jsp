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
<style>
	.inp{width:35%;}
</style>
</head>
<body >
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理 &gt;&gt; <span class="STYLE1">职位管理</span>&nbsp;&gt;&gt; <span class="STYLE1">查看职位</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="2" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看职位</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">职位名称： </td>
            <td height="20" bgcolor="#FFFFFF">${position.postname }</td>
          </tr>
          <tr>
            <td width="12% height="20" align="right" bgcolor="#f8f8f8">是否屏蔽</td>
            <td height="20" bgcolor="#FFFFFF" align="left">
				<s:if test="position.screen==1">是</s:if>
				<s:else>否</s:else>
			</td>
          </tr>                  
          <tr>
            <td width="12%  height="20" align="right" bgcolor="#f8f8f8">职位描述： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<textarea name="yoursuggest" cols ="50" rows = "5" readonly="readonly">${position.description }</textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="2" bgcolor="#FFFFFF">
	            <div align="center">
					<input type="button" class="buttons" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
	            </div>
            </td>
          </tr>
        </table>
		</td>
  </tr>
</table>


</div>
</body>
</html>