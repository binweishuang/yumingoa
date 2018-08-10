package kdf.chart.singleValue.angularGauge.data;

public class Color {
	private Double minValue;
	private Double maxValue;
	private String name;
	private String code;
	private String borderColor;
	private Integer alpha;
	
	public Color() {
		this.minValue = null;
		this.maxValue = null;
		this.name = null;
		this.code = null;
		this.borderColor = null;
		this.alpha = null;
	}

	public Color(Double minValue, Double maxValue, String name, String code,
			String borderColor, Integer alpha) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.name = name;
		this.code = code;
		this.borderColor = borderColor;
		this.alpha = alpha;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}
	
}
