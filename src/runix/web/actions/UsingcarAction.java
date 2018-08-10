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
import runix.persistent.model.UsingEquip;
import runix.persistent.model.Usingcar;
import runix.service.BaseDataService;
import runix.service.CarService;
import runix.service.DeptService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.ReimbursementService;
import runix.service.UsingcarService;

public class UsingcarAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List usingcarList;
	private String usingcarId_q;
	private String usingcarId;
	private String workflowId;
	private String userId;
	private String name;
	private String driver;
	private String dept;
	private String tel;
	private Date usingtime;
	private Date backtime;
	private String accomnum;
	private String usingproperty;
	private String title;
	private String carId;
	private String usingreason;
	private String usingway;
	private String status;
	private String checkstatus;
	private String opinion;
	private String flag;
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
	
	private List carList;
	private List<Map> nodes;
	private UsingcarService usingcarService;
	private BaseDataService baseDataService;
	private DeptService deptService;
	private CarService carService;
	private FlowNodeService flowNodeService;
	private ReimbursementService reimbursementService;
	private MatterService matterService;

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
			if(!"".equals(carId)&&null!=carId){
				condition.put("carId", "%"+carId+"%");
			}
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("gm2") < 0){
					condition.put("userId", user.getUserId());
				}
			}
			int record = usingcarService.getCountUsingcars(condition);
			List lst =usingcarService.getUsingcars(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			usingcarList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				name=user.getName();
			}
			carList = carService.getAllCars();
			Usingcar usingcar =usingcarService.getUsingcarById(usingcarId_q);
			usingcarId = usingcar.getUsingcarId();
			workflowId = usingcar.getWorkflowId();
			userId = usingcar.getUserId();
			driver = usingcar.getDriver();
			dept= deptService.getDeptnameById(usingcar.getDept());
			tel = usingcar.getTel();
			usingtime = usingcar.getUsingtime();
			backtime = usingcar.getBacktime();
			accomnum = usingcar.getAccomnum();
			usingproperty = usingcar.getUsingproperty();
			title = usingcar.getTitle();
			carId = usingcar.getCarId();
			usingreason = usingcar.getUsingreason();
			usingway = usingcar.getUsingway();
			status = usingcar.getStatus();
			opinion = usingcar.getOpinion();
			flag = usingcar.getFlag();
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
			Usingcar usingcar =usingcarService.getUsingcarById(usingcarId_q);
		//	usingcar.setUsingcarId(usingcarId);
			usingcar.setWorkflowId(workflowId);
		//	usingcar.setUserId(userId);
			usingcar.setDriver(driver);
			//usingcar.setDept(dept);
			usingcar.setTel(tel);
			usingcar.setUsingtime(usingtime);
			usingcar.setBacktime(backtime);
			usingcar.setAccomnum(accomnum);
			usingcar.setUsingproperty(usingproperty);
			usingcar.setTitle(title);
			usingcar.setCarId(carId);
			usingcar.setUsingreason(usingreason);
			usingcar.setUsingway(usingway);
		//	usingcar.setStatus(status);
		//	usingcar.setOpinion(opinion);
		//	usingcar.setFlag(flag);
			usingcarService.modifyUsingcar(usingcar);
			nodes =  flowNodeService.getNodeNamesByFlowname("用车管理");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(usingcarId_q);
			reimbursementService.removeFlowPeopleByRelateId(usingcarId_q);
			usingcarService.removeUsingcarById(usingcarId_q);
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
				name=user.getName();
				dept= deptService.getDeptnameById(user.getDeptId());
			}
			carList = carService.getAllCars();
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
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Usingcar usingcar = new Usingcar();
				//usingcarId = baseDataService.getSequence();
				//usingcar.setUsingcarId(usingcarId);
				usingcar.setWorkflowId(workflowId);
				usingcar.setUserId(user.getUserId());
				usingcar.setDriver(driver);
				usingcar.setDept(user.getDeptId());
				usingcar.setTel(tel);
				usingcar.setUsingtime(usingtime);
				usingcar.setBacktime(backtime);
				usingcar.setAccomnum(accomnum);
				usingcar.setUsingproperty(usingproperty);
				usingcar.setTitle(title);
				usingcar.setCarId(carId);
				usingcar.setUsingreason(usingreason);
				usingcar.setUsingway(usingway);
				usingcar.setStatus("0");
				usingcar.setCheckstatus("3");
				usingcar.setOpinion(opinion);
				usingcar.setFlag(flag);
				usingcarService.saveUsingcar(usingcar);
				usingcarId =usingcar.getUsingcarId();
				nodes =  flowNodeService.getNodeNamesByFlowname("用车管理");
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
					flowPeople.setRelateId(usingcarId);
					flowPeople.setSortnum(i+1+"");
					flowPeople.setHandlestatus("0");
					flowPeople.setReadstatus("0");
					reimbursementService.saveFlowPeople(flowPeople);
				}
				Usingcar usingcar =usingcarService.getUsingcarById(usingcarId);
				usingcar.setCheckstatus("3");//3未审核，2审核中，1已审核
				usingcar.setStatus("1");//1已提交，0未提交
				usingcarService.modifyUsingcar(usingcar);
				
				Matter matter = new Matter();
			//	matterId = baseDataService.getSequence();
			//	matter.setMatterId(matterId);
				matter.setExecutor(personIds[0]);
				matter.setHandlestatus("0");//0未处理,1已处理
				matter.setIssuetime(new Date());
				matter.setMattername("用车申请");
				matter.setReadstatus("0");//0未读1已读
				matter.setRelateId(usingcarId);
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
			usingcarList =usingcarService.getUsingcarByIdForView(usingcarId_q);
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

	public void setUsingcarList(List usingcarList){
		this.usingcarList=usingcarList;
	}
	public List getUsingcarList(){
		return usingcarList;
	}

	public void setUsingcarId_q(String usingcarId_q){
		this.usingcarId_q=usingcarId_q;
	}
	public String getUsingcarId_q(){
		return usingcarId_q;
	}

	public void setUsingcarId(String usingcarId){
		this.usingcarId=usingcarId;
	}
	public String getUsingcarId(){
		return usingcarId;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}

	public void setDriver(String driver){
		this.driver=driver;
	}
	public String getDriver(){
		return driver;
	}

	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}

	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
	}

	public void setUsingtime(Date usingtime){
		this.usingtime=usingtime;
	}
	public Date getUsingtime(){
		return usingtime;
	}

	public void setBacktime(Date backtime){
		this.backtime=backtime;
	}
	public Date getBacktime(){
		return backtime;
	}

	public void setAccomnum(String accomnum){
		this.accomnum=accomnum;
	}
	public String getAccomnum(){
		return accomnum;
	}

	public void setUsingproperty(String usingproperty){
		this.usingproperty=usingproperty;
	}
	public String getUsingproperty(){
		return usingproperty;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setCarId(String carId){
		this.carId=carId;
	}
	public String getCarId(){
		return carId;
	}

	public void setUsingreason(String usingreason){
		this.usingreason=usingreason;
	}
	public String getUsingreason(){
		return usingreason;
	}

	public void setUsingway(String usingway){
		this.usingway=usingway;
	}
	public String getUsingway(){
		return usingway;
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

	public void setUsingcarService(UsingcarService usingcarService){
		this.usingcarService=usingcarService;
	}
	public UsingcarService getUsingcarService(){
		return usingcarService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public List getCarList() {
		return carList;
	}

	public void setCarList(List carList) {
		this.carList = carList;
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

	public List<Map> getNodes() {
		return nodes;
	}

	public void setNodes(List<Map> nodes) {
		this.nodes = nodes;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
