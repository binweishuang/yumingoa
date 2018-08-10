<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="runix.persistent.model.BaseUser"%>
<%@ page import="kdf.constant.SystemConfig"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  
<head>
    <meta charset="utf-8">
    <meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/icon-style.css" />
	<link href="<%=SystemConfig.FRAMEWORK_PATH%>/css/radmin.css" rel="stylesheet" id="main-stylesheet">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/attendanceService.js'></script>
	<script type="text/javascript">  
		function menu_onclick(id){
			 window.parent.document.getElementById("mainFrame").src="<%=SystemConfig.FRAMEWORK_PATH%>/"+id;
			 window.parent.document.getElementById("leftFrame").src="<%=SystemConfig.FRAMEWORK_PATH%>/left.jsp";
		}
		
		function tick() {
			var hours, minutes, seconds, xfile;
			var intHours, intMinutes, intSeconds;
			var today, theday;
			var Clock = document.getElementById('Clock');
			today = new Date();
			function initArray(){
			this.length=initArray.arguments.length
			for(var i=0;i<this.length;i++)
			this[i+1]=initArray.arguments[i] }
			var d=new initArray(
			"星期日",
			"星期一",
			"星期二",
			"星期三",
			"星期四",
			"星期五",
			"星期六");
			theday = today.getFullYear()+"年" + [today.getMonth()+1]+"月" +today.getDate()+"日" + d[today.getDay()+1];
			intHours = today.getHours();
			intMinutes = today.getMinutes();
			intSeconds = today.getSeconds();
			if (intHours == 0) {
			hours = "12:";
			xfile = "午夜";
			} else if (intHours < 12) {
			hours = intHours+":";
			xfile = "上午";
			} else if (intHours == 12) {
			hours = "12:";
			xfile = "正午";
			} else {
			intHours = intHours - 12
			hours = intHours + ":";
			xfile = "下午";
			}
			if (intMinutes < 10) {
			minutes = "0"+intMinutes+":";
			} else {
			minutes = intMinutes+":";
			}
			if (intSeconds < 10) {
			seconds = "0"+intSeconds+" ";
			} else {
			seconds = intSeconds+" ";
			}
			timeString = theday+"\<br\>"+xfile+hours+minutes+seconds;
			Clock.innerHTML = timeString;
			window.setTimeout("tick();", 100);
			}
			window.onload = tick;
			
			<%BaseUser user = (BaseUser)session.getAttribute("user");%>
			
			function doAttend(){
				var flag =true;
				DWREngine.setAsync(false);
		         attendanceService.checkSignin(<%= user.getUserId()%>,function(data){
		        	 if(data>0){
		        		 flag = false;
		        	 }else{
						flag=true;
		        	 }
		         });
		         if(!flag){
		        	 alert('今天您已签到了！') ;
		        	return;
		         }
		        window.parent.document.getElementById("mainFrame").src="<%=request.getContextPath() %>/mainFrame/main.action?signin=yes";
		     //    parent.mainFrame.showMsg();
			//    var date = new Date();
			//	window.showModalDialog("../attendance/doAttend.action?random="+date.getTime(),"","dialogWidth=600px;dialogHeight=300px;dialogLeft=300px;dialogTop=180px;");
			
		         
		       
			}
			
			function doLeave(){
				var flag =true;
				DWREngine.setAsync(false);
		         attendanceService.checkSignin(<%= user.getUserId()%>,function(data){
		        	 if(data>0){
		        		 flag = false;
		        	 }else{
						flag=true;
		        	 }
		         });
		         if(flag){
		        	 alert('今天您还没签到呢！请先签到吧！');
		        	 return;
		         }else if(window.confirm('现在签退吗？')){
		        	 window.parent.document.getElementById("mainFrame").src="<%=request.getContextPath() %>/mainFrame/main.action?signin=no";
		   //     	 var date = new Date();
			//		window.showModalDialog("../attendance/doLeave.action?random="+date.getTime(),"","dialogWidth=600px;dialogHeight=300px;dialogLeft=300px;dialogTop=180px;");
		         }
				
			}
	</script>
	<style>
		.sign_in{ border:1px solid #333333;  text-align:center; font-size:20px;   line-height:50px; margin:5px 3px;padding:1px; background:#009FCC;cursor:pointer}	 
		.sign_out{ border:1px solid #333333;  text-align:center; font-size:20px;   line-height:50px; margin:5px 3px;padding:1px; background:#FF7744;cursor:pointer}	 
	</style>
</head>
<body>

  <!-- Page Header -->
  <div  style="width:100%;height:100%;padding-top:5px;">
  <table width="100%" border="0">
  <tr>
  <td width="20%">
               <div class="row-fluid" style="width:100%;height:100%;float:left;padding-top:10px;padding-left:10px">
						<div class="span12">
							<h2 class="welcome">
								欢迎：
								<span class="text-info"><%= user.getName()%></span>
							</h2>
						</div>
					</div>
	</td>
	<td width="50%">
		<div  style="width:100%;height:100%;float:right;">
			<ul class="top-nav-boxes">
				
				<li class="first">
 					<a  href="#" onclick="menu_onclick('office.jsp');">
						<i class=""><img src="img/icon-top1.png"></i>
						<span>个人办公</span>
					</a>
				</li>
 				<li>
 					<a href="#" onclick="menu_onclick('public_information.jsp');">
						<i class=""><img src="img/icon-top2.png"></i>
						<span>公共信息</span>
					</a>
				</li>
 				<li>
					<a href="#" onclick="menu_onclick('human_resources.jsp');">
						<i class=""><img src="img/icon-top3.png"></i>
						<span>人力资源</span>
					</a>
				</li>
                <li>
					<a href="#" onclick="menu_onclick('system_management.jsp');">
						<i class=""><img src="img/icon-top4.png"></i>
						<span>系统管理</span>
					</a>
				</li>				
			</ul>

			
				
			
		</div>
		</td>
		<td style="width:15%; text-align:left">
		    <div   style="width:100%;height:100%;background-color:transparent;margin-right:5px;">
					<span ><a href="#" onclick="doAttend();"><img src="img/qd.png"></img></a></span>
					<span ><a href="#" onclick="doLeave();"><img src="img/tc.png"></img></a></span>
				</div>
		</td >
		<td width="15%">
		   <div style="width:100%;height:100%">
			  <div align="center" style="width:100%;height:50%;"><p style="font-weight:bold;font-size: 12px; color:#000000 ;">
			  今天是
			  </p></div>
			  <div id="Clock" align="center" style="font-size: 12px; color:#49AFC5;width:100%;height:50%;" ></div>
		   </div>
		   </td>
		</tr>
		</table>
	</div>
</body>
</html>