package kdf.chart.singleSeries.pie2D.data;

public class DataPlotCosmetics {
	private Boolean showPlotBorder;
	private String plotBorderColor;
	private Integer plotBorderThickness;
	private Integer plotBorderAlpha;
	private Integer plotFillAngle;
	private Boolean showShadow;
	private Boolean use3DLighting;
	
	public DataPlotCosmetics() {
		this.showPlotBorder = null;
		this.plotBorderColor = null;
		this.plotBorderThickness = null;
		this.plotBorderAlpha = null;
		this.plotFillAngle = null;
		this.showShadow = null;
		this.use3DLighting = null;
	}

	public DataPlotCosmetics(Boolean showPlotBorder, String plotBorderColor,
			Integer plotBorderThickness, Integer plotBorderAlpha,
			Integer plotFillAngle, Boolean showShadow, Boolean use3DLighting) {
		super();
		this.showPlotBorder = showPlotBorder;
		this.plotBorderColor = plotBorderColor;
		this.plotBorderThickness = plotBorderThickness;
		this.plotBorderAlpha = plotBorderAlpha;
		this.plotFillAngle = plotFillAngle;
		this.showShadow = showShadow;
		this.use3DLighting = use3DLighting;
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

	public Integer getPlotBorderThickness() {
		return plotBorderThickness;
	}

	public void setPlotBorderThickness(Integer plotBorderThickness) {
		this.plotBorderThickness = plotBorderThickness;
	}

	public Integer getPlotBorderAlpha() {
		return plotBorderAlpha;
	}

	public void setPlotBorderAlpha(Integer plotBorderAlpha) {
		this.plotBorderAlpha = plotBorderAlpha;
	}

	public Integer getPlotFillAngle() {
		return plotFillAngle;
	}

	public void setPlotFillAngle(Integer plotFillAngle) {
		this.plotFillAngle = plotFillAngle;
	}

	public Boolean getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(Boolean showShadow) {
		this.showShadow = showShadow;
	}

	public Boolean getUse3DLighting() {
		return use3DLighting;
	}

	public void setUse3DLighting(Boolean use3DLighting) {
		this.use3DLighting = use3DLighting;
	}
	
}
