package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ReimbursementItemDAO;
import runix.service.ReimbursementItemService;
import runix.persistent.model.ReimbursementItem;

public class ReimbursementItemServiceImpl  implements ReimbursementItemService {
	private ReimbursementItemDAO reimbursementItemDAO;


	public List getReimbursementItems(Map condition,int offset,int limit)throws Exception {
		return reimbursementItemDAO.queryReimbursementItems(condition,offset,limit);
	}


	public int getCountReimbursementItems(Map condition)throws Exception {
		return reimbursementItemDAO.queryCountReimbursementItems(condition);
	}


	public ReimbursementItem getReimbursementItemById(String id)throws Exception {
		return reimbursementItemDAO.queryReimbursementItemById(id);
	}


	public void removeReimbursementItemById(String id)throws Exception {
		 reimbursementItemDAO.execDeleteReimbursementItemById(id);
	}


	public void saveReimbursementItem(ReimbursementItem reimbursementItem)throws Exception {
		 reimbursementItemDAO.execInsertReimbursementItem(reimbursementItem);
	}


	public void modifyReimbursementItem(ReimbursementItem reimbursementItem)throws Exception {
		 reimbursementItemDAO.execUpdateReimbursementItem(reimbursementItem);
	}


	public void setReimbursementItemDAO(ReimbursementItemDAO reimbursementItemDAO){
		this.reimbursementItemDAO=reimbursementItemDAO;
	}


	public List getReimbursementItemsByReimId(String reimId) throws Exception {
		return reimbursementItemDAO.queryReimbursementItemsByReimId(reimId);
	}


	public void removeReimbursementItemsByReimId(String reimId)
			throws Exception {
		 reimbursementItemDAO.execDeleteReimbursementItemsByReimId(reimId);
	}

}
