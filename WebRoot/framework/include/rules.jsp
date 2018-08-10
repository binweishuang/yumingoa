<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html> 
	<head>
	<link type="text/css" rel="stylesheet" href="css/system.css"/>
		<title></title>
		<script language="javascript">
		function reflush() {
			document.getElementsByName('currentPage')[0].value=1;
			document.forms[0].action="queryRules.action";
			document.forms[0].submit();
		}
		function gotoPages(pages){	
			document.getElementsByName('currentPage')[0].value=pages;
			document.forms[0].action="queryRules.action";
			document.forms[0].submit();
		}
		function checkPages(minPages,maxPages){
			var pages = document.getElementsByName("keyword2")[0].value;
			var reg = /^[0-9]+$/;
			if(!reg.test(pages)){
				Dialog.alert("请输入有效数字!");
			}else if(pages < minPages || pages > maxPages){
				Dialog.alert("页码超出范围，请注意!");
			}else{
				gotoPages(pages);
			}
		}
		</script>
	</head>
	<body>
	
				<div style="float:left;margin-top:4px">
							<s:select name="pages" onchange="reflush();" list="#{'15':'每页15行','30':'每页30行','50':'每页50行'}" listKey="key" listValue="value"/>
							<s:set name="pg" value="#request.pages"/>
							[第 <s:property value="#pg.currentPage"/> 页 共
							<s:if test="#pg.pages == 0">
								1
							</s:if>
							<s:else>
								<s:property value="#pg.pages"/>
					</s:else> 页] [
					<s:if test="#pg.currentPage == 1">首页     前页    </s:if><s:else><a href="#" onclick="gotoPages(<s:property value="#pg.firstPage"/>)">首页 </a>
					<a href="#" onclick="gotoPages(<s:property value="#pg.previousPage"/>)">前页</a></s:else>
					<s:if test="#pg.currentPage == #pg.lastPage"> 后页  尾页  </s:if><s:else><a href="#" onclick="gotoPages(<s:property value="#pg.nextPage"/>)"> 后页</a>
					<a href="#" onclick="gotoPages(<s:property value="#pg.lastPage"/>)"> 尾页   </a></s:else>][共 <s:property value="#pg.count"/> 条记录]
						</div>
						<div style="float:right">
							跳转到第
						  <input type='text' name='keyword2' value="" class="small_textbox input_style" />
							页
							<input type="button"  class="buttons" value="GO" class="go" onclick="checkPages(1,<s:property value="#pg.lastPage"/>)"/>
						</div>
						
		
	</body>
</html>