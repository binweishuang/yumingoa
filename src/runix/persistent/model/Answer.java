package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 选项表
 * @author SY
 */
public class Answer  implements BaseModel {
	private String answerId;
	private String sortnum;//排序
	private String content;//内容
	private String uservotenum;//投票次数
	private String surveyId;//关联投票表ID
	private String flag;
	private String votepeoples;//投票人


	public Map toMap(){
		return null;
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
	public String getVotepeoples() {
		return votepeoples;
	}
	public void setVotepeoples(String votepeoples) {
		this.votepeoples = votepeoples;
	}
	
}
