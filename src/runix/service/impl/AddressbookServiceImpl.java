package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.AddressbookDAO;
import runix.service.AddressbookService;
import runix.persistent.model.Addressbook;

/**
 * 通讯录service实现类
 */
public class AddressbookServiceImpl  implements AddressbookService {
	private AddressbookDAO addressbookDAO;

	/**
	 * 按条件查询通讯录列表
	 * @author SY
	 * @time 2013-11-28
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getAddressbooks(Map condition,int offset,int limit)throws Exception {
		return addressbookDAO.queryAddressbooks(condition,offset,limit);
	}

	/**
	 * 按条件查询通讯录数量
	 * @author SY
	 * @time 2013-11-28
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountAddressbooks(Map condition)throws Exception {
		return addressbookDAO.queryCountAddressbooks(condition);
	}

	/**
	 * 根据ID查询通讯录
	 * @author SY
	 * @time 2013-11-28
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Addressbook getAddressbookById(String id)throws Exception {
		return addressbookDAO.queryAddressbookById(id);
	}

	/**
	 * 根据ID查找对应通讯录和类型组别名称
	 * @author SY
	 * @time 2013-11-28 11:54
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getAddressbookAndTypesById(String id)throws Exception{
		return addressbookDAO.queryAddressbookAndTypesById(id);
	}

	/**
	 * 删除通讯录
	 * @author SY
	 * @time 2013-11-28
	 * @throws Exception
	 */
	public void removeAddressbookById(String id)throws Exception {
		 addressbookDAO.execDeleteAddressbookById(id);
	}

	/**
	 * 添加通讯录
	 */
	public void saveAddressbook(Addressbook addressbook)throws Exception {
		 addressbookDAO.execInsertAddressbook(addressbook);
	}

	/**
	 * 修改通讯录
	 * @author SY
	 * @time 2013-11-28
	 * @throws Exception
	 */
	public void modifyAddressbook(Addressbook addressbook)throws Exception {
		 addressbookDAO.execUpdateAddressbook(addressbook);
	}


	public void setAddressbookDAO(AddressbookDAO addressbookDAO){
		this.addressbookDAO=addressbookDAO;
	}

}
