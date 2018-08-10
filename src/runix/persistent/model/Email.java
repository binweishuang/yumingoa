package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Email  implements BaseModel {
	private String emailId;
	private String receiver;
	private String title;
	private String content;
	private String sender;
	private String draftsstatus;
	private String inboxstatus;
	private String readstatus;
	private String outboxstatus;
	private String sendstatus;
	private Date sendtime;
	private String attachname;
	private String attachpath;
	private String receivePeoples;
	private String receivePeoplesId;


	public String getReceivePeoplesId() {
		return receivePeoplesId;
	}
	public void setReceivePeoplesId(String receivePeoplesId) {
		this.receivePeoplesId = receivePeoplesId;
	}
	public String getReceivePeoples() {
		return receivePeoples;
	}
	public void setReceivePeoples(String receivePeoples) {
		this.receivePeoples = receivePeoples;
	}
	public Map toMap(){
		return null;
	}
	public void setEmailId(String emailId){
		this.emailId=emailId;
	}
	public String getEmailId(){
		return emailId;
	}
	public void setReceiver(String receiver){
		this.receiver=receiver;
	}
	public String getReceiver(){
		return receiver;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setSender(String sender){
		this.sender=sender;
	}
	public String getSender(){
		return sender;
	}
	public void setDraftsstatus(String draftsstatus){
		this.draftsstatus=draftsstatus;
	}
	public String getDraftsstatus(){
		return draftsstatus;
	}
	public void setInboxstatus(String inboxstatus){
		this.inboxstatus=inboxstatus;
	}
	public String getInboxstatus(){
		return inboxstatus;
	}
	public void setReadstatus(String readstatus){
		this.readstatus=readstatus;
	}
	public String getReadstatus(){
		return readstatus;
	}
	public void setOutboxstatus(String outboxstatus){
		this.outboxstatus=outboxstatus;
	}
	public String getOutboxstatus(){
		return outboxstatus;
	}
	public void setSendstatus(String sendstatus){
		this.sendstatus=sendstatus;
	}
	public String getSendstatus(){
		return sendstatus;
	}
	public void setSendtime(Date sendtime){
		this.sendtime=sendtime;
	}
	public Date getSendtime(){
		return sendtime;
	}
	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}
	public void setAttachpath(String attachpath){
		this.attachpath=attachpath;
	}
	public String getAttachpath(){
		return attachpath;
	}
}
