package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class MessageCenter  implements BaseModel {
	@Override
	public String toString() {
		return "MessageCenter [messageId=" + messageId + ", messagesort="
				+ messagesort + ", title=" + title + ", publishtime="
				+ publishtime + ", type=" + type + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", scope=" + scope + ", publisher="
				+ publisher + ", dept=" + dept + ", keywords=" + keywords
				+ ", content=" + content + ", status=" + status + ", flag="
				+ flag + "]";
	}
	private String messageId;
	private String messagesort;
	private String title;
	private Date publishtime;
	private String type;
	private Date starttime;
	private Date endtime;
	private String scope;
	private String publisher;
	private String dept;
	private String keywords;
	private String content;
	private String status;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setMessageId(String messageId){
		this.messageId=messageId;
	}
	public String getMessageId(){
		return messageId;
	}
	public void setMessagesort(String messagesort){
		this.messagesort=messagesort;
	}
	public String getMessagesort(){
		return messagesort;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setPublishtime(Date publishtime){
		this.publishtime=publishtime;
	}
	public Date getPublishtime(){
		return publishtime;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
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
	public void setScope(String scope){
		this.scope=scope;
	}
	public String getScope(){
		return scope;
	}
	public void setPublisher(String publisher){
		this.publisher=publisher;
	}
	public String getPublisher(){
		return publisher;
	}
	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}
	public void setKeywords(String keywords){
		this.keywords=keywords;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
