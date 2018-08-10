package kdf.tools.easyCode.config.struts.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import kdf.tools.easyCode.config.struts.StrutsConfig;
import kdf.tools.xml.XMLNode;

public class StrutsConfigImpl implements StrutsConfig{

	public void createStrutsConfig(XMLNode gatherStrutsConfigNode, XMLNode buildStrutsConfigNode) {
		String configFilePath=gatherStrutsConfigNode.getAttributeValue("filePath");
		String[] stringArray = configFilePath.split("\\.");
		//生成接口
		String strutsConfigFilePath="src";
		for(int i=0;i<stringArray.length;i++){
			strutsConfigFilePath +="/"+stringArray[i];
		}
		if(!(new File(strutsConfigFilePath)).exists()){
			  (new File(strutsConfigFilePath)).mkdirs();
			  System.out.println("创建struts-action配置文件的文件夹目录");
		 }
		try {
			String fileName = gatherStrutsConfigNode.getAttributeValue("fileName");
			
			FileWriter fw = new FileWriter(strutsConfigFilePath + "/"+fileName+".xml");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addStrutsConfigBeginMsg());
			
			out.println(this.addStrutsConfigContentMsg( buildStrutsConfigNode));
	
			out.println(this.addStrutsConfigEndMsg()); 
			out.close();
			fw.close();
			System.out.println(strutsConfigFilePath + "/"+fileName+".xml");
			System.out.println("创建struts-action配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String addStrutsConfigBeginMsg(){
		String tempStr = "<!DOCTYPE struts PUBLIC " +
				"\"-//Apache Software Foundation//DTD Struts Configuration 2.0//EN\"  " +
				"\"http://struts.apache.org/dtds/struts-2.0.dtd\">";
		tempStr +="<struts>";
		
		
		return tempStr;
	}
	
	private String addStrutsConfigContentMsg(XMLNode buildStrutsConfigNode){
		String tempStr="";
		XMLNode[] struts2ConfigNodeList = buildStrutsConfigNode.getChildNode("struts2Configs").selectNodes("./struts2Config");
		for(int i=0;i<struts2ConfigNodeList.length;i++){
			XMLNode tableNode = struts2ConfigNodeList[i].getChildNode("table");
			String tableName=tableNode.getAttributeValue("tableName");
			//tableName转换为beanName 格式为驼峰式
			String beanType=this.getBeanType(tableName);
			String beanName = this.getBeanName(beanType);
			XMLNode pageListNode = struts2ConfigNodeList[i].getChildNode("pageList");
			
			String listPagePath = "/"+pageListNode.getAttributeValue("filePath")+"/"+beanName+"List.jsp";
			String editPagePath = "/"+pageListNode.getAttributeValue("filePath")+"/"+beanName+"Edit.jsp";
			
			
			tempStr +="\n\t<package name=\""+beanName+"\" extends=\"base\" namespace=\"/"+beanName+"\">";
			tempStr +="\n\t\t<action name=\"query\" class=\""+beanName+"Action\" method=\"doQuery\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result>"+listPagePath+"</result>";
			tempStr +="\n\t\t</action>";
			tempStr +="\n\t\t<action name=\"insertInit\" class=\""+beanName+"Action\" method=\"doInsertInit\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result>"+editPagePath+"</result>";
			tempStr +="\n\t\t</action>";
			tempStr +="\n\t\t<action name=\"insert\" class=\""+beanName+"Action\" method=\"doInsert\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result type=\"redirect\">query.action</result>";
			tempStr +="\n\t\t</action>";
			tempStr +="\n\t\t<action name=\"updateInit\" class=\""+beanName+"Action\" method=\"doUpdateInit\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result>"+editPagePath+"</result>";
			tempStr +="\n\t\t</action>";
			tempStr +="\n\t\t<action name=\"update\" class=\""+beanName+"Action\" method=\"doUpdate\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result type=\"redirect\">query.action</result>";
			tempStr +="\n\t\t</action>";
			tempStr +="\n\t\t<action name=\"delete\" class=\""+beanName+"Action\" method=\"doDelete\">";
			tempStr +="\n\t\t\t<interceptor-ref name=\"myStack\"/>";
			tempStr +="\n\t\t\t<result type=\"redirect\">query.action</result>";
			tempStr +="\n\t\t</action>";
			
			tempStr +="\n\t</package>";
			tempStr +="\n\n";
		}
		return tempStr;
	}
	
	private String addStrutsConfigEndMsg(){
		return "\n</struts>";
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
