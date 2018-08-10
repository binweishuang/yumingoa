package kdf.chart.singleValue.angularGauge.data;

public class Point {
	private String displayValue;
	private Double value;
	private String fontcolor;
	
	public Point() {
		this.displayValue = null;
		this.value = null;
		this.fontcolor = null;
	}

	public Point(String displayValue, Double value, String fontcolor) {
		super();
		this.displayValue = displayValue;
		this.value = value;
		this.fontcolor = fontcolor;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}
	
}
