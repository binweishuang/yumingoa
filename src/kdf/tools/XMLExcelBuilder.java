/**
 *@(#)XMLExcelBuilder.java	
 */
package kdf.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类提供对XML的导出处理
 * @author 王清培
 * @version 4.0.0
 */
public class XMLExcelBuilder {
    private OutputStream out;

    private String title = "";

    private String subTitle = "";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public XMLExcelBuilder(OutputStream out) {
        this.out = out;
    }

    /**
     * 设置标题
     * @param title 标题名称
     */
    public void setTile(String title) {
        this.title = title;
    }

    /**
     * 设置子标题
     * @param 子标题名称
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 构建XMLExcel
     *
     * @param lst
     *            要导出的数据
     * @param only4ExportList
     *            该List只是为了导出用途
     */
    public void build(List lst, String[] fields, boolean only4ExportVOList)
            throws Exception {
        int count = lst.size();
        // count = count > 65000 ? 65000 : count;
        if (count > 0) {
            int sheetIndex = 0;
            this.beginWorkbook();
            Map firstVO = (Map)lst.get(0);
            Map types = new HashMap();
            int len = fields.length;
            String[] labels = new String[len];
            this.beginStyles();

            //如果没有记录时,导出的excel不能打开
            if(firstVO!=null){
                for (int i = 0; i < len; i++) {
                    String field = fields[i];
                    String fieldName = field.substring(0,field.lastIndexOf("-"));
                    String type = "";
                    int start = field.indexOf("[");
                    int end = field.indexOf("]");
                    if(start > 0 && end > start) {
                    	type = field.substring(start+1,end);
                    	fieldName = field.substring(0, start);
                    }
                    labels[i] = fieldName;
                    if (type != null) {
                        if (type.startsWith("number")) {
                            String format = "#.###############";
                            if (type.length() > "number:".length()) {
                                format = type.substring("number:".length());
                            }
                            types.put(field, "Number");
                            this.addStyle(field, format);
                        } else if ("date".equalsIgnoreCase(type)) {
                            // String format = "yyyy-mm-dd";
                            String value = (String)firstVO.get(fieldName);
                            SimpleDateFormat sdf = new SimpleDateFormat(
                                    "yyyy-mm-dd");
                            try {
                                sdf.parse(value);
                                types.put(field, "date");
                            } catch (Exception e) {
                            }
                            // this.addStyle(field, format);
                        }
                    }
                }
                this.endStyles();
            }else{
                labels= fields;
                this.endStyles();
                this.beginSheet("数据(" + sheetIndex + ")", len);
                buildTitles(len);
                buildFieldLabels(labels);
            }
            int index = 0;
            for (int i = 0; i < count; i++) {
                // /////new sheet
                if (i % 65000 == 0) {
                    sheetIndex++;
                    if (i > 0) {
                        this.endSheet();
                    }
                    this.beginSheet("数据(" + sheetIndex + ")", len);
                    if (i == 0) {
                        buildTitles(len);
                    }
                    // /BUILD field label
                    buildFieldLabels(labels);
                    // //end

                }
                if (only4ExportVOList) {
                    index = 0;
                } else {
                    index = i;
                }
                Map vo = (Map)lst.get(index);
                //  如果得到行，则表示该VOList已经为空，不处理;主要针对ResultSetVOListWrapper
                if (vo == null) {
                    break;
                }
                if (only4ExportVOList) {
                    lst.remove(0);
                }
                this.addRow(vo, fields, types);
            }
            this.endSheet();
            this.endWorkbook();
        }
    }

    /**
     * 输出字符流
     * @param value 将要输出的字符串
     */
    protected void write(String value) throws IOException {
        this.out.write(value.getBytes());
    }

    /**
     * 
     */
    protected void buildTitles(int cols) throws IOException {
        String temp = "<Row ss:AutoFitHeight=\"0\">"
                + "<Cell ss:MergeAcross=\"" + (cols - 1)
                + "\" ss:StyleID=\"sTitle\">" + "<Data ss:Type=\"String\">"
                + this.title + "</Data></Cell>" + "</Row>";
        this.write(temp);
        temp = "<Row ss:AutoFitHeight=\"0\">" + "<Cell ss:MergeAcross=\""
                + (cols - 1) + "\" ss:StyleID=\"sSubTitle\">"
                + "<Data ss:Type=\"String\">" + this.subTitle
                + "</Data></Cell>" + "</Row>";
        this.write(temp);
    }

    protected void buildFieldLabels(String[] labels) throws IOException {
        String temp = "<Row ss:AutoFitHeight=\"0\">";
        this.write(temp);
        int lablesLen = labels.length;
        for (int j = 0; j < lablesLen; j++) {
            temp = "<Cell ss:StyleID=\"sField\"><Data ss:Type=\"String\">"
                    + StringUtil.XMLEncode(labels[j]) + "</Data></Cell>";
            out.write(temp.getBytes());
        }
        temp = "</Row>";
        this.write(temp);
    }

    /**
     * 构建Wrokbook区域前缀׺
     *
     */
    public void beginWorkbook() throws IOException {
        this.write("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        this.write("<?mso-application progid=\"Excel.Sheet\"?>");
        this
                .write("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\" ");
        this.write(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
        this.write(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
        this
                .write(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        this.write(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
        this
                .write(" <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">");
        this
                .write("  <Created>1996-12-17T01:32:42Z</Created><LastSaved>2000-11-18T06:53:49Z</LastSaved><Version>11.5606</Version></DocumentProperties>");
        this
                .write(" <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\"><RemovePersonalInformation/></OfficeDocumentSettings>");
        this
                .write(" <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\"><WindowHeight>4530</WindowHeight><WindowWidth>8505</WindowWidth><WindowTopX>480</WindowTopX><WindowTopY>120</WindowTopY>");
        this
                .write("<AcceptLabelsInFormulas/><ProtectStructure>False</ProtectStructure><ProtectWindows>False</ProtectWindows></ExcelWorkbook>");

    }

    /**
     * 构建styles区域前缀׺
     *
     */
    public void beginStyles() throws IOException {
        this
                .write("<Styles><Style ss:ID=\"Default\" ss:Name=\"Normal\"><Alignment ss:Vertical=\"Bottom\"/>");
        this.write("<Borders>");
        this
                .write("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        this
                .write("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        this
                .write("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        this
                .write("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/></Borders>");
        this
                .write("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"10\"/><Interior/><NumberFormat/><Protection/></Style>");
        this
                .write("<Style ss:ID=\"sTitle\"><Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        this
                .write("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"14\" ss:Bold=\"1\"/></Style>");
        this
                .write("<Style ss:ID=\"sSubTitle\"><Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Bottom\"/>");
        this
                .write("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\" ss:Bold=\"1\"/></Style>");
        this
                .write("<Style ss:ID=\"sField\"><Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Bottom\"/><Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"10\" ss:Bold=\"1\"/></Style>");
        this
                .write("<Style ss:ID=\"sDate\"><NumberFormat ss:Format=\"yyyy-mm-dd\"/></Style>");
        this
                .write("<Style ss:ID=\"sDateTime\"><NumberFormat ss:Format=\"yyyy-mm-dd\\ hh:MM:ss\"/></Style>");
    }

    /**
     * 添加样式
     *
     * @param styleId
     *            样式ID
     * @param format
     *            格式
     */
    public void addStyle(String styleId, String format) throws IOException {
        this.write("<Style ss:ID=\"s" + styleId
                + "\"><NumberFormat ss:Format=\"" + format + "\"/></Style>");
    }

    /**
     * 结束样式定义区
     *
     */
    public void endStyles() throws IOException {
        this.write("</Styles>");
    }

    /**
     * 开始Sheet区域
     *
     * @param sheetName
     */
    public void beginSheet(String sheetName, int cols) throws IOException {
        this.write("<Worksheet ss:Name=\"" + sheetName + "\">");
        this.write("<Table ss:ExpandedColumnCount=\"" + cols
                + "\" x:FullColumns=\"1\" ");
        this
                .write(" x:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">");
        this.write("<Column ss:AutoFitWidth=\"0\" ss:Width=\"157.5\"/>");
    }

    /**
     * 添加一行数据
     *
     * @param vo
     * @param fields
     *            要导出的字段
     * @param types
     */
    public void addRow(Map vo, String[] fields, Map types)
            throws IOException {
        this.write("<Row ss:AutoFitHeight=\"0\">");
        int len = fields.length;
        for (int i = 0; i < len; i++) {
            String field = fields[i];
            String type = (String) types.get(field);
            if (type != null) {
                if (type.equals("date")) {
                	// /判断是否是合法的日期格式
                    boolean isDateFormat = false;
                    String fieldName = field.substring(0,field.lastIndexOf("-"));
                   
                    int start = field.indexOf("[");
                    int end = field.indexOf("]");
                    if(start > 0 && end > start) {
                    	fieldName = field.substring(0, start);
                    }
                    String value = (String)vo.get("fieldName");
                    if (value.length() > 10) {
                        try {
                            sdf.parse(value);
                            isDateFormat = true;
                        } catch (Exception e) {

                        }
                    }
                    if (isDateFormat) {
                        String style = "sDate";
                        if (value.length() > 10) {
                            style = "sDateTime";
                            if (value.length() > 18) {
                                value = value.substring(0, 19);
                            }
                            value = value.replaceAll(" ", "T");
                        }
                        this.write("<Cell ss:StyleID=\"" + style
                                + "\"><Data ss:Type=\"DateTime\">" + value
                                + "</Data></Cell>");
                    } else {
                        writeString(value);
                    }
                } else {
                    if ("Number".equalsIgnoreCase(type)) {
                        String value = (String)vo.get("fieldName");
                        if (NumberUtil.isDigital(value)) {
                            this.write("<Cell><Data ss:Type=\"" + type + "\">"
                                    + vo.get("fieldName") + "</Data></Cell>");
                        } else {
                            writeString(value);
                        }
                    }
                }
            } else {
                writeString((String)vo.get("fieldName"));
            }
        }
        this.write("</Row>");
    }

    protected void writeString(String value) throws IOException {
        this.write("<Cell><Data ss:Type=\"String\">"
                + StringUtil.XMLEncode(value) + "</Data></Cell>");
    }

    /**
     * 结束sheet区域
     *
     */
    public void endSheet() throws IOException {
        this.write("</Table></Worksheet>");
    }

    /**
     * 结束workbook
     *
     */
    public void endWorkbook() throws IOException {
        this.write("</Workbook>");
    }
    
    public static void main(String[] args) throws Exception {
        List lst = new ArrayList();
        Map vo = new HashMap();

        vo.put("userid", "zhf");
        vo.put("borth", "1992-02-02");
        vo.put("money", "1000.22");
        lst.add(vo);

        vo = new HashMap();
        vo.put("userid", "zhf2");
        vo.put("borth", "1992-02-09");
        vo.put("money", "11110");
        lst.add(vo);

        vo = new HashMap();
        vo.put("userid", "张汉飞");
        vo.put("borth", "1992-12-09");
        vo.put("money", "11110.3333");
        lst.add(vo);
        vo = new HashMap();
        vo.put("userid", "张汉飞2");
        vo.put("borth", "1992-12-09");
        vo.put("money", "11110.3333");
        lst.add(vo);
        vo = new HashMap();
        vo.put("userid", "张汉飞3");
        vo.put("borth", "1992-12-09");
        vo.put("money", "11110.3333");
        lst.add(vo);
        FileOutputStream out = new FileOutputStream("c:\\dddd.xls");

        XMLExcelBuilder builder = new XMLExcelBuilder(out);
        builder.setTile("主标题");
        builder.setSubTitle("次标题");
        builder.build(lst, new String[] { "userid[string]", "borth[date]", "money[number:##.##]" }, true);
        out.flush();
        out.close();

    }

}
