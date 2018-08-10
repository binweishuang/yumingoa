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
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<style>
	.inp{width:35%;}
</style>
	<script type="text/javascript">
		

		
		function doCheck(){
			var personnelId = document.getElementById('personnelId').value;
			var title = document.getElementById('title').value;
			var dimtype = document.getElementById('dimtype').value;
			if(dimtype==''||dimtype==null){
				Dialog.alert('请选择离职类型！');
				return;
			}
			if(title==''||title==null){
				Dialog.alert('概述不能为空！');
				return;
			}else if(title.length>50){
				Dialog.alert('概述长度不能超过50个字符！');
				return;
			}
			document.getElementById('personnelForm').action='update.action?personnelId_q='+personnelId;
			document.getElementById('personnelForm').submit();
		}
		
	</script>
</head>
<body >
       <s:form id="personnelForm" action="personnelAction" method="post">
       <s:hidden name="personnelId" id="personnelId" ></s:hidden>
       <s:hidden name="flag" ></s:hidden>
        <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改离职信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
              <td width="38%" height="20" bgcolor="#FFFFFF">
            	<select id="workflowId" name="personnel.workflowId" style="width:155px">
					<option value="LZH">离职管理</option>
				</select>
            </td>
            <td width="12%" align="right" bgcolor="#f8f8f8">姓　　名： </td>
            <td width="38%" bgcolor="#FFFFFF">
            	<s:property value="name"/>
            </td>
          </tr>
          <tr>
            <td width="12%" align="right" bgcolor="#f8f8f8">部　　门： </td>
            <td bgcolor="#FFFFFF"><s:property value="dept"/></td>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">离职类型： </td>
            <td bgcolor="#FFFFFF">
            	<select id="dimtype" name="dimtype" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="dimtypeList" >
						<option value="<s:property value='dataId'/>" <s:if test="dataId==dimtype">selected="true"</s:if>><s:property value='dataname'/></option>
					</s:iterator>
				</select>
            </td>
          </tr> 
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">离职概述： </td>
            <td bgcolor="#FFFFFF" colspan="3"><input type="text" id="title" name="title" size="30" value="<s:property value='title'/>"/></td>
          </tr> 
          <tr>
            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">离职申请内容： </td>
            <td width="38%" colspan="3" bgcolor="#FFFFFF"><textarea name="content" id="content" cols ="60" rows = "10"><s:property value='content'/></textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
	            <div align="center">
	                <input type="button" class="buttons" name="save" value="下一步" onclick="doCheck();"/>
	                <input type="reset" class="buttons" name="cancle" value="重置"/>
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