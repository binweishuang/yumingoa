package kdf.chart.combination.column3DLineDualY.data;

import java.util.List;

public class Dataset {
	private String parentYAxis;
	private String seriesName;
	private String color;
	private String alpha;
	private Boolean showValues;
	private Boolean includeInLegend;
	private Boolean drawAnchors;
	private Integer anchorSides;
	private Integer anchorRadius;
	private String anchorBorderColor;
	private Integer anchorBorderThickness;
	private String anchorBgColor;
	private Integer anchorAlpha;
	private Integer anchorBgAlpha;
	private Integer lineThickness;
	private Boolean dashed;
	private Integer lineDashLen;
	private Integer lineDashGap;	
	private List sets;
	
	public Dataset() {
		this.parentYAxis = null;
		this.seriesName = null;
		this.color = null;
		this.alpha = null;
		this.showValues = null;
		this.includeInLegend = null;
		this.drawAnchors = null;
		this.anchorSides = null;
		this.anchorRadius = null;
		this.anchorBorderColor = null;
		this.anchorBorderThickness = null;
		this.anchorBgColor = null;
		this.anchorAlpha = null;
		this.anchorBgAlpha = null;
		this.lineThickness = null;
		this.dashed = null;
		this.lineDashLen = null;
		this.lineDashGap = null;
		this.sets = null;
	}

	public Dataset(String parentYAxis, String seriesName, String color,
			String alpha, Boolean showValues, Boolean includeInLegend,
			Boolean drawAnchors, Integer anchorSides, Integer anchorRadius,
			String anchorBorderColor, Integer anchorBorderThickness,
			String anchorBgColor, Integer anchorAlpha, Integer anchorBgAlpha,
			Integer lineThickness, Boolean dashed, Integer lineDashLen,
			Integer lineDashGap, List sets) {
		super();
		this.parentYAxis = parentYAxis;
		this.seriesName = seriesName;
		this.color = color;
		this.alpha = alpha;
		this.showValues = showValues;
		this.includeInLegend = includeInLegend;
		this.drawAnchors = drawAnchors;
		this.anchorSides = anchorSides;
		this.anchorRadius = anchorRadius;
		this.anchorBorderColor = anchorBorderColor;
		this.anchorBorderThickness = anchorBorderThickness;
		this.anchorBgColor = anchorBgColor;
		this.anchorAlpha = anchorAlpha;
		this.anchorBgAlpha = anchorBgAlpha;
		this.lineThickness = lineThickness;
		this.dashed = dashed;
		this.lineDashLen = lineDashLen;
		this.lineDashGap = lineDashGap;
		this.sets = sets;
	}

	public String getParentYAxis() {
		return parentYAxis;
	}

	public void setParentYAxis(String parentYAxis) {
		this.parentYAxis = parentYAxis;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public Boolean getShowValues() {
		return showValues;
	}

	public void setShowValues(Boolean showValues) {
		this.showValues = showValues;
	}

	public Boolean getIncludeInLegend() {
		return includeInLegend;
	}

	public void setIncludeInLegend(Boolean includeInLegend) {
		this.includeInLegend = includeInLegend;
	}

	public Boolean getDrawAnchors() {
		return drawAnchors;
	}

	public void setDrawAnchors(Boolean drawAnchors) {
		this.drawAnchors = drawAnchors;
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

	public Integer getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(Integer lineThickness) {
		this.lineThickness = lineThickness;
	}

	public Boolean getDashed() {
		return dashed;
	}

	public void setDashed(Boolean dashed) {
		this.dashed = dashed;
	}

	public Integer getLineDashLen() {
		return lineDashLen;
	}

	public void setLineDashLen(Integer lineDashLen) {
		this.lineDashLen = lineDashLen;
	}

	public Integer getLineDashGap() {
		return lineDashGap;
	}

	public void setLineDashGap(Integer lineDashGap) {
		this.lineDashGap = lineDashGap;
	}
	
	public Integer getAnchorSides() {
		return anchorSides;
	}

	public void setAnchorSides(Integer anchorSides) {
		this.anchorSides = anchorSides;
	}

	public List getSets() {
		return sets;
	}

	public void setSets(List sets) {
		this.sets = sets;
	}
	
}
