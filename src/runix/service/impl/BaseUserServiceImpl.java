package runix.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kdf.tools.MD5;
import runix.persistent.dao.BaseUserDAO;
import runix.persistent.dao.DeptDAO;
import runix.persistent.dao.PositionDAO;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Dept;
import runix.persistent.model.Position;
import runix.service.BaseDataService;
import runix.service.BaseUserService;

public class BaseUserServiceImpl implements BaseUserService {
	private BaseUserDAO baseUserDAO;
	private DeptDAO deptDAO;
	private PositionDAO positionDAO;
	private BaseDataService baseDataService;

	public void setPositionDAO(PositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public List getBaseUsers(Map condition, int offset, int limit)
			throws Exception {
		return baseUserDAO.queryBaseUsers(condition, offset, limit);
	}

	public int getCountBaseUsers(Map condition) throws Exception {
		return baseUserDAO.queryCountBaseUsers(condition);
	}

	public BaseUser getBaseUserById(String id) throws Exception {
		return baseUserDAO.queryBaseUserById(id);
	}

	public void removeBaseUserById(String id) throws Exception {
		baseUserDAO.execDeleteBaseUserById(id);
	}

	public void saveBaseUser(BaseUser baseUser) throws Exception {
		baseUserDAO.execInsertBaseUser(baseUser);
	}

	public void modifyBaseUser(BaseUser baseUser) throws Exception {
		baseUserDAO.execUpdateBaseUser(baseUser);
	}


	public void setBaseUserDAO(BaseUserDAO baseUserDAO){
		this.baseUserDAO=baseUserDAO;
	}


	public List getBaseUsers2(Map condition, int offset, int limit)
			throws Exception {
		return baseUserDAO.queryBaseUsers2(condition,offset,limit);
	}


	public int getCountBaseUsers2(Map condition) throws Exception {
		return baseUserDAO.queryCountBaseUsers2(condition);
	}


	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<BaseUser> getAllUsers() {
		return baseUserDAO.getAllUsers();
	}

	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Map<String, String>> findAllUsers(BaseUser user) {
		if (user != null) {
			if ("-1".equals(user.getDeptId())) {
				user.setDeptId("");
			}
			if ("-1".equals(user.getPositionId())) {
				user.setPositionId("");
			}
			if ("-1".equals(user.getState())) {
				user.setState("");
			}
		}
		return baseUserDAO.findAllUsers(user);
	}

	/**
	 * 校验登陆账号是否已存在 true：存在 false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public boolean getResultByUserName(BaseUser user) {
		List<BaseUser> users = new ArrayList<BaseUser>();
		users = baseUserDAO.getUsersByUserName(user);
		if (users.isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * 校验姓名是否已存在 true：存在 false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public boolean getResultByName(BaseUser user) {
		List<BaseUser> users = new ArrayList<BaseUser>();
		users = baseUserDAO.getUsersByName(user);
		System.out.println("==================>"+user);
		if (users.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 获取所有的部门
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> getAllDepts() {
		return deptDAO.getAllDepts();
	}

	/**
	 * 获取所有的职位
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Position> getAllPositions() {
		return positionDAO.getAllPositions();
	}

	/**
	 * 持久化用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void persist(BaseUser user) throws Exception {
		if (user != null) {
			if ("-1".equals(user.getDeptId())) {
				user.setDeptId("");
			}
			if ("-1".equals(user.getPositionId())) {
				user.setPositionId("");
			}
//			try {
//				user.setUserId(baseDataService.getSequence());
//			} catch (Exception e) {
//				System.out.println("===============" + e.getMessage()
//						+ "================");
//				throw e;
//			}
			user.setPassword(new MD5().getMD5ofStr(user.getPassword()));
		}
		baseUserDAO.persist(user);
	}

	/**
	 * 获取指定的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public BaseUser getUserById(BaseUser user) {
		return baseUserDAO.getUserById(user);
	}

	/**
	 * 修改用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void update(BaseUser user) {
		if (user != null) {
			if ("-1".equals(user.getDeptId())) {
				user.setDeptId("");
			}
			if ("-1".equals(user.getPositionId())) {
				user.setPositionId("");
			}
			String passwd = user.getPassword();
			if (!"".equals(passwd)) {
				user.setPassword(new MD5().getMD5ofStr(user.getPassword()));
			}
		}
		baseUserDAO.update(user);
	}

	/**
	 * 删除用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void delete(BaseUser user) {
		baseUserDAO.delete(user);
	}

	/**
	 * 获取指定的部门
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public Dept getDeptById(BaseUser user) {
		Dept dept = new Dept();
		dept.setDeptId(user.getDeptId());
		return deptDAO.getDeptById(dept);
	}

	/**
	 * 获取指定的职位
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public Position getPositionById(BaseUser user) {
		Position p = null;
		try {
			p = positionDAO.queryPositionById(user.getPositionId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 查找符合条件的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> searchUsers(BaseUser user) {
		if ("-1".equals(user.getDeptId())) {
			user.setDeptId("");
		}
		if ("-1".equals(user.getPositionId())) {
			user.setPositionId("");
		}
		if ("-1".equals(user.getState())) {
			user.setState("");
		}
		String name = user.getName();
		if (!"".equals(name)) {
			user.setName("%" + name + "%");
		}
		return baseUserDAO.searchUsers(user);
	}

	public int getBaseUserNum(String username) throws Exception {
		return baseUserDAO.queryBaseUserNum(username) ;
	}

	public BaseUser getBaseUserByUsername(String username) throws Exception {
		return baseUserDAO.queryBaseUserByUsername( username);
	}

	public List getBirthPeoples() throws Exception {
		
		return baseUserDAO.queryBirthPeoples();
	}

	public List getUsersByDept(Map condition) throws Exception {
		return  baseUserDAO.queryUsersByDept(condition);
	}
}
