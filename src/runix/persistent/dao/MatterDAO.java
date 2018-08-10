package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Matter;

public interface MatterDAO {

	public List queryMatters(Map condition,int offset,int limit)throws Exception;

	public int queryCountMatters(Map condition)throws Exception;

	public Matter queryMatterById(String id)throws Exception;

	public void execDeleteMatterById(String id)throws Exception;

	public void execInsertMatter(Matter matter)throws Exception;

	public void execUpdateMatter(Matter matter)throws Exception;

	/**
	 * 根据关联表的id删除事项
	 * @param relateId
	 * @throws Exception
	 */
	public void execDeleteMatterByRelateId(String relateId)throws Exception;
	
	/**
	 * ycr 2013-12-31 已办事项查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List queryMatters2(Map condition,int offset,int limit)throws Exception;
	public int queryCountMatters2(Map condition)throws Exception;
	
	public List queryMatters3(Map condition,int offset,int limit)throws Exception;
	public int queryCountMatters3(Map condition)throws Exception;
}
