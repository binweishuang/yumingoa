<%@ page contentType="text/html; charset=utf-8" language="java" isErrorPage="true" %>
<%@ page import="kdf.tools.StringUtil" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<jsp:include  page="/framework/include/Resources.jsp"/>
<title>错误信息提示</title>
</head>

<body id="main_content">
	<table class="common_table">
		<tr>
			<td class="head">
				<span id="nav">错误信息</span>
			</td>
		</tr>
		<tr>
			<td class="err">
				<table class="err_table">
				  <tr>
				    <td rowspan="5" class="left_errico"></td>
				    <td class="right_errtxt">错误原因如下：<br>
				      <%if( exception != null ){%>
				      <li><%=exception.getMessage()%><br>
				      <%exception.printStackTrace();}%>
				    </td>
				  </tr>
				  <tr>
				  	 <td class="right_errtxt">详细信息：<br>
				     <%if( exception != null ){%>
				      <%=StringUtil.throwableToString(exception)%>
				      <%}%>
				    </td>
				  </tr>
				  <tr>
				    <td class="right_line1"></td>
				  </tr>
				  <tr>
				    <td class="right_line2"></td>
				  </tr>
				  <tr>
				    <td class="right_bottomtxt">请您确认问题后，直接联系系统管理员。</td>
				  </tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>

