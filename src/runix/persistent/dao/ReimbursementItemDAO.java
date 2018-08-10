package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.ReimbursementItem;

public interface ReimbursementItemDAO {

	public List queryReimbursementItems(Map condition,int offset,int limit)throws Exception;

	public int queryCountReimbursementItems(Map condition)throws Exception;

	public ReimbursementItem queryReimbursementItemById(String id)throws Exception;

	public void execDeleteReimbursementItemById(String id)throws Exception;

	public void execInsertReimbursementItem(ReimbursementItem reimbursementItem)throws Exception;

	public void execUpdateReimbursementItem(ReimbursementItem reimbursementItem)throws Exception;

	/**
	 * 根据报销id查询报销项目
	 * @param reimId
	 * @return
	 * @throws Exception
	 */
	public List queryReimbursementItemsByReimId(String reimId)throws Exception;
	
	/**
	 * 根据报销id删除报销项目
	 * @param reimId
	 * @return
	 * @throws Exception
	 */
	public void execDeleteReimbursementItemsByReimId(String reimId)throws Exception;
}
