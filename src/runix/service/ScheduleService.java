package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Schedule;

public interface ScheduleService {

	public List getSchedules(Map condition,int offset,int limit)throws Exception;

	public int getCountSchedules(Map condition)throws Exception;

	public Schedule getScheduleById(String id)throws Exception;

	public void removeScheduleById(String id)throws Exception;

	public void saveSchedule(Schedule schedule)throws Exception;

	public void modifySchedule(Schedule schedule)throws Exception;


}
