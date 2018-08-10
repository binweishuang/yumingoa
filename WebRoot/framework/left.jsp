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


						<link rel="stylesheet"
							href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-style.css" />
						<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap.css"
							rel="stylesheet" />
						<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin.css"
							rel="stylesheet" id="main-stylesheet" />


						<script type="text/javascript"
							src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>




						<script type="text/javascript">
	$(document).ready(function() {
		$("#menu_head_1").click(function() {
			$("#collapse1").slideToggle("300");
			$("#collapse2").slideUp("slow");
			$("#collapse3").slideUp("slow");

		});

		$("#menu_head_2").click(function() {
			$("#collapse3").slideToggle("300");
			$("#collapse1").slideUp("slow");
			$("#collapse2").slideUp("slow");

		});

		$("#menu_head_3").click(function() {
			$("#collapse2").slideToggle("300");
			$("#collapse1").slideUp("slow");
			$("#collapse3").slideUp("slow");

		});
	});

	function gotoPage(id) {
		window.parent.document.getElementById("mainFrame").src = id;
	}
</script>
						<%
							BaseUser user = (BaseUser) session.getAttribute("user");
							String userRole = user.getTitle();
						%>
					
	</head>
	<body id="body-index">

		<div class="sidebar-nav" style="width: 100%">
			<ul class="nav nav-stacked left-menu">
				<li id="leibiao_1">
					<a id="menu_head_1" class="accordion-toggle" href="#"><span
						class="label pull-right ">3</span><span class="box"> <i><img
									src="css/icon/icon-left1.png">
						</i> </span> <span>公文管理</span> </a>
					<div id="collapse1" class="accordion-body" style="display: none">
						<ul class="nav nav-stacked submenu">
							<%
								if (userRole.indexOf("e2") >= 0 || userRole.indexOf("em2") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/officialDocument/query.action?flag=1');">
									<span class="box"> <i><img
												src="css/icon/icon-left1.1.png">
									</i> </span> <span>收文管理</span> </a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left1.1.png">
									</i> </span> <span>收文管理</span> </a>
							</li>
							<%
								}
								if (userRole.indexOf("e3") >= 0 || userRole.indexOf("em3") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/officialDocument/query.action?flag=2');">
									<span class="box"> <i><img
												src="css/icon/icon-left1.2.png">
									</i> </span> <span>发文管理</span> </a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left1.2.png">
									</i> </span> <span>发文管理</span> </a>
							</li>
							<%
								}
								if (userRole.indexOf("e4") >= 0 || userRole.indexOf("em4") >= 0) {
							%>
							<li class="submenu-last">
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/archive/queryArchive.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left1.3.png">
									</i> </span> <span>公文档案</span> </a>
							</li>
							<%
								} else {
							%>
							<li class="submenu-last">
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left1.3.png">
									</i> </span> <span>公文档案</span> </a>
							</li>
							<%
								}
							%>
						</ul>
					</div>
				</li>
				<li id="leibiao_2">
					<a id="menu_head_2" class="accordion-toggle" href="#"> <span
						class="label pull-right ">3</span><span class="box"> <i><img
									src="css/icon/icon-left2.png">
						</i> </span> <span>在线交流</span> </a>
					<div id="collapse3" class="accordion-body" style="display: none">
						<ul class="nav nav-stacked submenu">
							<%
								if (userRole.indexOf("f2") >= 0 || userRole.indexOf("fm2") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/survey/query.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left2.1.png">
									</i> </span> <span>网上调查</span> </a>
								</a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left2.1.png">
									</i> </span> <span>网上调查</span> </a>
								</a>
							</li>
							<%
								}
								if (userRole.indexOf("f3") >= 0 || userRole.indexOf("fm3") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/suggestion/query.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left2.2.png">
									</i> </span> <span>个人建议</span> </a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left2.2.png">
									</i> </span> <span>个人建议</span> </a>
							</li>
							<%
								}
								if (userRole.indexOf("f4") >= 0 || userRole.indexOf("fm4") >= 0) {
							%>
							<li class="submenu-last">
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/netmeeting/query.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left2.3.png">
									</i> </span> <span>网络会议</span> </a>
							</li>
							<%
								} else {
							%>
							<li class="submenu-last">
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left2.3.png">
									</i> </span> <span>网络会议</span> </a>
							</li>
							<%
								}
							%>
						</ul>
					</div>
				</li>

				<li id="leibiao_3">
					<a id="menu_head_3" class="accordion-toggle" href="#"><span
						class="label pull-right ">3</span> <span class="box"> <i><img
									src="css/icon/icon-left3.png">
						</i> </span> <span>行政办公</span> </a>
					<div id="collapse2" class="accordion-body" style="display: none">
						<ul class="nav nav-stacked submenu">
							<%
								if (userRole.indexOf("g2") >= 0 || userRole.indexOf("gm2") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/car/queryM.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left3.1.png">
									</i> </span> <span>车辆管理</span> </a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left3.1.png">
									</i> </span> <span>车辆管理</span> </a>
							</li>
							<%
								}
								if (userRole.indexOf("g3") >= 0 || userRole.indexOf("gm3") >= 0) {
							%>
							<li>
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/apply/query.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left3.2.png">
									</i> </span> <span>请示管理</span> </a>
								</a>
							</li>
							<%
								} else {
							%>
							<li>
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left3.2.png">
									</i> </span> <span>请示管理</span> </a>
								</a>
							</li>
							<%
								}
								if (userRole.indexOf("g4") >= 0 || userRole.indexOf("gm4") >= 0) {
							%>
							<li class="submenu-last">
								<a href="#"
									onclick="gotoPage('<%=request.getContextPath()%>/usingEquip/queryUsingE.action');">
									<span class="box"> <i><img
												src="css/icon/icon-left3.3.png">
									</i> </span> <span>设备管理</span> </a>
							</li>
							<%
								} else {
							%>
							<li class="submenu-last">
								<a href="#"> <span class="box"> <i><img
												src="css/icon/icon-left3.3.png">
									</i> </span> <span>设备管理</span> </a>
							</li>
							<%
								}
							%>
						</ul>
					</div>
				</li>
				<%
					if (userRole.indexOf("h2") >= 0) {
				%>
				<li id="navigation-graphs">
					<a href="#"
						onclick="gotoPage('<%=request.getContextPath()%>/workflow/workflowPage.action');">
						<span class="box"> <i><img
									src="css/icon/icon-left4.png">
						</i> </span> <span>工作流程</span> </a>
				</li>
				<%
					} else {
				%>
				<li id="navigation-graphs">
					<a href="#"> <span class="box"> <i><img
									src="css/icon/icon-left4.png">
						</i> </span> <span>工作流程</span> </a>
				</li>
				<%
					}
				%>
			</ul>
		</div>

	</body>
</html>