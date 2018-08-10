package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.SuggestionDAO;
import runix.persistent.model.Suggestion;

public class SuggestionDAOImpl extends BaseDAO implements SuggestionDAO {
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
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"Suggestion.querySuggestions", condition, offset, limit);
	}

	/**
	 * 获取个人建议数据表中的符合条件的纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountSuggestions(Map condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Suggestion.queryCountSuggestions", condition);
	}

	public Suggestion querySuggestionById(String id) throws Exception {
		return (Suggestion) getSqlMapClientTemplate().queryForObject(
				"Suggestion.querySuggestionById", id);
	}

	public void execDeleteSuggestionById(String id) throws Exception {
		getSqlMapClientTemplate().delete("Suggestion.execDeleteSuggestionById",
				id);
	}

	public void execInsertSuggestion(Suggestion suggestion) throws Exception {
		getSqlMapClientTemplate().insert("Suggestion.execInsertSuggestion",
				suggestion);
	}

	public void execUpdateSuggestion(Suggestion suggestion) throws Exception {
		getSqlMapClientTemplate().update("Suggestion.execUpdateSuggestion",
				suggestion);
	}

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @param suggestionId
	 */
	public void delete(String suggestionId) {
		getSqlMapClientTemplate().delete("Suggestion.delete", suggestionId);
	}

	/**
	 * 持久化个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 */
	public void persist(Suggestion suggestion) {
		getSqlMapClientTemplate().insert("Suggestion.persist", suggestion);
	}

	/**
	 * 获取指定的suggestion
	 * 
	 * @author wangfq
	 * @param suggestionId
	 * @return
	 */
	public Suggestion findSuggById(String suggestionId) {
		return (Suggestion) getSqlMapClientTemplate().queryForObject(
				"Suggestion.findSuggById", suggestionId);
	}

	/**
	 * 修改个人建议
	 * 
	 * @author wangfq
	 * @param suggestion
	 * @throws Exception
	 */
	public void update(Suggestion suggestion) {
		getSqlMapClientTemplate().update("Suggestion.update",
				suggestion);
	}

}
