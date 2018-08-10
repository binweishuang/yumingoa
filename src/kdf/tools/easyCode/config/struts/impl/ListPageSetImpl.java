package kdf.tools.easyCode.config.struts.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import kdf.tools.easyCode.config.struts.ListPageSet;
import kdf.tools.xml.XMLNode;

public class ListPageSetImpl implements ListPageSet {

	public void createListPage(XMLNode struts2ConfigNode,Connection conn) {
		String tableName = struts2ConfigNode.getChildNode("table").getAttributeValue("tableName");
		String tempEditPagePath = struts2ConfigNode.getChildNode("pageList").getAttributeValue("filePath");
		String[] stringArray = tempEditPagePath.split("/");

		try {
			String path="WebRoot";
			for(int i=0;i<stringArray.length;i++){
				path=path+"/"+stringArray[i];
			}
			System.out.println("路径"+path);
			
			
			if(!(new File(path)).exists()){
				  (new File(path)).mkdirs();
				  System.out.println("创建文件夹目录");
			}
			//tableName转换为beanType 格式为驼峰式
			String[] tableNameArray = tableName.split("_");
			String beanType="";
			for(int j=0;j<tableNameArray.length;j++){
				beanType+=tableNameArray[j].substring(0, 1).toUpperCase()+tableNameArray[j].substring(1).toLowerCase();
			}
			String beanName=beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
			ResultSet rs=this.getResultSet(conn,tableName);
			rs.next();
			ResultSetMetaData rsmd  = rs.getMetaData();

			FileWriter fw = new FileWriter(path + "/"+beanName+"List.jsp");
			PrintWriter out = new PrintWriter(fw);   
			
			
			List propertyTypeList = new LinkedList();
			List propertyNameList = new LinkedList();
			for(int n=1;n<=rsmd.getColumnCount();n++){
				String columnType=rsmd.getColumnTypeName(n);
				String columnName=rsmd.getColumnName(n);
				String propertyType = this.oracleToJavaType(columnType);
				String propertyName = this.oracleToJavaField(columnName);

				propertyTypeList.add(propertyType);
				propertyNameList.add(propertyName);
			}
			String tempKeyId = propertyNameList.get(0).toString();
			out.println(this.addBeginMessage(beanName,tempKeyId));
			
			String isConfig = struts2ConfigNode.getChildNode("pageList").getAttributeValue("isConfig");
			if("false".equals(isConfig) || isConfig==null || "".equals(isConfig)){//不启用
				int count = propertyNameList.size();
				System.out.println("输出 count 信息:"+count);
				for(int m =1;m<propertyNameList.size();m++){
					if(count>=5){//只设置四条信息
						if(m%2==0){
							out.print("\n\t\t\t\t\t\t\t\t\t\t<tr>");
							out.print(this.addQueryProperty(beanName,propertyNameList.get(m-1).toString(),propertyTypeList.get(m-1).toString()));
							out.print(this.addQueryProperty(beanName,propertyNameList.get(m).toString(),propertyTypeList.get(m).toString()));
							out.print("\n\t\t\t\t\t\t\t\t\t\t</tr>");
						}
						
					}else{
						if(m%2==0){
							out.print("\n\t\t\t\t\t\t\t\t\t\t<tr>");
							out.print(this.addQueryProperty(beanName,propertyNameList.get(m-1).toString(),propertyTypeList.get(m-1).toString()));
							out.print(this.addQueryProperty(beanName,propertyNameList.get(m).toString(),propertyTypeList.get(m).toString()));
							out.print("\n\t\t\t\t\t\t\t\t\t\t</tr>");
						}
						if(m==propertyNameList.size()-1 && count%2==0){
							out.print("\n\t\t\t\t\t\t\t\t\t\t<tr>");
							out.print(this.addQueryProperty(beanName,propertyNameList.get(m).toString(),propertyTypeList.get(m).toString()));
							out.print("\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"></td>");
							out.print("\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\"></td>");
							out.print("\n\t\t\t\t\t\t\t\t\t\t</tr>");
						}
					}
					System.out.println("list 中存在的字段:"+propertyNameList.get(m).toString());
				}
			}else{//启用设置信息
				XMLNode[] propertyNodeList = struts2ConfigNode.getChildNode("pageList").getChildNode("queryPropertys").selectNodes("./property");
				int tempCount = propertyNodeList.length;
				for(int t =0; t<propertyNodeList.length;t++){
					if(t!=0 && t%2!=0){
						out.print("\n\t\t\t\t\t\t\t\t\t\t<tr>");
						out.print(this.addQueryPropertyConfig(beanName,propertyNodeList[t-1]));
						out.print(this.addQueryPropertyConfig(beanName,propertyNodeList[t]));
						out.print("\n\t\t\t\t\t\t\t\t\t\t</tr>");
					}
					if(t==propertyNodeList.length-1 && tempCount%2!=0){
						out.print("\n\t\t\t\t\t\t\t\t\t\t<tr>");
						out.print(this.addQueryPropertyConfig(beanName,propertyNodeList[t]));
						out.print("\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"></td>");
						out.print("\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\"></td>");
						out.print("\n\t\t\t\t\t\t\t\t\t\t</tr>");
					}
				}
				
			}
			//添加查询  与 添加  按钮
			out.println(this.addQueryButton());
			
			//添加查询 结尾信息 
			out.println(this.addEndQueryMsg());
			
			//添加 list中的字段
			if("false".equals(isConfig) || isConfig==null || "".equals(isConfig)){//不启用
				out.print("\n\t\t\t\t\t\t\t\t\t<tr class=\"theader\">");
				for(int i=0;i<propertyNameList.size();i++){
					out.print(this.addIteratorTheaderProperty(beanName,propertyNameList.get(i).toString()));
				}
				out.print("\n\t\t\t\t\t\t\t\t\t\t<td><s:text name =\"user.operate\"/></td>");
				
				out.print("\n\t\t\t\t\t\t\t\t\t</tr>");
				out.print("\n\t\t\t\t\t\t\t\t\t<s:iterator value=\""+beanName+"List\">");
				out.print("\n\t\t\t\t\t\t\t\t\t<tr>");
				for(int j=0;j<propertyNameList.size();j++){
					out.print(this.addIteratorProperty(propertyNameList.get(j).toString(),propertyTypeList.get(j).toString()));
				}
				out.print("\n\t\t\t\t\t\t\t\t\t\t<td>" +
							"\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" onclick=\"return doDelete('<s:property value=\""+tempKeyId+"\"/>');\"><s:text name=\"user.delete\"/></a>" +
							"\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" onclick=\"doUpdate('<s:property value=\""+tempKeyId+"\"/>');\"><s:text name=\"user.update\"/></a>"+
						 "\n\t\t\t\t\t\t\t\t\t\t</td>");
				out.print("\n\t\t\t\t\t\t\t\t\t</tr>");
				out.print("\n\t\t\t\t\t\t\t\t\t</s:iterator>");
				
			}else{  //启用配置信息
				XMLNode[] propertyNodeList = struts2ConfigNode.getChildNode("pageList").getChildNode("listPropertys").selectNodes("./property");
				out.print("\n\t\t\t\t\t\t\t\t\t<tr class=\"theader\">");
				for(int i=0;i<propertyNodeList.length;i++){
					String propertyName = propertyNodeList[i].getAttributeValue("name");
					out.print(this.addIteratorTheaderProperty(beanName,propertyName));
				}
				out.print("\n\t\t\t\t\t\t\t\t\t\t<td><s:text name =\"user.operate\"/></td>");
				out.print("\n\t\t\t\t\t\t\t\t\t</tr>");
				out.print("\n\t\t\t\t\t\t\t\t\t<s:iterator value=\""+beanName+"List\">");
				out.print("\n\t\t\t\t\t\t\t\t\t<tr>");
				for(int j=0;j<propertyNodeList.length;j++){
					String propertyName = propertyNodeList[j].getAttributeValue("name");
					String propertyType = propertyNodeList[j].getAttributeValue("showType");
					out.print(this.addIteratorProperty(propertyName,propertyType));
				}
				out.print("\n\t\t\t\t\t\t\t\t\t\t<td>" +
						"\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" onclick=\"return doDelete('<s:property value=\""+tempKeyId+"\"/>');\"><s:text name=\"user.delete\"/></a>" +
						"\n\t\t\t\t\t\t\t\t\t\t\t<a href=\"#\" onclick=\"doUpdate('<s:property value=\""+tempKeyId+"\"/>');\"><s:text name=\"user.update\"/></a>"+
								"\n\t\t\t\t\t\t\t\t\t\t</td>");
				out.print("\n\t\t\t\t\t\t\t\t\t</tr>");	
				out.print("\n\t\t\t\t\t\t\t\t\t</s:iterator>");
			}
			
			//添加结尾信息
			out.println(this.addEndMessage());
			out.close();
			fw.close();
			System.out.println("生成list.jsp文件");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	
	private String addBeginMessage(String beanName,String keyId){
		String temp="<%@ page contentType=\"text/html; charset=utf-8\" language=\"java\"%>";
		temp +="\n<%@ page import=\"kdf.constant.SystemConfig\"%>";
		temp +="\n<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>";
		temp +="\n<%@ taglib prefix=\"jscalendar\" uri=\"/jscalendar\"%>";
		temp +="\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		temp +="\n<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		temp +="\n\t<head>";
		temp +="\n\t\t<title></title>";
		temp +="\n\t\t<jsp:include page=\"/framework/include/Resources.jsp\">";
		temp +="\n\t\t\t<jsp:param name=\"resourcelet\" value=\"querytable.js\"/>";
		temp +="\n\t\t</jsp:include>";
		temp +="\n\t\t<script language=\"javascript\">";
		temp +="\n\t\tfunction doDelete("+keyId+"){";
		temp +="\n\t\t\tif(confirm(\"are you sure delete ?\") ){";
		temp +="\n\t\t\t\tdocument.getElementsByName('"+keyId+"_q')[0].value="+keyId+";";
		temp +="\n\t\t\t\tdocument.forms[0].action = \"delete.action\";";
		temp +="\n\t\t\t\tdocument.forms[0].submit();";
		temp +="\n\t\t\t}";
		temp +="\n\t\t}";
		temp +="\n\t\tfunction doUpdate("+keyId+") {";
		temp +="\n\t\t\tdocument.getElementsByName('"+keyId+"_q')[0].value="+keyId+";";
		temp +="\n\t\t\tdocument.forms[0].action = \"updateInit.action\";";
		temp +="\n\t\t\tdocument.forms[0].submit();";
		temp +="\n\t\t}";
		temp +="\n\t\t</script>";
		temp +="\n\t<jscalendar:head language=\"zh\"/>";
		temp +="\n\t</head>";
		temp +="\n\t<body id=\"main_content\">";
		temp +="\n\t\t<div id=\"errorMessage_div\" style=\"color:red;background: #FFEFD5;" +
				"border:0px solid #FFDAB9;border-collapse: collapse;\">";
		temp +="\n\t\t\t<s:actionerror/>";
		temp +="\n\t\t\t<s:actionmessage/>";
		temp +="\n\t\t\t<s:fielderror />";
		temp +="\n\t\t</div>";
		temp +="\n\t\t<s:form action=\""+beanName+"Action\" theme=\"simple\">";
		temp +="\n\t\t\t<s:hidden name=\"currentPage\"/>";
		temp +="\n\t\t\t<s:hidden name=\""+keyId+"_q\"/>";
		temp +="\n\t\t\t<table class=\"right01\" cellpadding=\"0\" cellspacing=\"0\">";
		temp +="\n\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t<td class=\"right01_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/left-top-right.gif\" width=\"17\"  height=\"35\" /></td>";
		temp +="\n\t\t\t\t\t<td class=\"right02_td\">";
		temp +="\n\t\t\t\t\t\t<div class=\"titlebt\"><s:text name=\""+beanName+".query\"/></div>";
		temp +="\n\t\t\t\t\t\t<ul class=\"maincon\">";
		temp +="\n\t\t\t\t\t\t\t<li><span class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" onmouseout=\"this.className='nobspan2'\" onclick=\"return showHelp('KDF.SYS.DEPT');\"><label><s:text name=\"sys.help\"/></label></span></li>";
		temp +="\n\t\t\t\t\t\t\t<li style=\"background:none;\">";
		temp +="\n\t\t\t\t\t\t\t\t<span id=\"screenQ\" class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" onmouseout=\"this.className='nobspan2'\">";
		temp +="\n\t\t\t\t\t\t\t\t\t<strong id=\"menuSwitch\" onclick=\"changeWin(isAllScreen)\"><s:text name=\"sys.fullScreen\"/></strong>";
		temp +="\n\t\t\t\t\t\t\t\t</span>";
		temp +="\n\t\t\t\t\t\t\t\t<span id=\"screenH\" class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" onmouseout=\"this.className='nobspan2'\">";
		temp +="\n\t\t\t\t\t\t\t\t\t<em id=\"menuSwitch\" onclick=\"changeWin(isAllScreen)\"><s:text name=\"sys.restore\"/></em>";
		temp +="\n\t\t\t\t\t\t\t\t</span>";
		temp +="\n\t\t\t\t\t\t\t</li>";
		temp +="\n\t\t\t\t\t\t</ul>";
		temp +="\n\t\t\t\t\t</td>";
		temp +="\n\t\t\t\t\t<td class=\"right03_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/nav-right-bg.gif\" width=\"16\" height=\"34\" /></td>";
		temp +="\n\t\t\t\t</tr>";
		temp +="\n\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t<td class=\"right04_td\">&nbsp;</td>";
		temp +="\n\t\t\t\t\t<td class=\"right05_td\">";
		temp +="\n\t\t\t\t\t\t<div id=\"main_content1\">";
		temp +="\n\t\t\t\t\t\t\t<div id=\"search_box\">";
		temp +="\n\t\t\t\t\t\t\t\t<div class=\"search_h28 pstitle\"><s:text name=\""+beanName+".query\"/></div>";
		temp +="\n\t\t\t\t\t\t\t\t<div class=\"search_h86\">";
		temp +="\n\t\t\t\t\t\t\t\t\t<table class=\"search_box_table\">";

		return temp;
	}
	
	private String addQueryButton(){
		String temp ="\n\t\t\t\t\t\t\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"6\">";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"pbutton\">";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t\t<s:submit action=\"query\" cssClass=\"search_botton01\" value=\"%{getText('button.query')}\"></s:submit>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t\t<s:submit action=\"insertInit\" cssClass=\"search_botton01\" value=\"%{getText('button.add')}\" ></s:submit>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t</td>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t</tr>";
		return temp;
	}
	
	
	private String addEndQueryMsg(){
		String temp ="\n\t\t\t\t\t\t\t\t\t</table>";
		temp +="\n\t\t\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t\t<div id=\"peos_fee\">";
		temp +="\n\t\t\t\t\t\t\t\t<table id=\"peos_ftable\" onmouseover=\"overIt();\" " +
				"onmouseout=\"outIt();\" onclick=\"clickIt();\">";		
		return temp;
	}
	
	
	private String addQueryProperty(String beanName,String propertyName,String propertyType){
		String temp ="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"><s:text name=\""+beanName+"."+propertyName+"\"/>:</td>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\"><s:textfield name=\""+propertyName+"\" cssClass=\"search_txt01\"/></td>";
				
		return temp;
	}
	
	private String addQueryPropertyConfig(String beanName,XMLNode propertyNode){
		String temp="";
		String type = propertyNode.getAttributeValue("showType");
		String propertyName = propertyNode.getAttributeValue("name");
		String list = propertyNode.getAttributeValue("list");
		String emptyOption = propertyNode.getAttributeValue("emptyOption");
		
		if(type.equalsIgnoreCase("textfield")){
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"><s:text name=\""+beanName+"."+propertyName+"\"/>:</td>";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\"><s:textfield name=\""+propertyName+"\" cssClass=\"search_txt01\"/></td>";
		}else if(type.equalsIgnoreCase("select")){
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"><s:text name=\""+beanName+"."+propertyName+"\"/>:</td>";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\">";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<s:select list=\""+list+"\" name=\""+propertyName+"\" emptyOption=\""+emptyOption+"\"></s:select>";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t</td>";
		}else if(type.equalsIgnoreCase("jscalendar")){
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"search_tR\"><s:text name=\""+beanName+"."+propertyName+"\"/>:</td>";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td width=\"38%\">";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<jscalendar:jscalendar name=\""+propertyName+"\" showstime=\"true\" format=\"%Y-%m-%d\" tooltip=\"%{getText('jscalendar.tooltip')}\"/>";
			temp +="\n\t\t\t\t\t\t\t\t\t\t\t</td>";
		}
				
		return temp;
	}
	private String addIteratorTheaderProperty(String beanName,String property){
		String temp="\n\t\t\t\t\t\t\t\t\t\t<td><s:text name=\""+beanName+"."+property+"\"/></td>";

		return temp;
	}

	private String addIteratorProperty(String propertyName,String propertyType){
		String temp ="";
		if(propertyType.equalsIgnoreCase("date")){
			temp +="\n\t\t\t\t\t\t\t\t\t\t<td><s:date name=\""+propertyName+"\" format=\"yyyy-MM-dd\"/></td>";
		}else{
			temp +="\n\t\t\t\t\t\t\t\t\t\t<td><s:property value=\""+propertyName+"\"/></td>";
		}
		return temp;
	}
	
	private String addEndMessage(){
		String temp="\n\t\t\t\t\t\t\t\t</table>";
		temp +="\n\t\t\t\t\t\t\t\t<s:include value=\"/framework/include/page.jsp\"/>";
		temp +="\n\t\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t</td>";
		temp +="\n\t\t\t\t\t<td class=\"right06_td\">&nbsp;</td>";
		temp +="\n\t\t\t\t</tr>";
		temp +="\n\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t<td class=\"right07_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_left2.gif\" width=\"17\" height=\"17\" /></td>";
		temp +="\n\t\t\t\t\t<td class=\"right08_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_bgs.gif\" width=\"17\" height=\"17\" /></td>";
		temp +="\n\t\t\t\t\t<td class=\"right09_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_right2.gif\" width=\"16\" height=\"17\" /></td>";
		temp +="\n\t\t\t\t</tr>";
		temp +="\n\t\t\t</table>";
		temp +="\n\t\t\t</s:form>";
		temp +="\n\t\t</body>";
		temp +="\n\t</html>";
		return temp;
	}
	
	private String oracleToJavaField(String columnName){
		//oracleName转换为javaName 格式为驼峰式
		String[] columnNameArray = columnName.split("_");
		String javaPopertyName=columnNameArray[0].substring(0, 1).toLowerCase()+columnNameArray[0].substring(1).toLowerCase();
		
		for(int j=1;j<columnNameArray.length;j++){
			javaPopertyName+=columnNameArray[j].substring(0, 1).toUpperCase()+columnNameArray[j].substring(1).toLowerCase();
		}
		return javaPopertyName;
	}
		

	private ResultSet getResultSet(Connection conn,String tableName){
		ResultSet rs=null;
		try {
			Statement stmt = conn.createStatement();
			String sql="select * from "+tableName;
			stmt.executeUpdate(sql);
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  rs;
	}
	
	/***
	 * columnType:BINARY_DOUBLE
	 * columnType:BINARY_FLOAT
	 * columnType:LONG
	 * columnType:NUMBER
	 * columnType:BLOB
	 * columnType:CLOB

	 * columnType:DATE
	 * columnType:TIMESTAMP
	 * columnType:INTERVALDS
	 * columnType:INTERVALYM
	 * columnType:RAW
	 * columnType:VARCHAR2
	 * columnType:CHAR
	 * */
	private String oracleToJavaType(String columnType){
		if("VARCHAR2".equalsIgnoreCase(columnType) ||  "CHAR".equalsIgnoreCase(columnType)){
			return "String";
		}
		if("DATE".equalsIgnoreCase(columnType) ||  "TIMESTAMP".equalsIgnoreCase(columnType)){
			return "Date";
		}
		if("NUMBER".equalsIgnoreCase(columnType) ||  "LONG".equalsIgnoreCase(columnType)){
			return "Double";
		}
		
		return "String";
	}


}
