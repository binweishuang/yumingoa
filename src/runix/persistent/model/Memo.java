package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 标签实体类
 * @author SY
 * @time 2013-12-03
 */
public class Memo  implements BaseModel {
	private String memoId;
	private String title;//标题
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String content;//内容
	private Date submittime;//提交时间
	private String reportperson;//上报人
	private String recieveperson;//上报对象
	private String status;//状态/处理方式
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setMemoId(String memoId){
		this.memoId=memoId;
	}
	public String getMemoId(){
		return memoId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
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
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setSubmittime(Date submittime){
		this.submittime=submittime;
	}
	public Date getSubmittime(){
		return submittime;
	}
	public void setReportperson(String reportperson){
		this.reportperson=reportperson;
	}
	public String getReportperson(){
		return reportperson;
	}
	public void setRecieveperson(String recieveperson){
		this.recieveperson=recieveperson;
	}
	public String getRecieveperson(){
		return recieveperson;
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
}
