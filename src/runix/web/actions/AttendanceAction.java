package runix.web.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Attendance;
import runix.persistent.model.BaseUser;
import runix.persistent.model.SetAttend;
import runix.service.AttendanceService;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.DeptService;
import runix.service.SetAttendService;

public class AttendanceAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List attendanceList;
	private String attendId_q;
	private String attendId;
	private String name;
	private String dept;
	private String signinstatus;
	private String latestatus;
	private String leavestatus;
	private String vacationstatus;
	private String gooutstatus;
	private String biztripstatus;
	private String absentstatus;
	private String travelstatus;
	private Date attenddate;
	private String signintime;
	private String signouttime;
	private String flag;
	private String signinMsg;//签到信息
	private String signoutMsg;//签退信息
	private String starttime;//上班时间
	private String endtime;//下班时间
	private String stime;
	private String etime;
	private List depts;
	private DeptService deptService;
	private AttendanceService attendanceService;
	private BaseDataService baseDataService;
	private SetAttendService setAttendService;
	private BaseUserService baseUserService;
	
	public String doQueryM(){
		return SUCCESS;
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
			int record =0;
			List lst = null;
			Map condition = new HashMap();
			
			depts = deptService.getAllDepts();
			if(!"".equals(name)&&null!=name){
				condition.put("name", "%"+name+"%");
			}
			if(!"".equals(dept)&&null!=dept){
				condition.put("dept", dept);
			}
			if(!"".equals(stime)&&null!=stime){
				condition.put("stime", stime);
			}
			if(!"".equals(etime)&&null!=etime){
				condition.put("etime", etime);
			}
			if(("".equals(stime)||null==stime)&&("".equals(etime)||null==etime)){
				Date now = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM");
				condition.put("attenddate", sm.format(now));
			}
			if("1".equals(flag)){//考情统计查询
				 record = attendanceService.getCountAttendancesCount(condition);
				 lst =attendanceService.getAttendancesCount(condition, offset, limit);
			}else if("2".equals(flag)){
				 record = attendanceService.getCountAttendances(condition);
				 lst =attendanceService.getAttendances(condition, offset, limit);
			}

			
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			attendanceList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("1".equals(flag)){
			return "count";
		}else if("2".equals(flag)){
			return "manage";
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			Attendance attendance =attendanceService.getAttendanceById(attendId_q);
			attendId = attendance.getAttendId();
			name = baseUserService.getBaseUserById(attendance.getName()).getName();
			dept = deptService.getDeptById(attendance.getDept()).getDeptname();
		//	signinstatus = attendance.getSigninstatus();
		//	latestatus = attendance.getLatestatus();
		//	leavestatus = attendance.getLeavestatus();
			vacationstatus = attendance.getVacationstatus();
			gooutstatus = attendance.getGooutstatus();
			biztripstatus = attendance.getBiztripstatus();
			absentstatus = attendance.getAbsentstatus();
		//	travelstatus = attendance.getTravelstatus();
			attenddate = attendance.getAttenddate();
		//	signintime = attendance.getSignintime();
		//	signouttime = attendance.getSignouttime();
		//	flag = attendance.getFlag();
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
			Attendance attendance =attendanceService.getAttendanceById(attendId_q);
			//attendance.setAttendId(attendId);
			//attendance.setName(name);
			//attendance.setDept(dept);
			//attendance.setSigninstatus(signinstatus);
			//attendance.setLatestatus(latestatus);
			//attendance.setLeavestatus(leavestatus);
			attendance.setVacationstatus(vacationstatus);
			attendance.setGooutstatus(gooutstatus);
			attendance.setBiztripstatus(biztripstatus);
			attendance.setAbsentstatus(absentstatus);
			//attendance.setTravelstatus(travelstatus);
			//attendance.setAttenddate(attenddate);
			//attendance.setSignintime(signintime);
			//attendance.setSignouttime(signouttime);
			//attendance.setFlag(flag);
			attendanceService.modifyAttendance(attendance);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			attendanceService.removeAttendanceById(attendId_q);
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
			Attendance attendance = new Attendance();
			attendance.setAttendId(attendId);
			attendance.setName(name);
			attendance.setDept(dept);
			attendance.setSigninstatus(signinstatus);
			attendance.setLatestatus(latestatus);
			attendance.setLeavestatus(leavestatus);
			attendance.setVacationstatus(vacationstatus);
			attendance.setGooutstatus(gooutstatus);
			attendance.setBiztripstatus(biztripstatus);
			attendance.setAbsentstatus(absentstatus);
			attendance.setTravelstatus(travelstatus);
			attendance.setAttenddate(attenddate);
			attendance.setSignintime(signintime);
			attendance.setSignouttime(signouttime);
			attendance.setFlag(flag);
			attendanceService.saveAttendance(attendance);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doAttend(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				SetAttend setAttend = setAttendService.getSetAttend();
				if(setAttend!=null){
					Map condition = new HashMap();
					Date now = new Date();
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					Date now2 = sm.parse(sm.format(now));
					condition.put("attenddate",sm.format(now));
					List attendances = attendanceService.getAttendancesByDate(condition);//查找当天是否已经生成每个员工的考情记录
					if(attendances==null||attendances.size()==0){//如果没有则生成当天每个员工的考情列表
						List userList = baseUserService.getAllUsers();
						for(int i=0;i<userList.size();i++){
							Attendance attendance = new Attendance();
							//attendId = baseDataService.getSequence();
							//attendance.setAttendId(attendId);
							attendance.setName(((BaseUser)userList.get(i)).getUserId());
							attendance.setDept(((BaseUser)userList.get(i)).getDeptId());
							attendance.setSigninstatus("0");
							attendance.setLatestatus("0");
							attendance.setLeavestatus("0");
							attendance.setVacationstatus("0");
							attendance.setGooutstatus("0");
							attendance.setBiztripstatus("0");
							attendance.setAbsentstatus("1");
							attendance.setTravelstatus("0");
							attendance.setAttenddate(now2);
							attendance.setSignintime(signintime);
							attendance.setSignouttime(signouttime);
							attendance.setFlag(flag);
							attendanceService.saveAttendance(attendance);
						}
					}
					Attendance attendance =attendanceService.getAttendanceByUserIdandDate(user.getUserId(),sm.format(now));
					attendance.setSigninstatus("1");//已签到
					Date nowtime = new Date();
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String attendDate = sf.format(nowtime);
					signintime = attendDate.split(" ")[1];
					String signinhour = signintime.split(":")[0];//签到的小时
					String signinmin = signintime.split(":")[1];///签到的分钟
					starttime = setAttend.getStarttime();
					endtime = setAttend.getEndtime();
					String sethour = starttime.split(":")[0];//上班时间的小时
					String setmin = starttime.split(":")[1];//上班时间的分钟
				
					if(Integer.parseInt(signinhour)>Integer.parseInt(sethour)){//签到小时大于上班时间小时
						attendance.setLatestatus("1");//“1”表示迟到
						signinMsg = "今天你迟到了！";
					}else if(Integer.parseInt(signinhour)==Integer.parseInt(sethour)&&Integer.parseInt(signinmin)>Integer.parseInt(setmin)){
						attendance.setLatestatus("1");//“1”表示迟到
						signinMsg = "今天你迟到了！";
					}else{
						attendance.setLatestatus("0");//“0”表示未迟到
						signinMsg = "很好，今天你没迟到！";
					}
					if(!"".equals(setAttend.getAbsentlimit())&&null!=setAttend.getAbsentlimit()){
						String absentlimithour = setAttend.getAbsentlimit().split(":")[0];//旷工限定时间小时
						String absentlimitmin = setAttend.getAbsentlimit().split(":")[1];//旷工限定时间分钟
						if(Integer.parseInt(signinhour)>Integer.parseInt(absentlimithour)){
							attendance.setAbsentstatus("1");//旷工
							attendance.setLatestatus("0");
						}else if(Integer.parseInt(signinhour)==Integer.parseInt(absentlimithour)&&Integer.parseInt(signinmin)>Integer.parseInt(absentlimitmin)){
							attendance.setAbsentstatus("1");//旷工
							attendance.setLatestatus("0");
						}else{
							attendance.setAbsentstatus("0");//未旷工
						}
					}else{
						attendance.setAbsentstatus("0");//未旷工
					}
					
					attendance.setSignintime(signintime);
					attendanceService.modifyAttendance(attendance);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doLeave(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				SetAttend setAttend = setAttendService.getSetAttend();
				if(setAttend!=null){
					Date now = new Date();
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					Attendance attendance =attendanceService.getAttendanceByUserIdandDate(user.getUserId(),sm.format(now));
					Date nowtime = new Date();
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String leaveDate = sf.format(nowtime);
					signouttime = leaveDate.split(" ")[1];
					String signouthour = signouttime.split(":")[0];//签退的小时
					String signoutmin = signouttime.split(":")[1];//签退的分钟
					starttime = setAttend.getStarttime();
					endtime = setAttend.getEndtime();
					String sethour = endtime.split(":")[0];//下班时间的小时
					String setmin = endtime.split(":")[1];//下班时间的分钟
					if(Integer.parseInt(signouthour)<Integer.parseInt(sethour)){//签退小时小于下班时间小时
						if("1".equals(attendance.getAbsentstatus())){
							attendance.setLeavestatus("0");
							signoutMsg = "今天你已算作旷工！";
						}else{
							attendance.setLeavestatus("1");//“1”表示早退
							signoutMsg = "今天你早退了！友情提醒：系统将以当天最后一次签退时间为准！";
						}
						
					}else if(Integer.parseInt(signouthour)==Integer.parseInt(sethour)&&Integer.parseInt(signoutmin)>Integer.parseInt(setmin)){
						if("1".equals(attendance.getAbsentstatus())){
							attendance.setLeavestatus("0");
							signoutMsg = "今天你已算作旷工！";
						}else{
							attendance.setLeavestatus("1");//“1”表示早退
							signoutMsg = "今天你早退了！友情提醒：系统将以当天最后一次签退时间为准！";
						}
					}else{
						if("1".equals(attendance.getAbsentstatus())){
							attendance.setLeavestatus("0");
							signoutMsg = "今天你已算作旷工！";
						}else{
							attendance.setLeavestatus("0");//“0”表示未早退
							signoutMsg = "下班时间到了，今天你没早退！";
						}
					}
					attendance.setSignouttime(signouttime);
					attendanceService.modifyAttendance(attendance);
				}
			}
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

	public void setAttendanceList(List attendanceList){
		this.attendanceList=attendanceList;
	}
	public List getAttendanceList(){
		return attendanceList;
	}

	public void setAttendId_q(String attendId_q){
		this.attendId_q=attendId_q;
	}
	public String getAttendId_q(){
		return attendId_q;
	}

	public void setAttendId(String attendId){
		this.attendId=attendId;
	}
	public String getAttendId(){
		return attendId;
	}

	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}

	public void setSigninstatus(String signinstatus){
		this.signinstatus=signinstatus;
	}
	public String getSigninstatus(){
		return signinstatus;
	}

	public void setLatestatus(String latestatus){
		this.latestatus=latestatus;
	}
	public String getLatestatus(){
		return latestatus;
	}

	public void setLeavestatus(String leavestatus){
		this.leavestatus=leavestatus;
	}
	public String getLeavestatus(){
		return leavestatus;
	}

	public void setVacationstatus(String vacationstatus){
		this.vacationstatus=vacationstatus;
	}
	public String getVacationstatus(){
		return vacationstatus;
	}

	public void setGooutstatus(String gooutstatus){
		this.gooutstatus=gooutstatus;
	}
	public String getGooutstatus(){
		return gooutstatus;
	}

	public void setBiztripstatus(String biztripstatus){
		this.biztripstatus=biztripstatus;
	}
	public String getBiztripstatus(){
		return biztripstatus;
	}

	public void setAbsentstatus(String absentstatus){
		this.absentstatus=absentstatus;
	}
	public String getAbsentstatus(){
		return absentstatus;
	}

	public void setTravelstatus(String travelstatus){
		this.travelstatus=travelstatus;
	}
	public String getTravelstatus(){
		return travelstatus;
	}

	public void setAttenddate(Date attenddate){
		this.attenddate=attenddate;
	}
	public Date getAttenddate(){
		return attenddate;
	}

	public void setSignintime(String signintime){
		this.signintime=signintime;
	}
	public String getSignintime(){
		return signintime;
	}

	public void setSignouttime(String signouttime){
		this.signouttime=signouttime;
	}
	public String getSignouttime(){
		return signouttime;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setAttendanceService(AttendanceService attendanceService){
		this.attendanceService=attendanceService;
	}
	public AttendanceService getAttendanceService(){
		return attendanceService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public SetAttendService getSetAttendService() {
		return setAttendService;
	}

	public void setSetAttendService(SetAttendService setAttendService) {
		this.setAttendService = setAttendService;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public String getSigninMsg() {
		return signinMsg;
	}

	public void setSigninMsg(String signinMsg) {
		this.signinMsg = signinMsg;
	}

	public String getSignoutMsg() {
		return signoutMsg;
	}

	public void setSignoutMsg(String signoutMsg) {
		this.signoutMsg = signoutMsg;
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

	public List getDepts() {
		return depts;
	}

	public void setDepts(List depts) {
		this.depts = depts;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	

}
