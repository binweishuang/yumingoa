package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.MeetingRoomDAO;
import runix.service.MeetingRoomService;
import runix.persistent.model.MeetingRoom;

/**
 * 会议室service实现类
 */
public class MeetingRoomServiceImpl  implements MeetingRoomService {
	private MeetingRoomDAO meetingRoomDAO;

	/**
	 * 按条件查询会议室列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getMeetingRooms(Map condition,int offset,int limit)throws Exception {
		return meetingRoomDAO.queryMeetingRooms(condition,offset,limit);
	}

	/**
	 * 按条件查询会议室数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountMeetingRooms(Map condition)throws Exception {
		return meetingRoomDAO.queryCountMeetingRooms(condition);
	}

	/**
	 * 根据ID查询会议室
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public MeetingRoom getMeetingRoomById(String id)throws Exception {
		return meetingRoomDAO.queryMeetingRoomById(id);
	}

	/**
	 * 删除会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeMeetingRoomById(String id)throws Exception {
		 meetingRoomDAO.execDeleteMeetingRoomById(id);
	}

	/**
	 * 添加会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveMeetingRoom(MeetingRoom meetingRoom)throws Exception {
		 meetingRoomDAO.execInsertMeetingRoom(meetingRoom);
	}

	/**
	 * 修改会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifyMeetingRoom(MeetingRoom meetingRoom)throws Exception {
		 meetingRoomDAO.execUpdateMeetingRoom(meetingRoom);
	}


	public void setMeetingRoomDAO(MeetingRoomDAO meetingRoomDAO){
		this.meetingRoomDAO=meetingRoomDAO;
	}

	public List getMeetingRoomsByMeetingId(String meetingId) throws Exception {
		return meetingRoomDAO.queryMeetingRoomsByMeetingId(meetingId);
	}

	public void removeMeetingRoomByMeetingId(String meetingId) throws Exception {
		 meetingRoomDAO.execDeleteMeetingRoomByMeetingId(meetingId);
	}

}
