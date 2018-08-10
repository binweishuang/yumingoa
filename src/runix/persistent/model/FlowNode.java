package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class FlowNode  implements BaseModel {
	private String flownodeId;
	private String nodename;
	private String appronum;
	private String approleaders;
	private String leadersid;
	public String getLeadersid() {
		return leadersid;
	}
	public void setLeadersid(String leadersid) {
		this.leadersid = leadersid;
	}
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setFlownodeId(String flownodeId){
		this.flownodeId=flownodeId;
	}
	public String getFlownodeId(){
		return flownodeId;
	}
	public void setNodename(String nodename){
		this.nodename=nodename;
	}
	public String getNodename(){
		return nodename;
	}
	public void setAppronum(String appronum){
		this.appronum=appronum;
	}
	public String getAppronum(){
		return appronum;
	}
	public void setApproleaders(String approleaders){
		this.approleaders=approleaders;
	}
	public String getApproleaders(){
		return approleaders;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
