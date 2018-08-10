package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.PersonnelDAO;
import runix.persistent.model.Personnel;

public class PersonnelDAOImpl extends BaseDAO implements PersonnelDAO {

	public List queryPersonnels(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Personnel.queryPersonnels",condition,offset,limit);
	}


	public int queryCountPersonnels(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Personnel.queryCountPersonnels",condition);
	}


	public Personnel queryPersonnelById(String id)throws Exception {
		return (Personnel)getSqlMapClientTemplate().queryForObject("Personnel.queryPersonnelById",id);
	}


	public void execDeletePersonnelById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Personnel.execDeletePersonnelById",id);
	}


	public void execInsertPersonnel(Personnel personnel)throws Exception {
		getSqlMapClientTemplate().insert("Personnel.execInsertPersonnel",personnel);
	}


	public void execUpdatePersonnel(Personnel personnel)throws Exception {
		getSqlMapClientTemplate().update("Personnel.execUpdatePersonnel",personnel);
	}
	
	public Personnel queryPersonnelByIdForView(String id)throws Exception {
		return (Personnel)getSqlMapClientTemplate().queryForObject("Personnel.queryPersonnelByIdForView",id);
	}


	public List queryPersonnelByIdForCheck(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("Personnel.queryPersonnelByIdForCheck",id);
	}

}
