package kdf.chart.singleValue.angularGauge.chart;

public class FunctionalAttributes {
	private Double lowerLimit;
	private Double upperLimit;
	private String lowerLimitDisplay;
	private String upperLimitDisplay;
	private Boolean showLimits;
	private Integer gaugeOuterRadius;
	private Integer gaugeInnerRadius;
	private Integer gaugeAlpha;
	private Boolean animation;
	private String clickURL;
	private Integer gaugeScaleAngle;
	private Integer gaugeStartAngle;
	private Integer gaugeOriginX;
	private Integer gaugeOriginY;
	private Boolean placeValuesInside;
	
	public FunctionalAttributes(){
		this.lowerLimit = null;
		this.upperLimit = null;
		this.lowerLimitDisplay = null;
		this.upperLimitDisplay = null;
		this.showLimits = null;
		this.gaugeOuterRadius = null;
		this.gaugeInnerRadius = null;
		this.gaugeAlpha = null;
		this.animation = null;
		this.clickURL = null;
		this.gaugeScaleAngle = null;
		this.gaugeStartAngle = null;
		this.gaugeOriginX = null;
		this.gaugeOriginY = null;
		this.placeValuesInside = null;
	}

	

	public FunctionalAttributes(Double lowerLimit, Double upperLimit,
			String lowerLimitDisplay, String upperLimitDisplay,
			Boolean showLimits, Integer gaugeOuterRadius,
			Integer gaugeInnerRadius, Integer gaugeAlpha, Boolean animation,
			String clickURL, Integer gaugeScaleAngle, Integer gaugeStartAngle,
			Integer gaugeOriginX, Integer gaugeOriginY,Boolean placeValuesInside) {
		super();
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.lowerLimitDisplay = lowerLimitDisplay;
		this.upperLimitDisplay = upperLimitDisplay;
		this.showLimits = showLimits;
		this.gaugeOuterRadius = gaugeOuterRadius;
		this.gaugeInnerRadius = gaugeInnerRadius;
		this.gaugeAlpha = gaugeAlpha;
		this.animation = animation;
		this.clickURL = clickURL;
		this.gaugeScaleAngle = gaugeScaleAngle;
		this.gaugeStartAngle = gaugeStartAngle;
		this.gaugeOriginX = gaugeOriginX;
		this.gaugeOriginY = gaugeOriginY;
		this.placeValuesInside = placeValuesInside;
	}



	public Double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getLowerLimitDisplay() {
		return lowerLimitDisplay;
	}

	public void setLowerLimitDisplay(String lowerLimitDisplay) {
		this.lowerLimitDisplay = lowerLimitDisplay;
	}

	public String getUpperLimitDisplay() {
		return upperLimitDisplay;
	}

	public void setUpperLimitDisplay(String upperLimitDisplay) {
		this.upperLimitDisplay = upperLimitDisplay;
	}

	public Boolean getShowLimits() {
		return showLimits;
	}

	public void setShowLimits(Boolean showLimits) {
		this.showLimits = showLimits;
	}

	public Integer getGaugeOuterRadius() {
		return gaugeOuterRadius;
	}

	public void setGaugeOuterRadius(Integer gaugeOuterRadius) {
		this.gaugeOuterRadius = gaugeOuterRadius;
	}

	public Integer getGaugeInnerRadius() {
		return gaugeInnerRadius;
	}

	public void setGaugeInnerRadius(Integer gaugeInnerRadius) {
		this.gaugeInnerRadius = gaugeInnerRadius;
	}

	public Integer getGaugeAlpha() {
		return gaugeAlpha;
	}

	public void setGaugeAlpha(Integer gaugeAlpha) {
		this.gaugeAlpha = gaugeAlpha;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}



	public Integer getGaugeScaleAngle() {
		return gaugeScaleAngle;
	}



	public void setGaugeScaleAngle(Integer gaugeScaleAngle) {
		this.gaugeScaleAngle = gaugeScaleAngle;
	}



	public Integer getGaugeStartAngle() {
		return gaugeStartAngle;
	}



	public void setGaugeStartAngle(Integer gaugeStartAngle) {
		this.gaugeStartAngle = gaugeStartAngle;
	}



	public Integer getGaugeOriginX() {
		return gaugeOriginX;
	}



	public void setGaugeOriginX(Integer gaugeOriginX) {
		this.gaugeOriginX = gaugeOriginX;
	}



	public Integer getGaugeOriginY() {
		return gaugeOriginY;
	}



	public void setGaugeOriginY(Integer gaugeOriginY) {
		this.gaugeOriginY = gaugeOriginY;
	}



	public Boolean getPlaceValuesInside() {
		return placeValuesInside;
	}



	public void setPlaceValuesInside(Boolean placeValuesInside) {
		this.placeValuesInside = placeValuesInside;
	}
	
}
