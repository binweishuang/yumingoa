package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.AnswerDAO;
import runix.persistent.model.Answer;

public class AnswerDAOImpl extends BaseDAO implements AnswerDAO {

	public List queryAnswers(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Answer.queryAnswers",condition,offset,limit);
	}


	public int queryCountAnswers(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Answer.queryCountAnswers",condition);
	}


	public Answer queryAnswerById(String id)throws Exception {
		return (Answer)getSqlMapClientTemplate().queryForObject("Answer.queryAnswerById",id);
	}


	public void execDeleteAnswerById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Answer.execDeleteAnswerById",id);
	}


	public void execInsertAnswer(Answer answer)throws Exception {
		getSqlMapClientTemplate().insert("Answer.execInsertAnswer",answer);
	}


	public void execUpdateAnswer(Answer answer)throws Exception {
		getSqlMapClientTemplate().update("Answer.execUpdateAnswer",answer);
	}


	public List queryAnswersBySurveyId(String surveyId) throws Exception {
		return getSqlMapClientTemplate().queryForList("Answer.queryAnswersBySurveyId",surveyId);
	}


	public void execDeleteAnswerBySurveyId(String surveyId) throws Exception {
		getSqlMapClientTemplate().delete("Answer.execDeleteAnswerBySurveyId",surveyId);
	}


	public int queryCheckLoginname(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Answer.queryCheckLoginname",condition);
	}


	public int queryTotalnumBySurveyId(String surveyId) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Answer.queryTotalnumBySurveyId",surveyId);
	}


}
