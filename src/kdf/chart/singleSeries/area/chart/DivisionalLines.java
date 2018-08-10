package kdf.chart.singleSeries.area.chart;

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
	private Boolean showAlternateHGridColor;
	private String alternateHGridColor;
	private Integer alternateHGridAlpha;
	private Integer numVDivLines;
	private String vDivLineColor;
	private Integer vDivLineThickness;
	private Integer vDivLineAlpha;
	private Boolean vDivLineIsDashed;
	private Integer vDivLineDashLen;
	private Integer vDivLineDashGap;
	private Boolean showAlternateVGridColor;
	private String alternateVGridColor;
	private Integer alternateVGridAlpha;
	private Boolean showZeroPlane;
	
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
		this.showAlternateHGridColor = null;
		this.alternateHGridColor = null;
		this.alternateHGridAlpha = null;
		this.numVDivLines = null;
		vDivLineColor = null;
		vDivLineThickness = null;
		vDivLineAlpha = null;
		vDivLineIsDashed = null;
		vDivLineDashLen = null;
		vDivLineDashGap = null;
		this.showAlternateVGridColor = null;
		this.alternateVGridColor = null;
		this.alternateVGridAlpha = null;
		this.showZeroPlane = null;
	}

	public DivisionalLines(Integer numDivLines, String divLineColor,
			Integer divLineThickness, Integer divLineAlpha,
			Boolean divLineIsDashed, Integer divLineDashLen,
			Integer divLineDashGap, String zeroPlaneColor,
			Integer zeroPlaneThickness, Integer zeroPlaneAlpha,
			Boolean showAlternateHGridColor, String alternateHGridColor,
			Integer alternateHGridAlpha, Integer numVDivLines,
			String divLineColor2, Integer divLineThickness2,
			Integer divLineAlpha2, Boolean divLineIsDashed2,
			Integer divLineDashLen2, Integer divLineDashGap2,
			Boolean showAlternateVGridColor, String alternateVGridColor,
			Integer alternateVGridAlpha, Boolean showZeroPlane) {
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
		this.showAlternateHGridColor = showAlternateHGridColor;
		this.alternateHGridColor = alternateHGridColor;
		this.alternateHGridAlpha = alternateHGridAlpha;
		this.numVDivLines = numVDivLines;
		vDivLineColor = divLineColor2;
		vDivLineThickness = divLineThickness2;
		vDivLineAlpha = divLineAlpha2;
		vDivLineIsDashed = divLineIsDashed2;
		vDivLineDashLen = divLineDashLen2;
		vDivLineDashGap = divLineDashGap2;
		this.showAlternateVGridColor = showAlternateVGridColor;
		this.alternateVGridColor = alternateVGridColor;
		this.alternateVGridAlpha = alternateVGridAlpha;
		this.showZeroPlane = showZeroPlane;
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

	public Boolean getShowAlternateHGridColor() {
		return showAlternateHGridColor;
	}

	public void setShowAlternateHGridColor(Boolean showAlternateHGridColor) {
		this.showAlternateHGridColor = showAlternateHGridColor;
	}

	public String getAlternateHGridColor() {
		return alternateHGridColor;
	}

	public void setAlternateHGridColor(String alternateHGridColor) {
		this.alternateHGridColor = alternateHGridColor;
	}

	public Integer getAlternateHGridAlpha() {
		return alternateHGridAlpha;
	}

	public void setAlternateHGridAlpha(Integer alternateHGridAlpha) {
		this.alternateHGridAlpha = alternateHGridAlpha;
	}

	public Integer getNumVDivLines() {
		return numVDivLines;
	}

	public void setNumVDivLines(Integer numVDivLines) {
		this.numVDivLines = numVDivLines;
	}

	public String getVDivLineColor() {
		return vDivLineColor;
	}

	public void setVDivLineColor(String divLineColor) {
		vDivLineColor = divLineColor;
	}

	public Integer getVDivLineThickness() {
		return vDivLineThickness;
	}

	public void setVDivLineThickness(Integer divLineThickness) {
		vDivLineThickness = divLineThickness;
	}

	public Integer getVDivLineAlpha() {
		return vDivLineAlpha;
	}

	public void setVDivLineAlpha(Integer divLineAlpha) {
		vDivLineAlpha = divLineAlpha;
	}

	public Boolean getVDivLineIsDashed() {
		return vDivLineIsDashed;
	}

	public void setVDivLineIsDashed(Boolean divLineIsDashed) {
		vDivLineIsDashed = divLineIsDashed;
	}

	public Integer getVDivLineDashLen() {
		return vDivLineDashLen;
	}

	public void setVDivLineDashLen(Integer divLineDashLen) {
		vDivLineDashLen = divLineDashLen;
	}

	public Integer getVDivLineDashGap() {
		return vDivLineDashGap;
	}

	public void setVDivLineDashGap(Integer divLineDashGap) {
		vDivLineDashGap = divLineDashGap;
	}

	public Boolean getShowAlternateVGridColor() {
		return showAlternateVGridColor;
	}

	public void setShowAlternateVGridColor(Boolean showAlternateVGridColor) {
		this.showAlternateVGridColor = showAlternateVGridColor;
	}

	public String getAlternateVGridColor() {
		return alternateVGridColor;
	}

	public void setAlternateVGridColor(String alternateVGridColor) {
		this.alternateVGridColor = alternateVGridColor;
	}

	public Integer getAlternateVGridAlpha() {
		return alternateVGridAlpha;
	}

	public void setAlternateVGridAlpha(Integer alternateVGridAlpha) {
		this.alternateVGridAlpha = alternateVGridAlpha;
	}

	public Boolean getShowZeroPlane() {
		return showZeroPlane;
	}

	public void setShowZeroPlane(Boolean showZeroPlane) {
		this.showZeroPlane = showZeroPlane;
	}
	
}
