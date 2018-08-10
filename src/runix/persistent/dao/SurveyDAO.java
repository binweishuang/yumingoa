package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Survey;

/**
 * 投票管理DAO接口
 */
public interface SurveyDAO {

	/**
	 * 查询投票管理列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List querySurveys(Map condition,int offset,int limit)throws Exception;

	/**
	 * 查询投票管理数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int queryCountSurveys(Map condition)throws Exception;

	/**
	 * 根据ID查询投票管理
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Survey querySurveyById(String id)throws Exception;

	/**
	 * 删除投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execDeleteSurveyById(String id)throws Exception;

	/**
	 * 添加投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execInsertSurvey(Survey survey)throws Exception;

	/**
	 * 修改投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execUpdateSurvey(Survey survey)throws Exception;


}
