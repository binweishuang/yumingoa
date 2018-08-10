<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<style type="text/css">
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
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
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
		if(flag=='1'){
			document.getElementById("iframone").src='<%=request.getContextPath() %>/archive/query.action';
		}else if(flag=='2'){
			document.getElementById("iframone").src='<%=request.getContextPath() %>/dossier/query.action';
		}
	}


	</script>
	</head>
<body >                   
            <!--右侧内容-->
			  <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公文管理 &gt;&gt; <span class="STYLE1">公文档案</span></td>
					  </tr>
					</table>
					
					<table id="tab_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">

						<tr>
							<td valign="top" class="td_bg_gray">
							<!--tab 开始-->
							<div id="con">
								<ul id="tags">
								  <li class="selectTag" id="man_nav_1" onclick="changeIframe('1','man_nav_1');"><A  href="javascript:void(0)">公文管理</A></li>
								  <li onclick="changeIframe('2','man_nav_2');" id="man_nav_2" class="bg_image"><A  href="javascript:void(0)">案卷管理</A></li>
								</ul>
								<div id="tagContent">
									 <iframe src="<%=request.getContextPath() %>/archive/query.action"  id="iframone" width="100%" frameborder=0 align="right" height="680px" scrolling="auto" ></iframe>	 
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

</body>
</html>