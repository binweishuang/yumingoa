<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>合同管理列表</title>
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
    <s:form action="query" namespace="/contract" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
	    <div  style="width:99%; margin:0 auto; background-color:#fff;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
			       		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 人力资源 &gt;&gt; <span class="STYLE1">合同管理</span></td>
					  		</tr>
						</table>
						<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						  <tr>
							<td height="25" width="12%" align="right" style="border:0px">合同状态：</td>
							<td height="25" width="12%" align="left" style="border:0px">
								<select id="status" name="status" style="width:155px">
									<option value="0">--请选择--</option>
									<option value="1" <s:if test='status == "1"'>selected="selected"</s:if> >已执行</option>
									<option value="2" <s:if test='status == "2"'>selected="selected"</s:if> >执行中</option>
									<option value="3" <s:if test='status == "3"'>selected="selected"</s:if> >未执行</option>
								</select>
							</td>
							<td height="25" width="12%" align="right" style="border:0px">合同类型：</td>
							<td height="25" width="12%" align="left" style="border:0px">
								<select id="contracttype" name="contracttype" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="contracttypeList">
									<option value="${dataId}" <s:if test='contracttype == dataId'>selected="selected"</s:if> >${dataname}</option>
									</s:iterator>
								</select>
							</td>
							<td height="25" width="12%" align="right" style="border:0px">相关部门：</td>
							<td height="25" width="12%" align="left" style="border:0px">
								<select id="department" name="department" style="width:155px">
									<option value="0">--请选择--</option>
									<s:iterator value="deptList">
									<option value="${deptId}" <s:if test='department == deptId'>selected="selected"</s:if> >${deptname}</option>
									</s:iterator>
								</select>
							</td>
							<td height="25" width="28%" align="left" style="border:0px"></td>
						  </tr>
						  <tr>
							<td align="right"><div style="padding-top:4px"><a href="<%=request.getContextPath() %>/contract/insertInit.action" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></div></td>
							<td align="left" colspan="6"><div style="padding-top:4px"><input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" /></div></td>
						  </tr>   
						</table>
				        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				          <thead height="33px">
				            <tr>
				              <th width="8%" >签订人</th>
				              <th width="10%">相关部门</th>
				              <th width="10%">合同类型</th>
				              <th width="8%">合同状态</th>
				              <th width="10%">客户单位</th>
							  <th width="8%">联系人</th>
							  <th width="10%">联系电话</th>
							  <th width="10%">签订日期</th>
							  <th width="10%">终止日期</th>
				              <th width="16%" >操作</th>
			                </tr>
			              </thead>
				          <tbody>
				          	<s:iterator value="contractList">
				            <tr height="32px">
				              <td >${SIGNEDPERSONNAME}</td>
				              <td >${DEPTNAME}</td>
				              <td >${CONTRACTTYPENAME}</td>
				              <td ><s:if test='STATUS == "1"'>已执行</s:if><s:elseif test='STATUS == "2"'>执行中</s:elseif><s:else>未执行</s:else></td>
				              <td >${CUSTOMECOMPANY}</td>
							  <td >${CONTACT}</td>
							  <td >${CONTACTTEL}</td>
							  <td ><s:date format="yyyy-MM-dd" name="SIGNEDDATE" /></td>
							  <td ><s:date format="yyyy-MM-dd" name="ENDDATE" /></td>
							  <td >
								 <a href="<%=request.getContextPath() %>/contract/updateInit.action?contractId_q=${CONTRACT_ID}" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
								 <a  href="<%=request.getContextPath() %>/contract/detail.action?contractId_q=${CONTRACT_ID}"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
								 <a  href="<%=request.getContextPath() %>/contract/delete.action?contractId_q=${CONTRACT_ID}&status=${status}&contracttype=${contracttype}&department=${department}&currentPage=${currentPage}" onclick="return confirm('确定删除吗？');"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
							 </td>				              
			                </tr>
			                </s:iterator>
							 <tr height="32px">
	                            <td height="20" colspan="10" align="right">
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
