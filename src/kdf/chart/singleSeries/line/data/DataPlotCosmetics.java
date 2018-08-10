package kdf.chart.singleSeries.line.data;

public class DataPlotCosmetics {
	private Boolean showShadow;
	private String lineColor;
	private Integer lineThickness;
	private Integer lineAlpha;
	private Boolean lineDashed;
	private Integer lineDashLen;
	private Integer lineDashGap;
	
	public DataPlotCosmetics() {
		this.showShadow = null;
		this.lineColor = null;
		this.lineThickness = null;
		this.lineAlpha = null;
		this.lineDashed = null;
		this.lineDashLen = null;
		this.lineDashGap = null;
	}

	public DataPlotCosmetics(Boolean showShadow, String lineColor,
			Integer lineThickness, Integer lineAlpha, Boolean lineDashed,
			Integer lineDashLen, Integer lineDashGap) {
		super();
		this.showShadow = showShadow;
		this.lineColor = lineColor;
		this.lineThickness = lineThickness;
		this.lineAlpha = lineAlpha;
		this.lineDashed = lineDashed;
		this.lineDashLen = lineDashLen;
		this.lineDashGap = lineDashGap;
	}

	public Boolean getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(Boolean showShadow) {
		this.showShadow = showShadow;
	}

	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	public Integer getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(Integer lineThickness) {
		this.lineThickness = lineThickness;
	}

	public Integer getLineAlpha() {
		return lineAlpha;
	}

	public void setLineAlpha(Integer lineAlpha) {
		this.lineAlpha = lineAlpha;
	}

	public Boolean getLineDashed() {
		return lineDashed;
	}

	public void setLineDashed(Boolean lineDashed) {
		this.lineDashed = lineDashed;
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
	
}
