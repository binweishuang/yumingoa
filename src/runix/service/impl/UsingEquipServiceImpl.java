package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.UsingEquipDAO;
import runix.service.UsingEquipService;
import runix.persistent.model.UsingEquip;

public class UsingEquipServiceImpl  implements UsingEquipService {
	private UsingEquipDAO usingEquipDAO;


	public List getUsingEquips(Map condition,int offset,int limit)throws Exception {
		return usingEquipDAO.queryUsingEquips(condition,offset,limit);
	}


	public int getCountUsingEquips(Map condition)throws Exception {
		return usingEquipDAO.queryCountUsingEquips(condition);
	}


	public UsingEquip getUsingEquipById(String id)throws Exception {
		return usingEquipDAO.queryUsingEquipById(id);
	}


	public void removeUsingEquipById(String id)throws Exception {
		 usingEquipDAO.execDeleteUsingEquipById(id);
	}


	public void saveUsingEquip(UsingEquip usingEquip)throws Exception {
		 usingEquipDAO.execInsertUsingEquip(usingEquip);
	}


	public void modifyUsingEquip(UsingEquip usingEquip)throws Exception {
		 usingEquipDAO.execUpdateUsingEquip(usingEquip);
	}


	public void setUsingEquipDAO(UsingEquipDAO usingEquipDAO){
		this.usingEquipDAO=usingEquipDAO;
	}


	public UsingEquip getUsingEquipByIdForView(String id) throws Exception {
		return usingEquipDAO.queryUsingEquipByIdForView(id);
	}


	public List getUsingEquipByIdForCheck(String id) throws Exception {
		return usingEquipDAO.queryUsingEquipByIdForCheck(id);
	}

}
