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

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/uploadify.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/swfobject.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.uploadify.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">
	function doCheck(id){
		var title = document.getElementById("title").value;
		var company = document.getElementById("company").value;
		var subjectterm = document.getElementById("subjectterm").value;
		var mainsender = document.getElementById("mainsender").value;
		var copysender = document.getElementById("copysender").value;
		var docsize = document.getElementById("docsize").value;
		var doctype = document.getElementById("doctype").value;
		var secret = document.getElementById("secret").value;
		var urgency = document.getElementById("urgency").value;
		var timelimit = document.getElementById("timelimit").value;
		var copies = document.getElementById("copies").value;
		var remark = document.getElementById("remark").value;
		var reg = /^[1-9]+$/;
		
		if(title==''||title==null){
			Dialog.alert('发文标题不能为空！');
			return;
		}else if(title.length>50){
			Dialog.alert('发文标题不能超过50个字符！');
			return;
		}
		if(company!=''&&company!=null){
			if(company.length>25){
				Dialog.alert('公司名称不能超过25个字符！');
				return;
			}
		}
		if(subjectterm==''||subjectterm==null){
			Dialog.alert('主题词不能为空！');
			return;
		}else if(subjectterm.length>25){
			Dialog.alert('主题词不能超过25个字符！');
			return;
		}
		if(mainsender==''||mainsender==null){
			Dialog.alert('主送者不能为空！');
			return;
		}else if(mainsender.length>25){
			Dialog.alert('主送者不能超过25个字符！');
			return;
		}
		if(copysender!=''&&copysender!=null){
			if(copysender.length>25){
				Dialog.alert('抄送者不能超过25个字符！');
				return;
			}
		}
		if(docsize==''||docsize==null){
			Dialog.alert('发文字号不能为空！');
			return;
		}else if(docsize.length>25){
			Dialog.alert('发文字号不能超过25个字符！');
			return;
		}
		
		if(timelimit!=null&&timelimit!=''){
			if(timelimit.length>25){
				Dialog.alert('保存期限不能超过25个字符！');
				return;
			}
		}
		if(doctype==''||doctype==null){
			Dialog.alert('发文类型不能为空！');
			return;
		}

		if(copies!=null&&copies!=''){
			if(!reg.test(copies)){
				Dialog.alert('印发份数只能输入整数！');
				return;
			}
		}
		if(remark!=null&&remark!=''){
			if(remark.length>100){
				Dialog.alert('备注不能超过100个字符！');
				return;
			}
		}

		document.getElementById('reveiveFileForm').action="insert.action";
		document.getElementById('reveiveFileForm').submit();
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
  <s:form id="reveiveFileForm" method="post" theme="simple">
  <s:hidden name="flag" id="flag"></s:hidden>
         <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公文管理 &gt;&gt; <span class="STYLE1">发文管理</span>&gt;&gt; <span class="STYLE1">发文拟稿</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">发文拟稿</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
				<select id="flowId" name="flowId" style="width:155px">
					<option value="FWGL">收文管理</option>
				</select>
			</td>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">发文标题： </td>
            <td width="38%" height="20" bgcolor="#FFFFFF">
            	<input name="title" id="title" type="text"  value="" size="20"/>	
            </td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">来文机关： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="company" id="company" type="text"  value="" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">主题词： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="subjectterm" id="subjectterm" type="text" value="" size="20"/></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">主送者： </td>
            <td height="20" bgcolor="#FFFFFF"><input name="mainsender" id="mainsender" type="text" value="" size="20"/></td>
            <td height="20" align="right" bgcolor="#f8f8f8">抄送者：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="copysender" id="copysender" type="text" value="" size="20"/></td>
          </tr>   
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">发文类型： </td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="doctype" name="doctype" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="sendTypeList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==doctype'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>			
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">秘密等级： </td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="secret" name="secret" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="secretList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==secret'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>
          </tr>		  
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">紧急程度：</td>
            <td height="20" bgcolor="#FFFFFF">
				<select id="urgency" name="urgency" style="width:155px">
					<option value="">--请选择--</option>
					<s:iterator value="urgencyList" >
				     <option value="<s:property value='dataId'/>" <s:if test='dataId==urgency'>selected="true"</s:if>><s:property value='dataname'/></option>
				    </s:iterator>
				</select>
			</td>
            <td height="20" align="right" bgcolor="#f8f8f8">发文字号：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="docsize" id="docsize" type="text" value="" size="20" /></td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">保存期限：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="timelimit" id="timelimit" type="text" value="" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy',vel:'smonth',realDateFmt:'yyyy'})" size="20" class="Wdate" /></td>
            <td height="20" align="right" bgcolor="#f8f8f8">印发份数：</td>
            <td height="20" bgcolor="#FFFFFF"><input name="copies" id="copies" type="text" value="" size="20" /></td>
          </tr>  
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">备注：</td>
            <td height="100" bgcolor="#FFFFFF" colspan="3" ><textarea id='remark' style="width:98%;height:100%" name='remark'></textarea></td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">上传文件： </td>
            <td height="20" colspan="3" bgcolor="#FFFFFF">
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
            <td height="20" colspan="4" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="下一步"  class="buttons" onclick="doCheck();"/>
                <input type="reset" name="cancle" value="重置" class="buttons" />
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
