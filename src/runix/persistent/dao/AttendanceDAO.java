package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Attendance;

public interface AttendanceDAO {

	public List queryAttendances(Map condition,int offset,int limit)throws Exception;

	public int queryCountAttendances(Map condition)throws Exception;

	public Attendance queryAttendanceById(String id)throws Exception;

	public void execDeleteAttendanceById(String id)throws Exception;

	public void execInsertAttendance(Attendance attendance)throws Exception;

	public void execUpdateAttendance(Attendance attendance)throws Exception;

	/**
	 * ycr 2013-12-25 查找当天是否已经生成每个员工的考情记录
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List queryAttendancesByDate(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-25 根据用户id查找当天的考情记录
	 * @param userId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Attendance queryAttendanceByUserIdandDate(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-25 根据用户id查询当天是否已签到
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int queryCheckSignin(Map condition)throws Exception;
	
	
	/**
	 * ycr 2013-12-25 考情统计查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryAttendancesCount(Map condition,int offset,int limit)throws Exception;

	public int queryCountAttendancesCount(Map condition)throws Exception;

	
}
