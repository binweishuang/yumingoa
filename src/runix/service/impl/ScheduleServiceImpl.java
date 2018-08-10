package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ScheduleDAO;
import runix.service.ScheduleService;
import runix.persistent.model.Schedule;

public class ScheduleServiceImpl  implements ScheduleService {
	private ScheduleDAO scheduleDAO;


	public List getSchedules(Map condition,int offset,int limit)throws Exception {
		return scheduleDAO.querySchedules(condition,offset,limit);
	}


	public int getCountSchedules(Map condition)throws Exception {
		return scheduleDAO.queryCountSchedules(condition);
	}


	public Schedule getScheduleById(String id)throws Exception {
		return scheduleDAO.queryScheduleById(id);
	}


	public void removeScheduleById(String id)throws Exception {
		 scheduleDAO.execDeleteScheduleById(id);
	}


	public void saveSchedule(Schedule schedule)throws Exception {
		 scheduleDAO.execInsertSchedule(schedule);
	}


	public void modifySchedule(Schedule schedule)throws Exception {
		 scheduleDAO.execUpdateSchedule(schedule);
	}


	public void setScheduleDAO(ScheduleDAO scheduleDAO){
		this.scheduleDAO=scheduleDAO;
	}

}
