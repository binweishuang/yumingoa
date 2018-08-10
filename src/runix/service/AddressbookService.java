package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Addressbook;

/**
 * 通讯录service接口
 */
public interface AddressbookService {

	/**
	 * 按条件查询通讯录列表
	 * @author SY
	 * @time  2013-11-28
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getAddressbooks(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询通讯录数量
	 * @author SY
	 * @time  2013-11-28
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountAddressbooks(Map condition)throws Exception;

	/**
	 * 根据ID查询通讯录  
	 * @author SY
	 * @time  2013-11-28
	 * @return 返回通讯录实体
	 * @throws Exception
	 */
	public Addressbook getAddressbookById(String id)throws Exception;
	
	/**
	 * 根据ID查找对应通讯录和类型组别名称
	 * @author SY
	 * @time 2013-11-28 11:54
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getAddressbookAndTypesById(String id)throws Exception;

	/**
	 * 删除通讯录
	 * @author SY
	 * @time  2013-11-28
	 * @throws Exception
	 */
	public void removeAddressbookById(String id)throws Exception;

	/**
	 * 添加通讯录
	 * @author SY
	 * @time  2013-11-28
	 * @throws Exception
	 */
	public void saveAddressbook(Addressbook addressbook)throws Exception;

	/**
	 * 修改通讯录
	 * @author SY
	 * @time  2013-11-28
	 * @throws Exception
	 */
	public void modifyAddressbook(Addressbook addressbook)throws Exception;


}
