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
import runix.persistent.model.DutyMessage;
import runix.service.BaseDataService;
import runix.service.DutyMessageService;

/**
 *	值班信息Action 
 */
public class DutyMessageAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页
	private int pages=15;//页面显示记录数
	private List dutyMessageList;//值班信息列表
	private String dutyId_q;
	private String dutyId;
	private String dutyperson;//值班人
	private String classes;//班次
	private Date dutydate;//值班日期
	private String starttime;//开始时间
	private String endtime;//结束时间
	private String dutyaddress;//值班地点
	private String remark;//备注
	private String flag;
	private DutyMessageService dutyMessageService;
	private BaseDataService baseDataService;
	private String userRole;

	/**
	 * 查询值班信息
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
			//查询条件
			Map condition = new HashMap();
//			System.out.println(dutyperson+"---"+starttime+"---"+endtime);
			if(!"".equals(dutyperson) && null != dutyperson){
				condition.put("dutyperson", "%"+dutyperson+"%");
			}
			if(!"".equals(starttime) && null != starttime){
				condition.put("starttime", starttime);
			}
			if(!"".equals(endtime) && null != endtime){
				condition.put("endtime", endtime);
			}
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("am8") < 0){
					readonly = true;
				}
			}
			int record = dutyMessageService.getCountDutyMessages(condition);
			List lst =dutyMessageService.getDutyMessages(condition, offset, limit);
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			dutyMessageList = pg.getResult();
//			System.out.println("object====="+dutyMessageList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新值班信息
	 */
	public String doUpdateInit(){
		try {
			dutyMessageList = dutyMessageService.getDutyMessageAndNameById(dutyId_q);
			DutyMessage dutyMessage =dutyMessageService.getDutyMessageById(dutyId_q);
			dutyId = dutyMessage.getDutyId();
			dutyperson = dutyMessage.getDutyperson();
			classes = dutyMessage.getClasses();
			dutydate = dutyMessage.getDutydate();
			starttime = dutyMessage.getStarttime();
			endtime = dutyMessage.getEndtime();
			dutyaddress = dutyMessage.getDutyaddress();
			remark = dutyMessage.getRemark();
			flag = dutyMessage.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改值班信息
	 */
	public String doUpdate(){
		try {
			DutyMessage dutyMessage =dutyMessageService.getDutyMessageById(dutyId_q);
			dutyMessage.setDutyperson(dutyperson);
			dutyMessage.setClasses(classes);
			dutyMessage.setDutydate(dutydate);
			dutyMessage.setStarttime(starttime);
			dutyMessage.setEndtime(endtime);
			dutyMessage.setDutyaddress(dutyaddress);
			dutyMessage.setRemark(remark);
			dutyMessage.setFlag(flag);
			dutyMessageService.modifyDutyMessage(dutyMessage);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 删除值班信息
	 */
	public String doDelete(){
		try {
			dutyMessageService.removeDutyMessageById(dutyId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化插入值班信息
	 */
	public String doInsertInit(){
		try {
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 添加值班信息
	 */
	public String doInsert(){
		try {
			DutyMessage dutyMessage = new DutyMessage();
			//dutyId = baseDataService.getSequence();
		//	dutyMessage.setDutyId(dutyId);
			dutyMessage.setDutyperson(dutyperson);
			dutyMessage.setClasses(classes);
			dutyMessage.setDutydate(dutydate);
			dutyMessage.setStarttime(starttime);
			dutyMessage.setEndtime(endtime);
			dutyMessage.setDutyaddress(dutyaddress);
			dutyMessage.setRemark(remark);
			dutyMessage.setFlag(flag);
			dutyMessageService.saveDutyMessage(dutyMessage);
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

	public void setDutyMessageList(List dutyMessageList){
		this.dutyMessageList=dutyMessageList;
	}
	public List getDutyMessageList(){
		return dutyMessageList;
	}

	public void setDutyId_q(String dutyId_q){
		this.dutyId_q=dutyId_q;
	}
	public String getDutyId_q(){
		return dutyId_q;
	}

	public void setDutyId(String dutyId){
		this.dutyId=dutyId;
	}
	public String getDutyId(){
		return dutyId;
	}

	public void setDutyperson(String dutyperson){
		this.dutyperson=dutyperson;
	}
	public String getDutyperson(){
		return dutyperson;
	}

	public void setClasses(String classes){
		this.classes=classes;
	}
	public String getClasses(){
		return classes;
	}

	public void setDutydate(Date dutydate){
		this.dutydate=dutydate;
	}
	public Date getDutydate(){
		return dutydate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setDutyaddress(String dutyaddress){
		this.dutyaddress=dutyaddress;
	}
	public String getDutyaddress(){
		return dutyaddress;
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

	public void setDutyMessageService(DutyMessageService dutyMessageService){
		this.dutyMessageService=dutyMessageService;
	}
	public DutyMessageService getDutyMessageService(){
		return dutyMessageService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

}
