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

import runix.persistent.model.Apply;
import runix.persistent.model.BaseUser;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.Matter;
import runix.persistent.model.OfficialDocument;
import runix.persistent.model.Personnel;
import runix.persistent.model.Recruitment;
import runix.persistent.model.Reimbursement;
import runix.persistent.model.UsingEquip;
import runix.persistent.model.Usingcar;
import runix.persistent.model.WorkingCondition;
import runix.service.ApplyService;
import runix.service.AttendanceService;
import runix.service.BaseDataService;
import runix.service.MatterService;
import runix.service.OfficialDocumentService;
import runix.service.PersonnelService;
import runix.service.RecruitmentService;
import runix.service.ReimbursementItemService;
import runix.service.ReimbursementService;
import runix.service.UsingEquipService;
import runix.service.UsingcarService;
import runix.service.WorkingConditionService;

public class MatterAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List matterList;
	private String matterId_q;
	private String matterId;
	private String mattername;
	private String sponsor;
	private String executor;
	private String title;
	private Date issuetime;
	private String handlestatus;
	private String readstatus;
	private String flag;
	private String relateId;
	private String sortnum;
	private Date starttime;
	private Date endtime;
	
	private String userRole;
	
	private String opinion;
	
	//公文（发文、收文）
	private List officialDocumentList;
	
	//请示
	private List applyList;//请示列表
	
	//工作状态（外出、休假、出差）
	private List workingConditionList;
	
	//人事（调动、转正、离职）
	private List personnelList;
	
	//招聘
	private List recruitmentList;
	
	//报销
	private List reimbursementItemList;
	private List reimbursementList;

	//设备
	private List usingEquipList;
	
	//用车
	private List usingcarList;
	
	private MatterService matterService;
	private BaseDataService baseDataService;
	private AttendanceService attendanceService;
	private UsingEquipService usingEquipService;
	private ApplyService applyService;
	private UsingcarService usingcarService;
	private OfficialDocumentService officialDocumentService;
	private PersonnelService personnelService;
	private RecruitmentService recruitmentService;
	private ReimbursementService reimbursementService;
	private ReimbursementItemService reimbursementItemService;
	private WorkingConditionService workingConditionService;


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
			if(!"".equals(mattername)&&null!=mattername){
				condition.put("mattername", mattername);
			}
			if(!"".equals(sponsor)&&null!=sponsor){
				condition.put("sponsor", "%"+sponsor+"%");
			}

			if(!"".equals(starttime)&&null!=starttime){
				condition.put("starttime", starttime);
			}
			if(!"".equals(endtime)&&null!=endtime){
				condition.put("endtime", endtime);
			}

			int record = 0;
			List lst = null;
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				if(!"".equals(handlestatus)&&null!=handlestatus){
					condition.put("handlestatus", handlestatus);
				}else{
					condition.put("handlestatus", "0");
				}
				userRole = user.getTitle();
				if(userRole.indexOf("am2") < 0){
					if(!"1".equals(handlestatus)){//待办事项查询
						condition.put("executor", user.getUserId());
						condition.put("personId", user.getUserId());
						record = matterService.getCountMatters(condition);
						lst =matterService.getMatters(condition, offset, limit);
					}else{//已办事项查询
						condition.put("personId", user.getUserId());
						record = matterService.getCountMatters2(condition);
						lst =matterService.getMatters2(condition, offset, limit);
					}
				}else{
						record = matterService.getCountMatters3(condition);
						lst =matterService.getMatters3(condition, offset, limit);
				}
				
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			matterList = pg.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			Matter matter =matterService.getMatterById(matterId_q);
			matterId = matter.getMatterId();
			mattername = matter.getMattername();
			sponsor = matter.getSponsor();
			executor = matter.getExecutor();
			title = matter.getTitle();
			issuetime = matter.getIssuetime();
			handlestatus = matter.getHandlestatus();
			readstatus = matter.getReadstatus();
			flag = matter.getFlag();
			relateId = matter.getRelateId();
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
			Matter matter =matterService.getMatterById(matterId_q);
			matter.setMatterId(matterId);
			matter.setMattername(mattername);
			matter.setSponsor(sponsor);
			matter.setExecutor(executor);
			matter.setTitle(title);
			matter.setIssuetime(issuetime);
			matter.setHandlestatus(handlestatus);
			matter.setReadstatus(readstatus);
			matter.setFlag(flag);
			matter.setRelateId(relateId);
			matterService.modifyMatter(matter);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterById(matterId_q);
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
			Matter matter = new Matter();
			matter.setMatterId(matterId);
			matter.setMattername(mattername);
			matter.setSponsor(sponsor);
			matter.setExecutor(executor);
			matter.setTitle(title);
			matter.setIssuetime(issuetime);
			matter.setHandlestatus(handlestatus);
			matter.setReadstatus(readstatus);
			matter.setFlag(flag);
			matter.setRelateId(relateId);
			matterService.saveMatter(matter);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doView(){
		try {
			Matter matter =matterService.getMatterById(matterId_q);
			matterId = matter.getMatterId();
			mattername = matter.getMattername();
			sponsor = matter.getSponsor();
			executor = matter.getExecutor();
			title = matter.getTitle();
			issuetime = matter.getIssuetime();
			//handlestatus = matter.getHandlestatus();
			//readstatus = matter.getReadstatus();
			sortnum = matter.getSortnum();
			flag = matter.getFlag();
			relateId = matter.getRelateId();
			if("出差申请".equals(mattername)||"外出申请".equals(mattername)||"休假申请".equals(mattername)){
				 workingConditionList = workingConditionService.getWorkingConditionByIdForCheck(relateId);
			}else if("领用申请".equals(mattername)){
				 usingEquipList =usingEquipService.getUsingEquipByIdForCheck(relateId);
			}else if("请示申请".equals(mattername)){
				applyList = applyService.getApplyAndNamesById(relateId);
			}else if("用车申请".equals(mattername)){
				usingcarList = usingcarService.getUsingcarByIdForView(relateId);
			}else if("发文申请".equals(mattername)||"收文申请".equals(mattername)){
				 officialDocumentList = officialDocumentService.getOfficialDocumentByIdForCheck(relateId);
			}else if("调动申请".equals(mattername)||"离职申请".equals(mattername)||"转正申请".equals(mattername)){
				personnelList = personnelService.getPersonnelByIdForCheck(relateId);
			}else if("招聘申请".equals(mattername)){
				recruitmentList =recruitmentService.getRecruitmentByIdForCheck(relateId);
			}else if("报销申请".equals(mattername)){
				reimbursementList  =reimbursementService.getReimbursementByIdForCheck(relateId);
				reimbursementItemList = reimbursementItemService.getReimbursementItemsByReimId(relateId);
			}
			Map condition = new HashMap();
			condition.put("relateId", matter.getRelateId());
			condition.put("sortnum", sortnum);
			FlowPeople flowPeople = baseDataService.getFlowPeople(condition);
			if(!"1".equals(handlestatus)){//如果是待办事项就修改审核人员表的阅读状态
				flowPeople.setReadstatus("1");
				baseDataService.modifyFlowPeople(flowPeople);
			}else{//如果是已办事项就查询出审核意见


					condition.clear();
					condition.put("relateId", matter.getRelateId());
					BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
					if(user!=null){
						condition.put("personId",user.getUserId());
					}
				List opinions = baseDataService.getFlowPeopleOpinions(condition);
				String opinion1 = "";
				if(null!=opinions){
					for(int i =0;i<opinions.size();i++){
						opinion1 += (String)(opinions.get(i));
					}
				}
				opinion = opinion1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("出差申请".equals(mattername)){
			return "chch";
		}else if("休假申请".equals(mattername)){
			return "xj";
		}else if("外出申请".equals(mattername)){
			return "wch";
		}else if("领用申请".equals(mattername)){
			return "ly";
		}else if("请示申请".equals(mattername)){
			return "qsh";
		}else if("用车申请".equals(mattername)){
			return "ych";
		}else if("发文申请".equals(mattername)){
			return "fw";
		}else if("收文申请".equals(mattername)){
			return "shw";
		}else if("调动申请".equals(mattername)){
			return "dd";
		}else if("离职申请".equals(mattername)){
			return "lzh";
		}else if("转正申请".equals(mattername)){
			return "zhzh";
		}else if("招聘申请".equals(mattername)){
			return "zhp";
		}else if("报销申请".equals(mattername)){
			return "bx";
		}
		return SUCCESS;
	}
	
	public String doCheck(){
		try{
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			String name ="";
			if(user!=null){
				name=user.getName();
			}
			Matter matter =matterService.getMatterById(matterId);
			
//			matterId = matter.getMatterId();
			sortnum = matter.getSortnum();
			mattername = matter.getMattername();
			relateId  = matter.getRelateId();
			Map condition1 = new HashMap();
			condition1.put("relateId", matter.getRelateId());
			condition1.put("sortnum", Integer.parseInt(sortnum)+1+"");
			executor = baseDataService.getNextPerson(condition1);
			Map condition2 = new HashMap();
			condition2.put("relateId", matter.getRelateId());
			condition2.put("sortnum", sortnum);
			FlowPeople flowPeople = baseDataService.getFlowPeople(condition2);
			flowPeople.setHandlestatus("1");
			flowPeople.setOpinion(opinion+"---【审核人："+name+"】");
			baseDataService.modifyFlowPeople(flowPeople);
			if(!"".equals(executor)&&null!=executor){
				matter.setExecutor(executor);
				matter.setSortnum(Integer.parseInt(sortnum)+1+"");
			}
			matterService.modifyMatter(matter);
			String beforeOpinion = "";
			if("出差申请".equals(mattername)||"外出申请".equals(mattername)||"休假申请".equals(mattername)){
				WorkingCondition  workingCondition = workingConditionService.getWorkingConditionById(relateId);
				if(!"".equals(executor)&&null!=executor){
					workingCondition.setCheckstatus("2");//审核中
				}else{
					workingCondition.setCheckstatus("1");//已审核
				}
				beforeOpinion = workingCondition.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				workingCondition.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				workingConditionService.modifyWorkingCondition(workingCondition);
			}else if("领用申请".equals(mattername)){
				UsingEquip usingEquip =usingEquipService.getUsingEquipById(relateId);
				if(!"".equals(executor)&&null!=executor){
					usingEquip.setCheckstatus("2");//审核中
				}else{
					usingEquip.setCheckstatus("1");//已审核
				}
				beforeOpinion = usingEquip.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				usingEquip.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				usingEquipService.modifyUsingEquip(usingEquip);
			}else if("请示申请".equals(mattername)){
				Apply apply = applyService.getApplyById(relateId);
				if(!"".equals(executor)&&null!=executor){
					apply.setCheckstatus("2");//审核中
				}else{
					apply.setCheckstatus("1");//已审核
				}
				beforeOpinion = apply.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				apply.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				applyService.modifyApply(apply);
			}else if("用车申请".equals(mattername)){
				Usingcar usingcar = usingcarService.getUsingcarById(relateId);
				if(!"".equals(executor)&&null!=executor){
					usingcar.setCheckstatus("2");//审核中
				}else{
					usingcar.setCheckstatus("1");//已审核
				}
				beforeOpinion = usingcar.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				usingcar.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				usingcarService.modifyUsingcar(usingcar);
			}else if("发文申请".equals(mattername)||"收文申请".equals(mattername)){
				OfficialDocument officialDocument = officialDocumentService.getOfficialDocumentById(relateId);
				if(!"".equals(executor)&&null!=executor){
					officialDocument.setCheckstatus("2");//审核中
				}else{
					officialDocument.setCheckstatus("1");//已审核
				}
				beforeOpinion = officialDocument.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				officialDocument.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				officialDocumentService.modifyOfficialDocument(officialDocument);
			}else if("调动申请".equals(mattername)||"离职申请".equals(mattername)||"转正申请".equals(mattername)){
				Personnel personnel = personnelService.getPersonnelById(relateId);
				if(!"".equals(executor)&&null!=executor){
					personnel.setCheckstatus("2");//审核中
				}else{
					personnel.setCheckstatus("1");//已审核
				}
				beforeOpinion = personnel.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				personnel.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				personnelService.modifyPersonnel(personnel);
			}else if("招聘申请".equals(mattername)){
				Recruitment recruitment =recruitmentService.getRecruitmentById(relateId);
				if(!"".equals(executor)&&null!=executor){
					recruitment.setCheckstatus("2");//审核中
				}else{
					recruitment.setCheckstatus("1");//已审核
				}
				beforeOpinion = recruitment.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				recruitment.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				recruitmentService.modifyRecruitment(recruitment);
			}else if("报销申请".equals(mattername)){
				Reimbursement reimbursement  =reimbursementService.getReimbursementById(relateId);
				if(!"".equals(executor)&&null!=executor){
					reimbursement.setCheckstatus("2");//审核中
				}else{
					reimbursement.setCheckstatus("1");//已审核
				}
				beforeOpinion = reimbursement.getOpinion();
				if(null==beforeOpinion){
					beforeOpinion = "";
				}
				reimbursement.setOpinion(beforeOpinion+opinion+"---【审核人："+name+"】\n");
				reimbursementService.modifyReimbursement(reimbursement);
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

	public void setMatterList(List matterList){
		this.matterList=matterList;
	}
	public List getMatterList(){
		return matterList;
	}

	public void setMatterId_q(String matterId_q){
		this.matterId_q=matterId_q;
	}
	public String getMatterId_q(){
		return matterId_q;
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

	public void setMatterService(MatterService matterService){
		this.matterService=matterService;
	}
	public MatterService getMatterService(){
		return matterService;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public AttendanceService getAttendanceService() {
		return attendanceService;
	}

	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	public UsingEquipService getUsingEquipService() {
		return usingEquipService;
	}

	public void setUsingEquipService(UsingEquipService usingEquipService) {
		this.usingEquipService = usingEquipService;
	}

	public ApplyService getApplyService() {
		return applyService;
	}

	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}

	public UsingcarService getUsingcarService() {
		return usingcarService;
	}

	public void setUsingcarService(UsingcarService usingcarService) {
		this.usingcarService = usingcarService;
	}

	public OfficialDocumentService getOfficialDocumentService() {
		return officialDocumentService;
	}

	public void setOfficialDocumentService(
			OfficialDocumentService officialDocumentService) {
		this.officialDocumentService = officialDocumentService;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}

	public RecruitmentService getRecruitmentService() {
		return recruitmentService;
	}

	public void setRecruitmentService(RecruitmentService recruitmentService) {
		this.recruitmentService = recruitmentService;
	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}

	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public List getApplyList() {
		return applyList;
	}

	public void setApplyList(List applyList) {
		this.applyList = applyList;
	}

	

	public List getRecruitmentList() {
		return recruitmentList;
	}

	public void setRecruitmentList(List recruitmentList) {
		this.recruitmentList = recruitmentList;
	}

	public List getReimbursementItemList() {
		return reimbursementItemList;
	}

	public void setReimbursementItemList(List reimbursementItemList) {
		this.reimbursementItemList = reimbursementItemList;
	}

	

	public List getReimbursementList() {
		return reimbursementList;
	}

	public void setReimbursementList(List reimbursementList) {
		this.reimbursementList = reimbursementList;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public ReimbursementItemService getReimbursementItemService() {
		return reimbursementItemService;
	}

	public void setReimbursementItemService(
			ReimbursementItemService reimbursementItemService) {
		this.reimbursementItemService = reimbursementItemService;
	}



	public List getWorkingConditionList() {
		return workingConditionList;
	}

	public void setWorkingConditionList(List workingConditionList) {
		this.workingConditionList = workingConditionList;
	}

	public List getUsingEquipList() {
		return usingEquipList;
	}

	public void setUsingEquipList(List usingEquipList) {
		this.usingEquipList = usingEquipList;
	}

	public String getSortnum() {
		return sortnum;
	}

	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}

	public WorkingConditionService getWorkingConditionService() {
		return workingConditionService;
	}

	public void setWorkingConditionService(
			WorkingConditionService workingConditionService) {
		this.workingConditionService = workingConditionService;
	}

	public List getUsingcarList() {
		return usingcarList;
	}

	public void setUsingcarList(List usingcarList) {
		this.usingcarList = usingcarList;
	}

	public List getOfficialDocumentList() {
		return officialDocumentList;
	}

	public void setOfficialDocumentList(List officialDocumentList) {
		this.officialDocumentList = officialDocumentList;
	}

	public List getPersonnelList() {
		return personnelList;
	}

	public void setPersonnelList(List personnelList) {
		this.personnelList = personnelList;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	

}
