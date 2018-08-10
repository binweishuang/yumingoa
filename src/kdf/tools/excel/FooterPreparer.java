/*
 * Created on 2005-8-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kdf.tools.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface FooterPreparer {
    /**
     * 处理页脚
     * 
     * @param sheet
     *            当前sheet
     * @param lst
     *            将填充数据
     * @param fields
     *            填充字段
     */
    void prepareFooter(HSSFWorkbook workbook, HSSFSheet sheet, List lst,
            String[] fields);
}