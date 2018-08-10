package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Dept;
import runix.persistent.model.Position;

public interface BaseUserService {

	public List getBaseUsers(Map condition, int offset, int limit)
			throws Exception;

	public int getCountBaseUsers(Map condition) throws Exception;

	public BaseUser getBaseUserById(String id) throws Exception;

	public void removeBaseUserById(String id) throws Exception;

	public void saveBaseUser(BaseUser baseUser) throws Exception;

	public void modifyBaseUser(BaseUser baseUser) throws Exception;

	/**
	 * ycr 2013-11-27 弹出框查询人员信息
	 * 
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getBaseUsers2(Map condition, int offset, int limit)
			throws Exception;

	public int getCountBaseUsers2(Map condition) throws Exception;

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
	 * 校验姓名是否已存在 true：存在 false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public boolean getResultByName(BaseUser user);

	/**
	 * 校验登陆账号是否已存在 true：存在 false：不存在
	 * 
	 * @author wangfq
	 * @return
	 */
	public boolean getResultByUserName(BaseUser user);

	/**
	 * 获取所有的部门
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> getAllDepts();

	/**
	 * 获取所有的职位
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Position> getAllPositions();

	/**
	 * 持久化用户
	 * 
	 * @author wangfq
	 * @param user
	 * @throws Exception
	 */
	public void persist(BaseUser user) throws Exception;

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
	 * 获取指定的部门
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public Dept getDeptById(BaseUser user);

	/**
	 * 获取指定的职位
	 * 
	 * @author wangfq
	 * @param user
	 * @return
	 */
	public Position getPositionById(BaseUser user);

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
	public int getBaseUserNum(String username)throws Exception; 
	/**
	 * ycr 2013-12-09
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public BaseUser getBaseUserByUsername(String username)throws Exception; 
	
	public List getBirthPeoples()throws Exception;
	
	public List getUsersByDept(Map condition)throws Exception;

}
