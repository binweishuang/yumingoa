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
	<script src='<%=request.getContextPath()%>/dwr/interface/carService.js'></script>
	<script>
		function doCheck(){
			var title = document.getElementById('title').value;
			var tel = document.getElementById('tel').value;
			
			var usingtime = document.getElementById('usingtime').value;
			var backtime =  document.getElementById('backtime').value;
			var accomnum =  document.getElementById('accomnum').value;
			var usingproperty =  document.getElementById('usingproperty').value;
			var carId = document.getElementById('carId').value;
			var usingreason = document.getElementById('usingreason').value;
			var reg = /^[0-9]+$/;
			var reg1 = /(^1[3,4,5,8]\d{9})$/;

			if(title==''||title==null){
				Dialog.alert('请输入申请概述！');
				return;
			}else if(title.length>50){
				Dialog.alert('申请概述不能超过50个字符！');
				return;
			}
			if(tel==''||tel==null){
				Dialog.alert('请输入联系电话！');
				return;
			}else if(!reg1.test(tel)){
				Dialog.alert('请输入有效电话号码！');
				return;
			}
			if(usingtime==''&&usingtime==null){
				Dialog.alert('使用时间不能为空！');
				return;
			}
			if(backtime==''||backtime==null){
				Dialog.alert('返回时间不能为空！');
				return;
			}
			if(usingtime!=''&&usingtime!=null&&backtime!=''&&backtime!=null){
				if(usingtime>backtime){
					Dialog.alert('用车时间不能大于返回时间！');
					return;
				}
			}
			if(accomnum!=''||accomnum!=null){
				if(!reg.test(accomnum)){
					Dialog.alert('陪同人数只能输入数字！');
					return;
				}
				
			}
			if(usingproperty==''||usingproperty==null){
				Dialog.alert('请选择用车性质！');
				return;
			}
			if(carId==''||carId==null){
				Dialog.alert('请选择申请车辆！');
				return;
			}
			if(usingreason!=''&&usingreason!=null){
				if(usingreason.length>250){
					Dialog.alert('备注不能超过250个字符！');
					return;
				}
				
			}


			document.getElementById('usingcarAddForm').action="insert.action";
			document.getElementById('usingcarAddForm').submit();
	}



		function selectCar(){
			var carId = document.getElementById('carId');
			 var index= carId.selectedIndex;
	        var selectedVal=carId.options[index].value;
			DWREngine.setAsync(false);
			carService.getDriverByCarId(selectedVal, function(data){
				 $('#driver').val(data);
			});
		}
</script>
</head>
<body >
	<s:form action="usingcarAddAction" id="usingcarAddForm" theme="simple"  method="post">
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">申请用车</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="workflowId" name="workflowId" style="width:155px">
				<option value="USINGCAR">用车管理</option>
				</select>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请概述： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<input name="title" id="title" type="text" value="" size="30"/>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">使用者： </td>
            <td height="20" bgcolor="#FFFFFF">
				<s:property value="name"/>
			</td>
			<td height="20" align="right" bgcolor="#f8f8f8">用车部门：</td>
            <td height="20" bgcolor="#FFFFFF">
				<s:property value="dept"/>
				</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">联系电话：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="tel"  id="tel" type="text" value="" size="20" /></td>
            <td height="20" align="right" bgcolor="#f8f8f8">用车时间：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="usingtime" id="usingtime" type="text" value="" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="Wdate"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">返回时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="backtime" id="backtime" type="text" value="" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20"  class="Wdate"/></td>  
            <td height="20" align="right" bgcolor="#f8f8f8">陪同人数：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="accomnum" id="accomnum" type="text" value=""  value="" size="20"/></td>                  
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车性质：</td>
            <td height="20" bgcolor="#FFFFFF" >
            	<select id="usingproperty" name="usingproperty" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1">公用</option>
					<option value="2">私用</option>
				</select>
            </td>  
            <td height="20" align="right" bgcolor="#f8f8f8">申请车辆：</td>
            <td height="20" bgcolor="#FFFFFF" >
            	<select id="carId" name="carId" style="width:155px" onchange="selectCar();">
					<option value="">--请选择--</option>
					<s:iterator value="carList" >
				     <option value="<s:property value='carId'/>"><s:property value='cartype'/>&nbsp;<s:property value='license'/></option>
				    </s:iterator>
				</select>
            </td>                  
          </tr>
           <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车司机： </td>
            <td height="20" bgcolor="#FFFFFF" colspan="3">
				<input name="driver" id="driver" type="text" value="" size="20" readonly/></td>
          </tr> 
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用车缘由：</td>
            <td height="160" bgcolor="#FFFFFF" colspan="3"><textarea id='usingreason' style="width:98%;height:100%" name='usingreason'></textarea></td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="下一步" class="buttons" onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置" class="buttons"/>
				<input type="button" name="back" value="返回"  class="buttons" onclick="javascript:window.history.back(-1)"/>
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