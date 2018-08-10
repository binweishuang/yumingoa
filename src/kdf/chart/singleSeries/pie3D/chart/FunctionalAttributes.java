package kdf.chart.singleSeries.pie3D.chart;

public class FunctionalAttributes {
	private Boolean animation;
	private Integer palette;
	private Boolean showZeroPies;
	private Boolean showPercentValues;
	private Boolean showPercentInToolTip;
	private Boolean showLabels;
	private Boolean showValues;
	private Integer labelSepChar;
	private String clickURL;
	private Boolean defaultAnimation;
	
	public FunctionalAttributes() {
		this.animation = null;
		this.palette = null;
		this.showZeroPies = null;
		this.showPercentValues = null;
		this.showPercentInToolTip = null;
		this.showLabels = null;
		this.showValues = null;
		this.labelSepChar = null;
		this.clickURL = null;
		this.defaultAnimation = null;
	}

	public FunctionalAttributes(Boolean animation, Integer palette,
			Boolean showZeroPies, Boolean showPercentValues,
			Boolean showPercentInToolTip, Boolean showLabels,
			Boolean showValues, Integer labelSepChar, String clickURL,
			Boolean defaultAnimation) {
		super();
		this.animation = animation;
		this.palette = palette;
		this.showZeroPies = showZeroPies;
		this.showPercentValues = showPercentValues;
		this.showPercentInToolTip = showPercentInToolTip;
		this.showLabels = showLabels;
		this.showValues = showValues;
		this.labelSepChar = labelSepChar;
		this.clickURL = clickURL;
		this.defaultAnimation = defaultAnimation;
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

	public Boolean getShowZeroPies() {
		return showZeroPies;
	}

	public void setShowZeroPies(Boolean showZeroPies) {
		this.showZeroPies = showZeroPies;
	}

	public Boolean getShowPercentValues() {
		return showPercentValues;
	}

	public void setShowPercentValues(Boolean showPercentValues) {
		this.showPercentValues = showPercentValues;
	}

	public Boolean getShowPercentInToolTip() {
		return showPercentInToolTip;
	}

	public void setShowPercentInToolTip(Boolean showPercentInToolTip) {
		this.showPercentInToolTip = showPercentInToolTip;
	}

	public Boolean getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(Boolean showLabels) {
		this.showLabels = showLabels;
	}

	public Boolean getShowValues() {
		return showValues;
	}

	public void setShowValues(Boolean showValues) {
		this.showValues = showValues;
	}

	public Integer getLabelSepChar() {
		return labelSepChar;
	}

	public void setLabelSepChar(Integer labelSepChar) {
		this.labelSepChar = labelSepChar;
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
	
}
