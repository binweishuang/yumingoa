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

	<meta charset="utf-8"></meta>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<style>
		#xinxiu_table td{
			text-align:center;
		}
		 .newCreate{ border:1px solid #333333; width:60px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
	var editer;
	//配置编辑器
	KindEditor.ready(function(K){
		editer=K.create("#content");
	});
		function doCheck(id){
			editer.sync();
			var secret = document.getElementById('secret').value;
			var partnum =  document.getElementById('partnum').value;
			var annual =  document.getElementById('annual').value;
			var filesize =  document.getElementById('filesize').value;
			var pagenum = document.getElementById('pagenum').value;
			var filetype = document.getElementById('filetype').value;
			var dossierId = document.getElementById('dossierId').value;
			var archivetype = document.getElementById('archivetype').value;
			var title = document.getElementById('title').value;
			var remark =  document.getElementById('remark').value;
			
			var reg = /^[1-9]+$/;

			
			if(secret==''&&secret==null){
				Dialog.alert('请选择秘密等级！');
				return;
			}
			if(partnum!=''||partnum!=null){
				 if(partnum.length>25){
					Dialog.alert('件号不能超过25个字符！');
					return;
				 }
			}
			if(annual==''||annual==null){
				Dialog.alert('档案年度不能为空！');
				return;
			}
			if(filesize==''||filesize==null){
				Dialog.alert('文件字号不能为空！');
				return;
			}else if(filesize.length>25){
				Dialog.alert('文件字号不能超过25个字符！');
				return;
			}
			if(pagenum!=''||pagenum!=null){
			   if(!reg.test(pagenum)){
				Dialog.alert('页号只能输入整数！');
				return;
			   }
			}
			if(filetype==''&&filetype==null){
				Dialog.alert('请选择文件类型！');
				return;
			}
			if(dossierId==''&&dossierId==null){
				Dialog.alert('请选择案卷！');
				return;
			}
			if(archivetype==''&&archivetype==null){
				Dialog.alert('请选择文种！');
				return;
			}
			
			if(title==''||title==null){
				Dialog.alert('文件标题不能为空！');
				return;
			}else if(title.length>50){
				Dialog.alert('文件标题不能超过50个字符！');
				return;
			}
			if(remark!=''&&remark!=null){
				if(remark.length>250){
					Dialog.alert('备注不能超过250个字符！');
					return;
				}
				
			}
			if(id=="filling"){
				Dialog.confirm("归档后该条信息不可删除，确定归档吗？",function(){
					document.getElementById('archiveAddForm').action="insert.action?flag="+id;
					document.getElementById('archiveAddForm').submit();
				});
			}else if(id=="save"){
				document.getElementById('archiveAddForm').action="insert.action?flag="+id;
				document.getElementById('archiveAddForm').submit();
			}
	}
		<%-- //文件上传
		$(document).ready(function() {
		 	var index = 0;
	        $("#uploadify").uploadify({
	                'swf'      		 : '<%=basePath%>/scripts/uploadify.swf',
	                'uploader'     	 : '<%=basePath%>/servlet/Upload',
	                'queueID'        : 'fileQueue',
	                'auto'           : false,
	                'multi'          : true,
	                'queueSizeLimit' : 5,//设置可以同时20个文件  
	                'fileSizeLimit'  : '100MB',
	               	'fileTypeDesc'   : 'Files',
	             	'fileTypeExts'   : '*.gif; *.jpg; *.png;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.txt;*.zip;*.rar;*.pdf;',
	               	'buttonText'      : '请选择文件', 
	               	'removeCompleted' : true, 
	               	'removeTimeout'   : 0,  
	               	'width':80,
	               	'height':30,
	               	onSelect: function(file){
	                	var attachname = $("#attachname").val();
	                	var names = attachname.split(";");
	                	for(var i = 0;i<names.length;i++){
	                		if(names[i] == file.name){
	                			Dialog.alert(file.name+"该文件已存在！");
	                			$("#uploadstart").attr("href","javascript:checkname('"+file.name+"');");
	                		}
	                	}
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
		            		//$('<li></li>').appendTo("#files").text(resText[1]);
		            		$('<li></li>').appendTo("#files").html("<span class='fil'>"+resText[1]+"</span><a href='javascript:void(0);' id='"+index+"' onclick='delFile("+index+");'><img src='<%=request.getContextPath()%>/scripts/uploadify-cancel.png' /></a>");
	            		}
	                },
	           
	           onComplete: function(event, ID,  fileObj, response, data){
	           	//alert("文件:" + fileObj.name + "  路径" + fileObj.filePath + "  大小" + fileObj.size + "上传成功");
	           	//alert("上传后的文件名:" + value + "");
	           	var attachname = fileObj.name;
	           	$("#attachname").val(attachname);
	           	var attachpath = response ;
	           	$("#attachpath").val(attachpath);
	           }
	     });
	});
	 function delFile(ind){
	 	//alert(ind);
	 	var attachname = $("#attachname").val();
	 	var names = attachname.split(";");
	 	var attachpath = $("#attachpath").val();
	 	var paths = attachpath.split(";");
	 	var delname = $("#"+ind).prev(".fil").html();
	 	//alert("delname=="+delname);
			$("#"+ind).parent().remove(); 
			for(var i = 0;i<names.length;i++){
				//alert("names[i]=="+names[i]);
				if(names[i] == delname){
					names.splice(i,1);
					paths.splice(i,1);
				}
			}
			attachname = names.toString().replace(/,/g,";");
			attachpath = paths.toString().replace(/,/g,";");
			$("#attachname").val(attachname);
			$("#attachpath").val(attachpath);		
	 }  --%>
	</script>

</head>
<body >
	<s:form action="archiveAddAction" id="archiveAddForm" theme="simple"  method="post">
            <div  style="width:99%;overflow-y:auto;white-space:nowrap;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加公文档案</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">责任者： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF"><s:property value="author"/></td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="secret" name="secret" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="secretList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==secret'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>
          </tr>

          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">件    号： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="partnum" id="partnum" type="text" value="" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">档案年度：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="annual" id="annual" type="text" value="" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy',vel:'smonth',realDateFmt:'yyyy'})" class="Wdate"/></td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">文件字号：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="filesize" id="filesize" type="text" value="" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">页    号：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="pagenum" id="pagenum" type="text" value="" size="20" /></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">文件类型：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="filetype" name="filetype" style="width:155px">
					<option value="">--请选择--</option>
					<option value="1">收文</option>
					<option value="2">发文</option>
					<option value="3">直接归档</option>
				</select>
			</td>  
			<td height="20" align="right" bgcolor="#f8f8f8">案    卷：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="dossierId" name="dossierId" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="dossierList" >
				     <option value="<s:property value='DOSSIER_ID'/>" <s:if test='DOSSIER_ID==dossierId'>selected="true"</s:if>><s:property value='TITLLE'/></option>
				    </s:iterator>
				</select>
			</td> 			
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">档案文种：</td>
            <td height="20" bgcolor="#FFFFFF" >
				<select id="archivetype" name="archivetype" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="archivetypeList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==archivetype'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>  
			<td height="20" align="right" bgcolor="#f8f8f8">文件标题：</td>
            <td height="20" bgcolor="#FFFFFF" ><input name="title" id="title" type="text" value="" size="20"/></td> 			
          </tr>
		  <!-- <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">附   件：</td>
            <td height="20" bgcolor="#FFFFFF" colspan="3">
                 <input type="hidden" name="attachname" id="attachname"/>
            	<input type="hidden" name="attachpath" id="attachpath"/>
            	<div id="fileQueue"></div>
            	<input type="file" name="uploadify" id="uploadify" />
        			<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
			        <ol id="files"></ol>
            </td>
          </tr> -->
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备   注：</td>
            <td height="50" bgcolor="#FFFFFF" colspan="3"><textarea id='remark' style="width:99%;height:100%" name='remark'></textarea></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">正   文：</td>
            <td height="260" bgcolor="#FFFFFF" colspan="3"><textarea id='content' style="width:100%;height:300px;" name='content'></textarea></td>
          </tr>
		       
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="暂存" class="buttons" onclick="doCheck('save');"/>
				<input type="button" name="save" value="归档"  class="buttons" onclick="doCheck('filling');"/>
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