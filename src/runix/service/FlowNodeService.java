package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.FlowNode;

public interface FlowNodeService {

	public List getFlowNodes(Map condition,int offset,int limit)throws Exception;

	public int getCountFlowNodes(Map condition)throws Exception;

	public FlowNode getFlowNodeById(String id)throws Exception;

	public void removeFlowNodeById(String id)throws Exception;

	public void saveFlowNode(FlowNode flowNode)throws Exception;

	public void modifyFlowNode(FlowNode flowNode)throws Exception;
	
	/**
	 * ycr 2013-11-27
	 * 验证节点名称是否已存在
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public int checkNodename(String nodename)throws Exception;
	
	/**
	 * ycr 2013-11-27
	 * 获取所有节点
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public List getAllFlowNodes()throws Exception;
	
	/**
	 * ycr 2013-11-29
	 * 根据节点名称获取id
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public String getNodeId(String nodeName)throws Exception;
	/**
	 * ycr 2013-11-29
	 * 根据id查询审核人员
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List getLeaders(String ids)throws Exception;
	
	public void removeWorkflowNodeById(String nodeId)throws Exception;
	/**
	 * ycr 2013-12-2
	 * 根据流程名称查找节点名称
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List getNodeNamesByFlowname(String flowname)throws Exception;

}
