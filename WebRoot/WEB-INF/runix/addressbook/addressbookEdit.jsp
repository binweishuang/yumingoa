<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>修改通讯录</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">		
		function doCheck(){
			var name = $("#name").val();
			var dept = $("#dept").val();
			var company = $("#company").val();
			var mobilephone = $("#mobilephone").val();
			if(name == null || name == ""){
				Dialog.alert("姓名不能为空！");
				return false;
			}else if(dept == null || dept == ""){
				Dialog.alert("部门不能为空！");
				return false;
			}else if(company == null || company == ""){
				Dialog.alert("单位不能为空！");
				return false;
			}else if(mobilephone == null || mobilephone == ""){
				Dialog.alert("移动电话不能为空！");
				return false;
			}
			$("#form1").submit();
			return true;
		}
	</script>
</head>
<body >
	<s:form action="update" namespace="/addressbook" id="form1" method="post">
    <div  style="width:99%; margin:0 auto; background-color:#fff;">
    	<input type="hidden" name="addrbookId_q" value="${addrbookId_q}" />
        <table width="100%" border="0" cellspacing="0" cellpadding="5">
	       <tr>
	        <td>
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">通讯录管理</span>&gt;&gt; <span class="STYLE1">修改通讯录</span></td>
				  </tr>
				</table>		  
		        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
		          <tr>
		            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改通讯录</span></td>
		          </tr>
		          <tr>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">类型： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">
		            	<select name="addrtype" id="addrtype" style="width:155px">
		            		<s:iterator value="addrtypeList">
		            		<s:if test='addrtype == dataId'>
		            		<option value="${dataId}" id="addr_${dataId}" selected="selected">${dataname}</option>
		            		</s:if>
		            		<s:else>
		            		<option value="${dataId}" id="addr_${dataId}">${dataname}</option>
		            		</s:else>
		            		</s:iterator>
		            	</select>
		            </td>
		            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">所属组别： </td>
		            <td width="38%" height="20" bgcolor="#FFFFFF">
		            	<select name="grouptype" id="grouptype" style="width:155px">
		            		<s:iterator value="addrgroupList">
		            		<s:if test="grouptype == dataId">
		            		<option value="${dataId}" selected="selected">${dataname}</option>
		            		</s:if>
		            		<s:else>
		            		<option value="${dataId}">${dataname}</option>
		            		</s:else>
		            		</s:iterator>
		            	</select>
		            </td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">姓   名： </td>
		            <td height="20" bgcolor="#FFFFFF"><input name="name" id="name" type="text" value="${name}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">英文名： </td>
		            <td height="20" bgcolor="#FFFFFF"><input name="enname" type="text" value="${enname}" size="20"/></td>
		          </tr>
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">出生日期： </td>
		            <td height="20" bgcolor="#FFFFFF">
		            	<input name="birthdate" type="text" value="<s:date format="yyyy-MM-dd" name='birthdate'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" size="20" class="Wdate"/>
		            </td>
		            <td height="20" align="right" bgcolor="#f8f8f8">性    别：</td>
		            <td height="20" bgcolor="#FFFFFF">
		            	<s:if test='sex == "0"'>
		            	<input type="radio" name="sex" value="0" checked="checked" />男
		            	</s:if>
		            	<s:else>
		            	<input type="radio" name="sex" value="0" />男
		            	</s:else>
		            	<s:if test='sex == "1"'>
		            	<input type="radio" name="sex" value="1" checked="checked" /> 女
		            	</s:if>
						<s:else>
						<input type="radio" name="sex" value="1" /> 女
						</s:else>
		            </td>
		          </tr>         
		          <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单    位：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="company" id="company" type="text" value="${company}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">部    门：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="dept" id="dept" type="text" value="${dept}" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">单位电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="officetel" type="text" value="${officetel}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">职    务：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="position" type="text" value="${position}" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">移动电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="mobilephone" type="text" id="mobilephone" value="${mobilephone}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">家庭电话：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="homephone" type="text" value="${homephone}" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">e-mail：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="email" type="text" value="${email}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">传    真：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="fax" type="text" value="${fax}" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">通讯地址：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="address" type="text" value="${address}" size="20"/></td>
		            <td height="20" align="right" bgcolor="#f8f8f8">邮    编：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="postcode" type="text" value="${postcode}" size="20" /></td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">其他联系方式：</td>
		            <td height="20" bgcolor="#FFFFFF"><input name="othercontact" type="text" value="${othercontact}" size="20"/></td>
		          	<td height="20" align="right" bgcolor="#f8f8f8">是否共享：</td>
		            <td height="20" bgcolor="#FFFFFF">
		            	<s:if test='flag == "1"'>
		            	<input name="flag" type="radio" value="1" checked="checked" />是
		            	</s:if>
		            	<s:else>
		            	<input name="flag" type="radio" value="1" />是
		            	</s:else>
		            	<s:if test='flag == "0"'>
		            	<input type="radio" name="flag" value="0" checked="checked" />否
		            	</s:if>
		            	<s:else>
						<input type="radio" name="flag" value="0" />否
						</s:else>
		            </td>
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">联系记录：</td>
		            <td height="50" bgcolor="#FFFFFF" colspan="3"><textarea id='contactrecord' style="width:98%;height:100%" name='contactrecord'>${contactrecord}</textarea></td>          
		          </tr>
				  <tr>
		            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
		            <td height="160" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'>${remark}</textarea></td>
		          </tr>
				       
		          <tr>
		            <td height="20" colspan="4" bgcolor="#FFFFFF">
		            	<div align="center">
		            		<input type="submit" name="save" class="buttons" value="保存"  onclick="return doCheck();"/>
			                <input type="reset" name="cancle" class="buttons" value="重置" />
							<input type="button" name="back" class="buttons" value="返回" onclick="javascript:window.history.back(-1)"/>
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
