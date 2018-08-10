package kdf.chart.combination.dualY2D;

import java.util.Iterator;
import java.util.List;

import kdf.chart.combination.dualY2D.chart.Anchors;
import kdf.chart.combination.dualY2D.chart.ChartCosmetics;
import kdf.chart.combination.dualY2D.chart.ChartPadding;
import kdf.chart.combination.dualY2D.chart.ChartTitles;
import kdf.chart.combination.dualY2D.chart.DivisionalLines;
import kdf.chart.combination.dualY2D.chart.FunctionalAttributes;
import kdf.chart.combination.dualY2D.chart.Legend;
import kdf.chart.combination.dualY2D.chart.Tooltip;
import kdf.chart.combination.dualY2D.data.Categories;
import kdf.chart.combination.dualY2D.data.Category;
import kdf.chart.combination.dualY2D.data.DataPlotCosmetics;
import kdf.chart.combination.dualY2D.data.Dataset;
import kdf.chart.combination.dualY2D.data.Set;
import kdf.chart.combination.dualY2D.others.FontProperties;
import kdf.chart.combination.dualY2D.others.NumberFormatting;
import kdf.chart.combination.dualY2D.others.TrendLines;
import kdf.chart.combination.dualY2D.others.VerticalLines;
import kdf.chart.util.CommonMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Plan2DDualYCombinationChart {
	private FunctionalAttributes functionalAttributes;
	private ChartTitles chartTitles;
	private ChartCosmetics chartCosmetics;
	private Anchors anchors;
	private DivisionalLines divisionalLines;
	private Legend legend;
	private Tooltip tooltip;
	private ChartPadding chartPadding;	
	private DataPlotCosmetics dataPlotCosmetics;
	private NumberFormatting numberFormatting;
	private FontProperties fontProperties;	
	private TrendLines trendLines;
	
	public Plan2DDualYCombinationChart() {
		functionalAttributes = new FunctionalAttributes();
		functionalAttributes.setShowValues(new Boolean(true));
		functionalAttributes.setShowLabels(new Boolean(true));
		functionalAttributes.setRotateYAxisName(new Boolean(false));
		functionalAttributes.setPalette(new Integer(1));
		functionalAttributes.setConnectNullData(new Boolean(false));
		chartTitles = new ChartTitles();
		chartTitles.setCaption("ͼ��1");
		chartTitles.setXAxisName("X��");
		chartTitles.setPYAxisName("��Y��");
		chartTitles.setSYAxisName("��Y��");
		chartCosmetics = null;
		anchors = null;
		divisionalLines = new DivisionalLines();
		divisionalLines.setNumDivLines(new Integer(4));
		divisionalLines.setDivLineDecimalPrecision(new Boolean(true));
		legend = null;
		tooltip = null;
		chartPadding = null;		
		dataPlotCosmetics = null;		
		numberFormatting = new NumberFormatting();
		numberFormatting.setFormatNumberScale(new Boolean(false));
		numberFormatting.setSYAxisValueDecimals(new Integer(2));
		fontProperties = null;		
		trendLines = null;		
	}

	public Plan2DDualYCombinationChart(
			FunctionalAttributes functionalAttributes, ChartTitles chartTitles,
			ChartCosmetics chartCosmetics, Anchors anchors,
			DivisionalLines divisionalLines, Legend legend, Tooltip tooltip,
			ChartPadding chartPadding, DataPlotCosmetics dataPlotCosmetics,
			NumberFormatting numberFormatting, FontProperties fontProperties,
			TrendLines trendLines) {
		super();
		this.functionalAttributes = functionalAttributes;
		this.chartTitles = chartTitles;
		this.chartCosmetics = chartCosmetics;
		this.anchors = anchors;
		this.divisionalLines = divisionalLines;
		this.legend = legend;
		this.tooltip = tooltip;
		this.chartPadding = chartPadding;
		this.dataPlotCosmetics = dataPlotCosmetics;
		this.numberFormatting = numberFormatting;
		this.fontProperties = fontProperties;
		this.trendLines = trendLines;
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
			if(null!=functionalAttributes.getConnectNullData())
				chart.addAttribute("connectNullData", CommonMethod.convertBooleanToString(functionalAttributes.getConnectNullData()));
			if(null!=functionalAttributes.getAreaOverColumns())
				chart.addAttribute("areaOverColumns", CommonMethod.convertBooleanToString(functionalAttributes.getAreaOverColumns()));
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
			if(null!=functionalAttributes.getPlaceValuesInside())
				chart.addAttribute("placeValuesInside", CommonMethod.convertBooleanToString(functionalAttributes.getPlaceValuesInside()));
			if(null!=functionalAttributes.getShowYAxisValues())
				chart.addAttribute("showYAxisValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowYAxisValues()));
			if(null!=functionalAttributes.getShowLimits())
				chart.addAttribute("showLimits", CommonMethod.convertBooleanToString(functionalAttributes.getShowLimits()));
			if(null!=functionalAttributes.getShowDivLineValues())
				chart.addAttribute("showDivLineValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowDivLineValues()));
			if(null!=functionalAttributes.getShowSecondaryLimits()) 
				chart.addAttribute("showSecondaryLimits", CommonMethod.convertBooleanToString(functionalAttributes.getShowSecondaryLimits()));
			if(null!=functionalAttributes.getShowDivLineSecondaryValue()) 
				chart.addAttribute("showDivLineSecondaryValue", CommonMethod.convertBooleanToString(functionalAttributes.getShowDivLineSecondaryValue()));
			if(null!=functionalAttributes.getDefaultAnimation()) 
				chart.addAttribute("defaultAnimation", CommonMethod.convertBooleanToString(functionalAttributes.getDefaultAnimation()));
			if(null!=functionalAttributes.getYAxisValuesStep())
				chart.addAttribute("yAxisValuesStep", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisValuesStep()));
			if(null!=functionalAttributes.getShowShadow())
				chart.addAttribute("showShadow", CommonMethod.convertBooleanToString(functionalAttributes.getShowShadow()));
			if(null!=functionalAttributes.getAdjustDiv()) 
				chart.addAttribute("adjustDiv", CommonMethod.convertBooleanToString(functionalAttributes.getAdjustDiv()));
			if(null!=functionalAttributes.getRotateYAxisName()) 
				chart.addAttribute("rotateYAxisName", CommonMethod.convertBooleanToString(functionalAttributes.getRotateYAxisName()));
			if(null!=functionalAttributes.getClickURL()) 
				chart.addAttribute("clickURL",functionalAttributes.getClickURL());			
			if(null!=functionalAttributes.getPYAxisMaxValue()) 
				chart.addAttribute("PYAxisMaxValue",CommonMethod.convertIntegerToString(functionalAttributes.getPYAxisMaxValue()));
			if(null!=functionalAttributes.getPYAxisMinValue()) 
				chart.addAttribute("PYAxisMinValue",CommonMethod.convertIntegerToString(functionalAttributes.getPYAxisMinValue()));
			if(null!=functionalAttributes.getSYAxisMinValue()) 
				chart.addAttribute("SYAxisMinValue",CommonMethod.convertIntegerToString(functionalAttributes.getSYAxisMinValue()));
			if(null!=functionalAttributes.getSYAxisMaxValue()) 
				chart.addAttribute("SYAxisMaxValue",CommonMethod.convertIntegerToString(functionalAttributes.getSYAxisMaxValue()));
			if(null!=functionalAttributes.getSetAdaptiveYMin()) 
				chart.addAttribute("setAdaptiveYMin", CommonMethod.convertBooleanToString(functionalAttributes.getSetAdaptiveYMin()));
			if(null!=functionalAttributes.getSetAdaptiveSYMin()) 
				chart.addAttribute("setAdaptiveSYMin", CommonMethod.convertBooleanToString(functionalAttributes.getSetAdaptiveSYMin()));
			if(null!=functionalAttributes.getPYAxisNameWidth()) 
				chart.addAttribute("PYAxisNameWidth",CommonMethod.convertIntegerToString(functionalAttributes.getPYAxisNameWidth()));
			if(null!=functionalAttributes.getSYAxisNameWidth()) 
				chart.addAttribute("SYAxisNameWidth",CommonMethod.convertIntegerToString(functionalAttributes.getSYAxisNameWidth()));
		}
		//chartTitles
		if(null!=chartTitles) {
			if(null!=chartTitles.getCaption()) 
				chart.addAttribute("caption",chartTitles.getCaption());
			if(null!=chartTitles.getSubCaption()) 
				chart.addAttribute("subCaption",chartTitles.getSubCaption());
			if(null!=chartTitles.getXAxisName()) 
				chart.addAttribute("xAxisName",chartTitles.getXAxisName());
			if(null!=chartTitles.getPYAxisName()) 
				chart.addAttribute("PYAxisName",chartTitles.getPYAxisName());
			if(null!=chartTitles.getSYAxisName()) 
				chart.addAttribute("SYAxisName",chartTitles.getSYAxisName());
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
			if(null!=dataPlotCosmetics.getUseRoundEdges()) 
				chart.addAttribute("useRoundEdges",CommonMethod.convertBooleanToString(dataPlotCosmetics.getUseRoundEdges()));
			if(null!=dataPlotCosmetics.getShowPlotBorder()) 
				chart.addAttribute("showPlotBorder",CommonMethod.convertBooleanToString(dataPlotCosmetics.getShowPlotBorder()));
			if(null!=dataPlotCosmetics.getPlotBorderColor()) 
				chart.addAttribute("plotBorderColor",dataPlotCosmetics.getPlotBorderColor());
			if(null!=dataPlotCosmetics.getPlotBorderThickness()) 
				chart.addAttribute("plotBorderThickness",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderThickness()));	
			if(null!=dataPlotCosmetics.getPlotBorderAlpha()) 
				chart.addAttribute("plotBorderAlpha",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderAlpha()));	
			if(null!=dataPlotCosmetics.getPlotBorderDashed()) 
				chart.addAttribute("plotBorderDashed",CommonMethod.convertBooleanToString(dataPlotCosmetics.getPlotBorderDashed()));	
			if(null!=dataPlotCosmetics.getPlotBorderDashLen()) 
				chart.addAttribute("plotBorderDashLen",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderDashLen()));
			if(null!=dataPlotCosmetics.getPlotBorderDashGap()) 
				chart.addAttribute("plotBorderDashGap",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderDashGap()));
			if(null!=dataPlotCosmetics.getPlotFillAngle()) 
				chart.addAttribute("plotFillAngle",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotFillAngle()));
			if(null!=dataPlotCosmetics.getPlotFillAlpha()) 
				chart.addAttribute("plotFillAlpha",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotFillAlpha()));
			if(null!=dataPlotCosmetics.getPlotGradientColor()) 
				chart.addAttribute("plotGradientColor",dataPlotCosmetics.getPlotGradientColor());
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
			if(null!=divisionalLines.getDivLineDecimalPrecision()) 
				chart.addAttribute("divLineDecimalPrecision",CommonMethod.convertBooleanToString(divisionalLines.getDivLineDecimalPrecision()));
		}
		//legend
		if(null!=legend) {
			if(null!=legend.getShowLegend()) 
				chart.addAttribute("showLegend",CommonMethod.convertBooleanToString(legend.getShowLegend()));
			if(null!=legend.getLegendPosition()) 
				chart.addAttribute("legendPosition",legend.getLegendPosition());
			if(null!=legend.getLegendCaption()) 
				chart.addAttribute("legendCaption",legend.getLegendCaption());
			if(null!=legend.getLegendMarkerCircle()) 
				chart.addAttribute("legendMarkerCircle",CommonMethod.convertBooleanToString(legend.getLegendMarkerCircle()));
			if(null!=legend.getLegendBgColor()) 
				chart.addAttribute("legendBgColor",legend.getLegendBgColor());
			if(null!=legend.getLegendBgAlpha()) 
				chart.addAttribute("legendBgAlpha",CommonMethod.convertIntegerToString(legend.getLegendBgAlpha()));
			if(null!=legend.getLegendBorderColor()) 
				chart.addAttribute("legendBorderColor",legend.getLegendBorderColor());
			if(null!=legend.getLegendBorderThickness()) 
				chart.addAttribute("legendBorderThickness",CommonMethod.convertIntegerToString(legend.getLegendBorderThickness()));
			if(null!=legend.getLegendBorderAlpha()) 
				chart.addAttribute("legendBorderAlpha",CommonMethod.convertIntegerToString(legend.getLegendBorderAlpha()));
			if(null!=legend.getLegendShadow()) 
				chart.addAttribute("legendShadow",CommonMethod.convertBooleanToString(legend.getLegendShadow()));
			if(null!=legend.getLegendAllowDrag()) 
				chart.addAttribute("legendAllowDrag",CommonMethod.convertBooleanToString(legend.getLegendAllowDrag()));
			if(null!=legend.getLegendScrollBgColor()) 
				chart.addAttribute("legendScrollBgColor",legend.getLegendScrollBgColor());
			if(null!=legend.getLegendScrollBarColor()) 
				chart.addAttribute("legendScrollBarColor",legend.getLegendScrollBarColor());
			if(null!=legend.getLegendScrollBtnColor()) 
				chart.addAttribute("legendScrollBtnColor",legend.getLegendScrollBtnColor());
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
			if(null!=numberFormatting.getSFormatNumber()) 
				chart.addAttribute("sFormatNumber",CommonMethod.convertBooleanToString(numberFormatting.getSFormatNumber()));
			if(null!=numberFormatting.getSFormatNumberScale()) 
				chart.addAttribute("sFormatNumberScale",CommonMethod.convertBooleanToString(numberFormatting.getSFormatNumberScale()));
			if(null!=numberFormatting.getSDefaultNumberScale()) 
				chart.addAttribute("sDefaultNumberScale",numberFormatting.getSDefaultNumberScale());
			if(null!=numberFormatting.getNumberScaleUnit()) 
				chart.addAttribute("sNumberScaleUnit",numberFormatting.getNumberScaleUnit());
			if(null!=numberFormatting.getNumberScaleValue()) 
				chart.addAttribute("sNumberScaleValue",numberFormatting.getNumberScaleValue());
			if(null!=numberFormatting.getNumberPrefix()) 
				chart.addAttribute("sNumberPrefix",numberFormatting.getNumberPrefix());
			if(null!=numberFormatting.getNumberSuffix()) 
				chart.addAttribute("sNumberSuffix",numberFormatting.getNumberSuffix());
			if(null!=numberFormatting.getSDecimals()) 
				chart.addAttribute("sDecimals",CommonMethod.convertIntegerToString(numberFormatting.getSDecimals()));
			if(null!=numberFormatting.getSYAxisValueDecimals()) 
				chart.addAttribute("sYAxisValueDecimals",CommonMethod.convertIntegerToString(numberFormatting.getSYAxisValueDecimals()));
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
			if(null!=tooltip.getSeriesNameInToolTip()) 
				chart.addAttribute("seriesNameInToolTip",CommonMethod.convertBooleanToString(tooltip.getSeriesNameInToolTip()));
		}
		//chartPadding
		if(null!=chartPadding) {
			if(null!=chartPadding.getCanvasPadding()) 
				chart.addAttribute("canvasPadding",CommonMethod.convertIntegerToString(chartPadding.getCanvasPadding()));
			if(null!=chartPadding.getLegendPadding()) 
				chart.addAttribute("legendPadding",CommonMethod.convertIntegerToString(chartPadding.getLegendPadding()));
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
			if(null!=chartPadding.getPlotSpacePercent()) 
				chart.addAttribute("plotSpacePercent",CommonMethod.convertIntegerToString(chartPadding.getPlotSpacePercent()));
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
			if(null!=trendLines.getShowOnTop())
				line.addAttribute("showOnTop", CommonMethod.convertBooleanToString(trendLines.getShowOnTop()));
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
				if(obj instanceof Categories) {
					Element categories = chart.addElement("categories");
					Categories categoriesObj = (Categories)obj;
					if(null!=categoriesObj.getFont())
						categories.addAttribute("font", categoriesObj.getFont());
					if(null!=categoriesObj.getFontSize())
						categories.addAttribute("fontSize", CommonMethod.convertIntegerToString(categoriesObj.getFontSize()));
					if(null!=categoriesObj.getFontColor())
						categories.addAttribute("fontColor", categoriesObj.getFontColor());
					if(null!=categoriesObj.getCategories()) {
						for(Iterator categoryIter = categoriesObj.getCategories().iterator();categoryIter.hasNext();) {
							Object obj1 = categoryIter.next();
							if(obj1 instanceof VerticalLines) {
								Element vLine = categories.addElement("vLine");
								VerticalLines vlineobj = (VerticalLines)obj1;
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
								Element category = categories.addElement("category");
								Category categoryObj = (Category)obj1;
								if(null!=categoryObj.getLabel())
									category.addAttribute("label", categoryObj.getLabel());
								if(null!=categoryObj.getShowLabel())
									category.addAttribute("showLabel", CommonMethod.convertBooleanToString(categoryObj.getShowLabel()));
								if(null!=categoryObj.getToolText())
									category.addAttribute("toolText", categoryObj.getToolText());
							}
						}
					}
					
				} else {
					Element dataset = chart.addElement("dataset");
					Dataset datasetObj = (Dataset)obj;
					if(null!=datasetObj.getRenderAs())
						dataset.addAttribute("renderAs", datasetObj.getRenderAs());
					if(null!=datasetObj.getParentYAxis())
						dataset.addAttribute("parentYAxis", datasetObj.getParentYAxis());
					if(null!=datasetObj.getShowPlotBorder())
						dataset.addAttribute("showPlotBorder", CommonMethod.convertBooleanToString(datasetObj.getShowPlotBorder()));
					if(null!=datasetObj.getPlotBorderColor())
						dataset.addAttribute("plotBorderColor", datasetObj.getPlotBorderColor());
					if(null!=datasetObj.getPlotBorderThickness())
						dataset.addAttribute("plotBorderThickness", CommonMethod.convertIntegerToString(datasetObj.getPlotBorderThickness()));
					if(null!=datasetObj.getPlotBorderAlpha())
						dataset.addAttribute("plotBorderAlpha", CommonMethod.convertIntegerToString(datasetObj.getPlotBorderAlpha()));
					if(null!=datasetObj.getSeriesName())
						dataset.addAttribute("seriesName", datasetObj.getSeriesName());
					if(null!=datasetObj.getColor())
						dataset.addAttribute("color", datasetObj.getColor());
					if(null!=datasetObj.getAlpha())
						dataset.addAttribute("alpha", datasetObj.getAlpha());
					if(null!=datasetObj.getShowValues())
						dataset.addAttribute("showValues", CommonMethod.convertBooleanToString(datasetObj.getShowValues()));
					if(null!=datasetObj.getDashed())
						dataset.addAttribute("dashed", CommonMethod.convertBooleanToString(datasetObj.getDashed()));
					if(null!=datasetObj.getIncludeInLegend())
						dataset.addAttribute("includeInLegend", CommonMethod.convertBooleanToString(datasetObj.getIncludeInLegend()));
					if(null!=datasetObj.getDrawAnchors())
						dataset.addAttribute("drawAnchors", CommonMethod.convertBooleanToString(datasetObj.getDrawAnchors()));
					if(null!=datasetObj.getAnchorSides())
						dataset.addAttribute("anchorSides", CommonMethod.convertIntegerToString(datasetObj.getAnchorSides()));
					if(null!=datasetObj.getAnchorRadius())
						dataset.addAttribute("anchorRadius", CommonMethod.convertIntegerToString(datasetObj.getAnchorRadius()));
					if(null!=datasetObj.getAnchorBorderColor())
						dataset.addAttribute("anchorBorderColor", datasetObj.getAnchorBorderColor());
					if(null!=datasetObj.getAnchorBorderThickness())
						dataset.addAttribute("anchorBorderThickness", CommonMethod.convertIntegerToString(datasetObj.getAnchorBorderThickness()));
					if(null!=datasetObj.getAnchorBgColor())
						dataset.addAttribute("anchorBgColor", datasetObj.getAnchorBgColor());
					if(null!=datasetObj.getAnchorAlpha())
						dataset.addAttribute("anchorAlpha", CommonMethod.convertIntegerToString(datasetObj.getAnchorAlpha()));
					if(null!=datasetObj.getLineThickness())
						dataset.addAttribute("lineThickness", CommonMethod.convertIntegerToString(datasetObj.getLineThickness()));
					if(null!=datasetObj.getDashed())
						dataset.addAttribute("dashed", CommonMethod.convertBooleanToString(datasetObj.getDashed()));
					if(null!=datasetObj.getLineDashLen())
						dataset.addAttribute("lineDashLen", CommonMethod.convertIntegerToString(datasetObj.getLineDashLen()));
					if(null!=datasetObj.getLineDashGap())
						dataset.addAttribute("lineDashGap", CommonMethod.convertIntegerToString(datasetObj.getLineDashGap()));
					if(null!=datasetObj.getSets()) {
						for(Iterator setIter = datasetObj.getSets().iterator();setIter.hasNext();) {
							Element set = dataset.addElement("set");
							Set setobj = (Set)setIter.next();
							if(null!=setobj.getDashed())
								set.addAttribute("dashed", CommonMethod.convertBooleanToString(setobj.getDashed()));
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
							if(null!=setobj.getValue())
								set.addAttribute("value", CommonMethod.convertDoubleToString(setobj.getValue()));
							if(null!=setobj.getColor())
								set.addAttribute("color", setobj.getColor());
							if(null!=setobj.getLink())
								set.addAttribute("link", setobj.getLink());
							if(null!=setobj.getToolText())
								set.addAttribute("toolText", setobj.getToolText());
							if(null!=setobj.getShowValue())
								set.addAttribute("showValue", CommonMethod.convertBooleanToString(setobj.getShowValue()));							
							if(null!=setobj.getAlpha())
								set.addAttribute("alpha", CommonMethod.convertIntegerToString(setobj.getAlpha()));							
						}
					}
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

	public Anchors getAnchors() {
		return anchors;
	}

	public void setAnchors(Anchors anchors) {
		this.anchors = anchors;
	}

	public DivisionalLines getDivisionalLines() {
		return divisionalLines;
	}

	public void setDivisionalLines(DivisionalLines divisionalLines) {
		this.divisionalLines = divisionalLines;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
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

	public NumberFormatting getNumberFormatting() {
		return numberFormatting;
	}

	public void setNumberFormatting(NumberFormatting numberFormatting) {
		this.numberFormatting = numberFormatting;
	}

	public FontProperties getFontProperties() {
		return fontProperties;
	}

	public void setFontProperties(FontProperties fontProperties) {
		this.fontProperties = fontProperties;
	}

	public TrendLines getTrendLines() {
		return trendLines;
	}

	public void setTrendLines(TrendLines trendLines) {
		this.trendLines = trendLines;
	}
	
}
