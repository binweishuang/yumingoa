package kdf.chart.singleSeries.pie3D.others;

public class FontProperties {
	private String baseFont;
	private Integer baseFontSize;
	private String baseFontColor;

	public FontProperties() {
		this.baseFont = null;
		this.baseFontSize = null;
		this.baseFontColor = null;
	}

	public FontProperties(String baseFont, Integer baseFontSize,
			String baseFontColor) {
		super();
		this.baseFont = baseFont;
		this.baseFontSize = baseFontSize;
		this.baseFontColor = baseFontColor;
	}

	public String getBaseFont() {
		return baseFont;
	}

	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}

	public Integer getBaseFontSize() {
		return baseFontSize;
	}

	public void setBaseFontSize(Integer baseFontSize) {
		this.baseFontSize = baseFontSize;
	}

	public String getBaseFontColor() {
		return baseFontColor;
	}

	public void setBaseFontColor(String baseFontColor) {
		this.baseFontColor = baseFontColor;
	}
	
}
