package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Matter;

public interface MatterService {

	public List getMatters(Map condition,int offset,int limit)throws Exception;

	public int getCountMatters(Map condition)throws Exception;

	public Matter getMatterById(String id)throws Exception;

	public void removeMatterById(String id)throws Exception;

	public void saveMatter(Matter matter)throws Exception;

	public void modifyMatter(Matter matter)throws Exception;

	/**
	 * 根据关联表的id删除事项
	 * @param relateId
	 * @throws Exception
	 */
	public void removeMatterByRelateId(String relateId)throws Exception;
	
	/**
	 * ycr 2013-12-31 已办事项查询
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List getMatters2(Map condition,int offset,int limit)throws Exception;
	public int getCountMatters2(Map condition)throws Exception;
	
	public List getMatters3(Map condition,int offset,int limit)throws Exception;
	public int getCountMatters3(Map condition)throws Exception;
}
