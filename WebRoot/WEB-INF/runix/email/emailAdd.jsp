<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Runix - the Runix Admin Template</title>
	<meta charset="utf-8">
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">
		var editer;
		//配置编辑器
		  KindEditor.ready(function(K){
		  	editer=K.create("#content");
		  });
		function showselect(){
			var date = new Date();
		 	var receivePeoples = document.getElementById('receivePeoples').value;
		  	var receivePeoplesId = document.getElementById('receivePeoplesId').value;	
		   var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择收件人";
			diag.URL = "<%=request.getContextPath()%>/selectPeople/query.action?random="+date.getTime()+"&people="+receivePeoples+"&peopleId="+receivePeoplesId;
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				 if(str!='' && str!=null){
					   var pple = str.split("%");
					   document.getElementById('receivePeoples').value=pple[0];
					   document.getElementById('receivePeoplesId').value=pple[1];
				   }
				diag.close();
			};	
			diag.show();
	}
	
	function doCheck(id){
		editer.sync();	//获取编辑器的值
		var receivePeoples = document.getElementById("receivePeoples").value;
		var title = document.getElementById("title").value;
		if(receivePeoples==''||receivePeoples==null){
			Dialog.alert('收件人不能为空！');
			return;
		}
		if(title==''||title==null){
			Dialog.alert('邮件主题不能为空！');
			return;
		}else if(title.length>50){
			Dialog.alert('邮件主题不能超过50个字符！');
			return;
		}

		document.getElementById('emailAddForm').action="insert.action?act="+id;
		document.getElementById('emailAddForm').submit();
	}
	 //文件上传
	 $(document).ready(function() {
		 var index = 0;
            $("#uploadify").uploadify({
	                'swf'      		 : '<%=basePath%>/scripts/uploadify.swf',
	                'uploader'     	 : '<%=basePath%>/servlet/Upload',//后台处理的请求	
	                'queueID'        : 'fileQueue',
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
	     } 
	</script>
</head>
<body >
  <s:form id="emailAddForm" method="post" theme="simple">
  <s:hidden name="receivePeoplesId" id="receivePeoplesId"></s:hidden>
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">内部邮件</span>&gt;&gt; <span class="STYLE1">写邮件</span></td>
				</tr>
			</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">填写新邮件</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">收件人： </td>
            <td width="85%" height="20" bgcolor="#FFFFFF"><input name="receivePeoples" id="receivePeoples" type="text" onclick="showselect()" value="" size="50" readonly/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">邮件主题： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="title" id="title" type="text"  value="" size="50"/></td>
           
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">附    件： </td>
            <td height="20" bgcolor="#FFFFFF">
                <input type="hidden" name="attachname" id="attachname"/>
            	<input type="hidden" name="attachpath" id="attachpath"/>
            	<div id="fileQueue"></div>
            	<input type="file" name="uploadify" id="uploadify" />
        			<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
			        <ol id="files"></ol>
            </td>
          </tr>         
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
            <td height="300" bgcolor="#FFFFFF" valign="middle"><textarea id='content' style="width:100%;height:300px;" name='content'></textarea></td>
          </tr>
		     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center"> 
				<input type="button" name="send" value="发送" class="buttons" onclick="doCheck('send');" />
				<input type="button" name="save" value="保存" class="buttons" onclick="doCheck('save');"/>
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
