package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Answer;

public interface AnswerDAO {

	public List queryAnswers(Map condition,int offset,int limit)throws Exception;

	public int queryCountAnswers(Map condition)throws Exception;

	public Answer queryAnswerById(String id)throws Exception;

	public void execDeleteAnswerById(String id)throws Exception;

	public void execInsertAnswer(Answer answer)throws Exception;

	public void execUpdateAnswer(Answer answer)throws Exception;
	/**
	 * ycr 2013-12-16 根据调查id查询选项列表
	 * @param survyId
	 * @return
	 * @throws Exception
	 */
	public List queryAnswersBySurveyId(String surveyId)throws Exception;
	/**
	 * ycr 2013-12-16 根据调查id删除选项列表
	 * @param surveyId
	 * @throws Exception
	 */
	public void execDeleteAnswerBySurveyId(String surveyId)throws Exception;
	/**
	 * ycr 2013-12-17 检查登录人是否已投过票
	 * @param loginname
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	public int queryCheckLoginname(Map condition)throws Exception;
	/**
	 * ycr 2013-12-17  获得总投票数
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	public int queryTotalnumBySurveyId(String surveyId)throws Exception;
}
