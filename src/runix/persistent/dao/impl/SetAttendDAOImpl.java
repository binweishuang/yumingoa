package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.SetAttendDAO;
import runix.persistent.model.SetAttend;

public class SetAttendDAOImpl extends BaseDAO implements SetAttendDAO {

	public List querySetAttends(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("SetAttend.querySetAttends",condition,offset,limit);
	}


	public int queryCountSetAttends(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("SetAttend.queryCountSetAttends",condition);
	}


	public SetAttend querySetAttendById(String id)throws Exception {
		return (SetAttend)getSqlMapClientTemplate().queryForObject("SetAttend.querySetAttendById",id);
	}


	public void execDeleteSetAttendById(String id)throws Exception {
		getSqlMapClientTemplate().delete("SetAttend.execDeleteSetAttendById",id);
	}


	public void execInsertSetAttend(SetAttend setAttend)throws Exception {
		getSqlMapClientTemplate().insert("SetAttend.execInsertSetAttend",setAttend);
	}


	public void execUpdateSetAttend(SetAttend setAttend)throws Exception {
		getSqlMapClientTemplate().update("SetAttend.execUpdateSetAttend",setAttend);
	}


	public SetAttend querySetAttend() throws Exception {
		return (SetAttend)getSqlMapClientTemplate().queryForObject("SetAttend.querySetAttend");
	}


}
