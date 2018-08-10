package kdf.chart.singleSeries.pie3D.data;

public class Set {
	private String borderColor;
	private Boolean isSliced;	
	private String label;
	private Double value;
	private String color;
	private String link;
	private String toolText;	
	
	public Set() {
		this.borderColor = null;
		this.isSliced = null;
		this.label = null;
		this.value = null;
		this.color = null;
		this.link = null;
		this.toolText = null;
	}

	public Set(String borderColor, Boolean isSliced,
			String label, Double value, String color, String link,
			String toolText) {
		super();
		this.borderColor = borderColor;
		this.isSliced = isSliced;
		this.label = label;
		this.value = value;
		this.color = color;
		this.link = link;
		this.toolText = toolText;
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
}
