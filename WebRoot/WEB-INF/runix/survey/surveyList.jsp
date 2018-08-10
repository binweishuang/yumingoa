<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>网上调查</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>

		function dovote(id,stime,etime){
			var now = new Date();
			var starttime = new Date(Date.parse(stime.replace(/\-/g,"/")));
			var endtime =new Date(Date.parse(etime.replace(/\-/g,"/")));
			//alert(now);
			//alert(starttime);
			if(now<starttime){
				Dialog.alert('投票开始时间还未到，暂时不可投票！');
				return;
			}
			if(now>endtime){
				Dialog.alert('投票终止时间已到，不可投票！');
				return;
			}
	//		var str=window.showModalDialog("../survey/vote.action?surveyId_q="+id,"","dialogWidth=600px;dialogHeight=320px;dialogLeft=400px;dialogTop=280px;");
		//	 var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 600;
				diag.Height = 320;
				diag.Top=40;
				diag.Title = "参加投票";
				diag.URL = "<%=request.getContextPath() %>/survey/vote.action?surveyId_q="+id;
				diag.OKEvent = function(){
				var str = 	diag.innerFrame.contentWindow.doCheck();

					diag.close();
				};	
				diag.show();
		}
		
	
		
		function doObject(id){
			document.getElementById('surveyListForm').action=id+".action";
			document.getElementById('surveyListForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('surveyListForm').action="updateInit.action?surveyId_q="+id;
			document.getElementById('surveyListForm').submit();
		}
		function doview(id){
			document.getElementById('surveyListForm').action="view.action?surveyId_q="+id;
			document.getElementById('surveyListForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
				document.getElementById('surveyListForm').action="delete.action?surveyId_q="+id;
				document.getElementById('surveyListForm').submit();
			});
		}
	</script>
</head>
<body >
    <!--右侧内容-->
    <s:form action="surveyListAction" id="surveyListForm" namespace="/survey" method="post" theme="simple">
    <s:hidden name="currentPage" value="currentPage"></s:hidden>
		<div  style="width:99%; margin:0 auto; background-color:#fff;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td>
		       			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  			<tr>
								<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网上调查</span></td>
				 	 		</tr>
						</table>
						<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
						  <tr>
							<td height="25" width="12%" align="right" style="border:0px">调查标题：</td>
							<td height="25" width="88%" align="left" style="border:0px">
								<input type="text" name="title" value="<s:property value='title'/>" id="title" size="20" />
							</td>
						  </tr>
						  <tr>
							<td align="right"><div style="padding-top:4px"><s:if test="readonly==true"></s:if><s:else><a onclick="doObject('insertInit')" href="#" ><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/add.png" border="0"></a></s:else></div></td>
							<td align="left" ><div style="padding-top:4px"><a onclick="doObject('query')" href="#" ><input type="image" src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" /></a></div></td>
						  </tr>   
						</table>
				        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
				          <thead height="33px">
				            <tr>
				              <th width="10%" >目前状态</th>
				              <th width="25%">投票标题</th>
				              <th width="10%">是否记名</th>
				              <th width="10%">是否公开</th>
				              <th width="15%">开始时间</th>
							  <th width="15%">终止时间</th>
				              <th width="15%" >操作</th>       
			                </tr>
			              </thead>
				          <tbody>
				          <s:iterator value="surveyList">
				            <tr height="32px">
				              <td ><s:if test='status=="1"'>启用</s:if><s:elseif test='status=="0"'>禁用</s:elseif></td>
				              <td ><s:if test='status=="1"'><a href="#" style="color:#49AFCB;" onclick="dovote('<s:property value='surveyId'/>','<s:date name='starttime' format='yyyy-MM-dd'/>','<s:date name='endtime' format='yyyy-MM-dd'/>');" ><s:property value="title"/></a></s:if><s:else><s:property value="title"/></s:else></td>
				              <td ><s:if test='votingway=="1"'>记名</s:if><s:elseif test='votingway=="0"'>不记名</s:elseif></td>
				              <td ><s:if test='publicity=="1"'>公开</s:if><s:elseif test='publicity=="0"'>不公开</s:elseif></td>
				              <td ><s:date name="starttime" format="yyyy-MM-dd"/></td>
							  <td ><s:date name="endtime" format="yyyy-MM-dd"/></td>
							  <td >
								 <s:if test="userId==voter and nowtime<starttime"><a href="#" onclick="doupdate('<s:property value='surveyId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/modify.png" border="0" style="vertical-align:middle;">修改</a></s:if>
								 <a  href="#" onclick="doview('<s:property value='surveyId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/view.png" border="0"  style="vertical-align:middle;">查看</a>
								 <s:if test="userId==voter"><a  href="#" onclick="dodelete('<s:property value='surveyId'/>')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/delete.png" border="0"  style="vertical-align:middle;">删除</a></s:if>
							  </td>	
			                </tr>
			                </s:iterator>
							<tr height="32px">
		                    	<td height="20" colspan="7" align="right">
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
