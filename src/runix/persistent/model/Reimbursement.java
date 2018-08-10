package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Reimbursement  implements BaseModel {
	private String reimId;
	private String workflowId;
	private String person;
	private String reimtype;
	private String affiltype;
	private String totalmoney;
	private String totaldoc;
	private String titlle;
	private String remark;
	private String item;
	private String flag;
	private String checkstatus;
	private String opinion;
	private String status;
	private String applytime;
	private String dept;


	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getApplytime() {
		return applytime;
	}
	public void setApplytime(String applytime) {
		this.applytime = applytime;
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
	public Map toMap(){
		return null;
	}
	public void setReimId(String reimId){
		this.reimId=reimId;
	}
	public String getReimId(){
		return reimId;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setPerson(String person){
		this.person=person;
	}
	public String getPerson(){
		return person;
	}
	public void setReimtype(String reimtype){
		this.reimtype=reimtype;
	}
	public String getReimtype(){
		return reimtype;
	}
	public void setAffiltype(String affiltype){
		this.affiltype=affiltype;
	}
	public String getAffiltype(){
		return affiltype;
	}
	public void setTotalmoney(String totalmoney){
		this.totalmoney=totalmoney;
	}
	public String getTotalmoney(){
		return totalmoney;
	}
	public void setTotaldoc(String totaldoc){
		this.totaldoc=totaldoc;
	}
	public String getTotaldoc(){
		return totaldoc;
	}
	public void setTitlle(String titlle){
		this.titlle=titlle;
	}
	public String getTitlle(){
		return titlle;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setItem(String item){
		this.item=item;
	}
	public String getItem(){
		return item;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
