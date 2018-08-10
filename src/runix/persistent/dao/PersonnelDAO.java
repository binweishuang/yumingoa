package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Personnel;

public interface PersonnelDAO {

	public List queryPersonnels(Map condition,int offset,int limit)throws Exception;

	public int queryCountPersonnels(Map condition)throws Exception;

	public Personnel queryPersonnelById(String id)throws Exception;

	public void execDeletePersonnelById(String id)throws Exception;

	public void execInsertPersonnel(Personnel personnel)throws Exception;

	public void execUpdatePersonnel(Personnel personnel)throws Exception;

	public Personnel queryPersonnelByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询人事信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryPersonnelByIdForCheck(String id)throws Exception;
}
