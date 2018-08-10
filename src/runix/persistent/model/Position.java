package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Position  implements BaseModel {
	private String positionId;
	private String postname;
	private String category;
	private String description;
	private String screen;
	private String rolerank;
	private String flag;

	public Map toMap(){
		return null;
	}
	public void setPositionId(String positionId){
		this.positionId=positionId;
	}
	public String getPositionId(){
		return positionId;
	}
	public void setPostname(String postname){
		this.postname=postname;
	}
	public String getPostname(){
		return postname;
	}
	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return category;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setScreen(String screen){
		this.screen=screen;
	}
	public String getScreen(){
		return screen;
	}
	public void setRolerank(String rolerank){
		this.rolerank=rolerank;
	}
	public String getRolerank(){
		return rolerank;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
