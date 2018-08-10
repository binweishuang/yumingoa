<%@page import="kdf.web.html.PageResources"%>
<%@page import="kdf.constant.SystemConfig"%>
<%
	String sys_resources = SystemConfig.SYS_RESOURCES_PATH;
	if( "/".equals(sys_resources) )
	{
		sys_resources = "";
	}
	//out.println(PageResources.outCss(sys_resources + "/css/kdf_system.css"));
	//2011-07-13 add
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_system.css"));
	out.println(PageResources.outJs(request.getContextPath() + "/js/serverside.js"));
	out.println(PageResources.outJs(sys_resources + "/js/system.js"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_bottom.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_head.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_info.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_info11111.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/kdf_leftc.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/select.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/solo.css"));
	out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/css/style.css"));
	//out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/js/ztree/css/demo.css"));
	//out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/js/ztree/css/zTreeStyle/zTreeStyle.css"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/ztree/js/jquery-1.4.4.min.js"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/ztree/js/jquery.ztree.core-3.0.js"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/ztree/js/jquery.ztree.excheck-3.0.js"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/ztree/js/jquery.ztree.exedit-3.0.js"));
	out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/check.js"));
	out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/js/My97DatePicker/WdatePicker.js"));
	//out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/extjs/resources/css/ext-all.css"));
	//out.println(PageResources.outCss(SystemConfig.FRAMEWORK_PATH + "/extjs/resources/css/ext-patch.css"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/extjs/adapter/ext/ext-base.js"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/extjs/ext-all-debug.js"));
	//out.println(PageResources.outJs(SystemConfig.FRAMEWORK_PATH + "/extjs/source/locale/ext-lang-zh_CN-GBK-min.js"));
	//out.println(PageResources.outJs(sys_resources + "/js/myext.js"));
	String[] lets = request.getParameterValues("resourcelet");
	if( lets != null )
	{
		String[] reses = PageResources.getResources(sys_resources, lets);
		if( reses != null )
		{
			int len = reses.length;
			for(int i = 0; i < len; i++ )
			{
				out.println(reses[i]);
			}
		}
	}
%>