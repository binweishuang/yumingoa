<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/baseDataService.js'></script>
	<script>
		function doCheck(){
			var datatype = document.getElementById('datatype').value;
			var dataname = document.getElementById('dataname').value;
			var datacode = document.getElementById('datacode').value;
			var sortnum = document.getElementById('sortnum').value;
			var remark = document.getElementById('remark').value;
			var flag= true;
			var reg = /^[a-zA-Z]+$/;
			var reg1=/^[0-9]+$/;
			if(datatype==''||datatype==null){
				Dialog.alert('请选择数据类型！');
				return;
			}
			if(dataname==''||dataname==null){
				Dialog.alert('数据名称不能为空！');
				return;
			}
			if(datacode==''||datacode==null){
				Dialog.alert('数据代码不能为空！');
				return;
			}else if(!reg.test(datacode)){
				Dialog.alert('数据代码必须是英文字符！');
				return;
			}else{
				 DWREngine.setAsync(false);
              	 baseDataService.checkDataCode(datacode,function(data){
               	 if(data>0){
               		Dialog.alert('此数据代码已存在，请重新输入！');
               		flag=false;
               	 }
                });
                if(!flag){
                	return;
                }
			}
			if(sortnum!=''&&sortnum!=null){
				if(!reg1.test(sortnum)){
					Dialog.alert('排序只能输入数字！');
					return;
				}
			}
			if(remark!=''&&remark!=null){
				if(remark.length>50){
					Dialog.alert('备注不能超过50个字符！');
					return;
				}
			}
			document.getElementById('baseDataAddForm').action="insert.action";
			document.getElementById('baseDataAddForm').submit();
		}
	</script>
</head>
<body >
	<s:form id="baseDataAddForm" method="post" theme="simple">
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加基础数据</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">数据类型： </td>
            <td width="88%" height="20" bgcolor="#FFFFFF"><select id="datatype" name="datatype" style="width:155px">
            	<option value="">--请选择--</option>
            	<s:iterator value="dataTypes" id="datatype">
					<option value="<s:property value='#datatype.DATACODE'/>"><s:property value='#datatype.DATANAME'/></option>
				</s:iterator>
            </select></td> 
          </tr>
		  <tr>
			<td  height="20" align="right" bgcolor="#f8f8f8">数据名称： </td>
            <td  height="20" bgcolor="#FFFFFF">
            	<input id="dataname" name="dataname" type="text"  value="" size="20"/>	
            </td>
		  </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">数据代码： </td>
            <td height="20" bgcolor="#FFFFFF"><input id="datacode" name="datacode" type="text"  value="" size="20"/></td>
          </tr>
		  <tr>
			 <td height="20" align="right" bgcolor="#f8f8f8">排    序： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="sortnum" id="sortnum" type="text" value="" size="20"/></td>
		  </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备    注： </td>
            <td height="20" bgcolor="#FFFFFF" ><input name="remark" id="remark" type="text" value="" size="60"/></td>     
          </tr>                  
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="保存"  class="buttons" onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置"  class="buttons"/>
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