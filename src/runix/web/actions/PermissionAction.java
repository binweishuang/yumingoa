package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Permission;
import runix.persistent.model.Position;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.PermissionService;
import runix.service.PositionService;

public class PermissionAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List permissionList;
	private String permissionId_q;
	private String permissionId;
	private String title;
	private String url;
	private String sortnum;
	private String parentid;
	private String flag;
	private PermissionService permissionService;//权限service
	private PositionService positionService;//职位service
	private BaseDataService baseDataService;//序列
	private List<Position> positionList;//职位list
	private Permission permission;//权限实体
	private String role;//职位权限
	private String positionId;//职位id

	/**
	 * 查询职位
	 * @author luqj
	 * @return
	 */
	public String doQueryPosition(){
		positionList = positionService.findAllPosition();
		return SUCCESS;
	}
	
	/**
	 * 授予权限
	 * @author luqj
	 * @return
	 */
	public String doInsert(){
		try {
			//职位没有权限
			if(permissionService.findPermissionByPosition(permission.getParentid())==null){
			//	permissionId = baseDataService.getSequence();//序列
			//	permission.setPermissionId(permissionId);//主键
				permission.setTitle(role);//权限
				permissionService.savePermission(permission);
			}else{//职位已授权
				permission = permissionService.findPermissionByPosition(permission.getParentid());
				permission.setTitle(role);//新权限
				permissionService.modifyPermission(permission);//修改权限
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询是否已授权
	 * @return
	 * @author luqj
	 */
	public String doQueryPermission(){
		permission = permissionService.findPermissionByPosition(positionId);
		positionList = positionService.findAllPosition();
		if(permission!=null){
			readonly = true;//已授权
		}else{
			readonly = false;//未授权
		}
		return SUCCESS;
	}
	
	/**
	 * 查询权限
	 * @return
	 */
	public String doQuery(){
		//职位
		positionList = positionService.findAllPosition();
		//选中职位的权限
		permission = permissionService.findPermissionByPosition(positionId);
		return SUCCESS;
	}
	
/*	public String doQuery(){
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
			condition.put("permissionId",  "".equals(permissionId)?"":"%"+permissionId+"%");
			condition.put("title",  "".equals(title)?"":"%"+title+"%");
			condition.put("url",  "".equals(url)?"":"%"+url+"%");
			condition.put("sortnum",  "".equals(sortnum)?"":"%"+sortnum+"%");
			condition.put("parentid",  "".equals(parentid)?"":"%"+parentid+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = permissionService.getCountPermissions(condition);
			List lst =permissionService.getPermissions(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			permissionList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}*/

	public String doUpdateInit(){
		try {
			permission =permissionService.getPermissionById(permissionId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			Permission permission =permissionService.getPermissionById(permissionId_q);
			permission.setPermissionId(permissionId);
			permission.setTitle(title);
			permission.setUrl(url);
			permission.setSortnum(sortnum);
			permission.setParentid(parentid);
			permission.setFlag(flag);
			permissionService.modifyPermission(permission);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			permissionService.removePermissionById(permissionId_q);
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

	public void addActionError(String anErrorMessage){
		   String s=anErrorMessage;
		   System.out.println(s);
		  }
		  public void addActionMessage(String aMessage){
		   String s=aMessage;
		   System.out.println(s);
		  
		  }
		  public void addFieldError(String fieldName, String errorMessage){
		   String s=errorMessage;
		   String f=fieldName;
		   System.out.println(s);
		   System.out.println(f);
		  
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

	public void setPermissionList(List permissionList){
		this.permissionList=permissionList;
	}
	public List getPermissionList(){
		return permissionList;
	}

	public void setPermissionId_q(String permissionId_q){
		this.permissionId_q=permissionId_q;
	}
	public String getPermissionId_q(){
		return permissionId_q;
	}

	public void setPermissionId(String permissionId){
		this.permissionId=permissionId;
	}
	public String getPermissionId(){
		return permissionId;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}

	public void setSortnum(String sortnum){
		this.sortnum=sortnum;
	}
	public String getSortnum(){
		return sortnum;
	}

	public void setParentid(String parentid){
		this.parentid=parentid;
	}
	public String getParentid(){
		return parentid;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setPermissionService(PermissionService permissionService){
		this.permissionService=permissionService;
	}
	public PermissionService getPermissionService(){
		return permissionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
}
