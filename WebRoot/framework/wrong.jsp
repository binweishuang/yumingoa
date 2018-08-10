<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<html>
  <head>
    <title>出错了!</title>
	<link type="text/css" rel="stylesheet" href="<%= SystemConfig.SYS_RESOURCES_PATH %>/css/kdf_system.css"></link>
  </head>
  
  <body style="background:#fff; overflow:auto">
  	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed">
  		<tr>
  			<td align="center" class="jxt-tablebg">
  				<img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/wrong.gif" style="filter:Alpha(opacity=80);">
  			</td>
  		</tr>
  	</table>    
  </body>
</html>
