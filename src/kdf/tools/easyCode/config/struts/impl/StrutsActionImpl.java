package kdf.tools.easyCode.config.struts.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.easyCode.config.struts.StrutsAction;
import kdf.tools.easyCode.config.struts.beanTableMsg.BeanTableMsg;
import kdf.tools.xml.XMLNode;

public class StrutsActionImpl implements StrutsAction{
	public void factoryStrutsAction(XMLNode actionServiceNode,List listBeanTableMsg) {
		
		for(int i=0;i<listBeanTableMsg.size();i++){
			BeanTableMsg beanTableMsg = (BeanTableMsg) listBeanTableMsg.get(i);
			this.createBeanActionMethod(beanTableMsg, actionServiceNode);
		}
		
		
		
	}
	
	public void createBeanActionMethod(BeanTableMsg beanTableMsg, XMLNode actionServiceNode) {
		String packagePath=actionServiceNode.getChildNode("filePath").getAttributeValue("classPath");
		String beanType = beanTableMsg.getBeanType();
		String beanName=beanTableMsg.getBeanName();
		String[] stringArray = packagePath.split("\\.");
		//生成接口
		String classPath="src";
		for(int i=0;i<stringArray.length;i++){
			classPath=classPath+"/"+stringArray[i];
		}
		System.out.println("路径"+classPath);
		if(!(new File(classPath)).exists()){
			  (new File(classPath)).mkdirs();
			  System.out.println("创建Action文件夹目录");
		 }
		try {
			FileWriter fw = new FileWriter(classPath + "/"+beanType+"Action.java");
			PrintWriter out = new PrintWriter(fw);   
			out.println(this.addBeginMessage(actionServiceNode,beanType));
			XMLNode[] propertyNodeList = actionServiceNode.getChildNode("propertysList").selectNodes("./property");
			for(int j=0;j<propertyNodeList.length;j++){
				String propertyName="";
				if("beanName".equals(propertyNodeList[j].getAttributeValue("flag"))){
					propertyName=beanName+propertyNodeList[j].getAttributeValue("propertyName");
				}else{
					propertyName=propertyNodeList[j].getAttributeValue("propertyName");
				}
				String propertyType=propertyNodeList[j].getAttributeValue("propertyType");
				String propertyValue=propertyNodeList[j].getAttributeValue("value");
				out.println(this.addProperty(propertyType, propertyName,propertyValue));
			}
			List objPropertyList = beanTableMsg.getPropertyList();
			List objPropertyTypeList = beanTableMsg.getPropertyTypeList();
			String tempKey = objPropertyList.get(0).toString();
			String tempKeyType = objPropertyTypeList.get(0).toString();
			out.println(this.addProperty(tempKeyType, tempKey+"_q",""));
			for(int j=0;j<objPropertyList.size();j++){
				String temp = objPropertyList.get(j).toString();
				String tempType = objPropertyTypeList.get(j).toString();
				out.println(this.addProperty(tempType, temp,""));
				
			}
			
			
			
			out.println(this.addProperty(beanType+"Service", beanName+"Service", ""));
			
			XMLNode[] nodeList = actionServiceNode.getChildNode("actionMethods").selectNodes("./method");
			System.out.println("nodeList的length："+nodeList.length);
			if(nodeList.length>=1){
				for(int i=0;i<nodeList.length;i++){
					out.println(this.addExecuteMethod(nodeList[i],beanTableMsg));		
				}
			}
			
			for(int m=0;m<propertyNodeList.length;m++){
				String propertyName="";
				if("beanName".equals(propertyNodeList[m].getAttributeValue("flag"))){
					propertyName=beanName+propertyNodeList[m].getAttributeValue("propertyName");
				}else{
					propertyName=propertyNodeList[m].getAttributeValue("propertyName");
				}
				String propertyType=propertyNodeList[m].getAttributeValue("propertyType");
				out.println(this.addGetSetMethod(propertyType, propertyName));
			}
			
			out.println(this.addGetSetMethod(tempKeyType, tempKey+"_q"));
			for(int j=0;j<objPropertyList.size();j++){
				String temp = objPropertyList.get(j).toString();
				String tempType = objPropertyTypeList.get(j).toString();
				out.println(this.addGetSetMethod(tempType, temp));
			}
			
			out.println(this.addGetSetMethod(beanType+"Service", beanName+"Service"));
			
			out.println(this.addEndMessage()); 
			out.close();
			fw.close();
			System.out.println("生成javaAction文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	private String addBeginMessage(XMLNode node,String beanType){
		String tempStr = "";
		String implClassPackage = node.getChildNode("filePath").getAttributeValue("classPath");
		String extendsClass =node.getChildNode("extendsClass").getAttributeValue("className");
		
		String[] extendsClassArray = extendsClass.split("\\.");
		String extendsClassType = extendsClassArray[extendsClassArray.length-1];
		
		String actionPackage="";
		String[] stringArray = implClassPackage.split("\\.");
		for(int i=0;i<stringArray.length;i++){
			if("".equals(actionPackage)){
				actionPackage+=stringArray[i];
			}else{
				actionPackage+="."+stringArray[i];
			}
		}
		tempStr = "package "+actionPackage+";\n";
		tempStr += "\nimport java.util.List;\n";
		tempStr += "import java.util.Map;";
		tempStr += "import java.util.HashMap;\n";
		
		tempStr +="\nimport java.util.Date;\n";
		tempStr +="\nimport kdf.tools.PageException;";
		tempStr +="\nimport kdf.tools.Pageable;";
		tempStr +="\nimport kdf.tools.IbatisPage;";
		
		tempStr += "\nimport org.apache.struts2.ServletActionContext;\n";
		
		tempStr += "\nimport "+node.getChildNode("beanPath").getAttributeValue("beanPath")+"."+beanType+";\n";
		tempStr += "import "+extendsClass+";\n";
		
		XMLNode[] nodeList = node.getChildNode("relateInterfaces").selectNodes("./relateInterface");
		for(int i=0;i<nodeList.length;i++){
			tempStr += "import "+nodeList[i].getAttributeValue("value")+"."+beanType+"Service;\n";
		}

		tempStr	+="\n";
		tempStr +="public class "+beanType+"Action ";
		tempStr +="extends "+extendsClassType+"{\n";
		
		System.out.println("tempStr:"+tempStr);
		return tempStr;
	}
	private String addProperty(String propertyType,String propertyName,String propertyValue){
		String propertyStr="\t";
		if("".equals(propertyValue)||propertyValue==null){
			propertyStr+="private "+propertyType+" "+propertyName+";";
		}else if(" ".equals(propertyValue)){
			propertyStr+="private "+propertyType+" "+propertyName+"=\"\";";
		}else{
			propertyStr+="private "+propertyType+" "+propertyName+"="+propertyValue+";";
		}
		return propertyStr;
	}
	private String addExecuteMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		
		if("doQuery".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoQueryMethod(methodNode,beanTableMsg);
		}else if("doUpdateInit".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoUpdateInitMethod(methodNode,beanTableMsg);
		}else if("doUpdate".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoUpdateMethod(methodNode,beanTableMsg);
		}else if("doDelete".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoDeleteMethod(methodNode,beanTableMsg);
		}else if("doInsertInit".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoInsertInitMethod(methodNode,beanTableMsg);
		}else if("doInsert".equals(methodNode.getAttributeValue("methodName"))){
			return this.setDoInsertMethod(methodNode,beanTableMsg);
		}
		return "";
	}
	
	private String setDoQueryMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		List propertyList = beanTableMsg.getPropertyList();
		String isConfig = beanTableMsg.getIsConfig();
		String beanType = beanTableMsg.getBeanType();
		String beanName = beanTableMsg.getBeanName();
		
		
		String doQueryStr = "\n\tpublic String doQuery(){";
		doQueryStr += "\n\t\ttry {";
		doQueryStr += "\n\t\t\tint currentPageInt = 1;";
		doQueryStr += "\n\t\t\tString strCurrentPage = currentPage;";
		doQueryStr += "\n\t\t\tif (strCurrentPage != null && !\"\".equals(strCurrentPage)) {";
		doQueryStr += "\n\t\t\t\ttry {";
		doQueryStr += "\n\t\t\t\t\tcurrentPageInt = Integer.parseInt(strCurrentPage);";
		doQueryStr += "\n\t\t\t\t} catch (NumberFormatException e) {";
		doQueryStr += "\n\t\t\t\t\te.printStackTrace();";
		doQueryStr += "\n\t\t\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doQueryStr += "\n\t\t\t\t}";
		doQueryStr += "\n\t\t\t}";
		doQueryStr += "\n\t\t\tint offset = (currentPageInt-1) * pages;";
		doQueryStr += "\n\t\t\tint limit = pages;";
		doQueryStr +="\n";
		doQueryStr += "\n\t\t\tMap condition = new HashMap();";
		for(int i=0;i<propertyList.size();i++){
			String property = propertyList.get(i).toString();
			String tempProperty = property.substring(0, 1).toUpperCase()+property.substring(1);
			doQueryStr += "\n\t\t\tcondition.put(\""+property+"\",  \"\".equals("+property+")?\"\":\"%\"+"+property+"+\"%\");";
		}
		doQueryStr +="\n";
		
		doQueryStr += "\n\t\t\tint record = "+beanName+"Service.getCount"+beanType+"s(condition);";
		doQueryStr += "\n\t\t\tList lst ="+beanName+"Service.get"+beanType+"s(condition, offset, limit);";
		doQueryStr +="\n";
		doQueryStr += "\n\t\t\tPageable pg = null;";
		doQueryStr += "\n\t\t\ttry {";
		doQueryStr += "\n\t\t\t\tpg = new IbatisPage(lst, record, currentPageInt, pages);";
		doQueryStr += "\n\t\t\t} catch (PageException e) {";
		doQueryStr += "\n\t\t\t\tpg = null;";
		doQueryStr += "\n\t\t\t}";
		doQueryStr += "\n\t\t\tServletActionContext.getRequest().setAttribute(\"pages\", pg);";
		doQueryStr += "\n\t\t\t"+beanName+"List = pg.getResult();";
		
		doQueryStr += "\n\t\t} catch (Exception e) {";
		doQueryStr += "\n\t\t\te.printStackTrace();";
		doQueryStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doQueryStr += "\n\t\t}";
		doQueryStr += "\n\t\treturn SUCCESS;";
		doQueryStr += "\n\t}";
		
		return doQueryStr;
	}
	private String setDoUpdateInitMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		List propertyList = beanTableMsg.getPropertyList();
		String tempKey = propertyList.get(0).toString()+"_q";
		String beanType = beanTableMsg.getBeanType();
		String beanName = beanTableMsg.getBeanName();
		
		String doUpdateInitStr = "\n\tpublic String doUpdateInit(){";
		doUpdateInitStr += "\n\t\ttry {";
		doUpdateInitStr += "\n\t\t\t"+beanType+" "+beanName+" =" +beanName+"Service.get"+beanType+"ById("+tempKey+");";
		for(int i=0; i<propertyList.size();i++){
			String property = propertyList.get(i).toString();
			String tempProperty = property.substring(0, 1).toUpperCase()+property.substring(1);
			doUpdateInitStr += "\n\t\t\t"+property+" = "+beanName+".get"+tempProperty+"();";
		}
		
		doUpdateInitStr += "\n\t\t\treadonly = true;";
		doUpdateInitStr += "\n\t\t\tServletActionContext.getRequest().setAttribute(\"readonly\",readonly);";
		doUpdateInitStr += "\n\t\t} catch (Exception e) {";
		doUpdateInitStr += "\n\t\t\te.printStackTrace();";
		doUpdateInitStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doUpdateInitStr += "\n\t\t}";
		doUpdateInitStr += "\n\t\treturn SUCCESS;";
		doUpdateInitStr += "\n\t}";
		return doUpdateInitStr;
	}
	private String setDoUpdateMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		List propertyList = beanTableMsg.getPropertyList();
		String tempKey = propertyList.get(0).toString()+"_q";
		String beanType = beanTableMsg.getBeanType();
		String beanName = beanTableMsg.getBeanName();
		
		String doUpdateStr ="\n\tpublic String doUpdate(){";
		doUpdateStr += "\n\t\ttry {";
		doUpdateStr += "\n\t\t\t"+beanType+" "+beanName+" =" +beanName+"Service.get"+beanType+"ById("+tempKey+");";
		for(int i=0;i<propertyList.size();i++){
			String property = propertyList.get(i).toString();
			String tempProperty = property.substring(0, 1).toUpperCase()+property.substring(1);
			doUpdateStr += "\n\t\t\t"+beanName+".set"+tempProperty+"("+property+");";
		}
		doUpdateStr += "\n\t\t\t"+beanName+"Service.modify"+beanType+"("+beanName+");";

		doUpdateStr += "\n\t\t} catch (Exception e) {";
		doUpdateStr += "\n\t\t\te.printStackTrace();";
		doUpdateStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doUpdateStr += "\n\t\t}";
		doUpdateStr += "\n\t\treturn SUCCESS;";
		doUpdateStr += "\n\t}";
		
		return doUpdateStr;
	}
	private String setDoDeleteMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		List propertyList = beanTableMsg.getPropertyList();
		String tempKey = propertyList.get(0).toString()+"_q";
		String beanType = beanTableMsg.getBeanType();
		String beanName = beanTableMsg.getBeanName();
		String doDeleteStr ="\n\tpublic String doDelete(){";
		doDeleteStr += "\n\t\ttry {";
		doDeleteStr += "\n\t\t\t"+beanName+"Service.remove"+beanType+"ById("+tempKey+");";
		
		doDeleteStr += "\n\t\t} catch (Exception e) {";
		doDeleteStr += "\n\t\t\te.printStackTrace();";
		doDeleteStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doDeleteStr += "\n\t\t}";
		doDeleteStr += "\n\t\treturn SUCCESS;";
		doDeleteStr += "\n\t}";
		
		return doDeleteStr;
	}
	private String setDoInsertInitMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		String doInsertInitStr = "\n\tpublic String doInsertInit(){";
		doInsertInitStr += "\n\t\ttry {";
		doInsertInitStr += "\n\t\t\treadonly = false;";
		doInsertInitStr += "\n\t\t\tServletActionContext.getRequest().setAttribute(\"readonly\",readonly);";
		
		doInsertInitStr += "\n\t\t} catch (Exception e) {";
		doInsertInitStr += "\n\t\t\te.printStackTrace();";
		doInsertInitStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doInsertInitStr += "\n\t\t}";
		doInsertInitStr += "\n\t\treturn SUCCESS;";
		doInsertInitStr += "\n\t}";
		return doInsertInitStr;
	}
	private String setDoInsertMethod(XMLNode methodNode,BeanTableMsg beanTableMsg){
		List propertyList = beanTableMsg.getPropertyList();
		String beanType = beanTableMsg.getBeanType();
		String beanName = beanTableMsg.getBeanName();
		
		String doInsertStr ="\n\tpublic String doInsert(){";
		doInsertStr += "\n\t\ttry {";
		doInsertStr += "\n\t\t\t"+beanType+" "+beanName+" = new "+beanType+"();";
		for(int i = 0;i<propertyList.size();i++){
			String property = propertyList.get(i).toString();
			String tempProperty = property.substring(0, 1).toUpperCase()+property.substring(1);
			doInsertStr += "\n\t\t\t"+beanName+".set"+tempProperty+"("+property+");";	
		}
		doInsertStr += "\n\t\t\t"+beanName+"Service.save"+beanType+"("+beanName+");";
		doInsertStr += "\n\t\t} catch (Exception e) {";
		doInsertStr += "\n\t\t\te.printStackTrace();";
		doInsertStr += "\n\t\t\tthis.addActionError(\"please check! there is Exception\");";
		doInsertStr += "\n\t\t}";
		doInsertStr += "\n\t\treturn SUCCESS;";
		doInsertStr += "\n\t}";
		return doInsertStr;
	}
	

	private String addGetSetMethod(String propertyType,String propertyName){
		String tempPropertyName=propertyName.substring(0, 1).toUpperCase()+propertyName.substring(1);
		String setMethodStr="\n\tpublic void set"+tempPropertyName+"("+propertyType+" "+propertyName+"){";
		setMethodStr+="\n\t\tthis."+propertyName+"="+propertyName+";";
		setMethodStr+="\n\t}";
		String getMethodStr="\n\tpublic "+propertyType+" get"+tempPropertyName+"(){";
		getMethodStr+="\n\t\treturn "+propertyName+";";
		getMethodStr+="\n\t}";
		return setMethodStr+getMethodStr;
	}
	private String addEndMessage(){
		
		return "\n}";
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
