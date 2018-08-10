package kdf.chart.multiSeries.line.others;

public class VerticalLines {
	private String color;
	private Integer thickness;
	private Integer alpha;
	private Boolean dashed;
	private Integer dashLen;
	private Integer dashGap;
	
	public VerticalLines() {
		this.color = null;
		this.thickness = null;
		this.alpha = null;
		this.dashed = null;
		this.dashLen = null;
		this.dashGap = null;
	}

	public VerticalLines(String color, Integer thickness, Integer alpha,
			Boolean dashed, Integer dashLen, Integer dashGap) {
		super();
		this.color = color;
		this.thickness = thickness;
		this.alpha = alpha;
		this.dashed = dashed;
		this.dashLen = dashLen;
		this.dashGap = dashGap;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
	
}
