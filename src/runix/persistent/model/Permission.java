package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Permission  implements BaseModel {
	//目前只用title、sortnum两个字段，其他字段保留备用
	private String permissionId;
	private String title;//当前的权限
	private String url;
	private String sortnum;//权限级别
	private String parentid;//职位
	private String flag;

	public Map toMap(){
		return null;
	}
	public void setPermissionId(String permissionId){
		this.permissionId=permissionId;
	}
	public String getPermissionId(){
		return permissionId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setSortnum(String sortnum){
		this.sortnum=sortnum;
	}
	public String getSortnum(){
		return sortnum;
	}
	public void setParentid(String parentid){
		this.parentid=parentid;
	}
	public String getParentid(){
		return parentid;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
