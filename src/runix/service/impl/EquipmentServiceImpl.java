package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.EquipmentDAO;
import runix.service.EquipmentService;
import runix.persistent.model.Equipment;

public class EquipmentServiceImpl  implements EquipmentService {
	private EquipmentDAO equipmentDAO;


	public List getEquipments(Map condition,int offset,int limit)throws Exception {
		return equipmentDAO.queryEquipments(condition,offset,limit);
	}


	public int getCountEquipments(Map condition)throws Exception {
		return equipmentDAO.queryCountEquipments(condition);
	}


	public Equipment getEquipmentById(String id)throws Exception {
		return equipmentDAO.queryEquipmentById(id);
	}


	public void removeEquipmentById(String id)throws Exception {
		 equipmentDAO.execDeleteEquipmentById(id);
	}


	public void saveEquipment(Equipment equipment)throws Exception {
		 equipmentDAO.execInsertEquipment(equipment);
	}


	public void modifyEquipment(Equipment equipment)throws Exception {
		 equipmentDAO.execUpdateEquipment(equipment);
	}


	public void setEquipmentDAO(EquipmentDAO equipmentDAO){
		this.equipmentDAO=equipmentDAO;
	}


	public List getEquipmentsByCategory(String category) throws Exception {
		return equipmentDAO.queryEquipmentsByCategory(category);
	}


	public Equipment getEquipmentByIdForView(String id) throws Exception {
		return equipmentDAO.queryEquipmentByIdForView(id);
	}


	public List getStoragenumByEquip(String id) throws Exception {
		return equipmentDAO.queryStoragenumByEquip(id);
	}

}
