package kdf.tools.easyCode.config.ibatis.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import kdf.tools.easyCode.config.ibatis.CreateIbatisConfig;
import kdf.tools.xml.XMLNode;

public class CreateIbatisConfigImpl implements CreateIbatisConfig {
	private List objFieldList = new LinkedList();
	private List tableColumnList = new LinkedList();
	

	public void createIbatisConfig(String tableName,XMLNode buildIbatisConfigNode,Connection conn) {
		String filePath=buildIbatisConfigNode.getChildNode("ibatisConfigFile").getAttributeValue("filePath");
		String[] stringArray = filePath.split("\\.");
		tableName=tableName.toLowerCase();
		try {
			String path="src";
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
			FileWriter fw = new FileWriter(path + "/"+beanType+".xml");
			PrintWriter out = new PrintWriter(fw); 
			String beanName=beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
			out.println(this.addBeginMessage(buildIbatisConfigNode,beanType,beanName));
			ResultSet rs=this.getResultSet(conn,tableName);
			rs.next();
			ResultSetMetaData rsmd  = rs.getMetaData();
			
			out.println(this.addTableMapJavaObj(rsmd, tableName, beanName));
			//为ibatis配置文件添加查询的SQLMSG	
			XMLNode[] nodeList = buildIbatisConfigNode.getChildNode("daoMethods").selectNodes("./method");
			System.out.print("nodeList的length："+nodeList.length);
			if(nodeList.length>=1){
				for(int i=0;i<nodeList.length;i++){
					out.println(this.addSQLMsg(nodeList[i],conn,beanType,beanName,tableName));		
				}
			}
			
			out.println(this.addEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成ibatis配置文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String addBeginMessage(XMLNode buildIbatisConfigNode,String beanType,String beanName){
		String tempStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		tempStr +="\n<!DOCTYPE sqlMap  PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\"  \n\t\"http://ibatis.apache.org/dtd/sql-map-2.dtd\">";
		tempStr += "\n<sqlMap namespace=\""+beanType+"\">";
		String beanPath = buildIbatisConfigNode.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType;
		tempStr +="\n\t<typeAlias alias=\""+beanName+"\" type=\""+beanPath+"\"/>";
		tempStr +="\n\t<typeAlias alias=\"list\" type=\"java.util.List\"/>";
		tempStr +="\n\t<typeAlias alias=\"string\" type=\"java.lang.String\"/>";
		tempStr +="\n\t<typeAlias alias=\"map\" type=\"java.util.Map\"/>";
		tempStr +="\n\t<typeAlias alias=\"hashmap\" type=\"java.util.HashMap\"/>";
		
		
		return tempStr;
	}
	private String addTableMapJavaObj(ResultSetMetaData rsmd,String tableName,String beanName){
		String tempStr="";
		
		tempStr += "\n\t<resultMap id=\""+tableName+"\" class=\""+beanName+"\"> ";
		try {
			for(int n=1;n<=rsmd.getColumnCount();n++){
				String columnName=rsmd.getColumnName(n);
				String fieldName = this.oracleToJavaField(columnName);
				tempStr += "\n\t\t<result property=\""+fieldName+"\" column=\""+columnName+"\"/>";
				objFieldList.add(fieldName);
				tableColumnList.add(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tempStr += "\n\t</resultMap>";
		
		return tempStr;
		
	}
	private String addSQLMsg(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+methodNode.getAttributeValue("methodNamePostfix");
		if("querys".equalsIgnoreCase(methodName)){
			return this.queryObjList(methodNode, conn,beanType,beanName, tableName);
		}else if("queryCounts".equalsIgnoreCase(methodName)){
			return this.queryCount(methodNode,conn, beanType,beanName, tableName);
		}else if("queryById".equalsIgnoreCase(methodName)){
			return this.queryObjById(methodNode,conn, beanType,beanName, tableName);
		}else if("execDeleteById".equalsIgnoreCase(methodName)){
			return this.deleteObjById(methodNode,conn,beanType, beanName, tableName);
		}else if("execInsert".equalsIgnoreCase(methodName)){
			return this.insertObj(methodNode,conn,beanType, beanName, tableName);
		}else if("execUpdate".equalsIgnoreCase(methodName)){
			return this.updateObj(methodNode,conn,beanType, beanName, tableName);
		}
		return "";
	}
	
	private String queryObjList(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String queryListStr="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
		queryListStr +="\n\t<select id=\""+methodName+"\" parameterClass=\"map\"  resultMap=\""+tableName+"\">";
		
		queryListStr +="\n\t\tselect * from "+tableName ;
		queryListStr += "\n\t\t\t<dynamic prepend=\"where\">";
		for(int i=0;i<tableColumnList.size();i++){
			queryListStr +="\n\t\t\t\t<isNotEmpty prepend=\"and\" property=\""+objFieldList.get(i).toString()+"\">";
			queryListStr +="\n\t\t\t\t\t"+tableColumnList.get(i).toString()+" like #"+objFieldList.get(i).toString()+"#";
			queryListStr +="\n\t\t\t\t</isNotEmpty>";
		}
		queryListStr +="\n\t\t\t</dynamic>";
				
		queryListStr +="\n\t</select>";

		return queryListStr;
	}
	private String queryCount(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String queryCountStr="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
		queryCountStr +="\n\t<select id=\""+methodName+"\" parameterClass=\"map\"  resultClass=\"int\">";
		
		queryCountStr +="\n\t\tselect count(*) from "+tableName ;
		queryCountStr += "\n\t\t\t<dynamic prepend=\"where\">";
		for(int i=0;i<tableColumnList.size();i++){
			queryCountStr +="\n\t\t\t\t<isNotEmpty prepend=\"and\" property=\""+objFieldList.get(i).toString()+"\">";
			queryCountStr +="\n\t\t\t\t\t"+tableColumnList.get(i).toString()+" like #"+objFieldList.get(i).toString()+"#";
			queryCountStr +="\n\t\t\t\t</isNotEmpty>";
		}
		queryCountStr +="\n\t\t\t</dynamic>";
		
		
		queryCountStr +="\n\t</select>";
		return queryCountStr;
	}
	private String queryObjById(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String queryByIdStr ="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
		queryByIdStr +="\n\t<select id=\""+methodName+"\" parameterClass=\"string\"  resultMap=\""+tableName+"\">";
		queryByIdStr +="\n\t\tselect * from "+tableName+" where "+tableColumnList.get(0).toString()+" = "+"#value#";;
		queryByIdStr +="\n\t</select>";
		return queryByIdStr;
	}
	
	private String deleteObjById(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String deleteStr ="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
		deleteStr +="\n\t<delete id=\""+methodName+"\" parameterClass=\"string\">";
		deleteStr +="\n\t\t delete from "+tableName+" where "+tableColumnList.get(0).toString()+" = "+"#"+objFieldList.get(0).toString()+"#";
		deleteStr +="\n\t</delete>";
		return deleteStr;
	}
	private String updateObj(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String updateStr="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
		updateStr += "\n\t<update id=\""+methodName+"\" parameterClass=\""+beanName+"\">";
		updateStr +="\n\t\tupdate "+tableName+" set";
		String tempStr="";
		for(int i=0;i<tableColumnList.size();i++){
			if(i<tableColumnList.size()-1){
				tempStr+="\n\t\t\t"+tableColumnList.get(i)+"=#"+objFieldList.get(i)+"#,";
			}else{
				tempStr+="\n\t\t\t"+tableColumnList.get(i)+"=#"+objFieldList.get(i)+"#";
			}
		}
		updateStr +=tempStr;
		updateStr +="\n\t\twhere "+tableColumnList.get(0).toString()+" = "+"#"+objFieldList.get(0).toString()+"#";
		updateStr +="\n\t</update>";
		return updateStr;
	}
	
	private String insertObj(XMLNode methodNode,Connection conn,String beanType,String beanName,String tableName){
		String insertStr ="";
		String methodName = methodNode.getAttributeValue("methodNamePrefix")+beanType+methodNode.getAttributeValue("methodNamePostfix");
	
		insertStr +="\n\t<insert id=\""+methodName+"\" parameterClass=\""+beanName+"\">";
		insertStr +="\n\t\tinsert into "+tableName+"(";
		//添加表中的字段
		String tempColumn="";
		for(int i=0;i<tableColumnList.size();i++){
			if(i%2==0){
				tempColumn+="\n\t\t\t";
			}
			if(i<tableColumnList.size()-1){
				tempColumn+=tableColumnList.get(i).toString()+",";
			}else{
				tempColumn+=tableColumnList.get(i).toString();
			}
		}
		insertStr +=tempColumn;
		insertStr +="\n\t\t) values(";
		//添加类中的字段
		String tempField="";
		for(int j=0;j<objFieldList.size();j++){
			if(j%2==0){
				tempField+="\n\t\t\t";
			}
			if(j<objFieldList.size()-1){
				tempField+="#"+objFieldList.get(j).toString()+"#,";
			}else{
				tempField+="#"+objFieldList.get(j).toString()+"#";
			}
		}
		insertStr +=tempField;
		insertStr +="\n\t\t)";
		insertStr +="\n\t</insert>";
		return insertStr;
	}	
	
	private String addEndMessage(){
		
		return "</sqlMap>";
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
	
	private String oracleToJavaField(String columnName){
		//oracleName转换为javaName 格式为驼峰式
		String[] columnNameArray = columnName.split("_");
		String javaPopertyName=columnNameArray[0].substring(0, 1).toLowerCase()+columnNameArray[0].substring(1).toLowerCase();
		
		for(int j=1;j<columnNameArray.length;j++){
			javaPopertyName+=columnNameArray[j].substring(0, 1).toUpperCase()+columnNameArray[j].substring(1).toLowerCase();
		}
		return javaPopertyName;
	}
	
	/***************************生成ibatis汇总文件*************************************/
	public void createGatherIbatisConfigFile(XMLNode buildIbatisConfigNode, XMLNode[] sourceTablesNode) {
		String gatherFilePath=buildIbatisConfigNode.getChildNode("gatherIbatisConfigFile").getAttributeValue("filePath");
		String[] stringArray = gatherFilePath.split("\\.");
		//生成接口
		String ibatisGatherFilePath="src";
		for(int i=0;i<stringArray.length;i++){
			ibatisGatherFilePath +="/"+stringArray[i];
		}
		if(!(new File(ibatisGatherFilePath)).exists()){
			  (new File(ibatisGatherFilePath)).mkdirs();
			  System.out.println("创建ibatis总配置文件的文件夹目录");
		 }
		try {
			String fileName = buildIbatisConfigNode.getChildNode("gatherIbatisConfigFile").getAttributeValue("fileName");
			
			FileWriter fw = new FileWriter(ibatisGatherFilePath + "/"+fileName+".xml");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addFileBeginMessage());
			String ibatisFilePath = buildIbatisConfigNode.getChildNode("ibatisConfigFile").getAttributeValue("filePath");
			out.println(this.addIbatisFile(ibatisFilePath,sourceTablesNode));
	
			out.println(this.addFileEndMessage()); 
			out.close();
			fw.close();
			System.out.println("创建ibatis总配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	private String addFileBeginMessage(){
		String tempStr="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		tempStr +="<!DOCTYPE sqlMapConfig " +
				"\n\tPUBLIC \"-//ibatis.apache.org//DTD SQL Map Config 2.0//EN\" " +
				"\n\t\"http://ibatis.apache.org/dtd/sql-map-config-2.dtd\">";
		
		tempStr += "\n<sqlMapConfig>";
		tempStr +="\n\t<settings useStatementNamespaces=\"true\"/>";
		
		return tempStr;
	}
	
	private String addIbatisFile(String ibatisFilePath,XMLNode[] sourceTablesNode){
		String[] stringArray = ibatisFilePath.split("\\.");
		String ibatisConfigFilePath="";
		for(int i=0;i<stringArray.length;i++){
			if(i==0){
				ibatisConfigFilePath +=stringArray[i];
			}else{
				ibatisConfigFilePath +="/"+stringArray[i];
			}
		}

		//添加需要的ibatis配置文件
		String tempStr="";
		for(int i=0;i<sourceTablesNode.length;i++){
			XMLNode tableNode = sourceTablesNode[i];
			String tableName=tableNode.getAttributeValue("tableName");
			//tableName转换为beanName 格式为驼峰式
			String beanType=this.getBeanType(tableName);
			tempStr +="\n\t<sqlMap resource=\""+ibatisConfigFilePath+"/"+beanType+".xml\"/>";
		}
		return tempStr;
	}
	
	
	
	private String addFileEndMessage(){
		
		return "\n</sqlMapConfig>";
	}
	
	
	
	//获取beanType
	private String getBeanType(String tableName){
		String[] stringArray = tableName.split("_");
		String beanType="";
		for(int j=0;j<stringArray.length;j++){
			beanType+=stringArray[j].substring(0, 1).toUpperCase()+stringArray[j].substring(1).toLowerCase();
		}
		System.out.println("beanType:"+beanType);
		return beanType;
	}

}
