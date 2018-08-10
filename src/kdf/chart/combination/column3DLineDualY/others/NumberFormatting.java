package kdf.chart.combination.column3DLineDualY.others;

public class NumberFormatting {
	private Boolean formatNumber;
	private Boolean formatNumberScale;
	private String defaultNumberScale;
	private String numberScaleUnit;
	private String numberScaleValue;
	private String numberPrefix;
	private String numberSuffix;
	private String decimalSeparator;
	private String thousandSeparator;
	private String inDecimalSeparator;
	private String inThousandSeparator;
	private Integer decimals;
	private Boolean forceDecimals;
	private Integer yAxisValueDecimals;
	private Boolean sFormatNumber;
	private Boolean sFormatNumberScale;
	private String sDefaultNumberScale;
	private String sNumberScaleUnit;
	private String sNumberScaleValue;
	private String sNumberPrefix;
	private String sNumberSuffix;
	private Integer sDecimals;
	private Integer sYAxisValueDecimals;
	
	public NumberFormatting() {
		this.formatNumber = null;
		this.formatNumberScale = null;
		this.defaultNumberScale = null;
		this.numberScaleUnit = null;
		this.numberScaleValue = null;
		this.numberPrefix = null;
		this.numberSuffix = null;
		this.decimalSeparator = null;
		this.thousandSeparator = null;
		this.inDecimalSeparator = null;
		this.inThousandSeparator = null;
		this.decimals = null;
		this.forceDecimals = null;
		yAxisValueDecimals = null;
		sFormatNumber = null;
		sFormatNumberScale = null;
		sDefaultNumberScale = null;
		sNumberScaleUnit = null;
		sNumberScaleValue = null;
		sNumberPrefix = null;
		sNumberSuffix = null;
		sDecimals = null;
		sYAxisValueDecimals = null;
	}

	public NumberFormatting(Boolean formatNumber, Boolean formatNumberScale,
			String defaultNumberScale, String numberScaleUnit,
			String numberScaleValue, String numberPrefix, String numberSuffix,
			String decimalSeparator, String thousandSeparator,
			String inDecimalSeparator, String inThousandSeparator,
			Integer decimals, Boolean forceDecimals, Integer axisValueDecimals,
			Boolean formatNumber2, Boolean formatNumberScale2,
			String defaultNumberScale2, String numberScaleUnit2,
			String numberScaleValue2, String numberPrefix2,
			String numberSuffix2, Integer decimals2, Integer axisValueDecimals2) {
		super();
		this.formatNumber = formatNumber;
		this.formatNumberScale = formatNumberScale;
		this.defaultNumberScale = defaultNumberScale;
		this.numberScaleUnit = numberScaleUnit;
		this.numberScaleValue = numberScaleValue;
		this.numberPrefix = numberPrefix;
		this.numberSuffix = numberSuffix;
		this.decimalSeparator = decimalSeparator;
		this.thousandSeparator = thousandSeparator;
		this.inDecimalSeparator = inDecimalSeparator;
		this.inThousandSeparator = inThousandSeparator;
		this.decimals = decimals;
		this.forceDecimals = forceDecimals;
		yAxisValueDecimals = axisValueDecimals;
		sFormatNumber = formatNumber2;
		sFormatNumberScale = formatNumberScale2;
		sDefaultNumberScale = defaultNumberScale2;
		sNumberScaleUnit = numberScaleUnit2;
		sNumberScaleValue = numberScaleValue2;
		sNumberPrefix = numberPrefix2;
		sNumberSuffix = numberSuffix2;
		sDecimals = decimals2;
		sYAxisValueDecimals = axisValueDecimals2;
	}

	public Boolean getFormatNumber() {
		return formatNumber;
	}

	public void setFormatNumber(Boolean formatNumber) {
		this.formatNumber = formatNumber;
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

	public String getNumberScaleUnit() {
		return numberScaleUnit;
	}

	public void setNumberScaleUnit(String numberScaleUnit) {
		this.numberScaleUnit = numberScaleUnit;
	}

	public String getNumberScaleValue() {
		return numberScaleValue;
	}

	public void setNumberScaleValue(String numberScaleValue) {
		this.numberScaleValue = numberScaleValue;
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

	public String getInDecimalSeparator() {
		return inDecimalSeparator;
	}

	public void setInDecimalSeparator(String inDecimalSeparator) {
		this.inDecimalSeparator = inDecimalSeparator;
	}

	public String getInThousandSeparator() {
		return inThousandSeparator;
	}

	public void setInThousandSeparator(String inThousandSeparator) {
		this.inThousandSeparator = inThousandSeparator;
	}

	public Integer getDecimals() {
		return decimals;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public Boolean getForceDecimals() {
		return forceDecimals;
	}

	public void setForceDecimals(Boolean forceDecimals) {
		this.forceDecimals = forceDecimals;
	}

	public Integer getYAxisValueDecimals() {
		return yAxisValueDecimals;
	}

	public void setYAxisValueDecimals(Integer axisValueDecimals) {
		yAxisValueDecimals = axisValueDecimals;
	}

	public Boolean getSFormatNumber() {
		return sFormatNumber;
	}

	public void setSFormatNumber(Boolean formatNumber) {
		sFormatNumber = formatNumber;
	}

	public Boolean getSFormatNumberScale() {
		return sFormatNumberScale;
	}

	public void setSFormatNumberScale(Boolean formatNumberScale) {
		sFormatNumberScale = formatNumberScale;
	}

	public String getSDefaultNumberScale() {
		return sDefaultNumberScale;
	}

	public void setSDefaultNumberScale(String defaultNumberScale) {
		sDefaultNumberScale = defaultNumberScale;
	}

	public String getSNumberScaleUnit() {
		return sNumberScaleUnit;
	}

	public void setSNumberScaleUnit(String numberScaleUnit) {
		sNumberScaleUnit = numberScaleUnit;
	}

	public String getSNumberScaleValue() {
		return sNumberScaleValue;
	}

	public void setSNumberScaleValue(String numberScaleValue) {
		sNumberScaleValue = numberScaleValue;
	}

	public String getSNumberPrefix() {
		return sNumberPrefix;
	}

	public void setSNumberPrefix(String numberPrefix) {
		sNumberPrefix = numberPrefix;
	}

	public String getSNumberSuffix() {
		return sNumberSuffix;
	}

	public void setSNumberSuffix(String numberSuffix) {
		sNumberSuffix = numberSuffix;
	}

	public Integer getSDecimals() {
		return sDecimals;
	}

	public void setSDecimals(Integer decimals) {
		sDecimals = decimals;
	}

	public Integer getSYAxisValueDecimals() {
		return sYAxisValueDecimals;
	}

	public void setSYAxisValueDecimals(Integer axisValueDecimals) {
		sYAxisValueDecimals = axisValueDecimals;
	}
	
}
