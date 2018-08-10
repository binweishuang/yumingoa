package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.Task;
import runix.persistent.model.UserTask;

public interface TaskService {

	public List getTasks(Map condition,int offset,int limit)throws Exception;

	public int getCountTasks(Map condition)throws Exception;
	
	public List getTasks1(Map condition,int offset,int limit)throws Exception;

	public int getCountTasks1(Map condition)throws Exception;

	public Task getTaskById(String id)throws Exception;

	public void removeTaskById(String id)throws Exception;

	public void saveTask(Task task)throws Exception;

	public void modifyTask(Task task)throws Exception;
	
	/**
	 * 执行任务列表
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getExeTasks(Map condition,int offset,int limit)throws Exception;
	
	/**
	 * 执行任务计数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountExeTasks(Map condition)throws Exception;
	
	public void saveUserTask(UserTask userTask)throws Exception;
	
	public UserTask getUserTaskById(String utId)throws Exception;
	
	public String checkComplete(String taskId)throws Exception;
	
	public void modifyUserTask(UserTask userTask)throws Exception;
	
	public List getUserTasks(String taskId)throws Exception;
}
