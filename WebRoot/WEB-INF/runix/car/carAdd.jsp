
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
	.inp{width:35%;}
</style>
<script>

	function doCheck(){
		var cartype= document.getElementById('cartype').value;
		var capacity = document.getElementById('capacity').value;
		var license = document.getElementById('license').value;
		var passengernum = document.getElementById('passengernum').value;
		var drivenkm = document.getElementById('drivenkm').value;
		var driver = document.getElementById('driver').value;
		var description = document.getElementById('description').value;
		var reg = /^[0-9]+$/;
		if(cartype==''||cartype==null){
			Dialog.alert('车型不能为空！');
			return;
		}else if(cartype.length>25){
			Dialog.alert('车型不能超过25个字符！');
			return;
		}
		if(capacity==''||capacity==null){
			Dialog.alert('排量不能为空！');
			return;
		}else if(capacity.length>25){
			Dialog.alert('排量不能超过25个字符！');
			return;
		}
		if(license==''||license==null){
			Dialog.alert('牌照不能为空！');
			return;
		}else if(license.length>25){
			Dialog.alert('牌照不能超过25个字符！');
			return;
		}
		if(passengernum!=''&&passengernum!=null){
			
			if(!reg.test(passengernum)){
				Dialog.alert('载客数量只能输入数字！');
				return;
			}
		}
		if(drivenkm!=''&&drivenkm!=null){
			
			if(!reg.test(drivenkm)){
				Dialog.alert('行驶公里只能输入数字！');
				return;
			}
		}
		if(driver==''||driver==null){
			Dialog.alert('负责司机不能为空！');
			return;
		}
		if(description!=''&&description!=null){
			if(description.length>250){
				Dialog.alert('车辆描述不能超过250个字符！');
				return;
			}
			
			
		}
		document.getElementById('carAddForm').action="insert.action";
		document.getElementById('carAddForm').submit();
	}
	
	function selectPerson(){
/*		var date = new Date();
		var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
		if(str != "" && str != null){
			var arr = str.split("%");
			$("#driver").val(arr[0]);
			
		}
	*/	
		   var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择人员";
			diag.URL = "<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime();
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				if(str != "" && str != null){
					var arr = str.split("%");
					$("#driver").val(arr[0]);
				}
				diag.close();
			};	
			diag.show();
	}
	
	//文件上传
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
 } 
</script>
</head>
<body >
	<form id="carAddForm" action="carAction" method="post" class="form">
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
		       <tr>
		        <td>
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加车辆信息</span></td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">车　　型： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="cartype" name="cartype" type="text"  value="" size="20" class="inp"/>
			            	<span style="margin-left:10px;"></span>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">排　　量： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="capacity" name="capacity" type="text"  value="" size="20"  class="inp"/>	
			            	<span style="margin-left:10px;"></span>
			            </td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">牌　　照： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="license" name="license" type="text"  value="" size="20"  class="inp"/>
			            	<span style="margin-left:10px;"></span>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">载客数量： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="passengernum" name="passengernum" type="text" value="" size="20"  class="inp"/>
			            	<span style="margin-left:10px;"></span>
			            </td>
			          </tr>
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">年检时间： </td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="checktime" name="checktime" type="text" size="20"
			            	value=""
			            	onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})"  class="Wdate"
											onblur="check_time();"/>
							<span style="margin-left:10px;"></span>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">投保时间：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="insuretime" name="insuretime" type="text" size="20" 
			            	value=""
							onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})"  class="Wdate"
											/>
							<span style="margin-left:10px;"></span>
			            </td>
			          </tr>         
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">购买时间：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="buytime" name="buytime" type="text" size="20"
			            	value=""
							onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})"  class="Wdate"
											/>
							<span style="margin-left:10px;"></span>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">行驶公里：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			                <input id="drivenkm" name="drivenkm" type="text" size="20"  class="inp"/>
			                <span style="margin-left:10px;"></span>
			            </td>
			          </tr>
					  <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">负责司机：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input id="driver" name="driver" type="text" value="" size="20" onclick="selectPerson();" class="inp"/>
			            	<span style="margin-left:10px;"></span>
			            </td>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">是否可用：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF">
			            	<input name="usable" type="radio" value="1"  checked="checked"/>
			            	  	是
			              	<input type="radio" name="usable" value="0" />
			             		 否
			            </td>
			          </tr>
					   <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">附　　件：</td>
			            <td width="38%" height="20" bgcolor="#FFFFFF" colspan="3">
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
			            <td width="12%"  height="20" align="right" bgcolor="#f8f8f8">车辆描述： </td>
			            <td width="38%" height="20" colspan="3" bgcolor="#FFFFFF">
			            	<textarea id="description" name="description" cols ="50" rows = "5"></textarea>
			            </td>
			          </tr>     
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
			                <input type="button" name="save" value="保存" class="buttons" onclick="doCheck();"/>
			                <input type="reset" name="cancle" class="buttons" value="重置" />
							<input type="button" name="back" class="buttons" value="返回" onclick="javascript:window.history.back(-1)"/>
			            </div></td>
			          </tr>
			        </table>
				</td>
  			</tr>
		</table>
	</div>
</form>
</body>
</html>