package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Permission;

public interface PermissionService {

	public List getPermissions(Map condition,int offset,int limit)throws Exception;

	public int getCountPermissions(Map condition)throws Exception;

	public Permission getPermissionById(String id)throws Exception;

	public void removePermissionById(String id)throws Exception;

	public void savePermission(Permission permission)throws Exception;

	public void modifyPermission(Permission permission)throws Exception;

	/**
	 * 根据职位查找权限
	 * @param positionId
	 * @return
	 * @author luqj
	 */
	public Permission findPermissionByPosition(String positionId);
}
