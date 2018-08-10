package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.MessageCenterDAO;
import runix.persistent.model.BaseData;
import runix.persistent.model.MessageCenter;
import runix.persistent.model.UserMessage;

public class MessageCenterDAOImpl extends BaseDAO implements MessageCenterDAO {

	public MessageCenter queryMessageCenterById(String id) throws Exception {
		return (MessageCenter) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryMessageCenterById", id);
	}

	public void execDeleteMessageCenterById(String id) throws Exception {
		getSqlMapClientTemplate().delete(
				"MessageCenter.execDeleteMessageCenterById", id);
	}

	public void execInsertMessageCenter(MessageCenter messageCenter)
			throws Exception {
		getSqlMapClientTemplate().insert(
				"MessageCenter.execInsertMessageCenter", messageCenter);
	}

	public void execUpdateMessageCenter(MessageCenter messageCenter)
			throws Exception {
		getSqlMapClientTemplate().update(
				"MessageCenter.execUpdateMessageCenter", messageCenter);
	}

	/**
	 * 持久化MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(MessageCenter mage) {
		getSqlMapClientTemplate().insert("MessageCenter.persist", mage);
	}

	/**
	 * 修改新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void update(MessageCenter mage) {
		getSqlMapClientTemplate().update("MessageCenter.update", mage);
	}

	/**
	 * 删除新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void delete(MessageCenter mage) {
		getSqlMapClientTemplate().delete("MessageCenter.delete", mage);
	}

	/**
	 * 获取新闻列表
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryMessageByNews(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"MessageCenter.queryMessageByNews", condition, offset, limit);
	}
	public List queryMessageByNews2(Map condition, int offset, int limit)
		throws Exception {
	return getSqlMapClientTemplate().queryForList(
			"MessageCenter.queryMessageByNews2", condition, offset, limit);
	}

	/**
	 * 获取通知列表
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryMessageByNotice(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"MessageCenter.queryMessageByNotice", condition, offset, limit);
	}

	/**
	 * 获取规章制度列表
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryMessageByRules(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"MessageCenter.queryMessageByRules", condition, offset, limit);
	}

	/**
	 * 获取所有的新闻
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByNews(Map condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryCountMessageByNews", condition);
	}

	/**
	 * 获取所有的通知
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByNotice(Map condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryCountMessageByNotice", condition);
	}

	/**
	 * 获取所有的规章制度
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByRules(Map condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryCountMessageByRules", condition);
	}

	/**
	 * 获取信息中心中所有的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 */
	public int queryCountMessageCenter(Map<String, Object> condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryCountMessageCenter", condition);
	}

	/**
	 * 获取信息中心中的新闻、通知和规章制度等信息
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<Map<String, String>> queryMessageCenter(
			Map<String, Object> condition, int offset, int limit) {
		return getSqlMapClientTemplate().queryForList(
				"MessageCenter.queryMessageCenter", condition, offset, limit);
	}

	public void execInsertUM(UserMessage userMessage) throws Exception {
		getSqlMapClientTemplate().insert("MessageCenter.execInsertUM", userMessage);
	}

	public void execDeleteUM(String messageId) throws Exception {
		getSqlMapClientTemplate().delete("MessageCenter.execDeleteUM", messageId);
	}

	public UserMessage queryUserMessageById(String umId) throws Exception {
		return (UserMessage) getSqlMapClientTemplate().queryForObject(
				"MessageCenter.queryUserMessageById", umId);
	}

	public void updateUserMessage(UserMessage userMessage) throws Exception {
		getSqlMapClientTemplate().update("MessageCenter.updateUserMessage", userMessage);
	}
}
