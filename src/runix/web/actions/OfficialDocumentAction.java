package runix.web.actions;

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
import runix.persistent.model.FlowPeople;
import runix.persistent.model.Matter;
import runix.persistent.model.OfficialDocument;
import runix.persistent.model.UsingEquip;
import runix.service.BaseDataService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.OfficialDocumentService;
import runix.service.ReimbursementService;
/**
 * 公文管理
 * @author runix
 *
 */
public class OfficialDocumentAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List officialDocumentList;
	private String documentId_q;
	private String documentId;
	private String docperson;
	private String docsort;
	private String flowId;
	private String title;
	private String company;
	private String subjectterm;
	private String docsize;
	private String doctype;
	private String secret;
	private String urgency;
	private String receivenum;
	private String copies;
	private Date receivedate;
	private String attachname;
	private String attachpath;
	private String remark;
	private String timelimit;
	private String mainsender;
	private String copysender;
	private String flag;
	private String status;
	private String checkstatus;
	private String opinion;
	
	private String userRole;
	
	private String checkmatter;//用于标识审核详细页面
	
	/**
	 * 人员关联表
	 */
	private String personIds[];
	private String personNames[];
	private String fpId;
	private String relateId;
	private String personId;
	private String sortnum;

	
	/**
	 * 待办事项表
	 */
	private String matterId;
	private String mattername;
	private String sponsor;
	private String executor;
	private String title_m;
	private Date issuetime;
	private String handlestatus;
	private String readstatus;
	
	private List receiveTypeList;//收文类型列表
	private List sendTypeList;//发文类型列表
	private List secretList;//秘密等级列表
	private List urgencyList;//紧急程度列表
	private List<Map> nodes;
	
	private FlowNodeService flowNodeService;
	private ReimbursementService reimbursementService;
	private MatterService matterService;
	private OfficialDocumentService officialDocumentService;
	private BaseDataService baseDataService;

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
			if (!"".equals(doctype) && null != doctype) {
				condition.put("doctype", doctype);
			}
			if (!"".equals(secret) && null != secret) {
				condition.put("secret", secret);
			}
			if (!"".equals(urgency) && null != urgency) {
				condition.put("urgency", urgency);
			}
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			urgencyList = baseDataService.getqBaseByDatatype("URGENT");
			if("1".equals(flag)){
				receiveTypeList = baseDataService.getqBaseByDatatype("RECEIVEFILE");
				condition.put("docsort", "1");//收文
				if(userRole.indexOf("em2") < 0){
					//condition.put("docperson", user.getUserId());
					readonly = true;
				}
			}else if("2".equals(flag)){
				sendTypeList = baseDataService.getqBaseByDatatype("SENDFILE");
				condition.put("docsort", "2");//发文
				if(userRole.indexOf("em3") < 0){
					//condition.put("docperson", user.getUserId());
					readonly = true;
				}
			}
			int record = officialDocumentService.getCountOfficialDocuments(condition);
			List lst =officialDocumentService.getOfficialDocuments(condition, offset, limit);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			officialDocumentList = pg.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			urgencyList = baseDataService.getqBaseByDatatype("URGENT");
			if("1".equals(flag)){
				receiveTypeList = baseDataService.getqBaseByDatatype("RECEIVEFILE");
			}else if("2".equals(flag)){
				sendTypeList = baseDataService.getqBaseByDatatype("SENDFILE");
			}
			OfficialDocument officialDocument =officialDocumentService.getOfficialDocumentById(documentId_q);
			documentId = officialDocument.getDocumentId();
			docperson = officialDocument.getDocperson();
			docsort = officialDocument.getDocsort();
			flowId = officialDocument.getFlowId();
			title = officialDocument.getTitle();
			company = officialDocument.getCompany();
			subjectterm = officialDocument.getSubjectterm();
			docsize = officialDocument.getDocsize();
			doctype = officialDocument.getDoctype();
			secret = officialDocument.getSecret();
			urgency = officialDocument.getUrgency();
			receivenum = officialDocument.getReceivenum();
			copies = officialDocument.getCopies();
			receivedate = officialDocument.getReceivedate();
			attachname = officialDocument.getAttachname();
			attachpath = officialDocument.getAttachpath();
			remark = officialDocument.getRemark();
			timelimit = officialDocument.getTimelimit();
			mainsender = officialDocument.getMainsender();
			copysender = officialDocument.getCopysender();
			flag = officialDocument.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			if("1".equals(flag)){
				OfficialDocument officialDocument =officialDocumentService.getOfficialDocumentById(documentId_q);
				//	officialDocument.setDocumentId(documentId);
				//	officialDocument.setDocperson(docperson);
				//	officialDocument.setDocsort(docsort);
				//	officialDocument.setFlowId(flowId);
					officialDocument.setTitle(title);
					officialDocument.setCompany(company);
					officialDocument.setSubjectterm(subjectterm);
					officialDocument.setDocsize(docsize);
					officialDocument.setDoctype(doctype);
					officialDocument.setSecret(secret);
					officialDocument.setUrgency(urgency);
					officialDocument.setReceivenum(receivenum);
					officialDocument.setCopies(copies);
					officialDocument.setReceivedate(receivedate);
					officialDocument.setAttachname(attachname);
					officialDocument.setAttachpath(attachpath);
					officialDocument.setRemark(remark);
				//	officialDocument.setTimelimit(timelimit);
				//	officialDocument.setMainsender(mainsender);
				//	officialDocument.setCopysender(copysender);
				//	officialDocument.setFlag(flag);
					officialDocumentService.modifyOfficialDocument(officialDocument);
					nodes =  flowNodeService.getNodeNamesByFlowname("收文管理");
			}else if("2".equals(flag)){
				OfficialDocument officialDocument =officialDocumentService.getOfficialDocumentById(documentId_q);
				//	officialDocument.setDocumentId(documentId);
				//	officialDocument.setDocperson(docperson);
				//	officialDocument.setDocsort(docsort);
				//	officialDocument.setFlowId(flowId);
					officialDocument.setTitle(title);
					officialDocument.setCompany(company);
					officialDocument.setSubjectterm(subjectterm);
					officialDocument.setDocsize(docsize);
					officialDocument.setDoctype(doctype);
					officialDocument.setSecret(secret);
					officialDocument.setUrgency(urgency);
//					officialDocument.setReceivenum(receivenum);
					officialDocument.setCopies(copies);
//					officialDocument.setReceivedate(receivedate);
					officialDocument.setAttachname(attachname);
					officialDocument.setAttachpath(attachpath);
					officialDocument.setRemark(remark);
					officialDocument.setTimelimit(timelimit);
					officialDocument.setMainsender(mainsender);
					officialDocument.setCopysender(copysender);
				//	officialDocument.setFlag(flag);
					officialDocumentService.modifyOfficialDocument(officialDocument);
					nodes =  flowNodeService.getNodeNamesByFlowname("发文管理");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(documentId_q);
			reimbursementService.removeFlowPeopleByRelateId(documentId_q);
			officialDocumentService.removeOfficialDocumentById(documentId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			urgencyList = baseDataService.getqBaseByDatatype("URGENT");
			if("1".equals(flag)){
				receiveTypeList = baseDataService.getqBaseByDatatype("RECEIVEFILE");
			}else if("2".equals(flag)){
				sendTypeList = baseDataService.getqBaseByDatatype("SENDFILE");
			}
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				if("1".equals(flag)){//收文
					OfficialDocument officialDocument = new OfficialDocument();
				//	documentId = baseDataService.getSequence();
					//officialDocument.setDocumentId(documentId);
					officialDocument.setDocperson(user.getUserId());
					officialDocument.setDocsort("1");//收文
					officialDocument.setFlowId(flowId);
					officialDocument.setTitle(title);
					officialDocument.setCompany(company);
					officialDocument.setSubjectterm(subjectterm);
					officialDocument.setDocsize(docsize);
					officialDocument.setDoctype(doctype);
					officialDocument.setSecret(secret);
					officialDocument.setUrgency(urgency);
					officialDocument.setReceivenum(receivenum);
					officialDocument.setCopies(copies);
					officialDocument.setReceivedate(receivedate);
					officialDocument.setAttachname(attachname);
					officialDocument.setAttachpath(attachpath);
					officialDocument.setRemark(remark);
					officialDocument.setTimelimit(timelimit);
					officialDocument.setMainsender(mainsender);
					officialDocument.setCopysender(copysender);
					officialDocument.setFlag(flag);
					officialDocument.setStatus("0");
					officialDocument.setCheckstatus("3");
					officialDocument.setOpinion(opinion);
					officialDocumentService.saveOfficialDocument(officialDocument);
					documentId =officialDocument.getDocumentId();
					nodes =  flowNodeService.getNodeNamesByFlowname("收文管理");
				}else if("2".equals(flag)){
					OfficialDocument officialDocument = new OfficialDocument();
					//documentId = baseDataService.getSequence();
				//	officialDocument.setDocumentId(documentId);
					officialDocument.setDocperson(user.getUserId());
					officialDocument.setDocsort("2");//发文
					officialDocument.setFlowId(flowId);
					officialDocument.setTitle(title);
					officialDocument.setCompany(company);
					officialDocument.setSubjectterm(subjectterm);
					officialDocument.setDocsize(docsize);
					officialDocument.setDoctype(doctype);
					officialDocument.setSecret(secret);
					officialDocument.setUrgency(urgency);
					officialDocument.setReceivenum(receivenum);
					officialDocument.setCopies(copies);
					officialDocument.setReceivedate(receivedate);
					officialDocument.setAttachname(attachname);
					officialDocument.setAttachpath(attachpath);
					officialDocument.setRemark(remark);
					officialDocument.setTimelimit(timelimit);
					officialDocument.setMainsender(mainsender);
					officialDocument.setCopysender(copysender);
					officialDocument.setFlag(flag);
					officialDocument.setStatus("0");
					officialDocument.setCheckstatus("3");
					officialDocument.setOpinion(opinion);
					officialDocumentService.saveOfficialDocument(officialDocument);
					documentId =officialDocument.getDocumentId();
					nodes =  flowNodeService.getNodeNamesByFlowname("发文管理");
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
		}
		return SUCCESS;
	}
	
	public String doInsertPerson(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				for(int i=0;i<personIds.length;i++){
					FlowPeople flowPeople = new FlowPeople();
					fpId = baseDataService.getSequence();
					flowPeople.setFpId(fpId);
					flowPeople.setPersonId(personIds[i]);
					flowPeople.setRelateId(documentId);
					flowPeople.setSortnum(i+1+"");
					flowPeople.setHandlestatus("0");
					flowPeople.setReadstatus("0");
					reimbursementService.saveFlowPeople(flowPeople);
				}
				OfficialDocument officialDocument =officialDocumentService.getOfficialDocumentById(documentId);
				officialDocument.setCheckstatus("3");//3未审核，2审核中，1已审核
				officialDocument.setStatus("1");//1已提交，0未提交
				officialDocumentService.modifyOfficialDocument(officialDocument);
				
				Matter matter = new Matter();
				matterId = baseDataService.getSequence();
				matter.setMatterId(matterId);
				matter.setExecutor(personIds[0]);
				matter.setHandlestatus("0");//0未处理,1已处理
				matter.setIssuetime(new Date());
				
				if("1".equals(flag)){
					matter.setMattername("收文申请");
				}else if("2".equals(flag)){
					matter.setMattername("收文申请");
				}
				matter.setReadstatus("0");//0未读1已读
				matter.setRelateId(documentId);
				matter.setSponsor(user.getUserId());
				matter.setTitle(title);
				matter.setSortnum("1");
				matterService.saveMatter(matter);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doView(){
		try {
			OfficialDocument officialDocument =officialDocumentService.getOfficialDocumentByIdForView(documentId_q);
			documentId = officialDocument.getDocumentId();
			docperson = officialDocument.getDocperson();
			docsort = officialDocument.getDocsort();
			flowId = officialDocument.getFlowId();
			title = officialDocument.getTitle();
			company = officialDocument.getCompany();
			subjectterm = officialDocument.getSubjectterm();
			docsize = officialDocument.getDocsize();
			doctype = officialDocument.getDoctype();
			secret = officialDocument.getSecret();
			urgency = officialDocument.getUrgency();
			receivenum = officialDocument.getReceivenum();
			copies = officialDocument.getCopies();
			receivedate = officialDocument.getReceivedate();
			attachname = officialDocument.getAttachname();
			attachpath = officialDocument.getAttachpath();
			remark = officialDocument.getRemark();
			timelimit = officialDocument.getTimelimit();
			checkstatus = officialDocument.getCheckstatus();
			opinion = officialDocument.getOpinion();
			mainsender = officialDocument.getMainsender();
			copysender = officialDocument.getCopysender();
			flag = officialDocument.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "receiveFile";
		}else if("2".equals(flag)){
			return "sendFile";
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

	public void setOfficialDocumentList(List officialDocumentList){
		this.officialDocumentList=officialDocumentList;
	}
	public List getOfficialDocumentList(){
		return officialDocumentList;
	}

	public void setDocumentId_q(String documentId_q){
		this.documentId_q=documentId_q;
	}
	public String getDocumentId_q(){
		return documentId_q;
	}

	public void setDocumentId(String documentId){
		this.documentId=documentId;
	}
	public String getDocumentId(){
		return documentId;
	}

	public void setDocperson(String docperson){
		this.docperson=docperson;
	}
	public String getDocperson(){
		return docperson;
	}

	public void setDocsort(String docsort){
		this.docsort=docsort;
	}
	public String getDocsort(){
		return docsort;
	}

	public void setFlowId(String flowId){
		this.flowId=flowId;
	}
	public String getFlowId(){
		return flowId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setCompany(String company){
		this.company=company;
	}
	public String getCompany(){
		return company;
	}

	public void setSubjectterm(String subjectterm){
		this.subjectterm=subjectterm;
	}
	public String getSubjectterm(){
		return subjectterm;
	}

	public void setDocsize(String docsize){
		this.docsize=docsize;
	}
	public String getDocsize(){
		return docsize;
	}

	public void setDoctype(String doctype){
		this.doctype=doctype;
	}
	public String getDoctype(){
		return doctype;
	}

	public void setSecret(String secret){
		this.secret=secret;
	}
	public String getSecret(){
		return secret;
	}

	public void setUrgency(String urgency){
		this.urgency=urgency;
	}
	public String getUrgency(){
		return urgency;
	}

	public void setReceivenum(String receivenum){
		this.receivenum=receivenum;
	}
	public String getReceivenum(){
		return receivenum;
	}

	public void setCopies(String copies){
		this.copies=copies;
	}
	public String getCopies(){
		return copies;
	}

	public void setReceivedate(Date receivedate){
		this.receivedate=receivedate;
	}
	public Date getReceivedate(){
		return receivedate;
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

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setTimelimit(String timelimit){
		this.timelimit=timelimit;
	}
	public String getTimelimit(){
		return timelimit;
	}

	public void setMainsender(String mainsender){
		this.mainsender=mainsender;
	}
	public String getMainsender(){
		return mainsender;
	}

	public void setCopysender(String copysender){
		this.copysender=copysender;
	}
	public String getCopysender(){
		return copysender;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setOfficialDocumentService(OfficialDocumentService officialDocumentService){
		this.officialDocumentService=officialDocumentService;
	}
	public OfficialDocumentService getOfficialDocumentService(){
		return officialDocumentService;
	}

	public List getReceiveTypeList() {
		return receiveTypeList;
	}

	public void setReceiveTypeList(List receiveTypeList) {
		this.receiveTypeList = receiveTypeList;
	}

	public List getSendTypeList() {
		return sendTypeList;
	}

	public void setSendTypeList(List sendTypeList) {
		this.sendTypeList = sendTypeList;
	}

	public List getSecretList() {
		return secretList;
	}

	public void setSecretList(List secretList) {
		this.secretList = secretList;
	}

	public List getUrgencyList() {
		return urgencyList;
	}

	public void setUrgencyList(List urgencyList) {
		this.urgencyList = urgencyList;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String[] getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String[] personIds) {
		this.personIds = personIds;
	}

	public String[] getPersonNames() {
		return personNames;
	}

	public void setPersonNames(String[] personNames) {
		this.personNames = personNames;
	}

	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSortnum() {
		return sortnum;
	}

	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}

	public String getMatterId() {
		return matterId;
	}

	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}

	public String getMattername() {
		return mattername;
	}

	public void setMattername(String mattername) {
		this.mattername = mattername;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getTitle_m() {
		return title_m;
	}

	public void setTitle_m(String title_m) {
		this.title_m = title_m;
	}

	public Date getIssuetime() {
		return issuetime;
	}

	public void setIssuetime(Date issuetime) {
		this.issuetime = issuetime;
	}

	public String getHandlestatus() {
		return handlestatus;
	}

	public void setHandlestatus(String handlestatus) {
		this.handlestatus = handlestatus;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

	public List<Map> getNodes() {
		return nodes;
	}

	public void setNodes(List<Map> nodes) {
		this.nodes = nodes;
	}

	public FlowNodeService getFlowNodeService() {
		return flowNodeService;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService) {
		this.flowNodeService = flowNodeService;
	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}

	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
	}

	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCheckmatter() {
		return checkmatter;
	}

	public void setCheckmatter(String checkmatter) {
		this.checkmatter = checkmatter;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
