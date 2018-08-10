package kdf.chart.multiSeries.column3D.others;

public class FontProperties {
	private String baseFont;
	private Integer baseFontSize;
	private String baseFontColor;
	private String outCnvBaseFont;
	private Integer outCnvBaseFontSize;
	private String outCnvBaseFontColor;
	
	public FontProperties() {
		this.baseFont = null;
		this.baseFontSize = null;
		this.baseFontColor = null;
		this.outCnvBaseFont = null;
		this.outCnvBaseFontSize = null;
		this.outCnvBaseFontColor = null;
	}

	public FontProperties(String baseFont, Integer baseFontSize,
			String baseFontColor, String outCnvBaseFont,
			Integer outCnvBaseFontSize, String outCnvBaseFontColor) {
		super();
		this.baseFont = baseFont;
		this.baseFontSize = baseFontSize;
		this.baseFontColor = baseFontColor;
		this.outCnvBaseFont = outCnvBaseFont;
		this.outCnvBaseFontSize = outCnvBaseFontSize;
		this.outCnvBaseFontColor = outCnvBaseFontColor;
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

	public String getOutCnvBaseFont() {
		return outCnvBaseFont;
	}

	public void setOutCnvBaseFont(String outCnvBaseFont) {
		this.outCnvBaseFont = outCnvBaseFont;
	}

	public Integer getOutCnvBaseFontSize() {
		return outCnvBaseFontSize;
	}

	public void setOutCnvBaseFontSize(Integer outCnvBaseFontSize) {
		this.outCnvBaseFontSize = outCnvBaseFontSize;
	}

	public String getOutCnvBaseFontColor() {
		return outCnvBaseFontColor;
	}

	public void setOutCnvBaseFontColor(String outCnvBaseFontColor) {
		this.outCnvBaseFontColor = outCnvBaseFontColor;
	}
	
}
