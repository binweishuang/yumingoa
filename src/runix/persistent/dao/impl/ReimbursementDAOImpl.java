package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ReimbursementDAO;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.Reimbursement;

public class ReimbursementDAOImpl extends BaseDAO implements ReimbursementDAO {

	public List queryReimbursements(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Reimbursement.queryReimbursements",condition,offset,limit);
	}


	public int queryCountReimbursements(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Reimbursement.queryCountReimbursements",condition);
	}


	public Reimbursement queryReimbursementById(String id)throws Exception {
		return (Reimbursement)getSqlMapClientTemplate().queryForObject("Reimbursement.queryReimbursementById",id);
	}


	public void execDeleteReimbursementById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Reimbursement.execDeleteReimbursementById",id);
	}


	public void execInsertReimbursement(Reimbursement reimbursement)throws Exception {
		getSqlMapClientTemplate().insert("Reimbursement.execInsertReimbursement",reimbursement);
	}


	public void execUpdateReimbursement(Reimbursement reimbursement)throws Exception {
		getSqlMapClientTemplate().update("Reimbursement.execUpdateReimbursement",reimbursement);
	}


	public void execInsertFlowPeople(FlowPeople flowPeople) throws Exception {
		getSqlMapClientTemplate().insert("Reimbursement.execInsertFlowPeople",flowPeople);
	}


	public void execDeleteFlowPeopleByRelateId(String relateId)
			throws Exception {
		getSqlMapClientTemplate().delete("Reimbursement.execDeleteFlowPeopleByRelateId",relateId);
	}

	public List queryReimbursementsCount(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Reimbursement.queryReimbursementsCount",condition,offset,limit);
	}


	public int queryCountReimbursementsCount(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Reimbursement.queryCountReimbursementsCount",condition);
	}


	public List queryReimbursementByIdForCheck(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("Reimbursement.queryReimbursementByIdForCheck",id);
	}
}
