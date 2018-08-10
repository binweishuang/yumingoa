package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.WorkflowDAO;
import runix.persistent.model.Workflow;
import runix.persistent.model.WorkflowNode;

public class WorkflowDAOImpl extends BaseDAO implements WorkflowDAO {

	public List queryWorkflows(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Workflow.queryWorkflows",condition,offset,limit);
	}


	public int queryCountWorkflows(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Workflow.queryCountWorkflows",condition);
	}


	public Workflow queryWorkflowById(String id)throws Exception {
		return (Workflow)getSqlMapClientTemplate().queryForObject("Workflow.queryWorkflowById",id);
	}


	public void execDeleteWorkflowById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Workflow.execDeleteWorkflowById",id);
	}


	public void execInsertWorkflow(Workflow workflow)throws Exception {
		getSqlMapClientTemplate().insert("Workflow.execInsertWorkflow",workflow);
	}


	public void execUpdateWorkflow(Workflow workflow)throws Exception {
		getSqlMapClientTemplate().update("Workflow.execUpdateWorkflow",workflow);
	}


	public List queryAllWorkflows() throws Exception {
		return getSqlMapClientTemplate().queryForList("Workflow.queryAllWorkflows");
	}


	public List queryFlowNode(String workflowId) throws Exception {
		return getSqlMapClientTemplate().queryForList("Workflow.queryFlowNode",workflowId);
	}


	public void execUpdateFlowNode(String workflowId) throws Exception {
		getSqlMapClientTemplate().update("Workflow.execUpdateFlowNode",workflowId);
	}


	public void execInsertWorkflowNode(WorkflowNode workflowNode) throws Exception {
		getSqlMapClientTemplate().insert("Workflow.execInsertWorkflowNode",workflowNode);
	}


}
