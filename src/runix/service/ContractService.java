package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Contract;

/**
 * 合同管理service接口
 */
public interface ContractService {

	/**
	 * 按条件查询合同列表
	 * @author SY
	 * @time  2013-12-03
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getContracts(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询合同数量
	 * @author SY
	 * @time  2013-12-03
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountContracts(Map condition)throws Exception;

	/**
	 * 根据ID查询合同
	 * @author SY
	 * @time  2013-12-03
	 * @return 返回合同实体
	 * @throws Exception
	 */
	public Contract getContractById(String id)throws Exception;
	
	/**
	 * 根据ID查询合同实体以及相关表字段名称
	 * @author SY
	 * @time 2013-12-03 11:52
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getContractAndNamesById(String id)throws Exception;

	/**
	 * 删除合同
	 * @author SY
	 * @time  2013-12-03
	 * @throws Exception
	 */
	public void removeContractById(String id)throws Exception;

	/**
	 * 添加合同
	 * @author SY
	 * @time  2013-12-03
	 * @throws Exception
	 */
	public void saveContract(Contract contract)throws Exception;

	/**
	 * 修改合同
	 * @author SY
	 * @time  2013-12-03
	 * @throws Exception
	 */
	public void modifyContract(Contract contract)throws Exception;


}
