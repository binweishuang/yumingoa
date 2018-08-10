package kdf.chart.scroll.scrollCombiDY2D.chart;

public class ScrollProperties {
	private String scrollColor;
	private Integer scrollHeight;
	private Integer scrollPadding;
	private Integer scrollBtnWidth;
	private Integer scrollBtnPadding;
	
	public ScrollProperties() {
		this.scrollColor = null;
		this.scrollHeight = null;
		this.scrollPadding = null;
		this.scrollBtnWidth = null;
		this.scrollBtnPadding = null;
	}

	public ScrollProperties(String scrollColor, Integer scrollHeight,
			Integer scrollPadding, Integer scrollBtnWidth,
			Integer scrollBtnPadding) {
		super();
		this.scrollColor = scrollColor;
		this.scrollHeight = scrollHeight;
		this.scrollPadding = scrollPadding;
		this.scrollBtnWidth = scrollBtnWidth;
		this.scrollBtnPadding = scrollBtnPadding;
	}

	public String getScrollColor() {
		return scrollColor;
	}

	public void setScrollColor(String scrollColor) {
		this.scrollColor = scrollColor;
	}

	public Integer getScrollHeight() {
		return scrollHeight;
	}

	public void setScrollHeight(Integer scrollHeight) {
		this.scrollHeight = scrollHeight;
	}

	public Integer getScrollPadding() {
		return scrollPadding;
	}

	public void setScrollPadding(Integer scrollPadding) {
		this.scrollPadding = scrollPadding;
	}

	public Integer getScrollBtnWidth() {
		return scrollBtnWidth;
	}

	public void setScrollBtnWidth(Integer scrollBtnWidth) {
		this.scrollBtnWidth = scrollBtnWidth;
	}

	public Integer getScrollBtnPadding() {
		return scrollBtnPadding;
	}

	public void setScrollBtnPadding(Integer scrollBtnPadding) {
		this.scrollBtnPadding = scrollBtnPadding;
	}
	
}
