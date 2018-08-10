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

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<style>
		#con { font-size:12px; width:100%;height:100%;}
		#tags { padding:0; margin:0; width:800px; height:26px;}
		#tags li { float:left; padding-left:10px; padding-right:10px; margin-right:2px;height:26px; list-style-type:none; background:url(<%=SystemConfig.FRAMEWORK_PATH%>/img/tagleft.gif) no-repeat left bottom;}
		#tags li A { padding:0 5px; float:left; color:#fff; line-height:26px; height:26px; text-decoration:none; }
		#tags li.selectTag { background-position:left top; margin-bottom:0px; position:relative; height:26px}
		#tags li.selectTag A {color:#000; line-height: 25px; height: 25px}
		#tagContent { background-color: #fff ;width:100%;height:100%}
		.tagContent { padding:0px; display:none; width:100%; color: #474747; height:100%; text-align:center }
		
		#tagContent div.selectTag {display: block}
		.td_bg_gray{background:#b2b2b2}
	</style>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
				//获取对象属性兼容方法
		 function getObject(objectId) {
		    if(document.getElementById && document.getElementById(objectId)) {
			// W3C DOM
			return document.getElementById(objectId);
		    } else if (document.all && document.all(objectId)) {
			// MSIE 4 DOM
			return document.all(objectId);
		    } else if (document.layers && document.layers[objectId]) {
			// NN 4 DOM.. note: this won't find nested layers
			return document.layers[objectId];
		    } else {
			return false;
		    }
		}
		
		var preClassName = "man_nav_1";
		function changeIframe(flag,classNa){
		    if(preClassName != ""){
					getObject(preClassName).className="bg_image";
				}
		
			if(getObject(classNa).className == "bg_image"){
				getObject(classNa).className="selectTag";	
				preClassName = classNa;
			}
			 document.getElementById("flag").value=flag;
			 var completion = document.getElementById("completion").value;
			 var reportTo = document.getElementById("reportTo").value;
			 var executer = document.getElementById("executer").value;
				document.getElementById("iframone").src="<%=request.getContextPath() %>/task/query.action?flag="+flag+"&completion="+completion+"&reportTo="+reportTo+"&executer="+executer;
		}
		
		function writetask(id){
			document.getElementById('taskForm').action=id+".action";
			document.getElementById('taskForm').submit();
		}
		function doObject(id){
			 var flag =   document.getElementById("flag").value;
			 if(flag == null || flag ==''){
		            flag = 1;
		     }
			 var completion = document.getElementById("completion").value;
			 var reportTo = document.getElementById("reportTo").value;
			 var executer = document.getElementById("executer").value;
			 document.getElementById("iframone").src="<%=request.getContextPath() %>/task/query.action?flag="+flag+"&completion="+completion+"&reportTo="+reportTo+"&executer="+executer;
		}
		function conferm(id,flag){
			
			Dialog.confirm("确定删除吗？",function(){
				
				if(flag=="send"){
					$("#iframone").contents().find("#sendBoxForm")[0].action="delete.action?emailId_q="+id;
					$("#iframone").contents().find("#sendBoxForm")[0].submit();
				}
				if(flag=="receive"){
					$("#iframone").contents().find("#receiveBoxForm")[0].action="delete.action?emailId_q="+id;
					$("#iframone").contents().find("#receiveBoxForm")[0].submit();
				}
				if(flag=="draft"){
					$("#iframone").contents().find("#draftBoxForm")[0].action="delete.action?emailId_q="+id;
					$("#iframone").contents().find("#draftBoxForm")[0].submit();
				}
				
				
			});
			
		}
	</script>
	</head>
<body id="mainbody">
          <s:form action="taskAction" id="taskForm" theme="simple"  method="post" target="_self">   
          <s:hidden name="flag" id="flag"></s:hidden>                   
            <!--右侧内容-->
			   <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">任务管理</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0"  style="border-collapse:collapse;"  >
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">汇报人：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<input type="text" id="reportTo" name="reportTo" value="<s:property value='reportTo'/>"/>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">执行人：</td>
						<td height="25"  width="15%" align="left" style="border:0px">
							<input type="text" id="executer" name="executer" value="<s:property value='executer'/>"/>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">任务状态：</td>	
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="completion" name="completion" style="width:152px">
								<option value="">请选择</option>
								<option value="0" <s:if test="completion==0"></s:if>>未完成</option>
								<option value="1" <s:if test="completion==1"></s:if>>已完成</option>
							</select>
						</td>
						<td height="25" width="10%" align="left" style="border:0px">
						</td>
					  </tr>
					  <tr>
						<td align="right" width="15%"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="#" onclick="writetask('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0" ></a></s:else></div></td>
						<td align="left" colspan="6"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
					  </tr>  
					</table>
					<table id="tab_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0"  style="border-collapse:collapse;">

						<tr>
							<td valign="top" class="td_bg_gray">
							<!--tab 开始-->
							<div id="con">
								<ul id="tags">
								  <li class="selectTag" id="man_nav_1" onclick="changeIframe('1','man_nav_1');"><A href="javascript:void(0)">执行任务</A></li>
								  <li class="bg_image" id="man_nav_2" onclick="changeIframe('2','man_nav_2');"><A  href="javascript:void(0)">下达任务</A></li>
									
								</ul>
								<div id="tagContent">
									 <iframe src="<%=request.getContextPath() %>/task/query.action?flag=1"  id="iframone"  width="100%" frameborder=0 align="right" height="630px" scrolling="auto" ></iframe>	
								</div>
							</div>
							<!--tab over-->
						  </td> 
						</tr>
					</table>
				</td>
				</tr>
				</table>
			</div>
        <!--右侧内容结束-->
	</s:form>
</body>
</html>