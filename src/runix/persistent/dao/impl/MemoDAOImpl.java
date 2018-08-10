package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.MemoDAO;
import runix.persistent.model.Memo;

/**
 * 我的便签DAO实现类
 */
public class MemoDAOImpl extends BaseDAO implements MemoDAO {

	/**
	 * 按条件查询便签列表
	 * @author SY
	 * @time 2013-12-02
	 */
	public List queryMemos(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Memo.queryMemos",condition,offset,limit);
	}

	/**
	 * 按条件查询便签数量
	 * @author SY
	 * @time 2013-12-02
	 */
	public int queryCountMemos(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Memo.queryCountMemos",condition);
	}

	/**
	 * 根据ID查询便签   返回便签实体
	 * @author SY
	 * @time 2013-12-02
	 */
	public Memo queryMemoById(String id)throws Exception {
		return (Memo)getSqlMapClientTemplate().queryForObject("Memo.queryMemoById",id);
	}

	/**
	 * 根据ID查看便签  返回便签实例、上报人和上报对象名称
	 * @author SY
	 * @time 2013-12-02 11:33
	 */
	public Memo queryMemoAndNamesById(String id)throws Exception{
		return (Memo)getSqlMapClientTemplate().queryForObject("Memo.queryMemoAndNamesById",id);
	}
	
	/**
	 * 删除便签
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execDeleteMemoById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Memo.execDeleteMemoById",id);
	}

	/**
	 * 添加便签
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execInsertMemo(Memo memo)throws Exception {
		getSqlMapClientTemplate().insert("Memo.execInsertMemo",memo);
	}

	/**
	 * 修改便签
	 * @author SY
	 * @time 2013-12-02
	 */
	public void execUpdateMemo(Memo memo)throws Exception {
		getSqlMapClientTemplate().update("Memo.execUpdateMemo",memo);
	}


}
