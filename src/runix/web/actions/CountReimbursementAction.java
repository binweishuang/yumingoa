package runix.web.actions;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.service.BaseDataService;
import runix.service.DeptService;
import runix.service.ReimbursementItemService;
import runix.service.ReimbursementService;

public class CountReimbursementAction extends BaseAction{


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

	private String item;

	private String flag;
	private String checkstatus;
	private String applytime;
	private String dept;

	private List deptList;
	private List reimtypeList;
	private List reimbursementItemList;

	private DeptService deptService;
	private BaseDataService baseDataService;
	private ReimbursementItemService reimbursementItemService;

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	private ReimbursementService reimbursementService;
	

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
			if (!"".equals(person) && null != person) {
				condition.put("person", "%"+person+"%");
			}
			if (!"".equals(reimtype) && null != reimtype) {
				condition.put("reimtype", reimtype);
			}
			if (!"".equals(dept) && null != dept) {
				condition.put("dept", dept);
			}
			deptList = deptService.getAllDepts();
			reimtypeList = baseDataService.getqBaseByDatatype("REIMBURSE");
			int record = reimbursementService.getCountReimbursementsCount(condition);
			List lst =reimbursementService.getReimbursementsCount(condition, offset, limit);

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

	public void setReimbursementService(ReimbursementService reimbursementService){
		this.reimbursementService=reimbursementService;
	}
	public ReimbursementService getReimbursementService(){
		return reimbursementService;
	}



	public List getReimbursementItemList() {
		return reimbursementItemList;
	}

	public void setReimbursementItemList(List reimbursementItemList) {
		this.reimbursementItemList = reimbursementItemList;
	}

	
	public ReimbursementItemService getReimbursementItemService() {
		return reimbursementItemService;
	}

	public void setReimbursementItemService(
			ReimbursementItemService reimbursementItemService) {
		this.reimbursementItemService = reimbursementItemService;
	}


	public List getReimtypeList() {
		return reimtypeList;
	}

	public void setReimtypeList(List reimtypeList) {
		this.reimtypeList = reimtypeList;
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

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}




}

