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
import runix.persistent.model.Task;
import runix.persistent.model.UserTask;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.TaskService;

public class TaskAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List<Task> taskList;
	private List<Map<String,Object>> tasks;//下达任务集合
//	private List<Map<String,Object>> exeTasks;//执行任务集合
	private String taskId_q;
	private String taskId;
	private String title;
	private String standard;
	private String reportTo;
	private Date starttime;
	private Date endtime;
	private String timelimit;
	private String attachname;
	private String attachpath;
	private String executer;
	private String executerid;
	private Date exestarttime;
	private Date exeendtime;
	private String content;
	private String process;
	private  String totalprocess;
	private String completion; 
	private String flag;//用于标识是1：执行任务   2：下达任务
	private TaskService taskService;//任务service
	private BaseDataService baseDataService;//序列
	private BaseUserService baseUserService;//用户sevice
	private Task task;//任务
	private String attach;//上传附件路径
	private String flagTask;//任务类别标识	1:执行任务；2:下达任务；
	
	private String utId;
	private String utId_q;
	private UserTask userTask;
	private String taskcomplete;
	private String userRole;
	
	/**
	 * 任务下达(添加)页面
	 * @return
	 * @author luqj
	 * @time 2013-12-3 11:50:57
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
	 * 任务下达
	 * @return
	 * @author luqj
	 * @time 2013-12-3 11:51:39
	 */
	public String doInsert(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Task task = new Task();
			//	task.setTaskId(baseDataService.getSequence());
				task.setTitle(title);
				task.setReportTo(reportTo);
				task.setContent(content);
				task.setStarttime(starttime);
				task.setEndtime(endtime);
				task.setExecuter(executer);
				if(!"".equals(executerid)&&null!=executerid){
					String exeIds[] = executerid.split(",");
			//		String exeid = "";
					for(int i=0;i<exeIds.length;i++){
//						if("".equals(exeid)){
//							exeid ="p"+exeIds[i]+"p";
//						}else{
//							exeid = exeid+",p"+exeIds[i]+"p";
//						}
						UserTask ut = new UserTask();
						//ut.setUtId(baseDataService.getSequence());
						ut.setTaskId(task.getTaskId());
						ut.setUserId(exeIds[i]);
						ut.setProcess("0");
						ut.setComletetion("0");
						taskService.saveUserTask(ut);
					}
		//			executerid = exeid;
				}
				task.setExecuterid(executerid);
				task.setProcess("0");
				task.setTimelimit(timelimit);
				task.setTotalprocess("0");
				task.setArranger(user.getUserId());
				task.setAttachname(attachname);
				task.setAttachpath(attachpath);
				task.setCompletion("0");
				taskService.saveTask(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doNew(){
		return SUCCESS;
	}
	
	public String doQueryM(){
		BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		userRole = user.getTitle();
		readonly = false;
		if(userRole.indexOf("am6") < 0){
			readonly = true;
		}
		ServletActionContext.getRequest().setAttribute("readonly", readonly);
		return SUCCESS;
	}
	/**
	 * 任务查询列表
	 * @return
	 */
	public String doQuery(){
		
//		if(flagTask==null){
//			flagTask = "1";
//		}
//		if(flagTask.equals("0")){//下达任务
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
				if(!"".equals(reportTo) && null != reportTo){//汇报对象
					if(!reportTo.equals(new String(reportTo.getBytes("utf-8")))){
						reportTo = new String(reportTo.getBytes("iso-8859-1"),"utf-8");
						condition.put("reportTo","%"+reportTo+"%");
					}
				}
				if(!"".equals(executer) && null != executer){//执行人
					if(!executer.equals(new String(executer.getBytes("utf-8")))){
						executer = new String(executer.getBytes("iso-8859-1"),"utf-8");
						condition.put("executer","%"+executer+"%");
					}
				}
				
				if(!"".equals(completion)&&null!=completion){//完成情况
					condition.put("completion",completion);
				}
				BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
				int record = 0;
				List lst = null;
				readonly = false;
				if("1".equals(flag)){//执行任务
					condition.put("exeId",user.getUserId());
					record = taskService.getCountTasks1(condition);
					 lst = taskService.getTasks1(condition, offset, limit);
				}else if("2".equals(flag)){//下达任务
					userRole = user.getTitle();
					if(userRole.indexOf("am6") < 0){
						readonly = true;
						condition.put("repId",user.getUserId());
					}else{
						condition.put("arranger",user.getUserId());
					}
					record = taskService.getCountTasks(condition);
					 lst = taskService.getTasks(condition, offset, limit);
				}
				Pageable pg = null;
				try {
					pg = new IbatisPage(lst, record, currentPageInt, pages);
				} catch (PageException e) {
					pg = null;
				}
				ServletActionContext.getRequest().setAttribute("pages", pg);
				ServletActionContext.getRequest().setAttribute("readonly", readonly);
				tasks = pg.getResult();
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("please check! there is Exception");
			}
//		}else{//执行任务
//			try {
//				int currentPageInt = 1;
//				String strCurrentPage = currentPage;
//				if (strCurrentPage != null && !"".equals(strCurrentPage)) {
//					try {
//						currentPageInt = Integer.parseInt(strCurrentPage);
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
//						this.addActionError("please check! there is Exception");
//					}
//				}
//				int offset = (currentPageInt-1) * pages;
//				int limit = pages;
//	
//				Map condition = new HashMap();
//				condition.put("reportTo",  "".equals(reportTo)?"":reportTo);//汇报对象
//				executer = "1253";//获取当前登陆用户的信息
//				condition.put("executer",  "".equals(executer)?"":executer);//执行人
//				condition.put("flag",  "".equals(flag)?"":flag);//完成情况
//	
//				int record = taskService.getCountTasks(condition);
//				List<Map<String,Object>> lst = taskService.getTasks(condition, offset, limit);
//	
//				Pageable pg = null;
//				try {
//					pg = new IbatisPage(lst, record, currentPageInt, pages);
//				} catch (PageException e) {
//					pg = null;
//				}
//				ServletActionContext.getRequest().setAttribute("pages", pg);
//				exeTasks = pg.getResult();
//			} catch (Exception e) {
//				e.printStackTrace();
//				this.addActionError("please check! there is Exception");
//			}
//		}
			if("1".equals(flag)){
				return "exetask";
			}else{
				return "arrtask";
			}
		
	}
	
	/**
	 * 执行任务查询
	 * @return
	 */
	public String doExeTask(){
		try {
			task = taskService.getTaskById(taskId);
			userTask = taskService.getUserTaskById(utId);
			//attachpath = task.getAttachpath().replace("\\", "/");
			//attachpath = attachpath.substring(attachpath.lastIndexOf("runixoa"));
			//System.out.println(attachpath);
			reportTo = baseUserService.getBaseUserById(task.getReportTo()).getName();
			//executer = baseUserService.getBaseUserById(task.getExecuter()).getName();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return "success";
	}
	
	public String doExe(){
		try {
			userTask = taskService.getUserTaskById(utId);
			if(process.length()>1&&process.charAt(0)=='0'){
				for(int i=0;i<process.length();i++){
					if(process.charAt(i)==0){
						process = process.substring(i+1);
					}else{
						break;
					}
				}
				
			}
			userTask.setProcess(process);
			if("100".equals(process)){
				userTask.setComletetion("1");
			}
			userTask.setTaskcomplete(taskcomplete);
			taskService.modifyUserTask(userTask);
			totalprocess = taskService.checkComplete(taskId);
			task = taskService.getTaskById(taskId);
			task.setTotalprocess(totalprocess);
			if("100".equals(totalprocess)){
				task.setCompletion("1");
			}
			taskService.modifyTask(task);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return "success";
	}
	
	/**
	 * 任务修改查询
	 * @author luqj
	 * @time 2013-12-5 11:56:45
	 * @return
	 */
	public String doUpdateTask(){
		try {
			task = taskService.getTaskById(taskId);
			reportTo = baseUserService.getBaseUserById(task.getReportTo()).getName();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改任务
	 * @return
	 */
	public String doUpdate(){
		try {
			task = taskService.getTaskById(taskId);
			task.setTitle(title);
			task.setReportTo(reportTo);
			task.setContent(content);
			task.setStarttime(starttime);
			task.setEndtime(endtime);
			task.setExecuter(executer);
			task.setExecuterid(executerid);
			task.setTimelimit(timelimit);
			task.setAttachname(attachname);
			task.setAttachpath(attachpath);
			taskService.modifyTask(task);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			taskService.removeTaskById(taskId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doView(){
		try {
			task = taskService.getTaskById(taskId_q);
			userTask = taskService.getUserTaskById(utId_q);
			reportTo = baseUserService.getBaseUserById(task.getReportTo()).getName();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doViewM(){
		try {
			task = taskService.getTaskById(taskId_q);
			List userTasks = taskService.getUserTasks(taskId_q);
			taskcomplete = "";
			String tc = "";
			for(int i=0;i<userTasks.size();i++){
				userTask = (UserTask)userTasks.get(i);
				if(userTask.getTaskcomplete()!=null){
					tc = userTask.getTaskcomplete();
				}else{
					tc = "";
				}
				taskcomplete =taskcomplete+"<p>"+ userTask.getUserId()+" 完成进度："+userTask.getProcess()+"% "+" 完成情况：\n"+tc+"</p>";
			}
			reportTo = baseUserService.getBaseUserById(task.getReportTo()).getName();
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

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public void setTaskId_q(String taskId_q){
		this.taskId_q=taskId_q;
	}
	public String getTaskId_q(){
		return taskId_q;
	}

	public void setTaskId(String taskId){
		this.taskId=taskId;
	}
	public String getTaskId(){
		return taskId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setStandard(String standard){
		this.standard=standard;
	}
	public String getStandard(){
		return standard;
	}

	public void setReportTo(String reportTo){
		this.reportTo=reportTo;
	}
	public String getReportTo(){
		return reportTo;
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

	public void setTimelimit(String timelimit){
		this.timelimit=timelimit;
	}
	public String getTimelimit(){
		return timelimit;
	}

	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}

	public void setAttachpath(String attachpath){
		this.attachpath=attachpath;
	}
	public String getAttachpath(){
		return attachpath;
	}

	public void setExecuter(String executer){
		this.executer=executer;
	}
	public String getExecuter(){
		return executer;
	}

	public void setExestarttime(Date exestarttime){
		this.exestarttime=exestarttime;
	}
	public Date getExestarttime(){
		return exestarttime;
	}

	public void setExeendtime(Date exeendtime){
		this.exeendtime=exeendtime;
	}
	public Date getExeendtime(){
		return exeendtime;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setProcess(String process){
		this.process=process;
	}
	public String getProcess(){
		return process;
	}

	public void setCompletion(String completion){
		this.completion=completion;
	}
	public String getCompletion(){
		return completion;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setTaskService(TaskService taskService){
		this.taskService=taskService;
	}
	public TaskService getTaskService(){
		return taskService;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public List<Map<String, Object>> getTasks() {
		return tasks;
	}

	public void setTasks(List<Map<String, Object>> tasks) {
		this.tasks = tasks;
	}


	public String getFlagTask() {
		return flagTask;
	}

	public void setFlagTask(String flagTask) {
		this.flagTask = flagTask;
	}

	public String getTotalprocess() {
		return totalprocess;
	}

	public void setTotalprocess(String totalprocess) {
		this.totalprocess = totalprocess;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public String getExecuterid() {
		return executerid;
	}

	public void setExecuterid(String executerid) {
		this.executerid = executerid;
	}

	public String getUtId() {
		return utId;
	}

	public void setUtId(String utId) {
		this.utId = utId;
	}

	public UserTask getUserTask() {
		return userTask;
	}

	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}

	public String getTaskcomplete() {
		return taskcomplete;
	}

	public void setTaskcomplete(String taskcomplete) {
		this.taskcomplete = taskcomplete;
	}

	public String getUtId_q() {
		return utId_q;
	}

	public void setUtId_q(String utId_q) {
		this.utId_q = utId_q;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
