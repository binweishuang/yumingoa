package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Addressbook;

/**
 *	通讯录DAO接口
 */
public interface AddressbookDAO {

	/**
	 * 查询通讯录列表
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return 
	 * @throws Exception
	 */
	public List queryAddressbooks(Map condition,int offset,int limit)throws Exception;

	/**
	 * 查询通讯录记录数
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	public int queryCountAddressbooks(Map condition)throws Exception;
	
	/**
	 * 根据ID查看通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Addressbook queryAddressbookById(String id)throws Exception;
	
	/**
	 * 根据ID查找通讯录和类型组别名称
	 * @author SY
	 * @time 2013-11-28 11:51
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryAddressbookAndTypesById(String id)throws Exception;

	/**
	 * 删除通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param id
	 * @throws Exception
	 */
	public void execDeleteAddressbookById(String id)throws Exception;
	
	/**
	 * 添加通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param addressbook
	 * @throws Exception
	 */
	public void execInsertAddressbook(Addressbook addressbook)throws Exception;

	/**
	 * 更新通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @param addressbook
	 * @throws Exception
	 */
	public void execUpdateAddressbook(Addressbook addressbook)throws Exception;


}
