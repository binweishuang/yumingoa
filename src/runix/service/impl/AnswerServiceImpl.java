package runix.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import runix.persistent.dao.AnswerDAO;
import runix.persistent.model.Answer;
import runix.service.AnswerService;

public class AnswerServiceImpl  implements AnswerService {
	private AnswerDAO answerDAO;


	public List getAnswers(Map condition,int offset,int limit)throws Exception {
		return answerDAO.queryAnswers(condition,offset,limit);
	}


	public int getCountAnswers(Map condition)throws Exception {
		return answerDAO.queryCountAnswers(condition);
	}


	public Answer getAnswerById(String id)throws Exception {
		return answerDAO.queryAnswerById(id);
	}


	public void removeAnswerById(String id)throws Exception {
		 answerDAO.execDeleteAnswerById(id);
	}


	public void saveAnswer(Answer answer)throws Exception {
		 answerDAO.execInsertAnswer(answer);
	}


	public void modifyAnswer(Answer answer)throws Exception {
		 answerDAO.execUpdateAnswer(answer);
	}


	public void setAnswerDAO(AnswerDAO answerDAO){
		this.answerDAO=answerDAO;
	}


	public List getAnswersBySurveyId(String surveyId) throws Exception {
		return answerDAO.queryAnswersBySurveyId(surveyId);
	}


	public void removeAnswerBySurveyId(String surveyId) throws Exception {
		 answerDAO.execDeleteAnswerBySurveyId(surveyId);
	}


	public String modifyAnswer(String answerStr) throws Exception {
		if(!"".equals(answerStr)&&null!=answerStr){
			String str[] = answerStr.split("%");
			String answerId=str[0];
			String uservotenum = str[1];
			String votepeoples = str[2];
			String loginname = str[3];
		if("0".equals(uservotenum)){
			uservotenum = "1";
		}else{
			uservotenum = Integer.parseInt(uservotenum)+1+"";
		}
		if("".equals(votepeoples)||null==votepeoples){
			votepeoples = loginname;
		}else{
			votepeoples = votepeoples +","+loginname;
		}
		Answer answer = answerDAO.queryAnswerById(answerId);
		answer.setUservotenum(uservotenum);
		answer.setVotepeoples(votepeoples);
		answerDAO.execUpdateAnswer(answer);
		return "1";
		}
		return "0";
	}


	public int checkLoginname(String loginname, String surveyId)
			throws Exception {
		Map condition = new HashMap();
		condition.put("loginname", "%"+loginname+"%");
		condition.put("surveyId", surveyId);
		return answerDAO.queryCheckLoginname(condition);
	}


	public int getTotalnumBySurveyId(String surveyId) throws Exception {
		return answerDAO.queryTotalnumBySurveyId(surveyId);
	}
	
	

}
