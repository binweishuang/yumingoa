package kdf.chart.multiSeries.column3D.others;

public class TrendLines {
	private Double startValue;
	private Double endValue;
	private String displayValue;
	private String color;
	private Boolean isTrendZone;
	private Boolean showOnTop;
	private Integer thickness;
	private Integer alpha;
	private Boolean dashed;
	private Integer dashLen;
	private Integer dashGap;
	private Boolean valueOnRight;
	
	public TrendLines() {
		this.startValue = null;
		this.endValue = null;
		this.displayValue = null;
		this.color = null;
		this.isTrendZone = null;
		this.showOnTop = null;
		this.thickness = null;
		this.alpha = null;
		this.dashed = null;
		this.dashLen = null;
		this.dashGap = null;
		this.valueOnRight = null;
	}

	public TrendLines(Double startValue, Double endValue, String displayValue,
			String color, Boolean isTrendZone, Boolean showOnTop,
			Integer thickness, Integer alpha, Boolean dashed, Integer dashLen,
			Integer dashGap, Boolean valueOnRight) {
		super();
		this.startValue = startValue;
		this.endValue = endValue;
		this.displayValue = displayValue;
		this.color = color;
		this.isTrendZone = isTrendZone;
		this.showOnTop = showOnTop;
		this.thickness = thickness;
		this.alpha = alpha;
		this.dashed = dashed;
		this.dashLen = dashLen;
		this.dashGap = dashGap;
		this.valueOnRight = valueOnRight;
	}

	public Double getStartValue() {
		return startValue;
	}

	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}

	public Double getEndValue() {
		return endValue;
	}

	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getIsTrendZone() {
		return isTrendZone;
	}

	public void setIsTrendZone(Boolean isTrendZone) {
		this.isTrendZone = isTrendZone;
	}

	public Boolean getShowOnTop() {
		return showOnTop;
	}

	public void setShowOnTop(Boolean showOnTop) {
		this.showOnTop = showOnTop;
	}

	public Integer getThickness() {
		return thickness;
	}

	public void setThickness(Integer thickness) {
		this.thickness = thickness;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public Boolean getDashed() {
		return dashed;
	}

	public void setDashed(Boolean dashed) {
		this.dashed = dashed;
	}

	public Integer getDashLen() {
		return dashLen;
	}

	public void setDashLen(Integer dashLen) {
		this.dashLen = dashLen;
	}

	public Integer getDashGap() {
		return dashGap;
	}

	public void setDashGap(Integer dashGap) {
		this.dashGap = dashGap;
	}

	public Boolean getValueOnRight() {
		return valueOnRight;
	}

	public void setValueOnRight(Boolean valueOnRight) {
		this.valueOnRight = valueOnRight;
	}
	
}
