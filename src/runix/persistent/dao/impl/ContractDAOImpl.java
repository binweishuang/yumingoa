package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ContractDAO;
import runix.persistent.model.Contract;

/**
 * 合同管理DAO实现类
 */
public class ContractDAOImpl extends BaseDAO implements ContractDAO {

	/**
	 * 按条件查询合同列表
	 * @author SY
	 * @time 2013-12-03
	 */
	public List queryContracts(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Contract.queryContracts",condition,offset,limit);
	}

	/**
	 * 按条件查询合同数量
	 * @author SY
	 * @time 2013-12-03
	 */
	public int queryCountContracts(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Contract.queryCountContracts",condition);
	}

	/**
	 * 根据ID查询合同   返回合同实体
	 * @author SY
	 * @time 2013-12-03
	 */
	public Contract queryContractById(String id)throws Exception {
		return (Contract)getSqlMapClientTemplate().queryForObject("Contract.queryContractById",id);
	}
	
	/**
	 * 根据ID查询合同实体和相关表字段名称
	 * @author SY 
	 * @time 2013-12-03 11:49
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryContractAndNamesById(String id)throws Exception{
		return getSqlMapClientTemplate().queryForList("Contract.queryContractAndNamesById",id);
	}

	/**
	 * 删除合同
	 * @author SY
	 * @time 2013-12-03
	 */
	public void execDeleteContractById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Contract.execDeleteContractById",id);
	}

	/**
	 * 添加合同
	 * @author SY
	 * @time 2013-12-03
	 */
	public void execInsertContract(Contract contract)throws Exception {
		getSqlMapClientTemplate().insert("Contract.execInsertContract",contract);
	}

	/**
	 * 修改合同
	 * @author SY
	 * @time 2013-12-03
	 */
	public void execUpdateContract(Contract contract)throws Exception {
		getSqlMapClientTemplate().update("Contract.execUpdateContract",contract);
	}


}
