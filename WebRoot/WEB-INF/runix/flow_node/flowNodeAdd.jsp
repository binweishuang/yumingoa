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
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/flowNodeService.js'></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
	function doCheck(){
		var  nodename = document.getElementById('nodename').value;
		var leaders = document.getElementsByName('approleaders');
		  var peopleId = document.getElementsByName('leadersid');
		  var leadersval ="";
		  var peopleIdval = "";
		  if(leaders.length>0){
			  for(var i=0;i<leaders.length;i++){
				  if(leadersval==''||leadersval==null){
					  leadersval = leadersval+leaders[i].value;
				  }else{
					  leadersval = leadersval+","+leaders[i].value;
				  }
			  }
		  }
		  if(peopleId.length>0){
			  for(var i=0;i<peopleId.length;i++){
				  if(peopleIdval==''||peopleIdval==null){
					  peopleIdval = peopleIdval+peopleId[i].value;
				  }else{
					  peopleIdval = peopleIdval+","+peopleId[i].value;
				  }
			  }
		  }
		var flag= true;

		if(nodename==''||nodename==null){
			Dialog.alert('节点名称不能为空！');
			return;
		}else{
			DWREngine.setAsync(false);
         	 flowNodeService.checkNodename(nodename,function(data){
          	 if(data>0){
          		Dialog.alert('此节点名称已存在，请重新输入！');
          		flag=false;
          	 }
           });
         	 if(!flag){
         		 return;
         	 }
		}
		if(leadersval==''||leadersval==null){
			Dialog.alert('审批领导不能为空！');
			return;
		}
		document.getElementById('flowNodeAddForm').action="insert.action?approleaders_i="+leadersval+"&leadersid_i="+peopleIdval;
		document.getElementById('flowNodeAddForm').submit();
	}
	
	function showselect(){
		 var date = new Date();
		  var leaders = document.getElementsByName('approleaders');
		  var peopleId = document.getElementsByName('leadersid');
		  var leadersval ="";
		  var peopleIdval = "";
		  if(leaders.length>0){
			  for(var i=0;i<leaders.length;i++){
				  if(leadersval==''||leadersval==null){
					  leadersval = leadersval+leaders[i].value;
				  }else{
					  leadersval = leadersval+","+leaders[i].value;
				  }
			  }
		  }
		  if(peopleId.length>0){
			  for(var i=0;i<peopleId.length;i++){
				  if(peopleIdval==''||peopleIdval==null){
					  peopleIdval = peopleIdval+peopleId[i].value;
				  }else{
					  peopleIdval = peopleIdval+","+peopleId[i].value;
				  }
			  }
		  }
/*		 var str=window.showModalDialog("../selectPeople/query.action?random="+date.getTime()+"&people="+leadersval+"&peopleId="+peopleIdval,"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
		   if(str!='' && str!=null){
			   var pple = str.split("%");
		  		var leaders = pple[0].split(",");
		  		var leadersid = pple[1].split(",");
			   if(leaders.length>0){
				   $('#checkleader tr').remove();
				   for(var i=0;i<leaders.length;i++){
				    	var str ="<tr id='"+leadersid[i]+"'><td text-align='center' width='70%'><input name='approleaders' value='"+leaders[i]+"' type='text' readonly='readonly'/><input type='hidden' name='leadersid' value='"+leadersid[i]+"'/></td><td text-align='center' width='30%'><a  href='#' onclick='dodel("+leadersid[i]+")'><img src='<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png' border='0'  style='vertical-align:middle;'>删除</a></td></tr>"
				   		$('#checkleader').append(str);
				   }
			   }
		   }
*/		   
//		   var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择人员";
			diag.URL = "<%=request.getContextPath()%>/selectPeople/query.action?random="+date.getTime()+"&people="+leadersval+"&peopleId="+peopleIdval;
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				if(str != "" && str != null){
					 var pple = str.split("%");
				  		var leaders = pple[0].split(",");
				  		var leadersid = pple[1].split(",");
					   if(leaders.length>0){
						   $('#checkleader tr').remove();
						   for(var i=0;i<leaders.length;i++){
						    	var str ="<tr id='"+leadersid[i]+"'><td text-align='center' width='70%'><input name='approleaders' value='"+leaders[i]+"' type='text' readonly='readonly'/><input type='hidden' name='leadersid' value='"+leadersid[i]+"'/></td><td text-align='center' width='30%'><a  href='#' onclick='dodel("+leadersid[i]+")'><img src='<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png' border='0'  style='vertical-align:middle;'>删除</a></td></tr>"
						   		$('#checkleader').append(str);
						   }
					   }
				}
				diag.close();
			};	
			diag.show();
	}
	
	function dodel(id){
		//alert(id);
		$('#'+id).remove();
	}
	</script>
</head>
<body >
	<s:form id="flowNodeAddForm" method="post" theme="simple">
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="3" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加节点数据</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">节点名称： </td>
            <td width="60%" height="20" bgcolor="#FFFFFF" colspan="2" style="border:0px"><input id="nodename" name="nodename" type="text"  value="" size="20"/></td> 
         	
          </tr>
		  <tr>
			<td width="12%"  align="right" bgcolor="#f8f8f8">审批领导： </td>
            <td width="30%"   bgcolor="#FFFFFF" style="border:0">
            	<!-- <textarea style="width:98%"  id= "approleaders" name="approleaders" cols="" rows="" readonly="readonly"></textarea> -->
            	<div style="width:100%;height:100%">
            	<table id="checkleader" width="100%" border="0" cellspacing="0" cellpadding="5">
       				<!--<tr>
        				<td text-align="center" width="70%">
        					<input name="approleaders" type="text"/><input type="hidden" name="leadersid"/>
        				</td>
        				<td text-align="center" width="30%">
        					<a  href="#" onclick="dodelete('<s:property value='DATA_ID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
        				</td>
        			</tr>-->
        		</table>
        		</div>
            </td>
            <td width="58%"  style="border:0" bgcolor="#FFFFFF"><input type="button" value="选择" class="buttons" onclick="showselect();"/></td>
		  </tr>
			                  
          <tr>
            <td height="20" colspan="3" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="保存"  class="buttons" onclick="doCheck();"/>
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