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
</head>
<body >
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">日程安排</span>&gt;&gt; 
						<span class="STYLE1">查看日程</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看日程</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">日程标题： </td>
            <td height="20" bgcolor="#FFFFFF">${schedule.title }</td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">安排人： </td>
            <td height="20" bgcolor="#FFFFFF">${schedule.executer }</td>
          </tr> 
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">时间： </td>
            <td height="20" bgcolor="#FFFFFF">
				<s:property value="@runix.util.StringUtil@dateFmt(schedule.starttime)"/>至<s:property value="@runix.util.StringUtil@dateFmt(schedule.endtime)"/>
			</td>
          </tr>        
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">日程内容：</td>
            <td height="20" bgcolor="#FFFFFF">
				${schedule.content }
			</td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
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