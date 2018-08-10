package kdf.chart.util;

public class CommonMethod {
	public static String convertBooleanToString(Boolean b) {
		if(b.booleanValue()==true) 
			return "1";
		else 
			return "0";
	}
	
	public static String convertIntegerToString(Integer i) {
		return String.valueOf(i);
	}
	
	public static String convertDoubleToString(Double d) {
		return String.valueOf(d);
	}
	
	public static String replaceSpecialCharacters(String dataXml) {
		dataXml = dataXml.replaceAll("%", "%25");
		dataXml = dataXml.replaceAll("&amp;", "%26");
		dataXml = dataXml.replaceAll("&", "%26");
		dataXml = dataXml.replaceAll("'", "%26apos;");		
		dataXml = dataXml.replaceAll("��", "%E2%82%AC");
		dataXml = dataXml.replaceAll("�6�3", "%E2%82%A3");
		dataXml = dataXml.replaceAll("�0�6", "%A5");
		dataXml = dataXml.replaceAll("�0�5", "%A3");		
		dataXml = dataXml.replaceAll("�0�4", "%A2");		
		return dataXml;
	}
}
