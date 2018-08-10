package kdf.tools.excel;

/**
 * 
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTooLargeException extends RuntimeException {

    /**
     * 捕获数据太长情况下的异常，
     *
     */
	public DataTooLargeException() {
		super("data too large,pls wait for exporting!");
	}

}
