<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css">
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/message-center.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		function detail(id, type, typeName, umid) {
				if (type == "NEWS") {
					location.href = "newsDetail.action?messageId=" + id + "&type="
							+ typeName +"&umId="+umid+ "&readstatus=1";
				}
				if (type == "NOTICE") {
					location.href = "noticeDetail.action?messageId=" + id + "&type="
							+ typeName +"&umId="+umid+ "&readstatus=1";
				}
				if (type == "RULES") {
					location.href = "rulesDetail.action?messageId=" + id + "&type="
							+ typeName +"&umId="+umid+ "&readstatus=1";
				}
			}
	</script>
	</head>
<body >
            <s:form  id="form" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" value="currentPage"></s:hidden>                       
            <!--右侧内容-->
			  <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">信息中心</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">信息类型：</td>
						<td height="25" width="14%" align="left" style="border:0px">
							<s:select id="type" name="type" headerKey="-1"
										headerValue="--请选择--" list="types" listKey="dataId"
										listValue="dataname" theme="simple" value="type"/>
						</td>
						<td height="25" width="10%" align="right" style="border:0px">起始时间：</td>
						<td height="25" width="14%" align="left" style="border:0px">
							<input name="starttime" id="start" type="text" value="${start }"
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')||\'2020-01-01\'}'})"
								class="Wdate" />
						</td>
						<td height="25" width="10%" align="right" style="border:0px">终止时间：</td>
						<td height="25" align="left" style="border:0px">
							<input name="endtime" id="endDate" type="text" value="${end }"
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})"
								class="Wdate" />
						</td>
					  </tr>
					   <tr>
						<td align="right" width="10%"></td>
						<td align="left" colspan="9">
							<div style="padding-top:4px"><a href="javascript:;" onclick="doSearchByCenter();"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" /></a></div>
						</td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="40%" >标题</th>
			              <th width="20%">类型</th>
			              <th width="20%">发布时间</th>
			              <th>阅读状态</th>
		                </tr>
		              </thead>
			          <tbody>
			          	<s:iterator value="mgs">
			          			<tr>
					              <td><a href="#" style="color:#49AFCB;" onclick="detail('${MESSAGE_ID }', '${TYPE}', '${TYPENAME}','${UM_ID}' );">${TITLE }</a></td>
					              <td>${TYPENAME }</td>
					              <td>${PUBLISHTIME }</td>
					              <td>
					              	<s:if test="READSTATUS==1">
					              		已读
					              	</s:if>
					              	<s:else>
					              		未读
					              	</s:else>
					              </td>
								 </tr>
						</s:iterator>
						 <tr height="32px">
                            <td height="20" align="right" colspan="4">
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
        <!--右侧内容结束-->
        </s:form>
</body>
</html>