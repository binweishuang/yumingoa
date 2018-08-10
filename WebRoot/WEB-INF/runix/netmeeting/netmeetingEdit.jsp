<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加新会议</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
	function doCheck(){
		var netmeetingId = document.getElementById('netmeetingId').value;
		var title= document.getElementById('title').value;
		var starttime = document.getElementById('starttime').value;
		var endtime = document.getElementById('endtime').value;
		var starttime = document.getElementById('title').value;
		var attendeename = document.getElementById('attendeename').value;
		if(title==''||title==null){
			Dialog.alert('会议主题不能为空！');
			return;
		}else if(title.length>50){
			Dialog.alert('会议主题不能超过50个字符！');
			return;
		}
		if(starttime==''||starttime==null){
			Dialog.alert('开始时间不能为空！');
			return;
		}
		if(endtime==''||endtime==null){
			Dialog.alert('结束时间不能为空！');
			return;
		}
		if(attendeename==''||attendeename==null){
			Dialog.alert('参加人员不能为空！');
			return;
		}
		document.getElementById('netmeetingform').action="update.action?netmeetingId_q="+netmeetingId;
		document.getElementById('netmeetingform').submit();
	}
	function selectPerson(){
//		   var date = new Date();
		   var attendee= document.getElementById('attendee').value;
		   var attendeename= document.getElementById('attendeename').value;
/*		   var str=window.showModalDialog("../selectPeople/query.action?random="+date.getTime()+"&people="+attendeename+"&peopleId="+attendee,"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
		   var pple = str.split("%");
		   $('#attendeename').val(pple[0]);
		   $('#attendee').val(pple[1]);
		   */
		   
		   var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择参加人员";
			diag.URL = "../selectPeople/query.action?random="+date.getTime()+"&people="+attendeename+"&peopleId="+attendee;
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				if(str != "" && str != null){
					  var pple = str.split("%");
					$('#attendeename').val(pple[0]);
					   $('#attendee').val(pple[1]);
				}
				diag.close();
			};	
			diag.show();
	}
		
	</script>
</head>
<body >
	<s:form action="netmeetingAction" method="post" id="netmeetingform">
	<s:hidden name="attendee" id="attendee"></s:hidden>
	<s:hidden name="netmeetingId" id="netmeetingId"></s:hidden>
	<div  style="width:99%; margin:0 auto; background-color:#fff;">    
    	<table width="100%" border="0" cellspacing="0" cellpadding="5">
        	<tr>
        		<td>
	        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 在线交流 &gt;&gt; <span class="STYLE1">网络会议</span>&gt;&gt; 
							<span class="STYLE1">添加会议</span>
						</td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加新会议</span></td>
			          </tr>
			          <tr>
			            <td width="15%" height="20" align="right" bgcolor="#f8f8f8">会议主题： </td>
			            <td width="85%" height="20" bgcolor="#FFFFFF"><input name="title" id="title" type="text" size="50" value="<s:property value='title'/>"/></td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">时间： </td>
			            <td height="20" bgcolor="#FFFFFF">
							<input name="starttime" id="starttime" type="text" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="<s:date name='starttime' format='yyyy-MM-dd HH:mm:ss'/>" size="20" class="Wdate"/> 至 <input name="endtime" id="endtime" type="text"  onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:date name='endtime' format='yyyy-MM-dd HH:mm:ss'/>" size="20" class="Wdate"/>
						</td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">参加人员：</td>
			            <td height="20"  bgcolor="#FFFFFF">
							<textarea name="attendeename" style="width:60%" id="attendeename" onclick="selectPerson();" readonly><s:property value="attendeename"/></textarea>
						</td>
			          </tr>     
			          <tr>
			            <td height="20" colspan="2" bgcolor="#FFFFFF">
			            	<div align="center">
				                <input type="button" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
				                <input type="reset" class="buttons" name="cancle" value="重置" />
								<input type="button" class="buttons" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
			            	</div>
			            </td>
			          </tr>
			        </table>
				</td>
  			</tr>
		</table>
	</div>
	</s:form>
</body>
</html>
