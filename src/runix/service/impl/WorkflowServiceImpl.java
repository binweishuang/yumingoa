package runix.service.impl;

import java.util.List;
import java.util.Map;

import runix.persistent.dao.WorkflowDAO;
import runix.persistent.model.Workflow;
import runix.persistent.model.WorkflowNode;
import runix.service.WorkflowService;

public class WorkflowServiceImpl  implements WorkflowService {
	private WorkflowDAO workflowDAO;


	public List getWorkflows(Map condition,int offset,int limit)throws Exception {
		return workflowDAO.queryWorkflows(condition,offset,limit);
	}


	public int getCountWorkflows(Map condition)throws Exception {
		return workflowDAO.queryCountWorkflows(condition);
	}


	public Workflow getWorkflowById(String id)throws Exception {
		return workflowDAO.queryWorkflowById(id);
	}


	public void removeWorkflowById(String id)throws Exception {
		 workflowDAO.execDeleteWorkflowById(id);
	}


	public void saveWorkflow(Workflow workflow)throws Exception {
		 workflowDAO.execInsertWorkflow(workflow);
	}


	public void modifyWorkflow(Workflow workflow)throws Exception {
		 workflowDAO.execUpdateWorkflow(workflow);
	}


	public void setWorkflowDAO(WorkflowDAO workflowDAO){
		this.workflowDAO=workflowDAO;
	}


	public List getAllWorkflows() throws Exception {
		return workflowDAO.queryAllWorkflows();
	}


	public List getFlowNode(String workflowId) throws Exception {
		return workflowDAO.queryFlowNode(workflowId);
	}


	public void modifyFlowNode(String workflowId) throws Exception {
		workflowDAO.execUpdateFlowNode(workflowId);
	}


	public void saveWorkflowNode(WorkflowNode workflowNode)
			throws Exception {
		workflowDAO.execInsertWorkflowNode(workflowNode);
	}

}
