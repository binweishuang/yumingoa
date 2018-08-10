package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Personnel;

public interface PersonnelService {

	public List getPersonnels(Map condition,int offset,int limit)throws Exception;

	public int getCountPersonnels(Map condition)throws Exception;

	public Personnel getPersonnelById(String id)throws Exception;

	public void removePersonnelById(String id)throws Exception;

	public void savePersonnel(Personnel personnel)throws Exception;

	public void modifyPersonnel(Personnel personnel)throws Exception;

	public Personnel getPersonnelByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询人事信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getPersonnelByIdForCheck(String id)throws Exception;

}
