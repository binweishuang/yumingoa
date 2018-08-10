package runix.web.actions;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Email;
import runix.service.BaseDataService;
import runix.service.EmailService;

public class EmailAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List emailList;
	private String emailId_q;
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
	private String flag;//1:收件箱2：发件箱3：草稿箱
	private String act ;//
	private List emails;
	public List getEmails() {
		return emails;
	}
	public void setEmails(List emails) {
		this.emails = emails;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	private EmailService emailService;
	private BaseDataService baseDataService;

	
	public String doQueryEmail(){
		return SUCCESS;
	}
	public String doQuery(){
		try {
			int currentPageInt = 1;
			String strCurrentPage = currentPage;
			if (strCurrentPage != null && !"".equals(strCurrentPage)) {
				try {
					currentPageInt = Integer.parseInt(strCurrentPage);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					this.addActionError("please check! there is Exception");
				}
			}
			int offset = (currentPageInt-1) * pages;
			int limit = pages;

			Map condition = new HashMap();
			if (!"".equals(sender) && null != sender) {
				sender = new String(sender.getBytes("iso-8859-1"),"utf-8");
				condition.put("sender", "%"+sender+"%");
			}
			if (!"".equals(readstatus) && null != readstatus) {
				condition.put("readstatus", readstatus);
			}
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				if("1".equals(flag)){
					if (!"".equals(receiver) && null != receiver) {
						receiver = new String(receiver.getBytes("iso-8859-1"),"utf-8");
						condition.put("receiver", "%"+receiver+"%");
					}
					condition.put("receive", user.getUserId());
				}else if("2".equals(flag)){
					if (!"".equals(receiver) && null != receiver) {
						receiver = new String(receiver.getBytes("iso-8859-1"),"utf-8");
						condition.put("receivePeoples", "%"+receiver+"%");
					}
					condition.put("send", user.getUserId());
				}else if("3".equals(flag)){
					if (!"".equals(receiver) && null != receiver) {
						receiver = new String(receiver.getBytes("iso-8859-1"),"utf-8");
						condition.put("receivePeoples", "%"+receiver+"%");
					}
					condition.put("draft", user.getUserId());
				}
				
				int record = emailService.getCountEmails(condition);
				List lst =emailService.getEmails(condition, offset, limit);
				
				Pageable pg = null;
				try {
					pg = new IbatisPage(lst, record, currentPageInt, pages);
				} catch (PageException e) {
					pg = null;
				}
				ServletActionContext.getRequest().setAttribute("pages", pg);
				emailList = pg.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receive";
		}else if("2".equals(flag)){
			return "send";
		}else if("3".equals(flag)){
			return "draft";
		}
		return SUCCESS;
	}


	public String doUpdateInit(){
		try {
			Email email =emailService.getEmailById(emailId_q);
			emailId = email.getEmailId();
			receivePeoples = email.getReceivePeoples();
			receivePeoplesId = email.getReceivePeoplesId();
			title = email.getTitle();
			content = email.getContent();
			attachname = email.getAttachname();
			attachpath = email.getAttachpath().substring(email.getAttachpath().indexOf("/upload"));
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Email email =emailService.getEmailById(emailId_q);	
				email.setTitle(title);
				email.setContent(content);
				if("save".equals(act)){
					email.setDraftsstatus("1");
					email.setSendstatus("0");
					email.setSendtime(sendtime);
					email.setInboxstatus(inboxstatus);
					email.setReadstatus(readstatus);
					email.setOutboxstatus(outboxstatus);
				}else if("send".equals(act)){
					email.setDraftsstatus("0");
					email.setSendstatus("1");
					email.setSendtime(new Date());
					email.setInboxstatus("1");
					email.setReadstatus("0");
					email.setOutboxstatus("1");
				}
				email.setAttachname(attachname);
				email.setAttachpath(attachpath);
				email.setReceivePeoples(receivePeoples);
				email.setReceivePeoplesId(receivePeoplesId);
				emailService.modifyEmail(email);
				if("send".equals(act)){
					String pids[] = receivePeoplesId.split(",");
					for(int i=0;i<pids.length;i++){
						Email email1 = new Email();
					//	emailId = baseDataService.getSequence();
					//	email1.setEmailId(emailId);
						email1.setReceiver(pids[i]);
						email1.setTitle(title);
						email1.setContent(content);
						email1.setSender(user.getUserId());
						email1.setDraftsstatus("0");
						email1.setSendstatus("1");
						email1.setSendtime(new Date());
						email1.setInboxstatus("1");
						email1.setReadstatus("0");
						email1.setOutboxstatus("1");
						email1.setAttachname(attachname);
						email1.setAttachpath(attachpath);
						email1.setReceivePeoples(null);
						email1.setReceivePeoplesId(null);
						emailService.saveEmail(email1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public void dodeleteOldFile(){
		String paths[] = attachpath.split(";");
		for (int i = 0; i < paths.length; i++) {
			File file = new File(ServletActionContext.getServletContext().getRealPath("")+paths[i]);
			if(file.exists()){
				file.delete();
			}else{
				System.out.println("没有找到对应的文件！");
			}
		}
	}
	public String doDelete(){
		try {
			
			if("1".equals(flag)){
				Email email =emailService.getEmailById(emailId_q);
				email.setInboxstatus("0");
				emailService.modifyEmail(email);
			}else if("2".equals(flag)){
				Email email =emailService.getEmailById(emailId_q);
				email.setOutboxstatus("0");
				emailService.modifyEmail(email);
			}else if("3".equals(flag)){
				emailService.removeEmailById(emailId_q);
			}
			//emailService.removeEmailById(emailId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Email email = new Email();
				//emailId = baseDataService.getSequence();
			//	email.setEmailId(emailId);
				email.setReceiver(receiver);
				email.setTitle(title);
				email.setContent(content);
				email.setSender(user.getUserId());
				if("save".equals(act)){
					email.setDraftsstatus("1");
					email.setSendstatus("0");
					email.setSendtime(sendtime);
					email.setInboxstatus(inboxstatus);
					email.setReadstatus(readstatus);
					email.setOutboxstatus(outboxstatus);
				}else if("send".equals(act)){
					email.setDraftsstatus("0");
					email.setSendstatus("1");
					email.setSendtime(new Date());
					email.setInboxstatus("1");
					email.setReadstatus("0");
					email.setOutboxstatus("1");
				}
				email.setAttachname(attachname);
				email.setAttachpath(attachpath);
				email.setReceivePeoples(receivePeoples);
				email.setReceivePeoplesId(receivePeoplesId);
				emailService.saveEmail(email);
				if("send".equals(act)){
					String pids[] = receivePeoplesId.split(",");
					for(int i=0;i<pids.length;i++){
						Email email1 = new Email();
						//emailId = baseDataService.getSequence();
						//email1.setEmailId(emailId);
						email1.setReceiver(pids[i]);
						email1.setTitle(title);
						email1.setContent(content);
						email1.setSender(user.getUserId());
		//				if("save".equals(act)){
		//					email1.setDraftsstatus("1");
		//					email1.setSendstatus("0");
		//					email1.setSendtime(sendtime);
		//					email1.setInboxstatus(inboxstatus);
		//					email1.setReadstatus(readstatus);
		//					email1.setOutboxstatus(outboxstatus);
		//				}else 
						email1.setDraftsstatus("0");
						email1.setSendstatus("1");
						email1.setSendtime(new Date());
						email1.setInboxstatus("1");
						email1.setReadstatus("0");
						email1.setOutboxstatus("1");
						email1.setAttachname(attachname);
						email1.setAttachpath(attachpath);
						email1.setReceivePeoples(null);
						email1.setReceivePeoplesId(null);
						emailService.saveEmail(email1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Email email =emailService.getEmailById2(emailId_q);	
			receiver = email.getReceiver();
			sender = email.getSender();
			sendtime = email.getSendtime();
			receivePeoples = email.getReceivePeoples();
			receivePeoplesId = email.getReceivePeoplesId();
			title = email.getTitle();
			content = email.getContent();
			attachpath = email.getAttachpath();
			attachname = email.getAttachname();
			
			if("1".equals(flag)){
				Email email2 =emailService.getEmailById(emailId_q);	
				email2.setReadstatus("1");
				emailService.modifyEmail(email2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly){
		this.readonly=readonly;
	}
	public boolean getReadonly(){
		return readonly;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage=currentPage;
	}
	public String getCurrentPage(){
		return currentPage;
	}

	public void setPages(int pages){
		this.pages=pages;
	}
	public int getPages(){
		return pages;
	}

	public void setEmailList(List emailList){
		this.emailList=emailList;
	}
	public List getEmailList(){
		return emailList;
	}

	public void setEmailId_q(String emailId_q){
		this.emailId_q=emailId_q;
	}
	public String getEmailId_q(){
		return emailId_q;
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

	public void setEmailService(EmailService emailService){
		this.emailService=emailService;
	}
	public EmailService getEmailService(){
		return emailService;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getReceivePeoples() {
		return receivePeoples;
	}
	public void setReceivePeoples(String receivePeoples) {
		this.receivePeoples = receivePeoples;
	}
	public String getReceivePeoplesId() {
		return receivePeoplesId;
	}
	public void setReceivePeoplesId(String receivePeoplesId) {
		this.receivePeoplesId = receivePeoplesId;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

}
