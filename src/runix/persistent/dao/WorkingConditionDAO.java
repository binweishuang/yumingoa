package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.WorkingCondition;

public interface WorkingConditionDAO {

	public List queryWorkingConditions(Map condition,int offset,int limit)throws Exception;

	public int queryCountWorkingConditions(Map condition)throws Exception;

	public WorkingCondition queryWorkingConditionById(String id)throws Exception;

	public void execDeleteWorkingConditionById(String id)throws Exception;

	public void execInsertWorkingCondition(WorkingCondition workingCondition)throws Exception;

	public void execUpdateWorkingCondition(WorkingCondition workingCondition)throws Exception;

	/**
	 * ycr 2013-12-27 详细页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WorkingCondition queryWorkingConditionByIdForView(String id)throws Exception;
	
	public List queryWorkingConditionByIdForCheck(String id)throws Exception;

}
