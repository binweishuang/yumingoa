<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String realPath =application.getRealPath("") ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Runix - the Runix Admin Template</title>
<meta name="description" content="An admin template from Runix" />
<meta name="author" content="Runix Inc." />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("table tbody tr").mouseover("background-color", "");
	});

	function del(id,path) {
		if (Dialog.confirm("确认删除吗？",function(){
			location.href = "delete.action?fileId=" + id + "&filepath="+path;
		}));
	}
	function delFolder(id) {
		if (Dialog.confirm("该文件夹或许存在文件,您确认要全部删除吗？",function(){
			location.href = "<%=request.getContextPath()%>/folder/delete.action?folderId_q=" + id;
		}));
	}
	function open(id) {
		location.href = "query.action?folderId="+id+"&flag=1";
	}
	
	function goAdd(){
		location.href = "<%=request.getContextPath()%>/folder/insertInit.action";
	}
	function sub() {
		document.form.submit();
	}
</script>
</head>
<body>
	<!--右侧内容-->
	<div style="width:99%; margin:0 auto; background-color:#fff;">
		<s:form name="form" id="form" action="query.action" method="post" theme="simple" target="_self">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" />
			<input type = "hidden" id = "folderID" name = "folderId" value="${folderId }"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置：公共信息 &gt;&gt; 
									<span class="STYLE1">网络硬盘</span>
								</td>
							</tr>
						</table>
						<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							<tr>
								<td height="25" align="right" style="border:0px">文件名：</td>
								<td height="25" align="left" style="border:0px">
									<input name="filename" type="text" value="${filename}" />
								</td>
								<td height="25" align="right" style="border:0px">上传时间：</td>
								<td height="25" align="left" style="border:0px">
									<input name="upDate" type="text" value="${upDate}" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})" class="Wdate" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<div style="padding-top:4px">
										<a href="insertInit.action?folderId=${folderId }">
											<img src="<%=request.getContextPath()%>/framework/img/add.png" border="0" />
										</a>
									</div>
								</td>
								<td align="left">
									<div style="padding-top:4px">
										<a href="javascript:sub();">
											<img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" />
										</a>
									</div>
								</td>
								<td align="right" colspan="2">
									<div style="padding-top:4px">
										<input type="button" value="新建文件夹" onclick="goAdd();" class="buttons"/>
										<input type="button" value="返回" onclick="javascript:window.history.back(-1)" class="buttons"/>
									</div>
								</td>
							</tr>
						</table>
						<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
							<thead height="33px">
								<tr>
									<th width="20%">文件名称</th>
									<th width="20%">文件大小</th>
									<th width="20%">上传时间</th>
									<th width="20%">是否共享</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="netdiskList.size()>0">
									<s:iterator value="netdiskList">
										<s:if test='FOLDERNAME != null and FOLDERNAME != ""'>
											<tr height="32px">
												<td style="text-align: left;padding-left: 30px"><a href="javascript:open('${FOLDER_ID}');"><img src="<%=request.getContextPath()%>/images/file.png"  border="0" style="vertical-align:middle;" height="18px" width="18px"></img>&nbsp;${FOLDERNAME}</a></td>
												<td colspan="3"></td>
												<td><s:if test="readonly==false">
													<a href="javascript:delFolder('${FOLDER_ID}');">
														<img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;" />删除
													</a></s:if>
												</td>
											</tr>
										</s:if>
										<s:if test='FOLDERNAME == null or FOLDERNAME == ""'>
										<tr height="32px">
											<td style="text-align: left;padding-left: 30px">${FILENAME}</td>
											<td>${FILESIZE}</td>
											<td><s:date format="yyyy-MM-dd" name="UPLOADTIME" /></td>
											<td><s:if test="PUBLICITY ==1">是</s:if><s:else>否</s:else>
											</td>
											<td>
											<s:if test="UPLOADER ==userId or readonly==false">
											<a href="updateInit.action?fileId=${FILE_ID}">
													<img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;" />修改
													</a>
												<a href="javascript:del('${FILE_ID}','${FILEPATH}');">
													<img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;" />删除
												</a></s:if>
												<a href="<%=path%>${FILEPATH}" target="_blank">
													<img src="<%=request.getContextPath()%>/framework/img/download.png" border="0" style="vertical-align:middle;" height="22px" width="22px"/>下载
												</a>
											</td>
										</tr>
										</s:if>
									</s:iterator>
								</s:if>
								<tr height="32px">
									<td height="20" colspan="6" align="right">
										<!--分页开始-->
											<s:include value="/framework/include/page.jsp" />
										<!--分页结束-->
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<!--右侧内容结束-->
</body>
</html>