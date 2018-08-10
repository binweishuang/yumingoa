package kdf.chart.combination.column3DLineDualY.data;

public class DataPlotCosmetics {
	private Boolean overlapColumns;
	private Boolean showPlotBorder;
	private String plotBorderColor;
	private Integer plotBorderAlpha;
	private Integer plotFillAlpha;
	private String lineColor;
	private Integer lineThickness;
	private Integer lineAlpha;
	private Boolean lineDashed;
	private Integer lineDashLen;
	private Integer lineDashGap;
	
	public DataPlotCosmetics() {
		this.overlapColumns = null;
		this.showPlotBorder = null;
		this.plotBorderColor = null;
		this.plotBorderAlpha = null;
		this.plotFillAlpha = null;
		this.lineColor = null;
		this.lineThickness = null;
		this.lineAlpha = null;
		this.lineDashed = null;
		this.lineDashLen = null;
		this.lineDashGap = null;
	}

	public DataPlotCosmetics(Boolean overlapColumns, Boolean showPlotBorder,
			String plotBorderColor, Integer plotBorderAlpha, Integer plotFillAlpha, String lineColor,
			Integer lineThickness, Integer lineAlpha, Boolean lineDashed,
			Integer lineDashLen, Integer lineDashGap) {
		super();
		this.overlapColumns = overlapColumns;
		this.showPlotBorder = showPlotBorder;
		this.plotBorderColor = plotBorderColor;
		this.plotBorderAlpha = plotBorderAlpha;
		this.plotFillAlpha = plotFillAlpha;
		this.lineColor = lineColor;
		this.lineThickness = lineThickness;
		this.lineAlpha = lineAlpha;
		this.lineDashed = lineDashed;
		this.lineDashLen = lineDashLen;
		this.lineDashGap = lineDashGap;
	}

	public Boolean getOverlapColumns() {
		return overlapColumns;
	}

	public void setOverlapColumns(Boolean overlapColumns) {
		this.overlapColumns = overlapColumns;
	}

	public Boolean getShowPlotBorder() {
		return showPlotBorder;
	}

	public void setShowPlotBorder(Boolean showPlotBorder) {
		this.showPlotBorder = showPlotBorder;
	}

	public String getPlotBorderColor() {
		return plotBorderColor;
	}

	public void setPlotBorderColor(String plotBorderColor) {
		this.plotBorderColor = plotBorderColor;
	}

	public Integer getPlotBorderAlpha() {
		return plotBorderAlpha;
	}

	public void setPlotBorderAlpha(Integer plotBorderAlpha) {
		this.plotBorderAlpha = plotBorderAlpha;
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

	public Integer getPlotFillAlpha() {
		return plotFillAlpha;
	}

	public void setPlotFillAlpha(Integer plotFillAlpha) {
		this.plotFillAlpha = plotFillAlpha;
	}
	
}
