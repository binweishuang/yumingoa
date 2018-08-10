<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>

		<!-- <link type="text/css" rel="stylesheet" href="../web/css/index.css" />
		<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script
			src='<%=request.getContextPath()%>/dwr/interface/WebUserService.js'></script> -->
		<script type="text/javascript">
			var mm_max = 5;
			function countdown() {
				if(mm_max > 0) {
					mm_max = eval(mm_max - 1);
					///document.getElementById("mm").innerHTML = mm_max;
					setTimeout("countdown();",5);
				} else {
					go();
				}
			}
			
			function go() {
			   var meg=Math.random();
			    var tt=document.getElementById("pid").value;
				window.parent.document.getElementById("win").src="../hovcBaseCadres/insertInit.action?pkid="+tt;
				 ////window.parent.document.getElementById("mainFrame")="./hovcBaseCadres/query.action";
			}
		</script>
	</head>
	<body>
	  <s:hidden name="pkid" id="pid"/>
		<div align="center" style="padding-top: 200px;">
		<img src="<%=SystemConfig.FRAMEWORK_PATH %>/sys-resources/images/loading.gif" width="85" height="81"/>
		<br/>
		<p>loading......</p>
       </div>

	</body>
	<script type="text/javascript">
		setTimeout("countdown();", 50);
	</script>
</html>
