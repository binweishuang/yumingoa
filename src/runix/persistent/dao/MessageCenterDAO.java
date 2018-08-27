package runix.persistent.dao;

import java.util.List;
import java.util.Map;

import runix.persistent.model.MessageCenter;
import runix.persistent.model.UserMessage;

public interface MessageCenterDAO {
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
			throws Exception;
	
	public List queryMessageByNews2(Map condition, int offset, int limit)
	throws Exception;

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
			throws Exception;

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
			throws Exception;

	public MessageCenter queryMessageCenterById(String id) throws Exception;

	public void execDeleteMessageCenterById(String id) throws Exception;

	public void execInsertMessageCenter(MessageCenter messageCenter)
			throws Exception;

	public void execUpdateMessageCenter(MessageCenter messageCenter)
			throws Exception;

	/**
	 * 获取用章制度列表
	 *
	 * @author gao
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getMessageByChapter(Map condition, int offset, int limit)
			throws Exception;



	/**
	 * 持久化MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(MessageCenter mage);

	/**
	 * 修改新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void update(MessageCenter mage);

	/**
	 * 删除新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void delete(MessageCenter mage);

	/**
	 * 获取所有的新闻
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByNews(Map condition);

	/**
	 * 获取所有的通知
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByNotice(Map condition);

	/**
	 * 获取所有的规章制度
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountMessageByRules(Map condition);

	/**
	 * 获取所有的规章制度
	 *
	 * @author gao
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByChapter(Map condition);

	/**
	 * 获取信息中心中所有的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 */
	public int queryCountMessageCenter(Map<String, Object> condition);

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
			Map<String, Object> condition, int offset, int limit);

	public void execInsertUM(UserMessage userMessage) throws Exception;
	
	public void execDeleteUM(String messageId)throws Exception;
	
	public UserMessage queryUserMessageById(String umId)throws Exception;
	
	public void updateUserMessage(UserMessage userMessage)throws Exception;
}
