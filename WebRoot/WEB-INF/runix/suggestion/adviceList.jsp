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
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/suggestion.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
	</script>
	<style type="text/css">
		#suggesttype{width:92%;height:23px;}
		#publicity{width:21%;height:23px;}
	</style>
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
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">个人建议</span></td>
					  </tr>
					</table>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="10%" align="right" style="border:0px">建议类型：</td>
						<td height="25" width="14%" align="left" style="border:0px">
							<s:select id="suggesttype" name="suggesttype" list="types" headerKey="-1" headerValue="--请选择--" listKey="dataId" listValue="dataname" value="suggesttype" theme="simple"></s:select>
						</td>
						<td height="25" width="15%" align="right" style="border:0px">是否公开：</td>
						<td height="25" align="left" style="border:0px">
							<s:select id="publicity" name="publicity" list="#{'0':'否', '1':'是' }" headerKey="-1" headerValue="--请选择--" listKey="key" listValue="value" value="publicity" theme="simple"></s:select>
						</td>
					  </tr>
					  <tr>
						<td align="right" width="10%"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a href="addIndex.action" ><img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"></a></s:else></div></td>
						<td align="left" colspan="3">
							<div style="padding-top:4px"><a href="javascript:;" onclick="doSearchBySuggestion();"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" ></a></div>
						</td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="30%" >建议主题</th>
			              <th width="13%">建议类型</th>
			              <th width="13%">是否公开</th>
						   <th width="25%">建议人员</th>
						  <th>操作</th>
		                </tr>
		              </thead>
			          <tbody>
			          	<s:if test="suggestions!=null && !suggestions.isEmpty()">
			          		<s:iterator value="suggestions">
			          			 <tr>
						              <td>${TITLE }</td>
						              <td>${SUGGESTTYPE }</td>
						              <td>
									  	<s:if test="PUBLICITY==0">
									  		否
									  	</s:if>
									  	<s:else>
									  		是
									  	</s:else>
									  </td>
									   <td>${TOPERSON }</td>
									  <td>
									  <s:if test="userid==SUGESTER">
										 <a href="modifyIndex.action?suggestionId=${SUGGESTION_ID}" ><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
										</s:if>
										 <a href="detail.action?suggestionId=${SUGGESTION_ID}&suggesttype=${SUGGESTTYPE }&toperson=${TOPERSON }"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
										 <s:if test="userid==SUGESTER">
										 <a href="javascript:;" onclick="del(${SUGGESTION_ID})"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
									     </s:if>
									  </td>
					              </tr>
			          		</s:iterator>
			          	</s:if>
			          	<s:else>
			          		<tr>
				              <td align="center" colspan="5"><span style="color:RED;">无记录</span></td>
			                </tr>
			          	</s:else>
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
        <!--右侧内容结束-->
        </s:form>
</body>
</html>