package runix.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import runix.persistent.dao.DeptDAO;
import runix.persistent.model.Dept;
import runix.service.BaseDataService;
import runix.service.DeptService;

public class DeptServiceImpl implements DeptService {
	private DeptDAO deptDAO;
	private BaseDataService baseDataService;

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public List getDepts(Map condition, int offset, int limit) throws Exception {
		return deptDAO.queryDepts(condition, offset, limit);
	}

	public int getCountDepts(Map condition) throws Exception {
		return deptDAO.queryCountDepts(condition);
	}

	public Dept getDeptById(String id) throws Exception {
		return deptDAO.queryDeptById(id);
	}

	public void removeDeptById(String id) throws Exception {
		deptDAO.execDeleteDeptById(id);
	}

	public void saveDept(Dept dept) throws Exception {
		deptDAO.execInsertDept(dept);
	}

	public void modifyDept(Dept dept) throws Exception {
		deptDAO.execUpdateDept(dept);
	}

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	/**
	 * 获取所有的部门实例
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> getAllDepts() {
		return deptDAO.getAllDepts();
	}

	/**
	 * 校验部门名称是否相同 相同：true, 不同：false
	 * 
	 * @author wangfq
	 * @return
	 */
	public boolean isSameName(Dept dept) {
		List<Dept> depts = new ArrayList<Dept>();
		depts = deptDAO.findDeptByName(dept);
		if (depts.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 部门持久化
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(Dept dept) throws Exception {
//		try {
//			dept.setDeptId(baseDataService.getSequence());
//		} catch (Exception e) {
//			System.out.println("================" + e.getMessage()
//					+ "=================");
//			throw e;
//		}
		deptDAO.persist(dept);
	}

	/**
	 * 通过id查询指定的部门
	 * 
	 * @author wangfq
	 * @param dept
	 * @return
	 */
	public Dept getDeptById(Dept dept) {
		return deptDAO.getDeptById(dept);
	}

	/**
	 * 修改部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void update(Dept dept) {
		deptDAO.update(dept);
	}

	/**
	 * 删除部门
	 * 
	 * @author wangfq
	 * @param dept
	 */
	public void delete(Dept dept) {
		deptDAO.delete(dept);
	}

	public String getDeptnameById(String id) throws Exception {
		return deptDAO.queryDeptnameById(id);
	}

}
