package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Suggestion  implements BaseModel {
	@Override
	public String toString() {
		return "Suggestion [suggestionId=" + suggestionId + ", title=" + title
				+ ", suggesttype=" + suggesttype + ", publicity=" + publicity
				+ ", toperson=" + toperson + ", sugester=" + sugester
				+ ", content=" + content + ", flag=" + flag + "]";
	}
	private String suggestionId;
	private String title;
	private String suggesttype;
	private String publicity;
	private String toperson;
	private String sugester;
	private String content;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setSuggestionId(String suggestionId){
		this.suggestionId=suggestionId;
	}
	public String getSuggestionId(){
		return suggestionId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setSuggesttype(String suggesttype){
		this.suggesttype=suggesttype;
	}
	public String getSuggesttype(){
		return suggesttype;
	}
	public void setPublicity(String publicity){
		this.publicity=publicity;
	}
	public String getPublicity(){
		return publicity;
	}
	public void setToperson(String toperson){
		this.toperson=toperson;
	}
	public String getToperson(){
		return toperson;
	}
	public void setSugester(String sugester){
		this.sugester=sugester;
	}
	public String getSugester(){
		return sugester;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
