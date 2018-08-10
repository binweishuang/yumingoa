package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.TaskDAO;
import runix.persistent.model.Task;
import runix.persistent.model.UserTask;

public class TaskDAOImpl extends BaseDAO implements TaskDAO {
	
	public List queryTasks(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Task.queryTasks",condition,offset,limit);
	}
	
	public int queryCountTasks(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Task.queryCountTasks",condition);
	}
	
	public List queryTasks1(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Task.queryTasks1",condition,offset,limit);
	}
	
	public int queryCountTasks1(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Task.queryCountTasks1",condition);
	}


	public Task queryTaskById(String id)throws Exception {
		return (Task)getSqlMapClientTemplate().queryForObject("Task.queryTaskById",id);
	}


	public void execDeleteTaskById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Task.execDeleteTaskById",id);
	}


	public void execInsertTask(Task task)throws Exception {
		getSqlMapClientTemplate().insert("Task.execInsertTask",task);
	}


	public void execUpdateTask(Task task)throws Exception {
		getSqlMapClientTemplate().update("Task.execUpdateTask",task);
	}

	public List queryExeTasks(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("Task.queryExeTasks",condition,offset,limit);
	}

	public int queryCountExeTasks(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Task.queryCountExeTasks",condition);
	}

	public void execInsertUserTask(UserTask userTask) throws Exception {
		getSqlMapClientTemplate().insert("Task.execInsertUserTask",userTask);
	}

	public UserTask queryUserTaskById(String utId) throws Exception {
		return (UserTask)getSqlMapClientTemplate().queryForObject("Task.queryUserTaskById",utId);
	}

	public String queryCheckComplete(String taskId) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("Task.queryCheckComplete",taskId);
	}

	public void execUpdateUserTask(UserTask userTask) throws Exception {
		getSqlMapClientTemplate().update("Task.execUpdateUserTask",userTask);
	}

	public List queryUserTasks(String taskId) throws Exception {
		return getSqlMapClientTemplate().queryForList("Task.queryUserTasks",taskId);
	}


}
