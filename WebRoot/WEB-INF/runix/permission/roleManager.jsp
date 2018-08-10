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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css">
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<style>
		.tab td{border-bottom:solid #CCCCCC 1px;}
	</style>
	<script>
		//父节点
		function selectParent(obj){
			var len = obj.parentElement.parentElement.getElementsByTagName("input").length;
			if(obj.checked){
				for(var i=0;i<len;i++){
					obj.parentElement.parentElement.getElementsByTagName("input")[i].checked = true;
				}
			}else{
				for(var i=0;i<len;i++){
					obj.parentElement.parentElement.getElementsByTagName("input")[i].checked = false;
				}
			}
		}
		
		//子节点
		function selectChild(obj){
			if(obj.checked){
				var len = obj.parentElement.parentElement.parentElement.parentElement.getElementsByTagName("input").length;
				for(var i=0;i<len;i++){
					obj.parentElement.parentElement.parentElement.parentElement.getElementsByTagName("input")[0].checked = true;
				}
			}else{
				var num = obj.parentElement.parentElement.getElementsByTagName("input").length;
				var m = 0;
				for(var i=0;i<num;i++){
					if(obj.parentElement.parentElement.getElementsByTagName("input")[i].checked==true){
						m++;
					}
				}
				if(m>0){
					obj.parentElement.parentElement.parentElement.parentElement.getElementsByTagName("input")[0].checked = true;
				}else{
					obj.parentElement.parentElement.parentElement.parentElement.getElementsByTagName("input")[0].checked = false;
				}
			}
		}
		
		//选择职位
		function selectPosition(){
			var positionId = $("#position").val();
			//是否已授权,授权则列出权限
			location.href = "query.action?positionId="+positionId;
		}
		
		//表单提交验证
		function doCheck(){
			var posiotion = $("#position").val();
			var flag = false;
			var m = 0;
		//	var n = 0;
			
			if(posiotion==-1){
				Dialog.alert("请选择职位");
			}else{
				flag = true;
			}
			
			if(flag){
				var checks = document.getElementsByTagName("input");    
				for(i = 0;i<checks.length;i++){        
					if(checks[i].type == "checkbox"){
						if(checks[i].checked==true){
							m++;
						}
					}
					
				//	if(checks[i].type == "radio"){
				//		if(checks[i].checked==true){
				//			n++;
				//		}
				//	}             
				}
				
			//	if(n==0){
			//		Dialog.alert("请选择权限级别");
			//	}else 
					if(m==0){
					Dialog.alert("请授予职位权限");
				}
				
			//	if(m>0&&n>0){
					if(m>0){
					document.form.submit();
				}
			}
		}
		
	</script>
</head>
<body >
          <div style="width:99%; margin:0 auto; background-color:#fff;">
           <form id="form" name="form" action="insert.action" method="post">
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理 &gt;&gt; <span class="STYLE1">权限管理</span></td>
					  </tr>
		  </table>		  
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#F8F8F8" bgcolor="#CCCCCC">
					  <tr>
						<td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">分配权限</span></td>
					  </tr>
					  <tr>
						<td width="15%" height="25" align="right" bgcolor="#f8f8f8">职    位： </td>
						<td width="85%" height="25" bgcolor="#FFFFFF" style="border:0px;">
							<s:select id="position" name="permission.parentid" value="positionId" headerKey="-1" 
							headerValue="--请选择--" list="positionList" listKey="positionId" 
							listValue="postname" theme="simple" onchange="selectPosition();"/>
						</td>
					 </tr>						
	<!-- 				  <tr>						
					   <td  height="20" align="right" bgcolor="#f8f8f8">权限级别： </td>
						<td  height="20" bgcolor="#FFFFFF" >
							<input name="permission.sortnum" type="radio" value="1" <s:if test="permission.sortnum==1">checked="checked"</s:if>/>管理级别
							<input name="permission.sortnum" type="radio" value="0" <s:if test="permission.sortnum==0">checked="checked"</s:if>/>普通级别	
						</td>
					  </tr> -->
					  <tr>						
					   <td  height="20" align="right" bgcolor="#f8f8f8">权限列表： </td>
						<td bgcolor="#ffffff">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab">
                            <tr>
                              <td width="165" style="border-right:1px solid #cccccc;" align="center">
                 					<input type="checkbox" name="role" id="role" value="a1" <s:if test="permission.title.indexOf('a1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 个人办公
							  </td>
							  <td valign="middle">
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="a2" <s:if test="permission.title.indexOf('a2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>待办事宜(普通)&nbsp;&nbsp;&nbsp;&nbsp;
							           <input type="checkbox" name="role" value="am2" <s:if test="permission.title.indexOf('am2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>待办事宜(管理)							
									</div>
									<div>
								      <input type="checkbox" name="role" value="a3" <s:if test="permission.title.indexOf('a3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>我的便签(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="am3" <s:if test="permission.title.indexOf('am3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>我的便签(管理)			  			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a4" <s:if test="permission.title.indexOf('a4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>内部邮件(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="am4" <s:if test="permission.title.indexOf('am4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>内部邮件(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a5" <s:if test="permission.title.indexOf('a5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>日程安排(普通)&nbsp;&nbsp;&nbsp;&nbsp;	
								      <input type="checkbox" name="role" value="am5" <s:if test="permission.title.indexOf('am5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>日程安排(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a6" <s:if test="permission.title.indexOf('a6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>任务管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;	
								      <input type="checkbox" name="role" value="am6" <s:if test="permission.title.indexOf('am6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>任务管理(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a7" <s:if test="permission.title.indexOf('a7')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>通讯录查询	  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a8" <s:if test="permission.title.indexOf('a8')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>值班信息(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="am8" <s:if test="permission.title.indexOf('am8')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>值班信息(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="a9" <s:if test="permission.title.indexOf('a9')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>信息中心(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="am9" <s:if test="permission.title.indexOf('am9')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>信息中心(管理)			  
									</div>
							  </div>							  
							  </td>
                            </tr>
                            <tr>
                              <td style="border-right:1px solid #cccccc;" align="center">
                              	 <input type="checkbox" id="check_2" name="role" value="b1" <s:if test="permission.title.indexOf('b1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 公共信息
                              </td>
							  <td>
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="b2" <s:if test="permission.title.indexOf('b2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>新闻管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
							          <input type="checkbox" name="role" value="bm2" <s:if test="permission.title.indexOf('bm2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>新闻管理(管理)									
									</div>
									<div>
								      <input type="checkbox" name="role" value="b3" <s:if test="permission.title.indexOf('b3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>通知管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="bm3" <s:if test="permission.title.indexOf('bm3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>通知管理(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="b4" <s:if test="permission.title.indexOf('b4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>规章制度(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="bm4" <s:if test="permission.title.indexOf('bm4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>规章制度(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="b5" <s:if test="permission.title.indexOf('b5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网络硬盘(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="bm5" <s:if test="permission.title.indexOf('bm5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网络硬盘(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="b6" <s:if test="permission.title.indexOf('b6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>通讯录管理				  
									</div>
							    </div>							  
							  </td>
                            </tr>
                            <tr>
                              <td style="border-right:1px solid #cccccc;" align="center">
                              	<input type="checkbox" id="check_3" name="role" value="c1" <s:if test="permission.title.indexOf('c1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 人力资源
                              </td>
							  <td>
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="c2" <s:if test="permission.title.indexOf('c2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>招聘管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
							          <input type="checkbox" name="role" value="cm2" <s:if test="permission.title.indexOf('cm2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>招聘管理(管理)	
									</div>
									<div>
								      <input type="checkbox" name="role" value="c3" <s:if test="permission.title.indexOf('c3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>合同管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="cm3" <s:if test="permission.title.indexOf('cm3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>合同管理(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="c4" <s:if test="permission.title.indexOf('c4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>人事管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="cm4" <s:if test="permission.title.indexOf('cm4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>人事管理(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="c5" <s:if test="permission.title.indexOf('c5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>考情管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="cm5" <s:if test="permission.title.indexOf('cm5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>考情管理(管理)				  
									</div>
									<div>
								      <input type="checkbox" name="role" value="c6" <s:if test="permission.title.indexOf('c6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>报销管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="cm6" <s:if test="permission.title.indexOf('cm6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>报销管理(管理)				  
									</div>
							    </div>
							  </td>
                            </tr>
                           
                            <tr>
                              <td style="border-right:1px solid #cccccc;" align="center">
                              	<input type="checkbox" id="check_5" name="role" value="e1" <s:if test="permission.title.indexOf('e1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 公文管理
                              </td>
							  <td>
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="e2" <s:if test="permission.title.indexOf('e2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>收文管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
							          <input type="checkbox" name="role" value="em2" <s:if test="permission.title.indexOf('em2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>收文管理(管理)								
									</div>
									<div>
								      <input type="checkbox" name="role" value="e3" <s:if test="permission.title.indexOf('e3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>发文管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="em3" <s:if test="permission.title.indexOf('em3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>发文管理(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="e4" <s:if test="permission.title.indexOf('e4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>公文档案(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="em4" <s:if test="permission.title.indexOf('em4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>公文档案(管理)			  
									</div>
							    </div>
							  </td>
                            </tr>
							<tr>
                              <td style="border-right:1px solid #cccccc;" align="center">
                              	<input type="checkbox" id="check_6" name="role" value="f1" <s:if test="permission.title.indexOf('f1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 在线交流
                              </td>
							  <td>
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="f2" <s:if test="permission.title.indexOf('f2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网上调查(普通)&nbsp;&nbsp;&nbsp;&nbsp;	
							          <input type="checkbox" name="role" value="fm2" <s:if test="permission.title.indexOf('fm2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网上调查(管理)							
									</div>
									<div>
								      <input type="checkbox" name="role" value="f3" <s:if test="permission.title.indexOf('f3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>个人建议(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="fm3" <s:if test="permission.title.indexOf('fm3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>个人建议(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="f4" <s:if test="permission.title.indexOf('f4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网络会议(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="fm4" <s:if test="permission.title.indexOf('fm4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>网络会议(管理)			  
									</div>
							    </div>
							  </td>
                            </tr>
                            <tr>
							<td style="border-right:1px solid #cccccc;" align="center">
								<input type="checkbox" id="check_7" name="role" value="g1" <s:if test="permission.title.indexOf('g1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 行政办公
							</td>
							<td>
								<div> 
									<div>
							          <input type="checkbox" name="role" value="g2" <s:if test="permission.title.indexOf('g2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>车辆管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
							           <input type="checkbox" name="role" value="gm2" <s:if test="permission.title.indexOf('gm2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>车辆管理(管理)								
									</div>
									<div>
								      <input type="checkbox" name="role" value="g3" <s:if test="permission.title.indexOf('g3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>请示管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								       <input type="checkbox" name="role" value="gm3" <s:if test="permission.title.indexOf('gm3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>请示管理(管理)			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="g4" <s:if test="permission.title.indexOf('g4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>设备管理(普通)&nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="checkbox" name="role" value="gm4" <s:if test="permission.title.indexOf('gm4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>设备管理(管理)			  
									</div>
							    </div>
							</td>
                            </tr>
                             <tr>
							<td style="border-right:1px solid #cccccc;" align="center">
								<input type="checkbox" id="check_4" name="role" value="d1" <s:if test="permission.title.indexOf('d1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 系统管理
							</td>
							<td>
								<div> 
									<div>
							          <input type="checkbox" name="role" value="d2" <s:if test="permission.title.indexOf('d2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>权限管理								
									</div>
									<div>
								      <input type="checkbox" name="role" value="d3" <s:if test="permission.title.indexOf('d3')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>系统设置			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="d4" <s:if test="permission.title.indexOf('d4')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>用户管理			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="d5" <s:if test="permission.title.indexOf('d5')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>职位管理			  
									</div>
									<div>
								      <input type="checkbox" name="role" value="d6" <s:if test="permission.title.indexOf('d6')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>部门管理			  
									</div>
							    </div>
							</td>
                            </tr>
                            <tr>
                              <td style="border-right:1px solid #cccccc;" align="center">
                              	<input type="checkbox" id="check_8" name="role" value="h1" <s:if test="permission.title.indexOf('h1')>=0">checked="checked"</s:if> onclick="selectParent(this);"/> 工作流程
                              </td>
							  <td>
							  	<div> 
									<div>
							          <input type="checkbox" name="role" value="h2" <s:if test="permission.title.indexOf('h2')>=0">checked="checked"</s:if> onclick="selectChild(this)"/>流程管理							
									</div>
							    </div>
							  </td>
                            </tr>
                          </table>
						</td>
					  </tr>
					  <tr>
						<td height="20" colspan="2" bgcolor="#FFFFFF">
						  <div align="center">
							<input type="button" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
							<input type="button" class="buttons" name="back" value="返回" onClick="javascript:window.history.back(-1)"/>
						  </div>
						</td>
					  </tr>
		  </table>
		 </td>
		  </tr>
		</table>
		</form>          
		</div>
</body>
</html>