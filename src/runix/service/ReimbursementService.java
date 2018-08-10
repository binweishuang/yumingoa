package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.FlowPeople;
import runix.persistent.model.Reimbursement;

public interface ReimbursementService {

	public List getReimbursements(Map condition,int offset,int limit)throws Exception;

	public int getCountReimbursements(Map condition)throws Exception;

	public Reimbursement getReimbursementById(String id)throws Exception;

	public void removeReimbursementById(String id)throws Exception;

	public void saveReimbursement(Reimbursement reimbursement)throws Exception;

	public void modifyReimbursement(Reimbursement reimbursement)throws Exception;
	/**
	 * 保存审核人员关联表
	 * @param flowPeople
	 * @throws Exception
	 */
	public void saveFlowPeople(FlowPeople flowPeople)throws Exception;
	/**
	 * 根据关联id删除审核人员表
	 * @param relateId
	 * @throws Exception
	 */
	public void removeFlowPeopleByRelateId(String relateId)throws Exception;
	/**
	 * 统计查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getReimbursementsCount(Map condition,int offset,int limit)throws Exception;
	public int getCountReimbursementsCount(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询报销信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getReimbursementByIdForCheck(String id)throws Exception;
}
