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
   <s:form action="equipmentAction" id="equipmentDetailForm" theme="simple"  method="post" >
           <div style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>	  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看设备</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">用品类别： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="cateogory"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">存放仓库： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="storage"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">购置人： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="buier"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">用品名称： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="goodsname"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用品数量： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="goodsnum"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">用品单价：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="unitprice"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">维修数量：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="repairnum"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">报废数量：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="scrapnum"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">登记人：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="registrant"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">登记时间：</td>
            <td height="20" bgcolor="#FFFFFF"><s:date name="registtime" format="yyyy-MM-dd"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">计量单位：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><s:property value="measureunit"/></td>          
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备    注：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><s:property value="remark"/></textarea></td>
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