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
		.inp{width:35%;}
	</style>
	<script>
		var flag = false;
		function checkName(){
			var pname = $("#pname").val();
			var positionId = $("#positionId").val();
			if(pname.trim().length==0){
				$("#nameErr").html("请填写职位名称");
				flag = false;		
			}else{
				$.ajax({
					url:"checkEdit.action",
					data:"postname="+pname+"&positionId="+positionId,
					type:"post",
					dataType:"json",
					success:function(data){
						if(data){
							$("#nameErr").css("color","blue");
							$("#nameErr").html("名称可用");
							flag = true;	
						}else{
							$("#nameErr").css("color","red");
							$("#nameErr").html("名称重复");
							flag = false;	
						}
					}
				});
			}
		}
		
		function doCheck(){
				checkName();
				if(flag){
					document.form.submit();	
				}			
		}
	</script>
</head>
<body >
          <div  style="width:99%; margin:0 auto; background-color:#fff;">
       <form name="form" id="form" action="update.action" method="post">
       <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td height="25" background="<%=SystemConfig.FRAMEWORK_PATH%>/img/bg-tit.jpg">&nbsp;&nbsp;当前位置： 系统管理 &gt;&gt; <span class="STYLE1">职位管理</span>&nbsp;&gt;&gt; <span class="STYLE1">修改职位</span></td>
					  </tr>
					</table>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="2" align="center" bgcolor="#FEF3D5"><span class="STYLE1">修改职位</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">职位名称： </td>
            <td height="20" bgcolor="#FFFFFF">
            	<input name="position.postname" id="pname" type="text"  value="${position.postname}" size="20" onBlur="checkName();"/>
            	<input type="hidden" name="position.positionId" id="positionId" value="${position.positionId}"/>
            	<span id="nameErr" style="color:red;"></span>
            </td>
          </tr>
          <tr>
            <td width="12% height="20" align="right" bgcolor="#f8f8f8">是否屏蔽： </td>
            <td height="20" bgcolor="#FFFFFF" align="left">
				是：<input name="position.screen" type="radio"  value="1" size="15" <s:if test="position.screen==1">checked="checked"</s:if>/>
				否：<input name="position.screen" type="radio"  value="0" size="15" <s:if test="position.screen==0">checked="checked"</s:if>/>
			</td>
          </tr>                  
          <tr>
            <td width="12%  height="20" align="right" bgcolor="#f8f8f8">职位描述： </td>
            <td height="20" bgcolor="#FFFFFF"><textarea name="position.description" cols ="50" rows = "5">${position.description }</textarea>
            </td>
          </tr>     
          <tr>
            <td height="20" colspan="2" bgcolor="#FFFFFF">
            	<div align="center">
	                <input type="submit" class="buttons" name="save" value="保存"  onclick="doCheck();"/>
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