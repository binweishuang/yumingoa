package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Contract;

/**
 * 合同管理DAO接口
 */
public interface ContractDAO {

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
	public List queryContracts(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询合同数量
	 * @author SY
	 * @time 2013-12-03
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	public int queryCountContracts(Map condition)throws Exception;

	/**
	 * 根据ID查询合同
	 * @author SY
	 * @time 2013-12-03
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Contract queryContractById(String id)throws Exception;
	
	/**
	 * 根据ID查询合同实体和相关表字段名称
	 * @author SY 
	 * @time 2013-12-03 11:49
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryContractAndNamesById(String id)throws Exception;

	/**
	 * 删除合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void execDeleteContractById(String id)throws Exception;

	/**
	 * 添加合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void execInsertContract(Contract contract)throws Exception;

	/**
	 * 修改合同
	 * @author SY
	 * @time 2013-12-03
	 * @throws Exception
	 */
	public void execUpdateContract(Contract contract)throws Exception;


}
