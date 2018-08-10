package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.FlowNodeDAO;
import runix.service.FlowNodeService;
import runix.persistent.model.FlowNode;

public class FlowNodeServiceImpl  implements FlowNodeService {
	private FlowNodeDAO flowNodeDAO;


	public List getFlowNodes(Map condition,int offset,int limit)throws Exception {
		return flowNodeDAO.queryFlowNodes(condition,offset,limit);
	}


	public int getCountFlowNodes(Map condition)throws Exception {
		return flowNodeDAO.queryCountFlowNodes(condition);
	}


	public FlowNode getFlowNodeById(String id)throws Exception {
		return flowNodeDAO.queryFlowNodeById(id);
	}


	public void removeFlowNodeById(String id)throws Exception {
		 flowNodeDAO.execDeleteFlowNodeById(id);
	}


	public void saveFlowNode(FlowNode flowNode)throws Exception {
		 flowNodeDAO.execInsertFlowNode(flowNode);
	}


	public void modifyFlowNode(FlowNode flowNode)throws Exception {
		 flowNodeDAO.execUpdateFlowNode(flowNode);
	}


	public void setFlowNodeDAO(FlowNodeDAO flowNodeDAO){
		this.flowNodeDAO=flowNodeDAO;
	}


	public int checkNodename(String nodename) throws Exception { 
		return flowNodeDAO.queryCheckNodename(nodename.trim());
	}


	public List getAllFlowNodes() throws Exception {
		return flowNodeDAO.queryAllFlowNodes();
	}


	public String getNodeId(String nodeName) throws Exception {
		return flowNodeDAO.queryNodeId(nodeName);
	}


	public List getLeaders(String ids) throws Exception {
		return flowNodeDAO.queryLeaders(ids);
	}


	public void removeWorkflowNodeById(String nodeId) throws Exception {
		flowNodeDAO.execDeleteWorkflowNodeById(nodeId);
	}


	public List getNodeNamesByFlowname(String flowname) throws Exception {
		return flowNodeDAO.queryNodeNamesByFlowname(flowname);
	}

}
