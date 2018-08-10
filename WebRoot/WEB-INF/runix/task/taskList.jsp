<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8"/>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"/>
	<meta name="author" content="Runix Inc."/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<style type="text/css">
	#con { font-size:12px; width:100%;height:100%;}
	#tags { padding:0; margin:0; width:800px; height:26px;}
	#tags li { float:left; padding-left:10px; padding-right:10px; margin-right:2px;height:26px; list-style-type:none; background:url(<%=request.getContextPath()%>/framework/img/tagleft.gif) no-repeat left bottom;}
	#tags li A { padding:0 5px; float:left; color:#fff; line-height:26px; height:26px; text-decoration:none; }
	#tags li.selectTag { background-position:left top; margin-bottom:0px; position:relative; height:26px}
	#tags li.selectTag A {color:#000; line-height: 25px; height: 25px}
	#tagContent { background-color: #fff ;width:100%;height:100%}
	.tagContent { padding:0px; display:none; width:100%; color: #474747; height:100%; text-align:center }
	#tagContent{
	box-shadow:3px 3px 3px #959595;
	-moz-box-shadow:10px 10px 5px #959595;
	-webkit-box-shadow:10px 10px 5px #959595;
	position:relative;z-index:1;}
	#tagContent div.selectTag {display: block}
	.td_bg_gray{background:#b2b2b2}
	</style>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<!--右侧内容结束-->
	<script type=text/javascript>
		function selectTag(showContent,selfObj){
			// 操作标签
			var tag = document.getElementById("tags").getElementsByTagName("li");
			var taglength = tag.length;
			for(i=0; i<taglength; i++){
				tag[i].className = "";
			}
			selfObj.parentNode.className = "selectTag";
			// 操作内容
			for(i=0; j=document.getElementById("tagContent"+i); i++){
				j.style.display = "none";
			}
			document.getElementById(showContent).style.display = "block";	
		}
	</script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		$(document).ready(function(){
			var flagTask = "${flagTask}";
			//alert(flagTask);
			var tag = document.getElementById("tags").getElementsByTagName("li");
			var taglength = tag.length;
			if(flagTask==0){
				tag[0].className = "";
				tag[1].className = "selectTag";
				document.getElementById("tagContent0").style.display = "none";
				document.getElementById("tagContent1").style.display = "block";
			}
		});
		
		function selectReporter(){
			var date = new Date();
			//弹出选择上报对象窗口
			var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			if(str != "" && str != null){
				var arr = str.split("%");
				$("#reportTo").val(arr[0]);
				$("#reportToId").val(arr[1]);
			}
		}
		
		//选择执行对象
		function selectExecuter(){
			var date = new Date();
			//弹出选择上报对象窗口
			var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			if(str != "" && str != null){
				var arr = str.split("%");
				$("#execuTer").val(arr[0]);
				$("#execuTerId").val(arr[1]);
			}
		}
		
		function selectTask(t){
			//下达任务
			if(t==0){
				//location.href = "query.action";
				$("#flagTask").val(0);
				document.form.submit();
			}

			//执行任务
			if(t==1){
				//location.href = "exeTask.action";
				$("#flagTask").val(1);
				document.form.submit();
			}
		}
		
		function subForm(){
			document.form.submit();
		}
		
	</script>
	</head>
<body >    
            <!--右侧内容-->
			  <div  style="width:99%;">
			  		<s:form name="form" id="form" action="query.action" method="post" theme="simple" target="_self">
			  		<%--  <s:hidden name="currentPage" value="currentPage"></s:hidden> --%>
			  		 <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			  		 <input type="hidden" id="flagTask" name="flagTask" value="${flagTask}"/>
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
			       <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" bgcolor="#e7f4fe">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">任务管理</span></td>
					  </tr>
					</table>
					<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;"  >
					  <tr>
						<td height="25" align="right" style="border:0px">任务状态：</td>	
						<td height="25" align="left" style="border:0px">
							<select name="flag">
								<option value="">请选择</option>
								<option value="0">未完成</option>
								<option value="1">已完成</option>
							</select>
						</td>
						<td height="25" align="right" style="border:0px">汇报人：</td>
						<td height="25" align="left" style="border:0px">
							<input type="text" id="reportTo" onclick="selectReporter();" value=""/>
            				<input type="hidden" id="reportToId" name="reportTo"/>
						</td>
						<td height="25" align="right" style="border:0px">执行人：</td>
						<td height="25" align="left" style="border:0px">
							<input type="text" id="execuTer" onclick="selectExecuter();" value=""/>
            				<input type="hidden" id="execuTerId" name="executer"/>
						</td>
						
					  </tr>
					  
					  <tr>
						<td align="right" width="10%">
							<a href="insertInit.action" >
									<img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"/>
							</a>
						</td>
						<td align="left" colspan="5">
						<table width="30%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td align="left">
								<a href="javascript:subForm();"><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0"/></a>
							</td>
							<td align="center">&nbsp;</td>
						  </tr>
						</table>
						</td>
					  </tr>  
					</table>
					<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">

						<tr>
							<td valign="top" class="td_bg_gray">
							<!--tab 开始-->
							<div id="con">
								<ul id="tags">
								  <li class="selectTag">
								  	<a onclick="selectTag('tagContent0',this)" href="query.action?flagTask=1">执行任务</a>
								  <!-- 	<a onclick="selectTag('tagContent0',this)" href="javascript:selectTask(1);">执行任务</a> -->
								  </li>
								  <li>
								  	<!-- <a onclick="selectTag('tagContent1',this)" href="javascript:selectTask(0);">下达任务</a> -->
								  	<a onclick="selectTag('tagContent1',this)" href="query.action?flagTask=0">下达任务</a>
								  </li>
								</ul>
								<div id="tagContent">
									<div class="tagContent selectTag" id="tagContent0">
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="10%" >汇报人</th>
												  <th width="10%">执行人</th>
												  <th width="15%">任务标题</th>
												  <th width="10%">任务总进度</th>
												  <th width="10%">汇报时限</th>
												  <th width="10%">开始时间</th>
												  <th width="10%">结束时间</th>
												  <th width="10%">完成情况</th>
												  <th width="15%">操作</th>															  
												</tr>
											  </thead>
											  <tbody>
											    <s:if test="exeTasks.size()>0">
											    <s:iterator value="exeTasks">
												<tr>										 
												  <td height="25">${REPORTTO }</td>
												  <td>${EXECUTER }</td>
												  <td>${TITLE }</td>
												  <td>${PROCESS }</td>
												  <td>${TIMELIMIT }</td>
												  <td><s:date format="yyyy-MM-dd HH:mm:ss" name="EXESTARTTIME"/></td>
												  <td><s:date format="yyyy-MM-dd HH:mm:ss" name="EXEENDTIME"/></td>
												  <td>${COMPLETION}</td>
												  <td ><a href="exeTask.action?taskId=${TASK_ID}">执行任务</a></td>
												</tr>
												</s:iterator>
												</s:if>
												<tr height="32px">
													<td height="20" colspan="9" align="right">
														<!--分页开始-->
														 <s:include value="/framework/include/page.jsp"/>
														<!--分页结束-->
													</td>
												</tr>
											  </tbody>
										</table>	
									</div>
									<div class="tagContent" id="tagContent1">
										<table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
											  <thead height="33px">
												<tr>
												  <th width="10%" >汇报人</th>
												  <th width="10%">执行人</th>
												  <th width="15%">任务标题</th>
												  <th width="10%">任务总进度</th>
												  <th width="10%">汇报时限</th>
												  <th width="10%">开始时间</th>
												  <th width="10%">结束时间</th>
												  <th width="10%">完成情况</th>												
												  <th>操作</th>														  
												</tr>
											  </thead>
											  <tbody>
											  	<s:if test="tasks.size()>0">
											  	<s:iterator value="tasks">
												<tr>										 
												  <td height="25">${REPORTTO }</td>
												  <td>${EXECUTER }</td>
												  <td>${TITLE }</td>
												  <td>${PROCESS }</td>
												  <td>${TIMELIMIT }</td>
												  <td><s:date format="yyyy-MM-dd HH:mm:ss" name="STARTTIME"/></td>
												  <td><s:date format="yyyy-MM-dd HH:mm:ss" name="ENDTIME"/></td>
												  <td>${COMPLETION}</td>												  
												  <td > <a href="updateTask.action?taskId=${TASK_ID}" ><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;"/>修改</a></td>
												</tr>
												</s:iterator>
												</s:if>
												 <tr height="32px">
													<td height="20" colspan="9" align="right">
														<!--分页开始-->
														 <s:include value="/framework/include/page.jsp"/>
														<!--分页结束-->
													</td>
												</tr>
											  </tbody>
										</table>
									</div>
									
									
								</div>
							</div>

							<!--tab over-->
						  </td> 
						</tr>
					</table>
				</td>
				</tr>
				</table>
				</s:form>
			</div>

</body>
</html>