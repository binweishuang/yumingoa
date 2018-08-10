package kdf.chart.singleSeries.pie2D.chart;

public class Tooltip {
	private Boolean showToolTip;
	private String toolTipBgColor;
	private String toolTipBorderColor;
	private String toolTipSepChar;
	
	public Tooltip() {
		this.showToolTip = null;
		this.toolTipBgColor = null;
		this.toolTipBorderColor = null;
		this.toolTipSepChar = null;
	}

	public Tooltip(Boolean showToolTip, String toolTipBgColor,
			String toolTipBorderColor, String toolTipSepChar) {
		super();
		this.showToolTip = showToolTip;
		this.toolTipBgColor = toolTipBgColor;
		this.toolTipBorderColor = toolTipBorderColor;
		this.toolTipSepChar = toolTipSepChar;
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
	
}
