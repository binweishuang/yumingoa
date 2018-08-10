package kdf.chart.singleSeries.pie2D.data;

public class SmartLines {
	private Boolean enableSmartLabels;
	private Boolean skipOverlapLabels;
	private Boolean isSmartLineSlanted;
	private String smartLineColor;
	private Integer smartLineThickness;
	private Integer smartLineAlpha;
	private Integer labelDistance;
	private Integer smartLabelClearance;
	
	public SmartLines() {
		this.enableSmartLabels = null;
		this.skipOverlapLabels = null;
		this.isSmartLineSlanted = null;
		this.smartLineColor = null;
		this.smartLineThickness = null;
		this.smartLineAlpha = null;
		this.labelDistance = null;
		this.smartLabelClearance = null;
	}

	public SmartLines(Boolean enableSmartLabels, Boolean skipOverlapLabels,
			Boolean isSmartLineSlanted, String smartLineColor,
			Integer smartLineThickness, Integer smartLineAlpha,
			Integer labelDistance, Integer smartLabelClearance) {
		super();
		this.enableSmartLabels = enableSmartLabels;
		this.skipOverlapLabels = skipOverlapLabels;
		this.isSmartLineSlanted = isSmartLineSlanted;
		this.smartLineColor = smartLineColor;
		this.smartLineThickness = smartLineThickness;
		this.smartLineAlpha = smartLineAlpha;
		this.labelDistance = labelDistance;
		this.smartLabelClearance = smartLabelClearance;
	}

	public Boolean getEnableSmartLabels() {
		return enableSmartLabels;
	}

	public void setEnableSmartLabels(Boolean enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}

	public Boolean getSkipOverlapLabels() {
		return skipOverlapLabels;
	}

	public void setSkipOverlapLabels(Boolean skipOverlapLabels) {
		this.skipOverlapLabels = skipOverlapLabels;
	}

	public Boolean getIsSmartLineSlanted() {
		return isSmartLineSlanted;
	}

	public void setIsSmartLineSlanted(Boolean isSmartLineSlanted) {
		this.isSmartLineSlanted = isSmartLineSlanted;
	}

	public String getSmartLineColor() {
		return smartLineColor;
	}

	public void setSmartLineColor(String smartLineColor) {
		this.smartLineColor = smartLineColor;
	}

	public Integer getSmartLineThickness() {
		return smartLineThickness;
	}

	public void setSmartLineThickness(Integer smartLineThickness) {
		this.smartLineThickness = smartLineThickness;
	}

	public Integer getSmartLineAlpha() {
		return smartLineAlpha;
	}

	public void setSmartLineAlpha(Integer smartLineAlpha) {
		this.smartLineAlpha = smartLineAlpha;
	}

	public Integer getLabelDistance() {
		return labelDistance;
	}

	public void setLabelDistance(Integer labelDistance) {
		this.labelDistance = labelDistance;
	}

	public Integer getSmartLabelClearance() {
		return smartLabelClearance;
	}

	public void setSmartLabelClearance(Integer smartLabelClearance) {
		this.smartLabelClearance = smartLabelClearance;
	}
	
}
