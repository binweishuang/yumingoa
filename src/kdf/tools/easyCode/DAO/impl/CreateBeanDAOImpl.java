package kdf.tools.easyCode.DAO.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import kdf.tools.easyCode.DAO.CreateBeanDAO;
import kdf.tools.xml.XMLNode;
import kdf.tools.xml.XMLNodeHandler;

public class CreateBeanDAOImpl implements CreateBeanDAO{

	public void createBeanDAOMethod(String beanType, XMLNode beanDAONode) {
		String packagePath=beanDAONode.getChildNode("filePath").getAttributeValue("implClassPath");
		String[] stringArray = packagePath.split("\\.");
		//生成接口
		String interfacePath="src";
		for(int i=0;i<stringArray.length-1;i++){
			interfacePath=interfacePath+"/"+stringArray[i];
		}
		System.out.println("路径"+interfacePath);
		
		this.createInterface(interfacePath,beanType, beanDAONode);
		
		//生成实现类
		String classPath="src";
		for(int i=0;i<stringArray.length;i++){
			classPath=classPath+"/"+stringArray[i];
		}

		this.createClass(classPath, beanType, beanDAONode);

	}
	
	
	/*************************生成接口**************************************************/
	
	private void createInterface(String interfacePath,String beanType, XMLNode beanDAONode){
		if(!(new File(interfacePath)).exists()){
			  (new File(interfacePath)).mkdirs();
			  System.out.println("创建DAO文件夹目录");
		 }
		try {
			FileWriter fw = new FileWriter(interfacePath + "/"+beanType+"DAO.java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.interfaceAddBeginMessage(beanDAONode,beanType));
			
			XMLNodeHandler xmlNodeHandler = (XMLNodeHandler) (beanDAONode.getChildNode("daoMethods"));
			XMLNode[] nodeList = xmlNodeHandler.selectNodes("./method");
			System.out.println("nodeList的length："+nodeList.length);
			if(nodeList.length>=1){
				for(int i=0;i<nodeList.length;i++){
					out.println(this.interfaceAddExecuteMethod(nodeList[i],beanType));		
				}
			}
			
			out.println(this.interfaceAddEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成javaDAO文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String interfaceAddBeginMessage(XMLNode node,String beanType){
		String tempStr = "";
		String implClassPackage = node.getChildNode("filePath").getAttributeValue("implClassPath");
		String DAOPackage="";
		String[] stringArray = implClassPackage.split("\\.");
		for(int i=0;i<stringArray.length-1;i++){
			if("".equals(DAOPackage)){
				DAOPackage+=stringArray[i];
			}else{
				DAOPackage+="."+stringArray[i];
			}
		}
		tempStr = "package "+DAOPackage+";\n";
		tempStr += "import java.util.List;\n";
		tempStr += "import java.util.Map;\n";
		tempStr += "import "+node.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType+";\n";
		tempStr	+="\n";
		tempStr +="public interface "+beanType+"DAO {\n";
		
		
		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	private String interfaceAddExecuteMethod(XMLNode methodNode,String beanType){
		String beanName=beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
		
		String executeMethodStr = "";
		String returnStr="beanType".equals(methodNode.getAttributeValue("methodReturn"))?beanType:methodNode.getAttributeValue("methodReturn");

		executeMethodStr += "\tpublic "+returnStr+" "+methodNode.getAttributeValue("methodNamePrefix")
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
		XMLNode[] nodeList = methodNode.getChildNode("params").selectNodes("./param");
		for(int i=0;i<nodeList.length;i++){
			String paramType="beanType".equals(nodeList[i].getAttributeValue("paramType"))?beanType:nodeList[i].getAttributeValue("paramType");
			String paramName ="beanName".equals(nodeList[i].getAttributeValue("paramName"))?beanName:nodeList[i].getAttributeValue("paramName");
			if(i<nodeList.length-1){
				executeMethodStr+=""+paramType+" "+paramName+",";
			}else{
				executeMethodStr+=""+paramType+" "+paramName+"";
			}
		}
		executeMethodStr+=")throws Exception;\n";
		
		System.out.println("方法名称："+executeMethodStr);
		return executeMethodStr;
	}
	private String interfaceAddEndMessage(){
		
		return "\n}";
	}
	
	private String interfaceAddProperty(String propertyType,String propertyName){
		return "";
	}
	
	private String interfaceAddGetSetMethod(String propertyType,String propertyName){
		
		return "";
	}

	/*************************生成实现类**************************************************/
	private void createClass(String classPath,String beanType, XMLNode beanDAOImplNode){
		System.out.println("路径"+classPath);
		if(!(new File(classPath)).exists()){
		   (new File(classPath)).mkdirs();
		   System.out.println("创建DAOImp文件夹目录");
		}
		try {
			FileWriter fw = new FileWriter(classPath + "/"+beanType+"DAOImpl.java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.classAddBeginMessage(beanDAOImplNode,beanType));
			
			XMLNodeHandler xmlNodeHandler = (XMLNodeHandler) (beanDAOImplNode.getChildNode("daoMethods"));
			XMLNode[] nodeList = xmlNodeHandler.selectNodes("./method");
			System.out.print("nodeList的length："+nodeList.length);
			if(nodeList.length>=1){
				for(int i=0;i<nodeList.length;i++){
					out.println(this.classAddExecuteMethod(nodeList[i],beanType));		
				}
			}
			
			out.println(this.classAddEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成javaDAOImpl文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String classAddBeginMessage(XMLNode node,String beanType){
		String tempStr = "";
		String extendsClass =node.getChildNode("extendsClass").getAttributeValue("className");
		
		String[] extendsClassArray = extendsClass.split("\\.");
		String extendsClassType = extendsClassArray[extendsClassArray.length-1];
		
		String implClassPackage = node.getChildNode("filePath").getAttributeValue("implClassPath");
		String DAOPackage="";
		String[] stringArray = implClassPackage.split("\\.");
		for(int i=0;i<stringArray.length-1;i++){
			if("".equals(DAOPackage)){
				DAOPackage+=stringArray[i];
			}else{
				DAOPackage+="."+stringArray[i];
			}
		}
		tempStr = "package "+implClassPackage+";\n";
		
		tempStr += "import java.util.List;\n";
		tempStr += "import java.util.Map;\n\n";
		tempStr += "import "+extendsClass+";\n";
		tempStr += "import "+DAOPackage+"."+beanType+"DAO;\n";
		tempStr += "import "+node.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType+";\n";
		tempStr	+="\n";
		tempStr +="public class "+beanType+"DAOImpl ";
		tempStr +="extends "+extendsClassType;
		tempStr +=" implements "+beanType+"DAO";
		tempStr +=" {";
		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	
	private String classAddExecuteMethod(XMLNode methodNode,String beanType){
		String beanName=beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
		
		String executeMethodStr = "";
		String returnStr="beanType".equals(methodNode.getAttributeValue("methodReturn"))?beanType:methodNode.getAttributeValue("methodReturn");

		executeMethodStr += "\n\tpublic "+returnStr+" "+methodNode.getAttributeValue("methodNamePrefix")
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
		XMLNode[] nodeList = methodNode.getChildNode("params").selectNodes("./param");
		for(int i=0;i<nodeList.length;i++){
			String paramType="beanType".equals(nodeList[i].getAttributeValue("paramType"))?beanType:nodeList[i].getAttributeValue("paramType");
			String paramName ="beanName".equals(nodeList[i].getAttributeValue("paramName"))?beanName:nodeList[i].getAttributeValue("paramName");
			if(i<nodeList.length-1){
				executeMethodStr+=""+paramType+" "+paramName+",";
			}else{
				executeMethodStr+=""+paramType+" "+paramName;
			}
		}
		executeMethodStr+=")throws Exception {\n";
		//判断返回类型
		if("List".equalsIgnoreCase(returnStr)){
			executeMethodStr+="\t\treturn getSqlMapClientTemplate().queryForList(\""+beanType+"."+
					methodNode.getAttributeValue("methodNamePrefix")
					+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if(beanType.equalsIgnoreCase(returnStr)){
			executeMethodStr+="\t\treturn ("+beanType+")getSqlMapClientTemplate().queryForObject(\""+beanType+"."+
					methodNode.getAttributeValue("methodNamePrefix")
					+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if("int".equalsIgnoreCase(returnStr)){
			executeMethodStr+="\t\treturn (Integer)getSqlMapClientTemplate().queryForObject(\""+beanType+"."+
					methodNode.getAttributeValue("methodNamePrefix")
					+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if("void".equalsIgnoreCase(returnStr)){
			if("execDelete".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\tgetSqlMapClientTemplate().delete(\""+beanType+"."+
							methodNode.getAttributeValue("methodNamePrefix")
							+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
				executeMethodStr+=this.getReturnMessage(nodeList,beanName);
				
			}else if("execInsert".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\tgetSqlMapClientTemplate().insert(\""+beanType+"."+
						methodNode.getAttributeValue("methodNamePrefix")
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
				executeMethodStr+=this.getReturnMessage(nodeList,beanName);
			}else if("execUpdate".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\tgetSqlMapClientTemplate().update(\""+beanType+"."+
						methodNode.getAttributeValue("methodNamePrefix")
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"\",";
				executeMethodStr+=this.getReturnMessage(nodeList,beanName);
			}
		}
		
		executeMethodStr+=");\n\t}\n";
	
		System.out.println("方法名称："+executeMethodStr);
		return executeMethodStr;
	}
	
	
	private String getReturnMessage(XMLNode[] nodeList,String beanName){
		String returnMsg="";
		
		for(int j=0;j<nodeList.length;j++){
			String paramName ="beanName".equals(nodeList[j].getAttributeValue("paramName"))?beanName:nodeList[j].getAttributeValue("paramName");
			if(j<nodeList.length-1){
				returnMsg+=paramName+",";
			}else{
				returnMsg+=paramName;
			}
		}
		return returnMsg;
	}
	
	
	
	private String classAddProperty(String propertyType,String propertyName){
		return "";
	}
	
	private String classAddGetSetMethod(String propertyType,String propertyName){
		
		return "";
	}
	
	
	private String classAddEndMessage(){
		
		return "\n}";
	}

}
