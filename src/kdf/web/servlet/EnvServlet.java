package kdf.web.servlet;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import kdf.constant.SystemConfig;
import kdf.tools.xml.XMLBuilder;
import kdf.tools.xml.XMLDocument;
import kdf.tools.xml.XMLException;
import kdf.tools.xml.XMLNode;

public class EnvServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.endsWith("serverside.js")) {
			StringBuffer buf = new StringBuffer();
			buf.append("function getContextPath(){\n");
			buf.append("\treturn \"" + req.getContextPath() + "\";}\n");
			buf.append("function getFrameworkPath(){\n");
			buf.append("\treturn \"" + req.getContextPath() + "/framework\";}\n");
			buf.append("function getSysResourcesPath(){\n");
			buf.append("\treturn \"" + req.getContextPath() + "/framework/sys-resources\";}\n");
			resp.resetBuffer();
			resp.getOutputStream().print(buf.toString());
			resp.flushBuffer();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		
		//得到Spring的ApplicationContext
		ServletContext ctx  = this.getServletContext();
		SystemConfig.CONTEXT = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		//设置web-inf绝对路径
		String webInfPath = this.getInitParameter("webInfo");
		if (webInfPath == null || "".equals(webInfPath)) {
			webInfPath = "/WEB-INF";
		}
		try {
			// WEB-INF绝对路径
			webInfPath = this.getServletContext().getRealPath(webInfPath);
		} catch (Exception e) {
			throw new ServletException("配置路径不存在！");
		}
		if (!webInfPath.endsWith("/")) {
			webInfPath += "/";
		}
		SystemConfig.WEB_INF_PATH = webInfPath;
		
		//设置webroot绝对路径
		String webRootPath = this.getServletContext().getRealPath("/");
		if (!webRootPath.endsWith("/") && !webRootPath.endsWith("\\")) {
			webRootPath += File.separator;
		}
		SystemConfig.WEB_ROOT_PATH = webRootPath;

		//加载system.xml配置
		String config = this.getServletContext().getRealPath(this.getInitParameter("config"));
		Map params = new HashMap();
		XMLDocument doc = XMLBuilder.buildDocument();
		try {
			doc.loadFromFile(config);
		} catch (XMLException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			XMLNode node = doc.getRoot().getChildNode("params");
			if (node != null) {
				int nCount = node.getChildNodesCount();
				for (int i = 0; i < nCount; i++) {
					XMLNode temp = node.getChildNode(i);
					Object valObject = temp.getNodeValueObject();
					params.put(temp.getNodeName(), valObject);
				}
			}
		}

		//得到初始化密码
		SystemConfig.INIT_PWD = params.get("init_pwd").toString();
		//得到系统名称
		SystemConfig.APP_NAME = params.get("app_name").toString();
		//得到系统版本
		SystemConfig.APP_VERSION = params.get("app_version").toString();
		//得到版权信息
		SystemConfig.APP_COPYRIGHT = params.get("app_copyright").toString();
		//得到平台版本
		SystemConfig.PLAT_VERSION = params.get("plat_version").toString();
		//得到技术支持电话
		SystemConfig.TELEPHONE = params.get("telephone").toString();
		//得到技术支持商
		SystemConfig.TECH_SUPPORT = params.get("tech_support").toString();
		//得到系统管理员ID
		SystemConfig.ADMIN = params.get("admin").toString();
		//得到系统管理员权限
		SystemConfig.MENU = params.get("menu").toString();
		//得到是否启用验证码
		SystemConfig.IS_STARTUP_VALIDATE = "1".equals(params.get("is_startup_validate").toString())?true:false;


		//得到容器编码
		SystemConfig.CONTAINER_ENCODING = params.get("container_encoding").toString();
		
		//得到servletContext
		SystemConfig.SERVLET_CONTEXT = this.getServletContext();
		//得到服务器IP
		try {
			SystemConfig.SERVER_IP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		//得到framework绝对路径
		SystemConfig.FRAMEWORK_PATH = SystemConfig.SERVLET_CONTEXT.getContextPath()+ "/framework";
		//得到sys-resources绝对路径
		SystemConfig.SYS_RESOURCES_PATH = SystemConfig.SERVLET_CONTEXT.getContextPath()+ "/framework/sys-resources";
		
		//设置数据库
		String database = params.get("database").toString();
		if ("".equals(database))
			database = "mysql";
		SystemConfig.DATABASE = database;
		
		//设置全文检索的路径
		SystemConfig.DOC_SEARCH_PATH =  SystemConfig.WEB_ROOT_PATH + params.get("doc_search_path").toString();
		SystemConfig.DOC_INDEX_PATH = SystemConfig.WEB_ROOT_PATH + params.get("doc_index_path").toString();
		if(!new File(SystemConfig.DOC_SEARCH_PATH).exists()){    
	        new File(SystemConfig.DOC_SEARCH_PATH).mkdirs();    
	    }
		if(!new File(SystemConfig.DOC_INDEX_PATH).exists()){    
	        new File(SystemConfig.DOC_INDEX_PATH).mkdirs();    
	    }
		
		//是否启用CAS的SSO
		//SystemConfig.IS_STARTUP_CAS = params.get("is_startup_cas").toString();
		//得到CAS的登出地址
		if("1".equals(SystemConfig.IS_STARTUP_CAS)) {
			SystemConfig.CAS_LOGOUT_URL = params.get("cas_logout_url").toString();
		}
		
		String lang = "";
		String country = "";
		String var = "";
		try {
			lang = this.getInitParameter("Language");
			country = this.getInitParameter("Country");
			var = this.getInitParameter("Variant");

			// 本地化
			SystemConfig.setTargetLocale(lang, country, var);
		} catch (Exception e) {
		}
//		System.out.println("----------------------平台初始化已完成！------------------");
//		System.out.println("********************系统环境变量列表********************");
//		System.out.println("*\t系统组件版本: " + SystemConfig.SYSTEM_VERSION );
//		System.out.println("*\t基础平台版本: " + SystemConfig.PLAT_VERSION);
//		System.out.println("*\t应用名称: " + SystemConfig.APP_NAME);
//		System.out.println("*\t应用版本: " + SystemConfig.APP_VERSION);
//		System.out.println("*\t应用绝对路径: " + SystemConfig.WEB_ROOT_PATH);
//		System.out.println("*\tWEB-INF绝对路径: " + SystemConfig.WEB_INF_PATH);
//		System.out.println("*\tFramework URL路径: " + SystemConfig.FRAMEWORK_PATH);
//		System.out.println("*\t目标编码: " + SystemConfig.ENCODING);
//		System.out.println("*\t容器编码: " + SystemConfig.CONTAINER_ENCODING);
//		System.out.println("*\t数据库：" + SystemConfig.DATABASE);
//		Locale locale = SystemConfig.getTargetLocale();
//		System.out.println("*\t目标语言: " + locale.getLanguage());
//		System.out.println("*\t所处国家: " + locale.getCountry());
//		System.out.println("*\t本地化变量: " + locale.getVariant());
//		System.out.println("*\t全文搜索绝对路径：" + SystemConfig.DOC_SEARCH_PATH);
//		System.out.println("*\t全文搜索索引绝对路径：" + SystemConfig.DOC_INDEX_PATH);
//		if("1".equals(SystemConfig.IS_STARTUP_CAS)) System.out.println("*\t已经启用基于CAS的SSO");
//		System.out.println("*****************************************************");
	}

}
