package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.PermissionDAO;
import runix.persistent.model.Permission;

public class PermissionDAOImpl extends BaseDAO implements PermissionDAO {

	public List queryPermissions(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Permission.queryPermissions",condition,offset,limit);
	}


	public int queryCountPermissions(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Permission.queryCountPermissions",condition);
	}


	public Permission queryPermissionById(String id)throws Exception {
		return (Permission)getSqlMapClientTemplate().queryForObject("Permission.queryPermissionById",id);
	}


	public void execDeletePermissionById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Permission.execDeletePermissionById",id);
	}


	public void execInsertPermission(Permission permission)throws Exception {
		getSqlMapClientTemplate().insert("Permission.execInsertPermission",permission);
	}


	public void execUpdatePermission(Permission permission)throws Exception {
		getSqlMapClientTemplate().update("Permission.execUpdatePermission",permission);
	}
	
	/**
	 * 根据职位查找权限
	 * @author luqj
	 */
	public Permission findPermissionByPosition(String positionId){
		return (Permission)getSqlMapClientTemplate().queryForObject("Permission.queryPermissionByPosition",positionId);
	}
}
