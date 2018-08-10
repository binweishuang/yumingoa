package runix.upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import runix.util.StringUtil;
 
@SuppressWarnings("serial")
public class Upload extends HttpServlet {
	
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String[] files = { ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".txt", ".zip", ".rar", ".pdf" ,".jpg",".gif",".png"};
        String savePath = "";
        String doc = "";
        savePath = savePath + "/upload/";
        File f1 = new File(savePath);
        System.out.println(savePath);
    
        if (!f1.exists()) {
            f1.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List fileList = null;
        
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }
        
        Iterator<FileItem> it = fileList.iterator();
        String name = "";//新文件
        String extName = "";//类型
        String size = "";
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                size = item.getSize() + ";";
                String type = item.getContentType();
                System.out.println(name+" "+size + " " + type);//文件名称、大小、类型
                if (name == null || name.trim().equals("")) {
                    continue;
                }
                //扩展名格式： 
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                if (!StringUtil.findString(extName, files)) {
    				response.getWriter().print(name + "   文件格式受限制");
    				return;
    			}
                File file = null;
                String uuName = "";
                do {
                    //生成文件名：
                	uuName = UUID.randomUUID().toString();
                    file = new File(savePath + uuName + extName);
                } while (file.exists());
					doc += savePath + uuName + extName;
					doc += ";";
                	File saveFile = new File(getServletContext().getRealPath(savePath)+ "/" + uuName + extName);
                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.getWriter().print(doc+"|"+name + "|" + size);
        System.out.println(doc+"|"+name + "|" + size);
    }
}