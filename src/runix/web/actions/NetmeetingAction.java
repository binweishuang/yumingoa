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
import runix.persistent.model.MeetingRoom;
import runix.persistent.model.Netmeeting;
import runix.service.BaseDataService;
import runix.service.MeetingRoomService;
import runix.service.NetmeetingService;

/**
 * 网络会议
 * @author SY
 */
public class NetmeetingAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页数
	private int pages=15;//每页显示记录数
	private List netmeetingList;//网络会议列表
	private String netmeetingId_q;
	private String netmeetingId;
	private String title;//会议标题
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private Date meetingdate;//会议日期
	private String status;//会议状态
	private String attendee;//出席者
	private String sponsor;//发起人
	private String attendeename;//出席者姓名
	private String flag;
	private Date nowtime;
	private String name;//登录用户姓名
	
	private String act;//标识是新增或不用新增             1：新增，2：不用新增
	
	private String roomId;
	private String spokesman;//发言人
	private String content;//发言内容
	private Date speaktime;//发言时间
	
	private List meetingRoomList;//会议发言列表
	private NetmeetingService netmeetingService;
	private BaseDataService baseDataService;
	private MeetingRoomService meetingRoomService;
	
	private String userRole;

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
			if(!"".equals(title)&&null!=title){
				condition.put("title", "%"+title+"%");
			}
			if(null!=starttime){
				condition.put("starttime", starttime);
			}
			if(null!=endtime){
				condition.put("endtime",endtime);
			}
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				condition.put("sponsor", user.getUserId());
				condition.put("attendee", "%p"+user.getUserId()+"p%");
				name = user.getName();
				if(userRole.indexOf("fm4") < 0){
					readonly = true;
				}
			}
			int record = netmeetingService.getCountNetmeetings(condition);
			List lst =netmeetingService.getNetmeetings(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			netmeetingList = pg.getResult();
			nowtime = new Date();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			Netmeeting netmeeting =netmeetingService.getNetmeetingById(netmeetingId_q);
			netmeetingId = netmeeting.getNetmeetingId();
			title = netmeeting.getTitle();
			starttime = netmeeting.getStarttime();
			endtime = netmeeting.getEndtime();
			meetingdate = netmeeting.getMeetingdate();
			status = netmeeting.getStatus();
			attendee = netmeeting.getAttendee();
			String personIds[] = attendee.split(",");
			String personid = "";
			for(int i=0;i<personIds.length;i++){
				if("".equals(personid)){
					personid = personIds[i].replaceAll("p", "");
				}else{
					personid = personid+","+personIds[i].replaceAll("p", "");
				}
			}
			attendee = personid;
			attendeename = netmeeting.getAttendeename();
			sponsor = netmeeting.getSponsor();
			flag = netmeeting.getFlag();
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
			Netmeeting netmeeting =netmeetingService.getNetmeetingById(netmeetingId_q);
			//netmeeting.setNetmeetingId(netmeetingId);
			netmeeting.setTitle(title);
			netmeeting.setStarttime(starttime);
			netmeeting.setEndtime(endtime);
			netmeeting.setMeetingdate(meetingdate);
			netmeeting.setStatus(status);
			String attendees[] = attendee.split(",");
			String peopleid="";
			for(int i=0;i<attendees.length;i++){
				if("".equals(peopleid)){
					peopleid = "p"+attendees[i]+"p";
				}else{
					peopleid = peopleid+",p"+attendees[i]+"p";
				}
			}
			netmeeting.setAttendee(peopleid);
			netmeeting.setAttendeename(attendeename);
		//	netmeeting.setSponsor(sponsor);
			netmeeting.setFlag(flag);
			netmeetingService.modifyNetmeeting(netmeeting);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			meetingRoomService.removeMeetingRoomByMeetingId(netmeetingId_q);
			netmeetingService.removeNetmeetingById(netmeetingId_q);
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
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Netmeeting netmeeting = new Netmeeting();
				//netmeetingId = baseDataService.getSequence();
				//netmeeting.setNetmeetingId(netmeetingId);
				netmeeting.setTitle(title);
				netmeeting.setStarttime(starttime);
				netmeeting.setEndtime(endtime);
				netmeeting.setMeetingdate(meetingdate);
				netmeeting.setStatus(status);
				String attendees[] = attendee.split(",");
				String peopleid="";
				for(int i=0;i<attendees.length;i++){
					if("".equals(peopleid)){
						peopleid = "p"+attendees[i]+"p";
					}else{
						peopleid = peopleid+",p"+attendees[i]+"p";
					}
				}
				netmeeting.setAttendee(peopleid);
				netmeeting.setAttendeename(attendeename);
				netmeeting.setSponsor(user.getUserId());
				netmeeting.setFlag(flag);
				netmeetingService.saveNetmeeting(netmeeting);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doMeeting(){
		try {
			Netmeeting netmeeting =netmeetingService.getNetmeetingById(netmeetingId_q);
			netmeetingId = netmeeting.getNetmeetingId();
			title= netmeeting.getTitle();
			starttime = netmeeting.getStarttime();
			endtime= netmeeting.getEndtime();
		//	readonly = false;
			meetingRoomList = meetingRoomService.getMeetingRoomsByMeetingId(netmeetingId_q);
		//	ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doSpoke(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Netmeeting netmeeting =netmeetingService.getNetmeetingById(netmeetingId_q);
				netmeetingId = netmeeting.getNetmeetingId();
				title= netmeeting.getTitle();
				starttime = netmeeting.getStarttime();
				endtime= netmeeting.getEndtime();
				if("1".equals(act)){
					MeetingRoom meetingRoom = new MeetingRoom();
					//roomId = baseDataService.getSequence();
					//meetingRoom.setRoomId(roomId);
					meetingRoom.setContent(content);
					meetingRoom.setNetmeetingId(netmeetingId);
					meetingRoom.setSpeaktime(new Date());
					meetingRoom.setFlag(flag);
					meetingRoom.setSpokesman(user.getName());
					meetingRoomService.saveMeetingRoom(meetingRoom);
				}
				
			//	readonly = false;
				meetingRoomList = meetingRoomService.getMeetingRoomsByMeetingId(netmeetingId_q);
			//	ServletActionContext.getRequest().setAttribute("readonly",readonly);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Netmeeting netmeeting =netmeetingService.getNetmeetingById(netmeetingId_q);
				netmeetingId = netmeeting.getNetmeetingId();
				title= netmeeting.getTitle();
				starttime = netmeeting.getStarttime();
				endtime= netmeeting.getEndtime();
				meetingRoomList = meetingRoomService.getMeetingRoomsByMeetingId(netmeetingId_q);
//				
//				int currentPageInt = 1;
//				if(currentPage != null && !"".equals(currentPage) && currentPage.equals("currentPage")){
//					currentPage = null;
//				}
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
//				condition.put("netmeetingId", netmeetingId_q);
//				int record = meetingRoomService.getCountMeetingRooms(condition);
//				List lst = meetingRoomService.getMeetingRooms(condition, offset, limit);
//
//				Pageable pg = null;
//				try {
//					pg = new IbatisPage(lst, record, currentPageInt, pages);
//				} catch (PageException e) {
//					pg = null;
//				}
//				ServletActionContext.getRequest().setAttribute("pages", pg);
//				meetingRoomList = pg.getResult();
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

	public void setNetmeetingList(List netmeetingList){
		this.netmeetingList=netmeetingList;
	}
	public List getNetmeetingList(){
		return netmeetingList;
	}

	public void setNetmeetingId_q(String netmeetingId_q){
		this.netmeetingId_q=netmeetingId_q;
	}
	public String getNetmeetingId_q(){
		return netmeetingId_q;
	}

	public void setNetmeetingId(String netmeetingId){
		this.netmeetingId=netmeetingId;
	}
	public String getNetmeetingId(){
		return netmeetingId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}


	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setMeetingdate(Date meetingdate){
		this.meetingdate=meetingdate;
	}
	public Date getMeetingdate(){
		return meetingdate;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setAttendee(String attendee){
		this.attendee=attendee;
	}
	public String getAttendee(){
		return attendee;
	}

	public void setSponsor(String sponsor){
		this.sponsor=sponsor;
	}
	public String getSponsor(){
		return sponsor;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setNetmeetingService(NetmeetingService netmeetingService){
		this.netmeetingService=netmeetingService;
	}
	public NetmeetingService getNetmeetingService(){
		return netmeetingService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public Date getNowtime() {
		return nowtime;
	}

	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
	}

	public String getAttendeename() {
		return attendeename;
	}

	public void setAttendeename(String attendeename) {
		this.attendeename = attendeename;
	}

	public List getMeetingRoomList() {
		return meetingRoomList;
	}

	public void setMeetingRoomList(List meetingRoomList) {
		this.meetingRoomList = meetingRoomList;
	}

	public MeetingRoomService getMeetingRoomService() {
		return meetingRoomService;
	}

	public void setMeetingRoomService(MeetingRoomService meetingRoomService) {
		this.meetingRoomService = meetingRoomService;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getSpokesman() {
		return spokesman;
	}

	public void setSpokesman(String spokesman) {
		this.spokesman = spokesman;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSpeaktime() {
		return speaktime;
	}

	public void setSpeaktime(Date speaktime) {
		this.speaktime = speaktime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	
	

}
