<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ page import="runix.persistent.model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<meta charset="utf-8">
		<title>Runix - the Runix Admin Template</title>
		<meta name="description" content="An admin template from Runix">
		<meta name="author" content="Runix Inc.">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/molle.css"
			rel="stylesheet" type="text/css">

		<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap.css"
			rel="stylesheet">
		<link rel="stylesheet"
			href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-style.css" />

		<link
			href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap-responsive.css"
			rel="stylesheet">
		<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin.css"
			rel="stylesheet" id="main-stylesheet">
		<link
			href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-responsive.css"
			rel="stylesheet">
		<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-photo.css"
			rel="stylesheet">
		<script type="text/javascript"
			src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/bootstrap.min.js"></script>
		<script type="text/javascript"
			src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.cloneposition.js"></script>

		<script type="text/javascript"
			src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/theme.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-plugins.css" />
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
	<body id="body-index"
		style="background: url(img/bg_repeat.jpg) repeat;">
		<form id="systemmanagement" method="post">
			<div class="container-fluid1 content-wrapper1"
				style="width: 99%; background: url(img/bg_repeat.jpg) repeat;">
				<div id="wrapper" style="margin: 0 auto;">
					<!--样式在icon-photo.css-->
					<%
						if (userRole.indexOf("d2") >= 0) {
					%>
					<a
						href="<%=request.getContextPath()%>/permission/queryPosition.action"><img
							src="img/sm1.jpg" class="captify" alt="Aspen Leaf">
					</a>
					<%
						} else {
					%>
					<a href="#"><img src="img/sm1.jpg" class="captify"
							alt="Aspen Leaf">
					</a>
					<%
						}
						if (userRole.indexOf("d3") >= 0) {
					%>
					<a href="<%=request.getContextPath()%>/baseData/queryData.action"><img
							src="img/sm2.jpg" class="captify" alt="Aspen Leaf">
					</a>
					<%
						} else {
					%>
					<a href="#"><img src="img/sm2.jpg" class="captify"
							alt="Aspen Leaf">
					</a>
					<%
						}
						if (userRole.indexOf("d4") >= 0) {
					%>
					<a href="<%=request.getContextPath()%>/baseUser/query.action"><img
							src="img/sm3.jpg" class="captify" alt="Aspen Leaf">
					</a>
					<%
						} else {
					%>
					<a href="#"><img src="img/sm3.jpg" class="captify"
							alt="Aspen Leaf">
					</a>
					<%
						}
						if (userRole.indexOf("d5") >= 0) {
					%>
					<a href="<%=request.getContextPath()%>/position/query.action"><img
							src="img/sm5.jpg" class="captify" alt="Aspen Leaf">
					</a>
					<%
						} else {
					%>
					<a href="#"><img src="img/sm5.jpg" class="captify"
							alt="Aspen Leaf">
					</a>
					<%
						}
						if (userRole.indexOf("d6") >= 0) {
					%>
					<a href="<%=request.getContextPath()%>/dept/query.action"> <img
							src="img/sm4.jpg" class="captify" alt="Aspen Leaf">
					</a>
					<%
						} else {
					%>
					<a href="#"> <img src="img/sm4.jpg" class="captify"
							alt="Aspen Leaf">
					</a>
					<%
						}
					%>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span12">
					&nbsp;
				</div>
			</div>
			</div>
		</form>
	</body>
</html>