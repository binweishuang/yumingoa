package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ApplyDAO;
import runix.service.ApplyService;
import runix.persistent.model.Apply;
import runix.persistent.model.FlowPeople;

/**
 * 请示管理service实现类
 */
public class ApplyServiceImpl  implements ApplyService {
	private ApplyDAO applyDAO;

	/**
	 * 按条件查询请示列表
	 * @author SY
	 * @time  
	 * @throws Exception
	 */
	public List getApplys(Map condition,int offset,int limit)throws Exception {
		return applyDAO.queryApplys(condition,offset,limit);
	}

	/**
	 * 按条件查询请示数量
	 * @author SY
	 * @time  
	 * @throws Exception
	 */
	public int getCountApplys(Map condition)throws Exception {
		return applyDAO.queryCountApplys(condition);
	}

	/**
	 * 根据ID查询请示
	 * @author SY
	 * @time  
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Apply getApplyById(String id)throws Exception {
		return applyDAO.queryApplyById(id);
	}

	/**
	 * 删除请示
	 * @author SY
	 * @time  
	 * @throws Exception
	 */
	public void removeApplyById(String id)throws Exception {
		 applyDAO.execDeleteApplyById(id);
	}

	/**
	 * 添加请示
	 * @author SY
	 * @time  
	 * @throws Exception
	 */
	public void saveApply(Apply apply)throws Exception {
		 applyDAO.execInsertApply(apply);
	}

	/**
	 * 修改请示
	 * @author SY
	 * @time  
	 * @throws Exception
	 */
	public void modifyApply(Apply apply)throws Exception {
		 applyDAO.execUpdateApply(apply);
	}


	public void setApplyDAO(ApplyDAO applyDAO){
		this.applyDAO=applyDAO;
	}

	/**
	 * 添加审批领导
	 * @author SY
	 * @time 2013-12-09 11:20
	 */
	public void saveFlowPeople(FlowPeople flowPeople) throws Exception {
		applyDAO.execInsertFlowPeople(flowPeople);
	}

	/**
	 * 根据ID查询请示实体和各字段名称
	 * @author SY
	 * @time 2013-12-09 16:05
	 */
	public List getApplyAndNamesById(String id) throws Exception {
		return applyDAO.queryApplyAndNamesById(id);
	}

}
