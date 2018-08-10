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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/suggestion.js"></script>
<style>
	.inp{width:35%;}
</style>
</head>
<body >
<form id="form" method="post">
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
           <input type="hidden" name="suggestion.suggestionId" value="${suggestion.suggestionId }"/>
           <input type="hidden" name="suggestion.sugester" value="${suggestion.sugester }"/>        
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">个人建议</span>&nbsp;&gt;&gt; <span class="STYLE1">修改建议</span></td>
				</tr>
			</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改建议</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议主题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<input id="title" name="suggestion.title" type="text" size="20" style="width:35%;" maxlength="50" value="${suggestion.title }"/>
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议类型： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:select id="suggesttype" name="suggestion.suggesttype" list="types" headerKey="-1" headerValue="--请选择--" listKey="dataId" listValue="dataname" value="suggestion.suggesttype" theme="simple"
            	onblur="checkMesg('建议类型','SUGGEST');" ></s:select>
			</td>
          </tr>
          <tr>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">建议人员： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<input id="username" name="toperson" type="text"  value="${names }" size="20" style="width:35%;" onclick="selectUser();"/>
            	<span style="margin-left:10px;"></span>	
            	<input id="userId" name="suggestion.toperson" type="hidden" value="${suggestion.toperson }"/>	
            </td>
           <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否公开： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:if test="suggestion.publicity==1">
            		<input type="radio" name="suggestion.publicity" value="1" checked="checked"/>是
					<input type="radio" name="suggestion.publicity" value="0"/>否
            	</s:if>
            	<s:else>
            		<input type="radio" name="suggestion.publicity" value="1"/>是
					<input type="radio" name="suggestion.publicity" value="0" checked="checked"/>否
            	</s:else>
			</td>
          </tr>       
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">建议内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF"><textarea name="suggestion.content" cols ="50" rows = "5">${suggestion.content }</textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="修改"  onclick="doModify();" class="buttons"/>
                <input type="reset" name="cancle" value="还原" class="buttons"/>
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