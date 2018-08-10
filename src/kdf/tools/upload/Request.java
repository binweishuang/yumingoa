/**
 *@(#)Request.java	
 * @update		10/03/09
 */
package kdf.tools.upload;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 类作用:
 * 
 * @author 殷云龙
 * @version 4.0.0
 */
public class Request {
	
	private Hashtable m_parameters;
	private int m_counter;

	/**
	 * 作用：对m_parameters,m_counter进行赋值
	 * */
	public Request() {
		m_parameters = new Hashtable();
		m_counter = 0;
	}
	/**
	 * 作用：设置参数，s作为key，s1作为value，其中假如参数中已经包含s时，则可以获取该key得到hashTable
	 * 		在其中用hashTable的大小作为key，s1作为value存入，假如参数中不包含s时，则新建hashTable
	 * 		以0作为key，s1作为value存入，再在m_parameters中以s作为key，以hashTable作为key存储
	 * 
	 * 
	 * @param	s				s作为key存储
	 * @param	s1				s1作为value存储
	 * */
	protected void putParameter(String s, String s1) {
		if (s == null)
			throw new IllegalArgumentException(
					"The name of an element cannot be null.");
		if (m_parameters.containsKey(s)) {
			Hashtable hashtable = (Hashtable) m_parameters.get(s);
			hashtable.put(new Integer(hashtable.size()), s1);
		} else {
			Hashtable hashtable1 = new Hashtable();
			hashtable1.put(new Integer(0), s1);
			m_parameters.put(s, hashtable1);
			m_counter++;
		}
	}
	/**
	 * 作用：
	 * 
	 * @param	s				s作为key来获取值信息
	 * @return	String			返回获取的value信息
	 * */
	public String getParameter(String s) {
		if (s == null)
			throw new IllegalArgumentException(
					"Form's name is invalid or does not exist (1305).");
		Hashtable hashtable = (Hashtable) m_parameters.get(s);
		if (hashtable == null)
			return null;
		else
			return (String) hashtable.get(new Integer(0));
	}

	public Enumeration getParameterNames() {
		return m_parameters.keys();
	}
	/**
	 * 作用：通过s这个key获取hashtable信息，获取hashTable中的所有value，存放于String[]中
	 * 
	 * @param	s				s作为key用于查询
	 * @return	String[]		返回查询出的value信息
	 * */
	public String[] getParameterValues(String s) {
		if (s == null)
			throw new IllegalArgumentException(
					"Form's name is invalid or does not exist (1305).");
		Hashtable hashtable = (Hashtable) m_parameters.get(s);
		if (hashtable == null)
			return null;
		String as[] = new String[hashtable.size()];
		for (int i = 0; i < hashtable.size(); i++)
			as[i] = (String) hashtable.get(new Integer(i));

		return as;
	}
}
