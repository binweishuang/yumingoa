package runix.persistent.dao;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Suggestion;

public interface SuggestionDAO {
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
	public List<Map<String, String>> querySuggestions(Map condition, int offset, int limit)
			throws Exception;

	/**
	 * 获取个人建议数据表中的符合条件的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountSuggestions(Map condition) throws Exception;

	public Suggestion querySuggestionById(String id) throws Exception;

	public void execDeleteSuggestionById(String id) throws Exception;

	public void execInsertSuggestion(Suggestion suggestion) throws Exception;

	public void execUpdateSuggestion(Suggestion suggestion) throws Exception;

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @param suggestionId
	 */
	public void delete(String suggestionId);

	/**
	 * 持久化个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 */
	public void persist(Suggestion suggestion);

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

}
