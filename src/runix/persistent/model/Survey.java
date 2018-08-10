package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 网上调查实体类
 * @author SY
 */
public class Survey  implements BaseModel {
	private String surveyId;
	private String title;//投票标题
	private String voter;//发起人
	private String surveyperson;//调查对象
	private Date starttime;//开始时间
	private Date endtime;//终止时间
	private String publicity;//是否公开   0、否         1、是  
	private String votingway;//投票方式   0、不记名  1、记名 
	private String questiontype;//题型     0、单选     1、多选
	private String questionname;//题目内容
	private String status;//目前状态 0、禁用  1、启用
	private String flag;
	private String peoplesname;//调查人姓名


	public Map toMap(){
		return null;
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
	public String getPeoplesname() {
		return peoplesname;
	}
	public void setPeoplesname(String peoplesname) {
		this.peoplesname = peoplesname;
	}
	
}
