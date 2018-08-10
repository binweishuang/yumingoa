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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<style>
	.inp{width:35%;}
</style>
<script>
	function doCheck(){
		var opinion = document.getElementById('opinion').value;
		if(opinion==''||opinion==null){
			Dialog.alert('请填写审批意见！');
			return;
		}
		document.getElementById('matterForm').action="doCheck.action";
		document.getElementById('matterForm').submit();
	}
	</script>
</head>
<body >
<s:form action="matterAction" id="matterForm" theme="simple"  method="post" target="_self">
<s:hidden name="matterId" id="matterId"></s:hidden>
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
		         <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			  <td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">待办事项</span>&gt;&gt; <span class="STYLE1">查看</span></td>
			  </tr>
			</table> 
			<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			  <tr>
				<td height="35" width="15%" align="right" >审批意见：</td>
				<td height="35" width="60%" align="left" style="border:0px"><textarea  style="width:95%;height:100%" name='opinion' id="opinion"><s:property value="opinion"/></textarea></td>
				<td height="35" width="25%" align="left" style="border:0px"><s:if test='handlestatus=="1"'></s:if><s:else><input name="submit1" type="button" onclick="doCheck()" class="buttons" value="审核" /></s:else>
				</td>
			  </tr> 
			</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">转正详细信息</span></td>
          </tr>
          <s:iterator value="personnelList">
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">姓　　名： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">&nbsp;${name}</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">部　　门： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            ${dept}
            </td>
          </tr>
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请日期： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:date format="yyyy-MM-dd" name="applytime"/></td>
             <td width="12%" height="20" align="right" bgcolor="#f8f8f8">概　　述： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	${title }	
            </td>
          </tr>
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">编辑内容： </td>
            <td width="38%" height="60" colspan="3" bgcolor="#FFFFFF">${content }
            </td>
          </tr>  
          </s:iterator>   
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
</s:form>
</body>
</html>