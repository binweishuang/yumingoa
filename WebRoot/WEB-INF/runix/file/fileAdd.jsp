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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css"></link>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
    <script type="text/javascript">
    	var deletePath = null;
	    $(document).ready(function() {
	    	deletePath = $("#deletePath").val();
			var index = 0;
			var filePath = "";
	        var fileName = "";
			var fileSize = "";
			var attachname = $("#attachname").val();
			var attachpath = $("#attachpath").val();
			var size = $("#filesize").val();
	         $("#uploadify").uploadify({
		          'swf'      		 : '<%=basePath%>/scripts/uploadify.swf',
		           'uploader'    : '<%=basePath%>/servlet/Upload',//后台处理的请求	
		          'queueID'      : 'fileQueue',
		          'auto'           : false,
		          'multi'          : true,
		          'queueSizeLimit' : 5,//设置可以同时20个文件  
		          'fileSizeLimit'  : '100MB',
		      	  'fileTypeDesc'   : 'Files',
		          'fileTypeExts'   : '*.mp3;*.gif; *.jpg; *.png;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.txt;*.zip;*.rar;*.pdf;',
		          'buttonText'      : '选择文件', 
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
		          onUploadSuccess:function(file, data, response){	//data后台Java处理文件的返回值
						$("#filesize").val(size);
						if(data.indexOf("文件格式受限制") >=0){
							$('<li></li>').appendTo("#files").text(data).css("color","red");
						    return false;
						}else{
							var resText = data.split("|");
							filePath = filePath + attachpath+resText[0];
							fileName = fileName + attachname+resText[1]+";";
							fileSize = fileSize + size+resText[2];
							index++;
							$('<li></li>').appendTo("#files").html("<span class='fil'>"+resText[1]+"</span><a href='javascript:void(0);' onclick='delFile("+index+");'><img src='<%=request.getContextPath()%>/scripts/uploadify-cancel.png' /></a>" + "<input type='hidden' id = '"+ index+"' value = '"+ resText[0] +"'></input>");
						}
						$("#attachname").val(fileName);
						$("#attachpath").val(filePath);
						$("#filesize").val(fileSize);
					}, 
					onComplete: function(event, ID,  fileObj, response, data){
						//event:事件对象
						//ID：上传队列中唯一标识
						//fileObj：一个包含文件信息的对象，【name】文件名称，【filePath】已上传文件在服务器的路径，【size】文件大小，【type】文件扩展名，以.开始
						//response：由后台脚本返回的文本值
					}
				});
			 });
	    
		 function delFile(ind){
			var path = document.getElementById(ind).value;
			$.ajax({
					url : "deleteFile.action",
					data : "OldFilePath=" + path,
					type : "POST",
					dataType : "json",
					success : function(data) {
						deletePath = deletePath + path;
						$("#deletePath").val(deletePath);
					}
				});
			var attachname = $("#attachname").val();
			var attachpath = $("#attachpath").val();
	    	var names = attachname.split(";");
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
		 
		 function doCheck(){
	 		document.form.submit();
	 	}
	</script>
</head>
<body>
      <div  style="width:99%; margin:0 auto; background-color:#fff;">
       <form id="form" name="form" action="insert.action" method="post">
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">网络硬盘</span>&nbsp;&gt;&gt; 
							<span class="STYLE1">上传文件</span>
						</td>
					  </tr>
			</table>
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">上传文件</span></td>
          </tr>
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">上传文件： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<div id="upload">
	            	<input type="hidden" name="netdisk.filename" id="attachname"/>
	            	<input type="hidden" name="netdisk.filepath" id="attachpath"/>
	            	<input type="hidden" name="netdisk.filesize" id="filesize"/>
	            	<input type= "hidden" name = "deletePath" id = "deletePath" value = ""/>
	            	<div id="fileQueue"></div>
	            	<input type="file" name="uploadify" id="uploadify" />
	        			<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
				        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
				        <ol id="files"></ol>
        		</div>
        		<div id="file">
        		</div>
            </td>
          </tr>
		   <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否共享： </td>
            <td height="20" bgcolor="#FFFFFF">
				是：<input name="netdisk.publicity" type="radio" size="20" checked="checked" value="1"/>
				否：<input name="netdisk.publicity" type="radio" size="20" value="0"/>
				<input type="hidden" name="netdisk.folderId" id="folderId" value="<s:property value='folderId'/>"/>
			</td>
          </tr>
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
				<div align="center">
					<input type="button" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
					<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
            	</div>
			</td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</form>              
</div>
</body>
</html>