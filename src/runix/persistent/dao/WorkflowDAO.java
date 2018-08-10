package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Workflow;
import runix.persistent.model.WorkflowNode;

public interface WorkflowDAO {

	public List queryWorkflows(Map condition,int offset,int limit)throws Exception;

	public int queryCountWorkflows(Map condition)throws Exception;

	public Workflow queryWorkflowById(String id)throws Exception;

	public void execDeleteWorkflowById(String id)throws Exception;

	public void execInsertWorkflow(Workflow workflow)throws Exception;

	public void execUpdateWorkflow(Workflow workflow)throws Exception;
	
	/*
	 * 查询所有流程列表
	 * ycr 2013-11-27
	 */
	public List queryAllWorkflows()throws Exception;

	/**
	 * ycr 2013-11-28
	 * 根据流程类型查询对应节点
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
    public List queryFlowNode(String workflowId)throws Exception;
    
    /**
	 * ycr 2013-11-28
	 * 删除流程类型对应的节点
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
    public void execUpdateFlowNode(String workflowId)throws Exception;
    
    /**
	 * ycr 2013-11-28
	 * 保存流程和节点对应关系
	 * @param workflowId
	 * @return
	 * @throws Exception
	 */
    public void execInsertWorkflowNode(WorkflowNode workflowNode)throws Exception;
}
