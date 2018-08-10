package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.SurveyDAO;
import runix.service.SurveyService;
import runix.persistent.model.Survey;

/**
 * 投票管理service实现类
 */
public class SurveyServiceImpl  implements SurveyService {
	private SurveyDAO surveyDAO;

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
	public List getSurveys(Map condition,int offset,int limit)throws Exception {
		return surveyDAO.querySurveys(condition,offset,limit);
	}

	/**
	 * 按条件查询投票数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountSurveys(Map condition)throws Exception {
		return surveyDAO.queryCountSurveys(condition);
	}

	/**
	 * 根据ID查询投票  返回实体对象
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public Survey getSurveyById(String id)throws Exception {
		return surveyDAO.querySurveyById(id);
	}

	/**
	 * 删除投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeSurveyById(String id)throws Exception {
		 surveyDAO.execDeleteSurveyById(id);
	}

	/**
	 * 添加投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveSurvey(Survey survey)throws Exception {
		 surveyDAO.execInsertSurvey(survey);
	}

	/**
	 * 修改投票
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifySurvey(Survey survey)throws Exception {
		 surveyDAO.execUpdateSurvey(survey);
	}


	public void setSurveyDAO(SurveyDAO surveyDAO){
		this.surveyDAO=surveyDAO;
	}

}
