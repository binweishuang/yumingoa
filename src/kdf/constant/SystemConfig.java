package kdf.constant;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

public final class SystemConfig {
	public static String SYSTEM_VERSION = "Spring 2.2.5;Ibatis 2.3.4.726;Hibernate 3.2.5;Struts 2.0.14;Extjs 2.1;Jbmp 3.2.3";
    private static Locale TargetLocale = null;
    public static String PLAT_VERSION = "4.0.0";
	public static String ENCODING = "GB2312";
    public static String CONTAINER_ENCODING = "ISO-8859-1";
    public static String WEB_ROOT_PATH = "";    
    public static ServletContext SERVLET_CONTEXT;
    public static String WEB_INF_PATH = File.separator;
    public static String APP_NAME = "";
    public static String APP_VERSION = "";
    public static String APP_COPYRIGHT = "";
    public static String SERVER_IP = "127.0.0.1";
    public static String INIT_PWD = "123456";
    public static String TELEPHONE = "";
    public static String TECH_SUPPORT = "";
    public static String FRAMEWORK_PATH = "";
    public static String SYS_RESOURCES_PATH = "";
    public static String DOC_SEARCH_PATH = "";
    public static String DOC_INDEX_PATH = "";
    public static String BACKUP_DATABASE_URL="localhost:1521/orcl";
    public static String ADMIN = "";
    public static String MENU = "";
    public static boolean IS_STARTUP_VALIDATE = true;
    
    /**导出excel最大行数**/
    public static String MAXROWPERSHEET="0";
    
    /**是否记录webservice日志*/
	public static boolean WSINVOKELOG = true;
	/**
	 * 系统使用的数据库
	 */
	public static String DATABASE = "";
	/**
	 * 是否启用CAS的SSO
	 */
	public static String IS_STARTUP_CAS = "0";
	/**
	 * CAS的登出地址
	 */
	public static String CAS_LOGOUT_URL = "";
	
	/**
	 * 系统使用局类型
	 */
	public static String ORG_TYPE = "0";
	
	/**
	 * 系统使用直属局中文名称
	 */
	public static String ORG_AREA = "";
	
	/**
	 * 直属局系统数据是否向总局同步
	 */
	public static String HOVC_SYSNC="";
	
	/**
     * Spring的ApplicationContext
     */
    public static ApplicationContext CONTEXT;
    
	/**
	 * 得到上传路径
	 * @return
	 */
    public static String getUploadPath()
    {
        return WEB_ROOT_PATH + "uploads" + File.separator;
    }
    
    /**
	 * 取得本地化对象
	 * 
	 * @return 返回本地化对象
	 */
	public static Locale getTargetLocale() {
		if (TargetLocale == null) {
			TargetLocale = Locale.getDefault();
		}
		return TargetLocale;
	}

	/**
	 * 设置本地化
	 * 
	 * @param language
	 *            语言
	 * @param country
	 *            国家
	 * @param variant
	 */
	public static void setTargetLocale(String language, String country,
			String variant) {
		if (!language.equals("")) {
			TargetLocale = new Locale(language, country, variant);
		}
	}
	
	/**
	 * 取得WEB-INF绝对路径
	 */
	public static String getWebInfPath() {
		return WEB_INF_PATH;
	}
}
