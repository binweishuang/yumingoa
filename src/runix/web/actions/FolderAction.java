package runix.web.actions;

import java.io.File;
import java.util.List;
import java.util.Map;import java.util.HashMap;


import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Folder;
import runix.persistent.model.Netdisk;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.FolderService;
import runix.service.NetdiskService;

/**
 * 文件夹
 * @author luqja
 *
 */
public class FolderAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List folderList;
	private String folderId_q;
	private String folderId;	
	private String foldername;	
	private String flag;	//
	private FolderService folderService;
	private NetdiskService netdiskService;
	private BaseDataService baseDataService;//序列service
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
			condition.put("folderId",  "".equals(folderId)?"":"%"+folderId+"%");
			condition.put("foldername",  "".equals(foldername)?"":"%"+foldername+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");

			int record = folderService.getCountFolders(condition);
			List lst =folderService.getFolders(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			folderList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			Folder folder =folderService.getFolderById(folderId_q);
			folderId = folder.getFolderId();
			foldername = folder.getFoldername();
			flag = folder.getFlag();
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
			Folder folder =folderService.getFolderById(folderId_q);
			folder.setFolderId(folderId);
			folder.setFoldername(foldername);
			folder.setFlag(flag);
			folderService.modifyFolder(folder);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			List<Netdisk> listNetdisk = netdiskService.findAll(folderId_q);
			for (int i = 0; i < listNetdisk.size(); i++) {
				Netdisk netDisk = listNetdisk.get(i);
				String path = ServletActionContext.getServletContext().getRealPath("")+netDisk.getFilepath();
				File file = new File(path);
				if(file.exists()){
					file.delete();
				}else{
					System.out.println("没有找到对应的文件");
				}
			}
			netdiskService.deleteAllNetdiskByFolderID(folderId_q);	//先删除此文件夹下面的所有文件
			folderService.removeFolderById(folderId_q);		//再删除此文件夹
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
			Folder folder = new Folder();
			//folder.setFolderId(baseDataService.getSequence());
			folder.setFoldername(foldername);
			folder.setFlag(flag);
			folderService.saveFolder(folder);
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

	public void setFolderList(List folderList){
		this.folderList=folderList;
	}
	public List getFolderList(){
		return folderList;
	}

	public void setFolderId_q(String folderId_q){
		this.folderId_q=folderId_q;
	}
	public String getFolderId_q(){
		return folderId_q;
	}

	public void setFolderId(String folderId){
		this.folderId=folderId;
	}
	public String getFolderId(){
		return folderId;
	}

	public void setFoldername(String foldername){
		this.foldername=foldername;
	}
	public String getFoldername(){
		return foldername;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setFolderService(FolderService folderService){
		this.folderService=folderService;
	}
	public FolderService getFolderService(){
		return folderService;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public NetdiskService getNetdiskService() {
		return netdiskService;
	}

	public void setNetdiskService(NetdiskService netdiskService) {
		this.netdiskService = netdiskService;
	}
	
	
}
