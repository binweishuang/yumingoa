package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Dept  implements BaseModel {
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptname=" + deptname + ", phone="
				+ phone + ", description=" + description + ", parentid="
				+ parentid + ", flag=" + flag + "]";
	}
	private String deptId;
	private String deptname;
	private String phone;
	private String description;
	private String parentid;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setDeptId(String deptId){
		this.deptId=deptId;
	}
	public String getDeptId(){
		return deptId;
	}
	public void setDeptname(String deptname){
		this.deptname=deptname;
	}
	public String getDeptname(){
		return deptname;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
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
