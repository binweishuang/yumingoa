<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>添加新请示</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	
	<script>
		//配置编辑器
		var editor;
		KindEditor.ready(function(K){
			editor = K.create("#content");//创建编辑器
		});
		
		 //上传多个附件
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
	             	'fileTypeExts'   : '*.gif; *.jpg; *.png;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.txt;*.zip;*.rar;*.pdf;',
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
	                }
              });
        });
        
        function checkname(filename){
        	Dialog.alert(filename+"该文件已存在！");
        }
        
        //删除已上传的附件
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
        
        function doCheck(status){
        	var type = $("#type").val();
        	var secret = $("#secret").val();
        	var urgency = $("#urgency").val();
        	var title = $("#title").val();
        	var money = $("#money").val();
        	
			editor.sync();//获取编辑器中的值
			
			if(type == "0"){
				Dialog.alert("请选择请示类型！");
				return false;
			}
			if(secret == "0"){
				Dialog.alert("请选择秘密等级！");
				return false;
			}
			if(urgency == "0"){
				Dialog.alert("请选择紧急程度！");
				return false;
			}
			if(title == null || title == ""){
				Dialog.alert("请填写请示标题！");
				return false;
			}
			if(money == null || money == ""){
				Dialog.alert("请填写请示金额！");
				return false;
			}else if(!/^[0-9]/.test(money)){
				Dialog.alert("请示金额填写错误！");
				return false;
			}
			$("#status").val(status);
			$("#form1").submit();
			return true;
		}
	</script>
</head>
<body >
	<form action="<%=path%>/apply/insert.action" method="post" id="form1" enctype="multipart/form-data">
	<div  style="width:99%; margin:0 auto; background-color:#fff;">        
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
        		<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 行政办公 &gt;&gt; <span class="STYLE1">请示管理</span>&nbsp;&gt;&gt; <span class="STYLE1">添加请示</span></td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加新请示</span></td>
			          </tr>
			          <tr>
			          	<td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
							请示管理<input type="hidden" id="workflowId" name="workflowId" value="APPLY" />
						</td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">请示类型： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
							<select id="type" name="type" style="width:155px"> 
								<option value="0">--请选择--</option>
								<s:iterator value="typeList">
								<option value="${dataId}">${dataname}</option>
								</s:iterator>
							</select>
						</td>
			          </tr>
			          <tr>
			          	<td width="12%" height="20" align="right" bgcolor="#f8f8f8">请示人： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
							${user.name}<input type="hidden" name="applicant" value="${user.userId}" />
							<input type="hidden" name="department" value="${user.deptId}" />
						</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
			            <td height="20" bgcolor="#FFFFFF">
							<select id="secret" name="secret" style="width:155px">
								<option value="0">--请选择--</option>
								<s:iterator value="secretList">
								<option value="${dataId}">${dataname}</option>
								</s:iterator>
							</select>
						</td>
			          </tr>
			          <tr>
			          	<td height="20" align="right" bgcolor="#f8f8f8">请示金额：</td>
			            <td height="20" bgcolor="#FFFFFF"><input name="money" id="money" type="text" size="10"/>元</td>
			            <td height="20" align="right" bgcolor="#f8f8f8">紧急程度： </td>
			            <td height="20" bgcolor="#FFFFFF">
							<select id="urgency" name="urgency" style="width:155px">
								<option value="0">--请选择--</option>
								<s:iterator value="urgencyList">
								<option value="${dataId}">${dataname}</option>
								</s:iterator>
							</select>
						</td>
			          </tr>
			          <tr>
			          	 <td height="20" align="right" bgcolor="#f8f8f8">请示标题： </td>
			             <td height="20" bgcolor="#FFFFFF" colspan="3">
			             	<input name="title" id="title" type="text" value="" size="30"/>
			             </td>
			          </tr>         
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">附    件： </td>
			            <td height="20" bgcolor="#FFFFFF" colspan="3">
			            	<input type="hidden" name="attachname" id="attachname"/>
			            	<input type="hidden" name="attachpath" id="attachpath"/>
			            	<div id="fileQueue"></div>
			            	<input type="file" name="uploadify" id="uploadify"/>
			        			<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			        			<a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
			        		<ol id="files"></ol>
			            </td>
			          </tr>
					  <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">内    容：</td>
			            <td height="260" bgcolor="#FFFFFF" colspan="3">
			            	<textarea id='content' style="width:100%;height:260px;" name='content'></textarea>
			            	<input type="hidden" name="status" id="status" />
			            </td>
			          </tr>
					       
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF">
			            	<div align="center">
				                <input type="button" class="buttons" name="save" value="下一步"  onclick="return doCheck(0);"/>
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
	</form>
</body>
</html>
