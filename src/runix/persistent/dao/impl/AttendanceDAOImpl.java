package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.AttendanceDAO;
import runix.persistent.model.Attendance;

public class AttendanceDAOImpl extends BaseDAO implements AttendanceDAO {

	public List queryAttendances(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Attendance.queryAttendances",condition,offset,limit);
	}


	public int queryCountAttendances(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Attendance.queryCountAttendances",condition);
	}


	public Attendance queryAttendanceById(String id)throws Exception {
		return (Attendance)getSqlMapClientTemplate().queryForObject("Attendance.queryAttendanceById",id);
	}


	public void execDeleteAttendanceById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Attendance.execDeleteAttendanceById",id);
	}


	public void execInsertAttendance(Attendance attendance)throws Exception {
		getSqlMapClientTemplate().insert("Attendance.execInsertAttendance",attendance);
	}


	public void execUpdateAttendance(Attendance attendance)throws Exception {
		getSqlMapClientTemplate().update("Attendance.execUpdateAttendance",attendance);
	}


	public List queryAttendancesByDate(Map condition) throws Exception {
		return getSqlMapClientTemplate().queryForList("Attendance.queryAttendancesByDate",condition);
	}


	public Attendance queryAttendanceByUserIdandDate(Map condition)
			throws Exception {
		return (Attendance)getSqlMapClientTemplate().queryForObject("Attendance.queryAttendanceByUserIdandDate",condition);
	}


	public int queryCheckSignin(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Attendance.queryCheckSignin",condition);
	}


	public List queryAttendancesCount(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("Attendance.queryAttendancesCount",condition,offset,limit);
	}


	public int queryCountAttendancesCount(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Attendance.queryCountAttendancesCount",condition);
	}


}
