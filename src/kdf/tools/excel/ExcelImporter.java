/**
 * @(#)ExcelImporter.java			
 * @update		10/03/08
 */
package kdf.tools.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类作用：
 * 
 * @author 殷云龙
 * @version 4.0.0 
 * */
public class ExcelImporter {
	/**
	 * 作用：判断传入的文件是否是Excel类型
	 * 
	 * @param	file			传入需要判断的文件
	 * @return	boolean			当后缀名是xls的文件则返回true，贩子返回false
	 * */
	public boolean isExcel(File file) {
		String filename = file.getName();
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		if("xls".equals(ext)) 
			return true;
		else
			return false;
	}
	/**
	 * 作用：判断传入的文件是否是Excel类型
	 * 
	 * @param	file			传入需要判断的文件
	 * @return	boolean			当后缀名是xls的文件则返回true，贩子返回false
	 * */
	public boolean isExcel4Struts(File file) {
		String filename = file.getName();
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		if("xls".equals(ext)) 
			return true;
		else
			return false;
	}
	/**
	 * 作用：判断传入的文件是否是Excel类型
	 * 
	 * @param	filename		传入需要判断的文件名称
	 * @return	boolean			当后缀名是xls的文件则返回true，贩子返回false
	 * */
	public boolean isExcel4Struts2(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		if("xls".equals(ext)) 
			return true;
		else
			return false;
	}
	/**
	 * 作用：
	 * 
	 * @param	input		传入需要转换的输入流
	 * @throws	Exception
	 * */
	public List excelToData(InputStream input) throws Exception {
		List result = new ArrayList();

		Excel excel = new Excel();

		try {

			excel.init(input);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Excel文件操作失败！" + e);			
		} finally {
			try {
				input.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				throw new Exception("Excel文件操作失败！" + ex);
			}
		}
		int sheets = excel.getSheets();
		List colnameList = null;
		if (sheets > 1) {
			colnameList = new ArrayList();
			int cols = excel.getCells(1, 0);
			for (short i = 0; i < cols; i++) {
				colnameList.add(excel.getCellValue(excel.getSheet(1),excel.getCell(1, 1, i)));
			}
		} else {
			colnameList = new ArrayList();
			int cols = excel.getCells(0, 0);
			for (short i = 0; i < cols; i++) {
				colnameList.add(String.valueOf(i + 1));
			}
		}
		int cols = excel.getCells(0, 0);
		if (cols != colnameList.size())
			throw new Exception("上传的Excel格式与模板表结构不一致,请查看Excel文件中的第二个Sheet！");

		int rows = excel.getRows(0);
		for (int i = 1; i < rows; i++) {
			HashMap map = new HashMap();
			for (short j = 0; j < cols; j++) {
				map.put(colnameList.get(j), excel.getCellValue(excel.getSheet(0),excel.getCell(0, i, j)));
			}
			result.add(map);
		}

		return result;
	}
}
