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
import runix.service.PositionService;
import runix.service.RecruitmentService;
import runix.service.ReimbursementItemService;
import runix.service.ReimbursementService;

public class CountRecruitmentAction extends BaseAction{


	private boolean readonly;

	private String currentPage;

	private int pages=15;
	private String department;
	private String position;

	private List deptList;
	private List positionList;
	private List recruitmentCountList;
	
	private PositionService positionService;
	private DeptService deptService;
	private RecruitmentService recruitmentService;

	

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

			deptList = deptService.getAllDepts();
			positionList = positionService.getALlPositions();
			int record = recruitmentService.getCountRecruitmentsCount(condition);
			List lst =recruitmentService.getRecruitmentsCount(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			recruitmentCountList = pg.getResult();
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

	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public List getDeptList() {
		return deptList;
	}


	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}


	public List getPositionList() {
		return positionList;
	}


	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}


	public List getRecruitmentCountList() {
		return recruitmentCountList;
	}


	public void setRecruitmentCountList(List recruitmentCountList) {
		this.recruitmentCountList = recruitmentCountList;
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


	public RecruitmentService getRecruitmentService() {
		return recruitmentService;
	}


	public void setRecruitmentService(RecruitmentService recruitmentService) {
		this.recruitmentService = recruitmentService;
	}

}

