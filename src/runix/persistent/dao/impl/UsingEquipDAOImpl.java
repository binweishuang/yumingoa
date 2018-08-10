package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.UsingEquipDAO;
import runix.persistent.model.UsingEquip;

public class UsingEquipDAOImpl extends BaseDAO implements UsingEquipDAO {

	public List queryUsingEquips(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("UsingEquip.queryUsingEquips",condition,offset,limit);
	}


	public int queryCountUsingEquips(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UsingEquip.queryCountUsingEquips",condition);
	}


	public UsingEquip queryUsingEquipById(String id)throws Exception {
		return (UsingEquip)getSqlMapClientTemplate().queryForObject("UsingEquip.queryUsingEquipById",id);
	}


	public void execDeleteUsingEquipById(String id)throws Exception {
		getSqlMapClientTemplate().delete("UsingEquip.execDeleteUsingEquipById",id);
	}


	public void execInsertUsingEquip(UsingEquip usingEquip)throws Exception {
		getSqlMapClientTemplate().insert("UsingEquip.execInsertUsingEquip",usingEquip);
	}


	public void execUpdateUsingEquip(UsingEquip usingEquip)throws Exception {
		getSqlMapClientTemplate().update("UsingEquip.execUpdateUsingEquip",usingEquip);
	}


	public UsingEquip queryUsingEquipByIdForView(String id) throws Exception {
		return (UsingEquip)getSqlMapClientTemplate().queryForObject("UsingEquip.queryUsingEquipByIdForView",id);
	}


	public List queryUsingEquipByIdForCheck(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("UsingEquip.queryUsingEquipByIdForCheck",id);
	}


}
