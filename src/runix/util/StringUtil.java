package runix.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StringUtil {
	
	public static String dateFmt(Date date){
		String time = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date==null){
			date=new Date();
		}
		time = format.format(date);
		return time;
	}
	
	/**
	 * 判断一个元素是否属于一个数组
	 * 
	 * @param str
	 * @param strs
	 * @return
	 */
	public static boolean findString(String str, String[] strs) {
		List list = Arrays.asList(strs);
		return list.contains(str.toLowerCase());
	}
}
