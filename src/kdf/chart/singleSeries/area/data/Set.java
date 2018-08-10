package kdf.chart.singleSeries.area.data;

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
	private Integer anchorSides;
	private Integer anchorRadius;
	private String anchorBorderColor;
	private Integer anchorBorderThickness;
	private String anchorBgColor;
	private Integer anchorAlpha;
	private Integer anchorBgAlpha;
	
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
		this.dashed = null;
		this.anchorSides = null;
		this.anchorRadius = null;
		this.anchorBorderColor = null;
		this.anchorBorderThickness = null;
		this.anchorBgColor = null;
		this.anchorAlpha = null;
		this.anchorBgAlpha = null;
	}

	public Set(String label, Double value, String color, String link,
			String toolText, Boolean showLabel, Boolean showValue,
			Boolean dashed, Integer alpha, Integer anchorSides,
			Integer anchorRadius, String anchorBorderColor,
			Integer anchorBorderThickness, String anchorBgColor,
			Integer anchorAlpha, Integer anchorBgAlpha) {
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
		this.anchorSides = anchorSides;
		this.anchorRadius = anchorRadius;
		this.anchorBorderColor = anchorBorderColor;
		this.anchorBorderThickness = anchorBorderThickness;
		this.anchorBgColor = anchorBgColor;
		this.anchorAlpha = anchorAlpha;
		this.anchorBgAlpha = anchorBgAlpha;
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

	public Integer getAnchorSides() {
		return anchorSides;
	}

	public void setAnchorSides(Integer anchorSides) {
		this.anchorSides = anchorSides;
	}

	public Integer getAnchorRadius() {
		return anchorRadius;
	}

	public void setAnchorRadius(Integer anchorRadius) {
		this.anchorRadius = anchorRadius;
	}

	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}

	public void setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
	}

	public Integer getAnchorBorderThickness() {
		return anchorBorderThickness;
	}

	public void setAnchorBorderThickness(Integer anchorBorderThickness) {
		this.anchorBorderThickness = anchorBorderThickness;
	}

	public String getAnchorBgColor() {
		return anchorBgColor;
	}

	public void setAnchorBgColor(String anchorBgColor) {
		this.anchorBgColor = anchorBgColor;
	}

	public Integer getAnchorAlpha() {
		return anchorAlpha;
	}

	public void setAnchorAlpha(Integer anchorAlpha) {
		this.anchorAlpha = anchorAlpha;
	}

	public Integer getAnchorBgAlpha() {
		return anchorBgAlpha;
	}

	public void setAnchorBgAlpha(Integer anchorBgAlpha) {
		this.anchorBgAlpha = anchorBgAlpha;
	}
		
}
