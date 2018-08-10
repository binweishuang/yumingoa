package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.Apply;
import runix.persistent.model.FlowPeople;

/**
 * 请示管理service接口
 */
public interface ApplyService {

	/**
	 * 按条件查询请示列表
	 * @author SY
	 * @time  2013-12-09
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getApplys(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询请示数量
	 * @author SY
	 * @time  2013-12-09
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountApplys(Map condition)throws Exception;

	/**
	 * 根据ID查询请示
	 * @author SY
	 * @time  2013-12-09
	 * @return 返回请示实体
	 * @throws Exception
	 */
	public Apply getApplyById(String id)throws Exception;
	
	/**
	 * 根据ID查询请示
	 * @author SY
	 * @time 2013-12-09 16:04
	 * @param id
	 * @return 返回请示实体和各字段名称
	 * @throws Exception
	 */
	public List getApplyAndNamesById(String id)throws Exception;

	/**
	 * 删除请示
	 * @author SY
	 * @time  2013-12-09
	 * @throws Exception
	 */
	public void removeApplyById(String id)throws Exception;

	/**
	 * 添加请示
	 * @author SY
	 * @time  2013-12-09
	 * @throws Exception
	 */
	public void saveApply(Apply apply)throws Exception;

	/**
	 * 修改请示
	 * @author SY
	 * @time  2013-12-09
	 * @throws Exception
	 */
	public void modifyApply(Apply apply)throws Exception;

	/**
	 * 添加审批领导
	 * @author SY
	 * @time 2013-12-09 11:19
	 * @param flowPeople
	 * @throws Exception
	 */
	public void saveFlowPeople(FlowPeople flowPeople)throws Exception;
}
