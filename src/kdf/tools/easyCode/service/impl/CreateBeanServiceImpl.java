package kdf.tools.easyCode.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import kdf.tools.easyCode.service.CreateBeanService;
import kdf.tools.xml.XMLNode;
import kdf.tools.xml.XMLNodeHandler;

public class CreateBeanServiceImpl implements CreateBeanService{

	public void createBeanServiceMethod(String beanType, XMLNode beanServiceNode) {
		String packagePath=beanServiceNode.getChildNode("filePath").getAttributeValue("implClassPath");
		String[] stringArray = packagePath.split("\\.");
		//生成接口
		String interfacePath="src";
		for(int i=0;i<stringArray.length-1;i++){
			interfacePath=interfacePath+"/"+stringArray[i];
		}
		System.out.println("路径"+interfacePath);
		
		this.createInterface(interfacePath,beanType, beanServiceNode);
		
		//生成实现类
		String classPath="src";
		for(int i=0;i<stringArray.length;i++){
			classPath=classPath+"/"+stringArray[i];
		}

		this.createClass(classPath, beanType, beanServiceNode);
		
	
	}
	/*************************生成接口**************************************************/
	
	private void createInterface(String interfacePath,String beanType, XMLNode beanServiceNode){
		if(!(new File(interfacePath)).exists()){
			  (new File(interfacePath)).mkdirs();
			  System.out.println("创建service文件夹目录");
		 }
		try {
			FileWriter fw = new FileWriter(interfacePath + "/"+beanType+"Service.java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.interfaceAddBeginMessage(beanServiceNode,beanType));
			
			XMLNodeHandler xmlNodeHandler = (XMLNodeHandler) (beanServiceNode.getChildNode("serviceMethods"));
			
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
			System.out.println("生成javaService文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private String interfaceAddBeginMessage(XMLNode node,String beanType){
		String tempStr = "";
		String implClassPackage = node.getChildNode("filePath").getAttributeValue("implClassPath");
		String ServicePackage="";
		String[] stringArray = implClassPackage.split("\\.");
		for(int i=0;i<stringArray.length-1;i++){
			if("".equals(ServicePackage)){
				ServicePackage+=stringArray[i];
			}else{
				ServicePackage+="."+stringArray[i];
			}
		}
		tempStr = "package "+ServicePackage+";\n";
		tempStr += "\nimport java.util.List;\n";
		tempStr += "import java.util.Map;\n";
		tempStr += "import "+node.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType+";\n";
		
		tempStr	+="\n";
		tempStr +="public interface "+beanType+"Service {\n";

		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	private String interfaceAddProperty(String propertyType,String propertyName){
		return "";
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

	
	private String interfaceAddGetSetMethod(String propertyType,String propertyName){
		
		return "";
	}
	
	private String interfaceAddEndMessage(){
		
		return "\n}";
	}
	
	/*************************生成实现类**************************************************/
	private void createClass(String classPath,String beanType, XMLNode beanServiceImplNode){
		String beanName=beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
		if(!(new File(classPath)).exists()){
		   (new File(classPath)).mkdirs();
		   System.out.println("创建serviceImp文件夹目录************************************************************");
		}
		try {
			FileWriter fw = new FileWriter(classPath + "/"+beanType+"ServiceImpl.java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.classAddBeginMessage(beanServiceImplNode,beanType));
			out.println(this.classAddProperty(beanType+"DAO", beanName+"DAO"));
			
			XMLNodeHandler xmlNodeHandler = (XMLNodeHandler) (beanServiceImplNode.getChildNode("serviceMethods"));
			XMLNode[] nodeList = xmlNodeHandler.selectNodes("./method");
			System.out.print("nodeList的length："+nodeList.length);
			if(nodeList.length>=1){
				for(int i=0;i<nodeList.length;i++){
					out.println(this.classAddExecuteMethod(nodeList[i],beanType));		
				}
			}
			
			out.println(this.classAddGetSetMethod(beanType+"DAO", beanName+"DAO"));
			out.println(this.classAddEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成javaServiceImpl文件");
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
		String ServicePackage="";
		String[] stringArray = implClassPackage.split("\\.");
		for(int i=0;i<stringArray.length-1;i++){
			if("".equals(ServicePackage)){
				ServicePackage+=stringArray[i];
			}else{
				ServicePackage+="."+stringArray[i];
			}
		}
		tempStr = "package "+implClassPackage+";\n";
		
		tempStr += "\nimport java.util.List;\n";
		tempStr += "import java.util.Map;\n\n";
		if(!"".equalsIgnoreCase(extendsClass)){
			tempStr += "import "+extendsClass+";\n";
		}
		
		XMLNode[] daoNodeList = node.getChildNode("relateInterfaces").selectNodes("./relateInterface");
		for(int i=0;i<daoNodeList.length;i++){
			tempStr += "\nimport "+daoNodeList[i].getAttributeValue("value")+"."+beanType+"DAO;";
		}

		tempStr += "\nimport "+ServicePackage+"."+beanType+"Service;\n";
		tempStr += "import "+node.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType+";\n";
		tempStr	+="\n";
		tempStr +="public class "+beanType+"ServiceImpl ";
		if(!"".equalsIgnoreCase(extendsClassType)){
			tempStr +="extends "+extendsClassType;
		}
		
		tempStr +=" implements "+beanType+"Service";
		tempStr +=" {";
		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	private String classAddProperty(String propertyType,String propertyName){
		return "\tprivate "+propertyType+" "+propertyName+";\n";
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
			executeMethodStr+="\t\treturn "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if(beanType.equalsIgnoreCase(returnStr)){
			executeMethodStr+="\t\treturn "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if("int".equalsIgnoreCase(returnStr)){
			executeMethodStr+="\t\treturn "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
			executeMethodStr+=this.getReturnMessage(nodeList,beanName);
		}else if("void".equalsIgnoreCase(returnStr)){
			if("remove".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\t "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
				executeMethodStr+=this.getReturnMessage(nodeList,beanName);
				
			}else if("save".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\t "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
				executeMethodStr+=this.getReturnMessage(nodeList,beanName);
			}else if("modify".equalsIgnoreCase(methodNode.getAttributeValue("methodNamePrefix"))){
				executeMethodStr+="\t\t "+beanName+"DAO."+this.serviceConvertDAO(methodNode.getAttributeValue("methodNamePrefix"))
						+beanType+ methodNode.getAttributeValue("methodNamePostfix")+"(";
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
	
	private String serviceConvertDAO(String serviceMethodStr){
		String tempStr="";
		if("get".equals(serviceMethodStr)){
			tempStr="query";	
		}else if("getCount".equals(serviceMethodStr)){
			tempStr="queryCount";
		}else if("remove".equals(serviceMethodStr)){
			tempStr="execDelete";
		}else if("save".equals(serviceMethodStr)){
			tempStr="execInsert";
		}else if("modify".equals(serviceMethodStr)){
			tempStr="execUpdate";
		}
		return tempStr;
	}
	
	
	
	private String classAddGetSetMethod(String propertyType,String propertyName){
		String setMethodStr="\n\tpublic void set"+propertyType+"("+propertyType+" "+propertyName+"){";
		setMethodStr+="\n\t\tthis."+propertyName+"="+propertyName+";";
		setMethodStr+="\n\t}";
		return setMethodStr;
	}
	
	private String classAddEndMessage(){
		
		return "\n}";
	}
	
	

}
