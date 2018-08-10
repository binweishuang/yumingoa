<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.v2.0.3.min.js"></script>
    <script type="text/javascript">
    	function doCheck(){
    		document.form.submit();
    	}
    </script>
</head>
<body >
          <div style="width:99%; margin:0 auto; background-color:#fff;">
           <form name="form" id="form" action="update.action" method="post">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">网络硬盘</span>&nbsp;&gt;&gt; 
						<span class="STYLE1">修改文件</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改文件</span></td>
          </tr>
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">文件： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<input type="text" name="filename" value="${filename}" maxlength="50"/>${doc}
            	<input type="hidden" name="fileId" value="${netdisk.fileId}"/>
            	<input type="hidden" name="doc" value="${doc}"/>
            </td>
          </tr>
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否共享： </td>
            <td height="20" bgcolor="#FFFFFF">
				是：<input name="publicity" type="radio" size="20" <s:if test="netdisk.publicity==1">checked="checked"</s:if> value="1"/>
				否：<input name="publicity" type="radio" size="20" <s:if test="netdisk.publicity==0">checked="checked"</s:if> value="0"/>
			</td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
				<div align="center">
					<input type="button" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
					<input type="button" class="buttons" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
            	</div>
			</td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</form>          
</div>
</body>
</html>