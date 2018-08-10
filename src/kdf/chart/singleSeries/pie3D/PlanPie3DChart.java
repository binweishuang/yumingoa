package kdf.chart.singleSeries.pie3D;

import java.util.Iterator;
import java.util.List;

import kdf.chart.singleSeries.pie3D.chart.ChartCosmetics;
import kdf.chart.singleSeries.pie3D.chart.ChartPadding;
import kdf.chart.singleSeries.pie3D.chart.ChartTitles;
import kdf.chart.singleSeries.pie3D.chart.FunctionalAttributes;
import kdf.chart.singleSeries.pie3D.chart.Tooltip;
import kdf.chart.singleSeries.pie3D.data.DataPlotCosmetics;
import kdf.chart.singleSeries.pie3D.data.PieProperties;
import kdf.chart.singleSeries.pie3D.data.Set;
import kdf.chart.singleSeries.pie3D.data.SmartLines;
import kdf.chart.singleSeries.pie3D.others.FontProperties;
import kdf.chart.singleSeries.pie3D.others.NumberFormatting;
import kdf.chart.util.CommonMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class PlanPie3DChart {
	private FunctionalAttributes functionalAttributes;
	private ChartTitles chartTitles;
	private ChartCosmetics chartCosmetics;
	private Tooltip tooltip;
	private ChartPadding chartPadding;	
	private DataPlotCosmetics dataPlotCosmetics;
	private PieProperties pieProperties;
	private SmartLines smartLines;
	private FontProperties fontProperties;
	private NumberFormatting numberFormatting;
	
	public PlanPie3DChart() {
		functionalAttributes = new FunctionalAttributes();
		functionalAttributes.setShowValues(new Boolean(true));
		functionalAttributes.setShowLabels(new Boolean(true));
		functionalAttributes.setShowPercentValues(new Boolean(true));
		functionalAttributes.setPalette(new Integer(4));
		chartTitles = new ChartTitles();
		chartTitles.setCaption("ͼ��1");
		chartCosmetics = new ChartCosmetics();
		chartCosmetics.setBgColor("99CCFF,FFFFFF");
		chartCosmetics.setBgAlpha("40,100");
		chartCosmetics.setBgRatio("0,100");
		chartCosmetics.setBgAngle(new Integer(360));
		chartCosmetics.setShowBorder(new Boolean(true));
		tooltip = null;
		chartPadding = null;
		dataPlotCosmetics = null;
		pieProperties = new PieProperties();
		pieProperties.setEnableRotation(new Boolean(false));
		pieProperties.setStartingAngle(new Integer(70));
		smartLines = new SmartLines();
		smartLines.setEnableSmartLabels(new Boolean(true));
		fontProperties=null;
		numberFormatting = new NumberFormatting();
		numberFormatting.setDecimals(new Integer(0));
		numberFormatting.setFormatNumberScale(new Boolean(false));
	}

	public PlanPie3DChart(FunctionalAttributes functionalAttributes,
			ChartTitles chartTitles, ChartCosmetics chartCosmetics,
			Tooltip tooltip, ChartPadding chartPadding,
			DataPlotCosmetics dataPlotCosmetics, PieProperties pieProperties,
			SmartLines smartLines, FontProperties fontProperties,
			NumberFormatting numberFormatting) {
		super();
		this.functionalAttributes = functionalAttributes;
		this.chartTitles = chartTitles;
		this.chartCosmetics = chartCosmetics;
		this.tooltip = tooltip;
		this.chartPadding = chartPadding;
		this.dataPlotCosmetics = dataPlotCosmetics;
		this.pieProperties = pieProperties;
		this.smartLines = smartLines;
		this.fontProperties = fontProperties;
		this.numberFormatting = numberFormatting;
	}
	
	public Document buildXml(List datas) {
		Document doc = DocumentHelper.createDocument();
		Element chart = doc.addElement("chart");
		//functionalAttributes
		if(null!=functionalAttributes) {
			if(null!=functionalAttributes.getAnimation()) 
				chart.addAttribute("animation", CommonMethod.convertBooleanToString(functionalAttributes.getAnimation()));
			if(null!=functionalAttributes.getPalette())
				chart.addAttribute("palette", CommonMethod.convertIntegerToString(functionalAttributes.getPalette()));
			if(null!=functionalAttributes.getShowZeroPies()) 
				chart.addAttribute("showZeroPies", CommonMethod.convertBooleanToString(functionalAttributes.getShowZeroPies()));
			if(null!=functionalAttributes.getShowPercentValues()) 
				chart.addAttribute("showPercentValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowPercentValues()));
			if(null!=functionalAttributes.getShowPercentInToolTip()) 
				chart.addAttribute("showPercentInToolTip", CommonMethod.convertBooleanToString(functionalAttributes.getShowPercentInToolTip()));
			if(null!=functionalAttributes.getShowLabels())
				chart.addAttribute("showLabels", CommonMethod.convertBooleanToString(functionalAttributes.getShowLabels()));
			if(null!=functionalAttributes.getShowValues())
				chart.addAttribute("showValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowValues()));		
			if(null!=functionalAttributes.getClickURL()) 
				chart.addAttribute("clickURL",functionalAttributes.getClickURL());
			if(null!=functionalAttributes.getDefaultAnimation()) 
				chart.addAttribute("defaultAnimation", CommonMethod.convertBooleanToString(functionalAttributes.getDefaultAnimation()));
		}
		//chartTitles
		if(null!=chartTitles) {
			if(null!=chartTitles.getCaption()) 
				chart.addAttribute("caption",chartTitles.getCaption());
			if(null!=chartTitles.getSubCaption()) 
				chart.addAttribute("subCaption",chartTitles.getSubCaption());
		}
		//chartCosmetics
		if(null!=chartCosmetics) {
			if(null!=chartCosmetics.getBgColor()) 
				chart.addAttribute("bgColor",chartCosmetics.getBgColor());
			if(null!=chartCosmetics.getBgAlpha()) 
				chart.addAttribute("bgAlpha",chartCosmetics.getBgAlpha());
			if(null!=chartCosmetics.getBgRatio()) 
				chart.addAttribute("bgRatio",chartCosmetics.getBgRatio());
			if(null!=chartCosmetics.getBgAngle()) 
				chart.addAttribute("bgAngle",CommonMethod.convertIntegerToString(chartCosmetics.getBgAngle()));
			if(null!=chartCosmetics.getBgSWF()) 
				chart.addAttribute("bgSWF",chartCosmetics.getBgSWF());
			if(null!=chartCosmetics.getBgSWFAlpha()) 
				chart.addAttribute("bgSWFAlpha",CommonMethod.convertIntegerToString(chartCosmetics.getBgSWFAlpha()));
			if(null!=chartCosmetics.getShowBorder()) 
				chart.addAttribute("showBorder",CommonMethod.convertBooleanToString(chartCosmetics.getShowBorder()));
			if(null!=chartCosmetics.getBorderColor()) 
				chart.addAttribute("borderColor",chartCosmetics.getBorderColor());
			if(null!=chartCosmetics.getBorderThickness()) 
				chart.addAttribute("borderThickness",CommonMethod.convertIntegerToString(chartCosmetics.getBorderThickness()));
			if(null!=chartCosmetics.getBorderAlpha()) 
				chart.addAttribute("borderAlpha",CommonMethod.convertIntegerToString(chartCosmetics.getBorderAlpha()));
		}
		//dataPlotCosmetics
		if(null!=dataPlotCosmetics) {
			if(null!=dataPlotCosmetics.getShowPlotBorder()) 
				chart.addAttribute("showPlotBorder",CommonMethod.convertBooleanToString(dataPlotCosmetics.getShowPlotBorder()));
			if(null!=dataPlotCosmetics.getPlotBorderColor()) 
				chart.addAttribute("plotBorderColor",dataPlotCosmetics.getPlotBorderColor());
			if(null!=dataPlotCosmetics.getPlotBorderThickness()) 
				chart.addAttribute("plotBorderThickness",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderThickness()));
			if(null!=dataPlotCosmetics.getPlotBorderAlpha()) 
				chart.addAttribute("plotBorderAlpha",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderAlpha()));
			if(null!=dataPlotCosmetics.getPlotFillAngle()) 
				chart.addAttribute("plotFillAngle",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotFillAngle()));
			if(null!=dataPlotCosmetics.getUse3DLighting()) 
				chart.addAttribute("use3DLighting",CommonMethod.convertBooleanToString(dataPlotCosmetics.getUse3DLighting()));
		}
		//pieProperties
		if(null!=pieProperties) {
			if(null!=pieProperties.getSlicingDistance()) 
				chart.addAttribute("slicingDistance",CommonMethod.convertIntegerToString(pieProperties.getSlicingDistance()));
			if(null!=pieProperties.getPieRadius()) 
				chart.addAttribute("pieRadius",CommonMethod.convertIntegerToString(pieProperties.getPieRadius()));
			if(null!=pieProperties.getStartingAngle()) 
				chart.addAttribute("startingAngle",CommonMethod.convertIntegerToString(pieProperties.getStartingAngle()));
			if(null!=pieProperties.getEnableRotation()) 
				chart.addAttribute("enableRotation",CommonMethod.convertBooleanToString(pieProperties.getEnableRotation()));
			if(null!=pieProperties.getPieInnerFaceAlpha()) 
				chart.addAttribute("pieInnerFaceAlpha",CommonMethod.convertIntegerToString(pieProperties.getPieInnerFaceAlpha()));
			if(null!=pieProperties.getPieOuterFaceAlpha()) 
				chart.addAttribute("pieOuterFaceAlpha",CommonMethod.convertIntegerToString(pieProperties.getPieOuterFaceAlpha()));
			if(null!=pieProperties.getPieYScale()) 
				chart.addAttribute("pieYScale",CommonMethod.convertIntegerToString(pieProperties.getPieYScale()));
			if(null!=pieProperties.getPieSliceDepth()) 
				chart.addAttribute("pieSliceDepth",CommonMethod.convertIntegerToString(pieProperties.getPieSliceDepth()));
		}
		//smartLines
		if(null!=smartLines) {
			if(null!=smartLines.getEnableSmartLabels()) 
				chart.addAttribute("enableSmartLabels",CommonMethod.convertBooleanToString(smartLines.getEnableSmartLabels()));
			if(null!=smartLines.getSkipOverlapLabels()) 
				chart.addAttribute("skipOverlapLabels",CommonMethod.convertBooleanToString(smartLines.getSkipOverlapLabels()));
			if(null!=smartLines.getIsSmartLineSlanted()) 
				chart.addAttribute("isSmartLineSlanted",CommonMethod.convertBooleanToString(smartLines.getIsSmartLineSlanted()));
			if(null!=smartLines.getSmartLineColor()) 
				chart.addAttribute("smartLineColor",smartLines.getSmartLineColor());
			if(null!=smartLines.getSmartLineThickness()) 
				chart.addAttribute("smartLineThickness",CommonMethod.convertIntegerToString(smartLines.getSmartLineThickness()));
			if(null!=smartLines.getSmartLineAlpha()) 
				chart.addAttribute("smartLineAlpha",CommonMethod.convertIntegerToString(smartLines.getSmartLineAlpha()));
			if(null!=smartLines.getLabelDistance()) 
				chart.addAttribute("labelDistance",CommonMethod.convertIntegerToString(smartLines.getLabelDistance()));
			if(null!=smartLines.getSmartLabelClearance()) 
				chart.addAttribute("smartLabelClearance",CommonMethod.convertIntegerToString(smartLines.getSmartLabelClearance()));
		}
		//numberFormatting
		if(null!=numberFormatting) {
			if(null!=numberFormatting.getFormatNumber()) 
				chart.addAttribute("formatNumber",CommonMethod.convertBooleanToString(numberFormatting.getFormatNumber()));
			if(null!=numberFormatting.getFormatNumberScale()) 
				chart.addAttribute("formatNumberScale",CommonMethod.convertBooleanToString(numberFormatting.getFormatNumberScale()));
			if(null!=numberFormatting.getDefaultNumberScale()) 
				chart.addAttribute("defaultNumberScale",numberFormatting.getDefaultNumberScale());
			if(null!=numberFormatting.getNumberScaleUnit()) 
				chart.addAttribute("numberScaleUnit",numberFormatting.getNumberScaleUnit());
			if(null!=numberFormatting.getNumberScaleValue()) 
				chart.addAttribute("numberScaleValue",numberFormatting.getNumberScaleValue());
			if(null!=numberFormatting.getNumberPrefix()) 
				chart.addAttribute("numberPrefix",numberFormatting.getNumberPrefix());
			if(null!=numberFormatting.getNumberSuffix()) 
				chart.addAttribute("numberSuffix",numberFormatting.getNumberSuffix());
			if(null!=numberFormatting.getDecimalSeparator()) 
				chart.addAttribute("decimalSeparator",numberFormatting.getDecimalSeparator());
			if(null!=numberFormatting.getThousandSeparator()) 
				chart.addAttribute("thousandSeparator",numberFormatting.getThousandSeparator());
			if(null!=numberFormatting.getInDecimalSeparator()) 
				chart.addAttribute("inDecimalSeparator",numberFormatting.getInDecimalSeparator());
			if(null!=numberFormatting.getInThousandSeparator()) 
				chart.addAttribute("inThousandSeparator",numberFormatting.getInThousandSeparator());
			if(null!=numberFormatting.getDecimals()) 
				chart.addAttribute("decimals",CommonMethod.convertIntegerToString(numberFormatting.getDecimals()));
			if(null!=numberFormatting.getForceDecimals()) 
				chart.addAttribute("forceDecimals",CommonMethod.convertBooleanToString(numberFormatting.getForceDecimals()));
		}
		//fontProperties
		if(null!=fontProperties) {
			if(null!=fontProperties.getBaseFont()) 
				chart.addAttribute("baseFont",fontProperties.getBaseFont());
			if(null!=fontProperties.getBaseFontSize()) 
				chart.addAttribute("baseFontSize",CommonMethod.convertIntegerToString(fontProperties.getBaseFontSize()));
			if(null!=fontProperties.getBaseFontColor()) 
				chart.addAttribute("baseFontColor",fontProperties.getBaseFontColor());
		}
		//tooltip
		if(null!=tooltip) {
			if(null!=tooltip.getShowToolTip()) 
				chart.addAttribute("showToolTip",CommonMethod.convertBooleanToString(tooltip.getShowToolTip()));
			if(null!=tooltip.getToolTipBgColor()) 
				chart.addAttribute("toolTipBgColor",tooltip.getToolTipBgColor());
			if(null!=tooltip.getToolTipBorderColor()) 
				chart.addAttribute("toolTipBorderColor",tooltip.getToolTipBorderColor());
			if(null!=tooltip.getToolTipSepChar()) 
				chart.addAttribute("toolTipSepChar",tooltip.getToolTipSepChar());
		}
		//chartPadding
		if(null!=chartPadding) {
			if(null!=chartPadding.getCaptionPadding()) 
				chart.addAttribute("captionPadding",CommonMethod.convertIntegerToString(chartPadding.getCaptionPadding()));
			if(null!=chartPadding.getChartLeftMargin()) 
				chart.addAttribute("chartLeftMargin",CommonMethod.convertIntegerToString(chartPadding.getChartLeftMargin()));
			if(null!=chartPadding.getChartRightMargin()) 
				chart.addAttribute("chartRightMargin",CommonMethod.convertIntegerToString(chartPadding.getChartRightMargin()));
			if(null!=chartPadding.getChartTopMargin()) 
				chart.addAttribute("chartTopMargin",CommonMethod.convertIntegerToString(chartPadding.getChartTopMargin()));
			if(null!=chartPadding.getChartBottomMargin()) 
				chart.addAttribute("chartBottomMargin",CommonMethod.convertIntegerToString(chartPadding.getChartBottomMargin()));
		}
		
		//set
		if(null!=datas&&0<datas.size()) {
			for(Iterator iter = datas.iterator();iter.hasNext();) {	
				Object obj = iter.next();
				
				Element set = chart.addElement("set");
				Set setobj = (Set)obj;
				if(null!=setobj.getBorderColor())
					set.addAttribute("borderColor", setobj.getBorderColor());
				if(null!=setobj.getIsSliced())
					set.addAttribute("isSliced", CommonMethod.convertBooleanToString(setobj.getIsSliced()));
				if(null!=setobj.getLabel())
					set.addAttribute("label", setobj.getLabel());
				if(null!=setobj.getValue())
					set.addAttribute("value", CommonMethod.convertDoubleToString(setobj.getValue()));
				if(null!=setobj.getColor())
					set.addAttribute("color", setobj.getColor());
				if(null!=setobj.getLink())
					set.addAttribute("link", setobj.getLink());
				if(null!=setobj.getToolText())
					set.addAttribute("toolText", setobj.getToolText());			
			}
		}
		return doc;
	}

	public FunctionalAttributes getFunctionalAttributes() {
		return functionalAttributes;
	}

	public void setFunctionalAttributes(FunctionalAttributes functionalAttributes) {
		this.functionalAttributes = functionalAttributes;
	}

	public ChartTitles getChartTitles() {
		return chartTitles;
	}

	public void setChartTitles(ChartTitles chartTitles) {
		this.chartTitles = chartTitles;
	}

	public ChartCosmetics getChartCosmetics() {
		return chartCosmetics;
	}

	public void setChartCosmetics(ChartCosmetics chartCosmetics) {
		this.chartCosmetics = chartCosmetics;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public ChartPadding getChartPadding() {
		return chartPadding;
	}

	public void setChartPadding(ChartPadding chartPadding) {
		this.chartPadding = chartPadding;
	}

	public DataPlotCosmetics getDataPlotCosmetics() {
		return dataPlotCosmetics;
	}

	public void setDataPlotCosmetics(DataPlotCosmetics dataPlotCosmetics) {
		this.dataPlotCosmetics = dataPlotCosmetics;
	}

	public PieProperties getPieProperties() {
		return pieProperties;
	}

	public void setPieProperties(PieProperties pieProperties) {
		this.pieProperties = pieProperties;
	}

	public SmartLines getSmartLines() {
		return smartLines;
	}

	public void setSmartLines(SmartLines smartLines) {
		this.smartLines = smartLines;
	}

	public FontProperties getFontProperties() {
		return fontProperties;
	}

	public void setFontProperties(FontProperties fontProperties) {
		this.fontProperties = fontProperties;
	}

	public NumberFormatting getNumberFormatting() {
		return numberFormatting;
	}

	public void setNumberFormatting(NumberFormatting numberFormatting) {
		this.numberFormatting = numberFormatting;
	}
	
}
