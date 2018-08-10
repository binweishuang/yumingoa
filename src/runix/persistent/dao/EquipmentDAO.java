package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Equipment;

public interface EquipmentDAO {

	public List queryEquipments(Map condition,int offset,int limit)throws Exception;

	public int queryCountEquipments(Map condition)throws Exception;

	public Equipment queryEquipmentById(String id)throws Exception;

	public void execDeleteEquipmentById(String id)throws Exception;

	public void execInsertEquipment(Equipment equipment)throws Exception;

	public void execUpdateEquipment(Equipment equipment)throws Exception;
	/**
	 * ycr 2013-12-10
	 * 根据物品类别获取物品列表
	 * @param category
	 * @throws Exception
	 */
	public List queryEquipmentsByCategory(String category)throws Exception;
	/**
	 * ycr 2013-12-10
	 * 查看页面需要
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Equipment queryEquipmentByIdForView(String id)throws Exception;
	/**
	 * ycr 2013-12-10
	 * 根据id查询库存数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryStoragenumByEquip(String id)throws Exception;
}
