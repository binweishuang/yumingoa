package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseData;
import runix.persistent.model.Dept;
import runix.persistent.model.MessageCenter;
import runix.persistent.model.UserMessage;

public interface MessageCenterService {
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
	public List getMessageByNews(Map condition, int offset, int limit)
			throws Exception;
	
	public List getMessageByNews2(Map condition, int offset, int limit)
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
	public List getMessageByNotice(Map condition, int offset, int limit)
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
	public List getMessageByRules(Map condition, int offset, int limit)
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
	 * 获取所有的新闻
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByNews(Map condition) throws Exception;

	/**
	 * 获取所有的通知
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByNotice(Map condition) throws Exception;

	/**
	 * 获取所有的规章制度
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByRules(Map condition) throws Exception;

	public MessageCenter getMessageCenterById(String id) throws Exception;

	public void removeMessageCenterById(String id) throws Exception;

	public void saveMessageCenter(MessageCenter messageCenter) throws Exception;

	public void modifyMessageCenter(MessageCenter messageCenter)
			throws Exception;

	/**
	 * 获取所有的用章制度
	 *
	 * @author gao
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByChapter(Map condition) throws Exception;



	/**
	 * 获取信息类型
	 * 
	 * @author wangfq
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public List<BaseData> findTypes(String info) throws Exception;

	/**
	 * 获取所有的部门
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> findAllDepts();

	/**
	 * 持久化MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(MessageCenter mage) throws Exception;

	/**
	 * 发布MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void release(MessageCenter mage) throws Exception;

	/**
	 * 修改新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void modify(MessageCenter mage);

	/**
	 * 发布MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void rel(MessageCenter mage);

	/**
	 * 删除新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void delete(MessageCenter mage);

	/**
	 * 获取信息中心中所有的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 */
	public int getCountMessageCenter(Map<String, Object> condition);

	/**
	 * 获取信息中心中的新闻、通知和规章制度等信息
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<Map<String, String>> getMessageCenter(
			Map<String, Object> condition, int offset, int limit);
	
	public void saveUM(UserMessage userMessage)throws Exception;
	
	public void deleteUM(String messageId)throws Exception;
	
	public UserMessage getUserMessageById(String umId)throws Exception;
	
	public void modifyUserMessage(UserMessage userMessage)throws Exception;

}
