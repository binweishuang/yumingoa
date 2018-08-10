<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ page import="runix.persistent.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8">
			<meta name="description" content="An admin template from Runix">
				<meta name="author" content="Runix Inc.">
					<meta name="viewport"
						content="width=device-width, initial-scale=1.0">

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
											<link
												href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-photo.css"
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
												<script type="text/javascript">
	function menu_onclick(id) {
		window.parent.document.getElementById("mainFrame").src = id;
	}
</script>
												<style>
html {
	overflow-x: hidden;
}
</style>
												<%
													BaseUser user = (BaseUser) session.getAttribute("user");
													String userRole = user.getTitle();
												%>
											
	</head>
	<body id="body-index"
		style="background: url(img/bg_repeat.jpg) repeat;">
		<div class="container-fluid content-wrapper"
			style="width: 99%; background: url(img/bg_repeat.jpg) repeat;">
			<div id="wrapper" style="margin: 0 auto;">
				<!--样式在icon-photo.css-->
				<%
					if (userRole.indexOf("a2") >= 0 || userRole.indexOf("am2") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/matter/query.action"><img
						src="img/po1.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po1.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a3") >= 0 || userRole.indexOf("am3") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/memo/query.action"><img
						src="img/po2.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po2.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a4") >= 0 || userRole.indexOf("am4") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/email/queryEmail.action"><img
						src="img/po3.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po3.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a5") >= 0 || userRole.indexOf("am5") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/schedule/query.action"><img
						src="img/po4.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po4.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a6") >= 0 || userRole.indexOf("am6") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/task/queryM.action"><img
						src="img/po5.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po5.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a7") >= 0) {
				%>
				<a
					href="<%=request.getContextPath()%>/addressbook/query.action?flag=1"><img
						src="img/po6.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po6.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a8") >= 0 || userRole.indexOf("am8") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/dutyMessage/query.action"><img
						src="img/po7.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po7.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
					if (userRole.indexOf("a9") >= 0 || userRole.indexOf("am9") >= 0) {
				%>
				<a href="<%=request.getContextPath()%>/messageCenter/query.action"><img
						src="img/po8.jpg" class="captify" alt="Aspen Leaf">
				</a>
				<%
					} else {
				%>
				<a href="#"><img src="img/po8.jpg" class="captify"
						alt="Aspen Leaf">
				</a>
				<%
					}
				%>
			</div>
		</div>


	</body>
</html>