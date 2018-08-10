/**
 * @(#)Range.java			
 * @update		10/03/09
 */
package kdf.tools.jsonext;

import java.util.ArrayList;
import java.util.List;
/**
 * 类作用：用于传送json串信息
 * 
 * @author 殷云龙
 * @version 4.0.0 
 * */
public class Range {
	/***/
	boolean success;
	/**json串中传递的消息*/
	String message;
	/**有几个记录集*/
	long totalProperty;
	/**json串根节点*/
	List root;
	
	public Range() {
		this.totalProperty = 0;
		this.root = new ArrayList();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(long totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List getRoot() {
		return root;
	}

	public void setRoot(List root) {
		this.root = root;
	}
	
}
