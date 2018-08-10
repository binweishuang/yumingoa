package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Netdisk  implements BaseModel {
	private String fileId;		//文件编号
	private String filename;	//文件名
	private String filesize;	//文件大小
	private String filepath;	//路径
	private Date uploadtime;	//上传时间
	private String publicity;	//是否共享
	private String folderId;	//文件夹
	private String uploader;	//文件夹
	private String flag;		//标示符


	public Map<String, String> toMap(){
		return null;
	}
	public void setFileId(String fileId){
		this.fileId=fileId;
	}
	public String getFileId(){
		return fileId;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}
	public String getFilename(){
		return filename;
	}
	public void setFilesize(String filesize){
		this.filesize=filesize;
	}
	public String getFilesize(){
		return filesize;
	}
	public void setFilepath(String filepath){
		this.filepath=filepath;
	}
	public String getFilepath(){
		return filepath;
	}
	public void setUploadtime(Date uploadtime){
		this.uploadtime=uploadtime;
	}
	public Date getUploadtime(){
		return uploadtime;
	}
	public void setPublicity(String publicity){
		this.publicity=publicity;
	}
	public String getPublicity(){
		return publicity;
	}
	public void setFolderId(String folderId){
		this.folderId=folderId;
	}
	public String getFolderId(){
		return folderId;
	}
	public void setUploader(String uploader){
		this.uploader=uploader;
	}
	public String getUploader(){
		return uploader;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
