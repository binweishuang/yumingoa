package kdf.tools.easyCode.config.struts;

import java.sql.Connection;

import kdf.tools.xml.XMLNode;

public interface ListPageSet {

	public void createListPage(XMLNode struts2ConfigNode,Connection conn);
}
