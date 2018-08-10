package runix.persistent.dao;
import java.util.List;
import java.util.Map;

import runix.persistent.model.Apply;
import runix.persistent.model.FlowPeople;

/**
 * 请示管理DAO接口
 * @author SY
 */
public interface ApplyDAO {

	/**
	 * 按条件查询请示列表
	 * @author SY
	 * @time 2013-12-09
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List queryApplys(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询请示数量
	 * @author SY
	 * @time 2013-12-09
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	public int queryCountApplys(Map condition)throws Exception;

	/**
	 * 根据ID查询请示 返回实体对象
	 * @author SY
	 * @time 2013-12-09
	 * @throws Exception
	 */
	public Apply queryApplyById(String id)throws Exception;
	
	/**
	 * 根据ID查询请示 
	 * @author SY
	 * @time 2013-12-09 16:00
	 * @param id
	 * @return 返回实体对象以及各字段名称
	 * @throws Exception
	 */
	public List queryApplyAndNamesById(String id)throws Exception;

	/**
	 * 删除请示
	 * @author SY
	 * @time 2013-12-09
	 * @throws Exception
	 */
	public void execDeleteApplyById(String id)throws Exception;

	/**
	 * 添加请示
	 * @author SY
	 * @time 2013-12-09
	 * @throws Exception
	 */
	public void execInsertApply(Apply apply)throws Exception;

	/**
	 * 修改请示
	 * @author SY
	 * @time 2013-12-09
	 * @throws Exception
	 */
	public void execUpdateApply(Apply apply)throws Exception;

	/**
	 * 添加审批领导
	 * @param flowPeople
	 * @throws Exception
	 */
	public void execInsertFlowPeople(FlowPeople flowPeople)throws Exception;
}
