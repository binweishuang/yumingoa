<%@page import="runix.persistent.model.BaseUser"%>

<%
	BaseUser user = (BaseUser)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("/framework/login.jsp");
	}
 %>
