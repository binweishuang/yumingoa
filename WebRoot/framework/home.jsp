<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="kdf.constant.SystemConfig"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>
  <head>
    <title><%= SystemConfig.APP_NAME %></title>
    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=GBK">
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
    <jsp:include  page="/framework/include/Resources.jsp"/>
    <script>
		
	</script>	
  </head>
	
<frameset rows="125,*,30" cols="*" framespacing="0" frameborder="no" border="0" id="frmSet" name="frmSet">

        <frameset id="mainfrm" name="mainfrm" rows="38,*" cols="*" frameborder="no" border="0" framespacing="0">
			<frame src="<%=SystemConfig.FRAMEWORK_PATH%>/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
		    <frame src="<%=SystemConfig.FRAMEWORK_PATH%>/top2.jsp" name="topFrame2" scrolling="No" noresize="noresize" id="topFrame2" />
		</frameset>	
		<frameset id="mainfrm2" name="mainfrm2" cols="17%,83%" frameborder="no" border="0" framespacing="0">
			<frame src="<%=SystemConfig.FRAMEWORK_PATH%>/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" frameborder="no" title="leftFrame" />	
			<frame src="<%=request.getContextPath() %>/mainFrame/main.action"    name="mainFrame" id="mainFrame" frameborder="no" scrolling="auto"/>
		</frameset>
		<frame src="<%=SystemConfig.FRAMEWORK_PATH%>/bottom.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
	</frameset>
  <noframes><body>
  </body></noframes>
</html>