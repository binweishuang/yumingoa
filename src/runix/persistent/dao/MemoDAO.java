package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Memo;
/**
 * 我的便签DAO接口
 */
public interface MemoDAO {

	/**
	 * 查询便签列表
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return 
	 * @throws Exception
	 */
	public List queryMemos(Map condition,int offset,int limit)throws Exception;

	/**
	 * 查询便签数量
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int queryCountMemos(Map condition)throws Exception;

	/**
	 * 根据ID查询便签
	 * @author SY
	 * @time 2013-12-02
	 * @return 返回便签实体
	 * @throws Exception
	 */
	public Memo queryMemoById(String id)throws Exception;
	
	/**
	 * 根据ID查询便签
	 * @author SY
	 * @time 2013-12-02 11:30
	 * @param id
	 * @return 返回便签实例、上报人和上报对象名称
	 * @throws Exception
	 */
	public Memo queryMemoAndNamesById(String id)throws Exception;

	/**
	 * 删除便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execDeleteMemoById(String id)throws Exception;

	/**
	 * 添加便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execInsertMemo(Memo memo)throws Exception;

	/**
	 * 修改便签
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execUpdateMemo(Memo memo)throws Exception;


}
