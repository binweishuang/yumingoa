package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Answer;

public interface AnswerService {

	public List getAnswers(Map condition,int offset,int limit)throws Exception;

	public int getCountAnswers(Map condition)throws Exception;

	public Answer getAnswerById(String id)throws Exception;

	public void removeAnswerById(String id)throws Exception;

	public void saveAnswer(Answer answer)throws Exception;

	public void modifyAnswer(Answer answer)throws Exception;
	/**
	 * ycr 2013-12-16 根据调查id查询选项列表
	 * @param survyId
	 * @return
	 * @throws Exception
	 */
	public List getAnswersBySurveyId(String surveyId)throws Exception;
	/**
	 * ycr 2013-12-16 根据调查id删除选项列表
	 * @param surveyId
	 * @throws Exception
	 */
	public void removeAnswerBySurveyId(String surveyId)throws Exception;
	/**
	 * ycr 2013-12-17 修改投票数和投票人
	 * @param answerId
	 * @param uservotenum
	 * @param votepeoples
	 * @throws Exception
	 */
	public String  modifyAnswer(String answerStr)throws Exception;
	/**
	 * ycr 2013-12-17 检查登录人是否已投过票
	 * @param loginname
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	public int checkLoginname(String loginname,String surveyId)throws Exception;
	/**
	 * 获得总投票数
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	public int getTotalnumBySurveyId(String surveyId)throws Exception;
}
