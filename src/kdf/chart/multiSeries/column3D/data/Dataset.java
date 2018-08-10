package kdf.chart.multiSeries.column3D.data;

import java.util.List;

public class Dataset {
	private String seriesName;
	private String color;
	private String alpha;
	private String ratio;
	private Boolean showValues;
	private Boolean includeInLegend;
	private List sets;
	
	public Dataset() {
		this.seriesName = null;
		this.color = null;
		this.alpha = null;
		this.ratio = null;
		this.showValues = null;
		this.includeInLegend = null;
		this.sets = null;
	}

	public Dataset(String seriesName, String color, String alpha, String ratio,
			Boolean showValues, Boolean includeInLegend, List sets) {
		super();
		this.seriesName = seriesName;
		this.color = color;
		this.alpha = alpha;
		this.ratio = ratio;
		this.showValues = showValues;
		this.includeInLegend = includeInLegend;
		this.sets = sets;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public Boolean getShowValues() {
		return showValues;
	}

	public void setShowValues(Boolean showValues) {
		this.showValues = showValues;
	}

	public Boolean getIncludeInLegend() {
		return includeInLegend;
	}

	public void setIncludeInLegend(Boolean includeInLegend) {
		this.includeInLegend = includeInLegend;
	}

	public List getSets() {
		return sets;
	}

	public void setSets(List sets) {
		this.sets = sets;
	}
	
}
