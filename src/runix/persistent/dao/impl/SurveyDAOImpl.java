package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.SurveyDAO;
import runix.persistent.model.Survey;

/**
 * 投票管理DAO实现类
 */
public class SurveyDAOImpl extends BaseDAO implements SurveyDAO {

	/**
	 * 按条件投票列表
	 * @author SY
	 * @time 
	 */
	public List querySurveys(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Survey.querySurveys",condition,offset,limit);
	}

	/**
	 * 按条件投票数量
	 * @author SY
	 * @time 
	 */
	public int queryCountSurveys(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Survey.queryCountSurveys",condition);
	}

	/**
	 * 根据ID查询投票  返回实体对象
	 * @author SY
	 * @time 
	 */
	public Survey querySurveyById(String id)throws Exception {
		return (Survey)getSqlMapClientTemplate().queryForObject("Survey.querySurveyById",id);
	}

	/**
	 * 删除投票
	 * @author SY
	 * @time 
	 */
	public void execDeleteSurveyById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Survey.execDeleteSurveyById",id);
	}

	/**
	 * 添加投票
	 * @author SY
	 * @time 
	 */
	public void execInsertSurvey(Survey survey)throws Exception {
		getSqlMapClientTemplate().insert("Survey.execInsertSurvey",survey);
	}

	/**
	 * 修改投票
	 * @author SY
	 * @time 
	 */
	public void execUpdateSurvey(Survey survey)throws Exception {
		getSqlMapClientTemplate().update("Survey.execUpdateSurvey",survey);
	}


}
