/**
 * @(#)TitlePreparerImpl.java			
 * @update		10/03/08
 */
package kdf.tools.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

/**
 * 类作用：创建工作表中的“标题”与“子标题”，并设置其样式，与单元格边框
 * 
 * @author 殷云龙
 * @version 4.0.0 
 * */
public class TitlePreparerImpl implements TitlePreparer {

	/**
     * 标题
     */
    protected String title = "";

    /**
     * 子标题
     */
    protected String subTitle = "";

    /**
     * Constructor
     * 
     * @param title
     * @param subTitle
     */
    public TitlePreparerImpl(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    /**
     * 作用：当title不为“”时，则在当前的工作表中添加单元格存放 title
     * 		当subTitle不为""时，则在当前的工作表中添加单元格存放 subTitle
     * 
     * @param	sheet			当前sheet
     * @param	lst				将填充的数据集
     * @param	fields			填充字段
     * @see com.exp.fcl.tool.TitlePreparer#prepareTitle(org.apache.poi.hssf.usermodel.HSSFWorkbook,
     *      org.apache.poi.hssf.usermodel.HSSFSheet, com.exp.fcl.vo.VOList,
     *      java.lang.String[])
     */
    public void prepareTitle(HSSFWorkbook wk, HSSFSheet sheet, List lst,
            String[] fields) {
        if (!"".equals(title)) {
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell((short) 0);

            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(title);
            Region rg = new Region(0, (short) 0, 0, (short) (fields.length - 1));
            sheet.addMergedRegion(rg);
            HSSFCellStyle cellStyle = this.getTitleCellStyle(wk);
            for (short i = 0; i < fields.length; i++) {
                HSSFCell c = row.getCell(i);
                if (c == null) {
                    c = row.createCell(i);
                }
                c.setCellStyle(cellStyle);
            }
        }
        if (!"".equals(subTitle)) {
            HSSFRow row = sheet.createRow(1);

            HSSFCell cell = row.createCell((short) 0);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(subTitle);
            sheet.addMergedRegion(new Region(1, (short) 0, 1,
                    (short) (fields.length - 1)));
            HSSFCellStyle cellStyle = this.getSubTitleCellStyle(wk);
            for (short i = 0; i < fields.length; i++) {
                HSSFCell c = row.getCell(i);
                if (c == null) {
                    c = row.createCell(i);
                }
                c.setCellStyle(cellStyle);
            }
        }
    }

    /**
     * 作用：设置标题单元格样式
     * 
     * @param wk
     */
    protected HSSFCellStyle getTitleCellStyle(HSSFWorkbook wk) {
        HSSFCellStyle cellStyle = wk.createCellStyle();

        HSSFFont font = wk.createFont();

        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight((short) 280);

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(font);

        this.setBorder(cellStyle);

        return cellStyle;
    }

    /**
     * 作用：设置单元格的边框，上，下，左，右
     * 
     * @param 	cellStyle		单元格样式对象	
     */
    private void setBorder(HSSFCellStyle cellStyle) {
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderRight((short) 1);
    }

    /**
     * 作用：设置子标题单元格的样式
     * 
     * @param wk
     * @param cell
     */
    protected HSSFCellStyle getSubTitleCellStyle(HSSFWorkbook wk) {
        HSSFCellStyle cellStyle = wk.createCellStyle();

        HSSFFont font = wk.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight((short) 240);

        cellStyle.setFont(font);
        setBorder(cellStyle);

        return cellStyle;
    }

}
