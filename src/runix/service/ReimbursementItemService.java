package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.ReimbursementItem;

public interface ReimbursementItemService {

	public List getReimbursementItems(Map condition,int offset,int limit)throws Exception;

	public int getCountReimbursementItems(Map condition)throws Exception;

	public ReimbursementItem getReimbursementItemById(String id)throws Exception;

	public void removeReimbursementItemById(String id)throws Exception;

	public void saveReimbursementItem(ReimbursementItem reimbursementItem)throws Exception;

	public void modifyReimbursementItem(ReimbursementItem reimbursementItem)throws Exception;
	/**
	 * 根据报销id查询报销项目
	 * @param reimId
	 * @return
	 * @throws Exception
	 */
	public List getReimbursementItemsByReimId(String reimId)throws Exception;
	/**
	 * 根据报销id删除报销项目
	 * @param reimId
	 * @return
	 * @throws Exception
	 */
	public void removeReimbursementItemsByReimId(String reimId)throws Exception;

}
