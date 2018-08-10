package kdf.chart.singleValue.angularGauge.chart;

public class ChartCosmetics {
	private String bgColor;
	private String bgAlpha;
	private String bgSWF;
	private Boolean showBorder;
	private String borderColor;
	private Integer borderThickness;
	
	public ChartCosmetics() {
		this.bgColor = null;
		this.bgAlpha = null;
		this.bgSWF = null;
		this.showBorder = null;
		this.borderColor = null;
		this.borderThickness = null;
	}

	public ChartCosmetics(String bgColor, String bgAlpha, String bgSWF,
			Boolean showBorder, String borderColor, Integer borderThickness) {
		super();
		this.bgColor = bgColor;
		this.bgAlpha = bgAlpha;
		this.bgSWF = bgSWF;
		this.showBorder = showBorder;
		this.borderColor = borderColor;
		this.borderThickness = borderThickness;
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

	public String getBgSWF() {
		return bgSWF;
	}

	public void setBgSWF(String bgSWF) {
		this.bgSWF = bgSWF;
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
	
}
