package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.MeetingRoomDAO;
import runix.persistent.model.MeetingRoom;

/**
 * 会议室DAO实现类
 */
public class MeetingRoomDAOImpl extends BaseDAO implements MeetingRoomDAO {

	/**
	 * 按条件查询会议室列表
	 * @author SY
	 * @time 
	 */
	public List queryMeetingRooms(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("MeetingRoom.queryMeetingRooms",condition,offset,limit);
	}

	/**
	 * 按条件查询会议室数量
	 * @author SY
	 * @time 
	 */
	public int queryCountMeetingRooms(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("MeetingRoom.queryCountMeetingRooms",condition);
	}

	/**
	 * 根据ID查询会议室   返回实体对象
	 * @author SY
	 * @time 
	 */
	public MeetingRoom queryMeetingRoomById(String id)throws Exception {
		return (MeetingRoom)getSqlMapClientTemplate().queryForObject("MeetingRoom.queryMeetingRoomById",id);
	}

	/**
	 * 删除会议室
	 * @author SY
	 * @time 
	 */
	public void execDeleteMeetingRoomById(String id)throws Exception {
		getSqlMapClientTemplate().delete("MeetingRoom.execDeleteMeetingRoomById",id);
	}

	/**
	 * 添加会议室
	 * @author SY
	 * @time 
	 */
	public void execInsertMeetingRoom(MeetingRoom meetingRoom)throws Exception {
		getSqlMapClientTemplate().insert("MeetingRoom.execInsertMeetingRoom",meetingRoom);
	}

	/**
	 * 修改会议室
	 * @author SY
	 * @time 
	 */
	public void execUpdateMeetingRoom(MeetingRoom meetingRoom)throws Exception {
		getSqlMapClientTemplate().update("MeetingRoom.execUpdateMeetingRoom",meetingRoom);
	}

	public List queryMeetingRoomsByMeetingId(String meetingId) throws Exception {
		return getSqlMapClientTemplate().queryForList("MeetingRoom.queryMeetingRoomsByMeetingId",meetingId);
	}

	public void execDeleteMeetingRoomByMeetingId(String meetingId)
			throws Exception {
		getSqlMapClientTemplate().delete("MeetingRoom.execDeleteMeetingRoomByMeetingId",meetingId);
	}


}
