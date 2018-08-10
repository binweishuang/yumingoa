package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.DutyMessageDAO;
import runix.persistent.model.DutyMessage;

/**
 * 值班信息DAO实现类
 */
public class DutyMessageDAOImpl extends BaseDAO implements DutyMessageDAO {

	/**
	 * 按条件查询值班信息列表
	 * @author SY
	 * @time 2013-12-02
	 */
	public List queryDutyMessages(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("DutyMessage.queryDutyMessages",condition,offset,limit);
	}

	/**
	 * 按条件查询值班信息数量
	 * @author SY
	 * @time 2013-12-02
	 */
	public int queryCountDutyMessages(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("DutyMessage.queryCountDutyMessages",condition);
	}

	/**
	 * 根据ID查询值班信息   返回实体对象
	 * @author SY
	 * @time 2013-12-02
	 */
	public DutyMessage queryDutyMessageById(String id)throws Exception {
		return (DutyMessage)getSqlMapClientTemplate().queryForObject("DutyMessage.queryDutyMessageById",id);
	}

	/**
	 * 根据ID查询值班信息和值班人名称
	 * @author SY
	 * @time 2013-12-02 16:42
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryDutyMessageAndNameById(String id)throws Exception{
		return getSqlMapClientTemplate().queryForList("DutyMessage.queryDutyMessageAndNameById",id);
	}
	
	/**
	 * 删除值班信息
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execDeleteDutyMessageById(String id)throws Exception {
		getSqlMapClientTemplate().delete("DutyMessage.execDeleteDutyMessageById",id);
	}

	/**
	 * 添加值班信息
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execInsertDutyMessage(DutyMessage dutyMessage)throws Exception {
		getSqlMapClientTemplate().insert("DutyMessage.execInsertDutyMessage",dutyMessage);
	}

	/**
	 * 修改值班信息
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execUpdateDutyMessage(DutyMessage dutyMessage)throws Exception {
		getSqlMapClientTemplate().update("DutyMessage.execUpdateDutyMessage",dutyMessage);
	}


}
