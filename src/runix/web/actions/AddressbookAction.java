package runix.web.actions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Addressbook;
import kdf.web.action.BaseAction;
import runix.service.AddressbookService;
import runix.service.BaseDataService;

/**
 * 通讯录Action
 * @author SY
 * @time 2013-11-26 至 2013-11-29
 */
public class AddressbookAction extends BaseAction{

	private boolean readonly;
	private String currentPage;//当前页
	private int pages=15;//页面显示记录数
	private List addressbookList;//通讯录列表
	private String addrbookId_q;
	private String addrbookId;
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
	private AddressbookService addressbookService;
	
	private BaseDataService baseDataService;
	private List addrgroupList;//通讯录组别列表
	private List addrtypeList;//通讯录类型列表

	/**
	 * 查询列表
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
			//查询条件
			if(!"".equals(name) && null != name && !"0".equals(name)){
				condition.put("name",  name);
			}
			if(!"".equals(grouptype) && null != grouptype && !"0".equals(grouptype)){
				condition.put("grouptype",  grouptype);
			}
			if(!"".equals(sex) && null != sex && !"3".equals(sex)){
				condition.put("sex",  sex);
			}
			if(!"".equals(flag) && null != flag && !"3".equals(flag)){
				condition.put("flag",  flag);
			}
			int record = addressbookService.getCountAddressbooks(condition);//总记录数
			List lst =addressbookService.getAddressbooks(condition, offset, limit);//查询到的记录集
			
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			addressbookList = pg.getResult();//分页之后的通讯录结果集
			addrgroupList = baseDataService.getqBaseByDatatype("ADDRGROUP");//通讯录组别集
			addrtypeList = baseDataService.getqBaseByDatatype("ADDRESS");//通讯录类型集
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化更新
	 */
	public String doUpdateInit(){
		try {
			addressbookList =addressbookService.getAddressbookAndTypesById(addrbookId_q);
			Addressbook addressbook = addressbookService.getAddressbookById(addrbookId_q);
			addrbookId = addressbook.getAddrbookId();
			addrtype = addressbook.getAddrtype();
			grouptype = addressbook.getGrouptype();
			name = addressbook.getName();
			enname = addressbook.getEnname();
			birthdate = addressbook.getBirthdate();
			sex = addressbook.getSex();
			company = addressbook.getCompany();
			dept = addressbook.getDept();
			officetel = addressbook.getOfficetel();
			position = addressbook.getPosition();
			mobilephone = addressbook.getMobilephone();
			homephone = addressbook.getHomephone();
			email = addressbook.getEmail();
			fax = addressbook.getFax();
			address = addressbook.getAddress();
			postcode = addressbook.getPostcode();
			othercontact = addressbook.getOthercontact();
			contactrecord = addressbook.getContactrecord();
			remark = addressbook.getRemark();
			flag = addressbook.getFlag();
			addrgroupList = baseDataService.getqBaseByDatatype("ADDRGROUP");//通讯录组别集
			addrtypeList = baseDataService.getqBaseByDatatype("ADDRESS");//通信录类型集
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改通讯录
	 */
	public String doUpdate(){
		try {
			Addressbook addressbook = addressbookService.getAddressbookById(addrbookId_q);
			addressbook.setAddrtype(addrtype);
			addressbook.setGrouptype(grouptype);
			addressbook.setName(name);
			addressbook.setEnname(enname);
			addressbook.setBirthdate(birthdate);
			addressbook.setSex(sex);
			addressbook.setCompany(company);
			addressbook.setDept(dept);
			addressbook.setOfficetel(officetel);
			addressbook.setPosition(position);
			addressbook.setMobilephone(mobilephone);
			addressbook.setHomephone(homephone);
			addressbook.setEmail(email);
			addressbook.setFax(fax);
			addressbook.setAddress(address);
			addressbook.setPostcode(postcode);
			addressbook.setOthercontact(othercontact);
			addressbook.setContactrecord(contactrecord);
			addressbook.setRemark(remark);
			addressbook.setFlag(flag);
			addressbookService.modifyAddressbook(addressbook);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 删除通讯录
	 */
	public String doDelete(){
		try {
			addressbookService.removeAddressbookById(addrbookId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 初始化插入
	 */
	public String doInsertInit(){
		try {
			addrgroupList = baseDataService.getqBaseByDatatype("ADDRGROUP");
			addrtypeList = baseDataService.getqBaseByDatatype("ADDRESS");
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 添加通讯录
	 */
	public String doInsert(){
		try {
			Addressbook addressbook = new Addressbook();
		//	addrbookId = baseDataService.getSequence();
		//	addressbook.setAddrbookId(addrbookId);
			addressbook.setAddrtype(addrtype);
			addressbook.setGrouptype(grouptype);
			addressbook.setName(name);
			addressbook.setEnname(enname);
			addressbook.setBirthdate(birthdate);
			addressbook.setSex(sex);
			addressbook.setCompany(company);
			addressbook.setDept(dept);
			addressbook.setOfficetel(officetel);
			addressbook.setPosition(position);
			addressbook.setMobilephone(mobilephone);
			addressbook.setHomephone(homephone);
			addressbook.setEmail(email);
			addressbook.setFax(fax);
			addressbook.setAddress(address);
			addressbook.setPostcode(postcode);
			addressbook.setOthercontact(othercontact);
			addressbook.setContactrecord(contactrecord);
			addressbook.setRemark(remark);
			addressbook.setFlag(flag);
			addressbookService.saveAddressbook(addressbook);
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

	public void setAddressbookList(List addressbookList){
		this.addressbookList=addressbookList;
	}
	public List getAddressbookList(){
		return addressbookList;
	}

	public void setAddrbookId_q(String addrbookId_q){
		this.addrbookId_q=addrbookId_q;
	}
	public String getAddrbookId_q(){
		return addrbookId_q;
	}

	public void setAddrbookId(String addrbookId){
		this.addrbookId=addrbookId;
	}
	public String getAddrbookId(){
		return addrbookId;
	}

	public void setAddrtype(String addrtype){
		this.addrtype=addrtype;
	}
	public String getAddrtype(){
		return addrtype;
	}

	public void setGrouptype(String grouptype){
		this.grouptype=grouptype;
	}
	public String getGrouptype(){
		return grouptype;
	}

	public void setName(String name){
		this.name=name;
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

	public void setAddressbookService(AddressbookService addressbookService){
		this.addressbookService=addressbookService;
	}
	public AddressbookService getAddressbookService(){
		return addressbookService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public List getAddrgroupList() {
		return addrgroupList;
	}

	public void setAddrgroupList(List addrgroupList) {
		this.addrgroupList = addrgroupList;
	}

	public List getAddrtypeList() {
		return addrtypeList;
	}

	public void setAddrtypeList(List addrtypeList) {
		this.addrtypeList = addrtypeList;
	}

}
