package kdf.tools.easyCode.bean.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import kdf.tools.easyCode.bean.CreateBean;
import kdf.tools.xml.XMLNode;

public class CreateBeanImpl implements CreateBean{

	public void createBeanMethod(String tableName,XMLNode node,Connection conn) {
		String packagePath=node.getChildNode("filePath").getAttributeValue("path");
		String[] stringArray = packagePath.split("\\.");
		
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
			FileWriter fw = new FileWriter(path + "/"+beanType+".java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addBeginMessage(node,beanType));
			ResultSet rs=this.getResultSet(conn,tableName);
			rs.next();
			ResultSetMetaData rsmd  = rs.getMetaData();
			System.out.println("columnCount:"+rsmd.getColumnCount());
			for(int n=1;n<=rsmd.getColumnCount();n++){
				String columnType=rsmd.getColumnTypeName(n);
				String columnName=rsmd.getColumnName(n);
				System.out.println("columnName:"+columnName);
				columnType = this.oracleToJavaType(columnType);
				columnName = this.oracleToJavaField(columnName);
				out.println(this.addProperty(columnType,columnName));
			}
			out.println();
			out.println(this.addToMapMethod());
			for(int m=1;m<=rsmd.getColumnCount();m++){
				String columnType=rsmd.getColumnTypeName(m);
				System.out.println("columnType:"+columnType);
				
				String columnName=rsmd.getColumnName(m);
				columnType = this.oracleToJavaType(columnType);
				columnName = this.oracleToJavaField(columnName);
				out.println(this.addGetSetMethod(columnType,columnName));
			}
			
			out.println(this.addEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成javabean文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	private String addBeginMessage(XMLNode node,String beanType){
		String tempStr = "";
		tempStr = "package "+node.getChildNode("filePath").getAttributeValue("path")+";\n";
		String extendsClassName = node.getChildNode("extendsClass").getAttributeValue("className");
		String[] extendsClassNameArray = extendsClassName.split("\\.");
		if(!"".equals(extendsClassName)){
			tempStr += "\nimport "+extendsClassName+";";
		}
		tempStr += "\nimport java.util.Map;\n";
		tempStr += "\nimport java.util.Date;\n";
		XMLNode[] implementInterfaceNodes =node.selectNodes("/easyCodeConfig/totalConfigs/singleConfig/beanConfig/implementInterfaces/interfaceName");
		if(implementInterfaceNodes.length>=1){
			for(int i=0;i<implementInterfaceNodes.length;i++){
				tempStr+="import "+implementInterfaceNodes[i].getAttributeValue("name")+";\n";
			}
		}
		String extendsClassStr="".equals(extendsClassName)?"":"extends "+extendsClassNameArray[extendsClassNameArray.length-1]+" ";
		String implementInterfaceStr="";
			if(implementInterfaceNodes.length>=1){
				implementInterfaceStr+="implements ";
				for(int j=0;j<implementInterfaceNodes.length;j++){
					String[] tempImplementArray= implementInterfaceNodes[j].getAttributeValue("name").split("\\.");
					implementInterfaceStr+=tempImplementArray[tempImplementArray.length-1]+" ,";
				}
				implementInterfaceStr=implementInterfaceStr.substring(0, implementInterfaceStr.length()-1);
			}
		tempStr	+="\n";
		tempStr +="public class "+beanType+" "+extendsClassStr+" "+implementInterfaceStr+"{";
		
		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	
	
	private String addProperty(String columnType,String columnName){
		return "\tprivate "+columnType+" "+columnName+";";
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
	/**
	 * public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}
	 * 
	 * 
	 * 
	 * **/
	private String addToMapMethod(){
		return "\n\tpublic Map toMap(){\n"+
				"\t\treturn null;\n\t}";
		
	}
	
	private String addGetSetMethod(String columnType,String columnName){
		String javaPopertyName = columnName.substring(0, 1).toUpperCase()+columnName.substring(1);
		String tempSetMethodStr = "\tpublic void set"+javaPopertyName+"("+columnType+" "
								+columnName+"){\n"+"\t\tthis."+columnName+"="+columnName+";\n\t}";
		String tempGetMethodStr =  "\n\tpublic "+columnType+" get"+javaPopertyName+"(){\n"
								+"\t\treturn "+columnName+";\n\t}";
		
		return tempSetMethodStr+tempGetMethodStr;
	}
	
	private String addEndMessage(){
		
		return "}";
	}

}
