package kdf.tools.easyCode.config.struts;

import java.sql.Connection;

import kdf.tools.xml.XMLNode;

public interface StrutsControl {
	public void createFactoryMethod(XMLNode buildStrutsConfigNode,Connection conn);
}
