package runix.web.actions;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Folder;
import runix.persistent.model.Netdisk;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.FolderService;
import runix.service.NetdiskService;

/**
 * 网络硬盘
 * @author luqj,ml
 *
 */
@SuppressWarnings("serial")
public class NetdiskAction extends BaseAction{
	
	private String OldFilePath;	//即将删除的文件路径
	private boolean readonly;
	private String currentPage;
	private int pages=15;	//每页显示记录数
	private List netdiskList;
	private String deletePath;
	private String fileId_q;
	private String fileId;
	private String filename;
	private String filepath;
	private Date uploadtime;
	private String upDate;	//上传时间
	private String publicity;	//公开状态	1为公开，0为不公开
	private String folderId;	// 父目录ID
	private String uploader;	//上传者ID
	private String flag;
	private NetdiskService netdiskService;//网络硬盘service
	private BaseDataService baseDataService;//序列service
	private FolderService folderService;//文件夹service
	private Netdisk netdisk;	//网络硬盘实体对象
	private Folder folder;	//文件夹实体对象
	private String doc;	//文件类型
	private String userRole;
	private String userId;

	/**
	 * 添加页面初始化(文件上传页面)
	 * 
	 */
	public String doInsertInit(){
		return SUCCESS;
	}
	
	/**
	 * 上传文件
	 * @throws Exception 
	 */
	public String doInsert() throws Exception{
		String names[] = netdisk.getFilename().split(";");	//取得所有上传文件的名称
		String sizes[] = netdisk.getFilesize().split(";");	//取得所有上传文件的大小
		String paths[] = netdisk.getFilepath().split(";");	//取得所有上传文件的路径
		String doDelete = deletePath;
		String deletePath[] = doDelete.split(";");
		for (int i = 0; i < names.length; i++) {
			long n = Long.parseLong(sizes[i]);
			if(n>0){
				n = n/1024;		//将文件大小转化为KB的单位
				netdisk.setFilesize(n + " KB");
			}
			if(n>1024){
				n = n/1024;	//将文件大小转化为MB的单位
				netdisk.setFilesize(n + " MB");
			}
			//netdisk.setFileId(baseDataService.getSequence());	//序列
			netdisk.setUploadtime(new Date());//上传时间
			netdisk.setFilename(names[i]);
			netdisk.setFilepath(paths[i]);
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			netdisk.setUploader(user.getUserId());	//上传人
			netdiskService.saveNetdisk(netdisk);
			for (int j = 0; j < deletePath.length; j++) {
				netdiskService.removeNetdiskByPath(deletePath[j]);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 进入硬盘首页列表查询
	 * 
	 */
	public String doQuery(){
		try {
			int currentPageInt = 1;
			String strCurrentPage = currentPage;
			if (strCurrentPage != null && !"".equals(strCurrentPage)) {
				try {
					currentPageInt = Integer.parseInt(strCurrentPage);
				} catch (Exception e) {
					e.printStackTrace();
					this.addActionError("please check! there is Exception");
				}
			}
			int offset = (currentPageInt-1) * pages;
			int limit = pages;
			Map<String,String> condition = new HashMap<String, String>();
			BaseUser user = (BaseUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			uploader = user.getUserId();
			userId = user.getUserId();
			userRole = user.getTitle();
			readonly = false;
			if(userRole.indexOf("bm5") < 0){
				condition.put("uploader",  "".equals(uploader)?"":uploader);
				condition.put("publicity",  "1");
				readonly = true;
			}
			condition.put("filename",  "".equals(filename)?"":filename);
			condition.put("uploadtime",  "".equals(upDate)?"":upDate);
			
			//record 总记录数
			int record = 0;
			List lst = null;
			if("1".equals(flag)){
				condition.put("folderId", folderId);
				record = netdiskService.getCountNetdisks1(condition);//更具文件夹ID查询
				lst =netdiskService.getNetdisks1(condition, offset, limit);
			}else{
				record = netdiskService.getCountNetdisks(condition);
				lst =netdiskService.getNetdisks(condition, offset, limit);
			}
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly", readonly);
			netdiskList = pg.getResult();
			//System.out.println(netdiskList.size());
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 修改查询
	 * @return
	 */
	public String doUpdateInit(){
		try {
			netdisk =netdiskService.getNetdiskById(fileId);
			filename = netdisk.getFilename();
			filename = netdisk.getFilename().substring(0,filename.lastIndexOf("."));
			publicity =netdisk.getPublicity();
			doc = netdisk.getFilename();
			doc = doc.substring(doc.lastIndexOf("."));
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 网络硬盘修改
	 * @return
	 */
	public String doUpdate(){
		try {
			netdisk = netdiskService.getNetdiskById(fileId);
			netdisk.setFilename(filename+doc);
			netdisk.setUploadtime(new Date());
			netdisk.setPublicity(publicity);
			netdiskService.modifyNetdisk(netdisk);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 网络硬盘删除文件
	 * @return
	 * @author 孟理
	 */
	public String doDelete(){
		String path = ServletActionContext.getServletContext().getRealPath("")+filepath;
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}else{
			System.out.println("没有找到对应的文件");
		}
		try {
			netdiskService.removeNetdiskById(fileId);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	//上传文件时在组件中删除文件
	public void doDeleteFile(){
		String path = ServletActionContext.getServletContext().getRealPath("")+OldFilePath ;
		System.out.println(path);
		File file = new File(path.substring(0, path.length()-1));
		if(file.exists()){
			file.delete();
		}else{
			System.out.println("没有找到对应的文件！！！");
		}
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
	public List<Netdisk> getNetdiskList(){
		return netdiskList;
	}

	public void setFileId_q(String fileId_q){
		this.fileId_q=fileId_q;
	}
	public String getFileId_q(){
		return fileId_q;
	}

	public void setFileId(String fileId){
		this.fileId=fileId;
	}
	public String getFileId(){
		return fileId;
	}

	public void setFilename(String filename){
		this.filename=filename;
	}
	public String getFilename(){
		return filename;
	}

	public String getOldFilePath() {
		return OldFilePath;
	}

	public void setOldFilePath(String oldFilePath) {
		OldFilePath = oldFilePath;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setNetdiskList(List netdiskList) {
		this.netdiskList = netdiskList;
	}

	public void setFilepath(String filepath){
		this.filepath=filepath;
	}
	public String getFilepath(){
		return filepath;
	}

	public void setUploadtime(Date uploadtime){
		this.uploadtime=uploadtime;
	}
	public Date getUploadtime(){
		return uploadtime;
	}

	public void setPublicity(String publicity){
		this.publicity=publicity;
	}
	public String getPublicity(){
		return publicity;
	}

	public void setFolderId(String folderId){
		this.folderId=folderId;
	}
	public String getFolderId(){
		return folderId;
	}

	public void setUploader(String uploader){
		this.uploader=uploader;
	}
	public String getUploader(){
		return uploader;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setNetdiskService(NetdiskService netdiskService){
		this.netdiskService=netdiskService;
	}
	
	public NetdiskService getNetdiskService(){
		return netdiskService;
	}

	public Netdisk getNetdisk() {
		return netdisk;
	}

	public void setNetdisk(Netdisk netdisk) {
		this.netdisk = netdisk;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getDeletePath() {
		return deletePath;
	}

	public void setDeletePath(String deletePath) {
		this.deletePath = deletePath;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
