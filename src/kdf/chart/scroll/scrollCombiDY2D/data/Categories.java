package kdf.chart.scroll.scrollCombiDY2D.data;

import java.util.List;

public class Categories {
	private String font;
	private Integer fontSize;
	private String fontColor;
	private List categories;
	
	public Categories() {
		this.font = null;
		this.fontSize = null;
		this.fontColor = null;
		this.categories = null;
	}

	public Categories(String font, Integer fontSize, String fontColor, List categories) {
		super();
		this.font = font;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.categories = categories;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public List getCategories() {
		return categories;
	}

	public void setCategories(List categories) {
		this.categories = categories;
	}
	
}
