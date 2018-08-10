<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>查看请示</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
<script type="text/javascript">
    	var index = 0;
		$(document).ready(function() {
			var attachname = $("#attachname").val();
			var attachpath = $("#attachpath").val();
			var names = attachname.split(";");
	 		var paths = attachpath.split(";");
	 		for(var i = 0;i<names.length-1;i++){
	 			$('<li></li>').appendTo("#files").html("<a href='<%=path%>"+paths[i]+"' class='fil'>"+names[i]+"</a>");
	 		}
		});
	</script>
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
					<s:iterator value="applyList">
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看请示</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请人： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${APPLICANTNAME}</td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请部门： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${DEPTNAME}</td>
			          </tr>
			          <tr>
					  <td height="20" align="right" bgcolor="#f8f8f8">请示类型： </td>
			            <td height="20" bgcolor="#FFFFFF">${TYPENAME}</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
			            <td height="20" bgcolor="#FFFFFF">${SECRETNAME}</td>        
			          </tr>
			          <tr>
			          	<td height="20" align="right" bgcolor="#f8f8f8">请示标题： </td>
			            <td height="20" bgcolor="#FFFFFF">${TITLE}</td>  
					  	<td height="20" align="right" bgcolor="#f8f8f8">紧急程度： </td>
			            <td height="20" bgcolor="#FFFFFF">${URGENCYNAME}</td>
			          </tr>         
			          <tr>
						<td height="20" align="right" bgcolor="#f8f8f8">请示金额：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3">${MONEY}</td>
			          </tr>
					  <tr>
						<td height="20" align="right" bgcolor="#f8f8f8">附    件：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3">
		            			<input  type="hidden" name = "attachname"  id = "attachname" value = "${ATTACHNAME}"/>
								<input  type="hidden" name = "attachpath"  id = "attachpath" value = "${ATTACHPATH}"/>
								<ol id="files"></ol>
			            </td>
					  </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
			            <td height="260" bgcolor="#FFFFFF" colspan="3">${CONTENT}</td>
			          </tr>
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
							<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
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
