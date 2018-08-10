<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="kdf.constant.SystemConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>通讯录管理</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
			if(${addrtype} != "0"){
				$("#addr_"+${addrtype}).attr("selected","selected");
			} 
			if(${grouptype} != "0"){
				$("#group_"+${grouptype}).attr("selected","selected");
			}
			if(${sex} != "3"){
				$("#sex_"+${sex}).attr("selected","selected"); 
			} 
		});
		
		function dodel(id){
			Dialog.confirm("确定删除吗？",function(){
				location = "<%=request.getContextPath() %>/addressbook/delete.action?addrbookId_q="+id+"&addrtype=${addrtype}&grouptype=${grouptype}&sex=${sex}&currentPage=${currentPage}";
			});
		}
	</script>
	</head>
<body >
    <!--右侧内容-->
    <s:form action="manage.action" namespace="/addressbook" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
    <div  style="width:99%; margin:0 auto; background-color:#fff;">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
       				<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  				<tr>
							<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公共信息 &gt;&gt; <span class="STYLE1">通讯录管理</span></td>
		  				</tr>
					</table>
					
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
					  		<td height="25" width="12%" align="right" style="border:0px">姓名：</td>
							<td height="25" width="12%" align="left" style="border:0px">
						    	<input type="text"  name = "name">
							</td>
						  <td height="25" width="12%" align="right" style="border:0px">通讯录类型：</td>
							<td height="25" width="12%" align="left" style="border:0px">
								<select id="addrtype" name="addrtype" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="addrtypeList">
									<option value="${dataId}" id="addr_${dataId}">${dataname}</option>
									</s:iterator>
								</select>
						  </td>
						<td height="25" width="12%" align="right" style="border:0px">性    别：</td>
						<td height="25" width="12%" align="left" style="border:0px">
							<select name="sex">
								<option value="3">--请选择--</option>
								<option value="0" id="sex_0">男</option>
								<option value="1" id="sex_1">女</option>
							</select>
						</td>
						<td height="25" width="28%" align="left" style="border:0px"></td>
					  </tr>
					  <tr>
						  <td align="right"><div style="padding-top:4px"><a href="<%=request.getContextPath() %>/addressbook/insertInit.action" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
						  <td align="left" colspan="6"><div style="padding-top:4px"><input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png"  border="0" /></div></td>
					  </tr>   
					</table>
					
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="10%" >类型</th>
			              <th width="10%">姓名</th>
			              <th width="7%">性别</th>
			              <th width="15%">单位</th>
			              <th width="12%">单位电话</th>
						  <th width="12%">移动电话</th>
						  <th width="12%">e-mail</th>
						  <th width="7%">是否共享</th>
			              <th width="15%" >操作</th>
			             </tr>
			          </thead>
			          <tbody>
			          	<s:iterator value="addressbookList">
			            <tr height="32px">
			              <td ><s:property value="ADDRTYPENAME" /></td>
			              <td >${NAME}</td>
			              <td ><s:if test='SEX == "0"'>男</s:if><s:else>女</s:else>  </td>
			              <td >${COMPANY}</td>
						  <td >${OFFICETEL}</td>
						  <td >${MOBILEPHONE}</td>
			              <td >${EMAIL}</td>
						  <td ><s:if test='FLAG == "0"'>否</s:if><s:else>是</s:else> </td>
						  <td >
							<a  href="<%=request.getContextPath() %>/addressbook/updateInit.action?addrbookId_q=${ADDRBOOK_ID}" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
							<a  href="<%=request.getContextPath() %>/addressbook/detail.action?addrbookId_q=${ADDRBOOK_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
							<a  href="<%=request.getContextPath() %>/addressbook/delete.action?addrbookId_q=${ADDRBOOK_ID}&addrtype=${addrtype}&grouptype=${grouptype}&sex=${sex}&currentPage=${currentPage}" onclick="dodel(${ADDRBOOK_ID});"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
						  </td>
			            </tr>
			            </s:iterator>
			            <tr></tr>
						<tr height="32px">
	                         <td height="20" colspan="10" align="right">
	                            <!--分页开始-->
								<s:include value="/framework/include/pageManage.jsp"/>
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
