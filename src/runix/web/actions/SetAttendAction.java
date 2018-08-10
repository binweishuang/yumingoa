package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.SetAttend;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.SetAttendService;

public class SetAttendAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List setAttendList;
	private String setattendId_q;
	private String setattendId;
	private String starttime;
	private String endtime;
	private String rest;
	private String absentlimit;
	private String addOrUpdate;//用于标识新增或修改，1代表新增，2代表修改
	String messageStr ;//用于返回页面信息

	private SetAttendService setAttendService;
	private BaseDataService baseDataService;

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}


	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}


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
			condition.put("setattendId",  "".equals(setattendId)?"":"%"+setattendId+"%");
			condition.put("starttime",  "".equals(starttime)?"":"%"+starttime+"%");
			condition.put("endtime",  "".equals(endtime)?"":"%"+endtime+"%");
			condition.put("rest",  "".equals(rest)?"":"%"+rest+"%");
			condition.put("absentlimit",  "".equals(absentlimit)?"":"%"+absentlimit+"%");

			int record = setAttendService.getCountSetAttends(condition);
			List lst =setAttendService.getSetAttends(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			setAttendList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doUpdateInit(){
		try {
			//SetAttend setAttend =setAttendService.getSetAttendById(setattendId_q);
			SetAttend setAttend =setAttendService.getSetAttend();
			if(setAttend==null){
				addOrUpdate="1";
			}else{
				setattendId = setAttend.getSetattendId();
				starttime = setAttend.getStarttime();
				endtime = setAttend.getEndtime();
				rest = setAttend.getRest();
				absentlimit = setAttend.getAbsentlimit();
				addOrUpdate="2";
			}
			
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

			if("1".equals(addOrUpdate)){//新增
				SetAttend setAttend = new SetAttend();
				//setattendId = baseDataService.getSequence();
				//setAttend.setSetattendId(setattendId);
				setAttend.setStarttime(starttime);
				setAttend.setEndtime(endtime);
				setAttend.setRest(rest);
				setAttend.setAbsentlimit(absentlimit);
				setAttendService.saveSetAttend(setAttend);
				messageStr = "考勤设置成功！";
				addOrUpdate="2";
				setattendId =setAttend.getSetattendId();
			}else if("2".equals(addOrUpdate)){//修改
				SetAttend setAttend =setAttendService.getSetAttendById(setattendId);
				setAttend.setSetattendId(setattendId);
				setAttend.setStarttime(starttime);
				setAttend.setEndtime(endtime);
				setAttend.setRest(rest);
				setAttend.setAbsentlimit(absentlimit);
				setAttendService.modifySetAttend(setAttend);
				messageStr = "考勤设置成功！";
			}
			this.addActionMessage(messageStr);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			setAttendService.removeSetAttendById(setattendId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

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

	public String doInsert(){
		try {
			SetAttend setAttend = new SetAttend();
			setAttend.setSetattendId(setattendId);
			setAttend.setStarttime(starttime);
			setAttend.setEndtime(endtime);
			setAttend.setRest(rest);
			setAttend.setAbsentlimit(absentlimit);
			setAttendService.saveSetAttend(setAttend);
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

	public void setSetAttendList(List setAttendList){
		this.setAttendList=setAttendList;
	}
	public List getSetAttendList(){
		return setAttendList;
	}

	public void setSetattendId_q(String setattendId_q){
		this.setattendId_q=setattendId_q;
	}
	public String getSetattendId_q(){
		return setattendId_q;
	}

	public void setSetattendId(String setattendId){
		this.setattendId=setattendId;
	}
	public String getSetattendId(){
		return setattendId;
	}

	public void setStarttime(String starttime){
		this.starttime=starttime;
	}
	public String getStarttime(){
		return starttime;
	}

	public void setEndtime(String endtime){
		this.endtime=endtime;
	}
	public String getEndtime(){
		return endtime;
	}

	public void setRest(String rest){
		this.rest=rest;
	}
	public String getRest(){
		return rest;
	}

	public void setAbsentlimit(String absentlimit){
		this.absentlimit=absentlimit;
	}
	public String getAbsentlimit(){
		return absentlimit;
	}

	public void setSetAttendService(SetAttendService setAttendService){
		this.setAttendService=setAttendService;
	}
	public SetAttendService getSetAttendService(){
		return setAttendService;
	}


	public String getAddOrUpdate() {
		return addOrUpdate;
	}

	public void setAddOrUpdate(String addOrUpdate) {
		this.addOrUpdate = addOrUpdate;
	}
	
	public String getMessageStr() {
		return messageStr;
	}


	public void setMessageStr(String messageStr) {
		this.messageStr = messageStr;
	}

}
