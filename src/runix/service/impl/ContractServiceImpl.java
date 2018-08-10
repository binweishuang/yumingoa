package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ContractDAO;
import runix.service.ContractService;
import runix.persistent.model.Contract;

/**
 * 合同管理service实现类
 */
public class ContractServiceImpl  implements ContractService {
	private ContractDAO contractDAO;

	/**
	 * 按条件查询合同列表
	 * @author SY
	 * @time 2013-12-03
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getContracts(Map condition,int offset,int limit)throws Exception {
		return contractDAO.queryContracts(condition,offset,limit);
	}

	/**
	 * 按条件查询合同数量
	 * @author SY
	 * @time 2013-12-03
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountContracts(Map condition)throws Exception {
		return contractDAO.queryCountContracts(condition);
	}

	/**
	 * 根据ID查询合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public Contract getContractById(String id)throws Exception {
		return contractDAO.queryContractById(id);
	}
	
	/**
	 * 根据ID查询合同实体以及相关表字段名称
	 * @author SY
	 * @time 2013-12-03 11:52
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getContractAndNamesById(String id)throws Exception{
		return contractDAO.queryContractAndNamesById(id);
	}
	
	/**
	 * 删除合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void removeContractById(String id)throws Exception {
		 contractDAO.execDeleteContractById(id);
	}

	/**
	 * 添加合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void saveContract(Contract contract)throws Exception {
		 contractDAO.execInsertContract(contract);
	}

	/**
	 * 修改合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void modifyContract(Contract contract)throws Exception {
		 contractDAO.execUpdateContract(contract);
	}


	public void setContractDAO(ContractDAO contractDAO){
		this.contractDAO=contractDAO;
	}

}
