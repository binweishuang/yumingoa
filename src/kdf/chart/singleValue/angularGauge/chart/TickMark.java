package kdf.chart.singleValue.angularGauge.chart;

public class TickMark {
	private Boolean showTickMarks;
	private Integer majorTMNumber;
	private String majorTMColor;
	private Integer majorTMHeight;
	private Integer majorTMThickness;
	private Integer minorTMNumber;
	private String minorTMColor;
	private Integer minorTMHeight;
	private Integer minorTMThickness;
	private Boolean showTickValues;
	private Integer displayValueDistance;
	private String tickMarkDecimalPrecision;
	
	public TickMark() {
		this.showTickMarks = null;
		this.majorTMNumber = null;
		this.majorTMColor = null;
		this.majorTMHeight = null;
		this.majorTMThickness = null;
		this.minorTMNumber = null;
		this.minorTMColor = null;
		this.minorTMHeight = null;
		this.minorTMThickness = null;
		this.showTickValues = null;
		this.displayValueDistance = null;
		this.tickMarkDecimalPrecision = null;
	}

	public TickMark(Boolean showTickMarks, Integer majorTMNumber,
			String majorTMColor, Integer majorTMHeight,
			Integer majorTMThickness, Integer minorTMNumber,
			String minorTMColor, Integer minorTMHeight,
			Integer minorTMThickness, Boolean showTickValues,
			Integer displayValueDistance, String tickMarkDecimalPrecision) {
		super();
		this.showTickMarks = showTickMarks;
		this.majorTMNumber = majorTMNumber;
		this.majorTMColor = majorTMColor;
		this.majorTMHeight = majorTMHeight;
		this.majorTMThickness = majorTMThickness;
		this.minorTMNumber = minorTMNumber;
		this.minorTMColor = minorTMColor;
		this.minorTMHeight = minorTMHeight;
		this.minorTMThickness = minorTMThickness;
		this.showTickValues = showTickValues;
		this.displayValueDistance = displayValueDistance;
		this.tickMarkDecimalPrecision = tickMarkDecimalPrecision;
	}

	public Boolean getShowTickMarks() {
		return showTickMarks;
	}

	public void setShowTickMarks(Boolean showTickMarks) {
		this.showTickMarks = showTickMarks;
	}

	public Integer getMajorTMNumber() {
		return majorTMNumber;
	}

	public void setMajorTMNumber(Integer majorTMNumber) {
		this.majorTMNumber = majorTMNumber;
	}

	public String getMajorTMColor() {
		return majorTMColor;
	}

	public void setMajorTMColor(String majorTMColor) {
		this.majorTMColor = majorTMColor;
	}

	public Integer getMajorTMHeight() {
		return majorTMHeight;
	}

	public void setMajorTMHeight(Integer majorTMHeight) {
		this.majorTMHeight = majorTMHeight;
	}

	public Integer getMajorTMThickness() {
		return majorTMThickness;
	}

	public void setMajorTMThickness(Integer majorTMThickness) {
		this.majorTMThickness = majorTMThickness;
	}

	public Integer getMinorTMNumber() {
		return minorTMNumber;
	}

	public void setMinorTMNumber(Integer minorTMNumber) {
		this.minorTMNumber = minorTMNumber;
	}

	public String getMinorTMColor() {
		return minorTMColor;
	}

	public void setMinorTMColor(String minorTMColor) {
		this.minorTMColor = minorTMColor;
	}

	public Integer getMinorTMHeight() {
		return minorTMHeight;
	}

	public void setMinorTMHeight(Integer minorTMHeight) {
		this.minorTMHeight = minorTMHeight;
	}

	public Integer getMinorTMThickness() {
		return minorTMThickness;
	}

	public void setMinorTMThickness(Integer minorTMThickness) {
		this.minorTMThickness = minorTMThickness;
	}

	public Boolean getShowTickValues() {
		return showTickValues;
	}

	public void setShowTickValues(Boolean showTickValues) {
		this.showTickValues = showTickValues;
	}

	public Integer getDisplayValueDistance() {
		return displayValueDistance;
	}

	public void setDisplayValueDistance(Integer displayValueDistance) {
		this.displayValueDistance = displayValueDistance;
	}

	public String getTickMarkDecimalPrecision() {
		return tickMarkDecimalPrecision;
	}

	public void setTickMarkDecimalPrecision(String tickMarkDecimalPrecision) {
		this.tickMarkDecimalPrecision = tickMarkDecimalPrecision;
	}
	
}
