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
import runix.persistent.model.Memo;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.MemoService;

/**
 * 便签管理Action
 */
public class MemoAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页
	private int pages=15;//每页显示记录数
	private List memoList;//标签列表
	private String memoId_q;
	private String memoId;
	private String title;//标题
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String content;//内容
	private Date submittime;//提交时间
	private String reportperson;//上报人
	private String recieveperson;//上报对象
	private String status;//处理方式 1、暂存  2、提交
	private String flag;
	private BaseUser user;//当前用户
	private MemoService memoService;
	private BaseDataService baseDataService;
	private BaseUserService baseUserService;
	private String userRole;
	private String reportpersonname;
	private String recievepersonname;
	/**
	 * 查询便签列表
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
			//查询条件
			//System.out.println(title+"-- "+starttime+"-- "+endtime+" -- "+status);
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("am3") < 0){
					reportperson = user.getUserId();
					recieveperson = user.getUserId();
					condition.put("reportperson", reportperson);
					condition.put("recieveperson", recieveperson);
				}else{
					if(!"".equals(reportperson) && null != reportperson){
						condition.put("reportperson", reportperson);
					}
					if(!"".equals(recieveperson) && null != recieveperson){
						condition.put("recieveperson", recieveperson);
					}
				}
				
			}
			if(!"".equals(title) && null != title){
				condition.put("title", "%"+title+"%");
			}
			if(!"".equals(starttime) && null != starttime){
				condition.put("starttime", starttime);
			}
			if(!"".equals(endtime) && null != endtime){
				condition.put("endtime", endtime);
			}
			if(!"".equals(status) && null != status && !"0".equals(status)){
				condition.put("status", status);
			}

			int record = memoService.getCountMemos(condition);
			List lst =memoService.getMemos(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			memoList = pg.getResult();
			System.out.println("list == "+memoList.size());
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新   查看便签
	 */
	public String doUpdateInit(){
		try {
		//	memoList = memoService.getMemoAndNamesById(memoId_q);
			Memo memo =memoService.getMemoById(memoId_q);
			memoId = memo.getMemoId();
			title = memo.getTitle();
			starttime = memo.getStarttime();
			endtime = memo.getEndtime();
			content = memo.getContent();
			submittime = memo.getSubmittime();
			reportperson = memo.getReportperson();
			reportpersonname = baseUserService.getBaseUserById(reportperson).getName();
			recieveperson = memo.getRecieveperson();
			recievepersonname = baseUserService.getBaseUserById(recieveperson).getName();
			status = memo.getStatus();
			flag = memo.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Memo memo = memoService.getMemoAndNamesById(memoId_q);
			memoId = memo.getMemoId();
			title = memo.getTitle();
			starttime = memo.getStarttime();
			endtime = memo.getEndtime();
			content = memo.getContent();
			submittime = memo.getSubmittime();
			reportperson = memo.getReportperson();
			recieveperson = memo.getRecieveperson();
			status = memo.getStatus();
			flag = memo.getFlag();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改便签
	 */
	public String doUpdate(){
		try {
			Memo memo =memoService.getMemoById(memoId_q);
			memo.setTitle(title);
			memo.setStarttime(starttime);
			memo.setEndtime(endtime);
			memo.setContent(content);
			memo.setSubmittime(new Date());
			memo.setReportperson(reportperson);
			memo.setRecieveperson(recieveperson);
			memo.setStatus(status);
			memo.setFlag(flag);
			memoService.modifyMemo(memo);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 根据ID删除便签
	 */
	public String doDelete(){
		try {
			memoService.removeMemoById(memoId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化插入
	 */
	public String doInsertInit(){
		try {
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
	 * 添加便签
	 */
	public String doInsert(){
		try {
			Memo memo = new Memo();
		//	memoId = baseDataService.getSequence();
		//	memo.setMemoId(memoId);
			memo.setTitle(title);
			memo.setStarttime(starttime);
			memo.setEndtime(endtime);
			memo.setContent(content);
			memo.setSubmittime(new Date());
			memo.setReportperson(reportperson);
			memo.setRecieveperson(recieveperson);
			memo.setStatus(status);
			memo.setFlag(flag);
			memoService.saveMemo(memo);
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

	public void setMemoList(List memoList){
		this.memoList=memoList;
	}
	public List getMemoList(){
		return memoList;
	}

	public void setMemoId_q(String memoId_q){
		this.memoId_q=memoId_q;
	}
	public String getMemoId_q(){
		return memoId_q;
	}

	public void setMemoId(String memoId){
		this.memoId=memoId;
	}
	public String getMemoId(){
		return memoId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
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

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setSubmittime(Date submittime){
		this.submittime=submittime;
	}
	public Date getSubmittime(){
		return submittime;
	}

	public void setReportperson(String reportperson){
		this.reportperson=reportperson;
	}
	public String getReportperson(){
		return reportperson;
	}

	public void setRecieveperson(String recieveperson){
		this.recieveperson=recieveperson;
	}
	public String getRecieveperson(){
		return recieveperson;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setMemoService(MemoService memoService){
		this.memoService=memoService;
	}
	public MemoService getMemoService(){
		return memoService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getReportpersonname() {
		return reportpersonname;
	}

	public void setReportpersonname(String reportpersonname) {
		this.reportpersonname = reportpersonname;
	}

	public String getRecievepersonname() {
		return recievepersonname;
	}

	public void setRecievepersonname(String recievepersonname) {
		this.recievepersonname = recievepersonname;
	}

}
