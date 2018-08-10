package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class WorkingCondition  implements BaseModel {
	private String conditionId;
	private String conditiontype;
	private String workflowId;
	private String name;
	private String dept;
	private Date starttime;
	private Date endtime;
	private String destination;
	private String title;
	private String content;
	private String vacationtype;
	private Date registdate;
	private String status;
	private String commitstatus;
	private String otherpeople;
	private String biztriplimit;
	private String expense;
	private String traffic;
	private String flag;
	private String checkstatus;
	private String opinion;


	public Map toMap(){
		return null;
	}
	public void setConditionId(String conditionId){
		this.conditionId=conditionId;
	}
	public String getConditionId(){
		return conditionId;
	}
	public void setConditiontype(String conditiontype){
		this.conditiontype=conditiontype;
	}
	public String getConditiontype(){
		return conditiontype;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}
	public void setStarttime(Date starttime){
		this.starttime=starttime;
	}
	public Date getStarttime(){
		return starttime;
	}
	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public Date getEndtime(){
		return endtime;
	}
	public void setDestination(String destination){
		this.destination=destination;
	}
	public String getDestination(){
		return destination;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setVacationtype(String vacationtype){
		this.vacationtype=vacationtype;
	}
	public String getVacationtype(){
		return vacationtype;
	}
	public void setRegistdate(Date registdate){
		this.registdate=registdate;
	}
	public Date getRegistdate(){
		return registdate;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setCommitstatus(String commitstatus){
		this.commitstatus=commitstatus;
	}
	public String getCommitstatus(){
		return commitstatus;
	}
	public void setOtherpeople(String otherpeople){
		this.otherpeople=otherpeople;
	}
	public String getOtherpeople(){
		return otherpeople;
	}
	public void setBiztriplimit(String biztriplimit){
		this.biztriplimit=biztriplimit;
	}
	public String getBiztriplimit(){
		return biztriplimit;
	}
	public void setExpense(String expense){
		this.expense=expense;
	}
	public String getExpense(){
		return expense;
	}
	public void setTraffic(String traffic){
		this.traffic=traffic;
	}
	public String getTraffic(){
		return traffic;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
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
	
}
