package kdf.chart.multiSeries.column3D;

import java.util.Iterator;
import java.util.List;

import kdf.chart.multiSeries.column3D.chart.ChartCosmetics;
import kdf.chart.multiSeries.column3D.chart.ChartPadding;
import kdf.chart.multiSeries.column3D.chart.ChartTitles;
import kdf.chart.multiSeries.column3D.chart.DivisionalLines;
import kdf.chart.multiSeries.column3D.chart.FunctionalAttributes;
import kdf.chart.multiSeries.column3D.chart.Legend;
import kdf.chart.multiSeries.column3D.chart.Tooltip;
import kdf.chart.multiSeries.column3D.data.Categories;
import kdf.chart.multiSeries.column3D.data.Category;
import kdf.chart.multiSeries.column3D.data.DataPlotCosmetics;
import kdf.chart.multiSeries.column3D.data.Dataset;
import kdf.chart.multiSeries.column3D.data.Set;
import kdf.chart.multiSeries.column3D.others.FontProperties;
import kdf.chart.multiSeries.column3D.others.NumberFormatting;
import kdf.chart.multiSeries.column3D.others.TrendLines;
import kdf.chart.multiSeries.column3D.others.VerticalLines;
import kdf.chart.util.CommonMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class PlanColumn3DChart {
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
	private Legend legend;
	
	public PlanColumn3DChart() {
		functionalAttributes = new FunctionalAttributes();
		functionalAttributes.setShowValues(new Boolean(true));
		functionalAttributes.setShowLabels(new Boolean(true));
		functionalAttributes.setRotateYAxisName(new Boolean(false));
		functionalAttributes.setPalette(new Integer(2));
		chartCosmetics = null;
		chartPadding = null;
		chartTitles = new ChartTitles();
		chartTitles.setCaption("ͼ��1");
		chartTitles.setXAxisName("X��");
		chartTitles.setYAxisName("Y��");
		dataPlotCosmetics = null;
		divisionalLines = null;
		fontProperties = null;
		numberFormatting = new NumberFormatting();
		numberFormatting.setDecimals(new Integer(0));
		numberFormatting.setFormatNumberScale(new Boolean(false));
		tooltip = null;
		trendLines = null;
		legend = null;
	}

	public PlanColumn3DChart(FunctionalAttributes functionalAttributes,
			ChartCosmetics chartCosmetics, ChartPadding chartPadding,
			ChartTitles chartTitles, DataPlotCosmetics dataPlotCosmetics,
			DivisionalLines divisionalLines, FontProperties fontProperties,
			NumberFormatting numberFormatting, Tooltip tooltip,
			TrendLines trendLines, Legend legend) {
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
		this.legend = legend;
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
			if(null!=functionalAttributes.getPlaceValuesInside())
				chart.addAttribute("placeValuesInside", CommonMethod.convertBooleanToString(functionalAttributes.getPlaceValuesInside()));
			if(null!=functionalAttributes.getShowYAxisValues())
				chart.addAttribute("showYAxisValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowYAxisValues()));
			if(null!=functionalAttributes.getShowLimits())
				chart.addAttribute("showLimits", CommonMethod.convertBooleanToString(functionalAttributes.getShowLimits()));
			if(null!=functionalAttributes.getShowDivLineValues())
				chart.addAttribute("showDivLineValues", CommonMethod.convertBooleanToString(functionalAttributes.getShowDivLineValues()));
			if(null!=functionalAttributes.getYAxisValuesStep())
				chart.addAttribute("yAxisValuesStep", CommonMethod.convertIntegerToString(functionalAttributes.getYAxisValuesStep()));
			if(null!=functionalAttributes.getShowShadow())
				chart.addAttribute("showShadow", CommonMethod.convertBooleanToString(functionalAttributes.getShowShadow()));
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
			if(null!=chartCosmetics.getCanvasBaseColor()) 
				chart.addAttribute("canvasBaseColor",chartCosmetics.getCanvasBaseColor());
			if(null!=chartCosmetics.getShowCanvasBg()) 
				chart.addAttribute("showCanvasBg",CommonMethod.convertBooleanToString(chartCosmetics.getShowCanvasBg()));
			if(null!=chartCosmetics.getShowCanvasBase()) 
				chart.addAttribute("showCanvasBase",CommonMethod.convertBooleanToString(chartCosmetics.getShowCanvasBase()));
			if(null!=chartCosmetics.getCanvasBaseDepth()) 
				chart.addAttribute("canvasBaseDepth",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBaseDepth()));
			if(null!=chartCosmetics.getCanvasBgDepth()) 
				chart.addAttribute("canvasBgDepth",CommonMethod.convertIntegerToString(chartCosmetics.getCanvasBgDepth()));
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
			if(null!=dataPlotCosmetics.getPlotBorderAlpha()) 
				chart.addAttribute("plotBorderAlpha",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotBorderAlpha()));
			if(null!=dataPlotCosmetics.getPlotFillAngle()) 
				chart.addAttribute("plotFillAngle",CommonMethod.convertIntegerToString(dataPlotCosmetics.getPlotFillAngle()));
			if(null!=dataPlotCosmetics.getOverlapColumns()) 
				chart.addAttribute("overlapColumns",CommonMethod.convertBooleanToString(dataPlotCosmetics.getOverlapColumns()));
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
			if(null!=divisionalLines.getZeroPlaneShowBorder()) 
				chart.addAttribute("zeroPlaneShowBorder",CommonMethod.convertBooleanToString(divisionalLines.getZeroPlaneShowBorder()));
			if(null!=divisionalLines.getZeroPlaneBorderColor()) 
				chart.addAttribute("zeroPlaneBorderColor",divisionalLines.getZeroPlaneBorderColor());
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
			if(null!=chartPadding.getCanvasPadding()) 
				chart.addAttribute("canvasPadding",CommonMethod.convertIntegerToString(chartPadding.getCanvasPadding()));
			if(null!=chartPadding.getLegendPadding()) 
				chart.addAttribute("legendPadding",CommonMethod.convertIntegerToString(chartPadding.getLegendPadding()));
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
					if(null!=datasetObj.getSeriesName())
						dataset.addAttribute("seriesName", datasetObj.getSeriesName());
					if(null!=datasetObj.getColor())
						dataset.addAttribute("color", datasetObj.getColor());
					if(null!=datasetObj.getAlpha())
						dataset.addAttribute("alpha", datasetObj.getAlpha());
					if(null!=datasetObj.getRatio())
						dataset.addAttribute("ratio", datasetObj.getRatio());
					if(null!=datasetObj.getShowValues())
						dataset.addAttribute("showValues", CommonMethod.convertBooleanToString(datasetObj.getShowValues()));
					if(null!=datasetObj.getIncludeInLegend())
						dataset.addAttribute("includeInLegend", CommonMethod.convertBooleanToString(datasetObj.getIncludeInLegend()));
					if(null!=datasetObj.getSets()) {
						for(Iterator setIter = datasetObj.getSets().iterator();setIter.hasNext();) {
							Element set = dataset.addElement("set");
							Set setobj = (Set)setIter.next();
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

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}
	
}
