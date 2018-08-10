package kdf.tools.easyCode.execute;

import java.sql.Connection;

import kdf.tools.easyCode.DAO.CreateBeanDAO;
import kdf.tools.easyCode.DAO.impl.CreateBeanDAOImpl;
import kdf.tools.easyCode.bean.CreateBean;
import kdf.tools.easyCode.bean.impl.CreateBeanImpl;
import kdf.tools.easyCode.config.ibatis.CreateIbatisConfig;
import kdf.tools.easyCode.config.ibatis.impl.CreateIbatisConfigImpl;
import kdf.tools.easyCode.config.spring.CreateSpringConfig;
import kdf.tools.easyCode.config.spring.impl.CreateSpringConfigImpl;
import kdf.tools.easyCode.config.struts.StrutsControl;
import kdf.tools.easyCode.config.struts.impl.StrutsControlImpl;
import kdf.tools.easyCode.dataSource.EasyCodeDataSource;
import kdf.tools.easyCode.service.CreateBeanService;
import kdf.tools.easyCode.service.impl.CreateBeanServiceImpl;
import kdf.tools.xml.XMLBuilder;
import kdf.tools.xml.XMLDocument;
import kdf.tools.xml.XMLNode;

public class SimpleFactory {
	private String xmlFilePath="src/config/easyCode/easyCode.xml";
	private Connection conn=null;
	
	public boolean createClass(){
		try {
			//获取xml文件
			XMLDocument doc = XMLBuilder.buildDocument();
			doc.loadFromFile(xmlFilePath);
			//连接数据库
			conn = this.getConnection(doc);
			XMLNode[] tableNodes = doc.selectNodes("/easyCodeConfig/totalConfigs/singleConfig/sourceTables/table");
			if(tableNodes.length<1){
				System.out.println("easyCode.xml文件中无表信息");
				return false;
			}else{
				XMLNode singleConfigNode = doc.getRoot().getChildNode("totalConfigs").getChildNode("singleConfig");
				XMLNode beanNode = singleConfigNode.getChildNode("beanConfig");
				XMLNode beanDAONode = singleConfigNode.getChildNode("daoConfig");
				XMLNode beanServiceNode = singleConfigNode.getChildNode("serviceConfig");
				XMLNode buildIbatisConfigNode = singleConfigNode.getChildNode("configFiles").getChildNode("buildIbatisConfig");
				XMLNode buildSpringConfigNode = singleConfigNode.getChildNode("configFiles").getChildNode("buildSpringConfig");
				XMLNode strutsNode = singleConfigNode.getChildNode("configFiles").getChildNode("buildStrutsConfig");

				XMLNode createObjectNode = singleConfigNode.getChildNode("createObject");
				for(int i=0;i<tableNodes.length;i++){
					XMLNode tableNode = tableNodes[i];
					String tableName=tableNode.getAttributeValue("tableName");
					//tableName转换为beanName 格式为驼峰式
					String beanType=this.getBeanType(tableName);
					//生成bean类
					if(createObjectNode.getChildNode("genBean")!=null && "bean".equalsIgnoreCase(createObjectNode.getChildNode("genBean").getAttributeValue("name"))){
						this.createBean(tableName, beanNode, conn);
					}
					
					//生成dao类
					if(createObjectNode.getChildNode("genDao")!=null && "dao".equalsIgnoreCase(createObjectNode.getChildNode("genDao").getAttributeValue("name"))){
						this.createBeanDAO(beanType, beanDAONode);
					}
					//生成service类
					if(createObjectNode.getChildNode("genService")!=null && "service".equalsIgnoreCase(createObjectNode.getChildNode("genService").getAttributeValue("name"))){
						this.createBeanService(beanType, beanServiceNode);
					}
					//生成单个bean  对应的ibatis配置文件
					if(createObjectNode.getChildNode("genIbatisConfig")!=null && "ibatisConfig".equalsIgnoreCase(createObjectNode.getChildNode("genIbatisConfig").getAttributeValue("name"))){
						this.createIbatisConfig(tableName, buildIbatisConfigNode, conn);
					}
				}
				//生成总的ibatis配置文件
				if(createObjectNode.getChildNode("genGatherIbatisConfig")!=null && "gatherIbatisConfig".equalsIgnoreCase(createObjectNode.getChildNode("genGatherIbatisConfig").getAttributeValue("name"))){
					this.createGatherIbatisConfigFile(buildIbatisConfigNode, tableNodes);
				}
				//生成spring文件1：spring-dao.xml;
				if(createObjectNode.getChildNode("genSpringDaoConfig")!=null && "springDaoConfig".equalsIgnoreCase(createObjectNode.getChildNode("genSpringDaoConfig").getAttributeValue("name"))){
					this.createSpringDaoConfigFile(buildSpringConfigNode, tableNodes);
				}
				//生成spring文件2:spring-service.xml;
				if(createObjectNode.getChildNode("genSpringServiceConfig")!=null && "springServiceConfig".equalsIgnoreCase(createObjectNode.getChildNode("genSpringServiceConfig").getAttributeValue("name"))){
					this.createSpringServiceConfigFile(buildSpringConfigNode, tableNodes);
				}
			
				/**
				 * 生成struts文件  ,需生成 
				 * 	1: action 类；
				 *  2: spring  action 配置文件； 
				 *  3: struts 配置文件，
				 *  4: struts 中 list 的 edit 页面
				 * 
				 */
				if(createObjectNode.getChildNode("genStrutsConfig")!=null && "strutsConfig".equalsIgnoreCase(createObjectNode.getChildNode("genStrutsConfig").getAttributeValue("name"))){
					this.createStrutsConfigFile(strutsNode,conn);
				}

			}		
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常");
		}
		return true;
	}
	
	
	//连接数据库
	private Connection getConnection(XMLDocument doc){
		EasyCodeDataSource easyCodeDataSource = new EasyCodeDataSource();
		XMLNode datasourceNode =  doc.getRoot().getChildNode("datasource");
		return easyCodeDataSource.getConnection(datasourceNode);
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
	//生成bean类
	private void createBean(String tableName,XMLNode beanNode,Connection conn){
		CreateBean createBean = new CreateBeanImpl();
		createBean.createBeanMethod(tableName, beanNode, conn);
	}
	//生成DAO类
	private void createBeanDAO(String beanType,XMLNode beanDAONode){
		CreateBeanDAO createBeanDAO = new CreateBeanDAOImpl();
		createBeanDAO.createBeanDAOMethod(beanType, beanDAONode);
	}
	//生成service类
	private void createBeanService(String beanType,XMLNode beanServiceNode){
		CreateBeanService createBeanService = new CreateBeanServiceImpl();
		createBeanService.createBeanServiceMethod(beanType, beanServiceNode);
	}
	
	//生成 单个相对bean的 ibatis配置文件
	private void createIbatisConfig(String tableName,XMLNode buildIbatisConfigNode,Connection conn){
		CreateIbatisConfig createIbatisConfig = new CreateIbatisConfigImpl();
		createIbatisConfig.createIbatisConfig(tableName, buildIbatisConfigNode, conn);
		
	}
	//生成总的ibatis配置文件
	private void createGatherIbatisConfigFile(XMLNode buildIbatisConfigNode,XMLNode[] sourceTablesNode){
		CreateIbatisConfig createIbatisConfig = new CreateIbatisConfigImpl();
		createIbatisConfig.createGatherIbatisConfigFile( buildIbatisConfigNode,sourceTablesNode);
	}
	//生成spring  dao层的配置文件
	private void createSpringDaoConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes){
		CreateSpringConfig createSpringConfig = new CreateSpringConfigImpl();
		createSpringConfig.createSpringDaoConfigFile(buildSpringConfigNode, tableNodes);
	}
	//生成spring  service层的 配置文件
	private void createSpringServiceConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes){
		CreateSpringConfig createSpringConfig = new CreateSpringConfigImpl();
		createSpringConfig.createSpringServiceConfigFile(buildSpringConfigNode, tableNodes);
		
	}
	
	//生成struts  的配置文件
	private void createStrutsConfigFile(XMLNode strutsNode,Connection conn){
		StrutsControl strutsControl = new StrutsControlImpl();
		strutsControl.createFactoryMethod(strutsNode,conn);
	}
	
	
	
	
}
