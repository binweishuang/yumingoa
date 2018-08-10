package runix.web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.service.BaseUserService;
import runix.service.DeptService;
import runix.service.PositionService;

public class SelectPeopleAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List baseUserList;
	private String userId_q;
	private String name_q;
	private String positionId_q;
	private String deptId_q;
	private String userId;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String org;
	private String deptId;
	private String positionId;
	private String state;
	private Date birthdate;
	private String age;
	private String email;
	private String phone;
	private String address;
	private String flag;
	private List deptList;
	private List positionList;
	private String people;//已选择的人员
	private String peopleId;//已选择的人员id
	private String random;
	private String fg;//标识可单选还是多选
	private String pId;//传递人员id字符串，splid之后根据id查出人员信息
	
	private String nocontains;
	private BaseUserService baseUserService;
	private DeptService deptService;
	private PositionService  positionService;
	public String doQuery(){
		try {
			int currentPageInt = 1;
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
			if (!"".equals(name_q) && null != name_q) {
				condition.put("name", "%"+name_q+"%");
			}
			if (!"".equals(deptId_q) && null != deptId_q) {
				condition.put("deptId", deptId_q);
			}
			if (!"".equals(positionId_q) && null != positionId_q) {
				condition.put("positionId", positionId_q);
			}
			if("1".equals(nocontains)){
				BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
				if(user!=null){
					condition.put("userId", user.getUserId());
				}
			}
			
			deptList = deptService.getAllDepts();
			positionList =positionService.getALlPositions();
			if("simple".equals(fg)){
				String pIds[] = pId.split(",");
				List idList = new ArrayList();
				for(int i=0;i<pIds.length;i++){
					idList.add(pIds[i]);
				}
				if (null != idList) {
					condition.put("idStr", idList);
				}
			}
			int record = baseUserService.getCountBaseUsers2(condition);
			List lst =baseUserService.getBaseUsers2(condition, offset, limit);

			if(!"".equals(people) && null != people){
				if(!people.equals(new String(people.getBytes("utf-8")))){
					people = new String(people.getBytes("iso-8859-1"),"utf-8");
				}
			}
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			baseUserList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		if("simple".equals(fg)){
			return "radio";
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

	public void setBaseUserList(List baseUserList){
		this.baseUserList=baseUserList;
	}
	public List getBaseUserList(){
		return baseUserList;
	}

	public void setUserId_q(String userId_q){
		this.userId_q=userId_q;
	}
	public String getUserId_q(){
		return userId_q;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}

	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
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

	public void setOrg(String org){
		this.org=org;
	}
	public String getOrg(){
		return org;
	}

	public void setDeptId(String deptId){
		this.deptId=deptId;
	}
	public String getDeptId(){
		return deptId;
	}

	public void setPositionId(String positionId){
		this.positionId=positionId;
	}
	public String getPositionId(){
		return positionId;
	}

	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}



	public String getRandom() {
		return random;
	}



	public void setRandom(String random) {
		this.random = random;
	}


	public void setBirthdate(Date birthdate){
		this.birthdate=birthdate;
	}
	public Date getBirthdate(){
		return birthdate;
	}

	public void setAge(String age){
		this.age=age;
	}
	public String getAge(){
		return age;
	}

	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}

	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setBaseUserService(BaseUserService baseUserService){
		this.baseUserService=baseUserService;
	}
	public BaseUserService getBaseUserService(){
		return baseUserService;
	}


	public DeptService getDeptService() {
		return deptService;
	}



	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}



	public PositionService getPositionService() {
		return positionService;
	}



	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	public List getDeptList() {
		return deptList;
	}



	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}



	public List getPositionList() {
		return positionList;
	}



	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}

	public String getName_q() {
		return name_q;
	}



	public void setName_q(String name_q) {
		this.name_q = name_q;
	}



	public String getPositionId_q() {
		return positionId_q;
	}



	public void setPositionId_q(String positionId_q) {
		this.positionId_q = positionId_q;
	}



	public String getDeptId_q() {
		return deptId_q;
	}



	public void setDeptId_q(String deptId_q) {
		this.deptId_q = deptId_q;
	}


	public String getPeople() {
		return people;
	}



	public void setPeople(String people) {
		this.people = people;
	}
	public String getPeopleId() {
		return peopleId;
	}



	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}



	public String getFg() {
		return fg;
	}



	public void setFg(String fg) {
		this.fg = fg;
	}



	public String getpId() {
		return pId;
	}



	public void setpId(String pId) {
		this.pId = pId;
	}



	public String getNocontains() {
		return nocontains;
	}



	public void setNocontains(String nocontains) {
		this.nocontains = nocontains;
	}

}
