<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="kdf.constant.SystemConfig"%>
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
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.FRAMEWORK_PATH%>/css/system.css"> 
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		
		
		function doObject(id){
			document.getElementById('selectPeopleForm').action=id+".action";
			document.getElementById('selectPeopleForm').submit();
		}
		
		function doSelect(){
			var a = document.getElementsByName("check");
			var people = document.getElementById('people').value;
			var pId = document.getElementById('peopleId').value;
		//	alert(pId);
			var text = '';  
			var peopleId = '';
			for(var i=0;i<a.length;i++){
			
				if(a[i].checked){
					  var n = $(a[i]).parent().next().text();
					  var m = $(a[i]).next().val();
					  if(text==""||text==null){
					  	text=n;
					  }else{
					  	text+="," +n;
					  }
					 if(peopleId==""||peopleId==null){
						  peopleId=m;
					  }else{
						  peopleId+="," +m;
					 }
					  
				}
			}
		
			if(people==null||people==''){
	//			window.returnValue=text+"%"+peopleId;
				return text+"%"+peopleId;
			}
			else{
			 
			 var p1 = people;
			 var p2 = pId;
			 var strsSon= text.split(","); //定义一数组   存放人员姓名
			 var strsSon2= peopleId.split(","); //定义一数组   存放人员id
 	         var info = "";
	   	     for (var i=0;i<strsSon.length ;i++){    
	            if(people.indexOf(strsSon[i]) == -1){
                // alert("1111");
		              p1= p1 +","+ strsSon[i];
		        }else{
		          // alert("2222");
		            info += strsSon[i] ;
		        }
		      }
	   	     for (var j=0;j<strsSon2.length ;j++){    
	            if(pId.indexOf(strsSon2[j]) == -1){
              // alert("1111");
		              p2= p2 +","+ strsSon2[j];
		        }
		      }
			  if(info != "" &&  info != null){
		         Dialog.alert(info+".....人员已被添加！");
		       } 
		 //    window.returnValue=p1+"%"+p2;
		 return p1+"%"+p2;
			}
	//                window.close();
		}

	</script>
	</head>
<body >
	<s:form action="selectPeopleAction" id="selectPeopleForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>
	<s:hidden name="people" id="people" ></s:hidden>
	<s:hidden name="peopleId" id="peopleId" ></s:hidden>
	 <div  style="width:99%; margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
					<td>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
					  	  <td height="25" width="15%" align="right" style="border:0px">姓     名：</td>
						  <td height="25" width="15%" align="left" style="border:0px"><input name="name_q" value="<s:property value='name_q'/>" type="text" size="17" /></td>
						  <td height="25" width="15%" align="right" style="border:0px">职    位：</td>
						  <td height="25" width="15%" align="left" style="border:0px">
								<select id="positionId_q" name="positionId_q" style="width:120px">
									<option value="">--请选择--</option>
									<s:iterator value="positionList" >
					                   <option value="<s:property value='positionId'/>"><s:property value='postname'/></option>
				                    </s:iterator>
								</select>
						  </td>
						<td height="25" width="15%" align="right" style="border:0px">部    门：</td>
						<td height="25" width="15%" align="left" style="border:0px">
						    <select id="deptId_q" name="deptId_q" style="width:120px">
									<option value="">--请选择--</option>
									<s:iterator value="deptList" >
					                   <option value="<s:property value='deptId'/>"><s:property value='deptname'/></option>
				                   </s:iterator>
							</select></td>		
						<td height="25" width="10%" align="right" style="border:0px"></td>
					  </tr>
					  
					  <tr>
						<td align="right"></td>
						<td align="left" colspan="6"><div style="padding-top:4px"><a href="#" onclick="doObject('query')"><img src="<%=SystemConfig.FRAMEWORK_PATH%>/img/search.png" border="0" ></a></div></td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			             <th width="20%" ></th>	 
			              <th width="20%">姓名</th>
			              <th width="20%">性别</th>
			              <th width="20%">职位</th>
			              <th width="20%">部门</th>
			                           
		                </tr>
		              </thead>
			          <tbody>
			          <s:iterator  value="baseUserList" status="s">
			            <tr height="32px">
			             <td><input  name="check" type="checkbox" id="#s.index+1" /><input type="hidden" name="userId" value="<s:property value='USER_ID'/>"/></td>  
			              <td ><s:property value="NAME"/></td>
			              <td ><s:if test="SEX==1">男</s:if><s:elseif test="SEX==2">女</s:elseif></td>
			              <td ><s:property value="POSTNAME"/></td>
						  <td ><s:property value="DEPTNAME"/></td>	             
		                </tr>
			          </s:iterator> 
						 <tr height="32px">
                            <td height="20" colspan="5" align="right">
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
</body>
</html>