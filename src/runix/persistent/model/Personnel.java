package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Personnel  implements BaseModel {
	private String personnelId;
	private String perstype;//类型	1：转正；2：离职；3：调整；
	private String name;//姓名
	private String dept;//原部门
	private Date applytime;//申请日期
	private String handlestatus;//办理状态
	private String commitstatus;//提交状态
	private String workflowId;//流程名称
	private String content;//编辑内容
	private String dimtype;//离职类型
	private String dimstatus;//是否离职
	private String title;//概述
	private String oldpositioln;//原职位
	private String newposition;//新职位
	private String newdept;//新部门
	private String attachname;//附件名称
	private String atachpath;//附件路径
	private String flag;
	private String status;//提交状态 	1:提交；0:未提交;
	private String checkstatus;//审核状态	 1:已审核；2:审核中；3:未审核；
	private String opinion;//审核意见

	public Map toMap(){
		return null;
	}
	public void setPersonnelId(String personnelId){
		this.personnelId=personnelId;
	}
	public String getPersonnelId(){
		return personnelId;
	}
	public void setPerstype(String perstype){
		this.perstype=perstype;
	}
	public String getPerstype(){
		return perstype;
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
	public void setApplytime(Date applytime){
		this.applytime=applytime;
	}
	public Date getApplytime(){
		return applytime;
	}
	public void setHandlestatus(String handlestatus){
		this.handlestatus=handlestatus;
	}
	public String getHandlestatus(){
		return handlestatus;
	}
	public void setCommitstatus(String commitstatus){
		this.commitstatus=commitstatus;
	}
	public String getCommitstatus(){
		return commitstatus;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setDimtype(String dimtype){
		this.dimtype=dimtype;
	}
	public String getDimtype(){
		return dimtype;
	}
	public void setDimstatus(String dimstatus){
		this.dimstatus=dimstatus;
	}
	public String getDimstatus(){
		return dimstatus;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setOldpositioln(String oldpositioln){
		this.oldpositioln=oldpositioln;
	}
	public String getOldpositioln(){
		return oldpositioln;
	}
	public void setNewposition(String newposition){
		this.newposition=newposition;
	}
	public String getNewposition(){
		return newposition;
	}
	public void setNewdept(String newdept){
		this.newdept=newdept;
	}
	public String getNewdept(){
		return newdept;
	}
	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}
	public void setAtachpath(String atachpath){
		this.atachpath=atachpath;
	}
	public String getAtachpath(){
		return atachpath;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
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
	
}
