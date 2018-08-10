package runix.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import runix.persistent.model.Attendance;

public interface AttendanceService {

	public List getAttendances(Map condition,int offset,int limit)throws Exception;

	public int getCountAttendances(Map condition)throws Exception;

	public Attendance getAttendanceById(String id)throws Exception;

	public void removeAttendanceById(String id)throws Exception;

	public void saveAttendance(Attendance attendance)throws Exception;

	public void modifyAttendance(Attendance attendance)throws Exception;

	/**
	 * ycr 2013-12-25 查找当天是否已经生成每个员工的考情记录
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List getAttendancesByDate(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-25 根据用户id查找当天的考情记录
	 * @param userId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Attendance getAttendanceByUserIdandDate(String userId,String date)throws Exception;
	
	/**
	 * ycr 2013-12-25 根据用户id查询当天是否已签到
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int checkSignin(String userId)throws Exception;
	
	/**
	 * ycr 2013-12-25 考情统计查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getAttendancesCount(Map condition,int offset,int limit)throws Exception;

	public int getCountAttendancesCount(Map condition)throws Exception;

}
