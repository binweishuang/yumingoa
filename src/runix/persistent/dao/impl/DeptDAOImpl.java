package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.DeptDAO;
import runix.persistent.model.Dept;

public class DeptDAOImpl extends BaseDAO implements DeptDAO {

	public List queryDepts(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("Dept.queryDepts",
				condition, offset, limit);
	}

	public int queryCountDepts(Map condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Dept.queryCountDepts", condition);
	}

	public Dept queryDeptById(String id) throws Exception {
		return (Dept) getSqlMapClientTemplate().queryForObject(
				"Dept.queryDeptById", id);
	}

	public void execDeleteDeptById(String id) throws Exception {
		getSqlMapClientTemplate().delete("Dept.execDeleteDeptById", id);
	}

	public void execInsertDept(Dept dept) throws Exception {
		getSqlMapClientTemplate().insert("Dept.execInsertDept", dept);
	}

	public void execUpdateDept(Dept dept) throws Exception {
		getSqlMapClientTemplate().update("Dept.execUpdateDept", dept);
	}

	/**
	 * 获取所有的部门实例
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> getAllDepts() {
		return getSqlMapClientTemplate().queryForList("Dept.getAllDepts");
	}

	/**
	 * 校验部门名称是否相同 相同：true, 不同：false
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> findDeptByName(Dept dept) {
		return getSqlMapClientTemplate().queryForList("Dept.findDeptByName",
				dept);
	}

	/**
	 * 部门持久化
	 * 
	 * @author wangfq
	 * @return
	 */
	public void persist(Dept dept) {
		getSqlMapClientTemplate().insert("Dept.persistDept", dept);
	}

	/**
	 * 通过id查询指定的部门
	 * 
	 * @author wangfq
	 * @param dept
	 * @return
	 */
	public Dept getDeptById(Dept dept) {
		return (Dept) getSqlMapClientTemplate().queryForObject(
				"Dept.getDeptById", dept);
	}

	/**
	 * 修改部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void update(Dept dept) {
		getSqlMapClientTemplate().update("Dept.updateDept", dept);
	}

	/**
	 * 删除部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void delete(Dept dept) {
		getSqlMapClientTemplate().delete("Dept.delete", dept);
	}

	public String queryDeptnameById(String id) throws Exception {
		 return (String) getSqlMapClientTemplate().queryForObject(
				"Dept.queryDeptnameById", id);
	}

}
