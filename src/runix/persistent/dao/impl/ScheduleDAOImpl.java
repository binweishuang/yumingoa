package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ScheduleDAO;
import runix.persistent.model.Schedule;

public class ScheduleDAOImpl extends BaseDAO implements ScheduleDAO {

	public List querySchedules(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Schedule.querySchedules",condition,offset,limit);
	}


	public int queryCountSchedules(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Schedule.queryCountSchedules",condition);
	}


	public Schedule queryScheduleById(String id)throws Exception {
		return (Schedule)getSqlMapClientTemplate().queryForObject("Schedule.queryScheduleById",id);
	}


	public void execDeleteScheduleById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Schedule.execDeleteScheduleById",id);
	}


	public void execInsertSchedule(Schedule schedule)throws Exception {
		getSqlMapClientTemplate().insert("Schedule.execInsertSchedule",schedule);
	}


	public void execUpdateSchedule(Schedule schedule)throws Exception {
		getSqlMapClientTemplate().update("Schedule.execUpdateSchedule",schedule);
	}


}
