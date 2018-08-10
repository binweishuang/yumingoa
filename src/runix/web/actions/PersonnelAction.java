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
import runix.persistent.model.Dept;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.Matter;
import runix.persistent.model.Personnel;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.DeptService;
import runix.service.FlowNodeService;
import runix.service.MatterService;
import runix.service.PersonnelService;
import runix.service.PositionService;
import runix.service.ReimbursementService;
/**
 * 人事管理
 * @author runix
 *
 */
public class PersonnelAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List personnelList;
	private String personnelId_q;
	private String personnelId;
	private String perstype;
	private String name;
	private String dept;
	private Date applytime;
	private String handlestatus;
	private String commitstatus;
	private String workflowId;
	private String content;
	private String dimtype;
	private String dimstatus;
	private String title;
	private String oldpositioln;
	private String newposition;
	private String newdept;
	private String attachname;
	private String atachpath;
	private String flag;
	private String checkstatus;
	private String status;
	private String opinion;
	private PersonnelService personnelService;//人事service
	private BaseDataService baseDataService;//序列service
	private FlowNodeService flowNodeService;//流程节点
	private BaseUserService baseUserService;//用户sevice
	private MatterService matterService;//事项
	private DeptService deptService;//部门service
	private PositionService positionService;
	private List<Dept> depts;//部门集合
	private List positionList;
	private Personnel personnel;
	private List<Map> nodes;
	private List dimtypeList;//离职类型列表
	
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

	private ReimbursementService reimbursementService;
	
	
	public String doQueryM(){
		return SUCCESS;
	}
	


	
	
	
	/**
	 * 转正查询
	 * @return
	 */
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
			if("1".equals(flag)){
				condition.put("perstype", "ZHZH") ;//转正
				if(!"".equals(checkstatus)&&null!=checkstatus){
					condition.put("checkstatus", checkstatus) ;
				}
				if(!"".equals(status)&&null!=status){
					condition.put("status", status) ;
				}
			}else if("2".equals(flag)){
				dimtypeList = baseDataService.getqBaseByDatatype("DIMTYPE");
				condition.put("perstype", "LZH") ;//离职
				if(!"".equals(dimtype)&&null!=dimtype){
					condition.put("dimtype", dimtype) ;
				}
				if(!"".equals(checkstatus)&&null!=checkstatus){
					condition.put("checkstatus", checkstatus) ;
				}
			}else if("3".equals(flag)){
				condition.put("perstype", "DD") ;//调动
				if(!"".equals(checkstatus)&&null!=checkstatus){
					condition.put("checkstatus", checkstatus) ;
				}
				if(!"".equals(status)&&null!=status){
					condition.put("status", status) ;
				}
			}
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("cm4") < 0){
						condition.put("name", user.getUserId()) ;
				}
				
			}
			int record = personnelService.getCountPersonnels(condition);
			List lst =personnelService.getPersonnels(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			personnelList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
		}
		return SUCCESS;
	}
	

	
	public String doUpdateInit(){
		try {
			if("2".equals(flag)){
				dimtypeList = baseDataService.getqBaseByDatatype("DIMTYPE");
			}
			if("3".equals(flag)){
				depts = deptService.getAllDepts();
				positionList = positionService.getALlPositions();
			}
			Personnel personnel =personnelService.getPersonnelById(personnelId_q);
			personnelId = personnel.getPersonnelId();
			perstype = personnel.getPerstype();
			name = baseUserService.getBaseUserById(personnel.getName()).getName();
			dept = deptService.getDeptById(personnel.getDept()).getDeptname();
			applytime = personnel.getApplytime();
			handlestatus = personnel.getHandlestatus();
			commitstatus = personnel.getCommitstatus();
			workflowId = personnel.getWorkflowId();
			content = personnel.getContent();
			dimtype = personnel.getDimtype();
			dimstatus = personnel.getDimstatus();
			title = personnel.getTitle();
			oldpositioln = positionService.getPositionById(personnel.getOldpositioln()).getPostname();
			newposition = personnel.getNewposition();
			newdept = personnel.getNewdept();
			attachname = personnel.getAttachname();
			atachpath = personnel.getAtachpath();
		//	flag = personnel.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			
			Personnel personnel =personnelService.getPersonnelById(personnelId_q);
			if("1".equals(flag)){
				personnel.setTitle(title);
				personnel.setContent(content);
				personnelService.modifyPersonnel(personnel);
				nodes = flowNodeService.getNodeNamesByFlowname("转正管理");
			}else if("2".equals(flag)){
				personnel.setTitle(title);
				personnel.setContent(content);
				personnel.setDimtype(dimtype);
				personnelService.modifyPersonnel(personnel);
				nodes = flowNodeService.getNodeNamesByFlowname("离职管理");
			}else if("3".equals(flag)){
				personnel.setTitle(title);
				personnel.setContent(content);
				personnel.setAttachname(attachname);
				personnel.setAtachpath(atachpath);
				personnel.setNewdept(newdept);
				personnel.setNewposition(newposition);
				personnelService.modifyPersonnel(personnel);
				nodes = flowNodeService.getNodeNamesByFlowname("调动管理");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			matterService.removeMatterByRelateId(personnelId_q);
			reimbursementService.removeFlowPeopleByRelateId(personnelId_q);
			personnelService.removePersonnelById(personnelId_q);
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
				//if("1".equals(flag)){
					name = user.getName();
					dept = deptService.getDeptnameById(user.getDeptId());
				//}
					if("2".equals(flag)){
						dimtypeList = baseDataService.getqBaseByDatatype("DIMTYPE");
					}
					if("3".equals(flag)){
						oldpositioln = positionService.getPositionById(user.getPositionId()).getPostname();
						depts = deptService.getAllDepts();
						positionList = positionService.getALlPositions();
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
		}
		return SUCCESS;
	}
	
	public String doInsert(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				if("1".equals(flag)){
					Personnel personnel = new Personnel();
					//personnelId = baseDataService.getSequence();
					//personnel.setPersonnelId(personnelId);
					//personnel.setApplytime(new Date());
					personnel.setName(user.getUserId());
					personnel.setDept(user.getDeptId());
					personnel.setContent(content);
					personnel.setTitle(title);
					personnel.setCheckstatus("3");//未审核
					personnel.setPerstype("ZHZH");//转正类型
					personnel.setStatus("0");//未提交
					personnelService.savePersonnel(personnel);
					personnelId = personnel.getPersonnelId();
					nodes = flowNodeService.getNodeNamesByFlowname("转正管理");
				}else if("2".equals(flag)){
					Personnel personnel = new Personnel();
					//personnelId = baseDataService.getSequence();
					//personnel.setPersonnelId(personnelId);
					//personnel.setApplytime(new Date());
					personnel.setName(user.getUserId());
					personnel.setDept(user.getDeptId());
					personnel.setContent(content);
					personnel.setTitle(title);
					personnel.setCheckstatus("3");//未审核
					personnel.setPerstype("LZH");//离职类型
					personnel.setStatus("0");//未提交
					personnel.setDimtype(dimtype);//离职类型
					personnelService.savePersonnel(personnel);
					personnelId = personnel.getPersonnelId();
					nodes = flowNodeService.getNodeNamesByFlowname("离职管理");
				}else if("3".equals(flag)){
					Personnel personnel = new Personnel();
					//personnelId = baseDataService.getSequence();
					//personnel.setPersonnelId(personnelId);
					//personnel.setApplytime(new Date());
					personnel.setName(user.getUserId());
					personnel.setDept(user.getDeptId());
					personnel.setOldpositioln(user.getPositionId());
					personnel.setNewdept(newdept);
					personnel.setNewposition(newposition);
					personnel.setAtachpath(atachpath);
					personnel.setAttachname(attachname);
					personnel.setContent(content);
					personnel.setTitle(title);
					personnel.setCheckstatus("3");//未审核
					personnel.setPerstype("DD");//调动类型
					personnel.setStatus("0");//未提交
					personnelService.savePersonnel(personnel);
					personnelId = personnel.getPersonnelId();
					nodes = flowNodeService.getNodeNamesByFlowname("调动管理");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
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
					flowPeople.setRelateId(personnelId);
					flowPeople.setSortnum(i+1+"");
					flowPeople.setHandlestatus("0");
					flowPeople.setReadstatus("0");
					reimbursementService.saveFlowPeople(flowPeople);//审核流程人员
				}
				personnel = personnelService.getPersonnelById(personnelId);
				personnel.setCheckstatus("3");//3：未审核；2：审核中；1：已审核；
				personnel.setStatus("1");//1：已提交；0：未提交；
				personnel.setApplytime(new Date());//提交日期
				personnelService.modifyPersonnel(personnel);//人事状态修改
				
				Matter matter = new Matter();//代办事项
				//matterId = baseDataService.getSequence();
				//matter.setMatterId(matterId);
				matter.setExecutor(personIds[0]);
				matter.setHandlestatus("0");//0：未处理,1：已处理；
				matter.setIssuetime(new Date());
				if("1".equals(flag)){
					matter.setMattername("转正申请");
				}else if("2".equals(flag)){
					matter.setMattername("离职申请");
				}else if("3".equals(flag)){
					matter.setMattername("调动申请");
				}
				matter.setReadstatus("0");//0：未读；1：已读；
				matter.setRelateId(personnelId);
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
		try{
			Personnel personnel =personnelService.getPersonnelByIdForView(personnelId_q);
			personnelId = personnel.getPersonnelId();
			perstype = personnel.getPerstype();
			name = personnel.getName();
			dept = personnel.getDept();
			applytime = personnel.getApplytime();
			handlestatus = personnel.getHandlestatus();
			commitstatus = personnel.getCommitstatus();
			workflowId = personnel.getWorkflowId();
			content = personnel.getContent();
			dimtype = personnel.getDimtype();
			dimstatus = personnel.getDimstatus();
			title = personnel.getTitle();
			oldpositioln = personnel.getOldpositioln();
			newposition = personnel.getNewposition();
			newdept = personnel.getNewdept();
			attachname = personnel.getAttachname();
			atachpath = personnel.getAtachpath();
			checkstatus = personnel.getCheckstatus();
			opinion = personnel.getOpinion();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "zhzh";//转正
		}else if("2".equals(flag)){
			return "lzh";//离职
		}else if("3".equals(flag)){
			return "dd";//调动
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

	public void setPersonnelList(List personnelList){
		this.personnelList=personnelList;
	}
	
	public List getPersonnelList(){
		return personnelList;
	}

	public void setPersonnelId_q(String personnelId_q){
		this.personnelId_q=personnelId_q;
	}
	public String getPersonnelId_q(){
		return personnelId_q;
	}

	public void setPersonnelId(String personnelId){
		this.personnelId=personnelId;
	}
	public String getPersonnelId(){
		return personnelId;
	}

	public void setPerstype(String perstype){
		this.perstype=perstype;
	}
	public String getPerstype(){
		return perstype;
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

	public void setApplytime(Date applytime){
		this.applytime=applytime;
	}
	public Date getApplytime(){
		return applytime;
	}

	public void setHandlestatus(String handlestatus){
		this.handlestatus=handlestatus;
	}
	public String getHandlestatus(){
		return handlestatus;
	}

	public void setCommitstatus(String commitstatus){
		this.commitstatus=commitstatus;
	}
	public String getCommitstatus(){
		return commitstatus;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setDimtype(String dimtype){
		this.dimtype=dimtype;
	}
	public String getDimtype(){
		return dimtype;
	}

	public void setDimstatus(String dimstatus){
		this.dimstatus=dimstatus;
	}
	public String getDimstatus(){
		return dimstatus;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setOldpositioln(String oldpositioln){
		this.oldpositioln=oldpositioln;
	}
	public String getOldpositioln(){
		return oldpositioln;
	}

	public void setNewposition(String newposition){
		this.newposition=newposition;
	}
	public String getNewposition(){
		return newposition;
	}

	public void setNewdept(String newdept){
		this.newdept=newdept;
	}
	public String getNewdept(){
		return newdept;
	}

	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}

	public void setAtachpath(String atachpath){
		this.atachpath=atachpath;
	}
	public String getAtachpath(){
		return atachpath;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setPersonnelService(PersonnelService personnelService){
		this.personnelService=personnelService;
	}
	public PersonnelService getPersonnelService(){
		return personnelService;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService) {
		this.flowNodeService = flowNodeService;
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

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
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

	public void setReimbursementService(ReimbursementService reimbursementService) {
		this.reimbursementService = reimbursementService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
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

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public FlowNodeService getFlowNodeService() {
		return flowNodeService;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public MatterService getMatterService() {
		return matterService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}

	public List getDimtypeList() {
		return dimtypeList;
	}
	public void setDimtypeList(List dimtypeList) {
		this.dimtypeList = dimtypeList;
	}
	public List getPositionList() {
		return positionList;
	}
	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}
	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
