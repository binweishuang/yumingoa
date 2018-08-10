package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Memo;

/**
 * 便签管理service接口
 */
public interface MemoService {

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
	public List getMemos(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询便签数量
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountMemos(Map condition)throws Exception;

	/**
	 * 根据ID查看便签
	 * @param id
	 * @return 返回便签实例
	 * @throws Exception
	 */
	public Memo getMemoById(String id)throws Exception;
	
	/**
	 * 根据ID查看便签
	 * @author SY
	 * @time 2013-12-02 11：45
	 * @param id
	 * @return 返回便签实例、上报人和上报对象名称
	 * @throws Exception
	 */
	public Memo getMemoAndNamesById(String id)throws Exception;

	/**
	 * 删除便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void removeMemoById(String id)throws Exception;

	/**
	 * 添加便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void saveMemo(Memo memo)throws Exception;

	/**
	 * 修改便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void modifyMemo(Memo memo)throws Exception;


}
