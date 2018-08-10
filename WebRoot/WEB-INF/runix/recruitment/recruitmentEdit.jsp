<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			editer.sync();
			var department = document.getElementById('department').value;
			var position = document.getElementById('position').value;
			var limittime = document.getElementById('limittime').value;
			var peoplenum = document.getElementById('peoplenum').value;
			var title = document.getElementById('title').value;
			var reg = /^[1-9]+$/;
			
			if(department==''||department==null){
				Dialog.alert('请选择用人部门！');
				return;
			}
			if(position==''||position==null){
				Dialog.alert('请选择招聘岗位！');
				return;
			}
			if(limittime==''||limittime==null){
				Dialog.alert('请输入招聘期限！');
				return;
			}else if(limittime.length>25){
				Dialog.alert('招聘期限不能超过25个字符！');
				return;
			}
			if(peoplenum==''||peoplenum==null){
				Dialog.alert('请输入招聘人数！');
				return;
			}else if(!reg.test(peoplenum)){
				Dialog.alert('招聘人数只能输入正整数！');
				return;
			}else if(peoplenum.length>5){
				Dialog.alert('招聘人数不能超过5个字符！');
				return;
			}
			
			if(title==''||title==null){
				Dialog.alert('请输入招聘标题！');
				return;
			}else if(title.length>50){
				Dialog.alert('招聘标题不能超过50个字符！');
				return;
			}
			document.getElementById('recruitmentAddForm').action="update.action";
			document.getElementById('recruitmentAddForm').submit();
	}


		//配置编辑器
		var editer;
		KindEditor.ready(function(K){
			editer=K.create("#details");
		});
	
	
</script>
</head>
<body >
	<s:form action="recruitmentAddAction" id="recruitmentAddForm" theme="simple"  method="post">
	<s:hidden name="recruitmentId"></s:hidden>
         <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>	  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改招聘信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<select id="workflowId" name="workflowId" style="width:155px">
				<option value="REIMBURSE">招聘管理</option>
				</select>
            </td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">用人部门： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<select id="department" name="department" style="width:155px">
						<option value="">--请选择--</option>
						<s:iterator value="deptList">
						<option value="<s:property value='deptname'/>" <s:if test="deptname==department">selected="true"</s:if>><s:property value='deptname'/></option>
					</s:iterator>
				</select>
            </td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘岗位： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<select id="position" name="position" style="width:155px">
						<option value="">--请选择--</option>
						<s:iterator value="positionList">
						<option value="<s:property value='postname'/>" <s:if test="postname==position">selected="true"</s:if>><s:property value='postname'/></option>
					</s:iterator>
				</select>
            </td>
            <td height="20" align="right" bgcolor="#f8f8f8">提交人： </td>
            <td height="20" bgcolor="#FFFFFF"><s:property value='submitter'/><input type="hidden" name="submitter" value="<s:property value='submitter'/>" /></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘期限： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="limittime" id="limittime" type="text" value="<s:property value='limittime'/>" size="20"/></td>
             <td height="20" align="right" bgcolor="#f8f8f8">招聘人数：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="peoplenum" id="peoplenum" type="text" value="<s:property value='peoplenum'/>" size="20"/></td>  
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">招聘标题：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><input name="title" id="title" type="text" value="<s:property value='title'/>" size="30"/></td>    
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">详细说明：</td>
            <td height="300" bgcolor="#FFFFFF" colspan="3"><textarea id='details' style="width:100%;height:300px" name='details'><s:property value='details'/></textarea></td>
          </tr>
		      
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
			<div align="center">
                <input type="button" name="save" value="下一步" class="buttons" onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置" class="buttons"/>
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