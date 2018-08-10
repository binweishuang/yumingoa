<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<link href="<%=request.getContextPath()%>/framework/css/main-css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	
	<script type="text/javascript" >
	function showMsg(){
		 var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 400;
			diag.Height = 200;
			diag.Top=40;
			diag.Title = "签到信息";
			diag.URL = "<%=request.getContextPath() %>/attendance/doAttend.action?random="+date.getTime();
			diag.OKEvent = function(){
				
				diag.close();
			};	
			diag.show();
	}
	
	function showMsg2(){
		 var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 400;
			diag.Height = 200;
			diag.Top=40;
			diag.Title = "签退信息";
			diag.URL = "<%=request.getContextPath() %>/attendance/doLeave.action?random="+date.getTime();
			diag.OKEvent = function(){
				
				diag.close();
			};	
			diag.show();
	}
	</script>
	
</head>
<body <s:if test="signin=='yes'">onload="showMsg();"</s:if><s:elseif test="signin=='no'">onload="showMsg2();"</s:elseif>>                                     
     <div  style="width:1020px; height:100%;margin:0 auto;">

 <!--1-->
    <div style="width:330px; float:left;margin:20px 15px 0px 0px;">
       <div style="height:40px; background:url(<%=request.getContextPath()%>/framework/img/home-tit.png) no-repeat;height:40px; line-height:39px; font-weight:bold; color:#49AFCD;"> &nbsp;我的日程<span style="float:right; margin-right:10px; padding-top:15px;"><a href="<%=request.getContextPath()%>/schedule/query.action"><img src="<%=request.getContextPath()%>/framework/img/more.gif" style="border:0px;"/></a></span></div>
        <s:action name="queryMain" namespace="/schedule" ignoreContextParams="true">
        	<s:param name="pages" value="4"></s:param>
       	</s:action>
        <div class="list">
          <ul style="height: 112px;">
          	<s:iterator value="#request.schedules" id="schedule" status="st">
          		<li><span style="padding-top:4px;"><s:date format="yyyy-MM-dd" name="#schedule.rangetime" /></span><a href='<%=request.getContextPath()%>/schedule/view.action?scheduleId=<s:property value="#schedule.scheduleId" />'><s:property value="#st.index+1" />.&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#schedule.title.length()>10"><s:property value="#schedule.title.substring(0,10).concat('...')"/></s:if><s:else><s:property value="#schedule.title" /></s:else></a></li>
          	</s:iterator>
          </ul>
       </div>

       <div style="height:40px;margin-top:16px; background:url(<%=request.getContextPath()%>/framework/img/home-tit.png) no-repeat;height:40px; line-height:39px;font-weight:bold; color:#49AFCD;">&nbsp;规章制度<span style="float:right; margin-right:10px; padding-top:15px;"><a href="<%=request.getContextPath()%>/messageCenter/queryRules.action"><img src="<%=request.getContextPath()%>/framework/img/more.gif" style="border:0px;"/></a></span></div>
        <s:action name="queryMainRules" namespace="/messageCenter" ignoreContextParams="true">
        	<s:param name="pages" value="4"></s:param>
       	</s:action>
        <div class="list">
          <ul style="height: 112px;">
          	<s:iterator value="#request.rules" id="rule" status="st">
          		<li><span style="padding-top:4px;"><s:date format="yyyy-MM-dd" name="#rule.PUBLISHTIME" /></span><a href='<%=request.getContextPath()%>/messageCenter/rulesDetail.action?messageId=<s:property value="#rule.MESSAGE_ID" />&type=<s:property value="#rule.TYPE" />&dept=<s:property value="#rule.DEPT" />'><s:property value="#st.index+1" />.&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#rule.TITLE.length()>10"><s:property value="#rule.TITLE.substring(0,10).concat('...')"/></s:if><s:else><s:property value="#rule.TITLE" /></s:else></a></li>
          	</s:iterator>
          </ul>
       </div>
    </div>
  <!--1end-->
   <!--2-->
    <div style="width:330px;float:left;margin:20px 15px 0px 0px;">
       <div style="height:40px; background:url(<%=request.getContextPath()%>/framework/img/home-tit.png) no-repeat;height:40px; line-height:39px;font-weight:bold; color:#49AFCD;">&nbsp;新闻列表<span style="float:right; margin-right:10px; padding-top:15px;"><a href="<%=request.getContextPath()%>/messageCenter/queryNews.action"><img src="<%=request.getContextPath()%>/framework/img/more.gif" style="border:0px;"/></a></span></div>
         <s:action name="queryMainNews" namespace="/messageCenter" ignoreContextParams="true">
        	<s:param name="pages" value="6"></s:param>
       	 </s:action>
        <div class="list">
          <div class="news1" >
            <img src="<%=request.getContextPath()%>/framework/img/news-photo.jpg" />
            <div class="news1-dec"><s:if test="#request.news[0].content.length()>50"><s:property value="#request.news[0].content.substring(0,50).concat('...')"/></s:if><s:else><s:property value="#request.news[0].content"/></s:else>
            <p><a href="#">更多>></a></p></div>
          </div>
          
          <ul style="height: 168px;">
			<s:iterator value="#request.news" id="news" status="st">
          		<li><span style="padding-top:4px;"><s:date format="yyyy-MM-dd" name="#news.publishtime" /></span><a href='<%=request.getContextPath()%>/messageCenter/newsDetail.action?messageId=<s:property value="#news.messageId" />&type=<s:property value="#news.type" />&dept=<s:property value="#news.dept" />'><s:property value="#st.index+1" />.&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#news.title.length()>10"><s:property value="#news.title.substring(0,10).concat('...')"/></s:if><s:else><s:property value="#news.title" /></s:else></a></li>
          	</s:iterator>
          </ul>
       </div>
    </div>
  <!--2end--> 
   <!--3-->
    <div style="width:330px;margin-top:20px; float:left;">
    
       <div style="height:40px; background:url(<%=request.getContextPath()%>/framework/img/home-tit.png) no-repeat;height:40px; line-height:39px;font-weight:bold; color:#49AFCD;">&nbsp;通知公告<span style="float:right; margin-right:10px; padding-top:15px;"><a href="<%=request.getContextPath()%>/messageCenter/queryNotice.action"><img src="<%=request.getContextPath()%>/framework/img/more.gif" style="border:0px;"/></a></span></div>
        <s:action name="queryMainNotice" namespace="/messageCenter" ignoreContextParams="true">
        	<s:param name="pages" value="4"></s:param>
       	 </s:action>
        <div class="list">
          <ul style="height: 112px;">
           <s:iterator value="#request.notices" id="notice" status="st">
          		<li><span style="padding-top:4px;"><s:date format="yyyy-MM-dd" name="#notice.PUBLISHTIME" /></span><a href='<%=request.getContextPath()%>/messageCenter/noticeDetail.action?messageId=<s:property value="#notice.MESSAGE_ID" />&type=<s:property value="#notice.TYPE" />&dept=<s:property value="#notice.DEPT" />'><s:property value="#st.index+1" />.&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="#notice.TITLE.length()>10"><s:property value="#notice.TITLE.substring(0,10).concat('...')"/></s:if><s:else><s:property value="#notice.TITLE" /></s:else></a></li>
          	</s:iterator>
        </ul>
       </div>
       
         <div style="height:40px; margin-top:16px;background:url(<%=request.getContextPath()%>/framework/img/home-tit.png) no-repeat;height:40px; line-height:39px;font-weight:bold; color:#49AFCD;">&nbsp;生日祝福&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#FF4500">祝本月生日人员生日快乐！</span></div>
        <div class="list">
          <ul style="height: 112px;">
			<s:iterator value="birthPeoples" >
          		<li><img src="<%=request.getContextPath()%>/images/birthday2.gif"  border="0" style="vertical-align:middle;" height="25px" width="27px"></img>&nbsp;<s:property value="NAME" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="BIRTHDATE" format="yyyy-MM-dd"/></li>
          	</s:iterator>
        </ul>
       </div>
    </div>
  <!--3end--> 
 </div>       
</body>
</html>