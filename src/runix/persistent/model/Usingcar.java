package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Usingcar  implements BaseModel {
	private String usingcarId;
	private String workflowId;
	private String userId;
	private String driver;
	private String dept;
	private String tel;
	private Date usingtime;
	private Date backtime;
	private String accomnum;
	private String usingproperty;
	private String title;
	private String carId;
	private String usingreason;
	private String usingway;
	private String status;
	private String opinion;
	private String flag;
	private String checkstatus;


	public Map toMap(){
		return null;
	}
	public void setUsingcarId(String usingcarId){
		this.usingcarId=usingcarId;
	}
	public String getUsingcarId(){
		return usingcarId;
	}
	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setDriver(String driver){
		this.driver=driver;
	}
	public String getDriver(){
		return driver;
	}
	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setUsingtime(Date usingtime){
		this.usingtime=usingtime;
	}
	public Date getUsingtime(){
		return usingtime;
	}
	public void setBacktime(Date backtime){
		this.backtime=backtime;
	}
	public Date getBacktime(){
		return backtime;
	}
	public void setAccomnum(String accomnum){
		this.accomnum=accomnum;
	}
	public String getAccomnum(){
		return accomnum;
	}
	public void setUsingproperty(String usingproperty){
		this.usingproperty=usingproperty;
	}
	public String getUsingproperty(){
		return usingproperty;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setCarId(String carId){
		this.carId=carId;
	}
	public String getCarId(){
		return carId;
	}
	public void setUsingreason(String usingreason){
		this.usingreason=usingreason;
	}
	public String getUsingreason(){
		return usingreason;
	}
	public void setUsingway(String usingway){
		this.usingway=usingway;
	}
	public String getUsingway(){
		return usingway;
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
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	
}
