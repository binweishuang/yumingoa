package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.UsingcarDAO;
import runix.persistent.model.Usingcar;

public class UsingcarDAOImpl extends BaseDAO implements UsingcarDAO {

	public List queryUsingcars(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Usingcar.queryUsingcars",condition,offset,limit);
	}


	public int queryCountUsingcars(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Usingcar.queryCountUsingcars",condition);
	}


	public Usingcar queryUsingcarById(String id)throws Exception {
		return (Usingcar)getSqlMapClientTemplate().queryForObject("Usingcar.queryUsingcarById",id);
	}


	public void execDeleteUsingcarById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Usingcar.execDeleteUsingcarById",id);
	}


	public void execInsertUsingcar(Usingcar usingcar)throws Exception {
		getSqlMapClientTemplate().insert("Usingcar.execInsertUsingcar",usingcar);
	}


	public void execUpdateUsingcar(Usingcar usingcar)throws Exception {
		getSqlMapClientTemplate().update("Usingcar.execUpdateUsingcar",usingcar);
	}


	public List queryUsingcarByIdForView(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("Usingcar.queryUsingcarByIdForView",id);
	}


}
