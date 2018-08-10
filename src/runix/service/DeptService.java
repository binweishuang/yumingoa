package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Dept;

public interface DeptService {

	public List getDepts(Map condition, int offset, int limit) throws Exception;

	public int getCountDepts(Map condition) throws Exception;

	public Dept getDeptById(String id) throws Exception;

	public void removeDeptById(String id) throws Exception;

	public void saveDept(Dept dept) throws Exception;

	public void modifyDept(Dept dept) throws Exception;

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
	public boolean isSameName(Dept dept);

	/**
	 * 部门持久化
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(Dept dept) throws Exception;

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

	public String getDeptnameById(String id) throws Exception;
}
