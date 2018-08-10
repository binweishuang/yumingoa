package kdf.chart.singleSeries.line.chart;

public class FunctionalAttributes {
	private Boolean animation;
	private Integer palette;
	private Boolean connectNullData;
	private Boolean showLabels;
	private String labelDisplay;
	private Boolean rotateLabels;
	private Boolean slantLabels;
	private Integer labelStep;
	private Integer staggerLines;
	private Boolean showValues;
	private Boolean rotateValues;
	private Boolean showYAxisValues;
	private Boolean showLimits;
	private Boolean showDivLineValues;
	private Integer yAxisValuesStep;
	private Boolean adjustDiv;
	private Boolean rotateYAxisName;
	private Integer yAxisNameWidth;
	private String clickURL;
	private Boolean defaultAnimation;
	private Integer yAxisMinValue;
	private Integer yAxisMaxValue;
	private Boolean setAdaptiveYMin;
	
	public FunctionalAttributes(){
		this.animation = null;
		this.palette = null;
		this.connectNullData = null;
		this.showLabels = null;
		this.labelDisplay = null;
		this.rotateLabels = null;
		this.slantLabels = null;
		this.labelStep = null;
		this.staggerLines = null;
		this.showValues = null;
		this.rotateValues = null;
		this.showYAxisValues = null;
		this.showLimits = null;
		this.showDivLineValues = null;
		yAxisValuesStep = null;
		this.adjustDiv = null;
		this.rotateYAxisName = null;
		yAxisNameWidth = null;
		this.clickURL = null;
		this.defaultAnimation = null;
		yAxisMinValue = null;
		yAxisMaxValue = null;
		this.setAdaptiveYMin = null;
	}

	public FunctionalAttributes(Boolean animation, Integer palette,
			Boolean connectNullData, Boolean showLabels, String labelDisplay,
			Boolean rotateLabels, Boolean slantLabels, Integer labelStep,
			Integer staggerLines, Boolean showValues, Boolean rotateValues,
			Boolean showYAxisValues, Boolean showLimits,
			Boolean showDivLineValues, Integer axisValuesStep,
			Boolean adjustDiv, Boolean rotateYAxisName, Integer axisNameWidth,
			String clickURL, Boolean defaultAnimation, Integer axisMinValue,
			Integer axisMaxValue, Boolean setAdaptiveYMin) {
		super();
		this.animation = animation;
		this.palette = palette;
		this.connectNullData = connectNullData;
		this.showLabels = showLabels;
		this.labelDisplay = labelDisplay;
		this.rotateLabels = rotateLabels;
		this.slantLabels = slantLabels;
		this.labelStep = labelStep;
		this.staggerLines = staggerLines;
		this.showValues = showValues;
		this.rotateValues = rotateValues;
		this.showYAxisValues = showYAxisValues;
		this.showLimits = showLimits;
		this.showDivLineValues = showDivLineValues;
		yAxisValuesStep = axisValuesStep;
		this.adjustDiv = adjustDiv;
		this.rotateYAxisName = rotateYAxisName;
		yAxisNameWidth = axisNameWidth;
		this.clickURL = clickURL;
		this.defaultAnimation = defaultAnimation;
		yAxisMinValue = axisMinValue;
		yAxisMaxValue = axisMaxValue;
		this.setAdaptiveYMin = setAdaptiveYMin;
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

	public Boolean getRotateYAxisName() {
		return rotateYAxisName;
	}

	public void setRotateYAxisName(Boolean rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}

	public Integer getYAxisNameWidth() {
		return yAxisNameWidth;
	}

	public void setYAxisNameWidth(Integer axisNameWidth) {
		yAxisNameWidth = axisNameWidth;
	}

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}

	public Boolean getDefaultAnimation() {
		return defaultAnimation;
	}

	public void setDefaultAnimation(Boolean defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
	}

	public Integer getYAxisMinValue() {
		return yAxisMinValue;
	}

	public void setYAxisMinValue(Integer axisMinValue) {
		yAxisMinValue = axisMinValue;
	}

	public Integer getYAxisMaxValue() {
		return yAxisMaxValue;
	}

	public void setYAxisMaxValue(Integer axisMaxValue) {
		yAxisMaxValue = axisMaxValue;
	}

	public Boolean getSetAdaptiveYMin() {
		return setAdaptiveYMin;
	}

	public void setSetAdaptiveYMin(Boolean setAdaptiveYMin) {
		this.setAdaptiveYMin = setAdaptiveYMin;
	}
	
}
