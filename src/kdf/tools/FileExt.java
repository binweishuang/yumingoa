package kdf.tools;

public class FileExt {

	public static String getFileExt(String fileName){
		String ext = null;
		int index = fileName.lastIndexOf(".");
		
		if(index < 0) {
			return null;
		} else {
			ext = fileName.substring(index, fileName.length());
			return ext;
		}
		
	}
}
