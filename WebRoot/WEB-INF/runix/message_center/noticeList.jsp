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
	</script>
	<style type="text/css">
		#type{width:94%;height:23px;}
		#dept{width:34%;height:23px;}
	</style>
	</head>
<body >
             <s:form  id="form" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>                         
            <!--右侧内容-->
			  <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">通知管理</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">标　　题：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="title" type="text" style="width:93%; height:18px;" value=${title }></input></td>
						<td height="25" width="10%" align="right" style="border:0px">通知类型：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<s:select id="type" name="type" headerKey="-1"
										headerValue="--请选择--" list="types" listKey="dataId"
										listValue="dataname" theme="simple" value="type"/>
						</td>
				<!--		<td height="25" width="10%" align="right" style="border:0px">发布部门：</td>
						<td height="25" align="left" style="border:0px">
							<s:select id="dept" name="dept" headerKey="-1"
										headerValue="--请选择--" list="depts" listKey="deptId"
										listValue="deptname" theme="simple" value="dept"/>
						</td>  -->
						<td height="25" width="10%" align="right" style="border:0px"></td>
						<td height="25" align="left" style="border:0px">
						</td>
					  </tr>
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">起始日期：</td>
							<td height="25" width="15%" align="left" style="border:0px"><input name="starttime" id="start" type="text" style="height: 18px;"
								value="${start }"
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')||\'2020-01-01\'}'})"
								class="Wdate" /></td>
							<td height="25" width="10%" align="right" style="border:0px">终止日期：</td>
							<td height="25" width="15%" align="left" style="border:0px"><input name="endtime" id="endDate" type="text" style="height: 18px;"
								value="${end }"	
								onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})"
								class="Wdate" /></td>
							<td height="25" width="10%" align="right" style="border:0px"></td>
							<td height="25" align="left" style="border:0px"></td>
					  </tr>
					  <tr>
						<td align="right" width="10%"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="addNoticeIndex.action" ><img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"/></a></s:else></div></td>
						<td align="left" colspan="9">
							<div style="padding-top:4px"><a href="javascript:;" onclick="doSearchByNotice();"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" /></a></div>
						</td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="9%" >发布人</th>
			              <th width="9%">发布部门</th>
			              <th width="9%">通知类型</th>
			              <th width="20%">标题</th>
			              <th width="9%">发布时间</th>
			              <th width="9%" >起始日期</th>
						  <th width="9%" >终止日期</th>
						  <th>操作</th>
		                </tr>
		              </thead>
			          <tbody>
			          	<s:if test="mgs!=null && !mgs.isEmpty()">
			          		<s:iterator value="mgs">
			          			<tr>
					              <td>${PUBLISHER }</td>
					              <td>${DEPT }</td>
					              <td>${TYPE }</td>
					              <td>${TITLE }</td>
					              <td>${PUBLISHTIME }</td>
								  <td>${STARTTIME }</td>
								  <td>${ENDTIME }</td>
								  <td>
								  <s:if test="readonly==true">
								  <a href="noticeDetail.action?messageId=${MESSAGE_ID }&type=${TYPE}&dept=${DEPT}"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
								  </s:if><s:else><s:if test="STATUS==0">
									 <a href="modifyNoticeIndex.action?messageId=${MESSAGE_ID }" ><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
									 </s:if>
									 <a href="noticeDetail.action?messageId=${MESSAGE_ID }&type=${TYPE}"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
									 <a href="javascript:;" onclick="delNotice(${MESSAGE_ID });"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
								  </s:else>
								  </td>
				                </tr>
			          		</s:iterator>
			          	</s:if>
						 <tr height="32px">
                            <td height="20" align="right" colspan="10">
                                <!--分页开始-->
                                <s:include value="/framework/include/notice.jsp"/>
                                <!--分页结束-->
                            </td>
                        </tr>
		              </tbody>
		            </table>		
						</td>
						</tr>
						</table>
			</div>
        <!--右侧内容结束--></s:form>
</body>
</html>