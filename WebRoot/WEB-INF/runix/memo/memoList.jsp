<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>我的便签列表</title>
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
		
		function doquery(){
			var starttime = $("#starttime").val();
			var endtime = $("#endtime").val();
			if(starttime > endtime){
				Dialog.alert("请选择正确的开始时间和结束时间！");
				return false;
			}
			$("#form1").submit();
		}
	</script>
	</head>
<body >
    <!--右侧内容-->
    <s:form action="query" namespace="/memo" method="post" id="form1" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
	  <div  style="width:99%;background-color:#fff;margin:0 auto;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
	       			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  			<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">我的便签</span></td>
			  			</tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">开始时间：</td>
						<td height="25" width="15%" align="left" style="border:0px;">
							<input class="Wdate" name="starttime" id="starttime" value='<s:date format="yyyy-MM-dd" name="starttime"/>' type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})" />
						</td>
						<td height="25" width="10%" align="right" style="border:0px">结束时间：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<input class="Wdate" name="endtime" id="endtime" value='<s:date format="yyyy-MM-dd" name="endtime"/>' type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})" />
						</td>
						<td height="25" width="10%" align="right" style="border:0px">便签标题：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="title" value="${title}" type="text" /></td>
						<td height="25" width="10%" align="right" style="border:0px">处理方式：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select name="status">
								<option value="0">--请选择--</option>
								<option value="1" <s:if test='status == "1"'>selected="selected"</s:if>>暂存
								<option value="2" <s:if test='status == "2"'>selected="selected"</s:if>>提交</option>
							</select>
						</td>
						<td height="25" width="40%"  style="border:0px"></td>
					  </tr> 
					  <tr>
						<td align="right">
							<div style="padding-top:4px">
								<a href="<%=request.getContextPath() %>/memo/insertInit.action" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a>
							</div>
						</td>
						<td align="left" colspan="8">
							<div style="padding-top:4px">
								<input type="image" onclick="return doquery();" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" />
							</div>	
						</td>
					  </tr>  
				</table>
		        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
		          <thead height="33px">
		            <tr>
		              <th width="15%" >便签标题</th>
		              <th width="10%">开始时间</th>
		              <th width="9%">结束时间</th>
		              <th width="9%">提交时间</th>
		              <th width="13%">上报人</th>
					  <th width="13%">上报对象</th>
					  <th width="13%">处理方式</th>
		              <th>操作</th>
	                </tr>
	              </thead>
		          <tbody>
		          	<s:iterator value="memoList">
		            <tr height="32px">
		              <td >${TITLE}</td>
		              <td ><s:date format="yyyy-MM-dd" name="STARTTIME"/></td>
		              <td ><s:date format="yyyy-MM-dd" name="ENDTIME"/></td>
		              <td ><s:date format="yyyy-MM-dd" name='SUBMITTIME'/></td>
		              <td >${REPORTPERSONNAME}</td>
					  <td >${RECIEVEPERSONNAME}</td>
					  <td ><s:if test='STATUS == "2"'>提交</s:if><s:else>暂存</s:else></td>
		              <td >
		              	 <s:if test='recieveperson != RECIEVEPERSON && STATUS != "2"'>
						 <a href="<%=request.getContextPath() %>/memo/updateInit.action?memoId_q=${MEMO_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
						 </s:if>
						 <a href="<%=request.getContextPath() %>/memo/detail.action?memoId_q=${MEMO_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0" style="vertical-align:middle;">查看</a>
						 <a href="<%=request.getContextPath() %>/memo/delete.action?memoId_q=${MEMO_ID}&starttime=${starttime}&endtime=${endtime}&title=${title}&status=${status}&currentPage=${currentPage}" onclick="return confirm('确定删除吗？');"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a>	
					  </td>
	                </tr>
	                </s:iterator>
	                <tr></tr>
					<tr height="32px">
	                    <td height="20" colspan="8" align="right">
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
