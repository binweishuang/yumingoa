package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.MeetingRoom;

/**
 * 会议室DAO接口
 */
public interface MeetingRoomDAO {

	/**
	 * 查询会议室列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return 
	 * @throws Exception
	 */
	public List queryMeetingRooms(Map condition,int offset,int limit)throws Exception;

	/**
	 * 查询会议室数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int queryCountMeetingRooms(Map condition)throws Exception;

	/**
	 * 根据ID查询会议室
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public MeetingRoom queryMeetingRoomById(String id)throws Exception;

	/**
	 * 删除会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execDeleteMeetingRoomById(String id)throws Exception;

	/**
	 * 添加会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execInsertMeetingRoom(MeetingRoom meetingRoom)throws Exception;

	/**
	 * 修改会议室
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execUpdateMeetingRoom(MeetingRoom meetingRoom)throws Exception;
	
	/**
	 * ycr 2013-12-18 根据会议id查询发言记录
	 * @param meetingId
	 * @return
	 * @throws Exception
	 */
	public List queryMeetingRoomsByMeetingId(String meetingId)throws Exception;


	public void execDeleteMeetingRoomByMeetingId(String meetingId)throws Exception;
}
