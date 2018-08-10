package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Survey;

/**
 * 投票管理service接口
 */
public interface SurveyService {

	/**
	 * 按条件查询投票列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getSurveys(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询投票数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountSurveys(Map condition)throws Exception;

	/**
	 * 根据ID查询投票
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Survey getSurveyById(String id)throws Exception;

	/**
	 * 删除投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeSurveyById(String id)throws Exception;

	/**
	 * 添加投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveSurvey(Survey survey)throws Exception;

	/**
	 * 修改投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifySurvey(Survey survey)throws Exception;


}
