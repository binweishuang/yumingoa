/**
 *@(#)Files.java	
 */
package kdf.tools.upload;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 文件类集合，提供对一个文件或一组文件的集合操作
 * @author 王清培
 * @version 4.0.0
 */
public class Files {
	private Hashtable m_files;

	private int m_counter;

	Files() {
		m_files = new Hashtable();
		m_counter = 0;
	}

	/**
	 * 添加文件到集合
	 * @param file 待添加的文件
	 */
	protected void addFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("newFile cannot be null.");
		} else {
			m_files.put(new Integer(m_counter), file);
			m_counter++;
			return;
		}
	}

	/**
	 * 返回集合里指定索引的文件
	 * @return File 待返回的单个文件
	 */
	public File getFile(int i) {
		if (i < 0)
			throw new IllegalArgumentException(
					"File's index cannot be a negative value (1210).");
		File file = (File) m_files.get(new Integer(i));
		if (file == null)
			throw new IllegalArgumentException(
					"Files' name is invalid or does not exist (1205).");
		else
			return file;
	}

	/**
	 * 获取集合中的元素数
	 * @return int 元素的集合数
	 */
	public int getCount() {
		return m_counter;
	}

	/**
	 * 获取集合的大小
	 * @return long 
	 * 
	 */
	public long getSize() throws IOException {
		long l = 0L;
		for (int i = 0; i < m_counter; i++)
			l += getFile(i).getSize();

		return l;
	}

	/**
	 * 返回文件集合
	 * @return Collection 
	 */
	public Collection getCollection() {
		return m_files.values();
	}

	/**
	 * 返回一个实现了Enumeration接口的文件元素
	 * @return Enumeration
	 */
	public Enumeration getEnumeration() {
		return m_files.elements();
	}
}
