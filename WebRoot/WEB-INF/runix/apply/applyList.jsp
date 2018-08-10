<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>请示管理</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<style>
		.small_textbox{width:20px; height:15px;}
		.go{ width:31px; height:26px;margin-top:0px; cursor:pointer}
		.menu_nav{width:100%; height:27px; text-align:left; color:#a4a4a4; line-height:27px; background:#ececec; border-bottom:#a7a7a7 1px solid}
        .menu_nav a{ color:#28528f; text-decoration:none}.menu_nav a:link{ color:#28528f; text-decoration:none}.menu_nav a:hover{ color:#28528f; text-decoration:underline}.menu_nav a:visited{ color:#28528f; text-decoration:underline; background:none}
		.text_box{width:80px;height:20px;}
		#xinxiu_table td{
			text-align:center;
		}
	    .newCreate{ border:1px solid #333333; width:50px; text-align:center; font-size:13px;  height:25px; line-height:20px; margin:2px 0px; background:#afffee;cursor:pointer}	    
	</style>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
	</script>
</head>
<body >                       
    <!--右侧内容-->
	<s:form action="query" namespace="/apply" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
		<div  style="width:99%; margin:0 auto; background-color:#fff;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
			       		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 行政办公 &gt;&gt; <span class="STYLE1">请示管理</span></td>
					  		</tr>
						</table>
						<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						  <tr>
							<td height="25" width="10%" align="right" style="border:0px">请示类型：</td>
							<td height="25" width="10%" align="left" style="border:0px">
								<select id="type" name="type" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="typeList">
									<option value="${dataId}" <s:if test='type == dataId'>selected="selected"</s:if>>${dataname}</option>
									</s:iterator>
								</select>
							</td>
							<td height="25" width="12%" align="right" style="border:0px">请示标题：</td>
							<td height="25" width="10%" align="left" style="border:0px">
								<input type="text" name="title" size="20" value="${title}" />
							</td>
							<td height="25" width="11%" align="right" style="border:0px">秘密等级：</td>
							<td height="25" width="10%" align="left" style="border:0px">
								<select id="secret" name="secret" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="secretList">
									<option value="${dataId}" <s:if test='secret == dataId'>selected="selected"</s:if>>${dataname}</option>
									</s:iterator>
								</select>
							</td>
							<td height="25" width="11%" align="right" style="border:0px">紧急程度：</td>
							<td height="25" width="10%" align="left" style="border:0px">
								<select id="urgency" name="urgency" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="urgencyList">
									<option value="${dataId}" <s:if test='urgency == dataId'>selected="selected"</s:if>>${dataname}</option>
									</s:iterator>
								</select>
							</td>
							<td height="25" width="16%" align="left" style="border:0px"></td>
						  </tr>
						  <tr>
							<td align="right" width="15%"><div style="padding-top:4px"><a href="<%=path %>/apply/insertInit.action" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
							<td align="left" colspan="8"><div style="padding-top:4px"><input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" /></div></td>
						  </tr>   
						</table>
				        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				          <thead height="33px">
				            <tr>
				              <th width="10%" >请示类型</th>
				              <th width="10%">请示标题</th>
				              <th width="9%">秘密等级</th>
				              <th width="9%">紧急程度</th>
				              <th width="10%">申请日期</th>
							  <th width="10%">请示金额</th>
							  <th width="9%">提交状态</th>
							  <th width="9%">审核状态</th>
				              <th width="16%" >操作</th>
			                </tr>
			              </thead>
				          <tbody>
				          	<s:iterator value="applyList">
				            <tr height="32px">
				              <td >${TYPENAME}</td>
				              <td >${TITLE}</td>
				              <td >${SECRETNAME}</td>
				              <td >${URGENCYNAME}</td>
				              <td ><s:date format="yyyy-MM-dd" name="ISSUEDATE" /></td>
							  <td >${MONEY}</td>
							  <td ><s:if test='STATUS == "1"'>已提交</s:if><s:else>已保存</s:else></td>
							  <td ><s:if test='CHECKSTATUS == "1"'>已审核</s:if><s:elseif test='CHECKSTATUS == "2"'>审核中</s:elseif><s:else>未审核</s:else></td>
							  <td >
							  	<s:if test='STATUS != "1"'>
								<a href="<%=path%>/apply/updateInit.action?applicationId_q=${APPLICATION_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
								</s:if>
								<a href="<%=path%>/apply/detail.action?applicationId_q=${APPLICATION_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
								<a href="<%=path%>/apply/delete.action?applicationId_q=${APPLICATION_ID}&type=${type}&title=${title}&secret=${secret}&urgency=${urgency}&currentPage=${currentPage}" onclick="return confirm('确定删除吗？');"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
							  </td>	
			                </tr>
			                </s:iterator>
							 <tr height="32px">
	                            <td height="20" colspan="9" align="right">
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
