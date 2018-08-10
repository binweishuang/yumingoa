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
				$('<li></li>').appendTo("#files").html("<a href='<%=path%>"+paths[i]+"' class='fil'>"+names[i]+"</a>" );
			}
	});
</script>
</head>

<body >
   <s:form action="receiveFileAction" id="receiveFileDetailForm" theme="simple"  method="post" >
         <div  style="width:99%; margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公文管理 &gt;&gt; <span class="STYLE1">收文管理</span>&gt;&gt; <span class="STYLE1">查看</span></td>
					  </tr>
					</table>         
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看收文</span></td>
          </tr>
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">收文者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF" ><s:property value="docperson"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">来文标题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="title"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">来文单位： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="company"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">主题词： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="subjectterm"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">来文字号： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="docsize"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">收文类型：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="doctype"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="secret"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">紧急程度：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="urgency"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">收文编号：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="receivenum"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">来文份数：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="copies"/></td>
          </tr>
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">收文日期：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:date name="receivedate" format="yyyy-MM-dd"/></td>
			<td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:elseif test='checkstatus=="3"'>未审核</s:elseif></td>
          </tr>  
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">审核意见：</td>
            <td height="60" bgcolor="#FFFFFF" colspan="3" ><pre ><s:property value="opinion"/></pre></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
            <td height="100" bgcolor="#FFFFFF" colspan="3" ><s:property value="remark"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">附件： </td>
            <td height="20" colspan="3" bgcolor="#FFFFFF">
				<input type="hidden" name="attachname" id="attachname" value="<s:property value='attachname'/>"/>
            	<input type="hidden" name="attachpath" id="attachpath" value="<s:property value='attachpath'/>"/>
            	<ol id="files"></ol>
			</td>
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