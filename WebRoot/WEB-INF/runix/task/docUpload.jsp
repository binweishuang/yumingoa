<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLEncoder"%>

<%@page import="runix.util.StringUtil"%>

<%@page import="java.io.*"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>


<%
	request.setCharacterEncoding("utf-8");
	String doc ="";

	String[] Files = { ".doc", ".docx", ".xls", ".xlsx", ".ppt",
			".pptx", ".txt", ".zip", ".rar", ".pdf" ,".jpg",".gif",".png"};
	//String[] Files = {".exe"};
	String savePath = "";
	savePath = savePath + "/uploads/notice";
	
	DiskFileItemFactory fac = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(fac);
	upload.setHeaderEncoding("utf-8");
	List fileList = null;
	try {
		fileList = upload.parseRequest(request);
	} catch (FileUploadException ex) {
		ex.printStackTrace();
	}
	Iterator<FileItem> it = fileList.iterator();
	String name = "";
	String extName = "";
	String category = "";
	String realName = "";

	while (it.hasNext()) {
		FileItem item = it.next();
		if (item.isFormField()) {
			if ("category".equals(item.getFieldName())) {
				category = item.getString("utf-8");
			}
		} else if (!item.isFormField()) {
			name = item.getName();
			if (name == null || name.trim().equals("")) {
				continue;
			}
			// 扩展名格式：
			if (name.lastIndexOf(".") >= 0) {
				extName = name.substring(name.lastIndexOf("."));
			}

			if (StringUtil.findString(extName, Files)) {
				response.getWriter().print(name + "   文件格式受限制");
				break;
			} else {
				String[] strings = savePath.split("/");
				String Mainpath = getServletContext().getRealPath("");
				for (int i = 0; i < strings.length; i++) {
					File demo = new File(Mainpath, strings[i]);
					if (!demo.exists()) {
						demo.mkdir();
					}
					StringBuffer strBuf = new StringBuffer();
					for (int j = 0; j <= i; j++) {
						strBuf.append("/");
						strBuf.append(strings[j]);
					}
					Mainpath = getServletContext().getRealPath(
							strBuf.toString());
				}

				String uuName = UUID.randomUUID().toString();
				doc += name;
				doc += ",";
				doc += savePath + "/" + uuName + extName;
				doc += ";";
				realName = getServletContext().getRealPath(savePath)
						+ "/" + uuName + extName;
				File saveFile = new File(realName);
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			response.getWriter().print(doc+"|"+name + " 上传成功");
		}
	}
%>
