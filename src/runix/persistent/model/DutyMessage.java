package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 值班信息实体类
 * @author SY
 * @time 2013-12-02
 */
public class DutyMessage  implements BaseModel {
	private String dutyId;
	private String dutyperson;//值班人
	private String classes;//班次
	private Date dutydate;//值班日期
	private String starttime;//开始时间
	private String endtime;//结束时间
	private String dutyaddress;//值班地点
	private String remark;//备注
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setDutyId(String dutyId){
		this.dutyId=dutyId;
	}
	public String getDutyId(){
		return dutyId;
	}
	public void setDutyperson(String dutyperson){
		this.dutyperson=dutyperson;
	}
	public String getDutyperson(){
		return dutyperson;
	}
	public void setClasses(String classes){
		this.classes=classes;
	}
	public String getClasses(){
		return classes;
	}
	public void setDutydate(Date dutydate){
		this.dutydate=dutydate;
	}
	public Date getDutydate(){
		return dutydate;
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
	public void setDutyaddress(String dutyaddress){
		this.dutyaddress=dutyaddress;
	}
	public String getDutyaddress(){
		return dutyaddress;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
