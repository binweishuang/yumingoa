package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Equipment;

public interface EquipmentService {

	public List getEquipments(Map condition,int offset,int limit)throws Exception;

	public int getCountEquipments(Map condition)throws Exception;

	public Equipment getEquipmentById(String id)throws Exception;

	public void removeEquipmentById(String id)throws Exception;

	public void saveEquipment(Equipment equipment)throws Exception;

	public void modifyEquipment(Equipment equipment)throws Exception;
	
	/**
	 * ycr 2013-12-10
	 * 根据物品类别获取物品列表
	 * @param category
	 * @throws Exception
	 */
	public List getEquipmentsByCategory(String category)throws Exception;
	/**
	 * ycr 2013-12-10
	 * 查看页面需要
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Equipment getEquipmentByIdForView(String id) throws Exception;
	/**
	 * ycr 2013-12-10
	 * 根据id查询库存数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getStoragenumByEquip(String id) throws Exception;


}
