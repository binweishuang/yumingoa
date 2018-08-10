/**
 * @(#)OperateMyConfig.java   
 * @create  10/03/11
 * @update  10/03/11
 * 
 */
package kdf.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpLoginException;
/**
 * 
 * 此类的主要作用，登录主机进行上传、下载文件的功能
 * 
 * 
 * @author 吴志鹏
 * @version 1.0.0 2009.04.22
 */
public class FTPUnit {
	
	private Log log = LogFactory.getLog("FILE");
	
	private FtpClient aftp = null;

	private TelnetOutputStream outs = null;
	
	private TelnetInputStream in = null;
	
	private TelnetInputStream getfile = null; 

	private byte[] ch = new byte[4096];

	private String hostname = "";

	private String userName = "";

	private String password = "";

	private int port = 0;

	private String path = "";
	/**
	 * 初始化构造函数
	 * @param hostname 主机名
	 * @param userName 用户名
	 * @param password 用户密码
	 * @param port
	 * @param filePath 文件路径
	 */
	public FTPUnit(String hostname, String userName, String password, int port,
			String filePath) {
		super();
		this.hostname = hostname;
		this.userName = userName;
		this.password = password;
		this.port = port;
		if (filePath.endsWith(File.separator))
			this.path = filePath;
		else
			this.path = filePath + File.separator;
		initFtp();
	}
/**
 * 显示连接信息
 */
	private void initFtp() {
		System.out.println("正在连接" + hostname + "，请等待.....");
		String msg = null;
		try {
			aftp = new FtpClient(hostname, port);
			aftp.login(userName, password);
			aftp.binary();
			msg = "连接主机:" + hostname + "成功!";
			System.out.println(msg);
		} catch (FtpLoginException e) {
			msg = "登陆主机:" + hostname + "失败!请检查用户名或密码是否正确：" + e;
			log.error(msg);
			System.out.println(msg);
		} catch (IOException e) {
			msg = "连接主机:" + hostname + "失败!请检查端口是否正确：" + e;
			log.error(msg);
			System.out.println(msg);
		} catch (SecurityException e) {
			msg = "无权限与主机:" + hostname + "连接!请检查是否有访问权限!" + e;
			log.error(msg);
			System.out.println(msg);
		}
	}

	/**
	 * 将文件目录中全部文件进行上传
	 * 
	 * @param fileDir
	 *            本地文件目录
	 * @return 返回boolean
	 */
	// public boolean upLoadFile(String fileDir) {
	// File fdir = new File(fileDir);
	// String pathName = "";
	// if (fdir != null && fdir.list() != null) {
	// for (int i = 0; i < fdir.list().length; i++) {
	// pathName = fdir.getAbsolutePath() + File.separator
	// + (fdir.list())[i];
	// if (!uploadFile(pathName, path + (fdir.list())[i])) {
	// return false;
	// }
	// }
	// } else {
	// return false;
	// }
	// return true;
	// }
	public boolean upLoadFile(String fileDir) {
		return uploadFile(fileDir, path
				+ fileDir.substring(fileDir.lastIndexOf(File.separator) + 1));
	}

	/**
	 * 上传文件
	 * 
	 * @param filepathname
	 *            上传文件真实路径
	 * @param fileName
	 *            上传文件名
	 * @return 成功返回true 失败返回 false
	 */
	public boolean uploadFile(String filepathname, String fileName) {
		boolean result = true;
		String message = "";
		RandomAccessFile sendFile = null;
		DataOutputStream outputs = null;
		if (aftp != null) {
			System.out.println("正在上传文件" + filepathname + ",请等待....");
			try {
				sendFile = new RandomAccessFile(filepathname, "r");
				sendFile.seek(0);
				outs = aftp.put(fileName);
				outputs = new DataOutputStream(outs);
				int n = 0;
				while ((n = sendFile.read(ch)) > 0) {
					outputs.write(ch, 0, n);
					n = 0;
				}
				outputs.flush();
				message = "上传" + fileName + "文件成功!";
				System.out.println(message);
			} catch (Exception e) {
				message = "上传" + fileName + "文件失败!" + e;
				log.error(message);
				System.out.println(message);
				result = false;
			} finally {
				try {
					if (outputs != null)
						outputs.close();
					if (outs != null)
						outs.close();
					if (sendFile != null)
						sendFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 显示路径下的文件列表
	 * @return 返回文件列表
	 */
	public String viewDir() {
		try {
			System.out.println("dir:"+aftp.pwd());
			byte[] bt;
			in = aftp.list(); 
	        System.out.println(aftp.getResponseString());                                                                       
	        bt=new byte[in.available()];       
	        in.read(bt);   
	        String str = new String(bt);   
	        System.out.println(str);
	        return str;
		} catch(IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 作用：获取指定文件夹下的所有文件名称
	 * 
	 * @param	fullPath			指定文件夹的完整路径
	 * @return	ArrayList			返回获取文件夹下的所有文件名称
	 * @throws	Exception
	 */
	public ArrayList fileNames(String fullPath) throws Exception {
		aftp.ascii(); // 注意，使用字符模式
		TelnetInputStream list = aftp.nameList(fullPath);
		byte[] names = new byte[2048];
		int bufsize = 0;
		bufsize = list.read(names, 0, names.length); // 从流中读取
		list.close();
		ArrayList namesList = new ArrayList();
		int i = 0;
		int j = 0;
		while (i < bufsize /**//* names.length */) {
			// char bc = (char) names;
			// System.out.println(i + " " + bc + " : " + (int) names);
			// i = i + 1;
			if (names[i] == 10) { // 字符模式为10，二进制模式为13
				// 文件名在数据中开始下标为j,i-j为文件名的长度,文件名在数据中的结束下标为i-1
				// System.out.write(names, j, i - j);
				// System.out.println(j + " " + i + " " + (i - j));
				String tempName = new String(names, j, i - j);
				namesList.add(tempName);
				// System.out.println(temp);
				// 处理代码处
				// j = i + 2; // 上一次位置二进制模式
				j = i + 1; // 上一次位置字符模式
			}
			i = i + 1;
		}
		return namesList;
	} 

	
	/**
	 * 转换目录
	 * 
	 * @param dirName 目录名
	 * @return 返回目录
	 */
	public String cdDir(String dirName) {
		try {
			byte[] bt;
			aftp.cd(dirName);
			System.out.println(aftp.getResponseString());
			in = aftp.list();                               
			System.out.println(aftp.getResponseString());                                                                          
	        bt=new byte[in.available()];       
	        in.read(bt);   
	        String str = new String(bt);   
	        System.out.println(str);   
	        System.out.println(aftp.getResponseString());     
	        return str;
		} catch(IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 下载文本文件
	 * @param inFileName 需要下载的文件名
	 * @param outFileName 下载后保存的文件名
	 */
	public void downloadText(String inFileName,String outFileName) {
		InputStreamReader in = null;
        BufferedReader b = null;                        
        BufferedWriter writer = null;
		try {
			getfile =  aftp.get(inFileName);         
			System.out.println(aftp.getResponseString());   
	        aftp.ascii();   
	        System.out.println(aftp.getResponseString());                                                    
	        in = new InputStreamReader(getfile);   
	        b = new BufferedReader(in);                         
	        writer = new BufferedWriter(new FileWriter(new File(outFileName)));                           
	        while(true) {
	        	String s = b.readLine();     
	            if(s == null) {
	            	break;     
	            } else {     
	                writer.write(s);                                           
	                writer.flush();           
	                writer.newLine();
	            }
	        }                             
          
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
			writer.close();   
	        in.close();   
	        b.close();   
	        getfile.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 下载二进制文件
	 * @param inFileName 需要下载的文件名
	 * @param outFileName 下载后保存的文件名
	 */
	public void downloadBinary(String inFileName,String outFileName) {
		FileOutputStream output = null;
		try {
			aftp.binary();            
	        System.out.println(aftp.getResponseString()); 
	        getfile = aftp.get(inFileName);
	        System.out.println(aftp.getResponseString());                                                     
	        output = new FileOutputStream(new File(outFileName));             
	        while(true) {     
	        	int i = getfile.read();                               
	        	if(i == -1) {  
	        		break;     
	        	} else {   
                  output.write((byte)i);   
                  output.flush();   
	        	}                                                 
	        }                             
		} catch(IOException e) {
			
		} finally {
			try {
				getfile.close();
				output.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 断开FTP连接
	 * 
	 */
	public void stopFtp() {
		String message = "";
		try {
			if (outs != null) {
				outs.close();
			}
			if (aftp != null) {
				aftp.closeServer();
				message = "与主机" + hostname + "连接已断开!";
				System.out.println(message);
			}
		} catch (IOException e) {
			message = "与主机" + hostname + "断开连接失败!" + e.getMessage();
			log.error(message);
			System.out.println(message);
		}
	}
}
