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
import runix.persistent.model.Contract;
import runix.persistent.model.Dept;
import runix.service.BaseDataService;
import runix.service.ContractService;
import runix.service.DeptService;

/**
 * 合同管理Action
 */
public class ContractAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页
	private int pages=15;//页面显示记录数
	private List contractList;//合同列表
	private String contractId_q;
	private String contractId;
	private String signedperson;//签订人
	private String department;//部门
	private String contracttype;//合同类型
	private String status;//合同状态  1、已执行  2、执行中  3、未执行
	private String customecompany;//客户单位
	private String contact;//联系人
	private String contacttel;//联系人电话
	private Date signeddate;//签订日期
	private Date enddate;//终止日期
	private String details;//合同说明
	private String flag;
	private ContractService contractService;
	private BaseDataService baseDataService;
	private DeptService deptService;
	private List contracttypeList;//合同类型列表
	private List deptList;//部门列表
	private BaseUser user;//当前用户
	private Dept dept;//部门
	
	private String userid;
	private String userRole;

	/**
	 * 查询合同
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

			//System.out.println(department+"---"+contracttype+"---"+status);
			Map condition = new HashMap();
			//查询条件
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("cm3") < 0){
					signedperson = user.getUserId();
					condition.put("signedperson", signedperson);
				}else{
					if(!"".equals(signedperson) && null != signedperson){
						condition.put("signedperson", signedperson);
					}
				}
				if(!"".equals(department) && null != department && !"0".equals(department)){
					condition.put("department", department);
				}
				if(!"".equals(contracttype) && null != contracttype && !"0".equals(contracttype)){
					condition.put("contracttype", contracttype);
				}
				if(!"".equals(status) && null != status && !"0".equals(status)){
					condition.put("status", status);
				}
	
				int record = contractService.getCountContracts(condition);
				List lst =contractService.getContracts(condition, offset, limit);
	
				Pageable pg = null;
				try {
					pg = new IbatisPage(lst, record, currentPageInt, pages);
				} catch (PageException e) {
					pg = null;
				}
				ServletActionContext.getRequest().setAttribute("pages", pg);
				contractList = pg.getResult();
				contracttypeList = baseDataService.getqBaseByDatatype("CONTRACT");
				deptList = deptService.getAllDepts();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新  查看
	 */
	public String doUpdateInit(){
		try {
			contractList = contractService.getContractAndNamesById(contractId_q);
			contracttypeList = baseDataService.getqBaseByDatatype("CONTRACT");
			Contract contract =contractService.getContractById(contractId_q);
			contractId = contract.getContractId();
			signedperson = contract.getSignedperson();
			department = contract.getDepartment();
			contracttype = contract.getContracttype();
			status = contract.getStatus();
			customecompany = contract.getCustomecompany();
			contact = contract.getContact();
			contacttel = contract.getContacttel();
			signeddate = contract.getSigneddate();
			enddate = contract.getEnddate();
			details = contract.getDetails();
			flag = contract.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改合同
	 */
	public String doUpdate(){
		try {
			Contract contract =contractService.getContractById(contractId_q);
		//	contract.setContractId(contractId);
			contract.setSignedperson(signedperson);
			contract.setDepartment(department);
			contract.setContracttype(contracttype);
			contract.setStatus(status);
			contract.setCustomecompany(customecompany);
			contract.setContact(contact);
			contract.setContacttel(contacttel);
			contract.setSigneddate(signeddate);
			contract.setEnddate(enddate);
			contract.setDetails(details);
			contract.setFlag(flag);
			contractService.modifyContract(contract);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 删除合同
	 */
	public String doDelete(){
		try {
			contractService.removeContractById(contractId_q);
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
			readonly = false;
			contracttypeList = baseDataService.getqBaseByDatatype("CONTRACT");
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			dept = deptService.getDeptById(user.getDeptId());
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 添加合同
	 */
	public String doInsert(){
		try {
			Contract contract = new Contract();
		//	contractId = baseDataService.getSequence();
		//	contract.setContractId(contractId);
			contract.setSignedperson(signedperson);
			contract.setDepartment(department);
			contract.setContracttype(contracttype);
			contract.setStatus(status);
			contract.setCustomecompany(customecompany);
			contract.setContact(contact);
			contract.setContacttel(contacttel);
			contract.setSigneddate(signeddate);
			contract.setEnddate(enddate);
			contract.setDetails(details);
			contract.setFlag(flag);
			contractService.saveContract(contract);
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

	public void setContractList(List contractList){
		this.contractList=contractList;
	}
	public List getContractList(){
		return contractList;
	}

	public void setContractId_q(String contractId_q){
		this.contractId_q=contractId_q;
	}
	public String getContractId_q(){
		return contractId_q;
	}

	public void setContractId(String contractId){
		this.contractId=contractId;
	}
	public String getContractId(){
		return contractId;
	}

	public void setSignedperson(String signedperson){
		this.signedperson=signedperson;
	}
	public String getSignedperson(){
		return signedperson;
	}

	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}

	public void setContracttype(String contracttype){
		this.contracttype=contracttype;
	}
	public String getContracttype(){
		return contracttype;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setCustomecompany(String customecompany){
		this.customecompany=customecompany;
	}
	public String getCustomecompany(){
		return customecompany;
	}

	public void setContact(String contact){
		this.contact=contact;
	}
	public String getContact(){
		return contact;
	}

	public void setContacttel(String contacttel){
		this.contacttel=contacttel;
	}
	public String getContacttel(){
		return contacttel;
	}

	public void setSigneddate(Date signeddate){
		this.signeddate=signeddate;
	}
	public Date getSigneddate(){
		return signeddate;
	}

	public void setEnddate(Date enddate){
		this.enddate=enddate;
	}
	public Date getEnddate(){
		return enddate;
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

	public void setContractService(ContractService contractService){
		this.contractService=contractService;
	}
	public ContractService getContractService(){
		return contractService;
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

	public List getContracttypeList() {
		return contracttypeList;
	}

	public void setContracttypeList(List contracttypeList) {
		this.contracttypeList = contracttypeList;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}
}
