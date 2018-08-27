<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ page import="runix.persistent.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta charset="utf-8" />
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix"></meta>
<meta name="author" content="Runix Inc."></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/molle.css" rel="stylesheet" type="text/css"></link>
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap.css" rel="stylesheet"></link>
<link rel="stylesheet" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-style.css" />
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap-responsive.css" rel="stylesheet"></link>
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin.css" rel="stylesheet" id="main-stylesheet"></link>
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-responsive.css" rel="stylesheet"></link>
<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-photo.css" rel="stylesheet"></link>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.cloneposition.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/theme.js"></script>
<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-plugins.css" />
<style>
html {
	overflow-x: hidden;
	overflow-y: hidden;
}
</style>
	<%
		BaseUser user = (BaseUser) session.getAttribute("user");
		String userRole = user.getTitle();
	%>
									
</head>
<body id="body-index" style="background: url(img/bg_repeat.jpg) repeat;">
	<div class="container-fluid1 content-wrapper1" style="width: 99%; background: url(img/bg_repeat.jpg) repeat;">
		<div id="wrapper" style="margin: 0 auto;">
			<!--样式在icon-photo.css-->
			<%
				if (userRole.indexOf("b2") >= 0 || userRole.indexOf("bm2") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/messageCenter/queryNews.action">
				<img src="img/pi3.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				} else {
			%>
			<a href="#">
				<img src="img/pi3.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				}
				if (userRole.indexOf("b3") >= 0 || userRole.indexOf("bm3") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/messageCenter/queryNotice.action">
				<img src="img/pi5.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				} else {
			%>
			<a href="#">
				<img src="img/pi5.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				}
				if (userRole.indexOf("b4") >= 0 || userRole.indexOf("bm4") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/messageCenter/queryRules.action">
				<img src="img/pi1.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				} else {
			%>
			<a href="#">
				<img src="img/pi1.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				}
				if (userRole.indexOf("b5") >= 0 || userRole.indexOf("bm5") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/netdisk/query.action">
				<img src="img/pi4.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				} else {
			%>
			<a href="#">
				<img src="img/pi4.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				}
				if (userRole.indexOf("b6") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/addressbook/manage.action">
				<img src="img/pi2.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
				} else {
			%>
			<a href="#">
				<img src="img/pi2.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%--测试用章制度--%>
			<%
				}
				if (userRole.indexOf("b6") >= 0) {
			%>
			<a href="<%=request.getContextPath()%>/messageCenter/queryChapter.action">
				<img src="img/pi2.jpg" class="captify" alt="Aspen Leaf"></img>
			</a>
			<%
			} else {
			%>
			<a href="#">
				<img src="img/pi2.jpg" class="captify" alt="Aspen Leaf"></img>

			</a>
			<%
				}
			%>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">&nbsp;</div>
	</div>
</body>
</html>