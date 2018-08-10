<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/framework/scripts/jquery.min.js"></script>
	<style type="text/css">
	#con { font-size:12px; width:100%;height:100%;}
	#tags { padding:0; margin:0; width:800px; height:26px;}
	#tags li { float:left; padding-left:10px; padding-right:10px; margin-right:2px;height:26px; list-style-type:none; background:url(<%=request.getContextPath()%>/framework/img/tagleft.gif) no-repeat left bottom;}
	#tags li A { padding:0 5px; float:left; color:#fff; line-height:26px; height:26px; text-decoration:none; }
	#tags li.selectTag { background-position:left top; margin-bottom:0px; position:relative; height:26px}
	#tags li.selectTag A {color:#000; line-height: 25px; height: 25px}
	#tagContent { background-color: #fff ;width:100%;height:100%}
	.tagContent { padding:0px; display:none; width:100%; color: #474747; height:100%; text-align:center }
	#tagContent{
	box-shadow:3px 3px 3px #959595;
	-moz-box-shadow:10px 10px 5px #959595;
	-webkit-box-shadow:10px 10px 5px #959595;
	position:relative;z-index:1;}
	#tagContent div.selectTag {display: block}
	.td_bg_gray{background:#b2b2b2}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"/>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/car.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
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
		document.getElementById("iframone").src='<%=request.getContextPath() %>/car/query.action';
	}else if(flag=='2'){
		document.getElementById("iframone").src='<%=request.getContextPath() %>/usingcar/query.action';
	}
}
		
	</script>
	<style type="text/css">
		#cartype, #license{width:94%;height:17px;}
	</style>
	</head>
<body >
                                
            <!--右侧内容-->
			  <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 行政办公 &gt;&gt; <span class="STYLE1">车辆管理</span></td>
					  </tr>
					</table>
					
					<table id="tab_table"  width="100%" border="1" align="center" cellpadding="4" cellspacing="0" style="border-collapse:collapse; border-color:#f8de91; ">

						<tr>
							<td valign="top" class="td_bg_gray">
							<!--tab 开始-->
							<div id="con">
								<ul id="tags">
								  <li class="selectTag" id="man_nav_1" onclick="changeIframe('1','man_nav_1');"><a  href="javascript:void(0)">车辆管理</a></li>
								  <li  onclick="changeIframe('2','man_nav_2');" id="man_nav_2" class="bg_image"><a  href="javascript:void(0)">用车管理</a></li>
								</ul>
								<div id="tagContent">
									 <iframe src="<%=request.getContextPath() %>/car/query.action"  id="iframone" width="100%" frameborder=0 align="right" height="635px" scrolling="auto" ></iframe>	
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