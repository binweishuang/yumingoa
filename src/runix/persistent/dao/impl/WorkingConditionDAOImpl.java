package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.WorkingConditionDAO;
import runix.persistent.model.WorkingCondition;

public class WorkingConditionDAOImpl extends BaseDAO implements WorkingConditionDAO {

	public List queryWorkingConditions(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("WorkingCondition.queryWorkingConditions",condition,offset,limit);
	}


	public int queryCountWorkingConditions(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("WorkingCondition.queryCountWorkingConditions",condition);
	}


	public WorkingCondition queryWorkingConditionById(String id)throws Exception {
		return (WorkingCondition)getSqlMapClientTemplate().queryForObject("WorkingCondition.queryWorkingConditionById",id);
	}


	public void execDeleteWorkingConditionById(String id)throws Exception {
		getSqlMapClientTemplate().delete("WorkingCondition.execDeleteWorkingConditionById",id);
	}


	public void execInsertWorkingCondition(WorkingCondition workingCondition)throws Exception {
		getSqlMapClientTemplate().insert("WorkingCondition.execInsertWorkingCondition",workingCondition);
	}


	public void execUpdateWorkingCondition(WorkingCondition workingCondition)throws Exception {
		getSqlMapClientTemplate().update("WorkingCondition.execUpdateWorkingCondition",workingCondition);
	}


	public WorkingCondition queryWorkingConditionByIdForView(String id)
			throws Exception {
		return (WorkingCondition)getSqlMapClientTemplate().queryForObject("WorkingCondition.queryWorkingConditionByIdForView",id);
	}


	public List queryWorkingConditionByIdForCheck(String id) throws Exception {
		return  getSqlMapClientTemplate().queryForList("WorkingCondition.queryWorkingConditionByIdForCheck",id);
	}


}
