package runix.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Workflow;
import runix.persistent.model.WorkflowNode;
import runix.service.BaseDataService;
import runix.service.FlowNodeService;
import runix.service.WorkflowService;

public class WorkflowAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List workflowList;
	private String workflowId_q;
	private String workflowId;
	private String flowname;
	private String flag;
	private String nodename;//节点名称
	private String messageStr;//返回页面信息

	
	private List flowNodeList;//所有节点列表

	private WorkflowService workflowService;
	private FlowNodeService flowNodeService;
	private BaseDataService baseDataService;


	public String doQuery(){
		try {
			int currentPageInt = 1;
			String strCurrentPage = currentPage;
			if (strCurrentPage != null && !"".equals(strCurrentPage)) {
				try {
					currentPageInt = Integer.parseInt(strCurrentPage);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					this.addActionError("please check! there is Exception");
				}
			}
			int offset = (currentPageInt-1) * pages;
			int limit = pages;

			Map condition = new HashMap();
			condition.put("workflowId",  "".equals(workflowId)?"":"%"+workflowId+"%");
			condition.put("flowname",  "".equals(flowname)?"":"%"+flowname+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = workflowService.getCountWorkflows(condition);
			List lst =workflowService.getWorkflows(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			workflowList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doWorkflowPage(){

		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
//			Workflow workflow =workflowService.getWorkflowById(workflowId_q);
//			workflowId = workflow.getWorkflowId();
//			flowname = workflow.getFlowname();
//			flag = workflow.getFlag();
			workflowList =workflowService.getAllWorkflows();//查询所有流程列表
			
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doSelectNode(){
		try{
			flowNodeList = flowNodeService.getAllFlowNodes();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	public String doUpdate(){
		try {
			workflowList =workflowService.getAllWorkflows();//查询所有流程列表
			workflowService.modifyFlowNode(workflowId);
			String nodes[] = nodename.split(",");
			String wnid = "";
			String nodeId ="";
			for(int i=0;i<nodes.length;i++){
				nodeId = flowNodeService.getNodeId(nodes[i].trim());
				WorkflowNode workflowNode = new WorkflowNode();
			   // wnid = baseDataService.getSequence();
			   // workflowNode.setWnid(wnid);
			    workflowNode.setWorkflowId(workflowId);
			    workflowNode.setNodeId(nodeId);
				workflowService.saveWorkflowNode(workflowNode);
			}
			messageStr = "数据保存成功！";
			this.addActionMessage(messageStr);
			
	//		workflowService.modifyWorkflow(workflow);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			workflowService.removeWorkflowById(workflowId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			readonly = false;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			Workflow workflow = new Workflow();
			workflow.setWorkflowId(workflowId);
			workflow.setFlowname(flowname);
			workflow.setFlag(flag);
			workflowService.saveWorkflow(workflow);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly){
		this.readonly=readonly;
	}
	public boolean getReadonly(){
		return readonly;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage=currentPage;
	}
	public String getCurrentPage(){
		return currentPage;
	}

	public void setPages(int pages){
		this.pages=pages;
	}
	public int getPages(){
		return pages;
	}

	public void setWorkflowList(List workflowList){
		this.workflowList=workflowList;
	}
	public List getWorkflowList(){
		return workflowList;
	}

	public void setWorkflowId_q(String workflowId_q){
		this.workflowId_q=workflowId_q;
	}
	public String getWorkflowId_q(){
		return workflowId_q;
	}

	public void setWorkflowId(String workflowId){
		this.workflowId=workflowId;
	}
	public String getWorkflowId(){
		return workflowId;
	}

	public void setFlowname(String flowname){
		this.flowname=flowname;
	}
	public String getFlowname(){
		return flowname;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	

	public List getFlowNodeList() {
		return flowNodeList;
	}

	public void setFlowNodeList(List flowNodeList) {
		this.flowNodeList = flowNodeList;
	}


	public void setWorkflowService(WorkflowService workflowService){
		this.workflowService=workflowService;
	}
	public WorkflowService getWorkflowService(){
		return workflowService;
	}
	

	public FlowNodeService getFlowNodeService() {
		return flowNodeService;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService) {
		this.flowNodeService = flowNodeService;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String getMessageStr() {
		return messageStr;
	}

	public void setMessageStr(String messageStr) {
		this.messageStr = messageStr;
	}


}
