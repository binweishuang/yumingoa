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
	<link rel="stylesheet" type="text/css" href="../css/system.css">
</head>
<body >
          <div  style="width:99%">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" bgcolor="#e7f4fe">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">任务管理</span>&gt;&gt; 
							<span class="STYLE1">查看下达任务</span>
						</td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看下达任务</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">任务标题： </td>
            <td width="85%" height="20" bgcolor="#FFFFFF">一个任务</td>
            
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">完成标准： </td>
            <td height="20" bgcolor="#FFFFFF">100%</td>
          </tr>
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">汇报对象： </td>
            <td height="20" bgcolor="#FFFFFF">abc</td>
          </tr>
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">日期： </td>
            <td height="20" bgcolor="#FFFFFF">
				2013-11-18至2013-11-19
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">附    件： </td>
            <td height="20" bgcolor="#FFFFFF"><input type="file" name="myFile" id="myFile"  title="只能发送小于10M的文件！"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">执行人： </td>
            <td height="20" bgcolor="#FFFFFF">abc</td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">任务时限： </td>
            <td height="20" bgcolor="#FFFFFF">2天</td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
            <td height="300" bgcolor="#FFFFFF"><textarea id='content' style="width:98%;height:100%" name='content'>asdfghjkl</textarea></td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center"> 
				<input type="button" name="send" value="发送"  />
				<input type="button" name="save" value="保存" />
                <input type="button" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
        </table>
		</td>
  </tr>
</table>


</div>
</body>
</html>