package kdf.chart.multiSeries.column2D.data;

public class DataPlotCosmetics {
	private Boolean useRoundEdges;
	private Boolean showPlotBorder;
	private String plotBorderColor;
	private Integer plotBorderThickness;
	private Integer plotBorderAlpha;
	private Boolean plotBorderDashed;
	private Integer plotBorderDashLen;
	private Integer plotBorderDashGap;
	private Integer plotFillAngle;
	private Integer plotFillRatio;
	private Integer plotFillAlpha;
	private String plotGradientColor;
	
	public DataPlotCosmetics() {
		this.useRoundEdges = null;
		this.showPlotBorder = null;
		this.plotBorderColor = null;
		this.plotBorderThickness = null;
		this.plotBorderAlpha = null;
		this.plotBorderDashed = null;
		this.plotBorderDashLen = null;
		this.plotBorderDashGap = null;
		this.plotFillAngle = null;
		this.plotFillRatio = null;
		this.plotFillAlpha = null;
		this.plotGradientColor = null;
	}

	public DataPlotCosmetics(Boolean useRoundEdges, Boolean showPlotBorder,
			String plotBorderColor, Integer plotBorderThickness,
			Integer plotBorderAlpha, Boolean plotBorderDashed,
			Integer plotBorderDashLen, Integer plotBorderDashGap,
			Integer plotFillAngle, Integer plotFillRatio,
			Integer plotFillAlpha, String plotGradientColor) {
		super();
		this.useRoundEdges = useRoundEdges;
		this.showPlotBorder = showPlotBorder;
		this.plotBorderColor = plotBorderColor;
		this.plotBorderThickness = plotBorderThickness;
		this.plotBorderAlpha = plotBorderAlpha;
		this.plotBorderDashed = plotBorderDashed;
		this.plotBorderDashLen = plotBorderDashLen;
		this.plotBorderDashGap = plotBorderDashGap;
		this.plotFillAngle = plotFillAngle;
		this.plotFillRatio = plotFillRatio;
		this.plotFillAlpha = plotFillAlpha;
		this.plotGradientColor = plotGradientColor;
	}

	public Boolean getUseRoundEdges() {
		return useRoundEdges;
	}

	public void setUseRoundEdges(Boolean useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
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

	public Boolean getPlotBorderDashed() {
		return plotBorderDashed;
	}

	public void setPlotBorderDashed(Boolean plotBorderDashed) {
		this.plotBorderDashed = plotBorderDashed;
	}

	public Integer getPlotBorderDashLen() {
		return plotBorderDashLen;
	}

	public void setPlotBorderDashLen(Integer plotBorderDashLen) {
		this.plotBorderDashLen = plotBorderDashLen;
	}

	public Integer getPlotBorderDashGap() {
		return plotBorderDashGap;
	}

	public void setPlotBorderDashGap(Integer plotBorderDashGap) {
		this.plotBorderDashGap = plotBorderDashGap;
	}

	public Integer getPlotFillAngle() {
		return plotFillAngle;
	}

	public void setPlotFillAngle(Integer plotFillAngle) {
		this.plotFillAngle = plotFillAngle;
	}

	public Integer getPlotFillRatio() {
		return plotFillRatio;
	}

	public void setPlotFillRatio(Integer plotFillRatio) {
		this.plotFillRatio = plotFillRatio;
	}

	public Integer getPlotFillAlpha() {
		return plotFillAlpha;
	}

	public void setPlotFillAlpha(Integer plotFillAlpha) {
		this.plotFillAlpha = plotFillAlpha;
	}

	public String getPlotGradientColor() {
		return plotGradientColor;
	}

	public void setPlotGradientColor(String plotGradientColor) {
		this.plotGradientColor = plotGradientColor;
	}
	
}
