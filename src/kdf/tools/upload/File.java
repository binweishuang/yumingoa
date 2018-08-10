/**
 *@(#)File.java	
 */
package kdf.tools.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
/**
 * 该类是一个文件类
 * @author 王清培
 * @version 4.0.0
 */
public class File {
	private FileItem fileItem;

	private long m_size;

	private String m_fieldname;

	private String m_filename;

	private String m_fileExt;

	private String m_filePathName;

	private String m_contentType;

	public static final int SAVEAS_AUTO = 0;

	public static final int SAVEAS_VIRTUAL = 1;

	public static final int SAVEAS_PHYSICAL = 2;

	File() {
		m_size = 0L;
		m_fieldname = "";
		m_filename = "";
		m_fileExt = "";
		m_filePathName = "";
		m_contentType = "";
	}

	/**
	 * 保存文件到指定路径下		
	 * @param s 待保存文件的路径字符串
	 */
	public void saveAs(String s) throws Exception {
		FileOutputStream outFile = new FileOutputStream(s);
		outFile.write(fileItem.get());
		outFile.flush();
		outFile.close();
	}

	/**
	 * 重载saveAs方法,保存文件到指定路径下
	 * @param s 待保存文件的路径字符串
	 */
	public void saveAs(String s, int flag) throws Exception {
		saveAs(s);
	}

	/**
	 * 返回文件长度
	 * @return boolean 文件的长度
	 */
	public boolean isMissing() {
		return m_size == 0L;
	}


	public String getFieldName() {
		return m_fieldname;
	}

	/**
	 * 获取文件上传后的名字
	 * @return String 文件上传后的名字
	 */
	public String getFileName() {
		return m_filename;
	}

	/**
	 * 获取文件路径名称
	 * @return String 文件路径名称
	 */
	public String getFilePathName() {
		return m_filePathName;
	}

	public String getFileExt() {
		return m_fileExt;
	}

	/**
	 * 获取文件的上下文类型
	 * @return 文件类型
	 */
	public String getContentType() {
		return m_contentType;
	}

	public String getContentString() {
		return "";
	}

	/**
	 * 获取文件的大小
	 * @return long 文件的大小
	 */
	public long getSize() {
		return m_size;
	}

	/**
	 * 设置文件的大小值
	 * @param i 文件的大小值
	 */
	protected void setSize(long i) {
		m_size = i;
	}


	protected void setFieldName(String s) {
		m_fieldname = s;
	}

	/**
	 * 设置文件上传后的名字
	 * @param s 文件的名字
	 */
	protected void setFileName(String s) {
		m_filename = s;
	}

	/**
	 * 设置文件的路径名称
	 * @param s 文件的路径名称
	 */
	protected void setFilePathName(String s) {
		m_filePathName = s;
	}

	protected void setFileExt(String s) {
		m_fileExt = s;
	}

	/**
	 * 设置文件的上下文类型
	 * @param s 文件的类型
	 */
	protected void setContentType(String s) {
		m_contentType = s;
	}

	/**
	 * 获取文件的项
	 */
	public FileItem getFileItem() {
		return fileItem;
	}

	/**
	 * 设置文件的项
	 */
	public void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	/**
	 * 获取文件的输出流
	 * @return InputStream 输出流
	 */
	public InputStream getInputStream() throws IOException {
		return fileItem.getInputStream();
	}
}
