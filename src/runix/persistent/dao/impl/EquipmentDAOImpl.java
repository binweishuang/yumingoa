package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.EquipmentDAO;
import runix.persistent.model.Equipment;

public class EquipmentDAOImpl extends BaseDAO implements EquipmentDAO {

	public List queryEquipments(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Equipment.queryEquipments",condition,offset,limit);
	}


	public int queryCountEquipments(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Equipment.queryCountEquipments",condition);
	}


	public Equipment queryEquipmentById(String id)throws Exception {
		return (Equipment)getSqlMapClientTemplate().queryForObject("Equipment.queryEquipmentById",id);
	}


	public void execDeleteEquipmentById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Equipment.execDeleteEquipmentById",id);
	}


	public void execInsertEquipment(Equipment equipment)throws Exception {
		getSqlMapClientTemplate().insert("Equipment.execInsertEquipment",equipment);
	}


	public void execUpdateEquipment(Equipment equipment)throws Exception {
		getSqlMapClientTemplate().update("Equipment.execUpdateEquipment",equipment);
	}


	public List queryEquipmentsByCategory(String category) throws Exception {
		return getSqlMapClientTemplate().queryForList("Equipment.queryEquipmentsByCategory",category); 
	}


	public Equipment queryEquipmentByIdForView(String id) throws Exception {
		return (Equipment)getSqlMapClientTemplate().queryForObject("Equipment.queryEquipmentByIdForView",id);
	}


	public List queryStoragenumByEquip(String id) throws Exception {
		return  getSqlMapClientTemplate().queryForList("Equipment.queryStoragenumByEquip",id); 
	}


}
