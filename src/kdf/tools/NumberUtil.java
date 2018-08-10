package kdf.tools;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import kdf.constant.SystemConfig;


/**
 * 数字处理辅助类：
 * 
 */
public class NumberUtil {
	/**
	 * 判断一个字符串是否是数字类型
	 * 
	 * @param value
	 *            待判断字符串
	 * @return 如果是数字类型，返回true;否则，返回false
	 */
	public static boolean isDigital(String value) {
		try {
			Double.valueOf(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isLong(String value) {
		if (NumberUtil.isDigital(value)) {
			double dbValue = Double.valueOf(value).doubleValue();
			if ((dbValue % 1) == 0) {
				return dbValue <= Long.MAX_VALUE && dbValue >= Long.MIN_VALUE;
			}
		}
		return false;
	}

	/**
	 * 判断该字符串是否为整型数字
	 * 
	 * @param value
	 *            待判断的字符串
	 * @return 如果是整型，则返回true；否则，则返回false
	 */
	public static boolean isInteger(String value) {
		if (NumberUtil.isDigital(value)) {
			double dbValue = Double.valueOf(value).doubleValue();
			if ((dbValue % 1) == 0) {
				return dbValue <= Integer.MAX_VALUE
						&& dbValue >= Integer.MIN_VALUE;
			}
		}
		return false;
	}

	/**
	 * 检查该字符串是否为浮点型数字
	 * 
	 * @param value
	 *            待判断的字符串
	 * @return 如果是浮点型，则返回tru；否则，返回false
	 */
	public static boolean isFloat(String value) {
		try {
			Float.valueOf(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 格式化目标成货币格式
	 * 
	 * @param number
	 *            待格式化的数据
	 * @param locale
	 *            本地化信息
	 * @return 格式化的字符串（含货币编码）
	 */
	public static String formatCurrency(double number, Locale locale) {
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
		return fmt.format(number);
	}

	/**
	 * 格式化为系统货币格式
	 * 
	 * @param number
	 *            待格式化数据
	 * @return 格式化后的字符串（含货币编码）
	 */
	public static String formatCurrency(double number) {
		NumberFormat fmt = NumberFormat.getCurrencyInstance(SystemConfig
				.getTargetLocale());
		return fmt.format(number);
	}

	/**
	 * 将一个数字字符串格式化目标货币格式
	 * 
	 * @param number
	 *            待格式化的数字字符串
	 * @param locale
	 *            本地化信息
	 * @return 格式化后的字符串
	 */

	public static String formatCurrency(String number, Locale locale) {
		return formatCurrency(Double.valueOf(number).doubleValue(), locale);
	}

	/**
	 * 将一数字字符串格式化系统货币格式
	 * 
	 * @param number
	 *            待格式化的数字字符串
	 * @return 格式化后的字符串
	 */
	public static String formatCurrency(String number) {
		return formatCurrency(Double.valueOf(number).doubleValue());
	}

	/**
	 * 格式化数字为指定精度
	 * 
	 * @param number
	 *            待格式化的数字
	 * @param precision
	 *            给定精度，不小于0
	 * @return 格式化后的数字
	 */
	public static String formatPrecision(double number, int precision) {
		String prec = "";
		for (int i = 0; i < precision; i++) {
			prec += "0";
		}
		if (!"".equals(prec)) {
			prec = "." + prec;
		}
		DecimalFormat df = new DecimalFormat("#0" + prec);
		return df.format(number);
		/*
		 * if (precision >= 0) { int tens = 1; String strNumber =
		 * String.valueOf(number); int realPrecision =
		 * strNumber.lastIndexOf("."); if (realPrecision == -1) { if (precision >
		 * 0) { strNumber += strNumber + "."; for (int i = 0; i < precision;
		 * i++) { strNumber += "0"; } return strNumber; } else { return
		 * strNumber; } } realPrecision = strNumber.length() - realPrecision -
		 * 1; if (realPrecision < precision) { for (int i = 0; i < precision -
		 * realPrecision; i++) { strNumber += "0"; } return strNumber; } else {
		 * for (int i = 1; i <= precision; i++) { tens = tens * 10; } double
		 * newNumber = number * tens; double modNumber = newNumber % 1; double
		 * intNumber = newNumber - modNumber; if (modNumber >= 0.5) { intNumber +=
		 * 1; } double retNumber = intNumber / tens; int newPrecision =
		 * getPrecision(retNumber); StringBuffer buf = new StringBuffer();
		 * buf.append(retNumber); if (newPrecision < precision) { for (int i =
		 * 0; i < precision - newPrecision; i++) { buf.append("0"); } } else if
		 * (newPrecision > precision) { if (newPrecision > 1) { // 0
		 * buf.deleteCharAt(buf.length() - 1); } else { // .0 int len =
		 * buf.length(); buf.delete(len - 2, len); } } return buf.toString(); } }
		 * else { return "" + number; }
		 */
	}

	/**
	 * 得到数字精度
	 * 
	 * @param number
	 *            给定的数字
	 * @return 该数字的精度
	 */
	public static int getPrecision(double number) {
		String strNumber = String.valueOf(number);
		int realPrecision = strNumber.lastIndexOf(".");
		if (realPrecision == -1) {
			return 0;
		}
		return strNumber.length() - realPrecision - 1;
	}

	private static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁",
			"肆", "伍", "陆", "柒", "捌", "玖" };

	private static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万",
			"拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "万", "拾", "佰", "仟" };

	private static String positiveIntegerToHanStr(String NumStr) { // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // 亿、万进位前有数值标记
		int len, n;
		len = NumStr.length();
		if (len > 15)
			return "数值过大!";
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' ')
				continue;
			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9)
				return "输入含非数字字符!";

			if (n != 0) {
				if (lastzero)
					RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
				// 除了亿万前的零不带到后面
				// if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
				// 如十进位前有零也不发壹音用此行
				if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
					RMBStr += HanDigiStr[n];
				RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
				hasvalue = true; // 置万进位前有值标记

			} else {
				if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
					RMBStr += HanDiviStr[i]; // “亿”或“万”
			}
			if (i % 8 == 0)
				hasvalue = false; // 万进位前有值标记逢亿复位
			lastzero = (n == 0) && (i % 4 != 0);
		}

		if (RMBStr.length() == 0)
			return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
		return RMBStr;
	}

	public static String numToRMBStr(double val) {
		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		int jiao, fen;

		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "数值位数过大!";
		// 四舍五入到分
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		jiao = (int) fraction / 10;
		fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			TailStr = "整";
		} else {
			TailStr = HanDigiStr[jiao];
			if (jiao != 0)
				TailStr += "角";
			if (integer == 0 && jiao == 0) // 零元后不写零几分
				TailStr = "";
			if (fen != 0)
				TailStr += HanDigiStr[fen] + "分";
		}

		// 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
		// if( !integer ) return SignStr+TailStr;

		return SignStr + positiveIntegerToHanStr(String.valueOf(integer)) + "元"
				+ TailStr;
	}

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("##.####");
		System.out.println(df.format(5555982929292.08));
		System.out.println(NumberUtil.formatPrecision(
				5555982929292.08, 4));
	}

}