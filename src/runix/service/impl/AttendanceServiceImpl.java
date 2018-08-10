package runix.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import runix.persistent.dao.AttendanceDAO;
import runix.persistent.model.Attendance;
import runix.service.AttendanceService;

public class AttendanceServiceImpl  implements AttendanceService {
	private AttendanceDAO attendanceDAO;


	public List getAttendances(Map condition,int offset,int limit)throws Exception {
		return attendanceDAO.queryAttendances(condition,offset,limit);
	}


	public int getCountAttendances(Map condition)throws Exception {
		return attendanceDAO.queryCountAttendances(condition);
	}


	public Attendance getAttendanceById(String id)throws Exception {
		return attendanceDAO.queryAttendanceById(id);
	}


	public void removeAttendanceById(String id)throws Exception {
		 attendanceDAO.execDeleteAttendanceById(id);
	}


	public void saveAttendance(Attendance attendance)throws Exception {
		 attendanceDAO.execInsertAttendance(attendance);
	}


	public void modifyAttendance(Attendance attendance)throws Exception {
		 attendanceDAO.execUpdateAttendance(attendance);
	}


	public void setAttendanceDAO(AttendanceDAO attendanceDAO){
		this.attendanceDAO=attendanceDAO;
	}


	public List getAttendancesByDate(Map condition) throws Exception {
		return  attendanceDAO.queryAttendancesByDate(condition);
	}


	public Attendance getAttendanceByUserIdandDate(String userId, String date)
			throws Exception {
		Map condition = new HashMap();
		condition.put("name", userId);
		condition.put("attenddate", date);
		return attendanceDAO.queryAttendanceByUserIdandDate(condition);
	}


	public int checkSignin(String userId) throws Exception {
		Map condition = new HashMap();
		condition.put("name", userId);
		Date now = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		condition.put("attenddate",sm.format(now));
		return attendanceDAO.queryCheckSignin(condition);
	}


	public List getAttendancesCount(Map condition, int offset, int limit)
			throws Exception {
		return attendanceDAO.queryAttendancesCount(condition,offset,limit);
	}


	public int getCountAttendancesCount(Map condition) throws Exception {
		return attendanceDAO.queryCountAttendancesCount(condition);
	}

}
