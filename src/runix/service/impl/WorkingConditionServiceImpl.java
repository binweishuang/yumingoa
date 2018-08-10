package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.WorkingConditionDAO;
import runix.service.WorkingConditionService;
import runix.persistent.model.WorkingCondition;

public class WorkingConditionServiceImpl  implements WorkingConditionService {
	private WorkingConditionDAO workingConditionDAO;


	public List getWorkingConditions(Map condition,int offset,int limit)throws Exception {
		return workingConditionDAO.queryWorkingConditions(condition,offset,limit);
	}


	public int getCountWorkingConditions(Map condition)throws Exception {
		return workingConditionDAO.queryCountWorkingConditions(condition);
	}


	public WorkingCondition getWorkingConditionById(String id)throws Exception {
		return workingConditionDAO.queryWorkingConditionById(id);
	}


	public void removeWorkingConditionById(String id)throws Exception {
		 workingConditionDAO.execDeleteWorkingConditionById(id);
	}


	public void saveWorkingCondition(WorkingCondition workingCondition)throws Exception {
		 workingConditionDAO.execInsertWorkingCondition(workingCondition);
	}


	public void modifyWorkingCondition(WorkingCondition workingCondition)throws Exception {
		 workingConditionDAO.execUpdateWorkingCondition(workingCondition);
	}


	public void setWorkingConditionDAO(WorkingConditionDAO workingConditionDAO){
		this.workingConditionDAO=workingConditionDAO;
	}


	public WorkingCondition getWorkingConditionByIdForView(String id)
			throws Exception {
		return workingConditionDAO.queryWorkingConditionByIdForView(id);
	}


	public List getWorkingConditionByIdForCheck(String id) throws Exception {
		return workingConditionDAO.queryWorkingConditionByIdForCheck(id);
	}

}
