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
import runix.service.ApplyService;
import runix.service.BaseDataService;
import runix.service.FlowNodeService;
import runix.service.MatterService;

public class ApplyAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页数
	private int pages=15;//每页显示记录数
	private List applyList;//请示列表
	private String applicationId_q;
	private String applicationId;
	private String workflowId;//流程
	private String type;//请示类型
	private String secret;//机密等级
	private String urgency;//紧急程度
	private String title;//请示标题
	private String money;//请示金额
	private String attachname;//附件名称
	private String attachpath;//附件地址
	private String content;//请示内容
	private String applicant;//申请人
	private String department;//部门
	private String checkstatus;//审核状态
	private String status;//提交状态
	private String opinion;//意见
	private Date issuedate;//申请日期
	private String flag;
	
	private List nodeList;//请示管理下的节点集合
	private List secretList;//机密等级集合
	private List urgencyList;//紧急程度集合
	private List typeList;//请示类型集合
	private String approleaders[];//审批领导名称
	private String leadersid[];//审批领导ID
	private String personIds[];
	private String personNames[];
	private BaseUser user;//用户
	
	//待办事项
	private String matterId;
	private String sponsor;//发起人
	private String mattertitle;//事项标题
	
	private ApplyService applyService;
	private BaseDataService baseDataService;
	private FlowNodeService flowNodeService;
	private MatterService matterService;
	private String userRole;

	/**
	 * 查询请示
	 */
	public String doQuery(){
		try {
			int currentPageInt = 1;
			if(currentPage != null && !"".equals(currentPage) && currentPage.equals("currentPage")){
				currentPage = null;
			}
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
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("gm3") < 0){
					applicant = user.getUserId();
					condition.put("applicant", applicant);
				}
				if(!"".equals(type) && null != type && !"0".equals(type)){
					condition.put("type", type);
				}
				if(!"".equals(secret) && null != secret && !"0".equals(secret)){
				condition.put("secret", secret);
				}
				if(!"".equals(urgency) && null != urgency && !"0".equals(urgency)){
				condition.put("urgency", urgency);
				}
				if(!"".equals(title) && null != title){
				condition.put("title", "%"+title+"%");
				}
				
				int record = applyService.getCountApplys(condition);
				List lst = applyService.getApplys(condition, offset, limit);
	
				Pageable pg = null;
				try {
					pg = new IbatisPage(lst, record, currentPageInt, pages);
				} catch (PageException e) {
					pg = null;
				}
				ServletActionContext.getRequest().setAttribute("pages", pg);
				applyList = pg.getResult();
				//机密等级集合
				secretList = baseDataService.getqBaseByDatatype("SECRET");
				//紧急程度集合
				urgencyList = baseDataService.getqBaseByDatatype("URGENT");
				//请示类型集合
				typeList = baseDataService.getqBaseByDatatype("APPLY");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新  查看请示
	 */
	public String doUpdateInit(){
		try {
			applyList = applyService.getApplyAndNamesById(applicationId_q);
			Apply apply = applyService.getApplyById(applicationId_q);
			applicationId = apply.getApplicationId();
			workflowId = apply.getWorkflowId();
			type = apply.getType();
			secret = apply.getSecret();
			urgency = apply.getUrgency();
			title = apply.getTitle();
			money = apply.getMoney();
			attachname = apply.getAttachname();
			attachpath = apply.getAttachpath();
			content = apply.getContent();
			applicant = apply.getApplicant();
			department = apply.getDepartment();
			status = apply.getStatus();
			opinion = apply.getOpinion();
			flag = apply.getFlag();
			
			//机密等级集合
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			//紧急程度集合
			urgencyList = baseDataService.getqBaseByDatatype("URGENT");
			//请示类型集合
			typeList = baseDataService.getqBaseByDatatype("APPLY");
			
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改请示
	 */
	public String doUpdate(){
		try {
			Apply apply = applyService.getApplyById(applicationId_q);
			apply.setType(type);
			apply.setSecret(secret);
			apply.setUrgency(urgency);
			apply.setTitle(title);
			apply.setMoney(money);
			apply.setAttachname(attachname);
			apply.setAttachpath(attachpath);
			apply.setContent(content);
			apply.setFlag(flag);
			applyService.modifyApply(apply);
			applicationId = apply.getApplicationId();
			//根据流程名称查询节点集合
			nodeList = flowNodeService.getNodeNamesByFlowname("请示管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 删除请示
	 */
	public String doDelete(){
		try {
			applyService.removeApplyById(applicationId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化添加
	 */
	public String doInsertInit(){
		try {
			//机密等级集合
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			//紧急程度集合
			urgencyList = baseDataService.getqBaseByDatatype("URGENT");
			//请示类型集合
			typeList = baseDataService.getqBaseByDatatype("APPLY");
			//获取当前用户
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 添加请示
	 */
	public String doInsert(){
		try {
			Apply apply = new Apply();
			//applicationId = baseDataService.getSequence();
			//apply.setApplicationId(applicationId);
			apply.setWorkflowId(workflowId);
			apply.setType(type);
			apply.setSecret(secret);
			apply.setUrgency(urgency);
			apply.setTitle(title);
			apply.setMoney(money);
			apply.setAttachname(attachname);
			apply.setAttachpath(attachpath);
			apply.setContent(content);
			apply.setApplicant(applicant);
			apply.setDepartment(department);
			apply.setStatus(status);
			apply.setOpinion(opinion);
			apply.setIssuedate(new Date());
			apply.setFlag(flag);
			applyService.saveApply(apply);
			applicationId = apply.getApplicationId();
			//根据流程名称查询节点集合
			nodeList = flowNodeService.getNodeNamesByFlowname("请示管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	

	
	/**
	 * 下一步添加  添加审批领导和待办事项
	 */
	public String doInsertNext(){
		try{
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				//修改请示状态
				Apply apply = applyService.getApplyById(applicationId);
				apply.setIssuedate(new Date());//修改申请日期为当前提交的时间
				apply.setCheckstatus("3");//3未审核，2审核中，1已审核
				apply.setStatus("1");//将提交状态由保存改为提交 1已提交，0未提交
				applyService.modifyApply(apply);
				FlowPeople flowPeople = new FlowPeople();
				//添加多个审批领导
				for(int i = 0;i<personIds.length;i++){
					//flowPeople.setFpId(baseDataService.getSequence());
					flowPeople.setPersonId(personIds[i]);
					flowPeople.setRelateId(applicationId);//设置关联的请示ID
					flowPeople.setSortnum(i+1+"");//设置排序
					flowPeople.setHandlestatus("0");//未处理
					flowPeople.setReadstatus("0");//未读
					applyService.saveFlowPeople(flowPeople);
				}
				
				//添加待办事项
				Matter matter = new Matter();
			//	matterId = baseDataService.getSequence();
			//	matter.setMatterId(matterId);
				matter.setMattername("请示申请");
				matter.setSponsor(user.getUserId());
				matter.setExecutor(personIds[0]);
				matter.setIssuetime(new Date());
				matter.setTitle(title);
				matter.setHandlestatus("0");//处理状态  0未处理 2已处理
				matter.setReadstatus("0");//阅读状态   0未阅读  1已阅读
				matter.setRelateId(applicationId);//设置关联模块为请示管理
				matter.setSortnum("1");
				matterService.saveMatter(matter);
			}
		}catch(Exception e){
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

	public void setApplyList(List applyList){
		this.applyList=applyList;
	}
	public List getApplyList(){
		return applyList;
	}

	public void setApplicationId_q(String applicationId_q){
		this.applicationId_q=applicationId_q;
	}
	public String getApplicationId_q(){
		return applicationId_q;
	}

	public void setApplicationId(String applicationId){
		this.applicationId=applicationId;
	}
	public String getApplicationId(){
		return applicationId;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
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

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setMoney(String money){
		this.money=money;
	}
	public String getMoney(){
		return money;
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

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
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

	public void setApplyService(ApplyService applyService){
		this.applyService=applyService;
	}
	public ApplyService getApplyService(){
		return applyService;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public List getNodeList() {
		return nodeList;
	}

	public void setNodeList(List nodeList) {
		this.nodeList = nodeList;
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

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public FlowNodeService getFlowNodeService() {
		return flowNodeService;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService) {
		this.flowNodeService = flowNodeService;
	}

	public String[] getApproleaders() {
		return approleaders;
	}

	public void setApproleaders(String[] approleaders) {
		this.approleaders = approleaders;
	}

	public String[] getLeadersid() {
		return leadersid;
	}

	public void setLeadersid(String[] leadersid) {
		this.leadersid = leadersid;
	}

	public String getMatterId() {
		return matterId;
	}

	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getMattertitle() {
		return mattertitle;
	}

	public void setMattertitle(String mattertitle) {
		this.mattertitle = mattertitle;
	}

	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public Date getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
