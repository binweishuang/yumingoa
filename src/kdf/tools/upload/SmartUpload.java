/**
 * @(#)SmartUpload.java			
 * @update		2010/3/8
 */
package kdf.tools.upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import kdf.constant.SystemConfig;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
/**
 *  该类的作用实现快速的上传文件
 * @author 王清培
 * @version 4.0.0
 */
public class SmartUpload {
	protected HttpServletRequest m_request;

	private Files m_files;

	private Request m_formRequest;

	private long maxSize = 52428800L;

	private int maxThreshold = 5242880;
	
	private String savePath;

	/**
	 * 构造函数(实例化文件集合类和请求类实例)
	 */
	public SmartUpload() {
		m_files = new Files();
		m_formRequest = new Request();
	}
	
	/**
	 * 判断上传的HTTP上下文是否为空
	 * @return Boolean True不为空,False为空
	 * @param request 请求对象
	 */
	public static boolean isMultipartContent(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(new ServletRequestContext(
				request));
	}
	
	/**
	 * 设置文件上传的保存路径
	 * @param path 文件保存的路径字符串
	 */
	public void setSavePath(String path) {
		this.savePath = path;
	}
	
	/**
	 * 设置上传文件的最大值
	 * @param i 待设置的值
	 */
	public void setMaxFileSize(int i) {
		maxSize = i;
		maxThreshold = i;
	}

	/**
	 * 设置请求对象HTTP的上下文
	 * @param request 请求对象
	 * 
	 * 
	 */
	public final void initialize(HttpServletRequest request)
			throws ServletException {
		m_request = request;
	}

	/**
	 * 开始上传文件
	 */
	public void upload() throws ServletException, IOException,
			FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxThreshold);
		String path = savePath;
		if("".equals(path)) path = SystemConfig.getUploadPath();
		File fTemp = new File(path);
		fTemp.mkdirs();
		factory.setRepository(fTemp);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(SystemConfig.ENCODING);
		upload.setSizeMax(maxSize);
		List items = upload.parseRequest(m_request);
		for (Iterator iter = items.iterator(); iter.hasNext();) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
				m_formRequest.putParameter(name, value);
			} else {
				String fileName = item.getName();
				String contentType = item.getContentType();
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				kdf.tools.upload.File file = new kdf.tools.upload.File();
				file.setFieldName(item.getFieldName());
				file.setFileExt(ext);
				file.setFileName(fileName.substring(fileName
						.lastIndexOf(File.separator) + 1));
				file.setContentType(contentType);
				file.setFileItem(item);
				file.setSize(item.getSize());
				m_files.addFile(file);
			}
		}

	}

	/**
	 * 获取上传的文件
	 * @return Files 上的文件
	 */
	public Files getFiles() {
		return m_files;
	}

	/**
	 * 获取请求对象Request
	 * @return Request 请求的对象
	 */
	public Request getRequest() {
		return m_formRequest;
	}

	/**
	 * 释放文件集合空间
	 */
	public void release() {
		try {
			int size = m_files.getCount();
			for (int i = 0; i < size; i++) {
				kdf.tools.upload.File f = m_files.getFile(i);
				f.getFileItem().delete();
			}

		} catch (Throwable throwable) {
		}
	}
}
