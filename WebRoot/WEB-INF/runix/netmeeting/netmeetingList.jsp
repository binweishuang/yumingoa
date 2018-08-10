<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>网络会议</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type=text/javascript>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});

		function doObject(id){
			document.getElementById('netmeetingListForm').action=id+".action";
			document.getElementById('netmeetingListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('netmeetingListForm').action="updateInit.action?netmeetingId_q="+id;
			document.getElementById('netmeetingListForm').submit();
		}
		function doview(id){
			document.getElementById('netmeetingListForm').action="view.action?netmeetingId_q="+id;
			document.getElementById('netmeetingListForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('netmeetingListForm').action="delete.action?netmeetingId_q="+id;
			document.getElementById('netmeetingListForm').submit();
			});
		}
		function domeeting(id){
			document.getElementById('netmeetingListForm').action="meeting.action?netmeetingId_q="+id;
			document.getElementById('netmeetingListForm').submit();
		}
	</script>
	</head>
<body >                     

	<s:form action="netmeetingAction" id="netmeetingListForm" namespace="/survey" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
	<div  style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网络会议</span></td>
					  </tr>
					</table>

							<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
							  <tr>
								<td height="25" width="15%" align="right" style="border:0px">会议主题：</td>
								<td height="25" width="15%" align="left" style="border:0px"><input name="title" type="text" value="<s:property value='title'/>"/></td>
								<td height="25"  width="15%" align="right" style="border:0px">开始时间：</td>
								<td height="25" width="15%" align="left" style="border:0px"><input name="starttime" type="text" value="<s:date name='starttime' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" class="Wdate"/></td>
								<td height="25" width="15%" align="right" style="border:0px">结束时间：</td>
								<td height="25" width="15%" align="left" style="border:0px"><input name="endtime" type="text" value="<s:date name='endtime' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" class="Wdate"/></td>
								<td height="25" width="10%" align="left" style="border:0px"></td>
							  </tr>
							  <tr>
								<td align="right" width="15%"><s:if test="readonly==true"></s:if><s:else>
									<a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else>
								</td>
								<td align="left" colspan="7">
								<a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a>
								</td>
							  </tr>  
							</table>
							<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
								  <thead height="33px">
									<tr>
									 <th width="15%">会议主题</th>
              						 <th width="10%">创建者</th>
              						 <th width="10%">开始时间</th>
             						 <th width="10%">结束时间</th>
									 <th width="15%">会议状态</th>
									 <th width="20%">操作</th>													  
									</tr>
								  </thead>
								  <tbody>
								  <s:iterator value="netmeetingList">
									<tr height="32px">										 
									  <td ><s:if test="nowtime<starttime"><s:property value="title"/></s:if><s:elseif test="nowtime>endtime "><s:property value="title"/></s:elseif><s:else><a href="#" title="进入会议室" onclick="domeeting('<s:property value='netmeetingId'/>');" style="color:blue"><s:property value="title"/></a></s:else></td>
             						  <td ><s:property value="sponsor"/></td>
              						  <td ><s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/></td>
									  <td ><s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/></td>
									  <td ><s:if test="nowtime<starttime">未开始</s:if><s:elseif test="nowtime>endtime ">已结束</s:elseif><s:else>进行中</s:else></td>
									  <td >
										 <s:if test="sponsor==name and nowtime<starttime"><a href="#" onclick="doupdate('<s:property value='netmeetingId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
										<s:if test='nowtime>endtime'><a  href="#" onclick="doview('<s:property value='netmeetingId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
										 <s:if test="sponsor==name"><s:if test="nowtime<starttime or nowtime>endtime"><a href="#" onclick="dodelete('<s:property value='netmeetingId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a></s:if></s:if>	
									  </td>
									</tr>
									</s:iterator>
									 <tr height="32px">
										<td height="20" colspan="6" align="right">
											<!--分页开始-->
											<s:include value="/framework/include/page.jsp"/>
											<!--分页结束-->
										</td>
									</tr>
								  </tbody>
							</table>	
						    </td> 
						</tr>
					</table>
	</div>
	</s:form>
</body>
</html>
