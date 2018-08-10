package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Permission;

public interface PermissionDAO {

	public List queryPermissions(Map condition,int offset,int limit)throws Exception;

	public int queryCountPermissions(Map condition)throws Exception;

	public Permission queryPermissionById(String id)throws Exception;

	public void execDeletePermissionById(String id)throws Exception;

	public void execInsertPermission(Permission permission)throws Exception;

	public void execUpdatePermission(Permission permission)throws Exception;
	
	//根据职位查询权限
	public Permission findPermissionByPosition(String positionId);
}
