package kdf.tools.easyCode.config.ibatis;

import java.sql.Connection;

import kdf.tools.xml.XMLNode;

public interface CreateIbatisConfig {
	
	
	
	public void createIbatisConfig(String tableName,XMLNode buildIbatisConfigNode,Connection conn);
	/**
	 * 生成ibatis汇总配置文件
	 * 
	 * */
	public void createGatherIbatisConfigFile(XMLNode buildIbatisConfigNode,XMLNode[] sourceTablesNode);
}
