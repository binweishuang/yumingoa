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

import kdf.tools.easyCode.config.struts.EditPageSet;
import kdf.tools.easyCode.config.struts.beanTableMsg.BeanTableMsg;
import kdf.tools.easyCode.config.struts.beanTableMsg.PropertyClass;
import kdf.tools.xml.XMLNode;

public class EditPageSetImpl implements EditPageSet {

	public BeanTableMsg createEditPage(XMLNode struts2ConfigNode,Connection conn) {
		BeanTableMsg beanTableMsg = new BeanTableMsg();
		String tableName = struts2ConfigNode.getChildNode("table").getAttributeValue("tableName");
		//添加table名称
		beanTableMsg.setTableName(tableName);
		String tempEditPagePath = struts2ConfigNode.getChildNode("pageEdit").getAttributeValue("filePath");
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
			//添加beanType
			beanTableMsg.setBeanType(beanType);
			//添加beanName 
			beanTableMsg.setBeanName(beanName);
			
			ResultSet rs=this.getResultSet(conn,tableName);
			rs.next();
			ResultSetMetaData rsmd  = rs.getMetaData();

			FileWriter fw = new FileWriter(path + "/"+beanName+"Edit.jsp");
			PrintWriter out = new PrintWriter(fw);   
			
			out.println(this.addBeginMessage(beanName));

			List columnTypeList = new LinkedList();
			List columnNameList = new LinkedList();
			List propertyTypeList = new LinkedList();
			List propertyNameList = new LinkedList();
			
			for(int n=1;n<=rsmd.getColumnCount();n++){
				String columnType=rsmd.getColumnTypeName(n);
				String columnName=rsmd.getColumnName(n);
				String propertyType = this.oracleToJavaType(columnType);
				String propertyName = this.oracleToJavaField(columnName);

				columnTypeList.add(columnType);
				columnNameList.add(columnName);
				propertyTypeList.add(propertyType);
				propertyNameList.add(propertyName);
			}
			beanTableMsg.setFieldList(columnNameList);
			beanTableMsg.setFieldTypeList(columnTypeList);
			beanTableMsg.setPropertyList(propertyNameList);
			beanTableMsg.setPropertyTypeList(propertyTypeList);
			//list页面中是否可配 设置
			String listIsConfig = struts2ConfigNode.getChildNode("pageList").getAttributeValue("isConfig");
			if("true".equalsIgnoreCase(listIsConfig)){
				beanTableMsg.setIsConfig("true");
				List queryProperyList = new LinkedList();
				XMLNode queryPropertyNode = struts2ConfigNode.getChildNode("pageList").getChildNode("queryPropertys");
				XMLNode[] propertyQueryList = queryPropertyNode.selectNodes("./property");
				for(int i=0;i<propertyQueryList.length;i++){
					PropertyClass propertyClass = new PropertyClass();
					XMLNode propertyNode = propertyQueryList[i];
					propertyClass.setPropertyName(propertyNode.getAttributeValue("name"));
					propertyClass.setPropertyType("String");
					queryProperyList.add(propertyClass);
				}
			}
			
			
			
			
			
			//编辑页面的设置
			String isConfig = struts2ConfigNode.getChildNode("pageEdit").getAttributeValue("isConfig");
			if("false".equals(isConfig) || isConfig==null || "".equals(isConfig)){//不启用
				int count = propertyNameList.size();
				System.out.println("输出 count 信息:"+count);
				for(int m =1;m<propertyNameList.size();m++){
					if(m%2==0){
						out.print("\n\t\t\t\t\t\t\t\t<tr>");
						out.println(this.addEditProperty(beanName,propertyNameList.get(m-1).toString()));
						out.println(this.addEditProperty(beanName,propertyNameList.get(m).toString()));
						out.print("\n\t\t\t\t\t\t\t\t</tr>");
						
					}
					if(m==propertyNameList.size()-1 && count%2==0){
						out.print("\n\t\t\t\t\t\t\t\t<tr>");
						
						out.println(this.addEditProperty(beanName,propertyNameList.get(m).toString()));
						out.print("\n\t\t\t\t\t\t\t\t\t<td width=\"12%\" class=\"base_tR\"></td>");
						out.print("\n\t\t\t\t\t\t\t\t\t<td width=\"23%\"></td>");
						out.print("\n\t\t\t\t\t\t\t\t</tr>");
					}
					
					
					System.out.println("list 中存在的字段:"+propertyNameList.get(m).toString());
				}
			}
			
			out.println(this.addButtonMsg());
			out.println(this.addEndMessage());
			out.close();
			fw.close();
			System.out.println("生成edit.jsp文件");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return beanTableMsg;
	}
	
	
	private String addButtonMsg(){
		String temp ="\n\t\t\t\t\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t\t\t\t\t<td colspan=\"4\" class=\"base_tR\">";
		temp +="\n\t\t\t\t\t\t\t\t\t\t<div>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t<table class=\"htable\" cellpadding=\"0\" cellspacing=\"0\">";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t<tr>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"4\">";
		
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"button\" onclick=\"goBack();\" value=\"<s:text name=\"button.back\"/>\" class=\"search_botton01 pleft\" />";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<s:reset value=\"%{getText('button.reset')}\" cssClass=\"search_botton01 pleft\"  ></s:reset>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t<s:if test=\"#readonly == false\">";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t\t<s:submit action=\"insert\" cssClass=\"search_botton01 pleft\" value=\"%{getText('button.save')}\" ></s:submit>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t</s:if><s:else>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t\t<s:submit action=\"update\" cssClass=\"search_botton01 pleft\" value=\"%{getText('button.modify')}\" ></s:submit>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t\t</s:else>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t</td>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t\t</tr>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t</table>";
		temp +="\n\t\t\t\t\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t\t\t\t</td>";
		temp +="\n\t\t\t\t\t\t\t\t</tr>";
		return temp;
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
	
	private String addBeginMessage(String beanName){
		String temp ="<%@ page contentType=\"text/html; charset=utf-8\" language=\"java\"%>";
		temp += "\n<%@ page import=\"kdf.constant.SystemConfig\"%>";
		temp += "\n<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>";
		temp += "\n<%@ taglib prefix=\"jscalendar\" uri=\"/jscalendar\"%>";
		temp += "\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" " +
				"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		
		temp += "\n<html xmlns=\"http://www.w3.org/1999/xhtml\"> ";
		temp +="\n\t<head>";
		temp +="\n\t\t<title></title>";
		temp +="\n\t\t<jsp:include page=\"/framework/include/Resources.jsp\">";
		temp +="\n\t\t\t<jsp:param name=\"resourcelet\" value=\"JSValidation/validation-framework.js\"/>";
		temp +="\n\t\t</jsp:include>";
		temp +="\n\t\t<script language=\"javascript\">";
		temp +="\n\t\tfunction goBack() {";
		temp +="\n\t\t\thistory.back();";
		temp +="\n\t\t}";
		temp +="\n\t\t</script>";
		temp +="\n\t\t<jscalendar:head language=\"zh\"/>";
		temp +="\n\t</head>";
		//设置body 信息
		temp +="\n\t<body id=\"main_content\">";
		temp +="\n\t<div id=\"errorMessage_div\" style=\"color:red;background: #FFEFD5;border:0px " +
				"solid #FFDAB9;border-collapse: collapse;\">";
		temp +="\n\t\t<s:actionerror/>";
		temp +="\n\t\t<s:actionmessage/>";
		temp +="\n\t\t<s:fielderror/>";
		temp +="\n\t</div>";
		temp +="\n\t\t<s:form id=\""+beanName+"Form\" action=\""+beanName+"Action\" theme=\"simple\">";
		temp +="\n\t\t<s:set name=\"readonly\" value=\"#request.readonly\"/>";
		temp +="\n\t\t<s:hidden name=\"readonly\"/>";
		temp +="\n\t\t<table  class=\"right01\" cellpadding=\"0\" cellspacing=\"0\">";
		temp +="\n\t\t\t<tr>";
		temp +="\n\t\t\t\t<td class=\"right01_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/left-top-right.gif\" width=\"17\"  height=\"35\" /></td>";
		temp +="\n\t\t\t\t<td class=\"right02_td\">";
		temp +="\n\t\t\t\t\t<div class=\"titlebt\">";
		temp +="\n\t\t\t\t\t\t<s:if test=\"#readonly == false\"><s:text name=\""+beanName+".insert\"/></s:if>";
		temp +="\n\t\t\t\t\t\t<s:elseif test=\"#readonly == true\"><s:text name=\""+beanName+".update\"/></s:elseif>";
		temp +="\n\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t<ul class=\"maincon\">";
		temp +="\n\t\t\t\t\t\t<li><span class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" " +
				"onmouseout=\"this.className='nobspan2'\" onclick=\"return showHelp('KDF.SYS.DEPT');\"><label><s:text name=\"sys.help\"/></label></span></li>";
		temp +="\n\t\t\t\t\t\t<li style=\"background:none;\">";
		temp +="\n\t\t\t\t\t\t\t<span id=\"screenQ\" class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" onmouseout=\"this.className='nobspan2'\">";
		temp +="\n\t\t\t\t\t\t\t\t<strong id=\"menuSwitch\" onclick=\"changeWin(isAllScreen)\"><s:text name=\"sys.fullScreen\"/></strong>";
		temp +="\n\t\t\t\t\t\t\t</span>";
		temp +="\n\t\t\t\t\t\t\t<span id=\"screenH\" class=\"mspan02\" onmouseover=\"this.className='mspan02bg'\" onmouseout=\"this.className='nobspan2'\">";
		temp +="\n\t\t\t\t\t\t\t\t<em id=\"menuSwitch\" onclick=\"changeWin(isAllScreen)\"><s:text name=\"sys.restore\"/></em>";
		temp +="\n\t\t\t\t\t\t\t</span>";
		temp +="\n\t\t\t\t\t\t</li>";
		temp +="\n\t\t\t\t\t</ul>";
		temp +="\n\t\t\t\t</td>";
		temp +="\n\t\t\t\t<td class=\"right03_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>" +
				"/images/common/nav-right-bg.gif\" width=\"16\" height=\"34\" /></td>";
		temp +="\n\t\t\t</tr>";
		temp +="\n\t\t\t<tr>";
		temp +="\n\t\t\t\t<td class=\"right04_td\">&nbsp;</td>";
		temp +="\n\t\t\t\t<td class=\"right05_td\">";
		temp +="\n\t\t\t\t\t<div id=\"main_content1\">";
		temp +="\n\t\t\t\t\t\t<div class=\"jh20\">";
		temp +="\n\t\t\t\t\t\t\t<span>";
		temp +="\n\t\t\t\t\t\t\t\t<s:if test=\"#readonly == false\"><s:text name=\""+beanName+".insert\"/></s:if>";
		temp +="\n\t\t\t\t\t\t\t\t<s:elseif test=\"#readonly == true\"><s:text name=\""+beanName+".update\"/></s:elseif>";
		temp +="\n\t\t\t\t\t\t\t</span>";
		temp +="\n\t\t\t\t\t\t\t<span id=\"help\" onclick=\"return showHelp('SYS.MANAGER.DEPT');\"></span>";
		temp +="\n\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t\t<div>";
		temp +="\n\t\t\t\t\t\t\t<table class=\"base_table\">";
		
		return temp;
	}
	private String addEditProperty(String beanName,String propertyName){
		String temp ="\n\t\t\t\t\t\t\t\t\t<td class=\"base_tR\"><s:text name=\""+beanName+"."+propertyName+"\"/>:</td>";
		temp +="\n\t\t\t\t\t\t\t\t\t<td><s:textfield name=\""+propertyName+"\" cssClass=\"search_txt01\"/></td>";
		
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
	
	private String addEndMessage(){
		String temp ="\n\t\t\t\t\t\t\t</table> ";
		temp +="\n\t\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t\t</div>";
		temp +="\n\t\t\t\t</td>";
		temp +="\n\t\t\t\t<td class=\"right06_td\">&nbsp;</td>";
		temp +="\n\t\t\t</tr>";
		temp +="\n\t\t\t<tr>";
		temp +="\n\t\t\t\t<td class=\"right07_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_left2.gif\" width=\"17\" height=\"17\" /></td>";
		temp +="\n\t\t\t\t<td class=\"right08_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_bgs.gif\" width=\"17\" height=\"17\" /></td>";
		temp +="\n\t\t\t\t<td class=\"right09_td\"><img src=\"<%= SystemConfig.SYS_RESOURCES_PATH %>/images/common/buttom_right2.gif\" width=\"16\" height=\"17\" /></td>";
		temp +="\n\t\t\t</tr>";
		temp +="\n\t\t</table>";
		temp +="\n\t\t</s:form>";
		temp +="\n\t</body>";
		temp +="\n</html>";
		
		
		return temp;
	}

}
