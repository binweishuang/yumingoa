package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Recruitment  implements BaseModel {
	private String recruitmentId;
	private String workflowId;
	private String department;
	private String position;
	private String submitter;
	private String limittime;
	private String title;
	private String peoplenum;
	private String details;
	private String flag;
	private String status;
	private String checkstatus;
	private String opinion;


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Map toMap(){
		return null;
	}
	public void setRecruitmentId(String recruitmentId){
		this.recruitmentId=recruitmentId;
	}
	public String getRecruitmentId(){
		return recruitmentId;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}
	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}
	public void setSubmitter(String submitter){
		this.submitter=submitter;
	}
	public String getSubmitter(){
		return submitter;
	}
	public void setLimittime(String limittime){
		this.limittime=limittime;
	}
	public String getLimittime(){
		return limittime;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setPeoplenum(String peoplenum){
		this.peoplenum=peoplenum;
	}
	public String getPeoplenum(){
		return peoplenum;
	}
	public void setDetails(String details){
		this.details=details;
	}
	public String getDetails(){
		return details;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
