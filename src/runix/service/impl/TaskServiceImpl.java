package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.TaskDAO;
import runix.service.TaskService;
import runix.persistent.model.Task;
import runix.persistent.model.UserTask;

public class TaskServiceImpl  implements TaskService {
	private TaskDAO taskDAO;


	public List getTasks(Map condition,int offset,int limit)throws Exception {
		return taskDAO.queryTasks(condition,offset,limit);
	}


	public int getCountTasks(Map condition)throws Exception {
		return taskDAO.queryCountTasks(condition);
	}
	
	public List getTasks1(Map condition,int offset,int limit)throws Exception {
		return taskDAO.queryTasks1(condition,offset,limit);
	}


	public int getCountTasks1(Map condition)throws Exception {
		return taskDAO.queryCountTasks1(condition);
	}


	public Task getTaskById(String id)throws Exception {
		return taskDAO.queryTaskById(id);
	}


	public void removeTaskById(String id)throws Exception {
		 taskDAO.execDeleteTaskById(id);
	}


	public void saveTask(Task task)throws Exception {
		 taskDAO.execInsertTask(task);
	}


	public void modifyTask(Task task)throws Exception {
		 taskDAO.execUpdateTask(task);
	}


	public void setTaskDAO(TaskDAO taskDAO){
		this.taskDAO=taskDAO;
	}


	public List getExeTasks(Map condition, int offset, int limit)
			throws Exception {
		return taskDAO.queryExeTasks(condition,offset,limit);
	}


	public int getCountExeTasks(Map condition) throws Exception {
		return taskDAO.queryCountExeTasks(condition);
	}


	public void saveUserTask(UserTask userTask) throws Exception {
		taskDAO.execInsertUserTask(userTask);
	}


	public UserTask getUserTaskById(String utId) throws Exception {
		return taskDAO.queryUserTaskById(utId);
	}


	public String checkComplete(String taskId) throws Exception {
		return taskDAO.queryCheckComplete(taskId);
	}


	public void modifyUserTask(UserTask userTask) throws Exception {
		taskDAO.execUpdateUserTask(userTask);
	}


	public List getUserTasks(String taskId) throws Exception {
		return taskDAO.queryUserTasks(taskId);
	}

}
