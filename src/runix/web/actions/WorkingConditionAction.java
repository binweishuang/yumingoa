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
import runix.persistent.model.WorkingCondition;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.DeptService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.ReimbursementService;
import runix.service.WorkingConditionService;

public class WorkingConditionAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List workingConditionList;
	private String conditionId_q;
	private String conditionId;
	private String conditiontype;
	private String workflowId;
	private String name;
	private String dept;
	private Date starttime;
	private Date endtime;
	private String destination;
	private String title;
	private String content;
	private String vacationtype;
	private Date registdate;
	private String status;
	private String commitstatus;
	private String otherpeople;
	private String biztriplimit;
	private String expense;
	private String traffic;
	private String flag;
	private String checkstatus;
	private String opinion;
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
	private String title_m;
	private Date issuetime;
	private String handlestatus2;
	private String readstatus;
	
	
	private WorkingConditionService workingConditionService;
	private BaseDataService baseDataService;//序列service
	private FlowNodeService flowNodeService;//流程节点
	private MatterService matterService;//事项
	private ReimbursementService reimbursementService;
	private BaseUserService baseUserService;
	private DeptService deptService;
	private List depts;
	private List vacationList;
	private List trafficList;
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
			if(!"".equals(status)&&null!=status){
				condition.put("status", status);
			}
			if(!"".equals(checkstatus)&&null!=checkstatus){
				condition.put("checkstatus", checkstatus);
			}
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("cm5") < 0){
					condition.put("name", user.getName());
				}else{
					if(!"".equals(name)&&null!=name){
						condition.put("name", "%"+name+"%");
					}
				}
			}
			
			if("3".equals(flag)){
				depts = deptService.getAllDepts();
				if(!"".equals(dept)&&null!=dept){
					condition.put("dept", dept);
				}
				condition.put("conditiontype", "goout");
			}else if("4".equals(flag)){
				if(!"".equals(vacationtype)&&null!=vacationtype){
					condition.put("vacationtype", vacationtype);
				}
				if(!"".equals(starttime)&&null!=starttime){
					condition.put("starttime", starttime);
				}
				if(!"".equals(endtime)&&null!=endtime){
					condition.put("endtime", endtime);
				}
				vacationList = baseDataService.getqBaseByDatatype("VACATION");//休假类型
				condition.put("conditiontype", "vacation");
			}else if("5".equals(flag)){
				if(!"".equals(starttime)&&null!=starttime){
					condition.put("starttime", starttime);
				}
				if(!"".equals(destination)&&null!=destination){
					condition.put("destination", "%"+destination+"%");
				}
				condition.put("conditiontype", "biztrip");
			}
			int record = workingConditionService.getCountWorkingConditions(condition);
			List lst =workingConditionService.getWorkingConditions(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			workingConditionList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			WorkingCondition workingCondition =workingConditionService.getWorkingConditionById(conditionId_q);
			conditionId = workingCondition.getConditionId();
			conditiontype = workingCondition.getConditiontype();
			workflowId = workingCondition.getWorkflowId();
			name = baseUserService.getBaseUserById(workingCondition.getName()).getName();
			dept = workingCondition.getDept();
			starttime = workingCondition.getStarttime();
			endtime = workingCondition.getEndtime();
			destination = workingCondition.getDestination();
			title = workingCondition.getTitle();
			content = workingCondition.getContent();
			vacationtype = workingCondition.getVacationtype();
			registdate = workingCondition.getRegistdate();
			status = workingCondition.getStatus();
			commitstatus = workingCondition.getCommitstatus();
			otherpeople = workingCondition.getOtherpeople();
			biztriplimit = workingCondition.getBiztriplimit();
			expense = workingCondition.getExpense();
			traffic = workingCondition.getTraffic();
		//	flag = workingCondition.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			if("4".equals(flag)){
				vacationList = baseDataService.getqBaseByDatatype("VACATION");//休假类型
			}else if("5".equals(flag)){
				trafficList = baseDataService.getqBaseByDatatype("TRAFFIC");//交通工具
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			WorkingCondition workingCondition =workingConditionService.getWorkingConditionById(conditionId_q);
			if("3".equals(flag)){
				workingCondition.setStarttime(starttime);
				workingCondition.setEndtime(endtime);
				workingCondition.setDestination(destination);
				workingCondition.setTitle(title);
				workingCondition.setContent(content);
				workingConditionService.modifyWorkingCondition(workingCondition);
				nodes = flowNodeService.getNodeNamesByFlowname("外出管理");
			}else if("4".equals(flag)){
				workingCondition.setVacationtype(vacationtype);
				workingCondition.setStarttime(starttime);
				workingCondition.setEndtime(endtime);
				workingCondition.setTitle(title);
				workingCondition.setContent(content);
				workingConditionService.modifyWorkingCondition(workingCondition);
				nodes = flowNodeService.getNodeNamesByFlowname("休假管理");
			}else if("5".equals(flag)){
				workingCondition.setTraffic(traffic);
				workingCondition.setStarttime(starttime);
				workingCondition.setOtherpeople(otherpeople);
				workingCondition.setDestination(destination);
				workingCondition.setBiztriplimit(biztriplimit);
				workingCondition.setTitle(title);
				workingCondition.setContent(content);
				workingConditionService.modifyWorkingCondition(workingCondition);
				nodes = flowNodeService.getNodeNamesByFlowname("出差管理");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(conditionId_q);
			reimbursementService.removeFlowPeopleByRelateId(conditionId_q);
			workingConditionService.removeWorkingConditionById(conditionId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
					name = user.getName();
					if("4".equals(flag)){
						vacationList = baseDataService.getqBaseByDatatype("VACATION");//休假类型
					}
					if("5".equals(flag)){
						trafficList = baseDataService.getqBaseByDatatype("TRAFFIC");//交通工具
					}
			}
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				if("3".equals(flag)){
					WorkingCondition workingCondition = new WorkingCondition();
					//conditionId =  baseDataService.getSequence();
					//workingCondition.setConditionId(conditionId);
					workingCondition.setConditiontype("goout");
					workingCondition.setWorkflowId(workflowId);
					workingCondition.setName(user.getUserId());
					workingCondition.setDept(user.getDeptId());
					workingCondition.setStarttime(starttime);
					workingCondition.setEndtime(endtime);
					workingCondition.setDestination(destination);
					workingCondition.setTitle(title);
					workingCondition.setContent(content);
//					workingCondition.setVacationtype(vacationtype);
//					workingCondition.setRegistdate(registdate);
					workingCondition.setStatus("0");
					workingCondition.setCheckstatus("3");
//					workingCondition.setCommitstatus(commitstatus);
//					workingCondition.setOtherpeople(otherpeople);
//					workingCondition.setBiztriplimit(biztriplimit);
//					workingCondition.setExpense(expense);
//					workingCondition.setTraffic(traffic);
//					workingCondition.setFlag(flag);
					workingConditionService.saveWorkingCondition(workingCondition);
					conditionId =workingCondition.getConditionId();
					nodes = flowNodeService.getNodeNamesByFlowname("外出管理");
				}else if("4".equals(flag)){
					WorkingCondition workingCondition = new WorkingCondition();
				//	conditionId =  baseDataService.getSequence();
				//	workingCondition.setConditionId(conditionId);
					workingCondition.setConditiontype("vacation");
					workingCondition.setWorkflowId(workflowId);
					workingCondition.setName(user.getUserId());
					workingCondition.setDept(user.getDeptId());
					workingCondition.setStarttime(starttime);
					workingCondition.setEndtime(endtime);
					workingCondition.setTitle(title);
					workingCondition.setContent(content);
					workingCondition.setVacationtype(vacationtype);
					workingCondition.setRegistdate(new Date());
					workingCondition.setStatus("0");
					workingCondition.setCheckstatus("3");
					workingConditionService.saveWorkingCondition(workingCondition);
					conditionId =workingCondition.getConditionId();
					nodes = flowNodeService.getNodeNamesByFlowname("休假管理");
				}else if("5".equals(flag)){
					WorkingCondition workingCondition = new WorkingCondition();
					//conditionId =  baseDataService.getSequence();
					//workingCondition.setConditionId(conditionId);
					workingCondition.setConditiontype("biztrip");
					workingCondition.setWorkflowId(workflowId);
					workingCondition.setName(user.getUserId());
					workingCondition.setDept(user.getDeptId());
					workingCondition.setStarttime(starttime);
//					if(!"".equals(otherpeople)&&null!=otherpeople){
//						otherpeople = new String(otherpeople.getBytes("iso-8859-1"),"utf-8");
//					}
					workingCondition.setOtherpeople(otherpeople);
					workingCondition.setBiztriplimit(biztriplimit);
					workingCondition.setTraffic(traffic);
					workingCondition.setDestination(destination);
					workingCondition.setRegistdate(new Date());
					workingCondition.setTitle(title);
					workingCondition.setContent(content);
					workingCondition.setStatus("0");
					workingCondition.setCheckstatus("3");
					workingConditionService.saveWorkingCondition(workingCondition);
					conditionId =workingCondition.getConditionId();
					nodes = flowNodeService.getNodeNamesByFlowname("出差管理");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
		}
		return SUCCESS;
	}
	
	public String doInsertPerson(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				for(int i=0;i<personIds.length;i++){
					FlowPeople flowPeople = new FlowPeople();
					//fpId = baseDataService.getSequence();
					//flowPeople.setFpId(fpId);
					flowPeople.setPersonId(personIds[i]);
					flowPeople.setRelateId(conditionId);
					flowPeople.setSortnum(i+1+"");
					flowPeople.setHandlestatus("0");
					flowPeople.setReadstatus("0");
					reimbursementService.saveFlowPeople(flowPeople);//审核流程人员
				}
				WorkingCondition workingCondition =workingConditionService.getWorkingConditionById(conditionId);
				workingCondition.setCheckstatus("3");//3：未审核；2：审核中；1：已审核；
				workingCondition.setStatus("1");//1：已提交；0：未提交；
				workingCondition.setRegistdate(new Date());//提交日期
				workingConditionService.modifyWorkingCondition(workingCondition);
				
				Matter matter = new Matter();//代办事项
				//matterId = baseDataService.getSequence();
				//matter.setMatterId(matterId);
				matter.setExecutor(personIds[0]);
				matter.setHandlestatus("0");//0：未处理,1：已处理；
				matter.setIssuetime(new Date());
				if("3".equals(flag)){
					matter.setMattername("外出申请");
				}else if("4".equals(flag)){
					matter.setMattername("休假申请");
				}else if("5".equals(flag)){
					matter.setMattername("出差申请");
				}
				matter.setReadstatus("0");//0：未读；1：已读；
				matter.setRelateId(conditionId);
				matter.setSponsor(user.getUserId());
				matter.setTitle(title);//概述
				matter.setSortnum("1");
				matterService.saveMatter(matter);//录入代办事项信息
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return "success";
	}

	public String doView(){
		try {
			WorkingCondition workingCondition =workingConditionService.getWorkingConditionByIdForView(conditionId_q);
			conditionId = workingCondition.getConditionId();
			conditiontype = workingCondition.getConditiontype();
			workflowId = workingCondition.getWorkflowId();
			name = workingCondition.getName();
			dept = workingCondition.getDept();
			starttime = workingCondition.getStarttime();
			endtime = workingCondition.getEndtime();
			destination = workingCondition.getDestination();
			title = workingCondition.getTitle();
			content = workingCondition.getContent();
			vacationtype = workingCondition.getVacationtype();
			registdate = workingCondition.getRegistdate();
			status = workingCondition.getStatus();
			checkstatus = workingCondition.getCheckstatus();
			opinion = workingCondition.getOpinion();
			commitstatus = workingCondition.getCommitstatus();
			otherpeople = workingCondition.getOtherpeople();
			biztriplimit = workingCondition.getBiztriplimit();
			expense = workingCondition.getExpense();
			traffic = workingCondition.getTraffic();
		//	flag = workingCondition.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("3".equals(flag)){
			return "goout";//外出
		}else if("4".equals(flag)){
			return "vacation";//休假
		}else if("5".equals(flag)){
			return "biztrip";//出差
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

	public void setWorkingConditionList(List workingConditionList){
		this.workingConditionList=workingConditionList;
	}
	public List getWorkingConditionList(){
		return workingConditionList;
	}

	public void setConditionId_q(String conditionId_q){
		this.conditionId_q=conditionId_q;
	}
	public String getConditionId_q(){
		return conditionId_q;
	}

	public void setConditionId(String conditionId){
		this.conditionId=conditionId;
	}
	public String getConditionId(){
		return conditionId;
	}

	public void setConditiontype(String conditiontype){
		this.conditiontype=conditiontype;
	}
	public String getConditiontype(){
		return conditiontype;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
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

	public void setDestination(String destination){
		this.destination=destination;
	}
	public String getDestination(){
		return destination;
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

	public void setVacationtype(String vacationtype){
		this.vacationtype=vacationtype;
	}
	public String getVacationtype(){
		return vacationtype;
	}

	public void setRegistdate(Date registdate){
		this.registdate=registdate;
	}
	public Date getRegistdate(){
		return registdate;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setCommitstatus(String commitstatus){
		this.commitstatus=commitstatus;
	}
	public String getCommitstatus(){
		return commitstatus;
	}

	public void setOtherpeople(String otherpeople){
		this.otherpeople=otherpeople;
	}
	public String getOtherpeople(){
		return otherpeople;
	}

	public void setBiztriplimit(String biztriplimit){
		this.biztriplimit=biztriplimit;
	}
	public String getBiztriplimit(){
		return biztriplimit;
	}

	public void setExpense(String expense){
		this.expense=expense;
	}
	public String getExpense(){
		return expense;
	}

	public void setTraffic(String traffic){
		this.traffic=traffic;
	}
	public String getTraffic(){
		return traffic;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setWorkingConditionService(WorkingConditionService workingConditionService){
		this.workingConditionService=workingConditionService;
	}
	public WorkingConditionService getWorkingConditionService(){
		return workingConditionService;
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

	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}

	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
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

	public String getHandlestatus2() {
		return handlestatus2;
	}

	public void setHandlestatus2(String handlestatus2) {
		this.handlestatus2 = handlestatus2;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public List getDepts() {
		return depts;
	}

	public void setDepts(List depts) {
		this.depts = depts;
	}

	public List getVacationList() {
		return vacationList;
	}

	public void setVacationList(List vacationList) {
		this.vacationList = vacationList;
	}

	public List getTrafficList() {
		return trafficList;
	}

	public void setTrafficList(List trafficList) {
		this.trafficList = trafficList;
	}
	
	

}
