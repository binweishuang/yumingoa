package runix.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import runix.persistent.dao.BaseDataDAO;
import runix.persistent.dao.DeptDAO;
import runix.persistent.dao.MessageCenterDAO;
import runix.persistent.model.BaseData;
import runix.persistent.model.Dept;
import runix.persistent.model.MessageCenter;
import runix.persistent.model.UserMessage;
import runix.service.BaseDataService;
import runix.service.MessageCenterService;

public class MessageCenterServiceImpl implements MessageCenterService {
	private MessageCenterDAO messageCenterDAO;
	private BaseDataDAO baseDataDAO;
	private DeptDAO deptDAO;
	private BaseDataService baseDataService;

	public BaseDataDAO getBaseDataDAO() {
		return baseDataDAO;
	}

	public void setBaseDataDAO(BaseDataDAO baseDataDAO) {
		this.baseDataDAO = baseDataDAO;
	}

	public DeptDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	/**
	 * 获取所有的新闻
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByNews(Map condition) throws Exception {
		return messageCenterDAO.queryCountMessageByNews(condition);
	}

	/**
	 * 获取所有的通知
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountMessageByNotice(Map condition) throws Exception {
		return messageCenterDAO.queryCountMessageByNotice(condition);
	}

	/**
	 * 获取所有的规章制度
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */

	public int getCountMessageByChapter(Map condition) throws Exception {
		return messageCenterDAO.getCountMessageByChapter(condition);
	}

	public int getCountMessageByRules(Map condition) throws Exception {
		return messageCenterDAO.queryCountMessageByRules(condition);
	}

	public MessageCenter getMessageCenterById(String id) throws Exception {
		return messageCenterDAO.queryMessageCenterById(id);
	}

	public void removeMessageCenterById(String id) throws Exception {
		messageCenterDAO.execDeleteMessageCenterById(id);
	}

	public void saveMessageCenter(MessageCenter messageCenter) throws Exception {
		messageCenterDAO.execInsertMessageCenter(messageCenter);
	}

	public void modifyMessageCenter(MessageCenter messageCenter)
			throws Exception {
		messageCenterDAO.execUpdateMessageCenter(messageCenter);
	}

	public void setMessageCenterDAO(MessageCenterDAO messageCenterDAO) {
		this.messageCenterDAO = messageCenterDAO;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	/**
	 * 获取信息类型
	 * 
	 * @author wangfq
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public List<BaseData> findTypes(String info) throws Exception {
		if(info!=null && !"".equals(info)){
			info = info.toUpperCase();
		}
		return baseDataDAO.queryBaseByDatatype(info);
	}

	/**
	 * 获取所有的部门
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Dept> findAllDepts() {
		return deptDAO.getAllDepts();
	}

	/**
	 * 持久化MessageCenter
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void persist(MessageCenter mage) throws Exception {
		//mage.setPublisher("judy");
		mage.setStatus("0");// 未发布
		if ("-1".equals(mage.getType())) {
			mage.setType("");
		}
		messageCenterDAO.persist(mage);
	}

	/**
	 * 发布MessageCenter-添加
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public void release(MessageCenter mage) throws Exception {
		//mage.setMessageId(baseDataService.getSequence());
		//mage.setPublisher("judy");
		mage.setStatus("1");// 发布
		mage.setPublishtime(new Date());
		if ("-1".equals(mage.getType())) {
			mage.setType("");
		}
		messageCenterDAO.persist(mage);
	}

	/**
	 * 修改信息
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void modify(MessageCenter mage) {
		if ("-1".equals(mage.getType())) {
			mage.setType("");
		}

		messageCenterDAO.update(mage);
	}

	/**
	 * 发布新闻-修改
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void rel(MessageCenter mage) {
		mage.setStatus("1");
		mage.setPublishtime(new Date());
		if ("-1".equals(mage.getType())) {
			mage.setType("");
		}
		messageCenterDAO.update(mage);
	}

	/**
	 * 删除新闻
	 * 
	 * @author wangfq
	 * @param mage
	 */
	public void delete(MessageCenter mage) {
		messageCenterDAO.delete(mage);
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
	public List getMessageByNews(Map condition, int offset, int limit)
			throws Exception {
		return messageCenterDAO.queryMessageByNews(condition, offset, limit);
	}
	
	public List getMessageByNews2(Map condition, int offset, int limit)
		throws Exception {
		return messageCenterDAO.queryMessageByNews2(condition, offset, limit);
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
	public List getMessageByNotice(Map condition, int offset, int limit)
			throws Exception {
		return messageCenterDAO.queryMessageByNotice(condition, offset, limit);
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
	public List getMessageByRules(Map condition, int offset, int limit)
			throws Exception {
		return messageCenterDAO.queryMessageByRules(condition, offset, limit);
	}


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
			throws Exception {
		return messageCenterDAO.getMessageByChapter(condition, offset, limit);
	}

	/**
	 * 获取信息中心中所有的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 */
	public int getCountMessageCenter(Map<String, Object> condition) {
		return messageCenterDAO.queryCountMessageCenter(condition);
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
	public List<Map<String, String>> getMessageCenter(
			Map<String, Object> condition, int offset, int limit) {
		return messageCenterDAO.queryMessageCenter(condition, offset, limit);
	}

	public void saveUM(UserMessage userMessage) throws Exception {
		messageCenterDAO.execInsertUM(userMessage);
	}

	public void deleteUM(String messageId) throws Exception {
		messageCenterDAO.execDeleteUM(messageId);
	}

	public UserMessage getUserMessageById(String umId) throws Exception {
		return messageCenterDAO.queryUserMessageById(umId);
	}

	public void modifyUserMessage(UserMessage userMessage) throws Exception {
		messageCenterDAO.updateUserMessage(userMessage);
	}
}
