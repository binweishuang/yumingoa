<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>

	<meta charset="utf-8">
	<title>Runix - the Runix Admin Template</title>
	<meta name="description" content="An admin template from Runix"></meta>
	<meta name="author" content="Runix Inc."></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<script type="text/javascript" src="scripts/jquery.min.js"></script>    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/css/system.css"></link>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/jquery.min.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/framework/js/message-center.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDialog.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/zDrag.js"></script>
	<script type="text/javascript" src="<%=SystemConfig.FRAMEWORK_PATH%>/js/My97DatePicker/WdatePicker.js"></script>
	<script>
		$(document).ready(function(){
			$("table tbody tr").mouseover("background-color","");
		});
		

		function doObject(id){
			document.getElementById('carForm').action=id+".action";
			document.getElementById('carForm').submit();
		}
		
		function doupdate(id){
			document.getElementById('carForm').action="updateInit.action?carId_q="+id;
			document.getElementById('carForm').submit();
		}
		function doview(id){
			document.getElementById('carForm').action="view.action?carId_q="+id;
			document.getElementById('carForm').submit();
		}
		function dodelete(id){
			Dialog.confirm("确定删除吗？",function(){
			document.getElementById('carForm').action="delete.action?carId_q="+id;
			document.getElementById('carForm').submit();
			});
		}
		function domeeting(id){
			document.getElementById('carForm').action="meeting.action?carId_q="+id;
			document.getElementById('carForm').submit();
		}
	</script>
	</head>
<body >
            <s:form  id="carForm" theme="simple"  method="post" target="_self">
	<s:hidden name="currentPage" id="currentPage"></s:hidden>   
                                
            <!--右侧内容-->
			  <div  style="margin:0 auto; background-color:#fff;">
					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td>
					<table id="chaxun_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
					  <tr>
						<td height="25" width="15%" align="right" style="border:0px">车型：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="cartype" type="text" value="<s:property value='cartype'/>"  /></td>
						<td height="25" width="15%" align="right" style="border:0px">牌照：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="license" type="text" value="<s:property value='license'/>"  /></td>
						<td height="25" width="15%" align="right" style="border:0px">年检时间：</td>
						<td height="25" width="15%" align="left" style="border:0px"><input name="checktime" type="text"  value="<s:date name='checktime' format='yyyy-MM-dd'/>" onclick="WdatePicker({autoPickDate:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd',vel:'smonth',realDateFmt:'yyyy-MM-dd'})" value="<s:date name='endtime' format='yyyy-MM-dd'/>"  class="Wdate"/></td>
						<td height="25" width="10%" align="left" style="border:0px"></td>
					  </tr>
					  <tr>
						<td width="10%" align="right"><s:if test="readonly==true"></s:if><s:else><a href="#"  onclick="doObject('insertInit')" ><img src="<%=request.getContextPath()%>/framework/img/add.png" border="0"></a></s:else></td>
						<td align="left" colspan="6"><a href="#"  onclick="doObject('query')" ><img src="<%=request.getContextPath()%>/framework/img/search.png" border="0" ></img></a></td>
					  </tr>  
					</table>
			        <table id="xinxiu_table" width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#f8de91" style="border-collapse:collapse;">
			          <thead height="33px">
			            <tr>
			              <th width="7%" >车型</th>
			              <th width="7%">牌照</th>
			              <th width="8%">负责司机</th>
			              <th width="7%">排量</th>
			              <th width="7%">载客量</th>
			              <th width="12%">年检时间</th>
						  <th width="12%">投保时间</th>
						  <th width="12%">购买时间</th>
						  <th width="8%">是否可用</th>
						  <th>操作</th>
		                </tr>
		              </thead>
			          <tbody>
			        
			          	<s:iterator value="cars">
			          		 <tr>
					             <td>${CARTYPE }</td>
					              <td>${LICENSE }</td>
					              <td>${DRIVER }</td>
					              <td>${CAPACITY }</td>
					              <td>${PASSENGERNUM }</td>
					              <td>${CHECKTIME }</td>
								  <td>${INSURETIME }</td>
								  <td>${BUYTIME }</td>
								  <td>
								  	<s:if test="USABLE==1">是</s:if>
								  	<s:else>否</s:else>
								  </td>
								  <td>
								  <s:if test="readonly==true">
								  <a href="#" onclick="doview('<s:property value='CAR_ID'/>')"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
								  </s:if><s:else>
									  <a href="#" onclick="doupdate('<s:property value='CAR_ID'/>')"><img src="<%=request.getContextPath()%>/framework/img/modify.png" border="0" style="vertical-align:middle;">修改</a>
									 <a href="#" onclick="doview('<s:property value='CAR_ID'/>')"><img src="<%=request.getContextPath()%>/framework/img/view.png" border="0" style="vertical-align:middle;">查看</a>
									 <a href="#" onclick="dodelete('<s:property value='CAR_ID'/>')"><img src="<%=request.getContextPath()%>/framework/img/delete.png" border="0" style="vertical-align:middle;">删除</a>
								  </s:else>
								  </td>
				              </tr>
			          	</s:iterator>
			       
						 <tr height="32px">
                            <td height="20" align="right" colspan="11">
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
        <!--右侧内容结束-->
        </s:form>
</body>
</html>