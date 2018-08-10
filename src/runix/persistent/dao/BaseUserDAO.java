package runix.persistent.dao;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseUser;

public interface BaseUserDAO {

	public List queryBaseUsers(Map condition, int offset, int limit)
			throws Exception;

	public int queryCountBaseUsers(Map condition) throws Exception;

	public BaseUser queryBaseUserById(String id) throws Exception;

	public void execDeleteBaseUserById(String id) throws Exception;

	public void execInsertBaseUser(BaseUser baseUser) throws Exception;

	public void execUpdateBaseUser(BaseUser baseUser) throws Exception;

	/**
	 * ycr 2013-11-27 弹出框查询人员信息
	 * 
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryBaseUsers2(Map condition, int offset, int limit)
			throws Exception;

	public int queryCountBaseUsers2(Map condition) throws Exception;

	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<BaseUser> getAllUsers();

	/**
	 * 获取所有的用户
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Map<String, String>> findAllUsers(BaseUser user);

	/**
	 * 通过姓名获取指定的baseUser实例
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> getUsersByName(BaseUser user);

	/**
	 * 通过登陆账号获取指定的baseUser实例
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> getUsersByUserName(BaseUser user);

	/**
	 * 持久化用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void persist(BaseUser user);

	/**
	 * 获取指定的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public BaseUser getUserById(BaseUser user);

	/**
	 * 修改用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void update(BaseUser user);

	/**
	 * 删除用户
	 * 
	 * @author wangfq
	 * @param user
	 */
	public void delete(BaseUser user);

	/**
	 * 查找符合条件的用户
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public List<BaseUser> searchUsers(BaseUser user);
	/**
	 * ycr 2013-12-09
	 * 检查用户名是否已存在
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int queryBaseUserNum(String username) throws Exception;
	/**
	 * ycr 2013-12-09
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public BaseUser queryBaseUserByUsername(String username)throws Exception;
	
	public List queryBirthPeoples()throws Exception;
	
	public List queryUsersByDept(Map condition)throws Exception;

}
