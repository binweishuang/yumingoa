package runix.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import runix.persistent.dao.BaseDataDAO;
import runix.persistent.dao.BaseUserDAO;
import runix.persistent.dao.SuggestionDAO;
import runix.persistent.model.BaseData;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Suggestion;
import runix.service.BaseDataService;
import runix.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {
	private SuggestionDAO suggestionDAO;
	private BaseDataDAO baseDataDAO;
	private BaseDataService baseDataService;
	private BaseUserDAO baseUserDAO;

	public BaseDataDAO getBaseDataDAO() {
		return baseDataDAO;
	}

	public void setBaseDataDAO(BaseDataDAO baseDataDAO) {
		this.baseDataDAO = baseDataDAO;
	}

	/**
	 * 获取个人建议表中的符合条件的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSuggestions(Map condition, int offset,
			int limit) throws Exception {
		return suggestionDAO.querySuggestions(condition, offset, limit);
	}

	/**
	 * 获取个人建议数据表中的符合条件的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountSuggestions(Map condition) throws Exception {
		return suggestionDAO.queryCountSuggestions(condition);
	}

	public Suggestion getSuggestionById(String id) throws Exception {
		return suggestionDAO.querySuggestionById(id);
	}

	public void removeSuggestionById(String id) throws Exception {
		suggestionDAO.execDeleteSuggestionById(id);
	}

	public void saveSuggestion(Suggestion suggestion) throws Exception {
		suggestionDAO.execInsertSuggestion(suggestion);
	}

	public void modifySuggestion(Suggestion suggestion) throws Exception {
		suggestionDAO.execUpdateSuggestion(suggestion);
	}

	public void setSuggestionDAO(SuggestionDAO suggestionDAO) {
		this.suggestionDAO = suggestionDAO;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public BaseUserDAO getBaseUserDAO() {
		return baseUserDAO;
	}

	public void setBaseUserDAO(BaseUserDAO baseUserDAO) {
		this.baseUserDAO = baseUserDAO;
	}

	/**
	 * 获取建议类型
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public List<BaseData> findTypesBySuggestion() throws Exception {
		return baseDataDAO.queryBaseByDatatype("SUGGEST");
	}

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @param suggestionId
	 */
	public void delete(String suggestionId) {
		suggestionDAO.delete(suggestionId);
	}

	/**
	 * 判断指定的类型是否存在
	 * 
	 * @author wangfq
	 * @param suggesttype
	 * @throws Exception
	 */
	public boolean isExist(String suggesttype) throws Exception {
		List<BaseData> types = baseDataDAO.queryBaseByDatatype(suggesttype);
		if (types != null && !types.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 持久化个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @throws Exception
	 */
	public void persist(Suggestion suggestion) throws Exception {
		//suggestion.setSuggestionId(baseDataService.getSequence());
		suggestionDAO.persist(suggestion);
	}

	/**
	 * 获取指定的suggestion
	 * 
	 * @author wangfq
	 * @param suggestionId
	 * @return
	 */
	public Suggestion findSuggById(String suggestionId) {
		return suggestionDAO.findSuggById(suggestionId);
	}

	/**
	 * 修改个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @throws Exception
	 */
	public void update(Suggestion suggestion) {
		suggestionDAO.update(suggestion);
	}

	/**
	 * 解析建议人员
	 * 
	 * @author wangfq
	 * @param lst
	 * @return
	 */
	public List<Map<String, String>> format(List<Map<String, String>> lst) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (lst != null && !lst.isEmpty()) {
			for (Map<String, String> map : lst) {
				String strVal = map.get("TOPERSON");
				String toperson = transforNameById(strVal);
				map.put("TOPERSON", toperson);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 将给定的id转换为姓名
	 * 
	 * @author wangfq
	 * @param id
	 * @return
	 */
	private String transforNameById(String id) {
		String toperson = "";
		if (id != null && !"".equals(id)) {
			String[] arrays = id.split(",");
			for (String array : arrays) {
				BaseUser user = new BaseUser();
				user.setUserId(array);
				user = baseUserDAO.getUserById(user);
				if (user != null) {
					toperson += user.getName() + ",";
				}
			}
		}
		if (!"".equals(toperson)) {
			toperson = toperson.substring(0, toperson.length() - 1);
		}
		return toperson;
	}

	/**
	 * 通过用户的id获取用户的姓名
	 * 
	 * @author wangfq
	 * @param suggestionId
	 * @return
	 */
	public String findNamesById(Suggestion suggestion) {
		return transforNameById(suggestion.getToperson());
	}
}
