<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/uploadify.css"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
    
    <script type="text/javascript">
        /* $(document).ready(function() {
            $("#uploadify").uploadify({
                'uploader'       : '<%=request.getContextPath()%>/scripts/uploadify.swf',
                'script'         : '<%=request.getContextPath()%>/servlet/Upload',//后台处理的请求
                'cancelImg'      : '<%=request.getContextPath()%>/images/cancel.png',
                'folder'         : 'uploads',//您想将文件保存到的路径
                'queueID'        : 'fileQueue',//与下面的id对应
                'queueSizeLimit' :1,
               
                //'fileDesc'    : 'rar文件或zip文件’,            
            	//'fileExt' : '*.rar;*.zip', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
                
                'auto'           : false,
                'multi'          : true,
                'simUploadLimit' : 2,
                'buttonText'     : 'BROWSE',
                
            });
        }); */
        
         $(document).ready(function() {
            $("#uploadify").uploadify({
            	'swf'      : '<%=request.getContextPath()%>/scripts/uploadify.swf',
				'script'         : '<%=request.getContextPath()%>/servlet/Upload',
				'queueID'        : 'fileQueue',
				'folder'         : 'uploads',
                'auto'           : false,
                'multi'          : true,
                'queueSizeLimit' : 5,//设置可以同时20个文件  
                'fileSizeLimit' : '100MB',
               	'fileTypeDesc' : 'Files',
               // 'fileTypeExts' : '*.gif; *.jpg; *.png;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.txt;*.zip;*.rar;*.pdf;',
               	'buttonText'      : '选择文件', 
               	'removeCompleted' : true, 
               	'removeTimeout'   : 0,  
               	'width':80,
               	'height':30,
                            
            });
        });
        </script> 
    
  </head>
  
  <body>
    <!-- <div id="fileQueue"></div>
        <input type="file" name="uploadify" id="uploadify" />
        <p>
        <a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
        <a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
        </p> -->
        <input type="file" name="uploadify" id="uploadify"/>	
		<div id="fileQueue"></div>
		<a href="javascript:$('#uploadify').uploadify('upload','*')" style="color:red;padding-top:30px;padding-left:10px" >上传文件</a>
  </body>
</html>
