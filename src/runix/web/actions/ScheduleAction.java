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
import runix.persistent.model.Schedule;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.ScheduleService;

public class ScheduleAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List<Schedule> scheduleList;//日程集合
	private String scheduleId_q;
	private String scheduleId;
	private String executer;
	private String title;
	private Date starttime;
	private Date endtime;
	private Date rangetime;
	private String content;
	private String flag;
	private ScheduleService scheduleService;//日程service
	private BaseDataService baseDataService;//序列
	private BaseUserService baseUserService;
	private Schedule schedule;//日程实体对象
	private String realName;//用户真实姓名
	private String userRole;
	private String userid;//登录用户id
	/**
	 * 日程添加页面
	 * @return
	 */
	public String doAddSchedule(){
		BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user!=null){
			userid = user.getUserId();
			realName = user.getName();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加日程
	 * @return
	 * @author luqj
	 */
	public String doInsert(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userid = user.getUserId();
			}
			//schedule.setScheduleId(baseDataService.getSequence());//序列
			schedule.setExecuter(userid);//登陆用户的主键，当前是wfq的主键
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			schedule.setRangetime(new Date());//当前系统时间
			scheduleService.saveSchedule(schedule);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	/**
	 * 日程查询
	 * @return
	 * @author luqj
	 */
	public String doQuery(){
	//	realName = "abc";
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
		//	System.out.println();
			//条件
			Map condition = new HashMap();
			//condition.put("scheduleId",  "".equals(scheduleId)?"":"%"+scheduleId+"%");
			//condition.put("executer",  "".equals(executer)?"":"%"+executer+"%");
			//condition.put("title",  "".equals(title)?"":"%"+title+"%");
			condition.put("starttime","".equals(starttime)?"":starttime);
			condition.put("endtime","".equals(endtime)?"":endtime);
			//condition.put("rangetime",  "".equals(rangetime)?"":"%"+rangetime+"%");
			//condition.put("content",  "".equals(content)?"":"%"+content+"%");
			//condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userid = user.getUserId();
				userRole = user.getTitle();
				if(userRole.indexOf("am5") < 0){
					condition.put("executer", userid);
				}
			}
			int record = scheduleService.getCountSchedules(condition);
			List lst =scheduleService.getSchedules(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			scheduleList = pg.getResult();
			this.getRequest().setAttribute("schedules", scheduleList);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	/**
	 * 修改查询
	 * @return
	 * @author luqj
	 */
	public String doUpdateInit(){
		try {
			schedule =scheduleService.getScheduleById(scheduleId);
		//	realName = baseUserService.getBaseUserById(schedule.getExecuter()).getName();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	/**
	 * 修改日程
	 * @return
	 */
	public String doUpdate(){
		try {
			schedule.setRangetime(new Date());//当前系统
			scheduleService.modifySchedule(schedule);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询日程
	 * @return
	 * @author luqj
	 */
	public String doView(){
		try {
			schedule =scheduleService.getScheduleById(scheduleId);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doDelete(){
		try {
			scheduleService.removeScheduleById(scheduleId);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userid = user.getUserId();
				realName = user.getName();
			}
			readonly = false;
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

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public void setScheduleId_q(String scheduleId_q){
		this.scheduleId_q=scheduleId_q;
	}
	public String getScheduleId_q(){
		return scheduleId_q;
	}

	public void setScheduleId(String scheduleId){
		this.scheduleId=scheduleId;
	}
	public String getScheduleId(){
		return scheduleId;
	}

	public void setExecuter(String executer){
		this.executer=executer;
	}
	public String getExecuter(){
		return executer;
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

	public void setRangetime(Date rangetime){
		this.rangetime=rangetime;
	}
	public Date getRangetime(){
		return rangetime;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setScheduleService(ScheduleService scheduleService){
		this.scheduleService=scheduleService;
	}
	public ScheduleService getScheduleService(){
		return scheduleService;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}
	
}
