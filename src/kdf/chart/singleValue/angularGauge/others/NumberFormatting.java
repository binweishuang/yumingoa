package kdf.chart.singleValue.angularGauge.others;

public class NumberFormatting {
	private String numberPrefix;
	private String numberSuffix;
	private Boolean formatNumber;
	private String decimalSeparator;
	private String thousandSeparator;
	private String decimalPrecision;
	private Boolean formatNumberScale;
	private String defaultNumberScale;
	private String numberScaleValue;
	private String numberScaleUnit;
	
	public NumberFormatting() {
		this.numberPrefix = null;
		this.numberSuffix = null;
		this.formatNumber = null;
		this.decimalSeparator = null;
		this.thousandSeparator = null;
		this.decimalPrecision = null;
		this.formatNumberScale = null;
		this.defaultNumberScale = null;
		this.numberScaleValue = null;
		this.numberScaleUnit = null;
	}

	public NumberFormatting(String numberPrefix, String numberSuffix,
			Boolean formatNumber, String decimalSeparator,
			String thousandSeparator, String decimalPrecision,
			Boolean formatNumberScale, String defaultNumberScale,
			String numberScaleValue, String numberScaleUnit) {
		super();
		this.numberPrefix = numberPrefix;
		this.numberSuffix = numberSuffix;
		this.formatNumber = formatNumber;
		this.decimalSeparator = decimalSeparator;
		this.thousandSeparator = thousandSeparator;
		this.decimalPrecision = decimalPrecision;
		this.formatNumberScale = formatNumberScale;
		this.defaultNumberScale = defaultNumberScale;
		this.numberScaleValue = numberScaleValue;
		this.numberScaleUnit = numberScaleUnit;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public Boolean getFormatNumber() {
		return formatNumber;
	}

	public void setFormatNumber(Boolean formatNumber) {
		this.formatNumber = formatNumber;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	public String getThousandSeparator() {
		return thousandSeparator;
	}

	public void setThousandSeparator(String thousandSeparator) {
		this.thousandSeparator = thousandSeparator;
	}

	public String getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public Boolean getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(Boolean formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}

	public String getDefaultNumberScale() {
		return defaultNumberScale;
	}

	public void setDefaultNumberScale(String defaultNumberScale) {
		this.defaultNumberScale = defaultNumberScale;
	}

	public String getNumberScaleValue() {
		return numberScaleValue;
	}

	public void setNumberScaleValue(String numberScaleValue) {
		this.numberScaleValue = numberScaleValue;
	}

	public String getNumberScaleUnit() {
		return numberScaleUnit;
	}

	public void setNumberScaleUnit(String numberScaleUnit) {
		this.numberScaleUnit = numberScaleUnit;
	}
	
}
