package kdf.chart.combination.dualY2D.chart;

public class ChartTitles {
	private String caption;
	private String subCaption;
	private String xAxisName;
	private String PYAxisName;
	private String SYAxisName;
	
	public ChartTitles() {
		this.caption = null;
		this.subCaption = null;
		xAxisName = null;
		PYAxisName = null;
		SYAxisName = null;
	}

	public ChartTitles(String caption, String subCaption, String axisName,
			String axisName2, String axisName3) {
		super();
		this.caption = caption;
		this.subCaption = subCaption;
		xAxisName = axisName;
		PYAxisName = axisName2;
		SYAxisName = axisName3;
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

	public String getPYAxisName() {
		return PYAxisName;
	}

	public void setPYAxisName(String axisName) {
		PYAxisName = axisName;
	}

	public String getSYAxisName() {
		return SYAxisName;
	}

	public void setSYAxisName(String xisName) {
		SYAxisName = xisName;
	}
	
}
