package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Workflow  implements BaseModel {
	private String workflowId;
	private String flowname;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setFlowname(String flowname){
		this.flowname=flowname;
	}
	public String getFlowname(){
		return flowname;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
