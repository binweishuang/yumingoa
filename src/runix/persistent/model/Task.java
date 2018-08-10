package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Task  implements BaseModel {
	private String taskId;
	private String title;//标题
	private String standard;//标准
	private String reportTo;//汇报对象
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String timelimit;//时限
	private String attachname;//附件
	private String attachpath;//附件路径
	private String executer;//执行人
	private String executerid;//执行人id
	private Date exestarttime;//执行任务开始时间
	private Date exeendtime;//执行任务结束时间
	private String content;//内容
	private String process;//进度
	private String completion;//完成状态
	private String totalprocess;//总进度
	private String arranger;//安排人
	private String taskcomplete;//完成情况内容
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setTaskId(String taskId){
		this.taskId=taskId;
	}
	public String getTaskId(){
		return taskId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setStandard(String standard){
		this.standard=standard;
	}
	public String getStandard(){
		return standard;
	}
	public void setReportTo(String reportTo){
		this.reportTo=reportTo;
	}
	public String getReportTo(){
		return reportTo;
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
	public void setTimelimit(String timelimit){
		this.timelimit=timelimit;
	}
	public String getTimelimit(){
		return timelimit;
	}
	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}
	public void setAttachpath(String attachpath){
		this.attachpath=attachpath;
	}
	public String getAttachpath(){
		return attachpath;
	}
	public void setExecuter(String executer){
		this.executer=executer;
	}
	public String getExecuter(){
		return executer;
	}
	public void setExestarttime(Date exestarttime){
		this.exestarttime=exestarttime;
	}
	public Date getExestarttime(){
		return exestarttime;
	}
	public void setExeendtime(Date exeendtime){
		this.exeendtime=exeendtime;
	}
	public Date getExeendtime(){
		return exeendtime;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setProcess(String process){
		this.process=process;
	}
	public String getProcess(){
		return process;
	}
	public void setCompletion(String completion){
		this.completion=completion;
	}
	public String getCompletion(){
		return completion;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public String getTotalprocess() {
		return totalprocess;
	}
	public void setTotalprocess(String totalprocess) {
		this.totalprocess = totalprocess;
	}
	public String getExecuterid() {
		return executerid;
	}
	public void setExecuterid(String executerid) {
		this.executerid = executerid;
	}
	public String getArranger() {
		return arranger;
	}
	public void setArranger(String arranger) {
		this.arranger = arranger;
	}
	public String getTaskcomplete() {
		return taskcomplete;
	}
	public void setTaskcomplete(String taskcomplete) {
		this.taskcomplete = taskcomplete;
	}
	
}
