package kdf.chart.singleValue.angularGauge.chart;

public class Pivot {
	private String pivotBgColor;
	private String pivotBorderColor;
	private Integer pivotBorderThickness;
	private Integer pivotRadius;
	
	public Pivot() {
		this.pivotBgColor = null;
		this.pivotBorderColor = null;
		this.pivotBorderThickness = null;
		this.pivotRadius = null;
	}

	public Pivot(String pivotBgColor, String pivotBorderColor,
			Integer pivotBorderThickness, Integer pivotRadius) {
		super();
		this.pivotBgColor = pivotBgColor;
		this.pivotBorderColor = pivotBorderColor;
		this.pivotBorderThickness = pivotBorderThickness;
		this.pivotRadius = pivotRadius;
	}

	public String getPivotBgColor() {
		return pivotBgColor;
	}

	public void setPivotBgColor(String pivotBgColor) {
		this.pivotBgColor = pivotBgColor;
	}

	public String getPivotBorderColor() {
		return pivotBorderColor;
	}

	public void setPivotBorderColor(String pivotBorderColor) {
		this.pivotBorderColor = pivotBorderColor;
	}

	public Integer getPivotBorderThickness() {
		return pivotBorderThickness;
	}

	public void setPivotBorderThickness(Integer pivotBorderThickness) {
		this.pivotBorderThickness = pivotBorderThickness;
	}

	public Integer getPivotRadius() {
		return pivotRadius;
	}

	public void setPivotRadius(Integer pivotRadius) {
		this.pivotRadius = pivotRadius;
	}
	
}
