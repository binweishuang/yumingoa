package runix.persistent.dao;
import java.util.List;
import java.util.Map;

import runix.persistent.model.Task;
import runix.persistent.model.UserTask;

public interface TaskDAO {

	public List queryTasks(Map condition,int offset,int limit)throws Exception;

	public int queryCountTasks(Map condition)throws Exception;
	
	public List queryTasks1(Map condition,int offset,int limit)throws Exception;

	public int queryCountTasks1(Map condition)throws Exception;

	public Task queryTaskById(String id)throws Exception;

	public void execDeleteTaskById(String id)throws Exception;

	public void execInsertTask(Task task)throws Exception;

	public void execUpdateTask(Task task)throws Exception;
	
	/**
	 * 执行任务列表
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryExeTasks(Map condition,int offset,int limit)throws Exception;
	
	/**
	 * 执行任务计数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountExeTasks(Map condition)throws Exception;
	
	public void execInsertUserTask(UserTask userTask)throws Exception;
	
	public UserTask queryUserTaskById(String utId)throws Exception;
	
	public String queryCheckComplete(String taskId)throws Exception;
	
	public void execUpdateUserTask(UserTask userTask)throws Exception;
	
	public List queryUserTasks(String taskId)throws Exception;
}
