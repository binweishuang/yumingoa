package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class OfficialDocument  implements BaseModel {
	private String documentId;
	private String docperson;
	private String docsort;
	private String flowId;
	private String title;
	private String company;
	private String subjectterm;
	private String docsize;
	private String doctype;
	private String secret;
	private String urgency;
	private String receivenum;
	private String copies;
	private Date receivedate;
	private String attachname;
	private String attachpath;
	private String remark;
	private String timelimit;
	private String mainsender;
	private String copysender;
	private String flag;
	private String status;
	private String checkstatus;
	private String opinion;


	public Map toMap(){
		return null;
	}
	public void setDocumentId(String documentId){
		this.documentId=documentId;
	}
	public String getDocumentId(){
		return documentId;
	}
	public void setDocperson(String docperson){
		this.docperson=docperson;
	}
	public String getDocperson(){
		return docperson;
	}
	public void setDocsort(String docsort){
		this.docsort=docsort;
	}
	public String getDocsort(){
		return docsort;
	}
	public void setFlowId(String flowId){
		this.flowId=flowId;
	}
	public String getFlowId(){
		return flowId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setCompany(String company){
		this.company=company;
	}
	public String getCompany(){
		return company;
	}
	public void setSubjectterm(String subjectterm){
		this.subjectterm=subjectterm;
	}
	public String getSubjectterm(){
		return subjectterm;
	}
	public void setDocsize(String docsize){
		this.docsize=docsize;
	}
	public String getDocsize(){
		return docsize;
	}
	public void setDoctype(String doctype){
		this.doctype=doctype;
	}
	public String getDoctype(){
		return doctype;
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
	public void setReceivenum(String receivenum){
		this.receivenum=receivenum;
	}
	public String getReceivenum(){
		return receivenum;
	}
	public void setCopies(String copies){
		this.copies=copies;
	}
	public String getCopies(){
		return copies;
	}
	public void setReceivedate(Date receivedate){
		this.receivedate=receivedate;
	}
	public Date getReceivedate(){
		return receivedate;
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
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setTimelimit(String timelimit){
		this.timelimit=timelimit;
	}
	public String getTimelimit(){
		return timelimit;
	}
	public void setMainsender(String mainsender){
		this.mainsender=mainsender;
	}
	public String getMainsender(){
		return mainsender;
	}
	public void setCopysender(String copysender){
		this.copysender=copysender;
	}
	public String getCopysender(){
		return copysender;
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
