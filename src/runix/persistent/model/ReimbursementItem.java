package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class ReimbursementItem  implements BaseModel {
	private String itemId;
	private String itemname;
	private Date time;
	private String reason;
	private Date reimdate;
	private String docpoll;
	private String reimmoney;
	private String otheritem;
	private String otherpoll;
	private String othermoney;
	private String flag;
	private String reimId;


	public Map toMap(){
		return null;
	}
	public void setItemId(String itemId){
		this.itemId=itemId;
	}
	public String getItemId(){
		return itemId;
	}
	public void setItemname(String itemname){
		this.itemname=itemname;
	}
	public String getItemname(){
		return itemname;
	}
	public void setTime(Date time){
		this.time=time;
	}
	public Date getTime(){
		return time;
	}
	public void setReason(String reason){
		this.reason=reason;
	}
	public String getReason(){
		return reason;
	}
	public void setReimdate(Date reimdate){
		this.reimdate=reimdate;
	}
	public Date getReimdate(){
		return reimdate;
	}
	public void setDocpoll(String docpoll){
		this.docpoll=docpoll;
	}
	public String getDocpoll(){
		return docpoll;
	}
	public void setReimmoney(String reimmoney){
		this.reimmoney=reimmoney;
	}
	public String getReimmoney(){
		return reimmoney;
	}
	public void setOtheritem(String otheritem){
		this.otheritem=otheritem;
	}
	public String getOtheritem(){
		return otheritem;
	}
	public void setOtherpoll(String otherpoll){
		this.otherpoll=otherpoll;
	}
	public String getOtherpoll(){
		return otherpoll;
	}
	public void setOthermoney(String othermoney){
		this.othermoney=othermoney;
	}
	public String getOthermoney(){
		return othermoney;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public void setReimId(String reimId){
		this.reimId=reimId;
	}
	public String getReimId(){
		return reimId;
	}
}
