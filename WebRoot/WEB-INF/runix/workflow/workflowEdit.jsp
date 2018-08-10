<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
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
	<script src='<%=request.getContextPath()%>/dwr/interface/workflowService.js'></script>
	<style>
	.newCreate{ border:1px solid #333333; width:80px; text-align:center; font-size:13px;  height:25px; line-height:23px; margin:1px; background:#afffee;cursor:pointer}	    
	.nodeDiv{width:200px;height:30px;padding-top:5px;text-align:center;line-height:31px;font-size:12px;font-weight: bold;border:1px solid #529214;}
	.arrow{width:100%;height:20px;padding-left:130px}
	.abc{padding:1px;margin:0px}
	</style>
	<script type="text/javascript">
		var i ='1';
		function addline(nodename){
		 var workflowId = document.getElementById('workflowId').value;
		 if(workflowId==''||workflowId==null){
			 Dialog.alert('请先选择流程类型！');
			 return;
		 }
		  var xinxiu_table = document.getElementById('xinxiu_table');
		  var xinxiu_table_rows = xinxiu_table.rows;
		  if(xinxiu_table_rows.length>5){
			Dialog.alert('最多可以添加5个流程节点');
			return;
		  }
		  var str ="<tr border='0px' id='row"+i+"' class='abc'><td style='border:0px;padding:1px;margin:0px' ><div style='margin-bottom:2px;'><center><div style='margin-bottom:2px;'><img src='<%=SystemConfig.FRAMEWORK_PATH%>/img/arrow.png' border='0'></div><div style='float:right;width:35%;height:20px;'></div><div style='float:right;width:5%;height:20px;padding-top:5px;'><input type='checkbox' name='lc' id='node"+i+"' style='padding-left:3px'/></div><div class='nodeDiv' ><input type='text' name='nodename' style='text-align:center' value='"+nodename+"' id='nodename"+i+"' onclick='selectNode(this.id);' size='20' title='请输入节点名称' style='height:18px;'/></div></center></div></td></tr>";		

		  $('#xinxiu_table').append(str);
		
       //   var xinxiu_table_new_tr = xinxiu_table.insertRow();
		//  xinxiu_table_new_tr.id='row'+i;
       //   var xinxiu_table_tr_index=xinxiu_table_new_tr.rowIndex;
		  
       //   var xinxiu_table_td1 = xinxiu_table_new_tr.insertCell();
       //   xinxiu_table_new_tr.class='abc';
		//  $("#xinxiu_table tr td").css({"border":"0px","margin":"0px","padding":"1px"});
		//  xinxiu_table_td1.innerHTML="<div style='margin-bottom:2px;'><center><div style='margin-bottom:2px;'><img src='<%=SystemConfig.FRAMEWORK_PATH%>/img/arrow.png' border='0'></div><div style='float:right;width:35%;height:20px;'></div><div style='float:right;width:5%;height:20px;padding-top:5px;'><input type='checkbox' name='lc' id='node"+i+"' style='padding-left:3px'/></div><div class='nodeDiv' ><input type='text' name='nodename' value='"+nodename+"' id='nodename"+i+"' onclick='selectNode(this.id);' size='20' title='请输入节点名称' style='height:18px;'/></div></center></div>";
		 
		  i++;
		}
		function delline(){
			//var nodes = document.getElementsByName('lc');
			//var selectedid;
			var flag = false;
			//for(j=0;j<nodes.length;j++){
			//	if(nodes[j].checked){
			//		selectedid=nodes[j].id;
			//		selectedid = selectedid.substr(4);
			//		$('#row'+selectedid).remove();
			//		flag=true;
			//	}
		//	}
			 $('input[name="lc"]:checked').each(function(){    
               $(this).parent().parent().parent().parent().parent().remove();
               flag=true;
             }); 
			if(!flag){
				Dialog.alert('请先选择节点再删除！');
			}
		}
		
		function selectNode(data){
		//	alert(data);
	/*		var date = new Date();
			 var str=window.showModalDialog("../workflow/selectNode.action?random="+date.getTime(),"","dialogWidth=330px;dialogHeight=180px;dialogLeft=500px;dialogTop=300px;");
			if(str!=''&&str!=null){
				var ary = str.split("%");
				 var nodename =ary[1];
				 $('#'+data).val(nodename);
			}	
			*/
			  var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 300;
				diag.Height = 120;
				diag.Top=40;
				diag.Title = "选择节点";
				diag.URL = "<%=request.getContextPath()%>/workflow/selectNode.action?random="+date.getTime();
				diag.OKEvent = function(){
					var str=diag.innerFrame.contentWindow.doCheck();
					if(str != "" && str != null){
						var ary = str.split("%");
						 var nodename =ary[1];
						 $('#'+data).val(nodename);
					}
					diag.close();
				};	
				diag.show();
		}
		function doCheck(){
			var workflowId = document.getElementById('workflowId').value;
			var nodename = document.getElementsByName('nodename');
			if(workflowId==''||workflowId==null){
				Dialog.alert('请选择流程类型！');
				return;
			}
			for(var j =0;j<nodename.length;j++){
				if(nodename[j].value==''||nodename[j].value==null){
					Dialog.alert('请选择流程节点！');
					return;
				}
			}
			document.getElementById('workflowEditForm').action="update.action";
			document.getElementById('workflowEditForm').submit();
			
		}
		
		function checkFlow(){
			var nodeId = document.getElementById('workflowId');
			 var index= nodeId.selectedIndex;
	         var selectedVal=nodeId.options[index].value;
	         DWREngine.setAsync(false);
	         workflowService.getFlowNode(selectedVal,function(data){
	        	 if(data!=null&&data!=''&&data.length!=0){
	        		 $('.abc').remove();
	        		 for(var j=0;j<data.length;j++){
	        			 addline(data[j].NODENAME);
	        		 }
	        	 }else{

	        		 $('.abc').remove();
	        	 }
	         });
		}
	</script>
</head>
<body >
	<s:form id="workflowEditForm" theme="simple" method="post" >
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	  
					<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
					  <tr>
						<td height="26" colspan="2" align="center" bgcolor="#FEF3D5"><span class="STYLE1">流程设置</span></td>
					  </tr>
					  <tr>
						<td width="15%" height="20" align="right" bgcolor="#f8f8f8">流程类型： </td>
						<td width="85%" height="20" bgcolor="#FFFFFF" style="border:0px;"><select id="workflowId" name="workflowId" onchange="checkFlow();" style="width:155px">
							<option value="">--请选择--</option>
							<s:iterator value="workflowList" >
					        <option value="<s:property value='workflowId'/>"><s:property value='flowname'/></option>
				             </s:iterator>
						</select></td>	
					</tr>
					  <tr>
						<td  height="20" bgcolor="#FFFFFF" colspan="2">
							
						  <table id="chaxun_table" width="100%" border="1" align="top" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="margin-top:4px;border-collapse:collapse;">			  
										  <thead>
										  <tr>
											<th align="center" style="width:12%">设置流程节点</th>
											<td align="left" style="width:88%"><input name="submit1" type="button" class="newCreate" value="添加节点" onClick="addline('');"/>
                                            <input name="submit1" type="button" class="newCreate" value="删除节点" onclick="delline();"/></td>
										  </tr> </thead> 
										</table>
			 <table id="xinxiu_table" width="100%"  border="1" align="top" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;padding:0px;margin:0px">	
			        <tbody>
			         <tr style="padding:1px;margin:0px">										 
						 <td  style="border:0px;padding:1px; margin:0px">
                   <center> <div style="width:50px;height:28px;border:1px solid #529214;text-align:center;line-height:28px;font-size:12px;font-weight: bold; margin-top:4px;">开始</div> </center>
                          </td>
					  </tr></tbody>
                    <!--   <tr border="0px" id="row1" style="padding:1px;margin:0px">										 
						 <td style="border:0px;padding:1px;margin:0px" >
                          <div style="margin-bottom:2px;">
                            <center>
                                 <div style="margin-bottom:2px;"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/arrow.png" border="0"></div>
								 <div style="float:right;width:35%;height:20px;"></div>
                                 <div style="float:right;width:5%;height:20px;padding-top:5px;"> <input type="checkbox" name="lc" id="node1" style="padding-left:3px"/>  </div> 
                                 <div class="nodeDiv" >
                                   <input type="text" name="nodename" size="20" id="nodename1" onclick="selectNode(this.id);" style="height:18px;"/>
                                   </div>	
                             </center>
                           </div>	
                           </td>                            
					  </tr> -->
			 </table>
			</td>
			  </tr>
					  <tr>
						<td height="20" colspan="2" bgcolor="#FFFFFF">
						  <div align="center">
							<input type="button" name="save" value="保存"  class="buttons"   onclick="doCheck();"/>
                            <input type="button" name="back" value="返回"  class="buttons"  onClick="javascript:window.history.back(-1)"/>
						  </div>
						</td>
					  </tr>
					</table>
				</td>
		  </tr>
		  <tr >
			<td height="20" colspan="2" bgcolor="#FFFFFF" >
			  <div id="message_div" style="color:red;background: #FFEFD5;border:0px solid #FFDAB9;border-collapse: collapse;">
			     <s:actionmessage/>
		      </div>
			</td>
		  </tr>
		</table>


		</div>
		
		</s:form>
</body>
</html>
