/**
 * @(#)Excel.java			
 * @update		10/03/08
 */
package kdf.tools.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 类作用：读取Excel文档，将其转换为HSSFWorkbook对象，从而可以获取Excel文件中的
 * 		行信息，单元格信息，
 * 
 * @author 殷云龙
 * @version 4.0.0 
 * */
public class Excel {
	protected POIFSFileSystem fs;
	/**该HSSFWorkbook对象就代表了Excel文档*/
	private HSSFWorkbook workBook;
	/**
	 * 作用：将一个文件对象传入，并将其转换为InputStream类型，
	 * 		调用参数为InputStream类型的Init方法
	 * 
	 * @param	file			传入一个file对象
	 * @throws	IOException
	 * */
	public void init(File file) throws IOException {
		init(((InputStream) (new FileInputStream(file))));
	}
	/**
	 * 作用：通过文件名找到文件，将其转换为InputStream类型，
	 * 		调用参数为InputStream类型的Init方法
	 * 
	 * @param	file			传入一个文件名称
	 * @throws	IOException		
	 * 
	 * */
	public void init(String fileName) throws IOException {
		init(((InputStream) (new FileInputStream(fileName))));
	}
	/**
	 * 作用：判断传入的输入流是否大于可用内存，若大于则读取失败，否则，首先生成一个POIFSFileSystem对象，
	 * 		由POIFSFileSystem对象构造一个HSSFWorkbook，该HSSFWorkbook对象就代表了Excel文档
	 * 
	 * @param	input		传入一个输入流对象
	 * @throws	IOException	
	 * */
	public void init(InputStream input) throws IOException {
		if ((double) input.available() > (double) Runtime.getRuntime()
				.freeMemory() * 0.29999999999999999D) {
			throw new IOException(
					"文件读取失败！");
		} else {
			fs = new POIFSFileSystem(input);
			workBook = new HSSFWorkbook(fs);
			return;
		}
	}
	/**
	 * 作用:将byte[]数组转换为ByteArrayInputStream对象，然后再次转换为InputStream
	 * 		调用参数为InputStream类型的Init方法
	 * 
	 * @param	byte		传入一个bytes[]数组
	 * @throws	IOException
	 * */
	public void init(byte bytes[]) throws IOException {
		ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
		init(((InputStream) (byteInput)));
	}
	/**
	 * 作用：获取工作表的个数
	 * */
	public int getSheets() {
		return workBook.getNumberOfSheets();
	}
	/**
	 * 作用：获取Excel文档
	 * */
	public HSSFWorkbook getWorkbook() {
		return workBook;
	}
	/**
	 * 作用：通过索引获取工作表中的行数
	 * 
	 * @param	sheetIndex		指定工作表的索引
	 * @return	int				当没有获取到对应索引的工作表时，则返回'-1',反之则返回对应工作表的行数
	 * */
	public int getRows(int sheetIndex) {
		HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		if (sheet != null)
			return sheet.getPhysicalNumberOfRows();
		else
			return -1;
	}
	/**
	 * 作用：通过索引获取工作表，再指定在那一行，来获取这一行的单元格数
	 * 
	 * @param	sheetIndex		指定工作表的索引
	 * @param	row				指定是那一行
	 * @return	当不存在指定工作表，或工作表中不存在指定行数的返回‘-1’,当存在时返回单元格个数
	 * */
	public int getCells(int sheetIndex, int row) {
		HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		if (sheet != null) {
			HSSFRow rowData = sheet.getRow(row);
			if (rowData != null)
				return rowData.getPhysicalNumberOfCells();
		}
		return -1;
	}
	/**
	 * 作用：：通过索引获取工作表，再指定在那一行，再对应单元格索引获取单元格HSSFCell对象
	 * 
	 * @param	sheetIndex		指定工作表的索引
	 * @param	row				指定是那一行
	 * @param	cellIndex		指定单元格在指定行的索引
	 * @return	HSSFCell		返回查找出的单元格，若单元格不存在返回null
	 * */
	public HSSFCell getCell(int sheetIndex, int row, short cellIndex) {
		if (workBook != null) {
			HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
			HSSFRow rowData = sheet.getRow(row);
			if (rowData != null) {
				HSSFCell cell = rowData.getCell(cellIndex);
				return cell;
			}
		}
		return null;
	}
	/**
	 * 作用：通过索引获取工作表对象
	 * 
	 * @param	sheetIndex		指定的工作表的索引
	 * @return	HSSFSheet		返回获取的工作表对象
	 * */
	public HSSFSheet getSheet(int sheetIndex) {
		return workBook.getSheetAt(sheetIndex);
	}
	/**
	 * 作用：获取单元格中的值
	 * 
	 * @param	sheet			指定的工作表	
	 * @param	cell			指定的单元格
	 * @return	String			返回查询出的单元格中的值
	 * */
	public String getCellValue(HSSFSheet sheet, HSSFCell cell) {
		if(null==sheet||null==cell) {
			return "";
		}
		int type = cell.getCellType();
		switch (type) {
		case 3: // '\003'
			return "";

		case 4: // '\004'
			boolean b = cell.getBooleanCellValue();
			if (b)
				return "1";
			else
				return "0";

		case 5: // '\005'
			return "";

		case 0: // '\0'
			return "" + cell.getNumericCellValue();

		case 2: // '\002'
			return cell.getCellFormula();

		case 1: // '\001'
		default:
			return cell.getStringCellValue();
		}
	}
}
