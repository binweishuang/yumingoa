<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<jsp:include page="/framework/include/Resources.jsp">
			<jsp:param name="resourcelet" value="querytable.js"/>
		</jsp:include>
		<script language="javascript">
		function doDelete(answerId){
			if(confirm("are you sure delete ?") ){
				document.getElementsByName('answerId_q')[0].value=answerId;
				document.forms[0].action = "delete.action";
				document.forms[0].submit();
			}
		}
		function doUpdate(answerId) {
			document.getElementsByName('answerId_q')[0].value=answerId;
			document.forms[0].action = "updateInit.action";
			document.forms[0].submit();
		}
		</script>
	<jscalendar:head language="zh"/>
	</head>
	<body id="main_content">
		<div id="errorMessage_div" style="color:red;background: #FFEFD5;border:0px solid #FFDAB9;border-collapse: collapse;">
			<s:actionerror/>
			<s:actionmessage/>
			<s:fielderror />
		</div>
		<s:form action="answerAction" theme="simple">
			<s:hidden name="currentPage"/>
			<s:hidden name="answerId_q"/>
			<table class="right01" cellpadding="0" cellspacing="0">
				<tr>
					<td class="right01_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/left-top-right.gif" width="17"  height="35" /></td>
					<td class="right02_td">
						<div class="titlebt"><s:text name="answer.query"/></div>
						<ul class="maincon">
							<li><span class="mspan02" onmouseover="this.className='mspan02bg'" onmouseout="this.className='nobspan2'" onclick="return showHelp('KDF.SYS.DEPT');"><label><s:text name="sys.help"/></label></span></li>
							<li style="background:none;">
								<span id="screenQ" class="mspan02" onmouseover="this.className='mspan02bg'" onmouseout="this.className='nobspan2'">
									<strong id="menuSwitch" onclick="changeWin(isAllScreen)"><s:text name="sys.fullScreen"/></strong>
								</span>
								<span id="screenH" class="mspan02" onmouseover="this.className='mspan02bg'" onmouseout="this.className='nobspan2'">
									<em id="menuSwitch" onclick="changeWin(isAllScreen)"><s:text name="sys.restore"/></em>
								</span>
							</li>
						</ul>
					</td>
					<td class="right03_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/nav-right-bg.gif" width="16" height="34" /></td>
				</tr>
				<tr>
					<td class="right04_td">&nbsp;</td>
					<td class="right05_td">
						<div id="main_content1">
							<div id="search_box">
								<div class="search_h28 pstitle"><s:text name="answer.query"/></div>
								<div class="search_h86">
									<table class="search_box_table">

										<tr>
											<td width="12%" class="search_tR"><s:text name="answer.sortnum"/>:</td>
											<td width="38%"><s:textfield name="sortnum" cssClass="search_txt01"/></td>
											<td width="12%" class="search_tR"><s:text name="answer.content"/>:</td>
											<td width="38%"><s:textfield name="content" cssClass="search_txt01"/></td>
										</tr>
										<tr>
											<td width="12%" class="search_tR"><s:text name="answer.uservotenum"/>:</td>
											<td width="38%"><s:textfield name="uservotenum" cssClass="search_txt01"/></td>
											<td width="12%" class="search_tR"><s:text name="answer.surveyId"/>:</td>
											<td width="38%"><s:textfield name="surveyId" cssClass="search_txt01"/></td>
										</tr>
										<tr>
											<td colspan="6">
												<div class="pbutton">
													<s:submit action="query" cssClass="search_botton01" value="%{getText('button.query')}"></s:submit>
													<s:submit action="insertInit" cssClass="search_botton01" value="%{getText('button.add')}" ></s:submit>
												</div>
											</td>
										</tr>

									</table>
								</div>
							</div>
							<div id="peos_fee">
								<table id="peos_ftable" onmouseover="overIt();" onmouseout="outIt();" onclick="clickIt();">

									<tr class="theader">
										<td><s:text name="answer.answerId"/></td>
										<td><s:text name="answer.sortnum"/></td>
										<td><s:text name="answer.content"/></td>
										<td><s:text name="answer.uservotenum"/></td>
										<td><s:text name="answer.surveyId"/></td>
										<td><s:text name="answer.flag"/></td>
										<td><s:text name ="user.operate"/></td>
									</tr>
									<s:iterator value="answerList">
									<tr>
										<td><s:property value="answerId"/></td>
										<td><s:property value="sortnum"/></td>
										<td><s:property value="content"/></td>
										<td><s:property value="uservotenum"/></td>
										<td><s:property value="surveyId"/></td>
										<td><s:property value="flag"/></td>
										<td>
											<a href="#" onclick="return doDelete('<s:property value="answerId"/>');"><s:text name="user.delete"/></a>
											<a href="#" onclick="doUpdate('<s:property value="answerId"/>');"><s:text name="user.update"/></a>
										</td>
									</tr>
									</s:iterator>
								</table>
								<s:include value="/framework/include/page.jsp"/>
							</div>
						</div>
					</td>
					<td class="right06_td">&nbsp;</td>
				</tr>
				<tr>
					<td class="right07_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_left2.gif" width="17" height="17" /></td>
					<td class="right08_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_bgs.gif" width="17" height="17" /></td>
					<td class="right09_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_right2.gif" width="16" height="17" /></td>
				</tr>
			</table>
			</s:form>
		</body>
	</html>
