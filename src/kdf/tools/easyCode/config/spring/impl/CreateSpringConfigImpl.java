package kdf.tools.easyCode.config.spring.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import kdf.tools.easyCode.config.spring.CreateSpringConfig;
import kdf.tools.xml.XMLNode;

public class CreateSpringConfigImpl implements CreateSpringConfig {
	
	/*************************生成spring dao 配置文件**************************************************************/
	public void createSpringDaoConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes) {
		String daoFilePath=buildSpringConfigNode.getChildNode("springDao").getAttributeValue("filePath");
		String[] stringArray = daoFilePath.split("\\.");
		//生成接口
		String springDaoFilePath="src";
		for(int i=0;i<stringArray.length;i++){
			springDaoFilePath +="/"+stringArray[i];
		}
		if(!(new File(springDaoFilePath)).exists()){
			  (new File(springDaoFilePath)).mkdirs();
			  System.out.println("创建spring-dao配置文件的文件夹目录");
		 }
		try {
			String fileName = buildSpringConfigNode.getChildNode("springDao").getAttributeValue("fileName");
			
			FileWriter fw = new FileWriter(springDaoFilePath + "/"+fileName+".xml");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addDaoBeginMsg());
			String implDaoFilePath = buildSpringConfigNode.getChildNode("springDao").getChildNode("implDaoPath").getAttributeValue("filePath");
			
			out.println(this.addDaoContentMsg(implDaoFilePath,tableNodes));
	
			out.println(this.addDaoEndMsg()); 
			out.close();
			fw.close();
			System.out.println("创建spring-dao配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String addDaoBeginMsg(){
		String tempStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		tempStr +="<beans xmlns =\"http://www.springframework.org/schema/beans\"  " +
				"\n\txmlns:xsi =\"http://www.w3.org/2001/XMLSchema-instance\"  " +
				"\n\txsi:schemaLocation =\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\">";
		
		
		return tempStr;
	}
	
	private String addDaoContentMsg(String implDaoFilePath,XMLNode[] tableNodes){
		
		String tempStr="";
		for(int i=0;i<tableNodes.length;i++){
			XMLNode tableNode = tableNodes[i];
			String tableName=tableNode.getAttributeValue("tableName");
			//tableName转换为beanName 格式为驼峰式
			String beanType=this.getBeanType(tableName);
			String beanName = this.getBeanName(beanType);
			tempStr +="\n\t<bean id=\""+beanName+"DAO\" class=\""+implDaoFilePath+"."+beanType+"DAOImpl\" parent=\"baseDAO\"/>\n";
			
		}
		return tempStr;
	}
	
	private String addDaoEndMsg(){
		return "\n</beans>";
	}
	
	
	/**************************生成spring service 配置文件*****************************************************************************/
	public void createSpringServiceConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes) {
		String serviceFilePath=buildSpringConfigNode.getChildNode("springService").getAttributeValue("filePath");
		String[] stringArray = serviceFilePath.split("\\.");
		//生成接口
		String springServiceFilePath="src";
		for(int i=0;i<stringArray.length;i++){
			springServiceFilePath +="/"+stringArray[i];
		}
		if(!(new File(springServiceFilePath)).exists()){
			  (new File(springServiceFilePath)).mkdirs();
			  System.out.println("创建spring-service配置文件的文件夹目录");
		 }
		try {
			String fileName = buildSpringConfigNode.getChildNode("springService").getAttributeValue("fileName");
			
			FileWriter fw = new FileWriter(springServiceFilePath + "/"+fileName+".xml");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addServiceBeginMsg());
			String implServiceFilePath = buildSpringConfigNode.getChildNode("springService").getChildNode("implServicePath").getAttributeValue("filePath");
			
			out.println(this.addServiceContentMsg(implServiceFilePath,tableNodes));
	
			out.println(this.addServiceEndMsg()); 
			out.close();
			fw.close();
			System.out.println("创建spring-service配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String addServiceBeginMsg(){
		String tempStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		tempStr +="<beans xmlns =\"http://www.springframework.org/schema/beans\"  " +
				"\n\txmlns:xsi =\"http://www.w3.org/2001/XMLSchema-instance\"  " +
				"\n\txsi:schemaLocation =\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\">";
		
		
		return tempStr;
	}
	
	private String addServiceContentMsg(String implServiceFilePath,XMLNode[] tableNodes){
		String tempStr="";
		for(int i=0;i<tableNodes.length;i++){
			XMLNode tableNode = tableNodes[i];
			String tableName=tableNode.getAttributeValue("tableName");
			//tableName转换为beanName 格式为驼峰式
			String beanType=this.getBeanType(tableName);
			String beanName = this.getBeanName(beanType);
			
			tempStr +="\n\t<bean id=\""+beanName+"Service\" parent=\"txProxyTemplate\">" +
					  "\n\t<property name=\"target\">" +
					  	  "\n\t\t<bean class=\""+implServiceFilePath+"."+beanType+"ServiceImpl\" autowire=\"byName\">" +
					  		 "\n\t\t\t<property name=\""+beanName+"DAO\" ref=\""+beanName+"DAO\"/>" +
					  	  "\n\t\t</bean>" +
					  "\n\t</property>" +
					  "\n\t</bean>\n\n";
		}
		return tempStr;
	}
	
	private String addServiceEndMsg(){
		return "\n</beans>";
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
	
	//获取beanName
	private String getBeanName(String beanType){
		return beanType.substring(0, 1).toLowerCase()+beanType.substring(1);
	}

}
