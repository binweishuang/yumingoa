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
import runix.service.BaseDataService;
import runix.service.DeptService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.PositionService;
import runix.service.RecruitmentService;
import runix.service.ReimbursementService;
import runix.service.ResumeService;
/**
 * 招聘管理
 * @author runix
 *
 */
public class RecruitmentAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List recruitmentList;
	private String recruitmentId_q;
	private String recruitmentId;
	private String workflowId;
	private String department;
	private String position;
	private String submitter;
	private String limittime;
	private String title;
	private String peoplenum;
	private String details;
	private String flag;
	private String status;
	private String checkstatus;
	private String opinion;
	private String userRole;
	private List deptList;
	private List positionList;
	private List<Map> nodes;
	private String name;
	

	private DeptService deptService;
	private PositionService positionService;
	private BaseDataService baseDataService;
	private RecruitmentService recruitmentService;
	private FlowNodeService flowNodeService;
	private ReimbursementService reimbursementService;
	private MatterService matterService;
	private ResumeService resumeService;

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
	
	public String doQueryRecruit(){
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
			if (!"".equals(department) && null != department) {
				condition.put("department", department);
			}
			if (!"".equals(position) && null != position) {
				condition.put("position", position);
			}
			if (!"".equals(status) && null != status) {
				condition.put("status", status);
			}
			if (!"".equals(checkstatus) && null != checkstatus) {
				condition.put("checkstatus", checkstatus);
			}
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				name = user.getName();
				userRole = user.getTitle();
				if(userRole.indexOf("cm2") < 0){
					condition.put("submitter", name);
				}
			}
			
			deptList = deptService.getAllDepts();
			positionList = positionService.getALlPositions();
			int record = recruitmentService.getCountRecruitments(condition);
			List lst =recruitmentService.getRecruitments(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			recruitmentList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			deptList = deptService.getAllDepts();
			positionList = positionService.getALlPositions();
			Recruitment recruitment =recruitmentService.getRecruitmentById(recruitmentId_q);
			recruitmentId = recruitment.getRecruitmentId();
			workflowId = recruitment.getWorkflowId();
			department = recruitment.getDepartment();
			position = recruitment.getPosition();
			submitter = recruitment.getSubmitter();
			limittime = recruitment.getLimittime();
			title = recruitment.getTitle();
			peoplenum = recruitment.getPeoplenum();
			details = recruitment.getDetails();
			flag = recruitment.getFlag();
			status = recruitment.getStatus();
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
			Recruitment recruitment =recruitmentService.getRecruitmentById(recruitmentId);
			recruitment.setWorkflowId(workflowId);
			recruitment.setDepartment(department);
			recruitment.setPosition(position);
			recruitment.setSubmitter(submitter);
			recruitment.setLimittime(limittime);
			recruitment.setTitle(title);
			recruitment.setPeoplenum(peoplenum);
			recruitment.setDetails(details);
			recruitment.setFlag(flag);
			recruitmentService.modifyRecruitment(recruitment);
			nodes =  flowNodeService.getNodeNamesByFlowname("招聘管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(recruitmentId_q);
			reimbursementService.removeFlowPeopleByRelateId(recruitmentId_q);
			recruitmentService.removeRecruitmentById(recruitmentId_q);
			resumeService.removeResumeByRelateId(recruitmentId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			deptList = deptService.getAllDepts();
			positionList = positionService.getALlPositions();
			BaseUser user= (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			ServletActionContext.getRequest().setAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			Recruitment recruitment = new Recruitment();
		//	recruitmentId = baseDataService.getSequence();
		//	recruitment.setRecruitmentId(recruitmentId);
			recruitment.setWorkflowId(workflowId);
			recruitment.setDepartment(department);
			recruitment.setPosition(position);
			recruitment.setSubmitter(submitter);
			recruitment.setLimittime(limittime);
			recruitment.setTitle(title);
			recruitment.setPeoplenum(peoplenum);
			recruitment.setDetails(details);
			recruitment.setFlag(flag);
			recruitment.setStatus("0");
			recruitment.setCheckstatus("3");
			recruitmentService.saveRecruitment(recruitment);
			recruitmentId =recruitment.getRecruitmentId();
			nodes =  flowNodeService.getNodeNamesByFlowname("招聘管理");
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
				//fpId = baseDataService.getSequence();
				//flowPeople.setFpId(fpId);
				flowPeople.setPersonId(personIds[i]);
				flowPeople.setRelateId(recruitmentId);
				flowPeople.setSortnum(i+1+"");
				flowPeople.setHandlestatus("0");
				flowPeople.setReadstatus("0");
				reimbursementService.saveFlowPeople(flowPeople);
			}
			Recruitment recruitment =recruitmentService.getRecruitmentById(recruitmentId);
			recruitment.setCheckstatus("3");//3未审核，2审核中，1已审核
			recruitment.setStatus("1");//1已提交，0未提交
			recruitmentService.modifyRecruitment(recruitment);
			
			Matter matter = new Matter();
			//matterId = baseDataService.getSequence();
			//matter.setMatterId(matterId);
			matter.setExecutor(personIds[0]);
			matter.setHandlestatus("0");//0未处理,1已处理
			matter.setIssuetime(new Date());
			matter.setMattername("招聘申请");
			matter.setReadstatus("0");//0未读1已读
			matter.setRelateId(recruitmentId);
			matter.setSponsor(sponsor);/////////////////////////////////////登录用户自己，此处需修改
			matter.setTitle(title);
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
			Recruitment recruitment =recruitmentService.getRecruitmentById(recruitmentId_q);
			workflowId = recruitment.getWorkflowId();
			department = recruitment.getDepartment();
			position = recruitment.getPosition();
			submitter = recruitment.getSubmitter();
			limittime = recruitment.getLimittime();
			title = recruitment.getTitle();
			peoplenum = recruitment.getPeoplenum();
			details = recruitment.getDetails();
			
			checkstatus = recruitment.getCheckstatus();
			opinion = recruitment.getOpinion();
			
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

	public void setRecruitmentList(List recruitmentList){
		this.recruitmentList=recruitmentList;
	}
	public List getRecruitmentList(){
		return recruitmentList;
	}

	public void setRecruitmentId_q(String recruitmentId_q){
		this.recruitmentId_q=recruitmentId_q;
	}
	public String getRecruitmentId_q(){
		return recruitmentId_q;
	}

	public void setRecruitmentId(String recruitmentId){
		this.recruitmentId=recruitmentId;
	}
	public String getRecruitmentId(){
		return recruitmentId;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}

	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}

	public void setSubmitter(String submitter){
		this.submitter=submitter;
	}
	public String getSubmitter(){
		return submitter;
	}

	public void setLimittime(String limittime){
		this.limittime=limittime;
	}
	public String getLimittime(){
		return limittime;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setPeoplenum(String peoplenum){
		this.peoplenum=peoplenum;
	}
	public String getPeoplenum(){
		return peoplenum;
	}

	public void setDetails(String details){
		this.details=details;
	}
	public String getDetails(){
		return details;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public List getPositionList() {
		return positionList;
	}
	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}
	public List getDeptList() {
		return deptList;
	}
	public void setDeptList(List deptList) {
		this.deptList = deptList;
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
	public void setRecruitmentService(RecruitmentService recruitmentService){
		this.recruitmentService=recruitmentService;
	}
	public RecruitmentService getRecruitmentService(){
		return recruitmentService;
	}
	public PositionService getPositionService() {
		return positionService;
	}
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
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
	public ResumeService getResumeService() {
		return resumeService;
	}
	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
