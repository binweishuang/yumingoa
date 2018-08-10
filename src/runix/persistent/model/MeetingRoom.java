package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 会议室实体类
 * @author SY
 */
public class MeetingRoom  implements BaseModel {
	private String roomId;
	private String spokesman;//发言人
	private String content;//发言内容
	private Date speaktime;//发言时间
	private String netmeetingId;//关联会议
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setRoomId(String roomId){
		this.roomId=roomId;
	}
	public String getRoomId(){
		return roomId;
	}
	public void setSpokesman(String spokesman){
		this.spokesman=spokesman;
	}
	public String getSpokesman(){
		return spokesman;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setSpeaktime(Date speaktime){
		this.speaktime=speaktime;
	}
	public Date getSpeaktime(){
		return speaktime;
	}
	public void setNetmeetingId(String netmeetingId){
		this.netmeetingId=netmeetingId;
	}
	public String getNetmeetingId(){
		return netmeetingId;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
