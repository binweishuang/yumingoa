package kdf.chart.singleValue.angularGauge.chart;

public class ChartPadding {
	private Integer chartLeftMargin;
	private Integer chartRightMargin;
	private Integer chartTopMargin;
	private Integer chartBottomMargin;
	
	public ChartPadding() {
		this.chartLeftMargin = null;
		this.chartRightMargin = null;
		this.chartTopMargin = null;
		this.chartBottomMargin = null;
	}

	public ChartPadding(Integer chartLeftMargin, Integer chartRightMargin,
			Integer chartTopMargin, Integer chartBottomMargin) {
		super();
		this.chartLeftMargin = chartLeftMargin;
		this.chartRightMargin = chartRightMargin;
		this.chartTopMargin = chartTopMargin;
		this.chartBottomMargin = chartBottomMargin;
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
