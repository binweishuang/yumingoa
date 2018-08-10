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
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			var reimId = document.getElementById('reimId').value;
			var reimtype = document.getElementById('reimtype').value;
			var titlle = document.getElementById('titlle').value;
			var totalmoney = document.getElementById('totalmoney').value;
			var totaldoc = document.getElementById('totaldoc').value;
			var remark = document.getElementById('remark').value;
			var itemname =  document.getElementsByName('itemname');
			var time =  document.getElementsByName('time');
			var reason =  document.getElementsByName('reason');
			var reimdate =  document.getElementsByName('reimdate');
			var docpoll =  document.getElementsByName('docpoll');
			var reimmoney =  document.getElementsByName('reimmoney');
			var otheritem =  document.getElementsByName('otheritem');
			var otherpoll =  document.getElementsByName('otherpoll');
			var othermoney =  document.getElementsByName('othermoney');
			var reg1 =/^(\d+)|(\d+\.[0-9][0-9]?)$/;
			var reg2 = /^\d+$/;
			
			if(reimtype==''||reimtype==null){
				Dialog.alert('请选择报销类型！');
				return;
			}
			if(titlle==''||titlle==null){
				Dialog.alert('请输入报销概述！');
				return;
			}else if(titlle.length>50){
				Dialog.alert('报销概述不能超过50个字符！');
				return;
			}
			if(remark!=''&&remark!=null){
				if(remark.length>250){
					Dialog.alert('备注信息不能超过250个字符！');
				}
			}
			for(var j=0;j<itemname.length;j++){
				if(itemname[j].value==''||itemname[j].value==null){
					Dialog.alert('报销项目不能为空！');
					return;
				}else if(itemname[j].length>25){
					Dialog.alert('报销项目不能超过25个字符！');
					return;
				}
			}
			for(var j=0;j<time.length;j++){
				if(time[j].value==''||time[j].value==null){
					Dialog.alert('时间不能为空！');
					return;
				}
			}
			for(var j=0;j<reason.length;j++){
				if(reason[j].value==''||reason[j].value==null){
					Dialog.alert('原因不能为空！');
					return;
				}else if(reason[j].length>25){
					Dialog.alert('原因不能超过25个字符！');
					return;
				}
			}

			for(var j=0;j<docpoll.length;j++){
				if(docpoll[j].value==''||docpoll[j].value==null){
					Dialog.alert('单据票数不能为空！');
					return;
				}else if(!reg2.test(docpoll[j].value)){
					Dialog.alert('单据票数只能输入整数数字！');
					return;
				}
			}
			for(var j=0;j<reimmoney.length;j++){
				if(reimmoney[j].value==''||reimmoney[j].value==null){
					Dialog.alert('报销金额不能为空！');
					return;
				}else if(!reg1.test(reimmoney[j].value)){
					Dialog.alert('报销金额输入格式有误！正确格式如：321.5');
					return;
				}
			}
			for(var j=0;j<otheritem.length;j++){
				if(otheritem[j].value!=''&&otheritem[j].value!=null&&otheritem[j].length>0){
					if(otheritem[j].length>25){
						Dialog.alert('其他项目不能超过25个字符！');
						return;
					}
				}
			}
			for(var j=0;j<otherpoll.length;j++){
				if(otherpoll[j].value!=''&&otherpoll[j].value!=null&&otherpoll[j].length>0){
					if(!reg2.test(otherpoll[j].value)){
						Dialog.alert('其他票数只能输入整数数字！');
						return;
					}
				}
			}
			for(var j=0;j<othermoney.length;j++){
				if(othermoney[j].value!=''&&othermoney[j].value!=null&&othermoney[j].length>0){
					if(!reg1.test(othermoney[j].value)){
						Dialog.alert('其他金额输入格式有误！正确格式如：321.5');
						return;
					}
				}
	       }
			document.getElementById('reimbursementAddForm').action="update.action?reimId_q="+reimId;
			document.getElementById('reimbursementAddForm').submit();
	}

	var i = '2';
	function addline() {
		var str = "<tr height='32p'id='item"+i+"'><td ><input name='ck' id='ck"+i+"' type='checkbox'/></td><td ><input name='itemname' id='itemname"+i+"' type='text' size='20' /></td><td ><input name='time'  id='time"+i+"' type='text' onclick=\"WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})\" value='' size='13'/></td><td ><input name='reason' id='reason"+i+"' type='text' value='' size='13'/></td><td ><input name='reimdate' id='reimdate"+i+"' type='text' value='' onclick=\"WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})\" size='10' /></td><td ><input name='docpoll' id='docpoll"+i+"' type='text' onblur='countpoll()' value='' size='10'/></td><td ><input name='reimmoney' id='reimmoney"+i+"' type='text' onblur='countmoney()' value='' size='13'/></td><td ><input name='otheritem'  id='otheritem"+i+"' type='text' value='' size='20'/></td><td ><input name='otherpoll' id='otherpoll"+i+"' onblur='countpoll()' type='text' value='' size='10'/></td><td ><input name='othermoney' id='othermoney"+i+"' onblur='countmoney()' type='text' value='' size='13'/></td></tr>";
		$('#xinxiu_table').append(str);
		i++;
	}
	function delline() {
		var flag = false;
		$('input[name="ck"]:checked').each(function() {
			$(this).parent().parent().remove();
			flag = true;
		});
		if (!flag) {
			Dialog.alert('请先选择报销项目再删除！');
		}
	}
	
	function countmoney(){
		var reimmoney =  document.getElementsByName('reimmoney');
		var othermoney =  document.getElementsByName('othermoney');
		var reg1 =/^(\d+)|(\d+\.[0-9][0-9]?)$/;
		var reg2 = /^\d+$/;
		var countmoney =0.0 ;
		for(var j=0;j<reimmoney.length;j++){
			if(reimmoney[j].value!=''&&reimmoney[j].value!=null&&reimmoney[j].value.length>0){
				 if(!reg1.test(reimmoney[j].value)){
				    Dialog.alert('报销金额输入格式有误！正确格式如：321.5');
				    return;
			     }else{
				    countmoney=parseFloat(countmoney)+ parseFloat(reimmoney[j].value);
			     }
			}
		}
		for(var j=0;j<othermoney.length;j++){
			if(othermoney[j].value!=''&&othermoney[j].value!=null&&othermoney[j].value.length>0){
				if(!reg1.test(othermoney[j].value)){
					Dialog.alert('其他金额输入格式有误！正确格式如：321.5');
					return;
				}else{
					countmoney = parseFloat(countmoney)+parseFloat(othermoney[j].value);
				}
			}
       }
		document.getElementById('totalmoney').value=countmoney;
	}
	
	function countpoll(){
		var docpoll =  document.getElementsByName('docpoll');
		var otherpoll =  document.getElementsByName('otherpoll');
		var reg1 =/^(\d+)|(\d+\.[0-9][0-9]?)$/;
		var reg2 = /^\d+$/;
		var countdoc =0;
		for(var j=0;j<docpoll.length;j++){
			if(docpoll[j].value!=''&&docpoll[j].value!=null&&docpoll[j].value.length>0){
				if(!reg2.test(docpoll[j].value)){
					Dialog.alert('单据票数只能输入整数数字！');
					return;
				}else{
				   countdoc += parseInt(docpoll[j].value);
				}
			}
		}
		for(var j=0;j<otherpoll.length;j++){
			if(otherpoll[j].value!=''&&otherpoll[j].value!=null&&otherpoll[j].value.length>0){
				if(!reg2.test(otherpoll[j].value)){
					Dialog.alert('其他票数只能输入整数数字！');
					return;
				}else{
					countdoc += parseInt(otherpoll[j].value);
				}
			}
		}
		document.getElementById('totaldoc').value=countdoc;
	}
	
</script>
</head>
<body >
	<s:form action="reimbursementAddAction" id="reimbursementAddForm" theme="simple"  method="post">

	<s:hidden name="reimId" id="reimId"></s:hidden>
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">申请报销</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="workflowId" name="workflowId" style="width:155px">
				<option value="REIMBURSE">报销管理</option>
				</select>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">报销者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<s:property value="person"/>
            </td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">报销类型： </td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="reimtype" name="reimtype" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="reimtypeList" >
						<option value="<s:property value='dataId'/>" <s:if test="reimtype==dataId">selected="true"</s:if>><s:property value='dataname'/></option>
					</s:iterator>
				</select>
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">报销概述：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="titlle" id="titlle" type="text" value="<s:property value='titlle'/>" size="30"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">报销总金额： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="totalmoney" id="totalmoney" type="text" value="<s:property value='totalmoney'/>" size="20" readonly/>元</td>
            <td height="20" align="right" bgcolor="#f8f8f8">报销总单据：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="totaldoc" id="totaldoc" type="text" value="<s:property value='totaldoc'/>" size="20" readonly/></td>
          </tr>         
		<tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备    注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' name="remark" style="width:50%;height:100%" name='remark'><s:property value='remark'/></textarea></td>
          </tr> 		  
        </table>
		<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="margin-top:4px;border-collapse:collapse;">			  
					  <tr>
						<td align="right" style="border:0;width:12%"><input name="submit1" type="button" onclick="addline()" class="newCreate" value="添加行" /></td>
						<td align="left" style="border:0;width:88%"><input name="submit1" type="button" onclick="delline()" class="newCreate" value="删除行" /></td>
					  </tr>  
		</table>
		<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <thead height="33px">
						<tr>
						  <th width="5%" ></th>
						  <th width="15%">报销项目</th>
						  <th width="10%">何时</th>
						  <th width="10%">何因</th>
						  <th width="9%">报销日期</th>
						  <th width="8%">单据票数</th>
						  <th width="10%">报销金额(元)</th>
						  <th width="15%">其他项目</th>
						  <th width="8%">其他票数</th>
						<th width="10%">其他金额(元)</th>													  
						</tr>
					  </thead>
					  <tbody>
					  <s:iterator value="reimbursementItemList" status="st">
						<tr height="32px">										 
						  <td ><s:if test="#st.index==0"></s:if><s:else><input name="ck" id="ck<s:property value='itemId'/>" type="checkbox"/></s:else></td>
						  <td ><input name="itemname" id="itemname<s:property value='itemId'/>" value="<s:property value='itemname'/>" type="text" size="20" /></td>				  
						  <td ><input name="time"  id="time<s:property value='itemId'/>" type="text" value="<s:date name='time' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" size="13"/></td>
						  <td ><input name="reason" id="reason<s:property value='itemId'/>" type="text" value="<s:property value='reason'/>" size="13"/></td>
						  <td ><input name="reimdate" id="reimdate<s:property value='itemId'/>" type="text" value="<s:date name='reimdate' format='yyyy-MM-dd'/>" size="10" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})"/></td>
						  <td ><input name="docpoll" id="docpoll<s:property value='itemId'/>" type="text" onblur="countpoll()" value="<s:property value='docpoll'/>" size="10"/></td>
						  <td ><input name="reimmoney" id="reimmoney<s:property value='itemId'/>" type="text" onblur="countmoney()" value="<s:property value='reimmoney'/>" size="13"/></td>
						  <td ><input name="otheritem"  id="otheritem<s:property value='itemId'/>" type="text" value="<s:property value='otheritem'/>" size="20"/></td>
						  <td ><input name="otherpoll" id="otherpoll<s:property value='itemId'/>" type="text" onblur="countpoll()" value="<s:property value='otherpoll'/>" size="10"/></td>
						  <td ><input name="othermoney" id="othermoney<s:property value='itemId'/>" type="text" onblur="countmoney()" value="<s:property value='othermoney'/>" size="13"/></td>
						</tr>	
						</s:iterator>		
					  </tbody>
				</table>
		</td>
  </tr>
  <tr>
	<td>
			<div align="center">
                <input type="button" name="save" value="下一步" class="buttons" onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置" class="buttons" />
				<input type="button" name="back" value="返回" class="buttons"  onclick="javascript:window.history.back(-1)"/>
            </div>
		</td>
	</tr>
</table>


</div>
</s:form>
</body>
</html>