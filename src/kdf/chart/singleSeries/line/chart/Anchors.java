package kdf.chart.singleSeries.line.chart;

public class Anchors {
	private Boolean drawAnchors;
	private Integer anchorSides;
	private Integer anchorRadius;
	private String anchorBorderColor;
	private Integer anchorBorderThickness;
	private String anchorBgColor;
	private Integer anchorAlpha;
	private Integer anchorBgAlpha;
	
	public Anchors() {
		this.drawAnchors = null;
		this.anchorSides = null;
		this.anchorRadius = null;
		this.anchorBorderColor = null;
		this.anchorBorderThickness = null;
		this.anchorBgColor = null;
		this.anchorAlpha = null;
		this.anchorBgAlpha = null;
	}

	public Anchors(Boolean drawAnchors, Integer anchorSides,
			Integer anchorRadius, String anchorBorderColor,
			Integer anchorBorderThickness, String anchorBgColor,
			Integer anchorAlpha, Integer anchorBgAlpha) {
		super();
		this.drawAnchors = drawAnchors;
		this.anchorSides = anchorSides;
		this.anchorRadius = anchorRadius;
		this.anchorBorderColor = anchorBorderColor;
		this.anchorBorderThickness = anchorBorderThickness;
		this.anchorBgColor = anchorBgColor;
		this.anchorAlpha = anchorAlpha;
		this.anchorBgAlpha = anchorBgAlpha;
	}

	public Boolean getDrawAnchors() {
		return drawAnchors;
	}

	public void setDrawAnchors(Boolean drawAnchors) {
		this.drawAnchors = drawAnchors;
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
