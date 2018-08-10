package kdf.tools.easyCode.config.spring;

import kdf.tools.xml.XMLNode;

public interface CreateSpringConfig {

	public void createSpringDaoConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes);
	
	public void createSpringServiceConfigFile(XMLNode buildSpringConfigNode,XMLNode[] tableNodes);
	
}
