package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ReimbursementDAO;
import runix.service.ReimbursementService;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.Reimbursement;

public class ReimbursementServiceImpl  implements ReimbursementService {
	private ReimbursementDAO reimbursementDAO;


	public List getReimbursements(Map condition,int offset,int limit)throws Exception {
		return reimbursementDAO.queryReimbursements(condition,offset,limit);
	}


	public int getCountReimbursements(Map condition)throws Exception {
		return reimbursementDAO.queryCountReimbursements(condition);
	}


	public Reimbursement getReimbursementById(String id)throws Exception {
		return reimbursementDAO.queryReimbursementById(id);
	}


	public void removeReimbursementById(String id)throws Exception {
		 reimbursementDAO.execDeleteReimbursementById(id);
	}


	public void saveReimbursement(Reimbursement reimbursement)throws Exception {
		 reimbursementDAO.execInsertReimbursement(reimbursement);
	}


	public void modifyReimbursement(Reimbursement reimbursement)throws Exception {
		 reimbursementDAO.execUpdateReimbursement(reimbursement);
	}


	public void setReimbursementDAO(ReimbursementDAO reimbursementDAO){
		this.reimbursementDAO=reimbursementDAO;
	}


	public void saveFlowPeople(FlowPeople flowPeople) throws Exception {
		 reimbursementDAO.execInsertFlowPeople(flowPeople);
	}


	public void removeFlowPeopleByRelateId(String relateId) throws Exception {
		reimbursementDAO.execDeleteFlowPeopleByRelateId(relateId);
	}

	public List getReimbursementsCount(Map condition,int offset,int limit)throws Exception {
		return reimbursementDAO.queryReimbursementsCount(condition,offset,limit);
	}


	public int getCountReimbursementsCount(Map condition)throws Exception {
		return reimbursementDAO.queryCountReimbursementsCount(condition);
	}


	public List getReimbursementByIdForCheck(String id) throws Exception {
		return reimbursementDAO.queryReimbursementByIdForCheck(id);
	}
}
