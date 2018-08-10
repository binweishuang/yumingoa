package kdf.chart;

import java.util.List;

import kdf.chart.util.CommonMethod;

import org.dom4j.Document;


public class FusionCharts {
	private String pBarLoadingText;
	private String xmlLoadingText;
	private String parsingDataText;
	private String chartNoDataText;
	private String renderingChartText;
	private String loadDataErrorText;
	private String invalidXMLText;
	
	private String chartSWF;
	private String chartId;
	private String chartWidth;
	private String chartHeight;
	private boolean debugMode;
	private boolean regWithJS;
	
	private Document dataXml;
	
	public FusionCharts(String chartSWF,Document dataXml) {
		pBarLoadingText = "Loading Chart. Please Wait";
		this.xmlLoadingText = "Retrieving Data. Please Wait";
		this.parsingDataText = "Reading Data. Please Wait";
		this.chartNoDataText = "No data to display.";
		this.renderingChartText = "Rendering Chart. Please Wait";
		this.loadDataErrorText = "Error in loading data.";
		this.invalidXMLText = "Invalid XML data.";
		
		this.chartSWF = chartSWF;
		this.chartId = "chartid";
		this.chartWidth = "800";
		this.chartHeight = "500";
		this.debugMode = false;
		this.regWithJS = false;
		this.dataXml = dataXml;
	}

	public FusionCharts(String barLoadingText, String xmlLoadingText,
			String parsingDataText, String chartNoDataText,
			String renderingChartText, String loadDataErrorText,
			String invalidXMLText, String chartSWF, String chartId,
			String chartWidth, String chartHeight, boolean debugMode,
			boolean regWithJS, Document dataXml) {
		super();
		pBarLoadingText = barLoadingText;
		this.xmlLoadingText = xmlLoadingText;
		this.parsingDataText = parsingDataText;
		this.chartNoDataText = chartNoDataText;
		this.renderingChartText = renderingChartText;
		this.loadDataErrorText = loadDataErrorText;
		this.invalidXMLText = invalidXMLText;
		this.chartSWF = chartSWF;
		this.chartId = chartId;
		this.chartWidth = chartWidth;
		this.chartHeight = chartHeight;
		this.debugMode = debugMode;
		this.regWithJS = regWithJS;
		this.dataXml = dataXml;
	}
	
	/**
	 * 生成图表
	 * @param links 如果图表中有链接的话,把所有的链接都存放在这个List中,但在dataXml中会有这样的设置
	 *              bnlj_set.addAttribute("link", "javascript:openkh(url"+i+")");
	 *              说明:i表示循环的值,如果是柱状图,那么表示第一个柱子对应着List中的第一个url,这样就会生成多个javascript中的代码，
	 *              如：var url='http://localhost:8080/test.jsp...';
	 * @param function javascript的方法，由于使用了links，会调用"javascript:openkh(url)"这样的方法，所以要在页面写出openkh(url)这个方法
	 * 				如：function openkh(){window.open('kh.jsp',null,'height=400,width=640,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes')}
	 * @return
	 */
	public String createChart(List links,String function) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='" + this.chartId + "Div' align='center'>");
		sb.append("<script type=\"text/javascript\">");
		if(links!=null&&links.size()>0) {
			for(int i=0;i<links.size();i++) {
				sb.append("var url"+i+" = '"+links.get(i).toString()+"';");
			}
		}
		if(function!=null) {
			sb.append(function);
		}
		String strDebugMode = "0";
		strDebugMode = CommonMethod.convertBooleanToString(new Boolean(this.debugMode));
		String strRegWithJS = "0";
		strRegWithJS = CommonMethod.convertBooleanToString(new Boolean(this.regWithJS));
		sb.append("var chart = new FusionCharts('" + this.chartSWF);
		sb.append("?PBarLoadingText=" + this.pBarLoadingText);
		sb.append("&XMLLoadingText=" + this.xmlLoadingText);
		sb.append("&ParsingDataText=" + this.parsingDataText);
		sb.append("&ChartNoDataText=" + this.chartNoDataText);
		sb.append("&RenderingChartText=" + this.renderingChartText);
		sb.append("&LoadDataErrorText=" + this.loadDataErrorText);
		sb.append("&InvalidXMLText=" + this.invalidXMLText);
		sb.append("','" + this.chartId + "','" + this.chartWidth + "','" + this.chartHeight +"','" + strDebugMode + "','"+ strRegWithJS +"');");
		String strXml = this.dataXml.asXML(); 
		strXml = strXml.substring(strXml.indexOf("<chart"));
		strXml = CommonMethod.replaceSpecialCharacters(strXml);
		sb.append("chart.setDataXML(\"" + strXml.replaceAll("\"", "'") + "\");");
		sb.append("chart.render(\"" + this.chartId + "Div\");");
		sb.append("</script>");
		sb.append("</div>");
		String script = sb.toString();
		return script;
	}
	
	
	public String createChart(List links) {
		return this.createChart(links, null);
	}
	
	public String createChart() {
		return this.createChart(null);
	}

	public String getPBarLoadingText() {
		return pBarLoadingText;
	}

	public void setPBarLoadingText(String barLoadingText) {
		pBarLoadingText = barLoadingText;
	}

	public String getXmlLoadingText() {
		return xmlLoadingText;
	}

	public void setXmlLoadingText(String xmlLoadingText) {
		this.xmlLoadingText = xmlLoadingText;
	}

	public String getParsingDataText() {
		return parsingDataText;
	}

	public void setParsingDataText(String parsingDataText) {
		this.parsingDataText = parsingDataText;
	}

	public String getChartNoDataText() {
		return chartNoDataText;
	}

	public void setChartNoDataText(String chartNoDataText) {
		this.chartNoDataText = chartNoDataText;
	}

	public String getRenderingChartText() {
		return renderingChartText;
	}

	public void setRenderingChartText(String renderingChartText) {
		this.renderingChartText = renderingChartText;
	}

	public String getLoadDataErrorText() {
		return loadDataErrorText;
	}

	public void setLoadDataErrorText(String loadDataErrorText) {
		this.loadDataErrorText = loadDataErrorText;
	}

	public String getInvalidXMLText() {
		return invalidXMLText;
	}

	public void setInvalidXMLText(String invalidXMLText) {
		this.invalidXMLText = invalidXMLText;
	}

	public String getChartSWF() {
		return chartSWF;
	}

	public void setChartSWF(String chartSWF) {
		this.chartSWF = chartSWF;
	}

	public String getChartId() {
		return chartId;
	}

	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	public String getChartWidth() {
		return chartWidth;
	}

	public void setChartWidth(String chartWidth) {
		this.chartWidth = chartWidth;
	}

	public String getChartHeight() {
		return chartHeight;
	}

	public void setChartHeight(String chartHeight) {
		this.chartHeight = chartHeight;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean isRegWithJS() {
		return regWithJS;
	}

	public void setRegWithJS(boolean regWithJS) {
		this.regWithJS = regWithJS;
	}

	public Document getDataXml() {
		return dataXml;
	}

	public void setDataXml(Document dataXml) {
		this.dataXml = dataXml;
	}
	
	
}
