package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.PermissionDAO;
import runix.service.PermissionService;
import runix.persistent.model.Permission;

public class PermissionServiceImpl  implements PermissionService {
	private PermissionDAO permissionDAO;


	public List getPermissions(Map condition,int offset,int limit)throws Exception {
		return permissionDAO.queryPermissions(condition,offset,limit);
	}


	public int getCountPermissions(Map condition)throws Exception {
		return permissionDAO.queryCountPermissions(condition);
	}


	public Permission getPermissionById(String id)throws Exception {
		return permissionDAO.queryPermissionById(id);
	}


	public void removePermissionById(String id)throws Exception {
		 permissionDAO.execDeletePermissionById(id);
	}


	public void savePermission(Permission permission)throws Exception {
		 permissionDAO.execInsertPermission(permission);
	}


	public void modifyPermission(Permission permission)throws Exception {
		 permissionDAO.execUpdatePermission(permission);
	}

	/**
	 * 根据职位查询权限
	 * @author luqj
	 */
	public Permission findPermissionByPosition(String positionId){
		return permissionDAO.findPermissionByPosition(positionId);
	}

	public void setPermissionDAO(PermissionDAO permissionDAO){
		this.permissionDAO=permissionDAO;
	}
	
}
