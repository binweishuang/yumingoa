package kdf.chart.singleSeries.pie3D.chart;

public class ChartPadding {
	private Integer captionPadding;
	private Integer chartLeftMargin;
	private Integer chartRightMargin;
	private Integer chartTopMargin;
	private Integer chartBottomMargin;
	
	public ChartPadding() {
		this.captionPadding = null;
		this.chartLeftMargin = null;
		this.chartRightMargin = null;
		this.chartTopMargin = null;
		this.chartBottomMargin = null;
	}

	public ChartPadding(Integer captionPadding, Integer chartLeftMargin,
			Integer chartRightMargin, Integer chartTopMargin,
			Integer chartBottomMargin) {
		super();
		this.captionPadding = captionPadding;
		this.chartLeftMargin = chartLeftMargin;
		this.chartRightMargin = chartRightMargin;
		this.chartTopMargin = chartTopMargin;
		this.chartBottomMargin = chartBottomMargin;
	}

	public Integer getCaptionPadding() {
		return captionPadding;
	}

	public void setCaptionPadding(Integer captionPadding) {
		this.captionPadding = captionPadding;
	}

	public Integer getChartLeftMargin() {
		return chartLeftMargin;
	}

	public void setChartLeftMargin(Integer chartLeftMargin) {
		this.chartLeftMargin = chartLeftMargin;
	}

	public Integer getChartRightMargin() {
		return chartRightMargin;
	}

	public void setChartRightMargin(Integer chartRightMargin) {
		this.chartRightMargin = chartRightMargin;
	}

	public Integer getChartTopMargin() {
		return chartTopMargin;
	}

	public void setChartTopMargin(Integer chartTopMargin) {
		this.chartTopMargin = chartTopMargin;
	}

	public Integer getChartBottomMargin() {
		return chartBottomMargin;
	}

	public void setChartBottomMargin(Integer chartBottomMargin) {
		this.chartBottomMargin = chartBottomMargin;
	}
	
}
