package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Schedule  implements BaseModel {
	private String scheduleId;
	private String executer;//执行人(用户自己)
	private String title;//日程标题
	private Date starttime;//起始时间
	private Date endtime;//结束时间
	private Date rangetime;//安排时间(录入时间)
	private String content;//日程内容
	private String flag;//标示字段


	public Map toMap(){
		return null;
	}
	public void setScheduleId(String scheduleId){
		this.scheduleId=scheduleId;
	}
	public String getScheduleId(){
		return scheduleId;
	}
	public void setExecuter(String executer){
		this.executer=executer;
	}
	public String getExecuter(){
		return executer;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setStarttime(Date starttime){
		this.starttime=starttime;
	}
	public Date getStarttime(){
		return starttime;
	}
	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public Date getEndtime(){
		return endtime;
	}
	public void setRangetime(Date rangetime){
		this.rangetime=rangetime;
	}
	public Date getRangetime(){
		return rangetime;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
