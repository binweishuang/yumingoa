/*
 * Created on 2005-8-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kdf.tools.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface HeaderFormater {
	/**
	 * 格式化列头ͷ
	 * 
	 * @param col
	 *            列
	 * @param cell
	 *            当前cell
	 */
	void formatHeader(HSSFWorkbook workbook, HSSFSheet sheet, int col,
			HSSFCell cell);
}