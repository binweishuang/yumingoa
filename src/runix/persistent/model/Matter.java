package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Matter  implements BaseModel {
	private String matterId;
	private String mattername;//事项名称
	private String sponsor;//发起人
	private String executor;//执行人
	private String title;//事项标题
	private Date issuetime;//发布时间
	private String handlestatus;//处理状态
	private String readstatus;//阅读状态
	private String flag;
	private String relateId;//管理模块ID
	private String sortnum;//执行人的顺序


	public Map toMap(){
		return null;
	}
	public void setMatterId(String matterId){
		this.matterId=matterId;
	}
	public String getMatterId(){
		return matterId;
	}
	public void setMattername(String mattername){
		this.mattername=mattername;
	}
	public String getMattername(){
		return mattername;
	}
	public void setSponsor(String sponsor){
		this.sponsor=sponsor;
	}
	public String getSponsor(){
		return sponsor;
	}
	public void setExecutor(String executor){
		this.executor=executor;
	}
	public String getExecutor(){
		return executor;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setIssuetime(Date issuetime){
		this.issuetime=issuetime;
	}
	public Date getIssuetime(){
		return issuetime;
	}
	public void setHandlestatus(String handlestatus){
		this.handlestatus=handlestatus;
	}
	public String getHandlestatus(){
		return handlestatus;
	}
	public void setReadstatus(String readstatus){
		this.readstatus=readstatus;
	}
	public String getReadstatus(){
		return readstatus;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public void setRelateId(String relateId){
		this.relateId=relateId;
	}
	public String getRelateId(){
		return relateId;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
	
	
}
