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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
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
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">查看发文</span></td>
          </tr>
          <s:iterator value="officialDocumentList">
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">发文管理
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">发文标题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value='title'/></td>
          </tr>
          
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">来文机关： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='company'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">主题词： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='subjectterm'/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">主送者： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='mainsender'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">抄送者：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='copysender'/></td>
          </tr>   
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">发文类型： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='doctype'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='secret'/></td>
          </tr>		  
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">紧急程度：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='urgency'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">发文字号：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='docsize'/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">保存期限：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='timelimit'/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">印发分数：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='copies'/></td>
          </tr>  
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
            <td height="100" bgcolor="#FFFFFF" colspan="3" ><s:property value="remark"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">上传文件： </td>
            <td height="20" colspan="3" bgcolor="#FFFFFF">
				<input  type="hidden" name = "attachname"  id = "attachname" value = "<s:property value='attachname'/>"/>
				<input  type="hidden" name = "attachpath"  id = "attachpath" value = "<s:property value='attachpath'/>"/>
				<ol id="files"></ol>
            </td>
          </tr>  
          </s:iterator>
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