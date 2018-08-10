package kdf.chart.singleValue.angularGauge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kdf.chart.singleValue.angularGauge.chart.ChartCosmetics;
import kdf.chart.singleValue.angularGauge.chart.ChartPadding;
import kdf.chart.singleValue.angularGauge.chart.FunctionalAttributes;
import kdf.chart.singleValue.angularGauge.chart.Hover;
import kdf.chart.singleValue.angularGauge.chart.Pivot;
import kdf.chart.singleValue.angularGauge.chart.TickMark;
import kdf.chart.singleValue.angularGauge.data.Color;
import kdf.chart.singleValue.angularGauge.data.ColorRange;
import kdf.chart.singleValue.angularGauge.data.Dial;
import kdf.chart.singleValue.angularGauge.data.Point;
import kdf.chart.singleValue.angularGauge.data.TrendPoints;
import kdf.chart.singleValue.angularGauge.others.FontProperties;
import kdf.chart.singleValue.angularGauge.others.NumberFormatting;
import kdf.chart.util.CommonMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class PlanAngularGaugeChart {
	private ChartCosmetics chartCosmetics;
	private ChartPadding chartPadding;
	private FunctionalAttributes functionalAttributes;
	private Hover hover;
	private Pivot pivot;
	private TickMark tickMark;
	private FontProperties fontProperties;
	private NumberFormatting numberFormatting;
	private ColorRange colorRange;
	private TrendPoints trendPoints;
	
	public PlanAngularGaugeChart() {
		functionalAttributes = new FunctionalAttributes();
		functionalAttributes.setGaugeOuterRadius(new Integer(90));
		functionalAttributes.setGaugeInnerRadius(new Integer(0));
		functionalAttributes.setGaugeOriginX(new Integer(130));
		functionalAttributes.setGaugeOriginY(new Integer(150));
		functionalAttributes.setGaugeScaleAngle(new Integer(360));
		functionalAttributes.setGaugeAlpha(new Integer(50));
		functionalAttributes.setPlaceValuesInside(new Boolean(false));
		tickMark = new TickMark();
		tickMark.setMajorTMHeight(new Integer(9));
		tickMark.setMinorTMNumber(new Integer(5));
		tickMark.setMinorTMColor("000000");
		tickMark.setMinorTMHeight(new Integer(3));
		tickMark.setMajorTMThickness(new Integer(2));
		tickMark.setDisplayValueDistance(new Integer(15));
		pivot = new Pivot();
		pivot.setPivotRadius(new Integer(6));
		chartCosmetics = new ChartCosmetics();
		chartCosmetics.setShowBorder(new Boolean(true));
		numberFormatting = new NumberFormatting();
		numberFormatting.setDecimalPrecision("0");
		hover = new Hover();
		hover.setHoverCapBgColor("F2F2FF");
		hover.setHoverCapBorderColor("6A6FA6");
		chartPadding = null;
		fontProperties = null;
		colorRange = new ColorRange();
		List colors = new ArrayList();
		Color color1 = new Color();
		color1.setMinValue(new Double(0));
		color1.setMaxValue(new Double(120));
		color1.setCode("C1E1C1");
		color1.setAlpha(new Integer(60));
		color1.setBorderColor("00B900");
		colors.add(color1);
		Color color2 = new Color();
		color2.setMinValue(new Double(120));
		color2.setMaxValue(new Double(240));
		color2.setCode("F6F164");
		color2.setAlpha(new Integer(60));
		color2.setBorderColor("FDC12E");
		colors.add(color2);
		Color color3 = new Color();
		color3.setMinValue(new Double(240));
		color3.setMaxValue(new Double(360));
		color3.setCode("F70118");
		color3.setAlpha(new Integer(60));
		color3.setBorderColor("E95D0F");
		colors.add(color3);
		colorRange.setColors(colors);

		trendPoints = null;
	}

	public PlanAngularGaugeChart(ChartCosmetics chartCosmetics,
			ChartPadding chartPadding,
			FunctionalAttributes functionalAttributes, Hover hover,
			Pivot pivot, TickMark tickMark, FontProperties fontProperties,
			NumberFormatting numberFormatting,ColorRange colorRange,TrendPoints trendPoints) {
		super();
		this.chartCosmetics = chartCosmetics;
		this.chartPadding = chartPadding;
		this.functionalAttributes = functionalAttributes;
		this.hover = hover;
		this.pivot = pivot;
		this.tickMark = tickMark;
		this.fontProperties = fontProperties;
		this.numberFormatting = numberFormatting;
		this.colorRange = colorRange;
		this.trendPoints = trendPoints;
	}

	public Document buildXml(List datas) {
		Document doc = DocumentHelper.createDocument();
		Element chart = doc.addElement("chart");
		//functionalAttributes
		if(null!=functionalAttributes) {
			if(null!=functionalAttributes.getAnimation())
				chart.addAttribute("animation", CommonMethod.convertBooleanToString(functionalAttributes.getAnimation()));
			if(null!=functionalAttributes.getClickURL())
				chart.addAttribute("clickURL", functionalAttributes.getClickURL());
			if(null!=functionalAttributes.getGaugeAlpha())
				chart.addAttribute("gaugeAlpha", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeAlpha()));
			if(null!=functionalAttributes.getGaugeInnerRadius())
				chart.addAttribute("gaugeInnerRadius", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeInnerRadius()));
			if(null!=functionalAttributes.getGaugeOriginX())
				chart.addAttribute("gaugeOriginX", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeOriginX()));
			if(null!=functionalAttributes.getGaugeOriginY())
				chart.addAttribute("gaugeOriginY", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeOriginY()));
			if(null!=functionalAttributes.getGaugeOuterRadius())
				chart.addAttribute("gaugeOuterRadius", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeOuterRadius()));
			if(null!=functionalAttributes.getGaugeScaleAngle())
				chart.addAttribute("gaugeScaleAngle", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeScaleAngle()));
			if(null!=functionalAttributes.getGaugeStartAngle())
				chart.addAttribute("gaugeStartAngle", CommonMethod.convertIntegerToString(functionalAttributes.getGaugeStartAngle()));
			if(null!=functionalAttributes.getLowerLimit())
				chart.addAttribute("lowerLimit", CommonMethod.convertDoubleToString(functionalAttributes.getLowerLimit()));
			if(null!=functionalAttributes.getLowerLimitDisplay())
				chart.addAttribute("lowerLimitDisplay", functionalAttributes.getLowerLimitDisplay());
			if(null!=functionalAttributes.getShowLimits())
				chart.addAttribute("showLimits", CommonMethod.convertBooleanToString(functionalAttributes.getShowLimits()));
			if(null!=functionalAttributes.getUpperLimit())
				chart.addAttribute("upperLimit", CommonMethod.convertDoubleToString(functionalAttributes.getUpperLimit()));
			if(null!=functionalAttributes.getUpperLimitDisplay())
				chart.addAttribute("upperLimitDisplay", functionalAttributes.getUpperLimitDisplay());
			if(null!=functionalAttributes.getPlaceValuesInside())
				chart.addAttribute("placeValuesInside", CommonMethod.convertBooleanToString(functionalAttributes.getPlaceValuesInside()));
		}
		//chartCosmetics
		if(null!=chartCosmetics) {
			if(null!=chartCosmetics.getBgAlpha())
				chart.addAttribute("bgAlpha", chartCosmetics.getBgAlpha());
			if(null!=chartCosmetics.getBgColor())
				chart.addAttribute("bgColor", chartCosmetics.getBgColor());
			if(null!=chartCosmetics.getBgSWF())
				chart.addAttribute("bgSWF", chartCosmetics.getBgSWF());
			if(null!=chartCosmetics.getBorderColor())
				chart.addAttribute("borderColor", chartCosmetics.getBorderColor());
			if(null!=chartCosmetics.getBorderThickness())
				chart.addAttribute("borderThickness", CommonMethod.convertIntegerToString(chartCosmetics.getBorderThickness()));
			if(null!=chartCosmetics.getShowBorder())
				chart.addAttribute("showBorder", CommonMethod.convertBooleanToString(chartCosmetics.getShowBorder()));
		}
		//chartPadding
		if(null!=chartPadding) {
			if(null!=chartPadding.getChartBottomMargin())
				chart.addAttribute("chartBottomMargin", CommonMethod.convertIntegerToString(chartPadding.getChartBottomMargin()));
			if(null!=chartPadding.getChartLeftMargin())
				chart.addAttribute("chartLeftMargin", CommonMethod.convertIntegerToString(chartPadding.getChartLeftMargin()));
			if(null!=chartPadding.getChartRightMargin())
				chart.addAttribute("chartRightMargin", CommonMethod.convertIntegerToString(chartPadding.getChartRightMargin()));
			if(null!=chartPadding.getChartTopMargin())
				chart.addAttribute("chartTopMargin", CommonMethod.convertIntegerToString(chartPadding.getChartTopMargin()));
		}
		//hover
		if(null!=hover) {
			if(null!=hover.getHoverCapBgColor())
				chart.addAttribute("hoverCapBgColor", hover.getHoverCapBgColor());
			if(null!=hover.getHoverCapBorderColor())
				chart.addAttribute("hoverCapBorderColor", hover.getHoverCapBorderColor());
			if(null!=hover.getHoverCapSepChar())
				chart.addAttribute("hoverCapSepChar", hover.getHoverCapSepChar());
			if(null!=hover.getShowhovercap())
				chart.addAttribute("showhovercap", CommonMethod.convertBooleanToString(hover.getShowhovercap()));
		}
		//pivot
		if(null!=pivot) {
			if(null!=pivot.getPivotBgColor())
				chart.addAttribute("pivotBgColor", pivot.getPivotBgColor());
			if(null!=pivot.getPivotBorderColor())
				chart.addAttribute("pivotBorderColor", pivot.getPivotBorderColor());
			if(null!=pivot.getPivotBorderThickness())
				chart.addAttribute("pivotBorderThickness", CommonMethod.convertIntegerToString(pivot.getPivotBorderThickness()));
			if(null!=pivot.getPivotRadius())
				chart.addAttribute("pivotRadius", CommonMethod.convertIntegerToString(pivot.getPivotRadius()));
		}
		//tickMark
		if(null!=tickMark) {
			if(null!=tickMark.getDisplayValueDistance())
				chart.addAttribute("displayValueDistance", CommonMethod.convertIntegerToString(tickMark.getDisplayValueDistance()));
			if(null!=tickMark.getMajorTMColor())
				chart.addAttribute("majorTMColor", tickMark.getMajorTMColor());
			if(null!=tickMark.getMajorTMHeight())
				chart.addAttribute("majorTMHeight", CommonMethod.convertIntegerToString(tickMark.getMajorTMHeight()));
			if(null!=tickMark.getMajorTMNumber())
				chart.addAttribute("majorTMNumber", CommonMethod.convertIntegerToString(tickMark.getMajorTMNumber()));
			if(null!=tickMark.getMajorTMThickness())
				chart.addAttribute("majorTMThickness", CommonMethod.convertIntegerToString(tickMark.getMajorTMThickness()));
			if(null!=tickMark.getMinorTMColor())
				chart.addAttribute("minorTMColor", tickMark.getMinorTMColor());
			if(null!=tickMark.getMinorTMHeight())
				chart.addAttribute("minorTMHeight", CommonMethod.convertIntegerToString(tickMark.getMinorTMHeight()));
			if(null!=tickMark.getMinorTMNumber())
				chart.addAttribute("minorTMNumber", CommonMethod.convertIntegerToString(tickMark.getMinorTMNumber()));
			if(null!=tickMark.getMinorTMThickness())
				chart.addAttribute("minorTMThickness", CommonMethod.convertIntegerToString(tickMark.getMinorTMThickness()));
			if(null!=tickMark.getShowTickMarks())
				chart.addAttribute("showTickMarks", CommonMethod.convertBooleanToString(tickMark.getShowTickMarks()));
			if(null!=tickMark.getShowTickValues())
				chart.addAttribute("showTickValues", CommonMethod.convertBooleanToString(tickMark.getShowTickValues()));
			if(null!=tickMark.getTickMarkDecimalPrecision())
				chart.addAttribute("tickMarkDecimalPrecision", tickMark.getTickMarkDecimalPrecision());
		}
		//fontProperties
		if(null!=fontProperties) {
			if(null!=fontProperties.getBaseFont()) 
				chart.addAttribute("baseFont", fontProperties.getBaseFont());
			if(null!=fontProperties.getBaseFontColor()) 
				chart.addAttribute("baseFontColor", fontProperties.getBaseFontColor());
			if(null!=fontProperties.getBaseFontSize()) 
				chart.addAttribute("baseFontSize", CommonMethod.convertIntegerToString(fontProperties.getBaseFontSize()));
		}
		//numberFormatting
		if(null!=numberFormatting) {
			if(null!=numberFormatting.getDecimalPrecision())
				chart.addAttribute("decimalPercision", numberFormatting.getDecimalPrecision());
			if(null!=numberFormatting.getDecimalSeparator())
				chart.addAttribute("decimalSeparator", numberFormatting.getDecimalSeparator());
			if(null!=numberFormatting.getDefaultNumberScale())
				chart.addAttribute("defaultNumberScale", numberFormatting.getDefaultNumberScale());
			if(null!=numberFormatting.getFormatNumber())
				chart.addAttribute("formatNumber", CommonMethod.convertBooleanToString(numberFormatting.getFormatNumber()));
			if(null!=numberFormatting.getFormatNumberScale())
				chart.addAttribute("formatNumberScale", CommonMethod.convertBooleanToString(numberFormatting.getFormatNumberScale()));
			if(null!=numberFormatting.getNumberPrefix())
				chart.addAttribute("numberPrefix", numberFormatting.getNumberPrefix());
			if(null!=numberFormatting.getNumberScaleUnit())
				chart.addAttribute("numberScaleUnit", numberFormatting.getNumberScaleUnit());
			if(null!=numberFormatting.getNumberScaleValue())
				chart.addAttribute("numberScaleValue", numberFormatting.getNumberScaleValue());
			if(null!=numberFormatting.getNumberSuffix())
				chart.addAttribute("numberSuffix", numberFormatting.getNumberSuffix());
			if(null!=numberFormatting.getThousandSeparator())
				chart.addAttribute("thousandSeparator", numberFormatting.getThousandSeparator());
		}
		//colorRange
		if(null!=colorRange) {
			Element colorRangeEl = chart.addElement("colorRange");
			if(null!=colorRange.getColors()) {
				for(Iterator iter = colorRange.getColors().iterator();iter.hasNext();) {	
					Color colorobj = (Color)iter.next();
					Element color = colorRangeEl.addElement("color");
					if(null!=colorobj.getAlpha())
						color.addAttribute("alpha", CommonMethod.convertIntegerToString(colorobj.getAlpha()));
					if(null!=colorobj.getBorderColor())
						color.addAttribute("borderColor", colorobj.getBorderColor());
					if(null!=colorobj.getCode())
						color.addAttribute("code", colorobj.getCode());
					if(null!=colorobj.getMaxValue())
						color.addAttribute("maxValue", CommonMethod.convertDoubleToString(colorobj.getMaxValue()));
					if(null!=colorobj.getMinValue())
						color.addAttribute("minValue", CommonMethod.convertDoubleToString(colorobj.getMinValue()));
					if(null!=colorobj.getName())
						color.addAttribute("number", colorobj.getName());
				}				
			}
		}
		//dials
		if(null!=datas&&0<datas.size()) {
			Element dials = chart.addElement("dials");
			for(Iterator iter = datas.iterator();iter.hasNext();) {	
				Object obj = iter.next();
				Element dial = dials.addElement("dial");
				Dial dialobj = (Dial)obj;
				if(null!=dialobj.getBaseWidth())
					dial.addAttribute("baseWidth", CommonMethod.convertIntegerToString(dialobj.getBaseWidth()));
				if(null!=dialobj.getBgColor())
					dial.addAttribute("bgColor", dialobj.getBgColor());
				if(null!=dialobj.getBorderAlpha())
					dial.addAttribute("borderAlpha", CommonMethod.convertIntegerToString(dialobj.getBorderAlpha()));
				if(null!=dialobj.getBorderColor())
					dial.addAttribute("borderColor", dialobj.getBorderColor());
				if(null!=dialobj.getBorderThickness())
					dial.addAttribute("borderThickness", CommonMethod.convertIntegerToString(dialobj.getBorderThickness()));
				if(null!=dialobj.getLink())
					dial.addAttribute("link", dialobj.getLink());
				if(null!=dialobj.getRadius())
					dial.addAttribute("radius", CommonMethod.convertIntegerToString(dialobj.getRadius()));
				if(null!=dialobj.getTopWidth())
					dial.addAttribute("topWidth", CommonMethod.convertIntegerToString(dialobj.getTopWidth()));
				if(null!=dialobj.getValue())
					dial.addAttribute("value", CommonMethod.convertDoubleToString(dialobj.getValue()));
			}
		}
		//trendPoints
		if(null!=trendPoints) {
			Element trendPointsEl = chart.addElement("trendPoints");
			if(null!=trendPoints.getPoints()) {
				for(Iterator iter = trendPoints.getPoints().iterator();iter.hasNext();) {	
					Point pointobj = (Point)iter.next();
					Element point = trendPointsEl.addElement("point");
					if(null!=pointobj.getDisplayValue())
						point.addAttribute("displayValue", pointobj.getDisplayValue());
					if(null!=pointobj.getFontcolor())
						point.addAttribute("fontColor", pointobj.getFontcolor());
					if(null!=pointobj.getValue())
						point.addAttribute("value", CommonMethod.convertDoubleToString(pointobj.getValue()));
				}				
			}
		}
		return doc;
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

	public FunctionalAttributes getFunctionalAttributes() {
		return functionalAttributes;
	}

	public void setFunctionalAttributes(FunctionalAttributes functionalAttributes) {
		this.functionalAttributes = functionalAttributes;
	}

	public Hover getHover() {
		return hover;
	}

	public void setHover(Hover hover) {
		this.hover = hover;
	}

	public Pivot getPivot() {
		return pivot;
	}

	public void setPivot(Pivot pivot) {
		this.pivot = pivot;
	}

	public TickMark getTickMark() {
		return tickMark;
	}

	public void setTickMark(TickMark tickMark) {
		this.tickMark = tickMark;
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

	public ColorRange getColorRange() {
		return colorRange;
	}

	public void setColorRange(ColorRange colorRange) {
		this.colorRange = colorRange;
	}

	public TrendPoints getTrendPoints() {
		return trendPoints;
	}

	public void setTrendPoints(TrendPoints trendPoints) {
		this.trendPoints = trendPoints;
	}
	
	
}
