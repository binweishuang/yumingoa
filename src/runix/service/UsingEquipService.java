package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.UsingEquip;

public interface UsingEquipService {

	public List getUsingEquips(Map condition,int offset,int limit)throws Exception;

	public int getCountUsingEquips(Map condition)throws Exception;

	public UsingEquip getUsingEquipById(String id)throws Exception;

	public void removeUsingEquipById(String id)throws Exception;

	public void saveUsingEquip(UsingEquip usingEquip)throws Exception;

	public void modifyUsingEquip(UsingEquip usingEquip)throws Exception;
	/**
	 * ycr 2013-12-11
	 * 详细页面根据id查询设备领用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UsingEquip getUsingEquipByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30
	 * 审核页面根据id查询设备领用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getUsingEquipByIdForCheck(String id)throws Exception;
}
