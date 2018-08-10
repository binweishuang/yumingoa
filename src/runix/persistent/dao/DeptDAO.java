package runix.persistent.dao;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Dept;

public interface DeptDAO {

	public List queryDepts(Map condition, int offset, int limit)
			throws Exception;

	public int queryCountDepts(Map condition) throws Exception;

	public Dept queryDeptById(String id) throws Exception;

	public void execDeleteDeptById(String id) throws Exception;

	public void execInsertDept(Dept dept) throws Exception;

	public void execUpdateDept(Dept dept) throws Exception;

	/**
	 * 获取所有的部门实例
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> getAllDepts();

	/**
	 * 校验部门名称是否相同 相同：true, 不同：false
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> findDeptByName(Dept dept);

	/**
	 * 部门持久化
	 * 
	 * @author wangfq
	 * @return
	 */
	public void persist(Dept dept);

	/**
	 * 通过id查询指定的部门
	 * 
	 * @author wangfq
	 * @param dept
	 * @return
	 */
	public Dept getDeptById(Dept dept);

	/**
	 * 修改部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void update(Dept dept);

	/**
	 * 删除部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void delete(Dept dept);
	
	public String queryDeptnameById(String id)throws Exception;

}
