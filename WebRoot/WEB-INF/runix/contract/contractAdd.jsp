<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加合同</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css" />
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script> 
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">
		//配置编辑器
		var editor;
		KindEditor.ready(function(K){
			editor = K.create("#details");//创建编辑器
		});
		//提交表单 保存合同
		function doCheck(){
			editor.sync();//获取编辑器中的值
			var contracttype = $("#contracttype").val(),
			status = $("#status").val(),
			customecompany = $("#customecompany").val(),
			contact = $("#contact").val(),
			contacttel = $("#contacttel").val(),
			signeddate = $("#signeddate").val(),
			enddate = $("#enddate").val();
			if(contracttype == "0"){
				Dialog.alert("请选择合同类型！");
				return false;
			}
			if(status == "0"){
				Dialog.alert("请选择合同状态！");
				return false;
			}
			if(customecompany == "" || customecompany == null){
				Dialog.alert("请填写客户单位！");
				return false;
			}
			if(contact == "" || contact == null){
				Dialog.alert("请填写联系人！");
				return false;
			}
			if(contacttel == "" || contacttel == null){
				Dialog.alert("请填写联系电话！")
				return false;
			}else if(!/(^(\d{3,4}-)\d{7,8})|(^1[3,4,5,8]\d{9})$/.test(contacttel)){
				Dialog.alert("联系电话格式不正确！");
				return false;
			}
			if(signeddate == "" || signeddate == null){
				Dialog.alert("请填写签订日期！");
				return false;
			}
			if(enddate == "" || enddate == null){
				Dialog.alert("请填写终止日期！");
				return false;
			}
			if(signeddate > enddate){
				Dialog.alert("请填写正确的签订日期和终止日期！");
				return false;
			}
			$("#form1").submit();
			return true;
		}
	</script>
</head>
<body >
	<form action="<%=request.getContextPath() %>/contract/insert.action" method="post" id="form1">     
    <div  style="width:99%; margin:0 auto; background-color:#fff;">                
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
       		<tr>
        		<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 人力资源 &gt;&gt; <span class="STYLE1">合同管理</span>&nbsp;&gt;&gt; <span class="STYLE1">添加合同</span></td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加新合同</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">签订人： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${user.name}<input name="signedperson" type="hidden"  value="${user.userId}" /></td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">相关部门： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">${dept.deptname}<input name="department" type="hidden" value="${user.deptId}" /></td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同类型： </td>
			            <td height="20" bgcolor="#FFFFFF">
			            	<select id="contracttype" name="contracttype" style="width:155px;">
			            		<option value="0">--请选择--</option>
			            		<s:iterator value="contracttypeList">
			            		<option value="${dataId}">${dataname}</option>
			            		</s:iterator>
			            	</select>
			            </td>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同状态： </td>
			            <td height="20" bgcolor="#FFFFFF">
			            	<select id="status" name="status" style="width:155px;">
			            		<option value="0">--请选择--</option>
			            		<option value="1">已执行</option>
			            		<option value="2">执行中</option>
			            		<option value="3">未执行</option>
			           	 	</select>
			           	 </td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">客户单位： </td>
			            <td height="20" bgcolor="#FFFFFF"><input name="customecompany" id="customecompany" type="text" size="20"/></td>
			            <td height="20" align="right" bgcolor="#f8f8f8">联系人：</td>
			            <td height="20" bgcolor="#FFFFFF"><input name="contact" id="contact" type="text" size="20"/></td>
			          </tr>         
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">联系电话：</td>
			            <td height="20" bgcolor="#FFFFFF"><input name="contacttel" id="contacttel" type="text" size="20"/></td>
			            <td height="20" align="right" bgcolor="#f8f8f8">签订日期：</td>
			            <td height="20" bgcolor="#FFFFFF">
			            	<input class="Wdate" name="signeddate" id="signeddate" type="text" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'enddate\')||\'2020-01-01\'}'})" />
			            </td>
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">终止日期：</td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3">
			            	<input class="Wdate" name="enddate" id="enddate" type="text" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'signeddate\')}',maxDate:'2020-10-01'})"/>
			            </td>          
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">合同说明：</td>
			            <td height="260" bgcolor="#FFFFFF" colspan="3"><textarea id='details' style="width:100%;height:260px;" name='details'></textarea></td>
			          </tr>
					       
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
			                <input type="submit" class="buttons" name="save" value="保存" class="buttons"  onclick="return doCheck();"/>
			                <input type="reset" class="buttons" name="cancle" value="重置" class="buttons" />
							<input type="button" class="buttons" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
			            </div></td>
			          </tr>
			        </table>
				</td>
  			</tr>
		</table>
	</div>
	</form>
</body>
</html>
