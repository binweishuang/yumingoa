package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.NetmeetingDAO;
import runix.persistent.model.Netmeeting;

/**
 * 网络会议DAO实现类
 */
public class NetmeetingDAOImpl extends BaseDAO implements NetmeetingDAO {

	/**
	 * 按条件网络会议列表
	 * @author SY
	 * @time 
	 */
	public List queryNetmeetings(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Netmeeting.queryNetmeetings",condition,offset,limit);
	}

	/**
	 * 按条件网络会议数量
	 * @author SY
	 * @time 
	 */
	public int queryCountNetmeetings(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Netmeeting.queryCountNetmeetings",condition);
	}

	/**
	 * 根据ID查询网络会议   返回实体对象
	 * @author SY
	 * @time 
	 */
	public Netmeeting queryNetmeetingById(String id)throws Exception {
		return (Netmeeting)getSqlMapClientTemplate().queryForObject("Netmeeting.queryNetmeetingById",id);
	}

	/**
	 * 删除网络会议
	 * @author SY
	 * @time 
	 */
	public void execDeleteNetmeetingById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Netmeeting.execDeleteNetmeetingById",id);
	}

	/**
	 * 添加网络会议
	 * @author SY
	 * @time 
	 */
	public void execInsertNetmeeting(Netmeeting netmeeting)throws Exception {
		getSqlMapClientTemplate().insert("Netmeeting.execInsertNetmeeting",netmeeting);
	}

	/**
	 * 修改网络会议
	 * @author SY
	 * @time 
	 */
	public void execUpdateNetmeeting(Netmeeting netmeeting)throws Exception {
		getSqlMapClientTemplate().update("Netmeeting.execUpdateNetmeeting",netmeeting);
	}


}
