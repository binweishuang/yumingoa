<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="kdf.constant.SystemConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>客户通讯录列表</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
			if(${grouptype} != "0"){
				$("#group_"+${grouptype}).attr("selected","selected");
			}
			if(${sex} != "3"){
				$("#sex_"+${sex}).attr("selected","selected");
			}
		});
	</script>
</head>
<body >
      <!--右侧内容-->
      <s:form action="query" namespace="/addressbook" theme="simple" id="form1" method="post">
      <s:hidden name="currentPage" value="currentPage"></s:hidden>
	  <div  style="width:99%;margin:0 auto; background-color:#fff;">
	  <input type="hidden" name="flag" value="${flag}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">通讯录查询</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">查询范围：</td>
						<td height="25" width="10%" align="left" style="border:0px">
							<select name="grouptype">
								<option value="0">--请选择--</option>
								<s:iterator value="addrgroupList">
								<option value="${dataId}" id="group_${dataId}">${dataname}</option>
								</s:iterator>
							</select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">性别：</td>
						<td height="25" align="left" style="border:0px">
							<select name="sex">
								<option value="3">--请选择--</option>
								<option value="0" id="sex_0">男</option>
								<option value="1" id="sex_1">女</option>
							</select>
						</td>
					  </tr>
					  <tr>
						<td align="right" width="10%"><div style="padding-top:4px"></div></td>
						<td align="left" colspan="3">
							<div style="padding-top:4px">
								<input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" />
							</div>
						</td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="11%" >姓名</th>
			              <th width="6%">性别</th>
			              <th width="19%">单位</th>
						   <th width="13%">单位电话</th>
						   <th width="13%" >移动电话</th>
			              <th width="16%">e-mail</th>
			              <th width="10%">组别</th>
						  <th>操作</th>
		                </tr>
		              </thead>
			          <tbody>
			          	<s:iterator value="addressbookList">
			              <tr>
			              <td>${NAME}</td>
			              <td><s:if test='SEX == "0"'>男</s:if><s:else>女</s:else></td>
			              <td>${COMPANY}</td>
						   <td>${OFFICETEL}</td>
						   <td>${MOBILEPHONE}</td>
			               <td>${EMAIL}</td>
			               <td>${GROUPTYPENAME}</td>
						  <td>
							 <a href="<%=request.getContextPath() %>/addressbook/detail.action?addrbookId_q=${ADDRBOOK_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0" style="vertical-align:middle;">查看</a>
						  </td>
		                </tr>
		                </s:iterator>
		                <tr></tr>
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
