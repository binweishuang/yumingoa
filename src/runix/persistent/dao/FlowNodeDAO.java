package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.FlowNode;

public interface FlowNodeDAO {

	public List queryFlowNodes(Map condition,int offset,int limit)throws Exception;

	public int queryCountFlowNodes(Map condition)throws Exception;

	public FlowNode queryFlowNodeById(String id)throws Exception;

	public void execDeleteFlowNodeById(String id)throws Exception;

	public void execInsertFlowNode(FlowNode flowNode)throws Exception;

	public void execUpdateFlowNode(FlowNode flowNode)throws Exception;
	
	/**
	 * ycr 2013-11-27
	 * 验证节点名称是否已存在
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public int queryCheckNodename(String nodename)throws Exception;
	
	/**
	 * ycr 2013-11-27
	 * 获取所有节点
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public List queryAllFlowNodes()throws Exception;

	/**
	 * ycr 2013-11-29
	 * 根据节点名称获取id
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public String queryNodeId(String nodeName)throws Exception;
	/**
	 * ycr 2013-11-29
	 * 根据id查询审核人员
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List queryLeaders(String ids)throws Exception;
	
	public void execDeleteWorkflowNodeById(String nodeId)throws Exception;
	/**
	 * ycr 2013-11-29
	 *  根据流程名称查找节点名称
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List queryNodeNamesByFlowname(String flowname)throws Exception;
}
