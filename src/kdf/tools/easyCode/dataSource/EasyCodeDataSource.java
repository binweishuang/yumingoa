package kdf.tools.easyCode.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import kdf.tools.xml.XMLNode;

public class EasyCodeDataSource {
	private Connection conn;
	
	public Connection getConnection(XMLNode node){
		try {
			Class.forName(node.getChildNode("driver").getAttributeValue("value"));
			System.out.println("驱动类:"+node.getChildNode("driver").getAttributeValue("value"));
			String url=node.getChildNode("url").getAttributeValue("value");
			String user=node.getChildNode("userName").getAttributeValue("value"); 
			String password=node.getChildNode("password").getAttributeValue("value");
			conn= (Connection) DriverManager.getConnection(url,user,password);
			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
