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
public interface DataFormater {

	/**
	 * 格式化数据
	 * 
	 * @param row
	 *            行索引
	 * @param col
	 *            列索引
	 * @param cell
	 *            当前cell
	 */
	void formatData(HSSFWorkbook workbook, HSSFSheet sheet, int row, int col,
			HSSFCell cell);
}