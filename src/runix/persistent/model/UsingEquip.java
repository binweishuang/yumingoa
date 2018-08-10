package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class UsingEquip  implements BaseModel {
	private String usingequipId;
	private String workflowId;
	private String usecondition;
	private String storage;
	private String cateogory;
	private String equipmentId;
	private String storagenum;
	private String applynum;
	private Date applytime;
	private String remark;
	private String applicant;
	private String department;
	private String status;
	private String opinion;
	private String flag;
	private String title;//申请概述
	private String checkstatus;//审核状态
	private String starttime;//开始时间
	private String endtime;//结束时间


	public Map toMap(){
		return null;
	}
	public void setUsingequipId(String usingequipId){
		this.usingequipId=usingequipId;
	}
	public String getUsingequipId(){
		return usingequipId;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setUsecondition(String usecondition){
		this.usecondition=usecondition;
	}
	public String getUsecondition(){
		return usecondition;
	}
	public void setStorage(String storage){
		this.storage=storage;
	}
	public String getStorage(){
		return storage;
	}
	public void setCateogory(String cateogory){
		this.cateogory=cateogory;
	}
	public String getCateogory(){
		return cateogory;
	}
	public void setEquipmentId(String equipmentId){
		this.equipmentId=equipmentId;
	}
	public String getEquipmentId(){
		return equipmentId;
	}
	public void setStoragenum(String storagenum){
		this.storagenum=storagenum;
	}
	public String getStoragenum(){
		return storagenum;
	}
	public void setApplynum(String applynum){
		this.applynum=applynum;
	}
	public String getApplynum(){
		return applynum;
	}
	public void setApplytime(Date applytime){
		this.applytime=applytime;
	}
	public Date getApplytime(){
		return applytime;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
