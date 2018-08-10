package kdf.chart.singleValue.angularGauge.data;

public class Dial {
	private Double value;
	private String link;
	private String bgColor;
	private Integer radius;
	private Integer baseWidth;
	private Integer topWidth;
	private String borderColor;
	private Integer borderThickness;
	private Integer borderAlpha;
	
	public Dial() {
		this.value = null;
		this.link = null;
		this.bgColor = null;
		this.radius = null;
		this.baseWidth = null;
		this.topWidth = null;
		this.borderColor = null;
		this.borderThickness = null;
		this.borderAlpha = null;
	}

	public Dial(Double value, String link, String bgColor, Integer radius,
			Integer baseWidth, Integer topWidth, String borderColor,
			Integer borderThickness, Integer borderAlpha) {
		super();
		this.value = value;
		this.link = link;
		this.bgColor = bgColor;
		this.radius = radius;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.borderColor = borderColor;
		this.borderThickness = borderThickness;
		this.borderAlpha = borderAlpha;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getBaseWidth() {
		return baseWidth;
	}

	public void setBaseWidth(Integer baseWidth) {
		this.baseWidth = baseWidth;
	}

	public Integer getTopWidth() {
		return topWidth;
	}

	public void setTopWidth(Integer topWidth) {
		this.topWidth = topWidth;
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
