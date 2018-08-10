<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('matterForm').action=id+".action";
			document.getElementById('matterForm').submit();
		}
		
		function doview(id){
			document.getElementById('matterForm').action="view.action?matterId_q="+id;
			document.getElementById('matterForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('matterForm').action="delete.action?matterId_q="+id;
			document.getElementById('matterForm').submit();
		});
		}

	</script>
	</head>
<body >
	<s:form action="matterAction" id="matterForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	  <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">待办事项</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">办理类型：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="mattername" name="mattername" style="width:155px">
								<option value="">--请选择--</option>
							     <option value="领用申请" <s:if test="mattername=='领用申请'">selected="true"</s:if>>领用申请</option>
							     <option value="请示申请" <s:if test="mattername=='请示申请'">selected="true"</s:if>>请示申请</option>
							     <option value="用车申请" <s:if test="mattername=='用车申请'">selected="true"</s:if>>用车申请</option>
							     <option value="发文申请" <s:if test="mattername=='发文申请'">selected="true"</s:if>>发文申请</option>
							     <option value="收文申请" <s:if test="mattername=='收文申请'">selected="true"</s:if>>收文申请</option>
							     <option value="调动申请" <s:if test="mattername=='调动申请'">selected="true"</s:if>>调动申请</option>
							     <option value="离职申请" <s:if test="mattername=='离职申请'">selected="true"</s:if>>离职申请</option>
							     <option value="转正申请" <s:if test="mattername=='转正申请'">selected="true"</s:if>>转正申请</option>
							     <option value="招聘申请" <s:if test="mattername=='招聘申请'">selected="true"</s:if>>招聘申请</option>
							     <option value="报销申请" <s:if test="mattername=='报销申请'">selected="true"</s:if>>报销申请</option>
							     <option value="外出申请" <s:if test="mattername=='外出申请'">selected="true"</s:if>>外出申请</option>
							     <option value="休假申请" <s:if test="mattername=='休假申请'">selected="true"</s:if>>休假申请</option>
							     <option value="出差申请" <s:if test="mattername=='出差申请'">selected="true"</s:if>>出差申请</option>
							</select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">发起人：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="sponsor" id="sponsor" type="text"  value="<s:property value='sponsor'/>" /></td>
						<td height="25" width="15%" align="right" style="border:0px">办理状态：</td>
						<td height="25" width="15%" align="left" style="border:0px" ><input name="handlestatus" type="radio" value="0" <s:if test='handlestatus=="0"'>checked="checked"</s:if> checked="checked"/>未办
																					<input type="radio" name="handlestatus" value="1" <s:if test='handlestatus=="1"'>checked="checked"</s:if> />已办</td>
						
						<td height="25" width="10%" align="left" style="border:0px"></td>
					  </tr><tr>
						<td height="25"  align="right" style="border:0px">起始时间：</td>
						<td height="25"  align="left" style="border:0px" >
						<input name="starttime"  id = "start" type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'end\')||\'2020-01-01\'}'})"   class="Wdate"/></td>
						<td height="25" align="right" style="border:0px">终止时间：</td>
						<td height="25"  align="left" style="border:0px" colspan="4">
						<input name="endtime"  id = "end" type="text"  onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})"  class="Wdate"/></td>
						
					  </tr>
					  
					  <tr>
						<td align="right" width="12%"></td>
						<td align="left" width="88%" colspan="6">
						  <a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></img></a></td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="14%" >办理类型</th>
			              <th width="10%">发起人</th>
			              <th width="10%">执行人</th>
			              <th width="20%">办理内容</th>
			              <th width="10%">发布时间</th>
						  <th width="8%">办理状态</th>
						  <th width="8%">阅读状态</th>
			              <th width="20%" >操作</th>
			              
		                </tr>
		              </thead>
			          <tbody>
			          <s:iterator value="matterList">
			            <tr height="32px">
			              <td ><s:property value="mattername"/></td>
			              <td ><s:property value="sponsor"/></td>
			              <td ><s:property value="executor"/></td>
			              <td ><s:property value="title"/></td>
			              <td ><s:date name="issuetime" format="yyyy-MM-dd"/></td>
						  <td ><s:if test='handlestatus=="0"'>未办理</s:if><s:elseif test='handlestatus=="1"'>已办理</s:elseif></td>
						  <td ><s:if test='readstatus=="0"'>未读</s:if><s:elseif test='readstatus=="1"'>已读</s:elseif></td>						  
			              <td >
							<a href="#" onclick="doview('<s:property value='matterId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0" style="vertical-align:middle;">查看</a>
							<a href="#" onclick="dodelete('<s:property value='matterId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
						  </td>
		                </tr>
			    	</s:iterator>
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
</body>
</html>