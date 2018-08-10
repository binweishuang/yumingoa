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
   <s:form action="usingcarAction" id="usingcarDetailForm" theme="simple"  method="post" >
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <s:iterator value="usingcarList">		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看申请用车</span></td>
          </tr>
         
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">使用者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<s:property value="userId"/>
			</td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">用车部门：</td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<s:property value="dept"/>
				</td>
          </tr>
           <tr>
            
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请概述： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
				<s:property value='title'/>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">联系电话：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='tel'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">用车时间：</td>
            <td height="20" bgcolor="#FFFFFF"><s:date name='usingtime' format='yyyy-MM-dd HH:mm:ss'/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">返回时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:date name='backtime' format='yyyy-MM-dd HH:mm:ss'/></td>  
            <td height="20" align="right" bgcolor="#f8f8f8">陪同人数：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value='accomnum'/></td>                  
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车性质：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='usingproperty=="1"'>公用</s:if><s:elseif test='usingproperty=="2"'>私用</s:elseif></td>  
            <td height="20" align="right" bgcolor="#f8f8f8">申请车辆：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value='carId'/>
            </td>                  
          </tr>
           <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车司机： </td>
            <td height="20" bgcolor="#FFFFFF" ><s:property value='driver'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">审核状态：</td>
            <td height="20" bgcolor="#FFFFFF" ><s:if test='checkstatus=="1"'>已审核</s:if><s:elseif test='checkstatus=="2"'>审核中</s:elseif><s:else>未审核</s:else></td>   			
          </tr>
		   <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">审核意见：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><pre ><s:property value="opinion"/></pre></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车缘由：</td>
            <td height="160" bgcolor="#FFFFFF" colspan="3"><s:property value='usingreason'/></td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
				<input type="button" name="back" value="返回"  class="buttons" onclick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
        </table>
        </s:iterator>
		</td>
  </tr>
</table>
</div>
</s:form>
</body>
</html>