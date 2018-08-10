package kdf.chart.multiSeries.column2D.chart;

public class Legend {
	private Boolean showLegend;
	private String legendPosition;
	private String legendCaption;
	private Boolean legendMarkerCircle;
	private String legendBgColor;
	private Integer legendBgAlpha;
	private String legendBorderColor;
	private Integer legendBorderThickness;
	private Integer legendBorderAlpha;
	private Boolean legendShadow;
	private Boolean legendAllowDrag;
	private String legendScrollBgColor;
	private String legendScrollBarColor;
	private String legendScrollBtnColor;
	
	public Legend() {
		this.showLegend = null;
		this.legendPosition = null;
		this.legendCaption = null;
		this.legendMarkerCircle = null;
		this.legendBgColor = null;
		this.legendBgAlpha = null;
		this.legendBorderColor = null;
		this.legendBorderThickness = null;
		this.legendBorderAlpha = null;
		this.legendShadow = null;
		this.legendAllowDrag = null;
		this.legendScrollBgColor = null;
		this.legendScrollBarColor = null;
		this.legendScrollBtnColor = null;
	}

	public Legend(Boolean showLegend, String legendPosition,
			String legendCaption, Boolean legendMarkerCircle,
			String legendBgColor, Integer legendBgAlpha,
			String legendBorderColor, Integer legendBorderThickness,
			Integer legendBorderAlpha, Boolean legendShadow,
			Boolean legendAllowDrag, String legendScrollBgColor,
			String legendScrollBarColor, String legendScrollBtnColor) {
		super();
		this.showLegend = showLegend;
		this.legendPosition = legendPosition;
		this.legendCaption = legendCaption;
		this.legendMarkerCircle = legendMarkerCircle;
		this.legendBgColor = legendBgColor;
		this.legendBgAlpha = legendBgAlpha;
		this.legendBorderColor = legendBorderColor;
		this.legendBorderThickness = legendBorderThickness;
		this.legendBorderAlpha = legendBorderAlpha;
		this.legendShadow = legendShadow;
		this.legendAllowDrag = legendAllowDrag;
		this.legendScrollBgColor = legendScrollBgColor;
		this.legendScrollBarColor = legendScrollBarColor;
		this.legendScrollBtnColor = legendScrollBtnColor;
	}

	public Boolean getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend) {
		this.showLegend = showLegend;
	}

	public String getLegendPosition() {
		return legendPosition;
	}

	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}

	public String getLegendCaption() {
		return legendCaption;
	}

	public void setLegendCaption(String legendCaption) {
		this.legendCaption = legendCaption;
	}

	public Boolean getLegendMarkerCircle() {
		return legendMarkerCircle;
	}

	public void setLegendMarkerCircle(Boolean legendMarkerCircle) {
		this.legendMarkerCircle = legendMarkerCircle;
	}

	public String getLegendBgColor() {
		return legendBgColor;
	}

	public void setLegendBgColor(String legendBgColor) {
		this.legendBgColor = legendBgColor;
	}

	public Integer getLegendBgAlpha() {
		return legendBgAlpha;
	}

	public void setLegendBgAlpha(Integer legendBgAlpha) {
		this.legendBgAlpha = legendBgAlpha;
	}

	public String getLegendBorderColor() {
		return legendBorderColor;
	}

	public void setLegendBorderColor(String legendBorderColor) {
		this.legendBorderColor = legendBorderColor;
	}

	public Integer getLegendBorderThickness() {
		return legendBorderThickness;
	}

	public void setLegendBorderThickness(Integer legendBorderThickness) {
		this.legendBorderThickness = legendBorderThickness;
	}

	public Integer getLegendBorderAlpha() {
		return legendBorderAlpha;
	}

	public void setLegendBorderAlpha(Integer legendBorderAlpha) {
		this.legendBorderAlpha = legendBorderAlpha;
	}

	public Boolean getLegendShadow() {
		return legendShadow;
	}

	public void setLegendShadow(Boolean legendShadow) {
		this.legendShadow = legendShadow;
	}

	public Boolean getLegendAllowDrag() {
		return legendAllowDrag;
	}

	public void setLegendAllowDrag(Boolean legendAllowDrag) {
		this.legendAllowDrag = legendAllowDrag;
	}

	public String getLegendScrollBgColor() {
		return legendScrollBgColor;
	}

	public void setLegendScrollBgColor(String legendScrollBgColor) {
		this.legendScrollBgColor = legendScrollBgColor;
	}

	public String getLegendScrollBarColor() {
		return legendScrollBarColor;
	}

	public void setLegendScrollBarColor(String legendScrollBarColor) {
		this.legendScrollBarColor = legendScrollBarColor;
	}

	public String getLegendScrollBtnColor() {
		return legendScrollBtnColor;
	}

	public void setLegendScrollBtnColor(String legendScrollBtnColor) {
		this.legendScrollBtnColor = legendScrollBtnColor;
	}
	
}
