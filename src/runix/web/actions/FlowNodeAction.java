package runix.web.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.FlowNode;
import runix.service.BaseDataService;
import runix.service.FlowNodeService;

public class FlowNodeAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List flowNodeList;
	private String flownodeId_q;
	private String nodename_q;//查询条件


	private String flownodeId;
	private String nodename;
	private String appronum;
	private String approleaders;
	private String leadersid;
	private String approleaders_i;
	private String leadersid_i;
	private String approleaders_u;
	private String leadersid_u;
	public String getApproleaders_u() {
		return approleaders_u;
	}

	public void setApproleaders_u(String approleaders_u) {
		this.approleaders_u = approleaders_u;
	}

	public String getLeadersid_u() {
		return leadersid_u;
	}

	public void setLeadersid_u(String leadersid_u) {
		this.leadersid_u = leadersid_u;
	}

	private List leaderList;//审核领导
	private String flag;
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
			if (!"".equals(nodename_q) && null != nodename_q) {
				condition.put("nodename", "%"+nodename_q+"%");
			}

			int record = flowNodeService.getCountFlowNodes(condition);
			List lst =flowNodeService.getFlowNodes(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			flowNodeList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			FlowNode flowNode =flowNodeService.getFlowNodeById(flownodeId_q);
			flownodeId = flowNode.getFlownodeId();
			nodename = flowNode.getNodename();
			appronum = flowNode.getAppronum();
			leadersid = flowNode.getLeadersid();
			String ary[] = leadersid.split(",");
			List leader = new ArrayList();
			for(int i=0;i<ary.length;i++){
				leader = flowNodeService.getLeaders(ary[i]);
				if(leaderList==null){
					leaderList=leader;
				}else{
					leaderList.add(leader.get(0));
				}
				
			}
			//approleaders = flowNode.getApproleaders();
			flag = flowNode.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			FlowNode flowNode =flowNodeService.getFlowNodeById(flownodeId_q);
			//flowNode.setFlownodeId(flownodeId);
			flowNode.setNodename(nodename);
			flowNode.setAppronum(appronum);
			if(!"".equals(approleaders_u) && null != approleaders_u){
				if(!approleaders_u.equals(new String(approleaders_u.getBytes("utf-8")))){
					approleaders_u = new String(approleaders_u.getBytes("iso-8859-1"),"utf-8");
				}
			}
			flowNode.setApproleaders(approleaders_u);
			flowNode.setLeadersid(leadersid_u);
			flowNode.setFlag(flag);
			flowNodeService.modifyFlowNode(flowNode);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			flowNodeService.removeWorkflowNodeById(flownodeId_q);
			flowNodeService.removeFlowNodeById(flownodeId_q);
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
			FlowNode flowNode = new FlowNode();
			//flownodeId = baseDataService.getSequence();
			//flowNode.setFlownodeId(flownodeId);
			flowNode.setNodename(nodename);
			flowNode.setAppronum(appronum);
			if(!"".equals(approleaders_i)&&null!=approleaders_i){
				approleaders_i = new String(approleaders_i.getBytes("iso-8859-1"),"utf-8");
			}
			flowNode.setApproleaders(approleaders_i);
			flowNode.setLeadersid(leadersid_i);
			flowNode.setFlag(flag);
			flowNodeService.saveFlowNode(flowNode);
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

	public void setFlowNodeList(List flowNodeList){
		this.flowNodeList=flowNodeList;
	}
	public List getFlowNodeList(){
		return flowNodeList;
	}

	public void setFlownodeId_q(String flownodeId_q){
		this.flownodeId_q=flownodeId_q;
	}
	public String getFlownodeId_q(){
		return flownodeId_q;
	}

	public void setFlownodeId(String flownodeId){
		this.flownodeId=flownodeId;
	}
	public String getFlownodeId(){
		return flownodeId;
	}

	public void setNodename(String nodename){
		this.nodename=nodename;
	}
	public String getNodename(){
		return nodename;
	}

	public void setAppronum(String appronum){
		this.appronum=appronum;
	}
	public String getAppronum(){
		return appronum;
	}

	public void setApproleaders(String approleaders){
		this.approleaders=approleaders;
	}
	public String getApproleaders(){
		return approleaders;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	
	public String getLeadersid() {
		return leadersid;
	}

	public void setLeadersid(String leadersid) {
		this.leadersid = leadersid;
	}
	
	public String getNodename_q() {
		return nodename_q;
	}

	public void setNodename_q(String nodename_q) {
		this.nodename_q = nodename_q;
	}

	public String getApproleaders_i() {
		return approleaders_i;
	}

	public void setApproleaders_i(String approleaders_i) {
		this.approleaders_i = approleaders_i;
	}


	public List getLeaderList() {
		return leaderList;
	}

	public void setLeaderList(List leaderList) {
		this.leaderList = leaderList;
	}

	public String getLeadersid_i() {
		return leadersid_i;
	}

	public void setLeadersid_i(String leadersid_i) {
		this.leadersid_i = leadersid_i;
	}

	public void setFlowNodeService(FlowNodeService flowNodeService){
		this.flowNodeService=flowNodeService;
	}
	public FlowNodeService getFlowNodeService(){
		return flowNodeService;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

}
