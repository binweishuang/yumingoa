package kdf.chart.singleSeries.pie2D.data;

public class Set {
	private String borderColor;
	private String borderAlpha;
	private Boolean isSliced;	
	private String label;
	private Double value;
	private String color;
	private String link;
	private String toolText;	
	private Boolean dashed;
	private Integer alpha;
	
	public Set() {
		this.borderColor = null;
		this.borderAlpha = null;
		this.isSliced = null;
		this.label = null;
		this.value = null;
		this.color = null;
		this.link = null;
		this.toolText = null;
		this.dashed = null;
		this.alpha = null;
	}

	public Set(String borderColor, String borderAlpha, Boolean isSliced,
			String label, Double value, String color, String link,
			String toolText, Boolean dashed, Integer alpha) {
		super();
		this.borderColor = borderColor;
		this.borderAlpha = borderAlpha;
		this.isSliced = isSliced;
		this.label = label;
		this.value = value;
		this.color = color;
		this.link = link;
		this.toolText = toolText;
		this.dashed = dashed;
		this.alpha = alpha;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Boolean getIsSliced() {
		return isSliced;
	}

	public void setIsSliced(Boolean isSliced) {
		this.isSliced = isSliced;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getToolText() {
		return toolText;
	}

	public void setToolText(String toolText) {
		this.toolText = toolText;
	}

	public String getBorderAlpha() {
		return borderAlpha;
	}

	public void setBorderAlpha(String borderAlpha) {
		this.borderAlpha = borderAlpha;
	}

	public Boolean getDashed() {
		return dashed;
	}

	public void setDashed(Boolean dashed) {
		this.dashed = dashed;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}
	
}
