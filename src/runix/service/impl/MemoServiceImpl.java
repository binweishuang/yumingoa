package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.MemoDAO;
import runix.service.MemoService;
import runix.persistent.model.Memo;

/**
 * 便签管理service实现类
 */
public class MemoServiceImpl  implements MemoService {
	private MemoDAO memoDAO;

	/**
	 * 按条件查询便签列表
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getMemos(Map condition,int offset,int limit)throws Exception {
		return memoDAO.queryMemos(condition,offset,limit);
	}

	/**
	 * 按条件查询便签数量
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountMemos(Map condition)throws Exception {
		return memoDAO.queryCountMemos(condition);
	}
	
	/**
	 * 根据ID查看便签   
	 */
	public Memo getMemoById(String id)throws Exception {
		return memoDAO.queryMemoById(id);
	}

	/**
	 * 根据ID查看便签  返回便签实例、上报人和上报对象的名称
	 * @author SY
	 * @time 2013-12-02 11:41
	 */
	public Memo getMemoAndNamesById(String id)throws Exception{
		return memoDAO.queryMemoAndNamesById(id);
	}
	
	/**
	 * 删除便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void removeMemoById(String id)throws Exception {
		 memoDAO.execDeleteMemoById(id);
	}

	/**
	 * 添加便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void saveMemo(Memo memo)throws Exception {
		 memoDAO.execInsertMemo(memo);
	}

	/**
	 * 修改便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void modifyMemo(Memo memo)throws Exception {
		 memoDAO.execUpdateMemo(memo);
	}


	public void setMemoDAO(MemoDAO memoDAO){
		this.memoDAO=memoDAO;
	}

}
