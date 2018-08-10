package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class SetAttend  implements BaseModel {
	private String setattendId;
	private String starttime;
	private String endtime;
	private String rest;
	private String absentlimit;


	public Map toMap(){
		return null;
	}
	public void setSetattendId(String setattendId){
		this.setattendId=setattendId;
	}
	public String getSetattendId(){
		return setattendId;
	}
	public void setStarttime(String starttime){
		this.starttime=starttime;
	}
	public String getStarttime(){
		return starttime;
	}
	public void setEndtime(String endtime){
		this.endtime=endtime;
	}
	public String getEndtime(){
		return endtime;
	}
	public void setRest(String rest){
		this.rest=rest;
	}
	public String getRest(){
		return rest;
	}
	public void setAbsentlimit(String absentlimit){
		this.absentlimit=absentlimit;
	}
	public String getAbsentlimit(){
		return absentlimit;
	}
}
