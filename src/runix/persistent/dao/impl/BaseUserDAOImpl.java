package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.BaseUserDAO;
import runix.persistent.model.BaseUser;

public class BaseUserDAOImpl extends BaseDAO implements BaseUserDAO {

	public List queryBaseUsers(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseUser.queryBaseUsers", condition, offset, limit);
	}

	public int queryCountBaseUsers(Map condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BaseUser.queryCountBaseUsers", condition);
	}

	public BaseUser queryBaseUserById(String id) throws Exception {
		return (BaseUser) getSqlMapClientTemplate().queryForObject(
				"BaseUser.queryBaseUserById", id);
	}

	public void execDeleteBaseUserById(String id) throws Exception {
		getSqlMapClientTemplate().delete("BaseUser.execDeleteBaseUserById", id);
	}

	public void execInsertBaseUser(BaseUser baseUser) throws Exception {
		getSqlMapClientTemplate().insert("BaseUser.execInsertBaseUser",
				baseUser);
	}

	public void execUpdateBaseUser(BaseUser baseUser) throws Exception {
		getSqlMapClientTemplate().update("BaseUser.execUpdateBaseUser",
				baseUser);
	}



	public List queryBaseUsers2(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("BaseUser.queryBaseUsers2",condition,offset,limit);
	}


	public int queryCountBaseUsers2(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("BaseUser.queryCountBaseUsers2",condition);
	}


	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<BaseUser> getAllUsers() {
		return getSqlMapClientTemplate().queryForList("BaseUser.getAllUsers");
	}

	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Map<String, String>> findAllUsers(BaseUser user) {
		return getSqlMapClientTemplate().queryForList("BaseUser.findAllUsers", user);
	}

	/**
	 * 通过登陆账号获取指定的baseUser实例
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> getUsersByUserName(BaseUser user) {
		return getSqlMapClientTemplate().queryForList(
				"BaseUser.getUsersByUserName", user);
	}
	/**
	 * 通过姓名获取指定的baseUser实例
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> getUsersByName(BaseUser user) {
		return getSqlMapClientTemplate().queryForList(
				"BaseUser.getUsersByName", user);
	}

	/**
	 * 持久化用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void persist(BaseUser user) {
		getSqlMapClientTemplate().insert("BaseUser.persist", user);
	}

	/**
	 * 获取指定的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public BaseUser getUserById(BaseUser user) {
		return (BaseUser) getSqlMapClientTemplate().queryForObject(
				"BaseUser.getUserById", user);
	}

	/**
	 * 修改用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void update(BaseUser user) {
		getSqlMapClientTemplate().update("BaseUser.update", user);
	}

	/**
	 * 删除用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void delete(BaseUser user) {
		getSqlMapClientTemplate().delete("BaseUser.delete", user);
	}

	/**
	 * 查找符合条件的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> searchUsers(BaseUser user) {
		return getSqlMapClientTemplate().queryForList("BaseUser.search", user);
	}

	public int queryBaseUserNum(String username) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("BaseUser.queryBaseUserNum", username);
	}

	public BaseUser queryBaseUserByUsername(String username) throws Exception {
		return (BaseUser) getSqlMapClientTemplate().queryForObject(
				"BaseUser.queryBaseUserByUsername", username);
	}

	public List queryBirthPeoples() throws Exception {
		return getSqlMapClientTemplate().queryForList("BaseUser.queryBirthPeoples");
	}

	public List queryUsersByDept(Map condition) throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseUser.queryUsersByDept", condition);
	}

}
