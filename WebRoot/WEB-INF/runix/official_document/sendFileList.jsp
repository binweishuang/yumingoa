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
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"> 
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('officialDocumentForm').action=id+".action";
			document.getElementById('officialDocumentForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('officialDocumentForm').action="updateInit.action?documentId_q="+id;
			document.getElementById('officialDocumentForm').submit();
		}
		function doview(id){
			document.getElementById('officialDocumentForm').action="view.action?documentId_q="+id;
			document.getElementById('officialDocumentForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
				document.getElementById('officialDocumentForm').action="delete.action?documentId_q="+id;
				document.getElementById('officialDocumentForm').submit();
			});
		}
		

	</script>
	</head>
<body >
	<s:form action="officialDocumentAction" id="officialDocumentForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="flag" ></s:hidden>
	 <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 公文管理 &gt;&gt; <span class="STYLE1">发文管理</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">发文类型：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="doctype" name="doctype" style="width:155px">
								<option value="">--请选择--</option>
								<s:iterator value="sendTypeList" >
							     <option value="<s:property value='dataId'/>" <s:if test='dataId==doctype'>selected="true"</s:if>><s:property value='dataname'/></option>
							    </s:iterator>
							</select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">秘密等级：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="secret" name="secret" style="width:155px">
								<option value="">--请选择--</option>
								<s:iterator value="secretList" >
							     <option value="<s:property value='dataId'/>" <s:if test='dataId==secret'>selected="true"</s:if>><s:property value='dataname'/></option>
							    </s:iterator>
							</select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">紧急程度：</td>
						<td height="25" width="15%" align="left" style="border:0px">
							<select id="urgency" name="urgency" style="width:155px">
								<option value="">--请选择--</option>
								<s:iterator value="urgencyList" >
							     <option value="<s:property value='dataId'/>" <s:if test='dataId==urgency'>selected="true"</s:if>><s:property value='dataname'/></option>
							    </s:iterator>
							</select>
						</td>
						<td height="25" width="10%" align="left" style="border:0px"></td>
					  </tr>
					  <tr>
							<td align="right"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="#" onclick="doObject('insertInit')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else></div></td>
							<td align="left" colspan="6"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
						</tr>   
					  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="10%" >发文类型</th>
			              <th width="12%">发文标题</th>
			              <th width="10%">主题词</th>
			              <th width="12%">发文单位</th>
			              <th width="8%">秘密等级</th>
						  <th width="8%">紧急程度</th>
						  <th width="8%">保存期限</th>
						  <th width="8%">提交状态</th>
						  <th width="8%">审核状态</th>
			              <th width="16%" >操作</th>		              
		                </tr>
		              </thead>
			          <tbody>
			          <s:iterator value="officialDocumentList">
			            <tr height="32px">
			              <td ><s:property value="DOCTYPE"/></td>
			              <td ><s:property value="TITLE"/></td>
			              <td ><s:property value="SUBJECTTERM"/></td>
			              <td ><s:property value="COMPANY"/></td>
			              <td ><s:property value="SECRET"/></td>
						  <td ><s:property value="URGENCY"/></td>
						  <td ><s:property value="TIMELIMIT"/></td>
						  <td ><s:if test='STATUS=="0"'>未提交</s:if><s:elseif test='STATUS=="1"'>已提交</s:elseif></td>
						  <td ><s:if test='CHECKSTATUS=="1"'>已审核</s:if><s:elseif test='CHECKSTATUS=="2"'>审核中</s:elseif><s:elseif test='CHECKSTATUS=="3"'>未审核</s:elseif></td>
						  <td >
						  <s:if test="readonly==true">
						  <s:if test='STATUS=="1"'><a  href="#" onclick="doview('<s:property value='DOCUMENTID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
						  </s:if><s:else>
							 <s:if test='STATUS=="0"'><a href="#" onclick="doupdate('<s:property value='DOCUMENTID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
							<s:if test='STATUS=="1"'><a  href="#" onclick="doview('<s:property value='DOCUMENTID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a></s:if>
							<a  href="#" onclick="dodelete('<s:property value='DOCUMENTID'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a>
						 </s:else>
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
</body>
</html>