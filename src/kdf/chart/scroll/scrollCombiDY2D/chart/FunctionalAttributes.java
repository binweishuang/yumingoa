package kdf.chart.scroll.scrollCombiDY2D.chart;

public class FunctionalAttributes {
	private Integer numVisiblePlot;
	private Boolean animation;
	private Integer palette;
	private Boolean connectNullData;
	private Boolean areaOverColumns;
	private Boolean showLabels;
	private String labelDisplay;
	private Boolean rotateLabels;
	private Boolean slantLabels;
	private Integer labelStep;
	private Integer staggerLines;
	private Boolean showValues;
	private Boolean rotateValues;
	private Boolean placeValuesInside;
	private Boolean showYAxisValues;
	private Boolean showLimits;
	private Boolean showDivLineValues;
	private Boolean showSecondaryLimits;
	private Boolean showDivLineSecondaryValue;
	private Boolean defaultAnimation;
	private Integer yAxisValuesStep;	
	private Boolean showShadow;	
	private Boolean adjustDiv;
	private Boolean rotateYAxisName;
	private String clickURL;
	private Integer PYAxisMaxValue;	
	private Integer SYAxisMinValue;	
	private Integer SYAxisMaxValue;
	private Integer PYAxisMinValue;
	private Boolean setAdaptiveYMin;
	private Boolean setAdaptiveSYMin;	
	private Integer PYAxisNameWidth;
	private Integer SYAxisNameWidth;
	private Boolean scrollToEnd;
	
	public FunctionalAttributes() {
		this.numVisiblePlot = null;
		this.animation = null;
		this.palette = null;
		this.connectNullData = null;
		this.areaOverColumns = null;
		this.showLabels = null;
		this.labelDisplay = null;
		this.rotateLabels = null;
		this.slantLabels = null;
		this.labelStep = null;
		this.staggerLines = null;
		this.showValues = null;
		this.rotateValues = null;
		this.placeValuesInside = null;
		this.showYAxisValues = null;
		this.showLimits = null;
		this.showDivLineValues = null;
		this.showSecondaryLimits = null;
		this.showDivLineSecondaryValue = null;
		this.defaultAnimation = null;
		yAxisValuesStep = null;
		this.showShadow = null;
		this.adjustDiv = null;
		this.rotateYAxisName = null;
		this.clickURL = null;
		PYAxisMaxValue = null;
		SYAxisMinValue = null;
		SYAxisMaxValue = null;
		PYAxisMinValue = null;
		this.setAdaptiveYMin = null;
		this.setAdaptiveSYMin = null;
		PYAxisNameWidth = null;
		SYAxisNameWidth = null;
		this.scrollToEnd = null;
	}

	public FunctionalAttributes(Integer numVisiblePlot, Boolean animation, Integer palette,
			Boolean connectNullData, Boolean areaOverColumns,
			Boolean showLabels, String labelDisplay, Boolean rotateLabels,
			Boolean slantLabels, Integer labelStep, Integer staggerLines,
			Boolean showValues, Boolean rotateValues,
			Boolean placeValuesInside, Boolean showYAxisValues,
			Boolean showLimits, Boolean showDivLineValues,
			Boolean showSecondaryLimits, Boolean showDivLineSecondaryValue,
			Boolean defaultAnimation, Integer axisValuesStep,
			Boolean showShadow, Boolean adjustDiv, Boolean rotateYAxisName,
			String clickURL, Integer axisMaxValue, Integer axisMinValue,
			Integer axisMaxValue2, Integer axisMinValue2,
			Boolean setAdaptiveYMin, Boolean setAdaptiveSYMin,
			Integer axisNameWidth, Integer axisNameWidth2,Boolean scrollToEnd) {
		super();
		this.numVisiblePlot = numVisiblePlot;
		this.animation = animation;
		this.palette = palette;
		this.connectNullData = connectNullData;
		this.areaOverColumns = areaOverColumns;
		this.showLabels = showLabels;
		this.labelDisplay = labelDisplay;
		this.rotateLabels = rotateLabels;
		this.slantLabels = slantLabels;
		this.labelStep = labelStep;
		this.staggerLines = staggerLines;
		this.showValues = showValues;
		this.rotateValues = rotateValues;
		this.placeValuesInside = placeValuesInside;
		this.showYAxisValues = showYAxisValues;
		this.showLimits = showLimits;
		this.showDivLineValues = showDivLineValues;
		this.showSecondaryLimits = showSecondaryLimits;
		this.showDivLineSecondaryValue = showDivLineSecondaryValue;
		this.defaultAnimation = defaultAnimation;
		yAxisValuesStep = axisValuesStep;
		this.showShadow = showShadow;
		this.adjustDiv = adjustDiv;
		this.rotateYAxisName = rotateYAxisName;
		this.clickURL = clickURL;
		PYAxisMaxValue = axisMaxValue;
		SYAxisMinValue = axisMinValue;
		SYAxisMaxValue = axisMaxValue2;
		PYAxisMinValue = axisMinValue2;
		this.setAdaptiveYMin = setAdaptiveYMin;
		this.setAdaptiveSYMin = setAdaptiveSYMin;
		PYAxisNameWidth = axisNameWidth;
		SYAxisNameWidth = axisNameWidth2;
		this.scrollToEnd = scrollToEnd;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public Integer getPalette() {
		return palette;
	}

	public void setPalette(Integer palette) {
		this.palette = palette;
	}

	public Boolean getConnectNullData() {
		return connectNullData;
	}

	public void setConnectNullData(Boolean connectNullData) {
		this.connectNullData = connectNullData;
	}

	public Boolean getAreaOverColumns() {
		return areaOverColumns;
	}

	public void setAreaOverColumns(Boolean areaOverColumns) {
		this.areaOverColumns = areaOverColumns;
	}

	public Boolean getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(Boolean showLabels) {
		this.showLabels = showLabels;
	}

	public String getLabelDisplay() {
		return labelDisplay;
	}

	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}

	public Boolean getRotateLabels() {
		return rotateLabels;
	}

	public void setRotateLabels(Boolean rotateLabels) {
		this.rotateLabels = rotateLabels;
	}

	public Boolean getSlantLabels() {
		return slantLabels;
	}

	public void setSlantLabels(Boolean slantLabels) {
		this.slantLabels = slantLabels;
	}

	public Integer getLabelStep() {
		return labelStep;
	}

	public void setLabelStep(Integer labelStep) {
		this.labelStep = labelStep;
	}

	public Integer getStaggerLines() {
		return staggerLines;
	}

	public void setStaggerLines(Integer staggerLines) {
		this.staggerLines = staggerLines;
	}

	public Boolean getShowValues() {
		return showValues;
	}

	public void setShowValues(Boolean showValues) {
		this.showValues = showValues;
	}

	public Boolean getRotateValues() {
		return rotateValues;
	}

	public void setRotateValues(Boolean rotateValues) {
		this.rotateValues = rotateValues;
	}

	public Boolean getPlaceValuesInside() {
		return placeValuesInside;
	}

	public void setPlaceValuesInside(Boolean placeValuesInside) {
		this.placeValuesInside = placeValuesInside;
	}

	public Boolean getShowYAxisValues() {
		return showYAxisValues;
	}

	public void setShowYAxisValues(Boolean showYAxisValues) {
		this.showYAxisValues = showYAxisValues;
	}

	public Boolean getShowLimits() {
		return showLimits;
	}

	public void setShowLimits(Boolean showLimits) {
		this.showLimits = showLimits;
	}

	public Boolean getShowDivLineValues() {
		return showDivLineValues;
	}

	public void setShowDivLineValues(Boolean showDivLineValues) {
		this.showDivLineValues = showDivLineValues;
	}

	public Boolean getShowSecondaryLimits() {
		return showSecondaryLimits;
	}

	public void setShowSecondaryLimits(Boolean showSecondaryLimits) {
		this.showSecondaryLimits = showSecondaryLimits;
	}

	public Boolean getShowDivLineSecondaryValue() {
		return showDivLineSecondaryValue;
	}

	public void setShowDivLineSecondaryValue(Boolean showDivLineSecondaryValue) {
		this.showDivLineSecondaryValue = showDivLineSecondaryValue;
	}

	public Boolean getDefaultAnimation() {
		return defaultAnimation;
	}

	public void setDefaultAnimation(Boolean defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
	}

	public Integer getYAxisValuesStep() {
		return yAxisValuesStep;
	}

	public void setYAxisValuesStep(Integer axisValuesStep) {
		yAxisValuesStep = axisValuesStep;
	}

	public Boolean getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(Boolean showShadow) {
		this.showShadow = showShadow;
	}

	public Boolean getAdjustDiv() {
		return adjustDiv;
	}

	public void setAdjustDiv(Boolean adjustDiv) {
		this.adjustDiv = adjustDiv;
	}

	public Boolean getRotateYAxisName() {
		return rotateYAxisName;
	}

	public void setRotateYAxisName(Boolean rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}

	public Integer getPYAxisMaxValue() {
		return PYAxisMaxValue;
	}

	public void setPYAxisMaxValue(Integer axisMaxValue) {
		PYAxisMaxValue = axisMaxValue;
	}

	public Integer getSYAxisMinValue() {
		return SYAxisMinValue;
	}

	public void setSYAxisMinValue(Integer axisMinValue) {
		SYAxisMinValue = axisMinValue;
	}

	public Integer getSYAxisMaxValue() {
		return SYAxisMaxValue;
	}

	public void setSYAxisMaxValue(Integer axisMaxValue) {
		SYAxisMaxValue = axisMaxValue;
	}

	public Integer getPYAxisMinValue() {
		return PYAxisMinValue;
	}

	public void setPYAxisMinValue(Integer axisMinValue) {
		PYAxisMinValue = axisMinValue;
	}

	public Boolean getSetAdaptiveYMin() {
		return setAdaptiveYMin;
	}

	public void setSetAdaptiveYMin(Boolean setAdaptiveYMin) {
		this.setAdaptiveYMin = setAdaptiveYMin;
	}

	public Boolean getSetAdaptiveSYMin() {
		return setAdaptiveSYMin;
	}

	public void setSetAdaptiveSYMin(Boolean setAdaptiveSYMin) {
		this.setAdaptiveSYMin = setAdaptiveSYMin;
	}

	public Integer getPYAxisNameWidth() {
		return PYAxisNameWidth;
	}

	public void setPYAxisNameWidth(Integer axisNameWidth) {
		PYAxisNameWidth = axisNameWidth;
	}

	public Integer getSYAxisNameWidth() {
		return SYAxisNameWidth;
	}

	public void setSYAxisNameWidth(Integer axisNameWidth) {
		SYAxisNameWidth = axisNameWidth;
	}

	public Integer getNumVisiblePlot() {
		return numVisiblePlot;
	}

	public void setNumVisiblePlot(Integer numVisiblePlot) {
		this.numVisiblePlot = numVisiblePlot;
	}

	public Boolean getScrollToEnd() {
		return scrollToEnd;
	}

	public void setScrollToEnd(Boolean scrollToEnd) {
		this.scrollToEnd = scrollToEnd;
	}
	
}
