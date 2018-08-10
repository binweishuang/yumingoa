package runix.persistent.dao;
import java.util.List;
import java.util.Map;

import runix.persistent.model.FlowPeople;
import runix.persistent.model.Reimbursement;

public interface ReimbursementDAO {

	public List queryReimbursements(Map condition,int offset,int limit)throws Exception;

	public int queryCountReimbursements(Map condition)throws Exception;

	public Reimbursement queryReimbursementById(String id)throws Exception;

	public void execDeleteReimbursementById(String id)throws Exception;

	public void execInsertReimbursement(Reimbursement reimbursement)throws Exception;

	public void execUpdateReimbursement(Reimbursement reimbursement)throws Exception;

	/**
	 * 保存审核人员关联表
	 * @param flowPeople
	 * @throws Exception
	 */
	public void execInsertFlowPeople(FlowPeople flowPeople)throws Exception;
	/**
	 * 根据关联id删除审核人员表
	 * @param relateId
	 * @throws Exception
	 */
	public void execDeleteFlowPeopleByRelateId(String relateId)throws Exception;
	/**
	 * 统计查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryReimbursementsCount(Map condition,int offset,int limit)throws Exception;
	public int queryCountReimbursementsCount(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询报销信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryReimbursementByIdForCheck(String id)throws Exception;
}
