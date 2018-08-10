<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Runix - the Runix Admin Template</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css"></link>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
<script type="text/javascript">
	var editer;
	//配置编辑器
	  KindEditor.ready(function(K){
	  	editer=K.create("#content");
	  });
		function selectReporter(){
			var date = new Date();
			//弹出选择上报对象窗口
			//var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			  var diag = new Dialog();
				diag.Width  = 700;
				diag.Height = 300;
				diag.Top=40;
				diag.Title = "选择上报对象";
				diag.URL = "<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime();
				diag.OKEvent = function(){
					var str=diag.innerFrame.contentWindow.doSelect();
					 if(str!='' && str!=null){
						   var arr = str.split("%");
						   $("#reportTo").val(arr[0]);
							$("#reportToId").val(arr[1]);
					   }
					diag.close();
				};	
				diag.show();
		}
		//选择执行对象
		function selectExecuter(){
			var date = new Date();
			//弹出选择上报对象窗口
	//		var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random=" 
	//					+ date.getTime(), "",
	//					"dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
	//	if (str != "" && str != null) {
	//		var arr = str.split("%");
	//		$("#executer").val(arr[0]);
	//		$("#executerId").val(arr[1]);
	//	}
	        var executer = $("#executer").val();
	        var executerid = $("#executerid").val();
			var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择执行对象";
			diag.URL = "<%=request.getContextPath()%>/selectPeople/query.action?random="+date.getTime()+"&people="+executer+"&peopleId="+executerid;
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				 if(str!='' && str!=null){
					   var arr = str.split("%");
					   $("#executer").val(arr[0]);
						$("#executerid").val(arr[1]);
				   }
				diag.close();
			};	
			diag.show();
	}

	//文件上传
	$(document).ready(function() {
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
            	var index=0;
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
			//'fileDesc'    : 'rar文件或zip文件’,            
			//'fileExt' : '*.rar;*.zip', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
			onComplete : function(event, ID, fileObj, response, data) {
				//alert("文件:" + fileObj.name + "  路径: " + fileObj.filePath + "  大小: " + fileObj.size + "上传成功");
				//alert("上传后的文件名:" + value + "");
				var attachname = fileObj.name;
				$("#attachname").val(attachname);
				var attachpath = response;
				$("#attachpath").val(attachpath);
			},
		/* onUploadError: function(event, queueID, fileObj) {  
		
				 alert("文件:" + fileObj.name + "上传失败");  
		
		},  
		onCancel: function(event, queueID, fileObj){  
		
				 alert("取消了" + fileObj.name);  
		}  */
		});
	});

	function doCheck() {
		editer.sync();
		var title = $("#title").val();
		
		var reportTo = $("#reportTo").val();
		var executer = $("#executer").val();
		var timelimit = $("#timelimit").val();
		var start = $("#startDate").val();
		var end = $("#endDate").val();

		if (title.trim().length == 0) {
			Dialog.alert("请填写任务标题");
		}  else if(reportTo.trim().length == 0){
			Dialog.alert("请选择汇报对象");
		} else if(start.length == 0 || end.length == 0){
			Dialog.alert("请选择任务日期");
		} else if(executer.trim().length == 0){
			Dialog.alert("请选择执行人");
		} else if(timelimit.trim().length == 0){
			Dialog.alert("请填写任务时限");
		}
		
		if (title.trim().length != 0 
				&& reportTo.trim().length != 0 && start.length != 0
				&& end.length != 0 && executer.trim().length != 0
				&& timelimit.trim().length != 0) {
			document.form.submit();
		}
	}
	
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
<body>
	<div style="width:99%;margin:0 auto; background-color:#fff;">
		<form id="form" name="form" action="insert.action" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公
									&gt;&gt; <span class="STYLE1">任务管理</span>&gt;&gt; <span
									class="STYLE1">下达任务</span>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" cellpadding="3"
							cellspacing="1" bgcolor="#CCCCCC">
							<tr>
								<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span
									class="STYLE1">下达任务</span></td>
							</tr>
							<tr>
								<td width="12%" height="20" align="right" bgcolor="#f8f8f8">任务标题：
								</td>
								<td width="85%" height="20" bgcolor="#FFFFFF"><input
									type="text" id="title" name="title" />
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">汇报对象：</td>
								<td height="20" bgcolor="#FFFFFF">
									<input type="text" id="reportTo"  onclick="selectReporter();" />
									<input type="hidden" id="reportToId" name="reportTo" />
								</td>
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">日期：</td>
								<td height="20" bgcolor="#FFFFFF"><input type="text"
									id="startDate" name="starttime"
									onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')||\'2020-01-01\'}'})"
									class="Wdate" /> 至 <input type="text" id="endDate"
									name="endtime"
									onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"
									class="Wdate" /></td>
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">附 件：</td>
								<td height="20" bgcolor="#FFFFFF">
									<input type="hidden" name="attachname" id="attachname" />
									<input type="hidden" name="attachpath" id="attachpath" />
									<div id="fileQueue"></div>
									<input type="file" name="uploadify" id="uploadify" />
										<a id="uploadstart" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			        					<a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
			        					<ol id="files"></ol>
								</td>
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">执行人：</td>
								<td height="20" bgcolor="#FFFFFF">
									<input type="text" id="executer"  onclick="selectExecuter();" name="executer" />
									<input type="hidden" id="executerid" name="executerid" />
								</td>
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">任务时限：</td>
								<td height="20" bgcolor="#FFFFFF">
									<input type="text" id="timelimit" name="timelimit" />
								</td>
							</tr>
							<tr>
								<td height="20" align="right" bgcolor="#f8f8f8">内 容：</td>
								<td bgcolor="#FFFFFF">
									<textarea id="content" name="content" style="width: 100%;height: 300px"></textarea>
								</td>
							</tr>
							<tr>
								<td height="20" colspan="4" bgcolor="#FFFFFF">
									<div align="center">
										<input type="button" class="buttons" name="save" value="保存" onclick="doCheck();" />
										<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)" />
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