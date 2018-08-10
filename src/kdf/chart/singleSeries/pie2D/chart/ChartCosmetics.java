package kdf.chart.singleSeries.pie2D.chart;

public class ChartCosmetics {
	private String bgColor;
	private String bgAlpha;
	private String bgRatio;
	private Integer bgAngle;
	private String bgSWF;
	private Integer bgSWFAlpha;
	private Boolean showBorder;
	private String borderColor;
	private Integer borderThickness;
	private Integer borderAlpha;
	
	public ChartCosmetics() {
		this.bgColor = null;
		this.bgAlpha = null;
		this.bgRatio = null;
		this.bgAngle = null;
		this.bgSWF = null;
		this.bgSWFAlpha = null;
		this.showBorder = null;
		this.borderColor = null;
		this.borderThickness = null;
		this.borderAlpha = null;
	}

	public ChartCosmetics(String bgColor, String bgAlpha, String bgRatio,
			Integer bgAngle, String bgSWF, Integer bgSWFAlpha,
			Boolean showBorder, String borderColor, Integer borderThickness,
			Integer borderAlpha) {
		super();
		this.bgColor = bgColor;
		this.bgAlpha = bgAlpha;
		this.bgRatio = bgRatio;
		this.bgAngle = bgAngle;
		this.bgSWF = bgSWF;
		this.bgSWFAlpha = bgSWFAlpha;
		this.showBorder = showBorder;
		this.borderColor = borderColor;
		this.borderThickness = borderThickness;
		this.borderAlpha = borderAlpha;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getBgAlpha() {
		return bgAlpha;
	}

	public void setBgAlpha(String bgAlpha) {
		this.bgAlpha = bgAlpha;
	}

	public String getBgRatio() {
		return bgRatio;
	}

	public void setBgRatio(String bgRatio) {
		this.bgRatio = bgRatio;
	}

	public Integer getBgAngle() {
		return bgAngle;
	}

	public void setBgAngle(Integer bgAngle) {
		this.bgAngle = bgAngle;
	}

	public String getBgSWF() {
		return bgSWF;
	}

	public void setBgSWF(String bgSWF) {
		this.bgSWF = bgSWF;
	}

	public Integer getBgSWFAlpha() {
		return bgSWFAlpha;
	}

	public void setBgSWFAlpha(Integer bgSWFAlpha) {
		this.bgSWFAlpha = bgSWFAlpha;
	}

	public Boolean getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(Boolean showBorder) {
		this.showBorder = showBorder;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(Integer borderThickness) {
		this.borderThickness = borderThickness;
	}

	public Integer getBorderAlpha() {
		return borderAlpha;
	}

	public void setBorderAlpha(Integer borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	
}
