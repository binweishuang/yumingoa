package runix.web.actions;

import java.text.SimpleDateFormat;
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
import runix.persistent.model.Dept;
import runix.persistent.model.Position;
import runix.service.BaseUserService;

public class BaseUserAction extends BaseAction {

	private boolean readonly;
	private String currentPage;
	private int pages = 15;
	private List baseUserList;
	private String userId_q;
	private String userId;
	private String username;
	private String password;
	private String sex;
	private String org;
	private Date birthdate;
	private String age;
	private String email;
	private String phone;
	private String address;
	private String flag;
	private BaseUserService baseUserService;
	// input
	private String deptId;// 部门
	private String positionId;// 职位
	private String state;// 任职状态
	private String name;// 姓名
	// output
	private BaseUser user;// 用户
	private List<BaseUser> users;// 用户列表
	private List<Map<String, String>> list;// 用户列表
	private Dept dept;// 部门
	private List<Dept> depts;// 部门列表
	private Position position;// 职位
	private List<Position> positions;// 职位列表
	private boolean result;// 校验登陆账号是否已存在 true：存在 false：不存在
	private String date;

	/**
	 * 用户管理列表
	 * 
	 * @author wangfq
	 * @returnfg
	 */
	public String doUserList() {
		depts = baseUserService.getAllDepts();
		positions = baseUserService.getAllPositions();
		list = baseUserService.findAllUsers(user);
		return SUCCESS;
	}

	/**
	 * 添加用户展示页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doAddIndex() {
		depts = baseUserService.getAllDepts();
		positions = baseUserService.getAllPositions();
		return SUCCESS;
	}

	/**
	 * 校验登陆账号是否已存在 true：存在     			false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doCheck() {
		result = baseUserService.getResultByUserName(user);
		return SUCCESS;
	}

	/**
	 * 校验姓名是否已存在 true：存在 false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doCheckName() {
		result = baseUserService.getResultByName(user);
		return SUCCESS;
	}

	/**
	 * 添加用户处理逻辑
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doAddOperate() {
		try {
			baseUserService.persist(user);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 修改用户展示页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doEditIndex() {
		depts = baseUserService.getAllDepts();
		positions = baseUserService.getAllPositions();
		user = baseUserService.getUserById(user);
		Date brith = user.getBirthdate();
		if (user != null && brith != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.format(brith);
		} else {
			date = "";
		}
		return SUCCESS;
	}

	/**
	 * 修改用户逻辑
	 * 
	 * @author wangfq
	 * @return doEditOperate
	 */
	public String doEditOperate() {
		baseUserService.update(user);
		return SUCCESS;
	}

	/**
	 * 查看页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDetail() {
		dept = baseUserService.getDeptById(user);
		position = baseUserService.getPositionById(user);
		user = baseUserService.getUserById(user);
		Date brith = user.getBirthdate();
		if (user != null && brith != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.format(brith);
		} else {
			date = "";
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDel() {
		baseUserService.delete(user);
		return SUCCESS;
	}

	public String doQuery() {
		depts = baseUserService.getAllDepts();
		positions = baseUserService.getAllPositions();
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
			int offset = (currentPageInt - 1) * pages;
			int limit = pages;
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("name", "".equals(name) ? "" : name);
			condition.put("deptId", "-1".equals(deptId) ? "" : deptId);
			condition.put("positionId", "-1".equals(positionId) ? ""
					: positionId);
			condition.put("state", "-1".equals(state) ? "" : state);
			int record = baseUserService.getCountBaseUsers(condition);
			List<Map<String, String>> lst = baseUserService.getBaseUsers(
					condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			list = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit() {
		try {
			BaseUser baseUser = baseUserService.getBaseUserById(userId_q);
			userId = baseUser.getUserId();
			username = baseUser.getUsername();
			password = baseUser.getPassword();
			name = baseUser.getName();
			sex = baseUser.getSex();
			org = baseUser.getOrg();
			deptId = baseUser.getDeptId();
			positionId = baseUser.getPositionId();
			state = baseUser.getState();
			birthdate = baseUser.getBirthdate();
			age = baseUser.getAge();
			email = baseUser.getEmail();
			phone = baseUser.getPhone();
			address = baseUser.getAddress();
			flag = baseUser.getFlag();
			readonly = true;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate() {
		try {
			BaseUser baseUser = baseUserService.getBaseUserById(userId_q);
			baseUser.setUserId(userId);
			baseUser.setUsername(username);
			baseUser.setPassword(password);
			baseUser.setName(name);
			baseUser.setSex(sex);
			baseUser.setOrg(org);
			baseUser.setDeptId(deptId);
			baseUser.setPositionId(positionId);
			baseUser.setState(state);
			baseUser.setBirthdate(birthdate);
			baseUser.setAge(age);
			baseUser.setEmail(email);
			baseUser.setPhone(phone);
			baseUser.setAddress(address);
			baseUser.setFlag(flag);
			baseUserService.modifyBaseUser(baseUser);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete() {
		try {
			baseUserService.removeBaseUserById(userId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit() {
		try {
			readonly = false;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert() {
		try {
			BaseUser baseUser = new BaseUser();
			baseUser.setUserId(userId);
			baseUser.setUsername(username);
			baseUser.setPassword(password);
			baseUser.setName(name);
			baseUser.setSex(sex);
			baseUser.setOrg(org);
			baseUser.setDeptId(deptId);
			baseUser.setPositionId(positionId);
			baseUser.setState(state);
			baseUser.setBirthdate(birthdate);
			baseUser.setAge(age);
			baseUser.setEmail(email);
			baseUser.setPhone(phone);
			baseUser.setAddress(address);
			baseUser.setFlag(flag);
			baseUserService.saveBaseUser(baseUser);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean getReadonly() {
		return readonly;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	public void setBaseUserList(List baseUserList) {
		this.baseUserList = baseUserList;
	}

	public List getBaseUserList() {
		return baseUserList;
	}

	public void setUserId_q(String userId_q) {
		this.userId_q = userId_q;
	}

	public String getUserId_q() {
		return userId_q;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrg() {
		return org;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	public List<BaseUser> getUsers() {
		return users;
	}

	public void setUsers(List<BaseUser> users) {
		this.users = users;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

}
