package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Position;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.PositionService;

public class PositionAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List<Position> positionList;
	private String positionId_q;
	private String positionId;
	private String postname;
	private String category;
	private String description;
	private String screen;
	private String rolerank;
	private String flag;
	private PositionService positionService;
	private BaseDataService baseDataService;//序列
	private Position position;//职位对象实体
	
	/**
	 * 查询列表
	 * @author luqj
	 * @return
	 */
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
			//condition.put("positionId",  "".equals(positionId)?"":"%"+positionId+"%");
			//condition.put("postname",  "".equals(postname)?"":"%"+postname+"%");
			//condition.put("category",  "".equals(category)?"":"%"+category+"%");
			//condition.put("description",  "".equals(description)?"":"%"+description+"%");
			//condition.put("screen",  "".equals(screen)?"":"%"+screen+"%");
			//condition.put("rolerank",  "".equals(rolerank)?"":"%"+rolerank+"%");
			//condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = positionService.getCountPositions(condition);
			List list =positionService.getPositions(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(list, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			positionList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 持久化页面
	 * @author luqj
	 * @return
	 */
	public String doAddPage(){
		return SUCCESS;
	}
	
	/**
	 * 持久化职位
	 * @author luqj
	 * @return
	 */
	public String doAddPosition(){
		try {
			//positionId = baseDataService.getSequence();
			//position.setPositionId(positionId);
			positionService.savePosition(position);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改验证
	 * @author luqj
	 * @return
	 */
	public String doCheckEdit(){
		position = new Position();
		position.setPositionId(positionId);
		position.setPostname(postname);
		positionList = positionService.checkEdit(position);
		if(positionList.size()>0){
			readonly = false;
		}else{
			readonly = true;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改查询
	 * @author luqj
	 * @return
	 */
	public String doUpdateInit(){
		try {
			position =positionService.getPositionById(positionId);
			/*positionId = position.getPositionId();
			postname = position.getPostname();
			category = position.getCategory();
			description = position.getDescription();
			screen = position.getScreen();
			rolerank = position.getRolerank();
			flag = position.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly",readonly);*/
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 职位修改
	 * @author luqj
	 * @return
	 */
	public String doUpdate(){
		try {
			/*Position position =positionService.getPositionById(positionId_q);
			position.setPositionId(positionId);
			position.setPostname(postname);
			position.setCategory(category);
			position.setDescription(description);
			position.setScreen(screen);
			position.setRolerank(rolerank);
			position.setFlag(flag);*/
			positionService.modifyPosition(position);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 职位删除
	 * @author luqj
	 * @return
	 */
	public String doDelete(){
		try {
			positionService.removePositionById(positionId);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * @author luqj
	 * @return
	 */
	public String doCheckName(){
		positionList = positionService.findByName(postname);
		if(positionList.size()>0){
			readonly = false;
		}else{
			readonly = true;
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

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	public void setPositionId_q(String positionId_q){
		this.positionId_q=positionId_q;
	}
	public String getPositionId_q(){
		return positionId_q;
	}

	public void setPositionId(String positionId){
		this.positionId=positionId;
	}
	public String getPositionId(){
		return positionId;
	}

	public void setPostname(String postname){
		this.postname=postname;
	}
	public String getPostname(){
		return postname;
	}

	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return category;
	}

	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}

	public void setScreen(String screen){
		this.screen=screen;
	}
	public String getScreen(){
		return screen;
	}

	public void setRolerank(String rolerank){
		this.rolerank=rolerank;
	}
	public String getRolerank(){
		return rolerank;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setPositionService(PositionService positionService){
		this.positionService=positionService;
	}
	
	public PositionService getPositionService(){
		return positionService;
	}
	
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
