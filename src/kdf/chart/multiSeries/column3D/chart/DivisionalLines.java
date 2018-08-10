package kdf.chart.multiSeries.column3D.chart;

public class DivisionalLines {
	private Integer numDivLines;
	private String divLineColor;
	private Integer divLineThickness;
	private Integer divLineAlpha;
	private Boolean divLineIsDashed;
	private Integer divLineDashLen;
	private Integer divLineDashGap;
	private String zeroPlaneColor;
	private Integer zeroPlaneThickness;
	private Integer zeroPlaneAlpha;
	private Boolean zeroPlaneShowBorder;
	private String zeroPlaneBorderColor;
	
	public DivisionalLines() {
		this.numDivLines = null;
		this.divLineColor = null;
		this.divLineThickness = null;
		this.divLineAlpha = null;
		this.divLineIsDashed = null;
		this.divLineDashLen = null;
		this.divLineDashGap = null;
		this.zeroPlaneColor = null;
		this.zeroPlaneThickness = null;
		this.zeroPlaneAlpha = null;
		this.zeroPlaneShowBorder = null;
		this.zeroPlaneBorderColor = null;
	}

	public DivisionalLines(Integer numDivLines, String divLineColor,
			Integer divLineThickness, Integer divLineAlpha,
			Boolean divLineIsDashed, Integer divLineDashLen,
			Integer divLineDashGap, String zeroPlaneColor,
			Integer zeroPlaneThickness, Integer zeroPlaneAlpha,
			Boolean zeroPlaneShowBorder, String zeroPlaneBorderColor) {
		super();
		this.numDivLines = numDivLines;
		this.divLineColor = divLineColor;
		this.divLineThickness = divLineThickness;
		this.divLineAlpha = divLineAlpha;
		this.divLineIsDashed = divLineIsDashed;
		this.divLineDashLen = divLineDashLen;
		this.divLineDashGap = divLineDashGap;
		this.zeroPlaneColor = zeroPlaneColor;
		this.zeroPlaneThickness = zeroPlaneThickness;
		this.zeroPlaneAlpha = zeroPlaneAlpha;
		this.zeroPlaneShowBorder = zeroPlaneShowBorder;
		this.zeroPlaneBorderColor = zeroPlaneBorderColor;
	}

	public Integer getNumDivLines() {
		return numDivLines;
	}

	public void setNumDivLines(Integer numDivLines) {
		this.numDivLines = numDivLines;
	}

	public String getDivLineColor() {
		return divLineColor;
	}

	public void setDivLineColor(String divLineColor) {
		this.divLineColor = divLineColor;
	}

	public Integer getDivLineThickness() {
		return divLineThickness;
	}

	public void setDivLineThickness(Integer divLineThickness) {
		this.divLineThickness = divLineThickness;
	}

	public Integer getDivLineAlpha() {
		return divLineAlpha;
	}

	public void setDivLineAlpha(Integer divLineAlpha) {
		this.divLineAlpha = divLineAlpha;
	}

	public Boolean getDivLineIsDashed() {
		return divLineIsDashed;
	}

	public void setDivLineIsDashed(Boolean divLineIsDashed) {
		this.divLineIsDashed = divLineIsDashed;
	}

	public Integer getDivLineDashLen() {
		return divLineDashLen;
	}

	public void setDivLineDashLen(Integer divLineDashLen) {
		this.divLineDashLen = divLineDashLen;
	}

	public Integer getDivLineDashGap() {
		return divLineDashGap;
	}

	public void setDivLineDashGap(Integer divLineDashGap) {
		this.divLineDashGap = divLineDashGap;
	}

	public String getZeroPlaneColor() {
		return zeroPlaneColor;
	}

	public void setZeroPlaneColor(String zeroPlaneColor) {
		this.zeroPlaneColor = zeroPlaneColor;
	}

	public Integer getZeroPlaneThickness() {
		return zeroPlaneThickness;
	}

	public void setZeroPlaneThickness(Integer zeroPlaneThickness) {
		this.zeroPlaneThickness = zeroPlaneThickness;
	}

	public Integer getZeroPlaneAlpha() {
		return zeroPlaneAlpha;
	}

	public void setZeroPlaneAlpha(Integer zeroPlaneAlpha) {
		this.zeroPlaneAlpha = zeroPlaneAlpha;
	}

	public Boolean getZeroPlaneShowBorder() {
		return zeroPlaneShowBorder;
	}

	public void setZeroPlaneShowBorder(Boolean zeroPlaneShowBorder) {
		this.zeroPlaneShowBorder = zeroPlaneShowBorder;
	}

	public String getZeroPlaneBorderColor() {
		return zeroPlaneBorderColor;
	}

	public void setZeroPlaneBorderColor(String zeroPlaneBorderColor) {
		this.zeroPlaneBorderColor = zeroPlaneBorderColor;
	}
	
}
