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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script>
	function doCheck(){
		var nodeId = document.getElementById('nodename');
         var index= nodeId.selectedIndex;
         var selectedVal=nodeId.options[index].value;
		// alert(selectedVal);
//		 window.returnValue=selectedVal;
//		 window.close();
		return selectedVal;
	}

	</script>
</head>
<body >
	<s:form id="flowNodeAddForm" method="post" theme="simple">
          <div  style="width:99%;align:center;padding-top:40px;padding-bottom:40px;margin:0 auto; background-color:#fff;">
		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td width="40%" height="30" align="right" bgcolor="#f8f8f8">节点名称： </td>
            <td width="60%" height="30" bgcolor="#FFFFFF" colspan="2" style="border:0px">
            <select id="nodename" name="nodename" style="width:120px">
				<option value="">--请选择--</option>
				<s:iterator value="flowNodeList" id="node">
                   <option value="<s:property value='#node.FLOWNODE_ID'/>%<s:property value='#node.NODENAME'/>"><s:property value='#node.NODENAME'/></option>
                   </s:iterator>
			</select>
            </td> 
         	
          </tr>

        </table>

</div>
</s:form>
</body>
</html>