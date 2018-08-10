package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ApplyDAO;
import runix.persistent.model.Apply;
import runix.persistent.model.FlowPeople;

/**
 * 请示管理DAO实现类
 */
public class ApplyDAOImpl extends BaseDAO implements ApplyDAO {

	/**
	 * 按条件查询请示列表
	 * @author SY
	 * @time 2013-12-09
	 */
	public List queryApplys(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Apply.queryApplys",condition,offset,limit);
	}

	/**
	 * 按条件查询请示数量
	 * @author SY
	 * @time 2013-12-09
	 */
	public int queryCountApplys(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Apply.queryCountApplys",condition);
	}

	/**
	 * 根据ID查询请示  返回实体
	 * @author SY
	 * @time 2013-12-09
	 */
	public Apply queryApplyById(String id)throws Exception {
		return (Apply)getSqlMapClientTemplate().queryForObject("Apply.queryApplyById",id);
	}

	/**
	 * 删除请示
	 * @author SY
	 * @time 2013-12-09
	 */
	public void execDeleteApplyById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Apply.execDeleteApplyById",id);
	}

	/**
	 * 添加请示
	 * @author SY
	 * @time 2013-12-09
	 */
	public void execInsertApply(Apply apply)throws Exception {
		getSqlMapClientTemplate().insert("Apply.execInsertApply",apply);
	}

	/**
	 * 修改请示
	 * @author SY
	 * @time 2013-12-09
	 */
	public void execUpdateApply(Apply apply)throws Exception {
		getSqlMapClientTemplate().update("Apply.execUpdateApply",apply);
	}

	/**
	 * 添加审批领导
	 * @author SY
	 * @time 2013-12-09 11:16
	 */
	public void execInsertFlowPeople(FlowPeople flowPeople) throws Exception {
		getSqlMapClientTemplate().insert("Apply.execInsertFlowPeople",flowPeople);
	}

	/**
	 * 根据ID查询请示实体以及各关联字段名称
	 * @author sy
	 * @time 2013-12-09 16:02
	 */
	public List queryApplyAndNamesById(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("Apply.queryApplyAndNamesById",id);
	}


}
