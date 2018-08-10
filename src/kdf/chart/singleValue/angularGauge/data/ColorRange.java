package kdf.chart.singleValue.angularGauge.data;

import java.util.List;

public class ColorRange {
	private List colors;
	
	public ColorRange() {
		this.colors = null;
	}

	public ColorRange(List colors) {
		super();
		this.colors = colors;
	}

	public List getColors() {
		return colors;
	}

	public void setColors(List colors) {
		this.colors = colors;
	}
	
}
