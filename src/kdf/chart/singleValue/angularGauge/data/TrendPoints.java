package kdf.chart.singleValue.angularGauge.data;

import java.util.List;

public class TrendPoints {
	private List points;
	
	public TrendPoints() {
		this.points = null;
	}

	public TrendPoints(List points) {
		super();
		this.points = points;
	}

	public List getPoints() {
		return points;
	}

	public void setPoints(List points) {
		this.points = points;
	}
	
}
