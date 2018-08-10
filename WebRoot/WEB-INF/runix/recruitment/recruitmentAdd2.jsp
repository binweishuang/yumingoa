<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix">
	<meta name="author" content="Runix Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css">
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script>
	function selectPerson(pid,indx){
		 var date = new Date();
		   var diag = new Dialog();
			diag.Width  = 700;
			diag.Height = 300;
			diag.Top=40;
			diag.Title = "选择审核人员";
			diag.URL = "<%=request.getContextPath() %>/selectPeople/query.action?random="+date.getTime()+"&pId="+pid+"&fg=simple";
			diag.OKEvent = function(){
				var str=diag.innerFrame.contentWindow.doSelect();
				if(str!='' && str!=null){
					   var pple = str.split("%");
				  		$('#personName'+indx).val(pple[0]);
				  		$('#personId'+indx).val(pple[1]);

				  }
				diag.close();
			};	
			diag.show();
	}
	function doCheck(){
		var fg = true;
		if($(".abc").length==0){
			Dialog.alert('请通知管理员添加流程审核人员！');
			return;
		}
		$(".abc").each(function(i){
			if($(this).val()==''||$(this).val()==null){
				fg=false;
			}
			
		});
		if(!fg){
			Dialog.alert('请选择审核人员！');
			return;
		}
		document.getElementById('recruitmentAddForm').action='insertPerson.action';
		document.getElementById('recruitmentAddForm').submit();
	}
	</script>
</head>
<body >
	<s:form action="recruitmentAddAction" id="recruitmentAddForm" theme="simple"  method="post">
	<s:hidden name="recruitmentId"></s:hidden>
	<s:hidden name="title"></s:hidden>
          <div  style="width:99%;margin:0 auto; background-color:#fff;">
                     
           <table width="100%" border="0" cellspacing="0" cellpadding="5">
       <tr>
        <td>		  
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td height="26" colspan="4" align="center" bgcolor="#FEF3D5"><span class="STYLE1">指定办理人</span></td>
          </tr>
          <tr>
            <td width="12%" height="20" align="right" bgcolor="#f8f8f8">流程名称： </td>
            <td width="88%" height="20" bgcolor="#FFFFFF">招聘管理
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">流程线路： </td>
            <td height="20" bgcolor="#FFFFFF">开始<s:iterator value="nodes" id="node">-><s:property value='#node.NODENAME'/></s:iterator>
			</td>
          </tr>
          <tr>
            <td height="20" align="right" bgcolor="#f8f8f8">选择办理者： </td>
            <td height="20" bgcolor="#FFFFFF">
			 <table width="80%" border="0" align="center" cellpadding="3" cellspacing="1" >
			 <s:iterator value="nodes" id="node"  status="st">
				<tr>
					<td height="20" align="right" bgcolor="#FFFFFF" border="0"><s:property value='#node.NODENAME'/>： </td>
					<td height="20" bgcolor="#FFFFFF" border="0"><input type="hidden" name="personIds" id="personId<s:property value="#st.index+1"/>"/>
						<input class="abc" name="personNames" id="personName<s:property value="#st.index+1"/>" type="text" value="" onclick="selectPerson('<s:property value='#node.LEADERSID'/>','<s:property value="#st.index+1"/>')" size="20" readonly/>
					</td>
				</tr>
				</s:iterator>
				
				</table>
			</td>
          </tr>         
        
		       
          <tr>
            <td height="20" colspan="2" bgcolor="#FFFFFF"><div align="center">
                <input type="button" name="save" value="提交" class="buttons" onclick="doCheck();"/>
				<input type="button" name="back" value="返回" class="buttons" onclick="javascript:window.history.back(-1)"/>
            </div></td>
          </tr>
        </table>
		</td>
  </tr>
</table>
</div>
</s:form>
</body>
</html>