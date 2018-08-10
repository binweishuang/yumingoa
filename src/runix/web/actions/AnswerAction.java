package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Answer;
import kdf.web.action.BaseAction;
import runix.service.AnswerService;

public class AnswerAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List answerList;
	private String answerId_q;
	private String answerId;
	private String sortnum;
	private String content;
	private String uservotenum;
	private String surveyId;
	private String flag;
	private AnswerService answerService;

	public String doQuery(){
		try {
			int currentPageInt = 1;
			String strCurrentPage = currentPage;
			if (strCurrentPage != null && !"".equals(strCurrentPage)) {
				try {
					currentPageInt = Integer.parseInt(strCurrentPage);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					this.addActionError("please check! there is Exception");
				}
			}
			int offset = (currentPageInt-1) * pages;
			int limit = pages;

			Map condition = new HashMap();
			condition.put("answerId",  "".equals(answerId)?"":"%"+answerId+"%");
			condition.put("sortnum",  "".equals(sortnum)?"":"%"+sortnum+"%");
			condition.put("content",  "".equals(content)?"":"%"+content+"%");
			condition.put("uservotenum",  "".equals(uservotenum)?"":"%"+uservotenum+"%");
			condition.put("surveyId",  "".equals(surveyId)?"":"%"+surveyId+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = answerService.getCountAnswers(condition);
			List lst =answerService.getAnswers(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			answerList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			Answer answer =answerService.getAnswerById(answerId_q);
			answerId = answer.getAnswerId();
			sortnum = answer.getSortnum();
			content = answer.getContent();
			uservotenum = answer.getUservotenum();
			surveyId = answer.getSurveyId();
			flag = answer.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			Answer answer =answerService.getAnswerById(answerId_q);
			answer.setAnswerId(answerId);
			answer.setSortnum(sortnum);
			answer.setContent(content);
			answer.setUservotenum(uservotenum);
			answer.setSurveyId(surveyId);
			answer.setFlag(flag);
			answerService.modifyAnswer(answer);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			answerService.removeAnswerById(answerId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			Answer answer = new Answer();
			answer.setAnswerId(answerId);
			answer.setSortnum(sortnum);
			answer.setContent(content);
			answer.setUservotenum(uservotenum);
			answer.setSurveyId(surveyId);
			answer.setFlag(flag);
			answerService.saveAnswer(answer);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly){
		this.readonly=readonly;
	}
	public boolean getReadonly(){
		return readonly;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage=currentPage;
	}
	public String getCurrentPage(){
		return currentPage;
	}

	public void setPages(int pages){
		this.pages=pages;
	}
	public int getPages(){
		return pages;
	}

	public void setAnswerList(List answerList){
		this.answerList=answerList;
	}
	public List getAnswerList(){
		return answerList;
	}

	public void setAnswerId_q(String answerId_q){
		this.answerId_q=answerId_q;
	}
	public String getAnswerId_q(){
		return answerId_q;
	}

	public void setAnswerId(String answerId){
		this.answerId=answerId;
	}
	public String getAnswerId(){
		return answerId;
	}

	public void setSortnum(String sortnum){
		this.sortnum=sortnum;
	}
	public String getSortnum(){
		return sortnum;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setUservotenum(String uservotenum){
		this.uservotenum=uservotenum;
	}
	public String getUservotenum(){
		return uservotenum;
	}

	public void setSurveyId(String surveyId){
		this.surveyId=surveyId;
	}
	public String getSurveyId(){
		return surveyId;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setAnswerService(AnswerService answerService){
		this.answerService=answerService;
	}
	public AnswerService getAnswerService(){
		return answerService;
	}

}
