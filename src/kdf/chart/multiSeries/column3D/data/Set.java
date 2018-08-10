package kdf.chart.multiSeries.column3D.data;

public class Set {
	private Double value;
	private String color;
	private String link;
	private String toolText;
	private Boolean showValue;
	private Integer alpha;
	
	public Set() {
		this.value = null;
		this.color = null;
		this.link = null;
		this.toolText = null;
		this.showValue = null;
		this.alpha = null;
	}

	public Set(Double value, String color, String link, String toolText,
			Boolean showValue, Integer alpha) {
		super();
		this.value = value;
		this.color = color;
		this.link = link;
		this.toolText = toolText;
		this.showValue = showValue;
		this.alpha = alpha;
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

	public Boolean getShowValue() {
		return showValue;
	}

	public void setShowValue(Boolean showValue) {
		this.showValue = showValue;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}
	
}
