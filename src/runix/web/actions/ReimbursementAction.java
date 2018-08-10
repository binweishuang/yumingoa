package runix.web.actions;

import java.text.SimpleDateFormat;
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
import runix.persistent.model.Reimbursement;
import runix.persistent.model.ReimbursementItem;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.ReimbursementItemService;
import runix.service.ReimbursementService;

public class ReimbursementAction extends BaseAction{


	private boolean readonly;

	private String currentPage;

	private int pages=15;

	private List reimbursementList;

	private String reimId_q;

	private String reimId;

	private String workflowId;

	private String person;

	private String reimtype;

	private String affiltype;

	private String totalmoney;

	private String totaldoc;

	private String titlle;

	private String remark;
	
	private String dept;

	private String item;

	private String flag;
	private String checkstatus;
	private String applytime;

	private String opinion;
	private String status;
	private Date currentdate;
	private Date reimdate[];
	private String itemId;
	private String itemname[];
	private Date time[];
	private String reason[];
	private String docpoll[];
	private String reimmoney[];
	private String otheritem[];
	private String otherpoll[];
	private String othermoney[];
	private List reimtypeList;
	private List<Map> nodes;
	private String userid;
	private String userRole;
	
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
	private String title;
	private Date issuetime;
	private String handlestatus;
	private String readstatus;
	
	
	private List reimbursementItemList;


	private BaseDataService baseDataService;
	private FlowNodeService flowNodeService;
	private MatterService matterService;
	private BaseUserService baseUserService;
	public FlowNodeService getFlowNodeService() {
		return flowNodeService;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService) {
		this.flowNodeService = flowNodeService;
	}
	private ReimbursementItemService reimbursementItemService;

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public List getReimtypeList() {
		return reimtypeList;
	}

	public void setReimtypeList(List reimtypeList) {
		this.reimtypeList = reimtypeList;
	}
	private ReimbursementService reimbursementService;
	
	public String doQueryReimb(){
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
			if (!"".equals(checkstatus) && null != checkstatus) {
				condition.put("checkstatus", checkstatus);
			}
			if (!"".equals(reimtype) && null != reimtype) {
				condition.put("reimtype", reimtype);
			}

			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("cm5") < 0){
					condition.put("person", user.getName());
				}else{
					if (!"".equals(person) && null != person) {
						condition.put("person", "%"+person+"%");
					}
				}
				
			}
			reimtypeList = baseDataService.getqBaseByDatatype("REIMBURSE");
			int record = reimbursementService.getCountReimbursements(condition);
			List lst =reimbursementService.getReimbursements(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			reimbursementList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doUpdateInit(){
		try {
			reimtypeList = baseDataService.getqBaseByDatatype("REIMBURSE");
			Reimbursement reimbursement =reimbursementService.getReimbursementById(reimId_q);
			reimId = reimbursement.getReimId();
			workflowId = reimbursement.getWorkflowId();
			person = reimbursement.getPerson();
			reimtype = reimbursement.getReimtype();
			affiltype = reimbursement.getAffiltype();
			totalmoney = reimbursement.getTotalmoney();
			totaldoc = reimbursement.getTotaldoc();
			titlle = reimbursement.getTitlle();
			remark = reimbursement.getRemark();
			item = reimbursement.getItem();
			flag = reimbursement.getFlag();
			status = reimbursement.getStatus();
			readonly = true;
			reimbursementItemList = reimbursementItemService.getReimbursementItemsByReimId(reimId_q);
			
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doUpdate(){
		try {
			Reimbursement reimbursement =reimbursementService.getReimbursementById(reimId_q);
			reimbursement.setReimId(reimId);
			reimbursement.setWorkflowId(workflowId);
			//reimbursement.setPerson(person);
			reimbursement.setReimtype(reimtype);
			reimbursement.setAffiltype(affiltype);
			reimbursement.setTotalmoney(totalmoney);
			reimbursement.setTotaldoc(totaldoc);
			reimbursement.setTitlle(titlle);
			reimbursement.setRemark(remark);
			reimbursement.setItem(item);
			reimbursement.setFlag(flag);
			reimbursementService.modifyReimbursement(reimbursement);
			reimbursementItemService.removeReimbursementItemsByReimId(reimId);
			for(int i=0;i<itemname.length;i++){
				ReimbursementItem reimbursementItem = new ReimbursementItem();//报销项目
				//itemId = baseDataService.getSequence();
				//reimbursementItem.setItemId(itemId);
				reimbursementItem.setReimId(reimId);
				reimbursementItem.setItemname(itemname[i]);
				reimbursementItem.setTime(time[i]);
				reimbursementItem.setReason(reason[i]);
				reimbursementItem.setReimdate(reimdate[i]);
				reimbursementItem.setReimmoney(reimmoney[i]);
				reimbursementItem.setDocpoll(docpoll[i]);
				reimbursementItem.setOtheritem(otheritem[i]);
				reimbursementItem.setOthermoney(othermoney[i]);
				reimbursementItem.setOtherpoll(otherpoll[i]);
				reimbursementItem.setFlag(flag);
				reimbursementItemService.saveReimbursementItem(reimbursementItem);
			}
			nodes =  flowNodeService.getNodeNamesByFlowname("报销管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doDelete(){
		try {
			reimbursementItemService.removeReimbursementItemsByReimId(reimId_q);
			matterService.removeMatterByRelateId(reimId_q);
			reimbursementService.removeFlowPeopleByRelateId(reimId_q);
			reimbursementService.removeReimbursementById(reimId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doInsertInit(){
		try {
			reimtypeList = baseDataService.getqBaseByDatatype("REIMBURSE");
			//currentdate = new Date();
		//	readonly = false;
		//	ServletActionContext.getRequest().setAttribute("readonly",readonly);
			BaseUser user= (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			ServletActionContext.getRequest().setAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doInsert(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Reimbursement reimbursement = new Reimbursement();
				//reimId = baseDataService.getSequence();
				//reimbursement.setReimId(reimId);
				reimbursement.setWorkflowId(workflowId);
				reimbursement.setPerson(user.getUserId());
				reimbursement.setDept(user.getDeptId());
				reimbursement.setReimtype(reimtype);
				reimbursement.setAffiltype(affiltype);
				reimbursement.setTotalmoney(totalmoney);
				reimbursement.setTotaldoc(totaldoc);
				reimbursement.setTitlle(titlle);
				reimbursement.setRemark(remark);
				reimbursement.setItem(item);
				reimbursement.setStatus("0");//未提交
				reimbursement.setCheckstatus("3");//未审核
				reimbursement.setFlag(flag);
				reimbursementService.saveReimbursement(reimbursement);
				for(int i=0;i<itemname.length;i++){
					ReimbursementItem reimbursementItem = new ReimbursementItem();//报销项目
					//itemId = baseDataService.getSequence();
					//reimbursementItem.setItemId(itemId);
					reimbursementItem.setReimId(reimbursement.getReimId());
					reimbursementItem.setItemname(itemname[i]);
					reimbursementItem.setTime(time[i]);
					reimbursementItem.setReason(reason[i]);
					reimbursementItem.setReimdate(reimdate[i]);
					reimbursementItem.setReimmoney(reimmoney[i]);
					reimbursementItem.setDocpoll(docpoll[i]);
					reimbursementItem.setOtheritem(otheritem[i]);
					reimbursementItem.setOthermoney(othermoney[i]);
					reimbursementItem.setOtherpoll(otherpoll[i]);
					reimbursementItem.setFlag(flag);
					reimbursementItemService.saveReimbursementItem(reimbursementItem);
				}
	//			String flowStr = "";
				reimId = reimbursement.getReimId();
				nodes =  flowNodeService.getNodeNamesByFlowname("报销管理");
	//			for(int i=0;i<nodes.size();i++){
	//				if("".equals(flowStr)){
	//					flowStr =flowStr +((HashMap)nodes.get(i).get("NODENAME")).toString();
	//				}else{
	//					flowStr = flowStr +"->"+((HashMap)nodes.get(i).get("NODENAME")).toString();
	//				}
	//			}
	//			
	//			ServletActionContext.getRequest().setAttribute("flowStr",flowStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doInsertPerson(){
		try {
			for(int i=0;i<personIds.length;i++){
				FlowPeople flowPeople = new FlowPeople();
			//	fpId = baseDataService.getSequence();
			//	flowPeople.setFpId(fpId);
				flowPeople.setPersonId(personIds[i]);
				flowPeople.setRelateId(reimId);
				flowPeople.setSortnum(i+1+"");
				flowPeople.setHandlestatus("0");
				flowPeople.setReadstatus("0");
				reimbursementService.saveFlowPeople(flowPeople);
			}
			Reimbursement reimbursement =reimbursementService.getReimbursementById(reimId);
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			applytime = sf.format(d);
			reimbursement.setApplytime(applytime);
			reimbursement.setCheckstatus("3");//3未审核，2审核中，1已审核
			reimbursement.setStatus("1");//1已提交，0未提交
			reimbursementService.modifyReimbursement(reimbursement);
			
			Matter matter = new Matter();
			//matterId = baseDataService.getSequence();
			//matter.setMatterId(matterId);
			matter.setExecutor(personIds[0]);
			matter.setHandlestatus("0");//0未处理,1已处理
			matter.setIssuetime(new Date());
			matter.setMattername("报销申请");
			matter.setReadstatus("0");//0未读1已读
			matter.setRelateId(reimId);
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				matter.setSponsor(user.getUserId());
			}
			matter.setTitle(titlle);
			matter.setSortnum("1");
			matterService.saveMatter(matter);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Reimbursement reimbursement =reimbursementService.getReimbursementById(reimId_q);
			reimId = reimbursement.getReimId();
			workflowId = reimbursement.getWorkflowId();
			person = baseUserService.getBaseUserById(reimbursement.getPerson()).getName();
			reimtype = baseDataService.getDataNameByCode(reimbursement.getReimtype());
	//		affiltype = reimbursement.getAffiltype();
			totalmoney = reimbursement.getTotalmoney();
			totaldoc = reimbursement.getTotaldoc();
			titlle = reimbursement.getTitlle();
			remark = reimbursement.getRemark();
			item = reimbursement.getItem();
			flag = reimbursement.getFlag();
			checkstatus = reimbursement.getCheckstatus();
			opinion = reimbursement.getOpinion();
	//		status = reimbursement.getStatus();
			readonly = true;
			reimbursementItemList = reimbursementItemService.getReimbursementItemsByReimId(reimId_q);
			
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
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


	public void setReimbursementList(List reimbursementList){
		this.reimbursementList=reimbursementList;
	}
	public List getReimbursementList(){
		return reimbursementList;
	}


	public void setReimId_q(String reimId_q){
		this.reimId_q=reimId_q;
	}
	public String getReimId_q(){
		return reimId_q;
	}


	public void setReimId(String reimId){
		this.reimId=reimId;
	}
	public String getReimId(){
		return reimId;
	}


	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}


	public void setPerson(String person){
		this.person=person;
	}
	public String getPerson(){
		return person;
	}


	public void setReimtype(String reimtype){
		this.reimtype=reimtype;
	}
	public String getReimtype(){
		return reimtype;
	}


	public void setAffiltype(String affiltype){
		this.affiltype=affiltype;
	}
	public String getAffiltype(){
		return affiltype;
	}


	public void setTotalmoney(String totalmoney){
		this.totalmoney=totalmoney;
	}
	public String getTotalmoney(){
		return totalmoney;
	}


	public void setTotaldoc(String totaldoc){
		this.totaldoc=totaldoc;
	}
	public String getTotaldoc(){
		return totaldoc;
	}


	public void setTitlle(String titlle){
		this.titlle=titlle;
	}
	public String getTitlle(){
		return titlle;
	}


	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}


	public void setItem(String item){
		this.item=item;
	}
	public String getItem(){
		return item;
	}


	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public void setReimbursementService(ReimbursementService reimbursementService){
		this.reimbursementService=reimbursementService;
	}
	public ReimbursementService getReimbursementService(){
		return reimbursementService;
	}


	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List getReimbursementItemList() {
		return reimbursementItemList;
	}

	public void setReimbursementItemList(List reimbursementItemList) {
		this.reimbursementItemList = reimbursementItemList;
	}

	public void setReimdate(Date[] reimdate) {
		this.reimdate = reimdate;
	}

	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}

	public void setTime(Date[] time) {
		this.time = time;
	}

	public void setReason(String[] reason) {
		this.reason = reason;
	}

	public void setDocpoll(String[] docpoll) {
		this.docpoll = docpoll;
	}

	public void setReimmoney(String[] reimmoney) {
		this.reimmoney = reimmoney;
	}

	public void setOtheritem(String[] otheritem) {
		this.otheritem = otheritem;
	}

	public void setOtherpoll(String[] otherpoll) {
		this.otherpoll = otherpoll;
	}

	public void setOthermoney(String[] othermoney) {
		this.othermoney = othermoney;
	}

	public void setCurrentdate(Date currentdate) {
		this.currentdate = currentdate;
	}

	public ReimbursementItemService getReimbursementItemService() {
		return reimbursementItemService;
	}

	public void setReimbursementItemService(
			ReimbursementItemService reimbursementItemService) {
		this.reimbursementItemService = reimbursementItemService;
	}

	public Date getCurrentdate() {
		return currentdate;
	}

	public Date[] getReimdate() {
		return reimdate;
	}

	public String[] getItemname() {
		return itemname;
	}

	public Date[] getTime() {
		return time;
	}

	public String[] getReason() {
		return reason;
	}

	public String[] getDocpoll() {
		return docpoll;
	}

	public String[] getReimmoney() {
		return reimmoney;
	}

	public String[] getOtheritem() {
		return otheritem;
	}

	public String[] getOtherpoll() {
		return otherpoll;
	}

	public String[] getOthermoney() {
		return othermoney;
	}

	public List<Map> getNodes() {
		return nodes;
	}

	public void setNodes(List<Map> nodes) {
		this.nodes = nodes;
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

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}




}

