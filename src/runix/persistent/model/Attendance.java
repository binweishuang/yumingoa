package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Attendance  implements BaseModel {
	private String attendId;
	private String name;
	private String dept;
	private String signinstatus;
	private String latestatus;
	private String leavestatus;
	private String vacationstatus;
	private String gooutstatus;
	private String biztripstatus;
	private String absentstatus;
	private String travelstatus;
	private Date attenddate;
	private String signintime;
	private String signouttime;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setAttendId(String attendId){
		this.attendId=attendId;
	}
	public String getAttendId(){
		return attendId;
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
	public void setSigninstatus(String signinstatus){
		this.signinstatus=signinstatus;
	}
	public String getSigninstatus(){
		return signinstatus;
	}
	public void setLatestatus(String latestatus){
		this.latestatus=latestatus;
	}
	public String getLatestatus(){
		return latestatus;
	}
	public void setLeavestatus(String leavestatus){
		this.leavestatus=leavestatus;
	}
	public String getLeavestatus(){
		return leavestatus;
	}
	public void setVacationstatus(String vacationstatus){
		this.vacationstatus=vacationstatus;
	}
	public String getVacationstatus(){
		return vacationstatus;
	}
	public void setGooutstatus(String gooutstatus){
		this.gooutstatus=gooutstatus;
	}
	public String getGooutstatus(){
		return gooutstatus;
	}
	public void setBiztripstatus(String biztripstatus){
		this.biztripstatus=biztripstatus;
	}
	public String getBiztripstatus(){
		return biztripstatus;
	}
	public void setAbsentstatus(String absentstatus){
		this.absentstatus=absentstatus;
	}
	public String getAbsentstatus(){
		return absentstatus;
	}
	public void setTravelstatus(String travelstatus){
		this.travelstatus=travelstatus;
	}
	public String getTravelstatus(){
		return travelstatus;
	}
	public void setAttenddate(Date attenddate){
		this.attenddate=attenddate;
	}
	public Date getAttenddate(){
		return attenddate;
	}
	public void setSignintime(String signintime){
		this.signintime=signintime;
	}
	public String getSignintime(){
		return signintime;
	}
	public void setSignouttime(String signouttime){
		this.signouttime=signouttime;
	}
	public String getSignouttime(){
		return signouttime;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
