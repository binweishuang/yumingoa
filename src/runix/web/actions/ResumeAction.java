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

import runix.persistent.model.Resume;
import runix.service.BaseDataService;
import runix.service.DeptService;
import runix.service.PositionService;
import runix.service.RecruitmentService;
import runix.service.ResumeService;
/*
 * 简历管理
 */
public class ResumeAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List resumeList;
	private String resumeId_q;
	private String resumeId;
	private String name;
	private String sex;
	private String age;
	private String tel;
	private String department;
	private String position;
	private String hire;
	private String recruitmentId;
	private String atachname;
	private String atachpath;
	private String remark;
	private String flag;
	private Date appdate;
	private String interview;
	private boolean result;
	
	private List positionList;
	private List recruitmentList;
	private List deptList;
	private PositionService positionService;
	private ResumeService resumeService;
	private DeptService deptService;
	private RecruitmentService recruitmentService;
	private BaseDataService baseDataService;

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
			if (!"".equals(position) && null != position) {
				condition.put("position", position);
			}
			if (!"".equals(interview) && null != interview) {
				condition.put("interview", interview);
			}
			
			positionList = positionService.getALlPositions();
			int record = resumeService.getCountResumes(condition);
			List lst =resumeService.getResumes(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			resumeList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			//deptList = deptService.getAllDepts();
			//positionList = positionService.getALlPositions();
			recruitmentList = recruitmentService.getAllRecruitments();//获取审核通过的招聘信息
			Resume resume =resumeService.getResumeById(resumeId_q);
			resumeId = resume.getResumeId();
			name = resume.getName();
			sex = resume.getSex();
			age = resume.getAge();
			tel = resume.getTel();
			department = resume.getDepartment();
			position = resume.getPosition();
			hire = resume.getHire();
			recruitmentId = resume.getRecruitmentId();
			atachname = resume.getAtachname();
			atachpath = resume.getAtachpath();
			remark = resume.getRemark();
			flag = resume.getFlag();
			appdate = resume.getAppdate();
			interview = resume.getInterview();
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
			Resume resume =resumeService.getResumeById(resumeId_q);
		//	resume.setResumeId(resumeId);
			resume.setName(name);
			resume.setSex(sex);
			resume.setAge(age);
			resume.setTel(tel);
			resume.setDepartment(department);
			resume.setPosition(position);
			resume.setHire(hire);
			resume.setRecruitmentId(recruitmentId);
			resume.setAtachname(atachname);
			resume.setAtachpath(atachpath);
			resume.setRemark(remark);
			resume.setFlag(flag);
			resume.setInterview(interview);
			resumeService.modifyResume(resume);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			resumeService.removeResumeById(resumeId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			//deptList = deptService.getAllDepts();
			//positionList = positionService.getALlPositions();
			recruitmentList = recruitmentService.getAllRecruitments();//获取审核通过的招聘信息
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
			Resume resume = new Resume();
			//resumeId = baseDataService.getSequence();
			//resume.setResumeId(resumeId);
			resume.setName(name);
			resume.setSex(sex);
			resume.setAge(age);
			resume.setTel(tel);
			resume.setDepartment(department);
			resume.setPosition(position);
			resume.setHire(hire);
			resume.setRecruitmentId(recruitmentId);
			resume.setAtachname(atachname);
			resume.setAtachpath(atachpath);
			resume.setRemark(remark);
			resume.setFlag(flag);
			resume.setAppdate(appdate);
			resume.setInterview(interview);
			resumeService.saveResume(resume);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInterviewInit(){
		try {
			Resume resume =resumeService.getResumeById(resumeId_q);
			resumeId = resume.getResumeId();
			name=resume.getName();
			appdate = resume.getAppdate();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doInterview(){
		try {
			result = false;
			Resume resume =resumeService.getResumeById(resumeId_q);
			resume.setAppdate(appdate);
			resumeService.modifyResume(resume);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Resume resume =resumeService.getResumeById2(resumeId_q);
			name = resume.getName();
			sex = resume.getSex();
			age = resume.getAge();
			tel = resume.getTel();
			department = resume.getDepartment();
			position = resume.getPosition();
			hire = resume.getHire();
			recruitmentId = resume.getRecruitmentId();
			atachname = resume.getAtachname();
			atachpath = resume.getAtachpath();
			remark = resume.getRemark();
			flag = resume.getFlag();
			appdate = resume.getAppdate();
			interview = resume.getInterview();
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

	public void setResumeList(List resumeList){
		this.resumeList=resumeList;
	}
	public List getResumeList(){
		return resumeList;
	}

	public void setResumeId_q(String resumeId_q){
		this.resumeId_q=resumeId_q;
	}
	public String getResumeId_q(){
		return resumeId_q;
	}

	public void setResumeId(String resumeId){
		this.resumeId=resumeId;
	}
	public String getResumeId(){
		return resumeId;
	}

	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public void setSex(String sex){
		this.sex=sex;
	}
	public String getSex(){
		return sex;
	}

	public void setAge(String age){
		this.age=age;
	}
	public String getAge(){
		return age;
	}

	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
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

	public void setHire(String hire){
		this.hire=hire;
	}
	public String getHire(){
		return hire;
	}

	public void setRecruitmentId(String recruitmentId){
		this.recruitmentId=recruitmentId;
	}
	public String getRecruitmentId(){
		return recruitmentId;
	}

	public void setAtachname(String atachname){
		this.atachname=atachname;
	}
	public String getAtachname(){
		return atachname;
	}

	public void setAtachpath(String atachpath){
		this.atachpath=atachpath;
	}
	public String getAtachpath(){
		return atachpath;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setAppdate(Date appdate){
		this.appdate=appdate;
	}
	public Date getAppdate(){
		return appdate;
	}

	public void setInterview(String interview){
		this.interview=interview;
	}
	public String getInterview(){
		return interview;
	}

	public void setResumeService(ResumeService resumeService){
		this.resumeService=resumeService;
	}
	public ResumeService getResumeService(){
		return resumeService;
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

	public List getRecruitmentList() {
		return recruitmentList;
	}

	public void setRecruitmentList(List recruitmentList) {
		this.recruitmentList = recruitmentList;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
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

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
