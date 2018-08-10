<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
	</script>
	<script>
		function del(scheduleId){
			Dialog.confirm("确定删除吗？",function(){
				location.href = "delete.action?scheduleId="+scheduleId;
			});
		}
		
		function subForm(){
			document.form.submit();
		}
		
	</script>
	</head>
<body >
                                
            <!--右侧内容-->
			  <div  style="width:99%; margin:0 auto; background-color:#fff;">
			  		<s:form name="form" id="form" action="query.action" method="post" theme="simple" target="_self">
			  		<s:hidden name="currentPage" value="currentPage"></s:hidden>
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">日程安排</span></td>
					  </tr>
					</table>
					
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">开始时间：</td>
						<td height="25" width="14%" align="left" style="border:0px">
							<input name="starttime" id="start" type="text" value="<s:date name='starttime' format='yyyy-MM-dd'/>"
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')||\'2020-01-01\'}'})" class="Wdate"/>
						</td>
						<td height="25" width="10%" align="right" style="border:0px">结束时间：</td>
						<td height="25" align="left" style="border:0px">
							<input name="endtime" id="endDate" type="text" value="<s:date name='endtime' format='yyyy-MM-dd'/>"
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})" class="Wdate"/>
						</td>
					  </tr>
					  
					  <tr>
						<td align="right">
								<a href="addSchedule.action" ><img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"/></a>
						</td>
						<td align="left" colspan="3">
									<a href="javascript:subForm();"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0"/></a>
						</td>
					  </tr>  
					</table>
					
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="15%" >安排人</th>
			              <th>日程标题</th>
			              <th width="10%">起始时间</th>
			              <th width="10%">结束时间</th>
			              <th width="10%">安排时间</th>
			              <th width="25%" >操作</th>
			              
		                </tr>
		              </thead>
			          <tbody>
			          	<s:if test="scheduleList.size()>0">
			          	<s:iterator value="scheduleList">
			            <tr height="32px">
			              <td>${executer }</td>
			              <td>${title }</td>
			              <td><s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/></td>
			              <td><s:property value="@runix.util.StringUtil@dateFmt(endtime)"/></td>
			              <td><s:property value="@runix.util.StringUtil@dateFmt(rangetime)"/></td>
			              <td>
							 <a href="updateInit.action?scheduleId=${scheduleId}" ><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
							 <a href="view.action?scheduleId=${scheduleId}"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
							 <a href="javascript:del(${scheduleId})"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
						  </td>
		                </tr>
			            </s:iterator>
			            </s:if>
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
					</s:form>
			</div>
        <!--右侧内容结束-->
</body>
</html>