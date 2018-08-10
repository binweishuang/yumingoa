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
import runix.persistent.model.Recruitment;
import runix.persistent.model.UsingEquip;
import runix.service.BaseDataService;
import runix.service.EquipmentService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.ReimbursementService;
import runix.service.UsingEquipService;

public class UsingEquipAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List usingEquipList;
	private String usingequipId_q;
	private String usingequipId;
	private String workflowId;
	private String usecondition;
	private String storage;
	private String cateogory;
	private String equipmentId;
	private String storagenum;
	private String applynum;
	private Date applytime;
	private String remark;
	private String applicant;
	private String department;
	private String status;
	private String opinion;

	private String flag;
	private String title;//申请概述
	private String checkstatus;//审核状态
	private String starttime;//开始时间
	private String endtime;//结束时间
	private List storageList;//仓库列表
	private List categoryList;//物品类别列表
	private List equipmentList;//物品列表 
	private List<Map> nodes;
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
	private String title_m;
	private Date issuetime;
	private String handlestatus;
	private String readstatus;
	
	private UsingEquipService usingEquipService;
	private BaseDataService baseDataService;
	private EquipmentService equipmentService;
	private FlowNodeService flowNodeService;
	private ReimbursementService reimbursementService;
	private MatterService matterService;
	
	public String doQueryUsingE(){
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
			if (!"".equals(status) && null != status) {
				condition.put("status", status);
			}
			if (!"".equals(checkstatus) && null != checkstatus) {
				condition.put("checkstatus", checkstatus);
			}
			if (!"".equals(usecondition) && null != usecondition) {
				condition.put("usecondition", usecondition);
			}
			if (!"".equals(cateogory) && null != cateogory) {
				condition.put("cateogory", cateogory);
			}
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("gm4") < 0){
					condition.put("applicant", user.getUserId());
				}
			}
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
			int record = usingEquipService.getCountUsingEquips(condition);
			List lst =usingEquipService.getUsingEquips(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			usingEquipList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
			UsingEquip usingEquip =usingEquipService.getUsingEquipById(usingequipId_q);
			usingequipId = usingEquip.getUsingequipId();
			workflowId = usingEquip.getWorkflowId();
			usecondition = usingEquip.getUsecondition();
			storage = usingEquip.getStorage();
			cateogory = usingEquip.getCateogory();
			equipmentId = usingEquip.getEquipmentId();
			storagenum = usingEquip.getStoragenum();
			applynum = usingEquip.getApplynum();
			applytime = usingEquip.getApplytime();
			remark = usingEquip.getRemark();
			applicant = usingEquip.getApplicant();
			department = usingEquip.getDepartment();
			status = usingEquip.getStatus();
			checkstatus=usingEquip.getCheckstatus();
			opinion = usingEquip.getOpinion();
			starttime = usingEquip.getStarttime();
			endtime = usingEquip.getEndtime();
			title=usingEquip.getTitle();
			flag = usingEquip.getFlag();
			readonly = true;
			equipmentList = equipmentService.getEquipmentsByCategory(cateogory);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			UsingEquip usingEquip =usingEquipService.getUsingEquipById(usingequipId_q);
			usingEquip.setUsingequipId(usingequipId);
			usingEquip.setUsecondition(usecondition);
			usingEquip.setStorage(storage);
			usingEquip.setCateogory(cateogory);
			usingEquip.setEquipmentId(equipmentId);
			usingEquip.setStoragenum(storagenum);
			usingEquip.setApplynum(applynum);
			usingEquip.setApplytime(new Date());
			usingEquip.setRemark(remark);
			usingEquip.setStarttime(starttime);
			usingEquip.setEndtime(endtime);
			usingEquip.setFlag(flag);
			usingEquip.setTitle(title);
			usingEquipService.modifyUsingEquip(usingEquip);
			nodes =  flowNodeService.getNodeNamesByFlowname("设备管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(usingequipId_q);
			reimbursementService.removeFlowPeopleByRelateId(usingequipId_q);
			usingEquipService.removeUsingEquipById(usingequipId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
			//equipmentList = equipmentService.getEquipmentsByCategory(null);
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
				UsingEquip usingEquip = new UsingEquip();
				//usingequipId = baseDataService.getSequence();
				//usingEquip.setUsingequipId(usingequipId);
				usingEquip.setWorkflowId(workflowId);
				usingEquip.setUsecondition(usecondition);
				usingEquip.setStorage(storage);
				usingEquip.setCateogory(cateogory);
				usingEquip.setEquipmentId(equipmentId);
				usingEquip.setStoragenum(storagenum);
				usingEquip.setApplynum(applynum);
				usingEquip.setApplytime(new Date());
				usingEquip.setRemark(remark);
				usingEquip.setApplicant(user.getUserId());
				usingEquip.setDepartment(user.getDeptId());
				usingEquip.setStatus("0");
				usingEquip.setOpinion(opinion);
				usingEquip.setCheckstatus("3");
				usingEquip.setStarttime(starttime);
				usingEquip.setEndtime(endtime);
				usingEquip.setTitle(title);
				usingEquip.setFlag(flag);
				usingEquipService.saveUsingEquip(usingEquip);
				usingequipId =usingEquip.getUsingequipId();
				nodes =  flowNodeService.getNodeNamesByFlowname("设备管理");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	public String doInsertPerson(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				for(int i=0;i<personIds.length;i++){
					FlowPeople flowPeople = new FlowPeople();
				//	fpId = baseDataService.getSequence();
				//	flowPeople.setFpId(fpId);
					flowPeople.setPersonId(personIds[i]);
					flowPeople.setRelateId(usingequipId);
					flowPeople.setSortnum(i+1+"");
					flowPeople.setHandlestatus("0");
					flowPeople.setReadstatus("0");
					reimbursementService.saveFlowPeople(flowPeople);
				}
				UsingEquip usingEquip =usingEquipService.getUsingEquipById(usingequipId);
				usingEquip.setCheckstatus("3");//3未审核，2审核中，1已审核
				usingEquip.setStatus("1");//1已提交，0未提交
				usingEquip.setApplytime(new Date());
				usingEquipService.modifyUsingEquip(usingEquip);
				
				Matter matter = new Matter();
			//	matterId = baseDataService.getSequence();
			//	matter.setMatterId(matterId);
				matter.setExecutor(personIds[0]);
				matter.setHandlestatus("0");//0未处理,1已处理
				matter.setIssuetime(new Date());
				matter.setMattername("领用申请");
				matter.setReadstatus("0");//0未读1已读
				matter.setRelateId(usingequipId);
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
			UsingEquip usingEquip =usingEquipService.getUsingEquipByIdForView(usingequipId_q);
			usingequipId = usingEquip.getUsingequipId();
			workflowId = usingEquip.getWorkflowId();
			usecondition = usingEquip.getUsecondition();
			storage = usingEquip.getStorage();
			cateogory = usingEquip.getCateogory();
			equipmentId = usingEquip.getEquipmentId();
			storagenum = usingEquip.getStoragenum();
			applynum = usingEquip.getApplynum();
			applytime = usingEquip.getApplytime();
			remark = usingEquip.getRemark();
			applicant = usingEquip.getApplicant();
			department = usingEquip.getDepartment();
			status = usingEquip.getStatus();
			checkstatus=usingEquip.getCheckstatus();
			opinion = usingEquip.getOpinion();
			starttime = usingEquip.getStarttime();
			endtime = usingEquip.getEndtime();
			title=usingEquip.getTitle();
			flag = usingEquip.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
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

	public void setUsingEquipList(List usingEquipList){
		this.usingEquipList=usingEquipList;
	}
	public List getUsingEquipList(){
		return usingEquipList;
	}

	public void setUsingequipId_q(String usingequipId_q){
		this.usingequipId_q=usingequipId_q;
	}
	public String getUsingequipId_q(){
		return usingequipId_q;
	}

	public void setUsingequipId(String usingequipId){
		this.usingequipId=usingequipId;
	}
	public String getUsingequipId(){
		return usingequipId;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setUsecondition(String usecondition){
		this.usecondition=usecondition;
	}
	public String getUsecondition(){
		return usecondition;
	}

	public void setStorage(String storage){
		this.storage=storage;
	}
	public String getStorage(){
		return storage;
	}

	public void setCateogory(String cateogory){
		this.cateogory=cateogory;
	}
	public String getCateogory(){
		return cateogory;
	}

	public void setEquipmentId(String equipmentId){
		this.equipmentId=equipmentId;
	}
	public String getEquipmentId(){
		return equipmentId;
	}

	public void setStoragenum(String storagenum){
		this.storagenum=storagenum;
	}
	public String getStoragenum(){
		return storagenum;
	}

	public void setApplynum(String applynum){
		this.applynum=applynum;
	}
	public String getApplynum(){
		return applynum;
	}

	public void setApplytime(Date applytime){
		this.applytime=applytime;
	}
	public Date getApplytime(){
		return applytime;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setApplicant(String applicant){
		this.applicant=applicant;
	}
	public String getApplicant(){
		return applicant;
	}

	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setOpinion(String opinion){
		this.opinion=opinion;
	}
	public String getOpinion(){
		return opinion;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setUsingEquipService(UsingEquipService usingEquipService){
		this.usingEquipService=usingEquipService;
	}
	public UsingEquipService getUsingEquipService(){
		return usingEquipService;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public List getStorageList() {
		return storageList;
	}

	public void setStorageList(List storageList) {
		this.storageList = storageList;
	}

	public List getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	public List getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List equipmentList) {
		this.equipmentList = equipmentList;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
