package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.MatterDAO;
import runix.persistent.model.Matter;

public class MatterDAOImpl extends BaseDAO implements MatterDAO {

	public List queryMatters(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Matter.queryMatters",condition,offset,limit);
	}


	public int queryCountMatters(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Matter.queryCountMatters",condition);
	}


	public Matter queryMatterById(String id)throws Exception {
		return (Matter)getSqlMapClientTemplate().queryForObject("Matter.queryMatterById",id);
	}


	public void execDeleteMatterById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Matter.execDeleteMatterById",id);
	}


	public void execInsertMatter(Matter matter)throws Exception {
		getSqlMapClientTemplate().insert("Matter.execInsertMatter",matter);
	}


	public void execUpdateMatter(Matter matter)throws Exception {
		getSqlMapClientTemplate().update("Matter.execUpdateMatter",matter);
	}


	public void execDeleteMatterByRelateId(String relateId) throws Exception {
		getSqlMapClientTemplate().delete("Matter.execDeleteMatterByRelateId",relateId);
	}


	public List queryMatters2(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("Matter.queryMatters2",condition,offset,limit);
	}


	public int queryCountMatters2(Map condition) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Matter.queryCountMatters2",condition);
	}
	
	public List queryMatters3(Map condition, int offset, int limit)
			throws Exception {
	return getSqlMapClientTemplate().queryForList("Matter.queryMatters3",condition,offset,limit);
	}
	
	
	public int queryCountMatters3(Map condition) throws Exception {
	return (Integer)getSqlMapClientTemplate().queryForObject("Matter.queryCountMatters3",condition);
}


}
