package kdf.chart.combination.column3DLineDualY.chart;

public class FunctionalAttributes {
	private Boolean animation;
	private Integer palette;
	private Boolean showLabels;
	private String labelDisplay;
	private Boolean rotateLabels;
	private Boolean slantLabels;
	private Integer labelStep;
	private Integer staggerLines;
	private Boolean connectNullData;
	private Boolean showValues;
	private Boolean rotateValues;
	private Boolean showYAxisValues;
	private Boolean showLimits;
	private Boolean showDivLineValues;
	private Integer yAxisValuesStep;	
	private Boolean adjustDiv;
	private Boolean showSecondaryLimits;
	private Boolean showDivLineSecondaryValue;
	private String clickURL;
	private Integer maxColWidth;
	private Boolean use3DLighting;
	private Boolean defaultAnimation;
	private Boolean showShadow;	
	private Integer PYAxisMaxValue;
	private Integer PYAxisMinValue;
	private Integer SYAxisMinValue;	
	private Integer SYAxisMaxValue;
	private Boolean setAdaptiveYMin;
	private Boolean setAdaptiveSYMin;
	private Boolean rotateYAxisName;
	private Integer PYAxisNameWidth;
	private Integer SYAxisNameWidth;
	
	public FunctionalAttributes() {
		this.animation = null;
		this.palette = null;
		this.showLabels = null;
		this.labelDisplay = null;
		this.rotateLabels = null;
		this.slantLabels = null;
		this.labelStep = null;
		this.staggerLines = null;
		this.connectNullData = null;
		this.showValues = null;
		this.rotateValues = null;
		this.showYAxisValues = null;
		this.showLimits = null;
		this.showDivLineValues = null;
		yAxisValuesStep = null;
		this.adjustDiv = null;
		this.showSecondaryLimits = null;
		this.showDivLineSecondaryValue = null;
		this.clickURL = null;
		this.maxColWidth = null;
		this.use3DLighting = null;
		this.defaultAnimation = null;
		this.showShadow = null;
		PYAxisMaxValue = null;
		PYAxisMinValue = null;
		SYAxisMinValue = null;
		SYAxisMaxValue = null;
		this.setAdaptiveYMin = null;
		this.setAdaptiveSYMin = null;
		this.rotateYAxisName = null;
		PYAxisNameWidth = null;
		SYAxisNameWidth = null;
	}

	public FunctionalAttributes(Boolean animation, Integer palette,
			Boolean showLabels, String labelDisplay, Boolean rotateLabels,
			Boolean slantLabels, Integer labelStep, Integer staggerLines,
			Boolean connectNullData, Boolean showValues, Boolean rotateValues,
			Boolean showYAxisValues, Boolean showLimits,
			Boolean showDivLineValues, Integer axisValuesStep,
			Boolean adjustDiv, Boolean showSecondaryLimits,
			Boolean showDivLineSecondaryValue, String clickURL,
			Integer maxColWidth, Boolean use3DLighting,
			Boolean defaultAnimation, Boolean showShadow, Integer axisMaxValue,
			Integer axisMinValue, Integer axisMinValue2, Integer axisMaxValue2,
			Boolean setAdaptiveYMin, Boolean setAdaptiveSYMin,
			Boolean rotateYAxisName, Integer axisNameWidth,
			Integer axisNameWidth2) {
		super();
		this.animation = animation;
		this.palette = palette;
		this.showLabels = showLabels;
		this.labelDisplay = labelDisplay;
		this.rotateLabels = rotateLabels;
		this.slantLabels = slantLabels;
		this.labelStep = labelStep;
		this.staggerLines = staggerLines;
		this.connectNullData = connectNullData;
		this.showValues = showValues;
		this.rotateValues = rotateValues;
		this.showYAxisValues = showYAxisValues;
		this.showLimits = showLimits;
		this.showDivLineValues = showDivLineValues;
		yAxisValuesStep = axisValuesStep;
		this.adjustDiv = adjustDiv;
		this.showSecondaryLimits = showSecondaryLimits;
		this.showDivLineSecondaryValue = showDivLineSecondaryValue;
		this.clickURL = clickURL;
		this.maxColWidth = maxColWidth;
		this.use3DLighting = use3DLighting;
		this.defaultAnimation = defaultAnimation;
		this.showShadow = showShadow;
		PYAxisMaxValue = axisMaxValue;
		PYAxisMinValue = axisMinValue;
		SYAxisMinValue = axisMinValue2;
		SYAxisMaxValue = axisMaxValue2;
		this.setAdaptiveYMin = setAdaptiveYMin;
		this.setAdaptiveSYMin = setAdaptiveSYMin;
		this.rotateYAxisName = rotateYAxisName;
		PYAxisNameWidth = axisNameWidth;
		SYAxisNameWidth = axisNameWidth2;
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

	public Boolean getConnectNullData() {
		return connectNullData;
	}

	public void setConnectNullData(Boolean connectNullData) {
		this.connectNullData = connectNullData;
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

	public Integer getYAxisValuesStep() {
		return yAxisValuesStep;
	}

	public void setYAxisValuesStep(Integer axisValuesStep) {
		yAxisValuesStep = axisValuesStep;
	}

	public Boolean getAdjustDiv() {
		return adjustDiv;
	}

	public void setAdjustDiv(Boolean adjustDiv) {
		this.adjustDiv = adjustDiv;
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

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}

	public Integer getMaxColWidth() {
		return maxColWidth;
	}

	public void setMaxColWidth(Integer maxColWidth) {
		this.maxColWidth = maxColWidth;
	}

	public Boolean getUse3DLighting() {
		return use3DLighting;
	}

	public void setUse3DLighting(Boolean use3DLighting) {
		this.use3DLighting = use3DLighting;
	}

	public Boolean getDefaultAnimation() {
		return defaultAnimation;
	}

	public void setDefaultAnimation(Boolean defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
	}

	public Boolean getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(Boolean showShadow) {
		this.showShadow = showShadow;
	}

	public Integer getPYAxisMaxValue() {
		return PYAxisMaxValue;
	}

	public void setPYAxisMaxValue(Integer axisMaxValue) {
		PYAxisMaxValue = axisMaxValue;
	}

	public Integer getPYAxisMinValue() {
		return PYAxisMinValue;
	}

	public void setPYAxisMinValue(Integer axisMinValue) {
		PYAxisMinValue = axisMinValue;
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

	public Boolean getRotateYAxisName() {
		return rotateYAxisName;
	}

	public void setRotateYAxisName(Boolean rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
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
	
}
