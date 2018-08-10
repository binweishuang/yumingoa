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
	<script>
		function doCheck(){
			var equipmentId = document.getElementById('equipmentId').value;
			var storage = document.getElementById('storage').value;
			var cateogory = document.getElementById('cateogory').value;
			var goodsname =  document.getElementById('goodsname').value;
			var goodsnum =  document.getElementById('goodsnum').value;
			var unitprice =  document.getElementById('unitprice').value;
			var repairnum =  document.getElementById('repairnum').value;
			var scrapnum =  document.getElementById('scrapnum').value;
			var measureunit =  document.getElementById('measureunit').value;
			var remark = document.getElementById('remark').value;
			
			var reg = /^[0-9]+$/;
			var reg1 = /^\d{1,10}\.*\d{0,2}$/;

			if(storage==''||storage==null){
				Dialog.alert('请选择领用仓库！');
				return;
			}
			if(cateogory==''||cateogory==null){
				Dialog.alert('请选择物品类别！');
				return;
			}
			if(goodsname==''||goodsname==null){
				Dialog.alert('物品名称不能为空！');
				return;
			}else if(goodsname.length>25){
				Dialog.alert('物品名称不能大于25个字符！');
				return;
			}
			if(goodsnum==''||goodsnum==null){
				Dialog.alert('用品数量不能为空！');
				return;
			}else if(!reg.test(goodsnum)){
				Dialog.alert('用品数量只能为整数！');
				return;
			}
			if(unitprice!=''&&unitprice!=null){
				if(!reg1.test(unitprice)){
					Dialog.alert('用品单价只能为数字');
					return;
				}
			}
			if(repairnum!=''&&repairnum!=null){
				if(!reg.test(repairnum)){
					Dialog.alert('维修数量只能为整数！');
					return;
				}else if(repairnum>goodsnum){
					Dialog.alert('维修数量不能大于用品数量！');
					return;
				}
			}
			if(scrapnum!=''&&scrapnum!=null){
				if(!reg.test(scrapnum)){
					Dialog.alert('报废数量只能为整数！');
					return;
				}else if(scrapnum>goodsnum){
					Dialog.alert('报废数量不能大于用品数量！');
					return;
				}
			}
			if(measureunit!=''&&measureunit!=null){
				if(measureunit.length>25){
					Dialog.alert('计量单位不能超过25个字符！');
					return;
				}
			}
			
			if(remark!=''&&remark!=null){
				if(remark.length>250){
					Dialog.alert('备注不能超过250个字符！');
					return;
				}
				
			}


			document.getElementById('equipmentAddForm').action="update.action?equipmentId_q="+equipmentId;
			document.getElementById('equipmentAddForm').submit();
	}

		function selectPerson(){
	/*		var date = new Date();
			 var str=window.showModalDialog("../selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			   if(str!='' && str!=null){
				   var pple = str.split("%");
			  		$('#buier').val(pple[0]);
			   }
			   */
			   var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 700;
				diag.Height = 300;
				diag.Top=40;
				diag.Title = "选择购置人";
				diag.URL = "<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime();
				diag.OKEvent = function(){
					var str=diag.innerFrame.contentWindow.doSelect();
					if(str != "" && str != null){
						 var pple = str.split("%");
					  		$('#buier').val(pple[0]);
					}
					diag.close();
				};	
				diag.show();
		}
		


</script>
</head>
<body >
	<s:form action="equipmentAddAction" id="equipmentAddForm" theme="simple"  method="post">
	<s:hidden name="equipmentId" id="equipmentId"></s:hidden>
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改设备信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">用品类别： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="cateogory" name="cateogory" style="width:155px">
					<option value="" >--请选择--</option>
					<s:iterator value="categoryList" id="ca">
					 <option value="<s:property value='#ca.dataId'/>" <s:if test="#ca.dataId==cateogory">selected="true"</s:if>><s:property value='#ca.dataname'/></option>
					</s:iterator>
                </select>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">存放仓库： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
                <select id="storage" name="storage" style="width:155px">
					<option value="" >--请选择--</option>
					<s:iterator value="storageList" id="st">
				     <option value="<s:property value='#st.dataId'/>" <s:if test="#st.dataId==storage">selected="true"</s:if>><s:property value='#st.dataname'/></option>
				    </s:iterator>
				</select>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">购置人： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input name="buier" id="buier" onclick="selectPerson();" type="text" value="<s:property value='buier'/>" size="20" readonly/>
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">用品名称： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input name="goodsname" id="goodsname" type="text" value="<s:property value='goodsname'/>" size="20"/>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">用品数量： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="goodsnum" id="goodsnum" type="text" value="<s:property value='goodsnum'/>" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">用品单价：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="unitprice" id="unitprice" type="text" value="<s:property value='unitprice'/>" size="20"/> 元</td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">维修数量：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="repairnum" id="repairnum" type="text" value="<s:property value='repairnum'/>" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">报废数量：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="scrapnum" id="scrapnum" type="text" value="<s:property value='scrapnum'/>" size="20" /></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">计量单位：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3"><input name="measureunit" id="measureunit" type="text" value="<s:property value='measureunit'/>" size="20"/></td>          
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'><s:property value='remark'/></textarea></td>
          </tr>
		       
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
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