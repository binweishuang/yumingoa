package kdf.chart.singleSeries.column2D.data;

public class Set {
	private String label;
	private Double value;
	private String color;
	private String link;
	private String toolText;
	private Boolean showLabel;
	private Boolean showValue;
	private Boolean dashed;
	private Integer alpha;
	
	public Set(){
		this.label = null;
		this.value = null;
		this.color = null;
		this.link = null;
		this.toolText = null;
		this.showLabel = null;
		this.showValue = null;
		this.dashed = null;
		this.alpha = null;
	}

	public Set(String label, Double value, String color, String link,
			String toolText, Boolean showLabel, Boolean showValue,
			Boolean dashed, Integer alpha) {
		super();
		this.label = label;
		this.value = value;
		this.color = color;
		this.link = link;
		this.toolText = toolText;
		this.showLabel = showLabel;
		this.showValue = showValue;
		this.dashed = dashed;
		this.alpha = alpha;
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

	public Boolean getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(Boolean showLabel) {
		this.showLabel = showLabel;
	}

	public Boolean getShowValue() {
		return showValue;
	}

	public void setShowValue(Boolean showValue) {
		this.showValue = showValue;
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
