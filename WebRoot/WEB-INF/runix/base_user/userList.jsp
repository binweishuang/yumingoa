<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

<meta charset="utf-8"></meta>
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix"></meta>
<meta name="author" content="Runix Inc."></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/framework/js/baseUser.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
</script>
<style type="text/css">
#deptId,#positionId,#state {
	width: 92%;
	height: 23px;
}

#name {
	width: 54%;
	height: 18px;
}
</style>
</head>
<body>
	<s:form id="form" theme="simple" method="post" target="_self">
		<s:hidden name="currentPage" value="currentPage"></s:hidden>
		<!--右侧内容-->
		<div style="width:99%; margin:0 auto; background-color:#fff;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置：系统管理 &gt;&gt; 
									<span class="STYLE1">用户管理</span>
								</td>
							</tr>
						</table>
						<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							<tr>
								<td height="25" width="10%" align="right" style="border:0px">部门：</td>
								<td height="25" width="15%" align="left" style="border:0px">
									<s:select id="deptId" name="deptId" headerKey="-1"
										headerValue="--请选择--" list="depts" listKey="deptId"
										listValue="deptname" theme="simple" value="deptId"
										onblur="checkDept();" />
								</td>
								<td height="25" width="7%" align="right" style="border:0px">职位：</td>
								<td height="25" width="15%" align="left" style="border:0px">
									<s:select id="positionId" name="positionId" headerKey="-1"
										headerValue="--请选择--" list="positions" listKey="positionId"
										listValue="postname" theme="simple" value="positionId"
										onblur="checkPosition();" />
								</td>
								<td height="25" width="7%" align="right" style="border:0px">任职状态：</td>
								<td height="25" width="15%" align="left" style="border:0px">
									<s:select id="state" name="state" headerKey="-1"
										headerValue="--请选择--" list="#{'0':'离职', '1':'在职' }"
										listKey="key" listValue="value" theme="simple" value="state" />
								</td>
								<td height="25" width="7%" align="right" style="border:0px">姓名：</td>
								<td height="25" align="left" style="border:0px">
									<input id="name" name="name" type="text" value="${name }" />
								</td>
							</tr>
							<tr>
								<td align="right" width="10%">
									<div style="padding-top:4px">
										<a href="addIndex.action">
											<img src="<%=request.getContextPath()%>/framework/img/add.png" border="0" />
										</a>
									</div>
								</td>
								<td align="left" colspan="7">
									<div style="padding-top:4px">
										<a href="javascript:;" onclick="doSearch();"><img
											src="<%=request.getContextPath()%>/framework/img/search.png" border="0" /> </a>
									</div>
								</td>
							</tr>
						</table>
						<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							<thead>
								<tr height="33">
									<th width="8%">姓名</th>
									<th width="10%">登陆账号</th>
									<th width="10%">部门</th>
									<th width="10%">职务</th>
									<th width="4%">性别</th>
									<th width="12%">邮箱</th>
									<th width="10%">手机号码</th>
									<th width="8%">任职状态</th>
									<th width="8%">是否屏蔽</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="list!=null && !list.isEmpty()">
									<s:iterator value="list">
										<tr>
											<td>${NAME }</td>
											<td>${USERNAME }</td>
											<td>${DEPTNAME }</td>
											<td>${POSTNAME }</td>
											<td><s:if test="SEX==1">男</s:if> <s:else>女</s:else></td>
											<td>${EMAIL }</td>
											<td>${PHONE }</td>
											<td><s:if test="STATE==0">
											离职
										</s:if> <s:else>
											在职
										</s:else></td>
											<td><s:if test="FLAG==1">
											是
										</s:if> <s:else>
											否
										</s:else></td>
											<td>
												<a href="editIndex.action?user.userId=${USER_ID}">
													<img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;"></img>修改
												</a> 
												<a href="detail.action?user.userId=${USER_ID}&user.deptId=${DEPT_ID}&user.positionId=${POSITION_ID}">
													<img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;"></img>查看
												</a> 
												<a href="javascript:;" onclick="doDelete(${USER_ID});">
													<img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;"></img>删除
												</a>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td colspan="10" align="center"><span style="color:RED;">无记录</span>
										</td>
									</tr>
								</s:else>
								<tr height="32px">
									<td height="20" align="right" colspan="10">
										<!--分页开始--> <s:include value="/framework/include/page.jsp" />
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