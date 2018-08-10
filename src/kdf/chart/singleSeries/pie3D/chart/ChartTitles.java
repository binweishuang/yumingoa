package kdf.chart.singleSeries.pie3D.chart;

public class ChartTitles {
	private String caption;
	private String subCaption;
	
	public ChartTitles() {
		this.caption = null;
		this.subCaption = null;
	}

	public ChartTitles(String caption, String subCaption) {
		super();
		this.caption = caption;
		this.subCaption = subCaption;
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
	
}
