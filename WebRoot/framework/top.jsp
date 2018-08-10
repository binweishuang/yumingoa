<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  
<head>
    <meta charset="utf-8" />
    <meta name="description" content="An admin template from Runix" />
	<meta name="author" content="Runix Inc." />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/molle.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap.css" rel="stylesheet"></link>
	<link rel="stylesheet" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-style.css" />
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap-responsive.css" rel="stylesheet"></link>
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin.css" rel="stylesheet" id="main-stylesheet"></link>
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-responsive.css" rel="stylesheet"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.cloneposition.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/theme.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin-plugins.css" />
		<script type="text/javascript">  
		function menu_onclick(id){
			 window.parent.document.getElementById("mainFrame").src="<%=request.getContextPath() %>/"+id;
		}
	    </script>
</head>
<body>
  <!-- Page Header -->
  <div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner black-gradient">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="#">
					<span class="rad">Runix</span> OA Template
				</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						
						<a  href="#" onclick="menu_onclick('mainFrame/main.action');" class="btn btn-mini btn-inverse navbar-link logout"> <img src="img/shouye.png"/>
							首页
						</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a   href="#" onclick="parent.location.href='<%=request.getContextPath() %>/loginAction!logout.action'" class="btn btn-mini btn-inverse navbar-link logout"> <i class="radmin-icon radmin-redo"></i>
							登出
						</a>
					</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>