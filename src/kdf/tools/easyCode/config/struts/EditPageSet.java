package kdf.tools.easyCode.config.struts;

import java.sql.Connection;
import java.util.List;

import kdf.tools.easyCode.config.struts.beanTableMsg.BeanTableMsg;
import kdf.tools.xml.XMLNode;

public interface EditPageSet {
	public BeanTableMsg createEditPage(XMLNode struts2ConfigNode,Connection conn);

}
