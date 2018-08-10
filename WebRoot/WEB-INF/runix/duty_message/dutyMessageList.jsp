<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>值班信息</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
	</script>
	</head>
<body >                     
    <!--右侧内容-->
	<s:form action="query" namespace="/dutyMessage" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
	  <div  style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
		  <tr>
			<td>
	       		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  		<tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">值班信息</span></td>
			  		</tr>
				</table>
				<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				  <tr>
					<td height="25" width="10%" align="right" style="border:0px">值班者：</td>
					<td height="25" width="10%" align="left" style="border:0px"><input name="dutyperson" value="${dutyperson}" type="text"  /></td>
					<td height="25" width="10%" align="right" style="border:0px">开始时间：</td>
					<td height="25" width="10%" align="left" style="border:0px">
					<input class="Wdate" name="starttime" value="${starttime}" type="text"   id= "starttime" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})" /></td>
					<td height="25" width="10%" align="right" style="border:0px">结束时间：</td>
					<td height="25" align="left" style="border:0px">
					<input class="Wdate" name="endtime" value="${endtime}" type="text"   id = "endtime" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})" /></td>
				  </tr>
				  <tr>
					<td align="right" width="10%">
						<div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else>
							<a href="<%=request.getContextPath() %>/dutyMessage/insertInit.action" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else>
						</div>
					</td>
					<td align="left" colspan="9">
						<div style="padding-top:4px">
							<input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" />
						</div>
					</td>
				  </tr>  
				</table>
		        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
		          <thead height="33px">
		            <tr>
		              <th width="13%" >值班者</th>
		              <th width="6%">班次</th>
					  <th width="13%">开始时间</th>
					  <th width="13%">结束时间</th>
					  <th width="26%">值班地点</th>
					  <th width="15%">备注</th>
					  <th>操作</th>
	                </tr>
	              </thead>
		          <tbody>
		          	<s:iterator value="dutyMessageList">
		             <tr>
		              <td>${DUTYPERSONNAME}</td>
		              <td>${CLASSES}</td>
					  <td>${STARTTIME}</td>
					  <td>${ENDTIME}</td>
					  <td>${DUTYADDRESS}</td>
					  <td>${REMARK}</td>
					  <td>
					  <s:if test="readonly==true"></s:if><s:else>
						 <a href="<%=request.getContextPath() %>/dutyMessage/updateInit.action?dutyId_q=${DUTY_ID}" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
						 <a href="<%=request.getContextPath() %>/dutyMessage/delete.action?dutyId_q=${DUTY_ID}&dutyperson=${dutyperson}&starttime=${starttime}&endtime=${endtime}&currentPage=${currentPage}" onclick="return confirm('确定删除吗？');"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
					</s:else>
					  </td>
	                </tr>
	                </s:iterator>
					<tr height="32px">
                         <td height="20" align="right" colspan="10">
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
    <!--右侧内容结束-->
</body>
</html>
