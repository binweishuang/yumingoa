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
<style>
	.inp{width:35%;}
</style>
</head>
<body >
          <div   style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">外出详细信息</span></td>
          </tr>
           <tr> 
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">外 出 人： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="name"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">外出时间： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:date name='starttime' format='yyyy-MM-dd HH:mm:ss'/>
            </td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">返回时间： </td>
            <td height="20" bgcolor="#FFFFFF"><s:date name='endtime' format='yyyy-MM-dd HH:mm:ss'/></td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">目 的 地： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="destination"/></td>
          </tr> 
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">外出简述： </td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value="title"/></td>
         
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">审核状态： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF" >
            	<s:if test="checkstatus==3">未审核</s:if>
				  <s:if test="checkstatus==2">审核中</s:if>
				  <s:if test="checkstatus==1">已审核</s:if>
            </td>
          </tr> 
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">审核意见： </td>
              <td width="38%" height="60" colspan="3" bgcolor="#FFFFFF"><pre ><s:property value="opinion"/></pre>
            </td>
          </tr> 
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">外出缘由： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF"><s:property value="content"/>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
	            <div align="center">
					<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
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