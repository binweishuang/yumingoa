package runix.persistent.model;

import java.util.Date;
import java.util.Map;

import kdf.persistent.model.BaseModel;

/**
 * 请示管理实体类
 * @author SY
 */
public class Apply  implements BaseModel {
	private String applicationId;
	private String workflowId;//关联流程
	private String type;//请示类型
	private String secret;//机密等级
	private String urgency;//紧急程度
	private String title;//请示标题
	private String money;//请示金额
	private String attachname;//附件名称
	private String attachpath;//附件地址
	private String content;//请示内容
	private String applicant;//请示人
	private String department;//部门
	private String checkstatus;//审核状态
	private String status;//提交状态
	private String opinion;//意见
	private Date issuedate;//申请日期
	private String flag;

	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	
	public Map toMap(){
		return null;
	}
	public void setApplicationId(String applicationId){
		this.applicationId=applicationId;
	}
	public String getApplicationId(){
		return applicationId;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setSecret(String secret){
		this.secret=secret;
	}
	public String getSecret(){
		return secret;
	}
	public void setUrgency(String urgency){
		this.urgency=urgency;
	}
	public String getUrgency(){
		return urgency;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setMoney(String money){
		this.money=money;
	}
	public String getMoney(){
		return money;
	}
	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}
	public void setAttachpath(String attachpath){
		this.attachpath=attachpath;
	}
	public String getAttachpath(){
		return attachpath;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setApplicant(String applicant){
		this.applicant=applicant;
	}
	public String getApplicant(){
		return applicant;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setOpinion(String opinion){
		this.opinion=opinion;
	}
	public String getOpinion(){
		return opinion;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
}
