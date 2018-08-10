package kdf.chart.singleValue.angularGauge.chart;

public class Hover {
	private Boolean showhovercap;
	private String hoverCapBgColor;
	private String hoverCapBorderColor;
	private String hoverCapSepChar;
	
	public Hover() {
		this.showhovercap = null;
		this.hoverCapBgColor = null;
		this.hoverCapBorderColor = null;
		this.hoverCapSepChar = null;
	}

	public Hover(Boolean showhovercap, String hoverCapBgColor,
			String hoverCapBorderColor, String hoverCapSepChar) {
		super();
		this.showhovercap = showhovercap;
		this.hoverCapBgColor = hoverCapBgColor;
		this.hoverCapBorderColor = hoverCapBorderColor;
		this.hoverCapSepChar = hoverCapSepChar;
	}

	public Boolean getShowhovercap() {
		return showhovercap;
	}

	public void setShowhovercap(Boolean showhovercap) {
		this.showhovercap = showhovercap;
	}

	public String getHoverCapBgColor() {
		return hoverCapBgColor;
	}

	public void setHoverCapBgColor(String hoverCapBgColor) {
		this.hoverCapBgColor = hoverCapBgColor;
	}

	public String getHoverCapBorderColor() {
		return hoverCapBorderColor;
	}

	public void setHoverCapBorderColor(String hoverCapBorderColor) {
		this.hoverCapBorderColor = hoverCapBorderColor;
	}

	public String getHoverCapSepChar() {
		return hoverCapSepChar;
	}

	public void setHoverCapSepChar(String hoverCapSepChar) {
		this.hoverCapSepChar = hoverCapSepChar;
	}
	
}
