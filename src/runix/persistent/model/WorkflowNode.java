package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class WorkflowNode  implements BaseModel {
	private String wnid;
	private String workflowId;
	private String nodeId;



	public Map toMap(){
		return null;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public String getWnid() {
		return wnid;
	}
	public void setWnid(String wnid) {
		this.wnid = wnid;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
}
