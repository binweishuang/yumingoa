package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.FlowNodeDAO;
import runix.persistent.model.FlowNode;

public class FlowNodeDAOImpl extends BaseDAO implements FlowNodeDAO {

	public List queryFlowNodes(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("FlowNode.queryFlowNodes",condition,offset,limit);
	}


	public int queryCountFlowNodes(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("FlowNode.queryCountFlowNodes",condition);
	}


	public FlowNode queryFlowNodeById(String id)throws Exception {
		return (FlowNode)getSqlMapClientTemplate().queryForObject("FlowNode.queryFlowNodeById",id);
	}


	public void execDeleteFlowNodeById(String id)throws Exception {
		getSqlMapClientTemplate().delete("FlowNode.execDeleteFlowNodeById",id);
	}


	public void execInsertFlowNode(FlowNode flowNode)throws Exception {
		getSqlMapClientTemplate().insert("FlowNode.execInsertFlowNode",flowNode);
	}


	public void execUpdateFlowNode(FlowNode flowNode)throws Exception {
		getSqlMapClientTemplate().update("FlowNode.execUpdateFlowNode",flowNode);
	}


	public int queryCheckNodename(String nodename) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("FlowNode.queryCheckNodename",nodename);
	}


	public List queryAllFlowNodes() throws Exception {
		return getSqlMapClientTemplate().queryForList("FlowNode.queryAllFlowNodes");
	}


	public String queryNodeId(String nodeName) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("FlowNode.queryNodeId",nodeName);
	}


	public List queryLeaders(String ids) throws Exception {
		return getSqlMapClientTemplate().queryForList("BaseUser.queryLeaders",ids);
	}


	public void execDeleteWorkflowNodeById(String nodeId) throws Exception {
		getSqlMapClientTemplate().delete("FlowNode.execDeleteWorkflowNodeById",nodeId);
	}


	public List queryNodeNamesByFlowname(String flowname) throws Exception {
		return getSqlMapClientTemplate().queryForList("FlowNode.queryNodeNamesByFlowname",flowname);
	}


}
