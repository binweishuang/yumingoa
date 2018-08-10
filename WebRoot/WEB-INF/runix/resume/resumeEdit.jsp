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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/recruitmentService.js'></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			var resumeId  = document.getElementById('resumeId').value;
			var name = document.getElementById('name').value;
			var tel = document.getElementById('tel').value;
			var age = document.getElementById('age').value;
			var interview = document.getElementById('interview').value;
			var hire = document.getElementById('hire').value;
			var recruitmentId = document.getElementById('recruitmentId').value;
			
			var remark = document.getElementById('remark').value;
			var reg = /^[1-9][0-9]$/;
			var reg1 = /(^1[3,4,5,8]\d{9})$/;
			
			if(name==''||name==null){
				Dialog.alert('请输入应聘者！');
				return;
			}else if(name.length>25){
				Dialog.alert('应聘者不能超过25个字符！');
				return;
			}
			if(age!=''||age!=null){
				if(!reg.test(age)){
					Dialog.alert('年龄格式不正确！');
					return;
				}
			}
			if(tel==''||tel==null){
				Dialog.alert('请输入联系电话！');
				return;
			}else if(!reg1.test(tel)){
				Dialog.alert('联系电话格式不正确！');
				return;
			}
			if(interview==''||interview==null){
				Dialog.alert('请选择是否面试！');
				return;
			}
			if(hire==''||hire==null){
				Dialog.alert('请选择是否录用！');
				return;
			}
		
			if(recruitmentId==''||recruitmentId==null){
				Dialog.alert('请选择招聘主题！');
				return;
			}
			if(remark!=''||remark!=null){
				if(remark.length>500){
					Dialog.alert('简历说明不能超过500个字符！');
					return;
				}
			}
			document.getElementById('resumeAddForm').action="update.action?resumeId_q="+resumeId;
			document.getElementById('resumeAddForm').submit();
	}
		function changeRecruit(){
			var recruitmentId = document.getElementById('recruitmentId');
			 var index= recruitmentId.selectedIndex;
	        var selectedVal=recruitmentId.options[index].value;
	        recruitmentService.getRecruitmentById(selectedVal,function(Recruitment){
	       	 if(Recruitment!=null){
				$('#department').val(Recruitment.department);		
				$('#position').val(Recruitment.position);
	       	 }
	        });
		}

</script>
</head>
<body >
	<s:form action="resumeAddAction" id="resumeAddForm" theme="simple"  method="post">
	<s:hidden name="resumeId" id="resumeId"></s:hidden>
         <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改简历信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">应聘者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><input name="name"  id="name" type="text"  value="<s:property value='name'/>" size="20" /></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">性    别： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><input name="sex"  type="radio"  value="1" <s:if test='sex=="1"'>checked="checked"</s:if> />男
									<input name="sex" type="radio" value="2" <s:if test='sex=="2"'>checked="checked"</s:if>/>女</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">年    龄： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="age" id="age" type="text"  value="<s:property value='age'/>" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">联系电话： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="tel" id="tel" type="text" value="<s:property value='tel'/>" size="20"/></td>
          </tr>
          <tr>
          	<td height="20" align="right" bgcolor="#f8f8f8">招聘主题：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3">
            	<select id="recruitmentId" name="recruitmentId" style="width:155px" onchange="changeRecruit();">
						<option value="">--请选择--</option>
						<s:iterator value="recruitmentList">
						<option value="<s:property value='RECRUITMENT_ID'/>" <s:if test="RECRUITMENT_ID==recruitmentId">selected="true"</s:if>><s:property value='TITLE'/></option>
					    </s:iterator>
				</select>
            </td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">应聘部门： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="department" id="department" type="text" value="<s:property value='department'/>" size="20" readonly/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">应聘岗位：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="position" id="position" type="text" value="<s:property value='position'/>" size="20" readonly/></td>
          </tr>         
           <tr>
           <td height="20" align="right" bgcolor="#f8f8f8">是否面试：</td>
            <td height="20" bgcolor="#FFFFFF" >
            	<select id="interview" name="interview" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='interview=="1"'>selected="true"</s:if>>是</option>
					<option value="0" <s:if test='interview=="0"'>selected="true"</s:if>>否</option>
				</select>
            </td> 
            <td height="20" align="right" bgcolor="#f8f8f8">是否录用：</td>
            <td height="20" bgcolor="#FFFFFF" >
            	<select id="hire" name="hire" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='hire=="1"'>selected="true"</s:if>>是</option>
					<option value="0" <s:if test='hire=="0"'>selected="true"</s:if>>否</option>
				</select>
            </td> 
						
          </tr>
          
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">简历附件：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><input type="file" name="myFile" id="myFile"  title="只能上传小于10M的文件！"/></td>
          </tr>
		  <tr>           
            <td height="20" align="right" bgcolor="#f8f8f8">简历说明：</td>
            <td height="200" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'><s:property value='remark'/></textarea></td>
          </tr>
		      
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
			<div align="center">
                <input type="button" name="save" value="保存" class="buttons" onclick="doCheck();"/>
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