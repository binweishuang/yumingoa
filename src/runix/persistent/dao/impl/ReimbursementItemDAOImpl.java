package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ReimbursementItemDAO;
import runix.persistent.model.ReimbursementItem;

public class ReimbursementItemDAOImpl extends BaseDAO implements ReimbursementItemDAO {

	public List queryReimbursementItems(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("ReimbursementItem.queryReimbursementItems",condition,offset,limit);
	}


	public int queryCountReimbursementItems(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("ReimbursementItem.queryCountReimbursementItems",condition);
	}


	public ReimbursementItem queryReimbursementItemById(String id)throws Exception {
		return (ReimbursementItem)getSqlMapClientTemplate().queryForObject("ReimbursementItem.queryReimbursementItemById",id);
	}


	public void execDeleteReimbursementItemById(String id)throws Exception {
		getSqlMapClientTemplate().delete("ReimbursementItem.execDeleteReimbursementItemById",id);
	}


	public void execInsertReimbursementItem(ReimbursementItem reimbursementItem)throws Exception {
		getSqlMapClientTemplate().insert("ReimbursementItem.execInsertReimbursementItem",reimbursementItem);
	}


	public void execUpdateReimbursementItem(ReimbursementItem reimbursementItem)throws Exception {
		getSqlMapClientTemplate().update("ReimbursementItem.execUpdateReimbursementItem",reimbursementItem);
	}


	public List queryReimbursementItemsByReimId(String reimId) throws Exception {
		return  getSqlMapClientTemplate().queryForList("ReimbursementItem.queryReimbursementItemsByReimId",reimId);
	}


	public void execDeleteReimbursementItemsByReimId(String reimId)
			throws Exception {
		getSqlMapClientTemplate().delete("ReimbursementItem.execDeleteReimbursementItemsByReimId",reimId);
	}


}
