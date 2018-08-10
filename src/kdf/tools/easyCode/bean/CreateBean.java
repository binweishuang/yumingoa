package kdf.tools.easyCode.bean;

import java.sql.Connection;
import kdf.tools.xml.XMLNode;


public interface CreateBean {
	public void createBeanMethod(String tableName,XMLNode node,Connection conn);
}
