package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 网络会议实体类
 * @author SY
 */
public class Netmeeting  implements BaseModel {
	private String netmeetingId;
	private String title;//会议主题
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private Date meetingdate;//会议日期
	private String status;//会议状态
	private String attendee;//出席者
	private String sponsor;//发起人
	private String flag;
	private String attendeename;//出席者姓名


	public Map toMap(){
		return null;
	}
	public void setNetmeetingId(String netmeetingId){
		this.netmeetingId=netmeetingId;
	}
	public String getNetmeetingId(){
		return netmeetingId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public void setMeetingdate(Date meetingdate){
		this.meetingdate=meetingdate;
	}
	public Date getMeetingdate(){
		return meetingdate;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setAttendee(String attendee){
		this.attendee=attendee;
	}
	public String getAttendee(){
		return attendee;
	}
	public void setSponsor(String sponsor){
		this.sponsor=sponsor;
	}
	public String getSponsor(){
		return sponsor;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public String getAttendeename() {
		return attendeename;
	}
	public void setAttendeename(String attendeename) {
		this.attendeename = attendeename;
	}
	
}
