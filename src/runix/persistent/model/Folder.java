package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Folder  implements BaseModel {
	private String folderId;
	private String foldername;
	private String flag;
	private String userId;

	public Map toMap(){
		return null;
	}
	public void setFolderId(String folderId){
		this.folderId=folderId;
	}
	public String getFolderId(){
		return folderId;
	}
	public void setFoldername(String foldername){
		this.foldername=foldername;
	}
	public String getFoldername(){
		return foldername;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
