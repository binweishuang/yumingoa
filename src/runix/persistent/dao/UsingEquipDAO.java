package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.UsingEquip;

public interface UsingEquipDAO {

	public List queryUsingEquips(Map condition,int offset,int limit)throws Exception;

	public int queryCountUsingEquips(Map condition)throws Exception;

	public UsingEquip queryUsingEquipById(String id)throws Exception;

	public void execDeleteUsingEquipById(String id)throws Exception;

	public void execInsertUsingEquip(UsingEquip usingEquip)throws Exception;

	public void execUpdateUsingEquip(UsingEquip usingEquip)throws Exception;

	/**
	 * ycr 2013-12-11
	 * 详细页面根据id查询设备领用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UsingEquip queryUsingEquipByIdForView(String id) throws Exception;
	
	/**
	 * ycr 2013-12-30
	 * 审核页面根据id查询设备领用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryUsingEquipByIdForCheck(String id)throws Exception;
}
