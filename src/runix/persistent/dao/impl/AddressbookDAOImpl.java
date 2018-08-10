package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.AddressbookDAO;
import runix.persistent.model.Addressbook;

/**
 * 通讯录DAO实现类
 */
public class AddressbookDAOImpl extends BaseDAO implements AddressbookDAO {

	/**
	 * 查询通讯录列表
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public List queryAddressbooks(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Addressbook.queryAddressbooks",condition,offset,limit);
	}

	/**
	 * 查询通讯录总记录数
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public int queryCountAddressbooks(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Addressbook.queryCountAddressbooks",condition);
	}

	/**
	 * 根据ID查看通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public Addressbook queryAddressbookById(String id)throws Exception {
		return (Addressbook)getSqlMapClientTemplate().queryForObject("Addressbook.queryAddressbookById",id);
	}
	
	/**
	 * 根据ID查找通讯录和类型组别名称
	 * @author SY
	 * @time 2013-11-28 11:51
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryAddressbookAndTypesById(String id)throws Exception {
		return getSqlMapClientTemplate().queryForList("Addressbook.queryAddressbookAndTypesById",id);
	}
	
	/**
	 * 删除通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public void execDeleteAddressbookById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Addressbook.execDeleteAddressbookById",id);
	}

	/**
	 * 添加通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public void execInsertAddressbook(Addressbook addressbook)throws Exception {
		getSqlMapClientTemplate().insert("Addressbook.execInsertAddressbook",addressbook);
	}

	/**
	 * 修改通讯录
	 * @author SY
	 * @time 2013-11-26 至 2013-11-29
	 * @throws Exception
	 */
	public void execUpdateAddressbook(Addressbook addressbook)throws Exception {
		getSqlMapClientTemplate().update("Addressbook.execUpdateAddressbook",addressbook);
	}


}
