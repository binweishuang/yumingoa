<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="kdf.constant.SystemConfig"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
	<head>
		<title></title>
		<jsp:include page="/framework/include/Resources.jsp">
			<jsp:param name="resourcelet" value="JSValidation/validation-framework.js"/>
		</jsp:include>
		<script language="javascript">
		function goBack() {
			history.back();
		}
		</script>
		<jscalendar:head language="zh"/>
	</head>
	<body id="main_content">
	<div id="errorMessage_div" style="color:red;background: #FFEFD5;border:0px solid #FFDAB9;border-collapse: collapse;">
		<s:actionerror/>
		<s:actionmessage/>
		<s:fielderror/>
	</div>
		<s:form id="folderForm" action="folderAction" theme="simple">
		<s:set name="readonly" value="#request.readonly"/>
		<s:hidden name="readonly"/>
		<table  class="right01" cellpadding="0" cellspacing="0">
			<tr>
				<td class="right01_td"><img src="<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/left-top-right.gif" width="17"  height="35" /></td>
				<td class="right02_td">
					<div class="titlebt">
						<s:if test="#readonly == false"><s:text name="folder.insert"/></s:if>
						<s:elseif test="#readonly == true"><s:text name="folder.update"/></s:elseif>
					</div>
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
						<div class="jh20">
							<span>
								<s:if test="#readonly == false"><s:text name="folder.insert"/></s:if>
								<s:elseif test="#readonly == true"><s:text name="folder.update"/></s:elseif>
							</span>
							<span id="help" onclick="return showHelp('SYS.MANAGER.DEPT');"></span>
						</div>
						<div>
							<table class="base_table">

								<tr>
									<td class="base_tR"><s:text name="folder.foldername"/>:</td>
									<td><s:textfield name="foldername" cssClass="search_txt01"/></td>

									<td class="base_tR"><s:text name="folder.flag"/>:</td>
									<td><s:textfield name="flag" cssClass="search_txt01"/></td>

								</tr>
								<tr>
									<td colspan="4" class="base_tR">
										<div>
										<table class="htable" cellpadding="0" cellspacing="0">
											<tr>
											<td colspan="4">
												<input type="button" onclick="goBack();" value="<s:text name="button.back"/>" class="search_botton01 pleft" />
												<s:reset value="%{getText('button.reset')}" cssClass="search_botton01 pleft"  ></s:reset>
												<s:if test="#readonly == false">
													<s:submit action="insert" cssClass="search_botton01 pleft" value="%{getText('button.save')}" ></s:submit>
												</s:if><s:else>
													<s:submit action="update" cssClass="search_botton01 pleft" value="%{getText('button.modify')}" ></s:submit>
												</s:else>
											</td>
											</tr>
										</table>
										</div>
									</td>
								</tr>

							</table> 
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
