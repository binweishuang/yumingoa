package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.MeetingRoom;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.MeetingRoomService;

/**
 * 会议室Action
 * @author SY
 */
public class MeetingRoomAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页数
	private int pages=15;//每页显示记录数
	private List meetingRoomList;//会议室列表
	private String roomId_q;
	private String roomId;
	private String spokesman;//发言人
	private String content;//发言内容
	private Date speaktime;//发言时间
	private String netmeetingId;//关联会议
	private String flag;
	private MeetingRoomService meetingRoomService;
	private BaseDataService baseDataService;

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
			condition.put("roomId",  "".equals(roomId)?"":"%"+roomId+"%");
			condition.put("spokesman",  "".equals(spokesman)?"":"%"+spokesman+"%");
			condition.put("content",  "".equals(content)?"":"%"+content+"%");
			condition.put("speaktime",  "".equals(speaktime)?"":"%"+speaktime+"%");
			condition.put("netmeetingId",  "".equals(netmeetingId)?"":"%"+netmeetingId+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = meetingRoomService.getCountMeetingRooms(condition);
			List lst =meetingRoomService.getMeetingRooms(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			meetingRoomList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			MeetingRoom meetingRoom =meetingRoomService.getMeetingRoomById(roomId_q);
			roomId = meetingRoom.getRoomId();
			spokesman = meetingRoom.getSpokesman();
			content = meetingRoom.getContent();
			speaktime = meetingRoom.getSpeaktime();
			netmeetingId = meetingRoom.getNetmeetingId();
			flag = meetingRoom.getFlag();
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
			MeetingRoom meetingRoom =meetingRoomService.getMeetingRoomById(roomId_q);
			meetingRoom.setRoomId(roomId);
			meetingRoom.setSpokesman(spokesman);
			meetingRoom.setContent(content);
			meetingRoom.setSpeaktime(speaktime);
			meetingRoom.setNetmeetingId(netmeetingId);
			meetingRoom.setFlag(flag);
			meetingRoomService.modifyMeetingRoom(meetingRoom);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			meetingRoomService.removeMeetingRoomById(roomId_q);
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
			MeetingRoom meetingRoom = new MeetingRoom();
			//roomId = baseDataService.getSequence();
			//meetingRoom.setRoomId(roomId);
			meetingRoom.setSpokesman(spokesman);
			meetingRoom.setContent(content);
			meetingRoom.setSpeaktime(speaktime);
			meetingRoom.setNetmeetingId(netmeetingId);
			meetingRoom.setFlag(flag);
			meetingRoomService.saveMeetingRoom(meetingRoom);
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

	public void setMeetingRoomList(List meetingRoomList){
		this.meetingRoomList=meetingRoomList;
	}
	public List getMeetingRoomList(){
		return meetingRoomList;
	}

	public void setRoomId_q(String roomId_q){
		this.roomId_q=roomId_q;
	}
	public String getRoomId_q(){
		return roomId_q;
	}

	public void setRoomId(String roomId){
		this.roomId=roomId;
	}
	public String getRoomId(){
		return roomId;
	}

	public void setSpokesman(String spokesman){
		this.spokesman=spokesman;
	}
	public String getSpokesman(){
		return spokesman;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setSpeaktime(Date speaktime){
		this.speaktime=speaktime;
	}
	public Date getSpeaktime(){
		return speaktime;
	}

	public void setNetmeetingId(String netmeetingId){
		this.netmeetingId=netmeetingId;
	}
	public String getNetmeetingId(){
		return netmeetingId;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setMeetingRoomService(MeetingRoomService meetingRoomService){
		this.meetingRoomService=meetingRoomService;
	}
	public MeetingRoomService getMeetingRoomService(){
		return meetingRoomService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

}
