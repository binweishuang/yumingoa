package kdf.tools.easyCode.config.struts.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import kdf.tools.easyCode.config.struts.SpringActionConfig;
import kdf.tools.xml.XMLNode;

public class SpringActionConfigImpl implements SpringActionConfig {

	public void createSpringAction(XMLNode springActionNode,XMLNode buildStrutsConfigNode) {
		String actionFilePath=springActionNode.getAttributeValue("filePath");
		String[] stringArray = actionFilePath.split("\\.");
		//生成接口
		String springActionFilePath="src";
		for(int i=0;i<stringArray.length;i++){
			springActionFilePath +="/"+stringArray[i];
		}
		if(!(new File(springActionFilePath)).exists()){
			  (new File(springActionFilePath)).mkdirs();
			  System.out.println("创建spring-action配置文件的文件夹目录");
		 }
		try {
			String fileName = springActionNode.getAttributeValue("fileName");
			
			FileWriter fw = new FileWriter(springActionFilePath + "/"+fileName+".xml");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addActionBeginMsg());
			String implActionFilePath =springActionNode.getChildNode("implActionPath").getAttributeValue("filePath");
			
			out.println(this.addActionContentMsg(implActionFilePath,buildStrutsConfigNode));
	
			out.println(this.addActionEndMsg()); 
			out.close();
			fw.close();
			System.out.println("创建spring-action配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String addActionBeginMsg(){
		String tempStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		tempStr +="<beans xmlns =\"http://www.springframework.org/schema/beans\"  " +
				"\n\txmlns:xsi =\"http://www.w3.org/2001/XMLSchema-instance\"  " +
				"\n\txsi:schemaLocation =\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\">";
		
		
		return tempStr;
	}
	
	private String addActionContentMsg(String implActionFilePath,XMLNode buildStrutsConfigNode){
		String tempStr="";
		XMLNode[] struts2ConfigNodeList = buildStrutsConfigNode.getChildNode("struts2Configs").selectNodes("./struts2Config");
		for(int i=0;i<struts2ConfigNodeList.length;i++){
			XMLNode tableNode = struts2ConfigNodeList[i].getChildNode("table");
			String tableName=tableNode.getAttributeValue("tableName");
			//tableName转换为beanName 格式为驼峰式
			String beanType=this.getBeanType(tableName);
			String beanName = this.getBeanName(beanType);
			tempStr +="\n\t<bean id =\""+beanName+"Action\" class=\""+implActionFilePath+"."+beanType+"Action\" autowire=\"byName\" scope=\"prototype\">" +
							"\n\t\t<property name=\""+beanName+"Service\" ref=\""+beanName+"Service\"/>" +
					"\n\t</bean>\n\n";
		}
		return tempStr;
	}
	
	private String addActionEndMsg(){
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
