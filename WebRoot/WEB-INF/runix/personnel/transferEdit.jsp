<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<style>
	.inp{width:35%;}
</style>
	<script type="text/javascript">
			$(document).ready(function() {
				var attachname = $("#attachname").val();
				var attachpath = $("#attachpath").val();
				var names = attachname.split(";");
		 		var paths = attachpath.split(";");
		 		for(var i = 0;i<names.length-1;i++){
		 			$('<li></li>').appendTo("#files").html("<a href='<%=path%>"+paths[i]+"' class='fil'>"+names[i]+"</a>");
		 		}
			});
			function doCheck(){
				var personnelId  = document.getElementById('personnelId').value;
				var title = document.getElementById('title').value;
				var newdept = document.getElementById('newdept').value;
				var newposition = document.getElementById('newposition').value;
				if(newdept==''||newdept==null){
					Dialog.alert('请选择调到部门！');
					return;
				}
				if(newposition==''||newposition==null){
					Dialog.alert('请选择调到岗位！');
					return;
				}
				if(title==''||title==null){
					Dialog.alert('概述不能为空！');
					return;
				}else if(title.length>50){
					Dialog.alert('概述长度不能超过50个字符！');
					return;
				}
				document.getElementById('personnelForm').action='update.action?personnelId_q='+personnelId;
				document.getElementById('personnelForm').submit();
			}
		
		 //文件上传
		 $(document).ready(function() {
			 var index = 0;
	           $("#uploadify").uploadify({
							                'swf'      		     		: 	'<%=basePath%>/scripts/uploadify.swf',
							                'uploader'     	 		: 	'<%=basePath%>/servlet/Upload',//后台处理的请求	
							                'queueID'        		: 	'fileQueue',
							                'auto'               		: 	false,
							                'multi'              		: 	true,
							                'queueSizeLimit'  : 	5,//设置可以同时20个文件  
							                'fileSizeLimit'  		: 	'100MB',
							               	'fileTypeDesc'   	: 	'Files',
							             	'fileTypeExts'   		: 	'*.mp3;*.gif; *.jpg; *.png;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.txt;*.zip;*.rar;*.pdf;',
							               	'buttonText'      	: 	'选择文件', 
							               	'removeCompleted' : true, 
							               	'removeTimeout' : 0,  
							               	'width'					:80,
							               	'height'					:30,
							               	onSelect: function(file){
							                	var attachname = $("#attachname").val();
							                	var attachpath = $("#attachpath").val();
							                	var names = attachname.split(";");
							                	for(var i = 0;i<names.length;i++){
							                		if(names[i] == file.name){
							                			Dialog.alert(file.name+"该文件已存在！将保留最后一次上传的文件! ");
							                		}
							                	}
							          //      	$.ajax({
							          //  			url : "deleteOld.action",
							          //  			data : "attachpath=" + attachpath,
							          //  			type : "POST",
							          //  			dataType : "json",
							          //  			success : function(data) {
							          //  				/* Dialog.alert("原文件删除成功！"); */
							          //  			}
							          //  		});

							                	$("#files").empty();
							               		$("#attachname").val("");
							               		$("#attachpath").val("");
							                },
							                onUploadSuccess: function(file, data, response){
							                	if(data.indexOf("文件格式受限制") >=0){
							            			$('<li></li>').appendTo("#files").text(data).css("color","red");
							            			return false;
							            		}else{
								            		var resText = data.split("|");
								            		var attachname = $("#attachname").val();
								            		attachname = attachname+resText[1]+";";
								            		$("#attachname").val(attachname);
								            		var attachpath = $("#attachpath").val();
								            		attachpath = attachpath+resText[0];
								            		$("#attachpath").val(attachpath);
								            		index++;
								            		$('<li></li>').appendTo("#files").html("<span class='fil'>"+resText[1]+"</span><a href='javascript:void(0);' id='"+index+"' onclick='delFile("+index+");'><img src='<%=request.getContextPath()%>/scripts/uploadify-cancel.png' /></a>");
							            		}
							                },
						              
						             		onComplete: function(event, ID,  fileObj, response, data){
									               	var attachname = fileObj.name;
									               	$("#attachname").val(attachname);
									               	var attachpath = response ;
									               	$("#attachpath").val(attachpath);
									             }
							       		 });
						 		});
							 function delFile(ind){
							     	var attachname = $("#attachname").val();
							     	var names = attachname.split(";");
							     	var attachpath = $("#attachpath").val();
							     	var paths = attachpath.split(";");
							     	var delname = $("#"+ind).prev(".fil").html();
										$("#"+ind).parent().remove(); 
										for(var i = 0;i<names.length;i++){
											if(names[i] == delname){
												names.splice(i,1);
												paths.splice(i,1);
											}
										}
										attachname = names.toString().replace(/,/g,";");
										attachpath = paths.toString().replace(/,/g,";");
										$("#attachname").val(attachname);
										$("#attachpath").val(attachpath);		
						    	} 
		</script>
</head>
<body >
       <s:form id="personnelForm" action="personnelAction" method="post">
       <s:hidden name="flag" ></s:hidden>
       <s:hidden name="personnelId" id="personnelId" ></s:hidden>
        <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
         <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改调动信息</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" bgcolor="#FFFFFF">
                <select id="workflowId" name="personnel.workflowId" style="width:155px">
					<option value="DD">调动管理</option>
				</select></td>
            <td width="12%" align="right" bgcolor="#f8f8f8">申 请 者： </td>
            <td width="38%" bgcolor="#FFFFFF">
            	<s:property value="name"/>
            </td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">原 部 门： </td>
            <td bgcolor="#FFFFFF"><s:property value="dept"/></td>
			<td width="12%" align="right" bgcolor="#f8f8f8">原 岗 位： </td>
            <td bgcolor="#FFFFFF"><s:property value="oldpositioln"/></td>
          </tr> 
		  <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">调到部门： </td>
            <td bgcolor="#FFFFFF">
            	<select id="newdept" name="newdept" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="depts" >
						<option value="<s:property value='deptId'/>" <s:if test="deptId==newdept">selected="true"</s:if>><s:property value='deptname'/></option>
					</s:iterator>
				</select>
            </td>
			<td width="12%" align="right" bgcolor="#f8f8f8">调到岗位： </td>
            <td bgcolor="#FFFFFF">
            	<select id="newposition" name="newposition" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="positionList" >
						<option value="<s:property value='positionId'/>" <s:if test="positionId==newposition">selected="true"</s:if>><s:property value='postname'/></option>
					</s:iterator>
				</select>
            </td>
          </tr> 
		  <tr>
			<td width="12%" height="20" align="right" bgcolor="#f8f8f8">申请概述： </td>
            <td bgcolor="#FFFFFF" colspan="3"><input type="text" id="title" name="title" size="30" value="<s:property value='title'/>"/></td>
            
          </tr> 
          <tr>
          <td width="12%" align="right" bgcolor="#f8f8f8">附　　件： </td>
            <td bgcolor="#FFFFFF" colspan="3">
            	 <input type="hidden" name="attachname" id="attachname"  value="<s:property value='attachname'/>"/>
            	<input type="hidden" name="atachpath" id="attachpath"  value="<s:property value='atachpath'/>" />
            	<div id="fileQueue"></div>
            	<input type="file" name="uploadify" id="uploadify" />
        			<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
			        <ol id="files"></ol>
            </td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">编辑内容： </td>
            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF"><textarea name="content" id="content" cols ="60" rows = "10"><s:property value='content'/></textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
	            <div align="center">
	                <input type="button" class="buttons" name="save" value="下一步"  onclick="doCheck();"/>
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
</s:form>              

</body>
</html>