package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseData;
import runix.persistent.model.Suggestion;

public interface SuggestionService {
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
			int limit) throws Exception;

	/**
	 * 获取个人建议数据表中的符合条件的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountSuggestions(Map condition) throws Exception;

	public Suggestion getSuggestionById(String id) throws Exception;

	public void removeSuggestionById(String id) throws Exception;

	public void saveSuggestion(Suggestion suggestion) throws Exception;

	public void modifySuggestion(Suggestion suggestion) throws Exception;

	/**
	 * 获取建议类型
	 * 
	 * @author wangfq
	 * @return
	 * @throws Exception
	 */
	public List<BaseData> findTypesBySuggestion() throws Exception;

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @param suggestionId
	 */
	public void delete(String suggestionId);

	/**
	 * 判断指定的类型是否存在
	 * 
	 * @author wangfq
	 * @param suggesttype
	 * @throws Exception
	 */
	public boolean isExist(String suggesttype) throws Exception;

	/**
	 * 持久化个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @throws Exception
	 */
	public void persist(Suggestion suggestion) throws Exception;

	/**
	 * 获取指定的suggestion
	 * 
	 * @author wangfq
	 * @param suggestionId
	 * @return
	 */
	public Suggestion findSuggById(String suggestionId);

	/**
	 * 修改个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @throws Exception
	 */
	public void update(Suggestion suggestion);

	/**
	 * 解析建议人员
	 * 
	 * @author wangfq
	 * @param lst
	 * @return
	 */
	public List<Map<String, String>> format(List<Map<String, String>> lst);

	/**
	 * 通过用户的id获取用户的姓名
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @return
	 */
	public String findNamesById(Suggestion suggestion);

}
