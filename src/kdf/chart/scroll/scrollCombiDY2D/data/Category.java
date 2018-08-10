package kdf.chart.scroll.scrollCombiDY2D.data;

public class Category {
	private String label;
	private Boolean showLabel;
	private String toolText;
	
	public Category() {
		this.label = null;
		this.showLabel = null;
		this.toolText = null;
	}

	public Category(String label, Boolean showLabel, String toolText) {
		super();
		this.label = label;
		this.showLabel = showLabel;
		this.toolText = toolText;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(Boolean showLabel) {
		this.showLabel = showLabel;
	}

	public String getToolText() {
		return toolText;
	}

	public void setToolText(String toolText) {
		this.toolText = toolText;
	}
	
}
