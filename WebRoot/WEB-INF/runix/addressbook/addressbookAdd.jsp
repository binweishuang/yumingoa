<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加通讯录</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">
	
		function doCheck(){
			var addrtype = $("#addrtype").val();
			var grouptype = $("grouptype").val();
			var name = $("#name").val();
			var dept = $("#dept").val();
			var company = $("#company").val();
			var mobilephone = $("#mobilephone").val();
			var enname = $("#enname").val();
			var homephone = $("#homephone").val();
			var officetel = $("#officetel").val();
			if(addrtype == "0"){
				Dialog.alert("请选择通讯录类型！");
				return false;
			}
			if(grouptype == "0"){
				Dialog.alert("请选择组别！");
				return false;
			}
			if(name == null || name == ""){
				Dialog.alert("请填写姓名！");
				return false;
			}
			if(dept == null || dept == ""){
				Dialog.alert("请填写部门！");
				return false;
			}
			if(company == null || company == ""){
				Dialog.alert("单位不能为空！");
				return false;
			}
			if(mobilephone == null || mobilephone == ""){
				Dialog.alert("移动电话不能为空！");
				return false;
			}
			if(!/^1(3|4|5|8)[0-9]{9}/.test(mobilephone)){
				Dialog.alert("请输入正确的移动电话！");
				return false;
			}
			if(!/^[a-zA-Z]+\s*/.test(enname)){
				Dialog.alert("英文名必须是字母！");
				return false;
			}
			if(!/^(\d{3,4}-|\s)?\d{7,8}/.test(homephone)){
				Dialog.alert("请输入正确的家庭电话！");
				return false;
			}
			if(!/^(\d{3,4}(-|\s))?\d{7,8}/.test(officetel)){
				Dialog.alert("请输入正确的单位电话！");
				return false;
			}
			$("#form1").submit();
			return true;
		}
	</script>
</head>
<body >
	<form action="<%=request.getContextPath()%>/addressbook/insert.action" id="form1" method="post">
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
	       <tr>
	        <td>
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">通讯录管理</span>&gt;&gt; <span class="STYLE1">添加通讯录</span></td>
				  </tr>
				</table>		  
		        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
		          <tr>
		            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加通讯录</span></td>
		          </tr>
		          <tr>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">类型： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">
						<select id="addrtype" name="addrtype" style="width:155px">
							<option value="0">--请选择--</option>
							<s:iterator value="addrtypeList">
							<option value="${dataId}">${dataname}</option>
							</s:iterator>
						</select>
					</td>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属组别： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">
						<select id="grouptype" name="grouptype" style="width:155px">
							<option value="0">--请选择--</option>
							<s:iterator value="addrgroupList">
							<option value="${dataId}">${dataname}</option>
							</s:iterator>
						</select>
					</td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">姓   名： </td>
		            <td height="20" bgcolor="#FFFFFF"><input name="name" id="name" type="text" value="" size="20" /></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">英文名： </td>
		            <td height="20" bgcolor="#FFFFFF"><input name="enname" id="enname" type="text" value="" size="20" /></td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">出生日期： </td>
		            <td height="20" bgcolor="#FFFFFF">
		            	<input name="birthdate" type="text" value="" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" class="Wdate"/>
		            </td>
		            <td height="20" align="right" bgcolor="#f8f8f8">性    别：</td>
		            <td height="20" bgcolor="#FFFFFF">
		            	<input name="sex" type="radio" value="0" checked="checked" />男
						<input type="radio" name="sex" value="1" /> 女
					</td>
		          </tr>         
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单    位：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="company" id="company" type="text" value="" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">部    门：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="dept" id="dept" type="text" value="" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单位电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="officetel" id="officetel" type="text" value="" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">职    务：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="position" id="position" type="text" value="" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">移动电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="mobilephone" id="mobilephone" type="text" value="" size="20" /></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">家庭电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="homephone" id="homephone" type="text" value="" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">e-mail：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="email" type="text" value="" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">传    真：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="fax" type="text" value="" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">通讯地址：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="address" type="text" value="" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">邮    编：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="postcode" type="text" value="" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">其他联系方式：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="othercontact" type="text" value="" size="20"/></td>
		          	<td height="20" align="right" bgcolor="#f8f8f8">是否共享：</td>
		          	<td height="20" bgcolor="#FFFFFF">
		            	<input name="flag" type="radio" value="1" checked="checked" />是
						<input type="radio" name="flag" value="0" /> 否
					</td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">联系记录：</td>
		            <td height="50" bgcolor="#FFFFFF" colspan="3"><textarea id='contactrecord' style="width:98%;height:100%" name='contactrecord'></textarea></td>          
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
		            <td height="160" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'></textarea></td>
		          </tr>
				       
		          <tr>
		            <td height="20" colspan="4" bgcolor="#FFFFFF">
		            	<div align="center">
			                <input type="submit" class="buttons" name="save" value="保存"  onclick="return doCheck();"/>
			                <input type="reset" class="buttons" name="cancle" value="重置" />
							<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
		            	</div>
		            </td>
		          </tr>
		        </table>
			 </td>
	  		</tr>
		</table>
	</div>
	</form>
</body>
</html>
