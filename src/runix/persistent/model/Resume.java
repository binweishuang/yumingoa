package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Resume  implements BaseModel {
	private String resumeId;
	private String name;
	private String sex;
	private String age;
	private String tel;
	private String department;
	private String position;
	private String hire;
	private String recruitmentId;
	private String atachname;
	private String atachpath;
	private String remark;
	private String flag;
	private Date appdate;
	private String interview;


	public Map toMap(){
		return null;
	}
	public void setResumeId(String resumeId){
		this.resumeId=resumeId;
	}
	public String getResumeId(){
		return resumeId;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getSex(){
		return sex;
	}
	public void setAge(String age){
		this.age=age;
	}
	public String getAge(){
		return age;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}
	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}
	public void setHire(String hire){
		this.hire=hire;
	}
	public String getHire(){
		return hire;
	}
	public void setRecruitmentId(String recruitmentId){
		this.recruitmentId=recruitmentId;
	}
	public String getRecruitmentId(){
		return recruitmentId;
	}
	public void setAtachname(String atachname){
		this.atachname=atachname;
	}
	public String getAtachname(){
		return atachname;
	}
	public void setAtachpath(String atachpath){
		this.atachpath=atachpath;
	}
	public String getAtachpath(){
		return atachpath;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public void setAppdate(Date appdate){
		this.appdate=appdate;
	}
	public Date getAppdate(){
		return appdate;
	}
	public void setInterview(String interview){
		this.interview=interview;
	}
	public String getInterview(){
		return interview;
	}
}
