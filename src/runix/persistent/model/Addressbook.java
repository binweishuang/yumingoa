package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 通讯录实体类
 * @author SY
 * @time 2013-11-26
 */
public class Addressbook  implements BaseModel {
	private String addrbookId;//ID
	private String addrtype;//类型
	private String grouptype;//组别
	private String name;//名称
	private String enname;//英文名
	private Date birthdate;//出生日期
	private String sex;//性别
	private String company;//单位
	private String dept;//部门
	private String officetel;//单位电话
	private String position;//职位
	private String mobilephone;//移动电话
	private String homephone;//家庭电话
	private String email;//邮箱
	private String fax;//传真
	private String address;//地址
	private String postcode;//邮编
	private String othercontact;//其他联系方式
	private String contactrecord;//联系记录
	private String remark;//备注
	private String flag;//是否共享


	public Map toMap(){
		return null;
	}
	public void setAddrbookId(String addrbookId){
		this.addrbookId=addrbookId;
	}
	public String getAddrbookId(){
		return addrbookId;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getAddrtype() {
		return addrtype;
	}
	public void setAddrtype(String addrtype) {
		this.addrtype = addrtype;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getName(){
		return name;
	}
	public void setEnname(String enname){
		this.enname=enname;
	}
	public String getEnname(){
		return enname;
	}
	public void setBirthdate(Date birthdate){
		this.birthdate=birthdate;
	}
	public Date getBirthdate(){
		return birthdate;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getSex(){
		return sex;
	}
	public void setCompany(String company){
		this.company=company;
	}
	public String getCompany(){
		return company;
	}
	public void setDept(String dept){
		this.dept=dept;
	}
	public String getDept(){
		return dept;
	}
	public void setOfficetel(String officetel){
		this.officetel=officetel;
	}
	public String getOfficetel(){
		return officetel;
	}
	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}
	public void setMobilephone(String mobilephone){
		this.mobilephone=mobilephone;
	}
	public String getMobilephone(){
		return mobilephone;
	}
	public void setHomephone(String homephone){
		this.homephone=homephone;
	}
	public String getHomephone(){
		return homephone;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setFax(String fax){
		this.fax=fax;
	}
	public String getFax(){
		return fax;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setPostcode(String postcode){
		this.postcode=postcode;
	}
	public String getPostcode(){
		return postcode;
	}
	public void setOthercontact(String othercontact){
		this.othercontact=othercontact;
	}
	public String getOthercontact(){
		return othercontact;
	}
	public void setContactrecord(String contactrecord){
		this.contactrecord=contactrecord;
	}
	public String getContactrecord(){
		return contactrecord;
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
}
