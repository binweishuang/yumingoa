package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.WorkingCondition;

public interface WorkingConditionService {

	public List getWorkingConditions(Map condition,int offset,int limit)throws Exception;

	public int getCountWorkingConditions(Map condition)throws Exception;

	public WorkingCondition getWorkingConditionById(String id)throws Exception;

	public void removeWorkingConditionById(String id)throws Exception;

	public void saveWorkingCondition(WorkingCondition workingCondition)throws Exception;

	public void modifyWorkingCondition(WorkingCondition workingCondition)throws Exception;

	/**
	 * ycr 2013-12-27 详细页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WorkingCondition getWorkingConditionByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30  审核页面根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getWorkingConditionByIdForCheck(String id)throws Exception;
}
