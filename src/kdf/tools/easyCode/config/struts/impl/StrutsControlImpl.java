package kdf.tools.easyCode.config.struts.impl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import kdf.tools.easyCode.config.struts.EditPageSet;
import kdf.tools.easyCode.config.struts.ListPageSet;
import kdf.tools.easyCode.config.struts.SpringActionConfig;
import kdf.tools.easyCode.config.struts.StrutsAction;
import kdf.tools.easyCode.config.struts.StrutsConfig;
import kdf.tools.easyCode.config.struts.StrutsControl;
import kdf.tools.easyCode.config.struts.beanTableMsg.BeanTableMsg;
import kdf.tools.xml.XMLNode;

public class StrutsControlImpl implements StrutsControl {

	public void createFactoryMethod(XMLNode buildStrutsConfigNode,Connection conn) {
		
		XMLNode springActionNode = buildStrutsConfigNode.getChildNode("springAction");
		XMLNode actionServiceNode = buildStrutsConfigNode.getChildNode("actionService");
		
		XMLNode gatherStrutsConfigNode = buildStrutsConfigNode.getChildNode("gatherStrutsConfig");
		XMLNode struts2ConfigsNode = buildStrutsConfigNode.getChildNode("struts2Configs");
		
		XMLNode[] struts2ConfigNodeList = struts2ConfigsNode.selectNodes("./struts2Config");
		//生成list页面
		for(int i = 0; i<struts2ConfigNodeList.length;i++){
			ListPageSet listPageSet = new ListPageSetImpl();
			listPageSet.createListPage(struts2ConfigNodeList[i],conn);
		}
		//生成edit页面
		List listBeanTableMsg = new LinkedList();
		for(int j=0;j<struts2ConfigNodeList.length; j++){
			EditPageSet editPageSet = new EditPageSetImpl();
			BeanTableMsg beanTableMsg = editPageSet.createEditPage(struts2ConfigNodeList[j],conn);
			listBeanTableMsg.add(beanTableMsg);
		}
		
		//生成spring action 文件
		SpringActionConfig springActionConfig = new SpringActionConfigImpl();
		springActionConfig.createSpringAction(springActionNode, buildStrutsConfigNode);
		
		//生成struts2 action 类文件
		StrutsAction strutsAction = new StrutsActionImpl();
		strutsAction.factoryStrutsAction(actionServiceNode,listBeanTableMsg);
		
		//生成struts2 配置文件
		StrutsConfig strutsConfig = new StrutsConfigImpl();
		strutsConfig.createStrutsConfig(gatherStrutsConfigNode, buildStrutsConfigNode);
		

	}

}
