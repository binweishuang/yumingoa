package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Schedule;

public interface ScheduleDAO {

	public List querySchedules(Map condition,int offset,int limit)throws Exception;

	public int queryCountSchedules(Map condition)throws Exception;

	public Schedule queryScheduleById(String id)throws Exception;

	public void execDeleteScheduleById(String id)throws Exception;

	public void execInsertSchedule(Schedule schedule)throws Exception;

	public void execUpdateSchedule(Schedule schedule)throws Exception;


}
