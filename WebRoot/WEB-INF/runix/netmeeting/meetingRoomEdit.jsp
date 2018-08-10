<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>网络会议</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 

	<script type=text/javascript>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});

		function doCheck(){
			var content = document.getElementById('content').value;
			var netmeetingId = document.getElementById('netmeetingId').value;
			if(content!=''&&content!=null){
				document.getElementById('netmeetingListForm').action="spoke.action?act=1&netmeetingId_q="+netmeetingId;
				document.getElementById('netmeetingListForm').submit();
			}else{
				document.getElementById('netmeetingListForm').action="spoke.action?act=2&netmeetingId_q="+netmeetingId;
				document.getElementById('netmeetingListForm').submit();
			}
			
		}
		
	</script>
	</head>
<body >                     

	<s:form action="netmeetingAction" id="netmeetingListForm" namespace="/survey" method="post" theme="simple">
    <s:hidden name="currentPage" id="currentPage"></s:hidden>
    <s:hidden name="netmeetingId" id="netmeetingId"></s:hidden>
	 <div  style="width:99%; margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网络会议</span>&gt;&gt; 
						<span class="STYLE1">会议室</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">会议室</span></td>
          </tr>
          <tr>
            <td width="12%" height="25" align="right" bgcolor="#f8f8f8">会议主题： </td>
            <td height="25" bgcolor="#FFFFFF"><s:property value="title"/></td>
          </tr>
          <tr>
            <td height="25" align="right" bgcolor="#f8f8f8">时间： </td>
            <td height="25" bgcolor="#FFFFFF">
				<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/> &nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
          </tr>
		  </table> 
		  <div style="width:100%;height:300px;padding-top:30px;overflow-y:auto;">
		  	<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
								  <thead height="33px">
									<tr>
									 <th width="15%">发言人</th>
              						 <th width="70%">内容</th>
              						 <th width="15%">时间</th>
									</tr>
								  </thead>
								  <tbody>
								  <s:iterator value="meetingRoomList">
									<tr height="32px">										 
									  <td ><s:property value="spokesman"/></td>
             						  <td ><s:property value="content"/></td>
              						  <td ><s:date name="speaktime" format="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
									</s:iterator>
								  </tbody>
							</table>	
		  </div>
		  
    <table>
       <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">我来发言：</td>
            <td height="20" bgcolor="#FFFFFF">
				<textarea name="content" id="content" cols="50" rows="2"></textarea>
			</td>
			<td height="20" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" class="buttons" value="发送"  onclick="doCheck();"/>
            </div></td>
          </tr>  
        </table>
		</td>
  </tr>
</table>


</div>
	</s:form>
</body>
</html>
