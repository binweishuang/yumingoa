package kdf.chart.combination.dualY2D.chart;

public class Tooltip {
	private Boolean showToolTip;
	private String toolTipBgColor;
	private String toolTipBorderColor;
	private String toolTipSepChar;
	private Boolean seriesNameInToolTip;
	
	public Tooltip() {
		this.showToolTip = null;
		this.toolTipBgColor = null;
		this.toolTipBorderColor = null;
		this.toolTipSepChar = null;
		this.seriesNameInToolTip = null;
	}

	public Tooltip(Boolean showToolTip, String toolTipBgColor,
			String toolTipBorderColor, String toolTipSepChar,
			Boolean seriesNameInToolTip) {
		super();
		this.showToolTip = showToolTip;
		this.toolTipBgColor = toolTipBgColor;
		this.toolTipBorderColor = toolTipBorderColor;
		this.toolTipSepChar = toolTipSepChar;
		this.seriesNameInToolTip = seriesNameInToolTip;
	}

	public Boolean getShowToolTip() {
		return showToolTip;
	}

	public void setShowToolTip(Boolean showToolTip) {
		this.showToolTip = showToolTip;
	}

	public String getToolTipBgColor() {
		return toolTipBgColor;
	}

	public void setToolTipBgColor(String toolTipBgColor) {
		this.toolTipBgColor = toolTipBgColor;
	}

	public String getToolTipBorderColor() {
		return toolTipBorderColor;
	}

	public void setToolTipBorderColor(String toolTipBorderColor) {
		this.toolTipBorderColor = toolTipBorderColor;
	}

	public String getToolTipSepChar() {
		return toolTipSepChar;
	}

	public void setToolTipSepChar(String toolTipSepChar) {
		this.toolTipSepChar = toolTipSepChar;
	}

	public Boolean getSeriesNameInToolTip() {
		return seriesNameInToolTip;
	}

	public void setSeriesNameInToolTip(Boolean seriesNameInToolTip) {
		this.seriesNameInToolTip = seriesNameInToolTip;
	}
	
}
