package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

/**
 * 合同实体类
 * @author SY
 * @time 2013-12-02 16:55
 */
public class Contract  implements BaseModel {
	private String contractId;
	private String signedperson;//签订人
	private String department;//相关部门
	private String contracttype;//合同类型
	private String status;//合同状态
	private String customecompany;//客户单位
	private String contact;//联系人
	private String contacttel;//联系电话
	private Date signeddate;//签订日期
	private Date enddate;//终止日期
	private String details;//合同说明
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setContractId(String contractId){
		this.contractId=contractId;
	}
	public String getContractId(){
		return contractId;
	}
	public void setSignedperson(String signedperson){
		this.signedperson=signedperson;
	}
	public String getSignedperson(){
		return signedperson;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}
	public void setContracttype(String contracttype){
		this.contracttype=contracttype;
	}
	public String getContracttype(){
		return contracttype;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setCustomecompany(String customecompany){
		this.customecompany=customecompany;
	}
	public String getCustomecompany(){
		return customecompany;
	}
	public void setContact(String contact){
		this.contact=contact;
	}
	public String getContact(){
		return contact;
	}
	public void setContacttel(String contacttel){
		this.contacttel=contacttel;
	}
	public String getContacttel(){
		return contacttel;
	}
	public void setSigneddate(Date signeddate){
		this.signeddate=signeddate;
	}
	public Date getSigneddate(){
		return signeddate;
	}
	public void setEnddate(Date enddate){
		this.enddate=enddate;
	}
	public Date getEnddate(){
		return enddate;
	}
	public void setDetails(String details){
		this.details=details;
	}
	public String getDetails(){
		return details;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
