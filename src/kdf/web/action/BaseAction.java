package kdf.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kdf.constant.SystemConfig;
import kdf.persistent.model.BaseModel;
import kdf.tools.StringUtil;
import kdf.tools.excel.ExcelExporter;
import kdf.tools.excel.TitlePreparer;
import kdf.tools.excel.TitlePreparerImpl;
import kdf.tools.upload.Request;
import kdf.tools.upload.SmartUpload;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	/**
	 * 导出模板路径
	 */
	protected String exporttemplatePath;
	
	private SmartUpload upload;
//	
//	public static DefinitionManager getDefinitionManager() {
//        DefinitionManager manager = null;
//		manager =(DefinitionManager) ServletActionContext.getContext().getApplication().get("definitionManager");
//		
//		return manager;
//	}
//	
	/**
	 * 得到全路径
	 * @param request
	 * @return
	 */
	protected String getUrl(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if(null != queryString && !"".equals(queryString)) {
			url += "?" + queryString;
		}
		return url;
	}
	
	/**
	 * 得到web.xml中的Context中的参数值
	 * <context-param>
	 *   <param-name>version</param-name>
	 *   <param-value>version 1.0.0 build by 2007.06.07</param-value>
	 * </context-param>
	 * 
	 * @param paramName
	 * @return
	 */
	protected String getContextParamValue(String paramName) {
		
		return ServletActionContext.getServletContext().getInitParameter(paramName);
	}
	
	/**
	 * 得到WEB-INF的绝对路径
	 * @return
	 */
	protected String getWebInfPath() {
		return SystemConfig.WEB_INF_PATH;
	}
	
	/**
	 * 得到web应用的绝对路径
	 * @return
	 */
	protected String getWebRootPath() {
		return SystemConfig.WEB_ROOT_PATH;
	}
	
	protected List convertListToMap(List list) throws Exception {
		List lst = new ArrayList();
		for(Iterator iter = list.iterator();iter.hasNext();) {
			Object obj = iter.next();
			if(obj instanceof Map) {
				lst.add((Map)obj);
			} else {
				BaseModel model = (BaseModel)obj;
				lst.add(model.toMap());
			}
		}
		return lst;
	}
	
	/**
	 * 通过查询的数据导出成Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param title 台头，可以为空
	 * @param titleSub 子台头，可以为空
	 * @throws Exception
	 */
	protected void toExcel(HttpServletResponse response,List lst,String field,String title,String subTitle) throws Exception{
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		if("".equals(field) || null==field) {
			if(null != lst && 0 < lst.size()) {
				if(lst.get(0) instanceof Map) {
					
				} else {
					throw new Exception("数据中不是Map对象!");
				}
				Map m = (Map)lst.get(0);
				fields = new String[m.keySet().size()];
				int i = 0;
				for(Iterator iter = m.keySet().iterator(); iter.hasNext();) {
					String fieldName = iter.next().toString();
					fields[i] = fieldName.toLowerCase() + "-" + fieldName;
					i++;
				}
			}
		} else {
			for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
	            String f = (String)tokens.nextElement();
	            if(f.equals("|"))
	            {
	                if(!tokens.hasMoreElements())
	                    break;
	                f = (String)tokens.nextElement();
	            }
	            fieldList.add(f);
			}
			fields = new String[fieldList.size()];
			for(int i=0;i<fieldList.size();i++) {
				fields[i] = fieldList.get(i).toString();
			}
		}
		ServletOutputStream stream = response.getOutputStream();
		ExcelExporter exporter = new ExcelExporter();
		TitlePreparer titlePreparer = new TitlePreparerImpl(title,subTitle);
		exporter.setTitlePreparer(titlePreparer);
		exporter.exportExcel(lst,stream,fields);
		response.setContentType("application/vnd.ms-excel; charset=GBK");
		response.setHeader("Content-Disposition", "attachment; filename=export.xls");
		stream.close();
	}
	
	/**
	 * 通过查询的数据导出成带模板的Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param template 模板文件路径
	 * @throws Exception
	 */
	protected void toExcel(HttpServletResponse response,List lst,String field,String template) throws Exception{
		
		if(this.exporttemplatePath!=null && !"".equals(exporttemplatePath)) {
			template = exporttemplatePath;
		} else {
			template = this.getWebRootPath() + "framework/sys-resources/exporttemplate/" + template;
		}
		
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		if("".equals(field) || null==field) {
			if(null != lst && 0 < lst.size()) {
				if(lst.get(0) instanceof Map) {
					
				} else {
					throw new Exception("数据中不是Map对象!");
				}
				Map m = (Map)lst.get(0);
				fields = new String[m.keySet().size()];
				int i = 0;
				for(Iterator iter = m.keySet().iterator(); iter.hasNext();) {
					String fieldName = iter.next().toString();
					fields[i] = fieldName.toLowerCase() + "-" + fieldName;
					i++;
				}
			}
		} else {
			for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
	            String f = (String)tokens.nextElement();
	            if(f.equals("|"))
	            {
	                if(!tokens.hasMoreElements())
	                    break;
	                f = (String)tokens.nextElement();
	            }
	            fieldList.add(f);
			}
			fields = new String[fieldList.size()];
			for(int i=0;i<fieldList.size();i++) {
				fields[i] = fieldList.get(i).toString();
			}
		}
		ServletOutputStream stream = response.getOutputStream();
		ExcelExporter exporter = new ExcelExporter();
		exporter.setSkipTitle(true);
		exporter.exportExcel(lst,template,stream,fields);
		response.setContentType("application/vnd.ms-excel; charset=GBK");
		response.setHeader("Content-Disposition", "attachment; filename=export.xls");
		stream.close();
	}
	
	/**
	 * 通过查询的数据导出成Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param title 台头，可以为空
	 * @param titleSub 子台头，可以为空
	 * @throws Exception
	 */
	protected String toExcelFile(HttpServletResponse response,List lst,String field,String title,String subTitle) throws Exception{
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		if("".equals(field) || null==field) {
			if(null != lst && 0 < lst.size()) {
				if(lst.get(0) instanceof Map) {
					
				} else {
					throw new Exception("数据中不是Map对象!");
				}
				Map m = (Map)lst.get(0);
				fields = new String[m.keySet().size()];
				int i = 0;
				for(Iterator iter = m.keySet().iterator(); iter.hasNext();) {
					String fieldName = iter.next().toString();
					fields[i] = fieldName.toLowerCase() + "-" + fieldName;
					i++;
				}
			}
		} else {
			for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
	            String f = (String)tokens.nextElement();
	            if(f.equals("|"))
	            {
	                if(!tokens.hasMoreElements())
	                    break;
	                f = (String)tokens.nextElement();
	            }
	            fieldList.add(f);
			}
			fields = new String[fieldList.size()];
			for(int i=0;i<fieldList.size();i++) {
				fields[i] = fieldList.get(i).toString();
			}
		}
		
		ExcelExporter exporter = new ExcelExporter();
//		exporter.exportExcel(lst,stream,fields);//原来的生成excel方法，由于数据量大会在页面显示出乱码而无法下载，所以不使用
		TitlePreparer titlePreparer = new TitlePreparerImpl(title,subTitle);
		exporter.setTitlePreparer(titlePreparer);
		String fileName = new Date().getTime()+".xls";
		String fileUrl = SystemConfig.WEB_ROOT_PATH+"export_files/"+fileName;
		exporter.exportExcel(lst, fileUrl, fields);
		/* 原来通过打开页面下载excel文件，由于页面显示乱码，所以不使用
		response.setContentType("application/vnd.ms-excel; charset=GBK");
		response.addHeader("Content-Disposition", "attachment; filename=export.xls");
		*/
		return fileName;
	}
	

	/**
	 * 通过查询的数据导出成带模板的Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param template 模板文件的路径
	 * @throws Exception
	 */
	protected String toExcelFile(HttpServletResponse response,List lst,String field,String template){
		
		if(this.exporttemplatePath!=null && !"".equals(exporttemplatePath)) {
			template = exporttemplatePath;
		} else {
			template = this.getWebRootPath() + "framework/sys-resources/exporttemplate/" + template;
		}
		
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
            String f = (String)tokens.nextElement();
           
            if(f.equals("|"))
            {
                if(!tokens.hasMoreElements())
                    break;
                f = (String)tokens.nextElement();
            }
            
            fieldList.add(f);
		}
		fields = new String[fieldList.size()];
		for(int i=0;i<fieldList.size();i++) {
			fields[i] = fieldList.get(i).toString();
			
		}
		String fileName = new Date().getTime()+".xls";
		String fileUrl = this.getWebRootPath()+"export_files/"+fileName;
		try{
//			 ServletOutputStream stream = response.getOutputStream();
				
				ExcelExporter exporter = new ExcelExporter();
				
				exporter.setSkipTitle(true);
				exporter.exportExcel(lst,template,fileUrl,fields);
				
//				response.setContentType("application/vnd.ms-excel; charset=GBK");
//				response.addHeader("Content-Disposition", "attachment; filename=export.xls");
//				
//				stream.flush();
//				stream.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
       
	}
	
	protected SmartUpload upload(HttpServletRequest request) throws Exception{
		upload = new SmartUpload();
        upload.initialize(request);
        upload.setSavePath(ServletActionContext.getServletContext().getRealPath("/"));
        upload.upload();
        Request req = upload.getRequest();
        for(Enumeration item = req.getParameterNames(); item.hasMoreElements();)
        {
            String key = (String)item.nextElement();
            String values[] = req.getParameterValues(key);
            if(values != null)
            {
                int nLen = values.length;
                for(int i = 0; i < nLen; i++)
                    request.setAttribute(key, StringUtil.enCoding(values[i]));

            }
        }
        return upload;
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
	public Map getSessionMap() {
		return ServletActionContext.getContext().getSession();
	}

	public void outJsonString(String str) {
		getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str);
	}

	public void outJson(Object obj) {
		outJsonString(JSONObject.fromObject(obj).toString());
	}

	public void outJsonArray(Object array) {
		outJsonArray(JSONArray.fromObject(array).toString());
	}

	public void outString(String str) {
		try {
			PrintWriter out = getResponse().getWriter();
			System.out.println(str);
			out.write(str);
		} catch (IOException e) {
		}
	}

	public void outXMLString(String xmlStr) {
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}
	
	/**
	 * 得到登录人的用户 ID
	 * @return
	 */
//	public String getActorId() {
//        Map session = this.getSessionMap();
//        return (String) session.get(Constants.AUTH_ACTOR_ID);
//    }
	
	/**
	 * 通过制定的模版将数据生成Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param template 模版完整路径
	 * @param startRow 插入数据起始行索引
	 * @param startColumn 插入数据起始列索引
	 * @throws Exception
	 */
	protected String toExcelWithTemplate(HttpServletResponse response,List lst,String field,String template,int startRow,int startColumn) throws Exception{
		
				
		ExcelExporter exporter = new ExcelExporter();
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
            String f = (String)tokens.nextElement();
           
            if(f.equals("|"))
            {
                if(!tokens.hasMoreElements())
                    break;
                f = (String)tokens.nextElement();
            }
            
            fieldList.add(f);
		}
		fields = new String[fieldList.size()];
		for(int i=0;i<fieldList.size();i++) {
			fields[i] = fieldList.get(i).toString();
			
		}
		String fileName = new Date().getTime()+".xls";
		String fileUrl = SystemConfig.WEB_ROOT_PATH+"export_files/"+fileName;
		template = this.getWebRootPath() + "framework/sys-resources/exporttemplate/" + template;
		exporter.exportExcelWithTemplate(lst, fields, template, fileUrl, startRow, startColumn);
				
		return fileName;
		
	}
	
	/**
	 * 通过制定的模版将数据生成带标题的Excel
	 * @param response
	 * @param lst 数据
	 * @param field 需要生成的字段，可以为空
	 * @param template 模版完整路径
	 * @param startRow 插入数据起始行索引
	 * @param startColumn 插入数据起始列索引
	 * @param title 标题
	 * @param subTitle 副标题
	 * @throws Exception
	 */
	protected String toExcelWithTemplate(HttpServletResponse response,List lst,String field,String template,int startRow,int startColumn,String title,String subTitle) throws Exception{
		
				
		ExcelExporter exporter = new ExcelExporter();
		List fieldList = new ArrayList();
		String[] fields = new String[0];
		for(StringTokenizer tokens = new StringTokenizer(field, "|", true);tokens.hasMoreElements();) {        	
            String f = (String)tokens.nextElement();
           
            if(f.equals("|"))
            {
                if(!tokens.hasMoreElements())
                    break;
                f = (String)tokens.nextElement();
            }
            
            fieldList.add(f);
		}
		fields = new String[fieldList.size()];
		for(int i=0;i<fieldList.size();i++) {
			fields[i] = fieldList.get(i).toString();
			
		}
		String fileName = new Date().getTime()+".xls";
		String fileUrl = SystemConfig.WEB_ROOT_PATH+"export_files/"+fileName;
		template = this.getWebRootPath() + "framework/sys-resources/exporttemplate/" + template;
		exporter.exportExcelWithTemplate(lst, fields, template, fileUrl, startRow, startColumn, title, subTitle);
				
		return fileName;
		
	}
}
