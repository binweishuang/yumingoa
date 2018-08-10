<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<meta charset="utf-8"></meta>
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=SystemConfig.FRAMEWORK_PATH%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	
	<script type="text/javascript">
		var editer;
		//配置编辑器
		KindEditor.ready(function(K){
			editer=K.create("#content");
		});
		//表单提交验证
		function doCheck(){
			editer.sync();
			var title = $("#title").val();
			var start = $("#start").val();
			var end = $("#endDate").val();
			var content = $("#content").val();
			if(title.trim().length==0){
				Dialog.alert("请输入日程标题");
			}else if(start.length==0||end.length==0){
					Dialog.alert("请选择日程的时间");
			}else if(content.trim().length==0){
						Dialog.alert("请填写日程内容");
			}else{
					document.form.submit();
				}
			}
	</script>
</head>
<body >
    <div  style="width:99%; margin:0 auto; background-color:#fff;">
      <form name="form" id="form" action="insert.action" method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 个人办公 &gt;&gt; <span class="STYLE1">日程安排</span>&gt;&gt; 
						<span class="STYLE1">添加日程</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">添加新日程</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">日程标题： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<input name="schedule.title" id="title" type="text" size="20" />
            	<span id="titleErr"></span>
            </td>
          </tr>
		  <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">安排人： </td>
            <td height="20" bgcolor="#FFFFFF">${realName }<input type="hidden" name="schedule.executer" value="${userid }"/></td>
          </tr> 
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">时间： </td>
            <td height="20" bgcolor="#FFFFFF">
				<input name="schedule.starttime" id="start" type="text" size="20"  class="Wdate"
				  onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')||\'2020-01-01\'}'})" />
				至
				<input name="schedule.endtime" id="endDate" type="text" size="20"  class="Wdate"
				  onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',vel:'smonth',realDateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})" />
				  <span id="timeErr"></span>
			</td>
          </tr>        
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">日程内容：</td>
            <td height="20" bgcolor="#FFFFFF">
				<textarea name="schedule.content" id="content" cols="60" rows="5" style="width:98%;height:200px;"></textarea>
				<span id="contentErr"></span>
			</td>
          </tr>     
          <tr>
            <td height="20" colspan="4" bgcolor="#FFFFFF">
	            <div align="center">
	                <input type="button" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
	                <input type="reset" class="buttons" name="cancle" value="重置" />
					<input type="button" class="buttons" name="back" value="返回" onclick="javascript:window.history.back(-1)"/>
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