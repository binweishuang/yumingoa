package kdf.chart.singleSeries.line;

import java.util.Iterator;
import java.util.List;

import kdf.chart.singleSeries.line.chart.Anchors;
import kdf.chart.singleSeries.line.chart.ChartCosmetics;
import kdf.chart.singleSeries.line.chart.ChartPadding;
import kdf.chart.singleSeries.line.chart.ChartTitles;
import kdf.chart.singleSeries.line.chart.DivisionalLines;
import kdf.chart.singleSeries.line.chart.FunctionalAttributes;
import kdf.chart.singleSeries.line.chart.Tooltip;
import kdf.chart.singleSeries.line.data.DataPlotCosmetics;
import kdf.chart.singleSeries.line.data.Set;
import kdf.chart.singleSeries.line.others.FontProperties;
import kdf.chart.singleSeries.line.others.NumberFormatting;
import kdf.chart.singleSeries.line.others.TrendLines;
import kdf.chart.singleSeries.line.others.VerticalLines;
import kdf.chart.util.CommonMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class PlanLineChart {
	private FunctionalAttributes functionalAttributes;
	private ChartCosmetics chartCosmetics;
	private ChartPadding chartPadding;
	private ChartTitles chartTitles;
	private DataPlotCosmetics dataPlotCosmetics;
	private DivisionalLines divisionalLines;
	private FontProperties fontProperties;
	private NumberFormatting numberFormatting;
	private Tooltip tooltip;
	private TrendLines trendLines;
	private Anchors anchors;
	
	public PlanLineChart() {
		functionalAttributes = new FunctionalAttributes();
		functionalAttributes.setShowValues(new Boolean(true));
		functionalAttributes.setShowLabels(new Boolean(true));
		functionalAttributes.setRotateYAxisName(new Boolean(false));
		functionalAttributes.setPalette(new Integer(2));
		chartCosmetics = new ChartCosmetics();
		chartCosmetics.setCanvasBorderColor("666666");
		chartPadding = null;
		chartTitles = new ChartTitles();
		chartTitles.setCaption("ͼ��1");
		chartTitles.setXAxisName("X��");
		chartTitles.setYAxisName("Y��");
		divisionalLines = new DivisionalLines();
		divisionalLines.setAlternateHGridColor("FCB541");
		divisionalLines.setAlternateHGridAlpha(new Integer(20));
		divisionalLines.setDivLineColor("FCB541");
		divisionalLines.setDivLineAlpha(new Integer(50));
		fontProperties = new FontProperties();
		fontProperties.setBaseFontColor("666666");
		numberFormatting = new NumberFormatting();
		numberFormatting.setDecimals(new Integer(0));
		numberFormatting.setFormatNumberScale(new Boolean(false));
		tooltip = null;
		trendLines = null;
		dataPlotCosmetics = new DataPlotCosmetics();
		dataPlotCosmetics.setLineColor("FCB541");
	}

	public PlanLineChart(FunctionalAttributes functionalAttributes,
			ChartCosmetics chartCosmetics, ChartPadding chartPadding,
			ChartTitles chartTitles, DataPlotCosmetics dataPlotCosmetics,
			DivisionalLines divisionalLines, FontProperties fontProperties,
			NumberFormatting numberFormatting, Tooltip tooltip,
			TrendLines trendLines, Anchors anchors) {
		super();
		this.functionalAttributes = functionalAttributes;
		this.chartCosmetics = chartCosmetics;
		this.chartPadding = chartPadding;
		this.chartTitles = chartTitles;
		this.dataPlotCosmetics = dataPlotCosmetics;
		this.divisionalLines = divisionalLines;
		this.fontProperties = fontProperties;
		this.numberFormatting = numberFormatting;
		this.tooltip = tooltip;
		this.trendLines = trendLines;
		this.anchors = anchors;
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
			if(null!=functionalAttributes.getShowLabels())
				chart.addAttribute("showLabels", CommonMethod.convertBooleanToString(functionalAttributes.getShowLabels()));
			if(null!=functionalAttributes.getLabelDisplay())
				chart.addAttribute("labelDisplay", functionalAttributes.getLabelDisplay());
			if(null!=functionalAttributes.getRotateLabels())
				chart.addAttribute("rotateLabels", CommonMethod.convertBooleanToString(functionalAttributes.getRotateLabels()));
			if(null!=functionalAttributes.getSlantLabels())
				chart.addAttribute("slantLabels", CommonMethod.convertBooleanToString(functionalAttributes.getSlantLabels()));
			if(null!=functionalAttributes.getLabelStep())
				chart.addAttribute("labelStep", CommonMethod.convertIntegerToString(functionalAttributes.getLabelStep()));
			if(null!=functionalAttributes.getStaggerLines())
				chart.addAttribute("staggerLines", CommonMethod.convertIntegerToString(functionalAttributes.getStaggerLines()));
			if(null!=functionalAttributes.getShowValues())
				chart.addAttribute("showValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowValues()));
			if(null!=functionalAttributes.getRotateValues())
				chart.addAttribute("rotateValues", CommonMethod.convertBooleanToString(functionalAttributes.getRotateValues()));
			if(null!=functionalAttributes.getConnectNullData())
				chart.addAttribute("connectNullData", CommonMethod.convertBooleanToString(functionalAttributes.getConnectNullData()));
			if(null!=functionalAttributes.getShowYAxisValues())
				chart.addAttribute("showYAxisValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowYAxisValues()));
			if(null!=functionalAttributes.getShowLimits())
				chart.addAttribute("showLimits", CommonMethod.convertBooleanToString(functionalAttributes.getShowLimits()));
			if(null!=functionalAttributes.getShowDivLineValues())
				chart.addAttribute("showDivLineValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowDivLineValues()));
			if(null!=functionalAttributes.getYAxisValuesStep())
				chart.addAttribute("yAxisValuesStep", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisValuesStep()));
			if(null!=functionalAttributes.getAdjustDiv()) 
				chart.addAttribute("adjustDiv", CommonMethod.convertBooleanToString(functionalAttributes.getAdjustDiv()));
			if(null!=functionalAttributes.getRotateYAxisName()) 
				chart.addAttribute("rotateYAxisName", CommonMethod.convertBooleanToString(functionalAttributes.getRotateYAxisName()));
			if(null!=functionalAttributes.getYAxisNameWidth())
				chart.addAttribute("yAxisNameWidth", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisNameWidth()));		
			if(null!=functionalAttributes.getClickURL()) 
				chart.addAttribute("clickURL",functionalAttributes.getClickURL());
			if(null!=functionalAttributes.getDefaultAnimation()) 
				chart.addAttribute("defaultAnimation", CommonMethod.convertBooleanToString(functionalAttributes.getDefaultAnimation()));
			if(null!=functionalAttributes.getYAxisMinValue())
				chart.addAttribute("yAxisMinValue", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisMinValue()));
			if(null!=functionalAttributes.getYAxisMaxValue())
				chart.addAttribute("yAxisMaxValue ", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisMaxValue()));
			if(null!=functionalAttributes.getSetAdaptiveYMin()) 
				chart.addAttribute("setAdaptiveYMin", CommonMethod.convertBooleanToString(functionalAttributes.getSetAdaptiveYMin()));
		}
		//chartTitles
		if(null!=chartTitles) {
			if(null!=chartTitles.getCaption()) 
				chart.addAttribute("caption",chartTitles.getCaption());
			if(null!=chartTitles.getSubCaption()) 
				chart.addAttribute("subCaption",chartTitles.getSubCaption());
			if(null!=chartTitles.getXAxisName()) 
				chart.addAttribute("xAxisName",chartTitles.getXAxisName());
			if(null!=chartTitles.getYAxisName()) 
				chart.addAttribute("yAxisName",chartTitles.getYAxisName());
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
			if(null!=chartCosmetics.getCanvasBgColor()) 
				chart.addAttribute("canvasBgColor",chartCosmetics.getCanvasBgColor());
			if(null!=chartCosmetics.getCanvasBgAlpha()) 
				chart.addAttribute("canvasBgAlpha",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBgAlpha()));
			if(null!=chartCosmetics.getCanvasBgRatio()) 
				chart.addAttribute("canvasBgRatio",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBgRatio()));
			if(null!=chartCosmetics.getCanvasBgAngle()) 
				chart.addAttribute("canvasBgAngle",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBgAngle()));
			if(null!=chartCosmetics.getCanvasBorderColor()) 
				chart.addAttribute("canvasBorderColor",chartCosmetics.getCanvasBorderColor());
			if(null!=chartCosmetics.getCanvasBorderThickness()) 
				chart.addAttribute("canvasBorderThickness",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBorderThickness()));
			if(null!=chartCosmetics.getCanvasBorderAlpha()) 
				chart.addAttribute("canvasBorderAlpha",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBorderAlpha()));
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
			if(null!=dataPlotCosmetics.getShowShadow()) 
				chart.addAttribute("showShadow",CommonMethod.convertBooleanToString(dataPlotCosmetics.getShowShadow()));
			if(null!=dataPlotCosmetics.getLineColor()) 
				chart.addAttribute("lineColor",dataPlotCosmetics.getLineColor());
			if(null!=dataPlotCosmetics.getLineThickness()) 
				chart.addAttribute("lineThickness",CommonMethod.convertIntegerToString(dataPlotCosmetics.getLineThickness()));
			if(null!=dataPlotCosmetics.getLineAlpha()) 
				chart.addAttribute("lineAlpha",CommonMethod.convertIntegerToString(dataPlotCosmetics.getLineAlpha()));
			if(null!=dataPlotCosmetics.getLineDashed()) 
				chart.addAttribute("lineDashed",CommonMethod.convertBooleanToString(dataPlotCosmetics.getLineDashed()));
			if(null!=dataPlotCosmetics.getLineDashLen()) 
				chart.addAttribute("lineDashLen",CommonMethod.convertIntegerToString(dataPlotCosmetics.getLineDashLen()));
			if(null!=dataPlotCosmetics.getLineDashGap()) 
				chart.addAttribute("lineDashGap",CommonMethod.convertIntegerToString(dataPlotCosmetics.getLineDashGap()));
		}
		//anchors
		if(null!=anchors) {
			if(null!=anchors.getDrawAnchors())
				chart.addAttribute("drawAnchors", CommonMethod.convertBooleanToString(anchors.getDrawAnchors()));
			if(null!=anchors.getAnchorSides())
				chart.addAttribute("anchorSides", CommonMethod.convertIntegerToString(anchors.getAnchorSides()));
			if(null!=anchors.getAnchorRadius())
				chart.addAttribute("anchorRadius", CommonMethod.convertIntegerToString(anchors.getAnchorRadius()));
			if(null!=anchors.getAnchorBorderColor())
				chart.addAttribute("anchorBorderColor", anchors.getAnchorBorderColor());
			if(null!=anchors.getAnchorBorderThickness())
				chart.addAttribute("anchorBorderThickness", CommonMethod.convertIntegerToString(anchors.getAnchorBorderThickness()));
			if(null!=anchors.getAnchorBgColor())
				chart.addAttribute("anchorBgColor", anchors.getAnchorBgColor());
			if(null!=anchors.getAnchorAlpha())
				chart.addAttribute("anchorAlpha", CommonMethod.convertIntegerToString(anchors.getAnchorAlpha()));
			if(null!=anchors.getAnchorBgAlpha())
				chart.addAttribute("anchorBgAlpha", CommonMethod.convertIntegerToString(anchors.getAnchorBgAlpha()));
		}
		//divisionalLines
		if(null!=divisionalLines) {
			if(null!=divisionalLines.getNumDivLines()) 
				chart.addAttribute("numDivLines",CommonMethod.convertIntegerToString(divisionalLines.getNumDivLines()));
			if(null!=divisionalLines.getDivLineColor()) 
				chart.addAttribute("divLineColor",divisionalLines.getDivLineColor());
			if(null!=divisionalLines.getDivLineThickness()) 
				chart.addAttribute("divLineThickness",CommonMethod.convertIntegerToString(divisionalLines.getDivLineThickness()));
			if(null!=divisionalLines.getDivLineAlpha()) 
				chart.addAttribute("divLineAlpha",CommonMethod.convertIntegerToString(divisionalLines.getDivLineAlpha()));
			if(null!=divisionalLines.getDivLineIsDashed()) 
				chart.addAttribute("divLineIsDashed",CommonMethod.convertBooleanToString(divisionalLines.getDivLineIsDashed()));
			if(null!=divisionalLines.getDivLineDashLen()) 
				chart.addAttribute("divLineDashLen",CommonMethod.convertIntegerToString(divisionalLines.getDivLineDashLen()));
			if(null!=divisionalLines.getDivLineDashGap()) 
				chart.addAttribute("divLineDashGap",CommonMethod.convertIntegerToString(divisionalLines.getDivLineDashGap()));
			if(null!=divisionalLines.getZeroPlaneColor()) 
				chart.addAttribute("zeroPlaneColor",divisionalLines.getZeroPlaneColor());
			if(null!=divisionalLines.getZeroPlaneThickness()) 
				chart.addAttribute("zeroPlaneThickness",CommonMethod.convertIntegerToString(divisionalLines.getZeroPlaneThickness()));
			if(null!=divisionalLines.getZeroPlaneAlpha()) 
				chart.addAttribute("zeroPlaneAlpha",CommonMethod.convertIntegerToString(divisionalLines.getZeroPlaneAlpha()));
			if(null!=divisionalLines.getShowAlternateHGridColor()) 
				chart.addAttribute("showAlternateHGridColor",CommonMethod.convertBooleanToString(divisionalLines.getShowAlternateHGridColor()));
			if(null!=divisionalLines.getAlternateHGridColor()) 
				chart.addAttribute("alternateHGridColor",divisionalLines.getAlternateHGridColor());
			if(null!=divisionalLines.getAlternateHGridAlpha()) 
				chart.addAttribute("alternateHGridAlpha",CommonMethod.convertIntegerToString(divisionalLines.getAlternateHGridAlpha()));
			if(null!=divisionalLines.getNumVDivLines()) 
				chart.addAttribute("numVDivLines",CommonMethod.convertIntegerToString(divisionalLines.getNumVDivLines()));
			if(null!=divisionalLines.getVDivLineColor()) 
				chart.addAttribute("vDivLineColor",divisionalLines.getVDivLineColor());
			if(null!=divisionalLines.getVDivLineThickness()) 
				chart.addAttribute("vDivLineThickness",CommonMethod.convertIntegerToString(divisionalLines.getVDivLineThickness()));
			if(null!=divisionalLines.getVDivLineAlpha()) 
				chart.addAttribute("vDivLineAlpha",CommonMethod.convertIntegerToString(divisionalLines.getVDivLineAlpha()));
			if(null!=divisionalLines.getVDivLineIsDashed()) 
				chart.addAttribute("vDivLineIsDashed",CommonMethod.convertBooleanToString(divisionalLines.getVDivLineIsDashed()));
			if(null!=divisionalLines.getVDivLineDashLen()) 
				chart.addAttribute("vDivLineDashLen",CommonMethod.convertIntegerToString(divisionalLines.getVDivLineDashLen()));
			if(null!=divisionalLines.getVDivLineDashGap()) 
				chart.addAttribute("vDivLineDashGap",CommonMethod.convertIntegerToString(divisionalLines.getVDivLineDashGap()));
			if(null!=divisionalLines.getShowAlternateVGridColor()) 
				chart.addAttribute("showAlternateVGridColor",CommonMethod.convertBooleanToString(divisionalLines.getShowAlternateVGridColor()));
			if(null!=divisionalLines.getAlternateVGridColor()) 
				chart.addAttribute("alternateVGridColor",divisionalLines.getAlternateVGridColor());
			if(null!=divisionalLines.getAlternateVGridAlpha()) 
				chart.addAttribute("alternateVGridAlpha",CommonMethod.convertIntegerToString(divisionalLines.getAlternateVGridAlpha()));
			if(null!=divisionalLines.getShowZeroPlane()) 
				chart.addAttribute("showZeroPlane",CommonMethod.convertBooleanToString(divisionalLines.getShowZeroPlane()));
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
			if(null!=numberFormatting.getYAxisValueDecimals()) 
				chart.addAttribute("yAxisValueDecimals",CommonMethod.convertIntegerToString(numberFormatting.getYAxisValueDecimals()));
		}
		//fontProperties
		if(null!=fontProperties) {
			if(null!=fontProperties.getBaseFont()) 
				chart.addAttribute("baseFont",fontProperties.getBaseFont());
			if(null!=fontProperties.getBaseFontSize()) 
				chart.addAttribute("baseFontSize",CommonMethod.convertIntegerToString(fontProperties.getBaseFontSize()));
			if(null!=fontProperties.getBaseFontColor()) 
				chart.addAttribute("baseFontColor",fontProperties.getBaseFontColor());
			if(null!=fontProperties.getOutCnvBaseFont()) 
				chart.addAttribute("outCnvBaseFont",fontProperties.getOutCnvBaseFont());
			if(null!=fontProperties.getOutCnvBaseFontSize()) 
				chart.addAttribute("outCnvBaseFontSize",CommonMethod.convertIntegerToString(fontProperties.getOutCnvBaseFontSize()));
			if(null!=fontProperties.getOutCnvBaseFontColor()) 
				chart.addAttribute("outCnvBaseFontColor",fontProperties.getOutCnvBaseFontColor());
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
			if(null!=chartPadding.getCanvasPadding()) 
				chart.addAttribute("canvasPadding",CommonMethod.convertIntegerToString(chartPadding.getCanvasPadding()));
			if(null!=chartPadding.getCaptionPadding()) 
				chart.addAttribute("captionPadding",CommonMethod.convertIntegerToString(chartPadding.getCaptionPadding()));
			if(null!=chartPadding.getXAxisNamePadding()) 
				chart.addAttribute("xAxisNamePadding",CommonMethod.convertIntegerToString(chartPadding.getXAxisNamePadding()));
			if(null!=chartPadding.getYAxisNamePadding()) 
				chart.addAttribute("yAxisNamePadding",CommonMethod.convertIntegerToString(chartPadding.getYAxisNamePadding()));
			if(null!=chartPadding.getYAxisValuesPadding()) 
				chart.addAttribute("yAxisValuesPadding",CommonMethod.convertIntegerToString(chartPadding.getYAxisValuesPadding()));
			if(null!=chartPadding.getLabelPadding()) 
				chart.addAttribute("labelPadding",CommonMethod.convertIntegerToString(chartPadding.getLabelPadding()));
			if(null!=chartPadding.getValuePadding()) 
				chart.addAttribute("valuePadding",CommonMethod.convertIntegerToString(chartPadding.getValuePadding()));			
			if(null!=chartPadding.getChartLeftMargin()) 
				chart.addAttribute("chartLeftMargin",CommonMethod.convertIntegerToString(chartPadding.getChartLeftMargin()));
			if(null!=chartPadding.getChartRightMargin()) 
				chart.addAttribute("chartRightMargin",CommonMethod.convertIntegerToString(chartPadding.getChartRightMargin()));
			if(null!=chartPadding.getChartTopMargin()) 
				chart.addAttribute("chartTopMargin",CommonMethod.convertIntegerToString(chartPadding.getChartTopMargin()));
			if(null!=chartPadding.getChartBottomMargin()) 
				chart.addAttribute("chartBottomMargin",CommonMethod.convertIntegerToString(chartPadding.getChartBottomMargin()));
		}
		//treadLines
		if(null!=trendLines) {
			Element trendLines_el = chart.addElement("trendLines");
			Element line = trendLines_el.addElement("line");
			if(null!=trendLines.getStartValue())
				line.addAttribute("startValue", CommonMethod.convertDoubleToString(trendLines.getStartValue()));
			if(null!=trendLines.getEndValue())
				line.addAttribute("endValue", CommonMethod.convertDoubleToString(trendLines.getEndValue()));
			if(null!=trendLines.getDisplayValue())
				line.addAttribute("displayValue", trendLines.getDisplayValue());
			if(null!=trendLines.getColor())
				line.addAttribute("color", trendLines.getColor());
			if(null!=trendLines.getIsTrendZone())
				line.addAttribute("isTrendZone", CommonMethod.convertBooleanToString(trendLines.getIsTrendZone()));
			if(null!=trendLines.getShowOnTop())
				line.addAttribute("showOnTop", CommonMethod.convertBooleanToString(trendLines.getShowOnTop()));
			if(null!=trendLines.getThickness())
				line.addAttribute("thickness", CommonMethod.convertIntegerToString(trendLines.getThickness()));
			if(null!=trendLines.getAlpha())
				line.addAttribute("alpha", CommonMethod.convertIntegerToString(trendLines.getAlpha()));
			if(null!=trendLines.getDashed())
				line.addAttribute("dashed", CommonMethod.convertBooleanToString(trendLines.getDashed()));
			if(null!=trendLines.getDashLen())
				line.addAttribute("dashLen", CommonMethod.convertIntegerToString(trendLines.getDashLen()));
			if(null!=trendLines.getDashGap())
				line.addAttribute("dashGap", CommonMethod.convertIntegerToString(trendLines.getDashGap()));
			if(null!=trendLines.getValueOnRight())
				line.addAttribute("valueOnRight", CommonMethod.convertBooleanToString(trendLines.getValueOnRight()));
		}
		
		//set
		if(null!=datas&&0<datas.size()) {
			for(Iterator iter = datas.iterator();iter.hasNext();) {	
				Object obj = iter.next();
				if(obj instanceof VerticalLines) {
					Element vLine = chart.addElement("vLine");
					VerticalLines vlineobj = (VerticalLines)obj;
					if(null!=vlineobj.getColor())
						vLine.addAttribute("color", vlineobj.getColor());
					if(null!=vlineobj.getThickness())
						vLine.addAttribute("thickness", CommonMethod.convertIntegerToString(vlineobj.getThickness()));
					if(null!=vlineobj.getAlpha())
						vLine.addAttribute("alpha", CommonMethod.convertIntegerToString(vlineobj.getAlpha()));
					if(null!=vlineobj.getDashed())
						vLine.addAttribute("dashed", CommonMethod.convertBooleanToString(vlineobj.getDashed()));
					if(null!=vlineobj.getDashLen())
						vLine.addAttribute("dashLen", CommonMethod.convertIntegerToString(vlineobj.getDashLen()));
					if(null!=vlineobj.getDashGap())
						vLine.addAttribute("dashGap", CommonMethod.convertIntegerToString(vlineobj.getDashGap()));
				} else {
					Element set = chart.addElement("set");
					Set setobj = (Set)obj;
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
					if(null!=setobj.getShowLabel())
						set.addAttribute("showLabel", CommonMethod.convertBooleanToString(setobj.getShowLabel()));
					if(null!=setobj.getShowValue())
						set.addAttribute("showValue", CommonMethod.convertBooleanToString(setobj.getShowValue()));
					if(null!=setobj.getDashed())
						set.addAttribute("dashed", CommonMethod.convertBooleanToString(setobj.getDashed()));
					if(null!=setobj.getAlpha())
						set.addAttribute("alpha", CommonMethod.convertIntegerToString(setobj.getAlpha()));
					if(null!=setobj.getAnchorSides())
						set.addAttribute("anchorSides", CommonMethod.convertIntegerToString(setobj.getAnchorSides()));
					if(null!=setobj.getAnchorRadius())
						set.addAttribute("anchorRadius", CommonMethod.convertIntegerToString(setobj.getAnchorRadius()));
					if(null!=setobj.getAnchorBorderColor())
						set.addAttribute("anchorBorderColor", setobj.getAnchorBorderColor());
					if(null!=setobj.getAnchorBorderThickness())
						set.addAttribute("anchorBorderThickness", CommonMethod.convertIntegerToString(setobj.getAnchorBorderThickness()));
					if(null!=setobj.getAnchorBgColor())
						set.addAttribute("anchorBgColor", setobj.getAnchorBgColor());
					if(null!=setobj.getAnchorAlpha())
						set.addAttribute("anchorAlpha", CommonMethod.convertIntegerToString(setobj.getAnchorAlpha()));
					if(null!=setobj.getAnchorBgAlpha())
						set.addAttribute("anchorBgAlpha", CommonMethod.convertIntegerToString(setobj.getAnchorBgAlpha()));
				}
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

	public ChartCosmetics getChartCosmetics() {
		return chartCosmetics;
	}

	public void setChartCosmetics(ChartCosmetics chartCosmetics) {
		this.chartCosmetics = chartCosmetics;
	}

	public ChartPadding getChartPadding() {
		return chartPadding;
	}

	public void setChartPadding(ChartPadding chartPadding) {
		this.chartPadding = chartPadding;
	}

	public ChartTitles getChartTitles() {
		return chartTitles;
	}

	public void setChartTitles(ChartTitles chartTitles) {
		this.chartTitles = chartTitles;
	}

	public DataPlotCosmetics getDataPlotCosmetics() {
		return dataPlotCosmetics;
	}

	public void setDataPlotCosmetics(DataPlotCosmetics dataPlotCosmetics) {
		this.dataPlotCosmetics = dataPlotCosmetics;
	}

	public DivisionalLines getDivisionalLines() {
		return divisionalLines;
	}

	public void setDivisionalLines(DivisionalLines divisionalLines) {
		this.divisionalLines = divisionalLines;
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

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public TrendLines getTrendLines() {
		return trendLines;
	}

	public void setTrendLines(TrendLines trendLines) {
		this.trendLines = trendLines;
	}

	public Anchors getAnchors() {
		return anchors;
	}

	public void setAnchors(Anchors anchors) {
		this.anchors = anchors;
	}
	
}
