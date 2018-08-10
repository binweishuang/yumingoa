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
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.v2.0.3.min.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		function doCheck(){
			var dossierId = document.getElementById('dossierId').value;
			var titlle = document.getElementById('titlle').value;
			var boxnum = document.getElementById('boxnum').value;
			
			var annual = document.getElementById('annual').value;
			var recgroupnum =  document.getElementById('recgroupnum').value;
			var keeplimit =  document.getElementById('keeplimit').value;
			var archivesnum =  document.getElementById('archivesnum').value;
			var docroom = document.getElementById('docroom').value;
			var category = document.getElementById('category').value;
			var pagenum = document.getElementById('pagenum').value;
			var secret = document.getElementById('secret').value;
			var starttime = document.getElementById('starttime').value;
			var endtime = document.getElementById('endtime').value;
			var status =  document.getElementById('status').value;
			var remark =  document.getElementById('remark').value;
			var reg = /^[1-9]+$/;

			if(titlle==''||titlle==null){
				alert('请输入案卷题名！');
				return;
			}else if(titlle.length>50){
				alert('案卷题名不能超过50个字符！');
				return;
			}
			if(boxnum==''||boxnum==null){
				alert('请输入盒号！');
				return;
			}else if(boxnum.length>25){
				alert('盒号不能超过25个字符！');
				return;
			}
			if(annual==''&&annual==null){
				alert('案卷年度不能为空！');
				return;
			}
			if(recgroupnum==''||recgroupnum==null){
				alert('全宗号不能为空！');
				return;
			}else if(recgroupnum.length>25){
				alert('全宗号不能超过25个字符！');
				return;
			}
			if(keeplimit!=''&&keeplimit!=null){
				if(keeplimit.length>25){
					alert('保管期限不能超过25个字符！');
					return;
				}
				
			}
			if(archivesnum==''||archivesnum==null){
				alert('档案馆代号不能为空！');
				return;
			}else if(archivesnum.length>25){
				alert('档案馆代号不能超过25个字符！');
				return;
			}
			if(docroom==''||docroom==null){
				alert('请选择档案室！');
				return;
			}
			if(pagenum!=''&&pagenum!=null){
				if(!reg.test(pagenum)){
					alert('页数只能输入整数！');
					return;
				}
			}
			if(secret==''||secret==null){
				alert('请选择秘密等级！');
				return;
			}
			
			if(starttime!=''&&starttime!=null&&endtime!=''&&endtime!=null){
				if(starttime>endtime){
					alert('起始时间不能大于终止时间！');
					return;
				}
			}
			if(status==''||status==null){
				alert('请选择案卷状态！');
				return;
			}
			if(remark!=''&&remark!=null){
				if(remark.length>250){
					alert('备注不能超过250个字符！');
					return;
				}
				
			}


			document.getElementById('dossierAddForm').action="update.action?dossierId_q="+dossierId;
			document.getElementById('dossierAddForm').submit();
	}


		

</script>
</head>
<body >
	<s:form action="dossierAddAction" id="dossierAddForm" theme="simple"  method="post">
	<s:hidden name="dossierId" id="dossierId"></s:hidden>
     <div  style="width:99%">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加案卷</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">案卷题名： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><input name="titlle" id="titlle" type="text" value="<s:property value='titlle'/>" size="20"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">盒    号： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><input name="boxnum" id="boxnum" type="text" value="<s:property value='boxnum'/>" size="20"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">案卷年度： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input name="annual" id="annual" type="text" value="<s:property value='annual'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy',vel:'smonth',realDateFmt:'yyyy'})" size="20" class="Wdate"/>
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">全宗号： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input name="recgroupnum" id="recgroupnum" type="text" value="<s:property value='recgroupnum'/>" size="20"/>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">保管期限： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="keeplimit" id="keeplimit" type="text" value="<s:property value='keeplimit'/>" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy',vel:'smonth',realDateFmt:'yyyy'})" size="20" class="Wdate"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">档案馆代号：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="archivesnum" id="archivesnum" type="text" value="<s:property value='archivesnum'/>" size="20"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">档案室：</td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="docroom" name="docroom" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="docroomList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==docroom'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">责任者：</td>
            <td height="20" bgcolor="#FFFFFF"><s:property value="author"/></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">类    目：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="category" name="category" style="width:155px">
					<option value="" >--请选择--</option>
					<option value="1" <s:if test='category=="1"'>selected="true"</s:if>>一般档案</option>
					<option value="2" <s:if test='category=="2"'>selected="true"</s:if>>重要档案</option>
                </select>
			</td>  
			<td height="20" align="right" bgcolor="#f8f8f8">页    数：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="pagenum" id="pagenum" type="text" value="<s:property value='pagenum'/>" size="20" /></td> 			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="secret" name="secret" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="secretList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==secret'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>  
			<td height="20" align="right" bgcolor="#f8f8f8">卷内文件起始时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="starttime" id="starttime" type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})" value="<s:date name='starttime' format='yyyy-MM-dd'/>" size="20" class="Wdate"/></td> 			
          </tr>
		  <tr>
			<td height="20" align="right" bgcolor="#f8f8f8">卷内文件终止时间：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="endtime" id="endtime" type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})" value="<s:date name='endtime' format='yyyy-MM-dd'/>" size="20" class="Wdate"/></td> 			
          	<td height="20" align="right" bgcolor="#f8f8f8">案卷状态：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="status" name="status" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1" <s:if test='status=="1"'>selected="true"</s:if>>拆卷</option>
					<option value="2" <s:if test='status=="2"'>selected="true"</s:if>>封卷</option>
				</select>
			</td> 
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备   注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:98%;height:100%" name='remark'><s:property value='remark'/></textarea></td>
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