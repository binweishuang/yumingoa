package kdf.chart.singleSeries.column3D.data;

public class DataPlotCosmetics {
	private Boolean showPlotBorder;
	private String plotBorderColor;
	private Integer plotBorderAlpha;
	private Integer plotFillAlpha;
	private Boolean overlapColumns;
	
	public DataPlotCosmetics() {
		this.showPlotBorder = null;
		this.plotBorderColor = null;
		this.plotBorderAlpha = null;
		this.plotFillAlpha = null;
		this.overlapColumns = null;
	}

	public DataPlotCosmetics(Boolean showPlotBorder, String plotBorderColor,
			Integer plotBorderAlpha, Integer plotFillAlpha,
			Boolean overlapColumns) {
		super();
		this.showPlotBorder = showPlotBorder;
		this.plotBorderColor = plotBorderColor;
		this.plotBorderAlpha = plotBorderAlpha;
		this.plotFillAlpha = plotFillAlpha;
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

	public Integer getPlotFillAlpha() {
		return plotFillAlpha;
	}

	public void setPlotFillAlpha(Integer plotFillAlpha) {
		this.plotFillAlpha = plotFillAlpha;
	}

	public Boolean getOverlapColumns() {
		return overlapColumns;
	}

	public void setOverlapColumns(Boolean overlapColumns) {
		this.overlapColumns = overlapColumns;
	}
	
}
