package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.MeetingRoom;

/**
 * 会议室service接口
 */
public interface MeetingRoomService {

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
	public List getMeetingRooms(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询会议室数量
	 * @author SY
	 * @time  
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountMeetingRooms(Map condition)throws Exception;

	/**
	 * 根据ID查询会议室
	 * @author SY
	 * @time  
	 * @return 返回会议室实体
	 * @throws Exception
	 */
	public MeetingRoom getMeetingRoomById(String id)throws Exception;

	/**
	 * 删除会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeMeetingRoomById(String id)throws Exception;

	/**
	 * 添加会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveMeetingRoom(MeetingRoom meetingRoom)throws Exception;

	/**
	 * 修改会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifyMeetingRoom(MeetingRoom meetingRoom)throws Exception;
	
	/**
	 * ycr 2013-12-18 根据会议id查询发言记录
	 * @param meetingId
	 * @return
	 * @throws Exception
	 */
	public List getMeetingRoomsByMeetingId(String meetingId)throws Exception;

	/**
	 * ycr 2013-12-19 根据会议id删除发言记录
	 * @param meetingId
	 * @throws Exception
	 */
	public void removeMeetingRoomByMeetingId(String meetingId)throws Exception;

}
