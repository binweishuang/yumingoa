package kdf.chart.singleSeries.line.chart;

public class ChartPadding {
	private Integer canvasPadding;
	private Integer captionPadding;
	private Integer xAxisNamePadding;
	private Integer yAxisNamePadding;
	private Integer yAxisValuesPadding;
	private Integer labelPadding;
	private Integer valuePadding;
	private Integer chartLeftMargin;
	private Integer chartRightMargin;
	private Integer chartTopMargin;
	private Integer chartBottomMargin;
	
	public ChartPadding() {
		this.canvasPadding = null;
		this.captionPadding = null;
		xAxisNamePadding = null;
		yAxisNamePadding = null;
		yAxisValuesPadding = null;
		this.labelPadding = null;
		this.valuePadding = null;
		this.chartLeftMargin = null;
		this.chartRightMargin = null;
		this.chartTopMargin = null;
		this.chartBottomMargin = null;
	}

	public ChartPadding(Integer canvasPadding, Integer captionPadding, Integer axisNamePadding,
			Integer axisNamePadding2, Integer axisValuesPadding,
			Integer labelPadding, Integer valuePadding,
			Integer chartLeftMargin,
			Integer chartRightMargin, Integer chartTopMargin,
			Integer chartBottomMargin) {
		super();
		this.canvasPadding = canvasPadding;
		this.captionPadding = captionPadding;
		xAxisNamePadding = axisNamePadding;
		yAxisNamePadding = axisNamePadding2;
		yAxisValuesPadding = axisValuesPadding;
		this.labelPadding = labelPadding;
		this.valuePadding = valuePadding;
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

	public Integer getXAxisNamePadding() {
		return xAxisNamePadding;
	}

	public void setXAxisNamePadding(Integer axisNamePadding) {
		xAxisNamePadding = axisNamePadding;
	}

	public Integer getYAxisNamePadding() {
		return yAxisNamePadding;
	}

	public void setYAxisNamePadding(Integer axisNamePadding) {
		yAxisNamePadding = axisNamePadding;
	}

	public Integer getYAxisValuesPadding() {
		return yAxisValuesPadding;
	}

	public void setYAxisValuesPadding(Integer axisValuesPadding) {
		yAxisValuesPadding = axisValuesPadding;
	}

	public Integer getLabelPadding() {
		return labelPadding;
	}

	public void setLabelPadding(Integer labelPadding) {
		this.labelPadding = labelPadding;
	}

	public Integer getValuePadding() {
		return valuePadding;
	}

	public void setValuePadding(Integer valuePadding) {
		this.valuePadding = valuePadding;
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

	public Integer getCanvasPadding() {
		return canvasPadding;
	}

	public void setCanvasPadding(Integer canvasPadding) {
		this.canvasPadding = canvasPadding;
	}
	
}
