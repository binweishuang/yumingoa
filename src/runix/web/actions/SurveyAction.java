package runix.web.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Answer;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Survey;
import runix.service.AnswerService;
import runix.service.BaseDataService;
import runix.service.SurveyService;

/**
 * 投票管理Action
 * @author SY
 */
public class SurveyAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页数
	private int pages=15;//每页显示记录数
	private List surveyList;//投票列表
	private String surveyId_q;
	private String surveyId;
	private String title;//投票标题
	private String voter;//发起人
	private String surveyperson;//参与人id
	private String peoplesname;//参与人姓名
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String publicity;//是否公开   0、否    1、是
	private String votingway;//投票方式   0、不记名   1、记名
	private String questiontype;//问题类型 0、单选   1、多选
	private String questionname;//问题
	private String status;//投票状态
	private String flag;
	private String votepeoples;//投票人
	private String loginname;//登录人姓名
	private int totalnum;//总投票数
	private List answerList;
	private SurveyService surveyService;
	private BaseDataService baseDataService;
	private AnswerService answerService;
	private String content[];//选项内容
	private BaseUser user;//当前用户
	private String userId;
	private Date nowtime;//现在日期
	
	private String userRole;

	/**
	 * 查询投票
	 */
	public String doQuery(){
		try {
			int currentPageInt = 1;
			if(currentPage != null && !"".equals(currentPage) && currentPage.equals("currentPage")){
				currentPage = null;
			}
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
			if (!"".equals(title) && null != title) {
				condition.put("title", "%"+title+"%");
			}
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			userRole = user.getTitle();
			if(user!=null){
				condition.put("surveyperson", "%p"+user.getUserId()+"p%");
				condition.put("voter",user.getUserId());
				userId = user.getUserId();
				if(userRole.indexOf("fm2") < 0){
					readonly = true;
				}
			}
			int record = surveyService.getCountSurveys(condition);
			List lst =surveyService.getSurveys(condition, offset, limit);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			nowtime = new Date();
			surveyList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新   查看投票
	 */
	public String doUpdateInit(){
		try {
			Survey survey =surveyService.getSurveyById(surveyId_q);
			surveyId = survey.getSurveyId();
			title = survey.getTitle();
			voter = survey.getVoter();
			surveyperson = survey.getSurveyperson();
			String personIds[] = surveyperson.split(",");
			String personid = "";
			for(int i=0;i<personIds.length;i++){
				if("".equals(personid)){
					personid = personIds[i].replaceAll("p", "");
				}else{
					personid = personid+","+personIds[i].replaceAll("p", "");
				}
			}
			surveyperson = personid;
			peoplesname= survey.getPeoplesname();
			starttime = survey.getStarttime();
			endtime = survey.getEndtime();
			publicity = survey.getPublicity();
			votingway = survey.getVotingway();
			questiontype = survey.getQuestiontype();
			questionname = survey.getQuestionname();
			status = survey.getStatus();
			flag = survey.getFlag();
			answerList = answerService.getAnswersBySurveyId(surveyId);
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改投票
	 */
	public String doUpdate(){
		try {
			Survey survey =surveyService.getSurveyById(surveyId_q);
			survey.setTitle(title);
			//survey.setVoter(voter);
			String surveypersons[] = surveyperson.split(",");
			String peopleid = "";
			for(int i=0;i<surveypersons.length;i++){
				if("".equals(peopleid)){
					peopleid = "p"+surveypersons[i]+"p";
				}else{
					peopleid = peopleid+",p"+surveypersons[i]+"p";
				}
			}
			survey.setSurveyperson(peopleid);
			survey.setPeoplesname(peoplesname);
			survey.setStarttime(starttime);
			survey.setEndtime(endtime);
			survey.setPublicity(publicity);
			survey.setVotingway(votingway);
			survey.setQuestiontype(questiontype);
			survey.setQuestionname(questionname);
			survey.setStatus(status);
			survey.setFlag(flag);
			surveyService.modifySurvey(survey);
			answerService.removeAnswerBySurveyId(surveyId_q);
			//添加选择
			String answerId;
			for(int i = 0;i<content.length;i++){
				Answer answer = new Answer();
			//	answerId = baseDataService.getSequence();
			//	answer.setAnswerId(answerId);
				answer.setContent(content[i]);
				answer.setSortnum(i+1+"");
				answer.setSurveyId(surveyId_q);
				answer.setUservotenum("0");
				answer.setFlag(flag);
				answer.setVotepeoples(votepeoples);
				answerService.saveAnswer(answer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 删除投票
	 */
	public String doDelete(){
		try {
			answerService.removeAnswerBySurveyId(surveyId_q);
			surveyService.removeSurveyById(surveyId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化添加投票
	 */
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

	/**
	 * 添加投票
	 */
	public String doInsert(){
		try {
			user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			Survey survey = new Survey();
		//	surveyId = baseDataService.getSequence();
		//	survey.setSurveyId(surveyId);
			survey.setTitle(title);
			if(user!=null){
				voter = user.getUserId();
			}
			survey.setVoter(voter);
			String surveypersons[] = surveyperson.split(",");
			String peopleid = "";
			for(int i=0;i<surveypersons.length;i++){
				if("".equals(peopleid)){
					peopleid = "p"+surveypersons[i]+"p";
				}else{
					peopleid = peopleid+",p"+surveypersons[i]+"p";
				}
			}
			survey.setSurveyperson(peopleid);
			survey.setPeoplesname(peoplesname);
			survey.setStarttime(starttime);
			survey.setEndtime(endtime);
			survey.setPublicity(publicity);
			survey.setVotingway(votingway);
			survey.setQuestiontype(questiontype);
			survey.setQuestionname(questionname);
			survey.setStatus(status);
			survey.setFlag(flag);
			surveyService.saveSurvey(survey);
			//添加选择
			String answerId;
			for(int i = 0;i<content.length;i++){
				Answer answer = new Answer();
			//	answerId = baseDataService.getSequence();
			//	answer.setAnswerId(answerId);
				answer.setContent(content[i]);
				answer.setSortnum(i+1+"");
				answer.setSurveyId(survey.getSurveyId());
				answer.setUservotenum("0");
				answer.setFlag(flag);
				answer.setVotepeoples(votepeoples);
				answerService.saveAnswer(answer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doVoteInit(){
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				loginname = user.getName();
			}
			Survey survey =surveyService.getSurveyById(surveyId_q);
			surveyId = survey.getSurveyId();
			title = survey.getTitle();
			questiontype = survey.getQuestiontype();
			answerList = answerService.getAnswersBySurveyId(surveyId);
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Survey survey =surveyService.getSurveyById(surveyId_q);
			surveyId = survey.getSurveyId();
			title = survey.getTitle();
			voter = survey.getVoter();
			surveyperson = survey.getSurveyperson();
			String personIds[] = surveyperson.split(",");
			String personid = "";
			for(int i=0;i<personIds.length;i++){
				if("".equals(personid)){
					personid = personIds[i].replaceAll("p", "");
				}else{
					personid = personid+","+personIds[i].replaceAll("p", "");
				}
			}
			surveyperson = personid;
			peoplesname= survey.getPeoplesname();
			starttime = survey.getStarttime();
			endtime = survey.getEndtime();
			publicity = survey.getPublicity();
			votingway = survey.getVotingway();
			questiontype = survey.getQuestiontype();
			questionname = survey.getQuestionname();
			status = survey.getStatus();
			flag = survey.getFlag();
			answerList = answerService.getAnswersBySurveyId(surveyId);
			totalnum = answerService.getTotalnumBySurveyId(surveyId);
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
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

	public void setSurveyList(List surveyList){
		this.surveyList=surveyList;
	}
	public List getSurveyList(){
		return surveyList;
	}

	public void setSurveyId_q(String surveyId_q){
		this.surveyId_q=surveyId_q;
	}
	public String getSurveyId_q(){
		return surveyId_q;
	}

	public void setSurveyId(String surveyId){
		this.surveyId=surveyId;
	}
	public String getSurveyId(){
		return surveyId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setVoter(String voter){
		this.voter=voter;
	}
	public String getVoter(){
		return voter;
	}

	public void setSurveyperson(String surveyperson){
		this.surveyperson=surveyperson;
	}
	public String getSurveyperson(){
		return surveyperson;
	}

	public void setStarttime(Date starttime){
		this.starttime=starttime;
	}
	public Date getStarttime(){
		return starttime;
	}

	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public Date getEndtime(){
		return endtime;
	}

	public void setPublicity(String publicity){
		this.publicity=publicity;
	}
	public String getPublicity(){
		return publicity;
	}

	public void setVotingway(String votingway){
		this.votingway=votingway;
	}
	public String getVotingway(){
		return votingway;
	}

	public void setQuestiontype(String questiontype){
		this.questiontype=questiontype;
	}
	public String getQuestiontype(){
		return questiontype;
	}

	public void setQuestionname(String questionname){
		this.questionname=questionname;
	}
	public String getQuestionname(){
		return questionname;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setSurveyService(SurveyService surveyService){
		this.surveyService=surveyService;
	}
	public SurveyService getSurveyService(){
		return surveyService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	public String getVotepeoples() {
		return votepeoples;
	}

	public void setVotepeoples(String votepeoples) {
		this.votepeoples = votepeoples;
	}

	public List getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public String getPeoplesname() {
		return peoplesname;
	}

	public void setPeoplesname(String peoplesname) {
		this.peoplesname = peoplesname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getNowtime() {
		return nowtime;
	}

	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}






}
