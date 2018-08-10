package kdf.chart.singleSeries.line.chart;

public class ChartTitles {
	private String caption;
	private String subCaption;
	private String xAxisName;
	private String yAxisName;
	
	public ChartTitles() {
		
	}

	public ChartTitles(String caption, String subCaption, String axisName,
			String axisName2) {
		super();
		this.caption = caption;
		this.subCaption = subCaption;
		xAxisName = axisName;
		yAxisName = axisName2;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getXAxisName() {
		return xAxisName;
	}

	public void setXAxisName(String axisName) {
		xAxisName = axisName;
	}

	public String getYAxisName() {
		return yAxisName;
	}

	public void setYAxisName(String axisName) {
		yAxisName = axisName;
	}
	
	
}
