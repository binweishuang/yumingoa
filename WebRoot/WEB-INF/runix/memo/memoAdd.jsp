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
	<meta charset="utf-8"></meta>
	<title>添加便签</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"></link>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/flowNodeService.js'></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript">
		var editer;
		//配置编辑器
		  KindEditor.ready(function(K){
		  	editer=K.create("#content");
		  });
		//选择上报对象
		function selectUser(){
/*		var date = new Date();
			//弹出选择上报对象窗口
			var str = window.showModalDialog("<%=request.getContextPath()%>/selectPeople/queryRadio.action?random="+date.getTime(),"","dialogWidth=800px;dialogHeight=450px;dialogLeft=300px;dialogTop=180px;");
			if(str != "" && str != null){
				var arr = str.split("%");
				$("#recievepersonName").val(arr[0]);
				$("#recievepersonId").val(arr[1]);
			}
*/		
			 var date = new Date();
			   var diag = new Dialog();
				diag.Width  = 700;
				diag.Height = 300;
				diag.Top=40;
				diag.Title = "选择上报对象";
				diag.URL = "<%=request.getContextPath() %>/selectPeople/queryRadio.action?random="+date.getTime()+"&nocontains=1";
				diag.OKEvent = function(){
					var str=diag.innerFrame.contentWindow.doSelect();
					if(str != "" && str != null){
						var arr = str.split("%");
						$("#recievepersonName").val(arr[0]);
						$("#recievepersonId").val(arr[1]);
					}
					diag.close();
				};	
				diag.show();
		}
		//验证参数   提交表单
		function doCheck(status){
			editer.sync();
			var title = $("#title").val();
			var recieveperson = $("#recievepersonId").val();
			var starttime = $("#starttime").val();
			var endtime = $("#endtime").val();
			if(title == "" || title == null){
				Dialog.alert("标题不能为空！");
				return;
			}
			if(recieveperson == "" || recieveperson == null){
				Dialog.alert("上报对象不能为空！");
				return;
			} 
			if(starttime == "" || starttime == null){
				Dialog.alert("开始时间不能为空！");
				return;
			}
			if(endtime == "" || endtime == null){
				Dialog.alert("结束时间不能为空！");
				return;
			}
			if(starttime > endtime){
				Dialog.alert("请选择正确的开始时间和结束时间！");
				return;
			}
			$("#status").val(status);
			if(status == 2){
				Dialog.confirm("提交之后此信息不可修改！确定提交吗？",function(){
					$("#form1").submit();
				});
			}else{
				$("#form1").submit();
			}
		}
	</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/memo/insert.action" id="form1" method="post">
    	<div  style="width:99%;margin:0 auto; background-color:#fff;">
    	   <input type="hidden" name="leadersid"/>
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
		       <tr>
		        <td>
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">我的便签</span>&gt;&gt; 
						<span class="STYLE1">添加便签</span></td>
					  </tr>
					</table>		  
			        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
			          <tr>
			            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加新便签</span></td>
			          </tr>
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">上报人： </td>
			            <td height="20" bgcolor="#FFFFFF">${user.name}<input type="hidden" name="reportperson" value="${user.userId}" /></td>
			          </tr> 
			          <tr>
			            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">便签标题： </td>
			            <td height="20" bgcolor="#FFFFFF"><input name="title" type="text" id="title" size="20" /></td>
			          </tr>
					  
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">上报对象： </td>
			            <td height="20" bgcolor="#FFFFFF">
			            	<input onclick="selectUser();" id="recievepersonName" type="text" size="20" />
			            	<input type="hidden" name="recieveperson" id="recievepersonId" />
			            </td>
			          </tr> 
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">有效时间： </td>
			            <td height="20" bgcolor="#FFFFFF">
							<input name="starttime" type="text" id="starttime" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endtime\')||\'2020-01-01\'}'})" class="Wdate" />
							至
							<input name="endtime" type="text" id="endtime" size="20" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'2020-10-01'})" class="Wdate" />
						</td>
			          </tr>        
			          <tr>
			            <td height="20" align="right" bgcolor="#f8f8f8">便签内容：</td>
			            <td height="20" bgcolor="#FFFFFF">
							<textarea name="content" cols="50" rows="5" id = "content" style="width:100%;height:150px;"></textarea>
							<input type="hidden" name="status" id="status" />
						</td>
			          </tr>     
			          <tr>
			            <td height="20" colspan="4" bgcolor="#FFFFFF">
			              <div align="center">
			                <input type="button" class="buttons" name="save" value="暂存"  onclick="doCheck(1);"/>
			                <input type="button" class="buttons" name="sub" value="提交" onclick="doCheck(2);" />
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
</form>
</body>
</html>
