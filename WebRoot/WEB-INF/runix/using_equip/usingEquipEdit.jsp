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
	<script src='<%=request.getContextPath()%>/dwr/interface/equipmentService.js'></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			var usingequipId = document.getElementById('usingequipId').value;
			var title = document.getElementById('title').value;
			var usecondition = document.getElementById('usecondition').value;
			
			var cateogory = document.getElementById('cateogory').value;
			var equipmentId =  document.getElementById('equipmentId').value;
			var storagenum =  document.getElementById('storagenum').value;
			var applynum =  document.getElementById('applynum').value;
			var starttime = document.getElementById('starttime').value;
			var endtime = document.getElementById('endtime').value;
			var remark =  document.getElementById('remark').value;
			
			var reg = /^[1-9]+$/;

			if(title==''||title==null){
				Dialog.alert('请输入申请概述！');
				return;
			}else if(title.length>50){
				Dialog.alert('申请概述不能超过50个字符！');
				return;
			}
			if(usecondition==''&&usecondition==null){
				Dialog.alert('请选择使用方式！');
				return;
			}
			if(cateogory==''||cateogory==null){
				Dialog.alert('请选择物品类别！');
				return;
			}
			if(equipmentId==''||equipmentId==null){
				Dialog.alert('请选择物品名称！');
				return;
			}
			if(applynum==''||applynum==null){
				Dialog.alert('领用数量不能为空！');
				return;
			}else if(!reg.test(applynum)){
				Dialog.alert('领用数量只能为正整数！');
				return;
			}
			if(starttime!=''&&starttime!=null&&endtime!=''&&endtime!=null){
				if(starttime-endtime>0){
					Dialog.alert('使用开始时间不能大于使用结束时间！');
					return;
				}
			}
			if(remark!=''&&remark!=null){
				if(remark.length>250){
					Dialog.alert('备注不能超过250个字符！');
					return;
				}
				
			}


			document.getElementById('usingEquipAddForm').action="update.action?usingequipId_q="+usingequipId;
			document.getElementById('usingEquipAddForm').submit();
	}

		function changeCategory(){
			var cateogory = document.getElementById('cateogory');
			 var index= cateogory.selectedIndex;
	        var selectedVal=cateogory.options[index].value;
			DWREngine.setAsync(false);
			equipmentService.getEquipmentsByCategory(selectedVal, function(equipmentList){
				 document.getElementById("equipmentId").length=0;
    			 document.getElementById("equipmentId").length++;
    			 document.getElementById("equipmentId").options[0].text = "请选择";	
    			 document.getElementById("equipmentId").options[0].value = "";
            	 for(i=0;i<equipmentList.length;i++){
            		 document.getElementById("equipmentId").length++;
    				 document.getElementById("equipmentId").options[document.getElementById("equipmentId").length - 1].text =equipmentList[i].GOODSNAME;	
                   	document.getElementById("equipmentId").options[document.getElementById("equipmentId").length - 1].value =equipmentList[i].EQUIPMENT_ID;

                 }
            	
			});
			
		}

		function changeEquip(){
			var equipmentId = document.getElementById('equipmentId');
			 var index= equipmentId.selectedIndex;
	        var selectedVal=equipmentId.options[index].value;
			DWREngine.setAsync(false);
			equipmentService.getStoragenumByEquip(selectedVal, function(equipmentList){
				 $('#storagenum').val(equipmentList[0].STORAGENUM);
			});
		}
</script>
</head>
<body >
	<s:form action="usingEquipAddAction" id="usingEquipAddForm" theme="simple"  method="post">
	<s:hidden name="usingequipId" id="usingequipId"></s:hidden>
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改领用</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="workflowId" name="workflowId" style="width:155px">
				<option value="USINGEQUIP">领用管理</option>
				</select>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请概述： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<input name="title" id="title" type="text" value="<s:property value='title'/>" size="20"/>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">使用方式： </td>
            <td height="20" bgcolor="#FFFFFF" colspan="3">
				<select id="usecondition" name="usecondition" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='usecondition=="1"'>selected="true"</s:if>>领用</option>
					<option value="2" <s:if test='usecondition=="2"'>selected="true"</s:if>>借用</option>
				</select>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">物品类别： </td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="cateogory" name="cateogory" style="width:155px" onchange="changeCategory();">
					<option value="" >--请选择--</option>
					<s:iterator value="categoryList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==cateogory'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select></td>
            <td height="20" align="right" bgcolor="#f8f8f8">物品名称：</td>
            <td height="20" bgcolor="#FFFFFF">
                <select id="equipmentId" name="equipmentId" style="width:155px" onchange="changeEquip();">
					<option value="" >--请选择--</option>
					<s:iterator value="equipmentList" >
				     <option value="<s:property value='EQUIPMENT_ID'/>" <s:if test='EQUIPMENT_ID==equipmentId'>selected="true"</s:if>><s:property value='GOODSNAME'/></option>
				    </s:iterator>
				</select>
				</td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">库存数量：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="storagenum"  id="storagenum" type="text" value="<s:property value='storagenum'/>" size="20" readonly/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">领用数量：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="applynum" id="applynum" type="text" value="<s:property value='applynum'/>" size="20" /></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">使用时间：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><input name="starttime" id="starttime" type="text" value="<s:property value='starttime'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" size="20"  class="Wdate"/> 至 <input name="endtime" id="endtime" type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" value="<s:property value='endtime'/>" size="20"  class="Wdate"/></td>          
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">领用备注：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'><s:property value='remark'/></textarea></td>
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