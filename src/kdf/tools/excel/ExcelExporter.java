package kdf.tools.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kdf.constant.SystemConfig;
import kdf.tools.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;



public class ExcelExporter {
	/**
     * 标题处理类
     */
    protected TitlePreparer titlePreparer;

    /**
     * 表底处理类
     */
    protected FooterPreparer footerPreparer;

    /**
     *
     */
    protected HeaderFormater headerFormter;

    /**
     * 数据格式类
     */
    protected DataFormater dataFormater;
    
    
	protected String firstSheetName = "";
	
	protected HSSFWorkbook workBook = null;
	
	/**
     * 是否不处理标题
     */
    protected boolean skipTitle = false;

    protected boolean onlyForExport = false;
	
	public ExcelExporter() {
		firstSheetName = "";
        workBook = null;
	}
	
	public void init() {
		if(workBook==null)
			workBook = new HSSFWorkbook();
	}
	
	/**
     * 是否不处理标题
     *
     * @return
     */
    public boolean isSkipTitle() {
        return skipTitle;
    }

    /**
     * 设置不处理标题
     *
     * @param skipTitle
     */
    public void setSkipTitle(boolean skipTitle) {
        this.skipTitle = skipTitle;
    }

    /**
     * HSSFWorkbook
     *
     * @return
     */
    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    /**
     * HSSFWorkbook
     */
    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    /**
     * DataFormater
     *
     * @return Returns the dataFormater.
     */
    public DataFormater getDataFormater() {
        return dataFormater;
    }

    /**
     * @param dataFormater
     *            The dataFormater to set.
     */
    public void setDataFormater(DataFormater dataFormater) {
        this.dataFormater = dataFormater;
    }

    /**
     * @return Returns the footerPreparer.
     */
    public FooterPreparer getFooterPreparer() {
        return footerPreparer;
    }

    /**
     * @param footerPreparer
     *            The footerPreparer to set.
     */
    public void setFooterPreparer(FooterPreparer footerPreparer) {
        this.footerPreparer = footerPreparer;
    }

    /**
     * @return Returns the headerFormter.
     */
    public HeaderFormater getHeaderFormter() {
        return headerFormter;
    }

    /**
     * @param headerFormter
     *            The headerFormter to set.
     */
    public void setHeaderFormter(HeaderFormater headerFormter) {
        this.headerFormter = headerFormter;
    }

    /**
     * @return Returns the titlePreparer.
     */
    public TitlePreparer getTitlePreparer() {
        return titlePreparer;
    }

    /**
     * @param titlePreparer
     *            The titlePreparer to set.
     */
    public void setTitlePreparer(TitlePreparer titlePreparer) {
        this.titlePreparer = titlePreparer;
    }
	
	protected HSSFCellStyle getNormalCellStyle(HSSFWorkbook wk)
    {
        HSSFCellStyle cellStyle = wk.createCellStyle();
        setBorder(cellStyle);
        return cellStyle;
    }
	
	private void setBorder(HSSFCellStyle cellStyle)
    {
        cellStyle.setBorderLeft((short)1);
        cellStyle.setBorderTop((short)1);
        cellStyle.setBorderBottom((short)1);
        cellStyle.setBorderRight((short)1);
    }
	
	public HSSFWorkbook exportExcel(HSSFWorkbook workBook, List[] lsts, String[] sheets, List fieldsOfSheet) {
		// 数据集行数
        int cnt = lsts.length;
        // Excel sheet 数
        int numberOfSheets = workBook.getNumberOfSheets();
        // sheetCount
        int sheetCount = numberOfSheets;

        // 每个Sheet，导出数据最大行数
        String maxRowPerSheet = SystemConfig.MAXROWPERSHEET;
        if(maxRowPerSheet.equals("")){
            maxRowPerSheet = "5003";
        }
        int nowMaxRowPerSheet = Integer.valueOf(
                maxRowPerSheet)
                .intValue();
        // cnt += workBook.get

        for (int m = 0; m < cnt; m++) {
            if (m > 0) {
                sheetCount++;
            } else if (m == 0 && numberOfSheets == 0) {
                sheetCount++;
            }

            //
            List lst = lsts[m];

            String[] fields = null;
            if (m < fieldsOfSheet.size()) {
                fields = (String[]) fieldsOfSheet.get(m);
            } else {
                fields = new String[0];
            }

            //
            Map firstVO = null;

            //
            Map styles = new HashMap();

            // 数据集合行数
            int size = lst.size();

            // 处理标题标志
            boolean handleTitle = false;
            if (this.skipTitle) {
                handleTitle = true;
            }

            HSSFSheet sheet = null;
            for (int i = 0; i < size; i++) {
                HSSFSheet currentSheet = null;
                if (numberOfSheets > 0 && numberOfSheets == sheetCount) {
                    // 取得当前处理Sheet
                    currentSheet = workBook.getSheetAt(sheetCount - 1);
                    if (currentSheet != null) {
                        // 取得Sheet最后一行数据行数
                        int nextRows = currentSheet.getPhysicalNumberOfRows();
                        if ((nextRows + 1) > nowMaxRowPerSheet) {
                            sheetCount++;
                        }
                    } else {
                        sheetCount++;
                    }
                }

                // Excel sheet 名称
                String sheetName = "";
                if (sheets != null && sheets.length > m) {
                    sheetName = sheets[m];
                }

                //
                if (numberOfSheets < sheetCount) {
                    workBook.createSheet();

                    handleTitle = false;

                    if (this.skipTitle) {
                        handleTitle = true;
                    }

                    if ("".equals(sheetName)) {
                        sheetName = "数据页" + m;
                    }
                    if (!"".equals(this.firstSheetName) && sheetCount == 1) {
                        sheetName = this.firstSheetName;
                    } else {
                        sheetName += "(" + sheetCount + ")";
                    }

                    workBook.setSheetName(sheetCount - 1, sheetName,
                            HSSFWorkbook.ENCODING_UTF_16);

                    numberOfSheets = sheetCount;
                }

                sheet = workBook.getSheetAt(sheetCount - 1);
                if (firstVO == null) {
                    firstVO = (Map)lst.get(0);
                    if (this.titlePreparer != null) {
                        this.titlePreparer.prepareTitle(workBook, sheet, lst,
                                fields);
                    }
                }

                int len = fields.length;

                int rows = sheet.getPhysicalNumberOfRows() - 1;

                int cellIndex = 0;
                if (!handleTitle) {
                    // create caption
                    // HSSFCellStyle style = workBook.createCellStyle();
                    // style.setFillBackgroundColor(HSSFColor..index);
                    // style.setFillPattern(HSSFCellStyle.);

                    HSSFRow row = sheet.createRow(++rows);
                    HSSFCellStyle cellStyle = this.getNormalCellStyle(workBook);
                    HSSFFont font = workBook.createFont();
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    font.setFontHeight((short) 200);
                    cellStyle.setFont(font);
                    for (int j = 0; j < fields.length; j++) {
                        HSSFCell cell = row.createCell((short) cellIndex);
                        // cell.setCellStyle(style);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(fields[j].substring(fields[j].indexOf("-")+1));
                        cell.setCellStyle(cellStyle);
                        if (this.headerFormter != null) {
                            this.headerFormter.formatHeader(workBook, sheet,
                                    cellIndex, cell);
                        }
                        cellIndex++;
                    }
                    handleTitle = true;
                }

                cellIndex = 0;

                HSSFRow row = sheet.createRow(++rows);

                Map vo = null;
                if (this.onlyForExport) {
                	vo = (Map)lst.get(0);
                    lst.remove(0);
                } else {
                	vo = (Map)lst.get(i);
                }
                // 如果得到行，则表示该VOList已经为空，不处理;主要针对ResultSetVOListWrapper
                if (vo == null) {
                    break;
                }
                for (int n = 0; n < len; n++) {
                	String field = fields[n];
                    HSSFCell cell = row.createCell((short) cellIndex);

                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);

                    String fieldName = field.substring(0,field.lastIndexOf("-"));
                    String type = "";
                    int start = field.indexOf("[");
                    int end = field.indexOf("]");
                    if(start > 0 && end > start) {
                    	type = field.substring(start+1,end);
                    	fieldName = field.substring(0, start);
                    }
                    
                    if ("date".equals(type)) {
                        try {
                            cell.setCellValue(SimpleDateFormat.getInstance()
                                    .parse((String)vo.get(fieldName)));
                        } catch (Exception e) {
                            HSSFCellStyle style = (HSSFCellStyle) styles
                                    .get(fieldName);
                            if (style == null) {
                                style = workBook.createCellStyle();
                                style.setDataFormat(HSSFDataFormat
                                        .getBuiltinFormat("text"));
                                styles.put(fieldName, style);
                                this.setBorder(style);

                            }
                            cell.setCellStyle(style);
                            cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                        }
                    } else if (type != null
                            && type.toLowerCase().startsWith("number")) {
                        try {
                            HSSFCellStyle style = (HSSFCellStyle) styles
                                    .get(fieldName);
                            if (style == null) {
                                style = workBook.createCellStyle();
                                if (type.length() > "number:".length()) {
                                    String format = type.substring("number:"
                                            .length());
                                    style.setDataFormat(HSSFDataFormat
                                            .getBuiltinFormat(format));
                                } else {
                                    style
                                            .setDataFormat(HSSFDataFormat
                                                    .getBuiltinFormat("#.###############"));
                                }
                                styles.put(fieldName, style);
                                this.setBorder(style);
                            }

                            cell.setCellStyle(style);
                            cell.setCellValue(Double.parseDouble(vo.get(fieldName).toString()));
                        } catch (Exception e) {
                            HSSFCellStyle style = (HSSFCellStyle) styles
                                    .get(fieldName);
                            if (style == null) {
                                style = workBook.createCellStyle();
                                style.setDataFormat(HSSFDataFormat
                                        .getBuiltinFormat("text"));
                                styles.put(fieldName, style);
                                this.setBorder(style);
                            }
                            cell.setCellStyle(style);
                            cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                        }
                    } else {
                        HSSFCellStyle style = (HSSFCellStyle) styles
                                .get(fieldName);
                        if (style == null) {
                            style = workBook.createCellStyle();
                            style.setDataFormat(HSSFDataFormat
                                    .getBuiltinFormat("text"));
                            styles.put(fieldName, style);
                            this.setBorder(style);
                        }
                        cell.setCellStyle(style);
                        cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                    }
                    if (this.dataFormater != null) {
                        this.dataFormater.formatData(workBook, sheet, i,
                                cellIndex, cell);
                    }
                    cellIndex++;
                }
                if (i > 0)
                    vo.clear();
            }

            if (this.footerPreparer != null) {
                this.footerPreparer.prepareFooter(workBook, sheet, lst, fields);
            }
        }
        // checkOutOfMemory(workBook);
        return workBook;
	}
	
	/**
     * 检测类存
     *
     * @param workBook
     */
    public static void checkOutOfMemory(HSSFWorkbook workBook) {
        boolean canGetSize = true;
        try {
            workBook.getClass().getMethod("getSize", new Class[] {});
        } catch (Throwable thr) {
            canGetSize = false;
        }
        if (canGetSize) {
            int size = workBook.getSize();
            if (size > Runtime.getRuntime().freeMemory() * 0.15) {
                throw new DataTooLargeException();
            }
        }
    }

    /**
     *
     * @param name
     * @param fields
     * @return
     */
    protected boolean canExport(String name, String[] fields) {
        if (fields == null || fields.length == 0) {
            return true;
        } else {
            return StringUtil.exists(name, fields, true);
        }
    }
	
    /**
     * 将给定数据集中数据导出到Excel,并将其写入到指定OutStream中
     *
     * @param lst
     *            数据集
     * @param out
     *            指定流
     * @param fields
     *            待导出的字段列表
     * @return
     * @throws IOException
     */
    public HSSFWorkbook exportExcel(List lst, OutputStream out,
                                    String[] fields) throws IOException {
        this.init();

        List vFields = new ArrayList();

        vFields.add(fields);

        // 将给定数据集中数据导出到Excel
        this.exportExcel(workBook, new List[] { lst }, new String[] { "数据" },
                vFields);
        // checkOutOfMemory(workBook);
        workBook.write(out);
        return workBook;
    }
	
	/**
    *
    * @param lsts
    * @param out
    * @param sheets
    * @param fields
    * @return
    * @throws IOException
    */
	public HSSFWorkbook exportExcel(List[] lsts, OutputStream out,
                                   String[] sheets, List fields) throws IOException {
       //
       this.init();

       //
       this.exportExcel(workBook, lsts, sheets, fields);

       //
       // checkOutOfMemory(workBook);

       //
       workBook.write(out);

       return workBook;
	}
	
	/**
    *
    * @param lst
    * @param template
    * @param out
    * @param fields
    * @return
    * @throws IOException
    */
	public HSSFWorkbook exportExcel(List lst, String template,
                                   OutputStream out, String[] fields) throws IOException {
       //
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(template));

       //
       HSSFWorkbook workbook = new HSSFWorkbook(fs);

       List vFields = new ArrayList();
       vFields.add(fields);

       exportExcel(workbook, new List[] { lst }, new String[] { "数据" },
               vFields);

       // checkOutOfMemory(workBook);

       workbook.write(out);

       return workbook;
	}
	
	
	/**
    *
    * @param lsts
    * @param template
    * @param out
    * @param sheets
    * @param fields
    * @return
    * @throws IOException
    */
	public HSSFWorkbook exportExcel(List[] lsts, String template,
                                   OutputStream out, String[] sheets, List fields) throws IOException {
       //
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(template));

       //
       HSSFWorkbook workbook = new HSSFWorkbook(fs);

       //
       this.exportExcel(workbook, lsts, sheets, fields);

       // checkOutOfMemory(workBook);

       workbook.write(out);

       return workbook;
	}
	
	/**
     * 将给定数据集中数据导出到Excel,并将其写入到指定文件中
     *
     * @param lst
     *            数据集
     * @param fileName
     *            文件名称
     * @param fields
     *            待导出字段列表
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HSSFWorkbook exportExcel(List lst, String fileName, String[] fields)
            throws FileNotFoundException, IOException {
        //
        FileOutputStream out = new FileOutputStream(fileName);

        //
        HSSFWorkbook ret = exportExcel(lst, out, fields);

        //
        out.flush();
        out.close();

        //
        return ret;
    }

    /**
    *
    * @param lst
    *            数据集
    * @param template
    *            模板名称
    * @param fileName
    *            文件名称
    * @param fields
    *            待导出字段列表
    * @return
    * @throws FileNotFoundException
    * @throws IOException
    */
    public HSSFWorkbook exportExcel(List lst, String template,
                                   String fileName, String[] fields) throws FileNotFoundException,
           IOException {
       //
       FileOutputStream out = new FileOutputStream(fileName);

       //
       HSSFWorkbook ret = exportExcel(lst, template, out, fields);

       //
       out.flush();
       out.close();

       return ret;
    }
    
    public void setOnlyForExport(boolean onlyForExport) {
        this.onlyForExport = onlyForExport;
    }

    public String getFirstSheetName() {
        return firstSheetName;
    }

    public void setFirstSheetName(String firstSheetName) {
        this.firstSheetName = firstSheetName;
    }
	
    /**
    *
    * @param lst 数据
    * @param fields 待导出字段列表
    * @param template 完整模版路径
    * @param fileName 文件名
    * @param startRow 插入数据起始行索引
    * @param startColumn 插入数据起始列索引
    * @return
    * @throws IOException
    */
	public HSSFWorkbook exportExcelWithTemplate(List lst, String[] fields, String template,
                                   String fileName, int startRow, int startColumn) throws FileNotFoundException,IOException {

	   FileOutputStream out = new FileOutputStream(fileName);
	   POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(template));

       HSSFWorkbook workbook = new HSSFWorkbook(fs);
       HSSFSheet sheet = workbook.getSheet("Sheet1");
       if(sheet==null){
    	   System.out.println("ERROR:制定的模版不存在Sheet1页！");
    	   return null;
       }
       
       Map styles = new HashMap();
       for(int i=0;i<lst.size();i++){
    	   Map vo = (HashMap)lst.get(i);
    	   if(vo==null){
    		   break;
    	   }
    	   HSSFRow row = sheet.getRow(startRow+i);
    	   if(row==null){
    		   row = sheet.createRow(startRow+i);
    	   }
    	   for(int j=0;j<fields.length;j++){
    		   String field = fields[j];
    		   HSSFCell cell = row.getCell((short)(startColumn+j));
    		   if(cell==null){
    			   cell = row.createCell((short)(startColumn+j));
    		   }
    		   cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    		   String fieldName = field.substring(0,field.lastIndexOf("-"));
    		   String type = "";
    		   int start = field.indexOf("[");
               int end = field.indexOf("]");
               if(start > 0 && end > start) {
               	type = field.substring(start+1,end);
               	fieldName = field.substring(0, start);
               }
               
               if ("date".equals(type)) {
                   try {
                       cell.setCellValue(SimpleDateFormat.getInstance()
                               .parse((String)vo.get(fieldName)));
                   } catch (Exception e) {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           style.setDataFormat(HSSFDataFormat
                                   .getBuiltinFormat("text"));
                           styles.put(fieldName, style);
                           this.setBorder(style);

                       }
                       cell.setCellStyle(style);
                       cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                   }
               } else if (type != null
                       && type.toLowerCase().startsWith("number")) {
                   try {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           if (type.length() > "number:".length()) {
                               String format = type.substring("number:"
                                       .length());
                               style.setDataFormat(HSSFDataFormat
                                       .getBuiltinFormat(format));
                           } else {
                               style
                                       .setDataFormat(HSSFDataFormat
                                               .getBuiltinFormat("#.###############"));
                           }
                           styles.put(fieldName, style);
                           this.setBorder(style);
                       }

                       cell.setCellStyle(style);
                       cell.setCellValue(Double.parseDouble(vo.get(fieldName).toString()));
                   } catch (Exception e) {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           style.setDataFormat(HSSFDataFormat
                                   .getBuiltinFormat("text"));
                           styles.put(fieldName, style);
                           this.setBorder(style);
                       }
                       cell.setCellStyle(style);
                       cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                   }
               } else {
                   HSSFCellStyle style = (HSSFCellStyle) styles
                           .get(fieldName);
                   if (style == null) {
                       style = workbook.createCellStyle();
                       style.setDataFormat(HSSFDataFormat
                               .getBuiltinFormat("text"));
                       styles.put(fieldName, style);
                       this.setBorder(style);
                   }
                   cell.setCellStyle(style);
                   cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
               }
    	   }
       }

       workbook.write(out);
       out.flush();
       out.close();

       return workbook;
	}
	
	/**
    *
    * @param lst 数据
    * @param fields 待导出字段列表
    * @param template 完整模版路径
    * @param fileName 文件名
    * @param startRow 插入数据起始行索引
    * @param startColumn 插入数据起始列索引
    * @param title 标题
    * @param subTitle 副标题
    * @return
    * @throws IOException
    */
	public HSSFWorkbook exportExcelWithTemplate(List lst, String[] fields, String template,
                                   String fileName, int startRow, int startColumn, String title, String subTitle) throws FileNotFoundException,IOException {

	   FileOutputStream out = new FileOutputStream(fileName);
	   POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(template));

       HSSFWorkbook workbook = new HSSFWorkbook(fs);
       HSSFSheet sheet = workbook.getSheet("Sheet1");
       if(sheet==null){
    	   System.out.println("ERROR:制定的模版不存在Sheet1页！");
    	   return null;
       }
       
       if (!"".equals(title)) {
           HSSFRow titleRow = sheet.getRow(0);
           if(titleRow==null){
        	   titleRow = sheet.createRow(0);
           }
           HSSFCell titleCell = titleRow.getCell((short)0);
           if(titleCell==null){
        	   titleCell = titleRow.createCell((short) 0);
           }
           titleCell.setEncoding(HSSFCell.ENCODING_UTF_16);
           titleCell.setCellValue(title);
           Region rg = new Region(0, (short) 0, 0, (short) (fields.length - 1));
           sheet.addMergedRegion(rg);
           HSSFCellStyle cellStyle = titleCell.getCellStyle();
           if(cellStyle==null){
        	   cellStyle = this.getTitleCellStyle(workbook);
           }
           for (short i = 0; i < fields.length; i++) {
               HSSFCell c = titleRow.getCell(i);
               if (c == null) {
                   c = titleRow.createCell(i);
               }
               c.setCellStyle(cellStyle);
           }
       }
       
       if(!"".equals(subTitle)&&subTitle!=null){
    	   HSSFRow subRow = sheet.getRow(1);
    	   if(subRow==null){
    		   subRow = sheet.createRow(1);
    	   }
    	   HSSFCell subCell = subRow.getCell((short)0);
    	   if(subCell==null){
    		   subCell = subRow.createCell((short)0);
    	   }
    	   subCell.setEncoding(HSSFCell.ENCODING_UTF_16);
    	   subCell.setCellValue(subTitle);
           sheet.addMergedRegion(new Region(1, (short) 0, 1,
                   (short) (fields.length - 1)));
           HSSFCellStyle cellStyle = subCell.getCellStyle();
           if(cellStyle==null){
        	   cellStyle = this.getSubTitleCellStyle(workbook);
           }
           for (short i = 0; i < fields.length; i++) {
               HSSFCell c = subRow.getCell(i);
               if (c == null) {
                   c = subRow.createCell(i);
               }
               c.setCellStyle(cellStyle);
           }
       }
       
       Map styles = new HashMap();
       for(int i=0;i<lst.size();i++){
    	   Map vo = (HashMap)lst.get(i);
    	   if(vo==null){
    		   break;
    	   }
    	   HSSFRow row = sheet.getRow(startRow+i);
    	   if(row==null){
    		   row = sheet.createRow(startRow+i);
    	   }
    	   for(int j=0;j<fields.length;j++){
    		   String field = fields[j];
    		   HSSFCell cell = row.getCell((short)(startColumn+j));
    		   if(cell==null){
    			   cell = row.createCell((short)(startColumn+j));
    		   }
    		   cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    		   String fieldName = field.substring(0,field.lastIndexOf("-"));
    		   String type = "";
    		   int start = field.indexOf("[");
               int end = field.indexOf("]");
               if(start > 0 && end > start) {
               	type = field.substring(start+1,end);
               	fieldName = field.substring(0, start);
               }
               
               if ("date".equals(type)) {
                   try {
                       cell.setCellValue(SimpleDateFormat.getInstance()
                               .parse((String)vo.get(fieldName)));
                   } catch (Exception e) {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           style.setDataFormat(HSSFDataFormat
                                   .getBuiltinFormat("text"));
                           styles.put(fieldName, style);
                           this.setBorder(style);

                       }
                       cell.setCellStyle(style);
                       cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                   }
               } else if (type != null
                       && type.toLowerCase().startsWith("number")) {
                   try {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           if (type.length() > "number:".length()) {
                               String format = type.substring("number:"
                                       .length());
                               style.setDataFormat(HSSFDataFormat
                                       .getBuiltinFormat(format));
                           } else {
                               style
                                       .setDataFormat(HSSFDataFormat
                                               .getBuiltinFormat("#.###############"));
                           }
                           styles.put(fieldName, style);
                           this.setBorder(style);
                       }

                       cell.setCellStyle(style);
                       cell.setCellValue(Double.parseDouble(vo.get(fieldName).toString()));
                   } catch (Exception e) {
                       HSSFCellStyle style = (HSSFCellStyle) styles
                               .get(fieldName);
                       if (style == null) {
                           style = workbook.createCellStyle();
                           style.setDataFormat(HSSFDataFormat
                                   .getBuiltinFormat("text"));
                           styles.put(fieldName, style);
                           this.setBorder(style);
                       }
                       cell.setCellStyle(style);
                       cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
                   }
               } else {
                   HSSFCellStyle style = (HSSFCellStyle) styles
                           .get(fieldName);
                   if (style == null) {
                       style = workbook.createCellStyle();
                       style.setDataFormat(HSSFDataFormat
                               .getBuiltinFormat("text"));
                       styles.put(fieldName, style);
                       this.setBorder(style);
                   }
                   cell.setCellStyle(style);
                   cell.setCellValue(vo.get(fieldName)==null?"":vo.get(fieldName).toString());
               }
    	   }
       }

       workbook.write(out);
       out.flush();
       out.close();

       return workbook;
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
