package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class UserTask  implements BaseModel {
	private String utId;
	private String taskId;
	private String userId;
	private String process;
	private String comletetion;
	private String taskcomplete;
	

	public Map toMap(){
		return null;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUtId() {
		return utId;
	}

	public void setUtId(String utId) {
		this.utId = utId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getComletetion() {
		return comletetion;
	}

	public void setComletetion(String comletetion) {
		this.comletetion = comletetion;
	}

	public String getTaskcomplete() {
		return taskcomplete;
	}

	public void setTaskcomplete(String taskcomplete) {
		this.taskcomplete = taskcomplete;
	}





}
