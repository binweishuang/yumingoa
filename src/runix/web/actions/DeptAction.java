package runix.web.actions;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Dept;
import kdf.web.action.BaseAction;
import runix.service.DeptService;

public class DeptAction extends BaseAction {

	private boolean readonly;
	private String currentPage;
	private int pages = 15;
	private List deptList;
	private String deptId_q;
	private String deptId;
	private String deptname;
	private String phone;
	private String description;
	private String parentid;
	private String flag;
	private DeptService deptService;
	// input

	// ouput
	private Dept dept;// 部门
	private List<Dept> depts;// 部门集合
	private boolean result;

	/**
	 * 部门列表页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doList() {
		depts = deptService.getAllDepts();
		return SUCCESS;
	}

	/**
	 * 部门添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doAddIndex() {
		depts = deptService.getAllDepts();
		return SUCCESS;
	}

	/**
	 * 校验部门名称是否相同 相同：true, 不同：false
	 * 
	 * @author wangfq
	 * @return
	 */
	public String isSameName() {
		result = deptService.isSameName(dept);
		return SUCCESS;
	}

	/**
	 * 部门持久化
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doAddOperate() {
		try {
			deptService.persist(dept);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 部门修改页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doEditIndex() {
		depts = deptService.getAllDepts();
		dept = deptService.getDeptById(dept);
		return SUCCESS;
	}

	/**
	 * 部门的修改操作
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doEditOperate() {
		deptService.update(dept);
		return SUCCESS;
	}

	/**
	 * 部门的修改操作
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDetail() {
		dept = deptService.getDeptById(dept);
		return SUCCESS;
	}

	/**
	 * 部门的修改操作
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDel() {
		deptService.delete(dept);
		return SUCCESS;
	}

	public String doQuery() {
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
			int offset = (currentPageInt - 1) * pages;
			int limit = pages;

			Map<String, String> condition = new HashMap<String, String>();

			int record = deptService.getCountDepts(condition);
			List<Dept> lst = deptService.getDepts(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			depts = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		System.out.println("deptList==============" + depts);
		return SUCCESS;
	}

	public String doUpdateInit() {
		try {
			Dept dept = deptService.getDeptById(deptId_q);
			deptId = dept.getDeptId();
			deptname = dept.getDeptname();
			phone = dept.getPhone();
			description = dept.getDescription();
			parentid = dept.getParentid();
			flag = dept.getFlag();
			readonly = true;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate() {
		try {
			Dept dept = deptService.getDeptById(deptId_q);
			dept.setDeptId(deptId);
			dept.setDeptname(deptname);
			dept.setPhone(phone);
			dept.setDescription(description);
			dept.setParentid(parentid);
			dept.setFlag(flag);
			deptService.modifyDept(dept);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete() {
		try {
			deptService.removeDeptById(deptId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit() {
		try {
			readonly = false;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert() {
		try {
			Dept dept = new Dept();
			dept.setDeptId(deptId);
			dept.setDeptname(deptname);
			dept.setPhone(phone);
			dept.setDescription(description);
			dept.setParentid(parentid);
			dept.setFlag(flag);
			deptService.saveDept(dept);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean getReadonly() {
		return readonly;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptId_q(String deptId_q) {
		this.deptId_q = deptId_q;
	}

	public String getDeptId_q() {
		return deptId_q;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
