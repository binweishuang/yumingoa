package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.Workflow;
import runix.persistent.model.WorkflowNode;

public interface WorkflowService {

	public List getWorkflows(Map condition,int offset,int limit)throws Exception;

	public int getCountWorkflows(Map condition)throws Exception;

	public Workflow getWorkflowById(String id)throws Exception;

	public void removeWorkflowById(String id)throws Exception;

	public void saveWorkflow(Workflow workflow)throws Exception;

	public void modifyWorkflow(Workflow workflow)throws Exception;
	
	/*
	 * 查询所有流程列表
	 * ycr 2013-11-27
	 */
	public List getAllWorkflows()throws Exception;
	
	/**
	 * ycr 2013-11-28
	 * 根据流程类型查询对应节点
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
	public List getFlowNode(String workflowId)throws Exception;
	
	/**
	 * ycr 2013-11-28
	 * 删除流程类型对应的节点
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
	public void modifyFlowNode(String workflowId)throws Exception;
	
	/**
	 * ycr 2013-11-28
	 * 保存流程和节点对应关系
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
	public void saveWorkflowNode(WorkflowNode workflowNode)throws Exception;
}
