package runix.web.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.Archive;
import runix.persistent.model.BaseUser;
import runix.service.ArchiveService;
import runix.service.BaseDataService;
import runix.service.DossierService;

public class ArchiveAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List archiveList;
	private String archiveId_q;
	private String archiveId;
	private String author;
	private String filetype;
	private String secret;
	private Date createdate;
	private Date fillingdate;
	private String partnum;
	private String annual;
	private String filesize;
	private String pagenum;
	private String archiveroom;
	private String dossierId;
	private String archivetype;
	private String title;
	private String remark;
	private String content;
	private String flag;
	private String fillingstatus;
	private List secretList;//秘密等级列表
	private List archiveroomList;//档案室列表
	private List archivetypeList;//档案文种列表
	private List dossierList;//案卷列表
	private ArchiveService archiveService;
	private BaseDataService baseDataService;
	private DossierService dossierService;
	
	private String userRole;

	public String doQueryArchive(){
		return SUCCESS;
	}
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
			if (!"".equals(title) && null != title) {
				condition.put("title", "%"+title+"%");
			}
			if (!"".equals(filesize) && null != filesize) {
				condition.put("filesize", "%"+filesize+"%");
			}
			if (!"".equals(filetype) && null != filetype) {
				condition.put("filetype", filetype);
			}
			if (!"".equals(secret) && null != secret) {
				condition.put("secret", secret);
			}
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			userRole = user.getTitle();
			if(userRole.indexOf("em3") < 0){
				readonly = true;
			}
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			int record = archiveService.getCountArchives(condition);
			List lst =archiveService.getArchives(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			archiveList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			//	archiveroomList = baseDataService.getqBaseByDatatype("FILEROOM");
				archivetypeList = baseDataService.getqBaseByDatatype("LANGUAGE");
				dossierList = dossierService.getDossierList();
			Archive archive =archiveService.getArchiveById(archiveId_q);
			archiveId = archive.getArchiveId();
			author = archive.getAuthor();
			filetype = archive.getFiletype();
			secret = archive.getSecret();
			createdate = archive.getCreatedate();
			fillingdate = archive.getFillingdate();
			partnum = archive.getPartnum();
			annual = archive.getAnnual();
			filesize = archive.getFilesize();
			pagenum = archive.getPagenum();
			archiveroom = archive.getArchiveroom();
			dossierId = archive.getDossierId();
			archivetype = archive.getArchivetype();
			title = archive.getTitle();
			remark = archive.getRemark();
			content = archive.getContent();
			flag = archive.getFlag();
			fillingstatus = archive.getFillingstatus();
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
			Archive archive =archiveService.getArchiveById(archiveId);
		//	archive.setArchiveId(archiveId);
			//archive.setAuthor(author);
			archive.setFiletype(filetype);
			archive.setSecret(secret);
			if("save".equals(flag)){
				archive.setCreatedate(new Date());
				archive.setFillingdate(fillingdate);
				archive.setFillingstatus("0");
			}else if("filling".equals(flag)){
				archive.setCreatedate(new Date());
				archive.setFillingdate(new Date());
				archive.setFillingstatus("1");
			}
			archive.setPartnum(partnum);
			archive.setAnnual(annual);
			archive.setFilesize(filesize);
			archive.setPagenum(pagenum);
			archive.setArchiveroom(archiveroom);
			archive.setDossierId(dossierId);
			archive.setArchivetype(archivetype);
			archive.setTitle(title);
			archive.setRemark(remark);
			archive.setContent(content);
			archive.setFlag(null);
			archiveService.modifyArchive(archive);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			archiveService.removeArchiveById(archiveId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
		//	archiveroomList = baseDataService.getqBaseByDatatype("FILEROOM");
			archivetypeList = baseDataService.getqBaseByDatatype("LANGUAGE");
			dossierList = dossierService.getDossierList();
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				author = user.getName();
			}
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
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				Archive archive = new Archive();
				//archiveId = baseDataService.getSequence();
				//archive.setArchiveId(archiveId);
				archive.setAuthor(user.getName());
				archive.setFiletype(filetype);
				archive.setSecret(secret);
				if("save".equals(flag)){
					archive.setCreatedate(new Date());
					archive.setFillingdate(fillingdate);
					archive.setFillingstatus("0");
				}else if("filling".equals(flag)){
					archive.setCreatedate(new Date());
					archive.setFillingdate(new Date());
					archive.setFillingstatus("1");
				}
				archive.setPartnum(partnum);
				archive.setAnnual(annual);
				archive.setFilesize(filesize);
				archive.setPagenum(pagenum);
				archive.setArchiveroom(archiveroom);
				archive.setDossierId(dossierId);
				archive.setArchivetype(archivetype);
				archive.setTitle(title);
				archive.setRemark(remark);
				archive.setContent(content);
				archive.setFlag(null);
				
				archiveService.saveArchive(archive);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doView(){
		try {
			Archive archive =archiveService.getArchiveByIdForView(archiveId_q);
			archiveId = archive.getArchiveId();
			author = archive.getAuthor();
			filetype = archive.getFiletype();
			secret = archive.getSecret();
			createdate = archive.getCreatedate();
			fillingdate = archive.getFillingdate();
			partnum = archive.getPartnum();
			annual = archive.getAnnual();
			filesize = archive.getFilesize();
			pagenum = archive.getPagenum();
			archiveroom = archive.getArchiveroom();
			dossierId = archive.getDossierId();
			archivetype = archive.getArchivetype();
			title = archive.getTitle();
			remark = archive.getRemark();
			content = archive.getContent();
			flag = archive.getFlag();
			fillingstatus = archive.getFillingstatus();
			readonly = true;
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

	public void setArchiveList(List archiveList){
		this.archiveList=archiveList;
	}
	public List getArchiveList(){
		return archiveList;
	}

	public void setArchiveId_q(String archiveId_q){
		this.archiveId_q=archiveId_q;
	}
	public String getArchiveId_q(){
		return archiveId_q;
	}

	public void setArchiveId(String archiveId){
		this.archiveId=archiveId;
	}
	public String getArchiveId(){
		return archiveId;
	}

	public void setAuthor(String author){
		this.author=author;
	}
	public String getAuthor(){
		return author;
	}

	public void setFiletype(String filetype){
		this.filetype=filetype;
	}
	public String getFiletype(){
		return filetype;
	}

	public void setSecret(String secret){
		this.secret=secret;
	}
	public String getSecret(){
		return secret;
	}

	public void setCreatedate(Date createdate){
		this.createdate=createdate;
	}
	public Date getCreatedate(){
		return createdate;
	}

	public void setFillingdate(Date fillingdate){
		this.fillingdate=fillingdate;
	}
	public Date getFillingdate(){
		return fillingdate;
	}

	public void setPartnum(String partnum){
		this.partnum=partnum;
	}
	public String getPartnum(){
		return partnum;
	}

	public void setAnnual(String annual){
		this.annual=annual;
	}
	public String getAnnual(){
		return annual;
	}

	public void setFilesize(String filesize){
		this.filesize=filesize;
	}
	public String getFilesize(){
		return filesize;
	}

	public void setPagenum(String pagenum){
		this.pagenum=pagenum;
	}
	public String getPagenum(){
		return pagenum;
	}

	public void setArchiveroom(String archiveroom){
		this.archiveroom=archiveroom;
	}
	public String getArchiveroom(){
		return archiveroom;
	}

	public void setDossierId(String dossierId){
		this.dossierId=dossierId;
	}
	public String getDossierId(){
		return dossierId;
	}

	public void setArchivetype(String archivetype){
		this.archivetype=archivetype;
	}
	public String getArchivetype(){
		return archivetype;
	}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setFillingstatus(String fillingstatus){
		this.fillingstatus=fillingstatus;
	}
	public String getFillingstatus(){
		return fillingstatus;
	}

	public void setArchiveService(ArchiveService archiveService){
		this.archiveService=archiveService;
	}
	public ArchiveService getArchiveService(){
		return archiveService;
	}
	public List getSecretList() {
		return secretList;
	}
	public void setSecretList(List secretList) {
		this.secretList = secretList;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	public List getArchiveroomList() {
		return archiveroomList;
	}
	public void setArchiveroomList(List archiveroomList) {
		this.archiveroomList = archiveroomList;
	}
	public List getArchivetypeList() {
		return archivetypeList;
	}
	public void setArchivetypeList(List archivetypeList) {
		this.archivetypeList = archivetypeList;
	}
	public List getDossierList() {
		return dossierList;
	}
	public void setDossierList(List dossierList) {
		this.dossierList = dossierList;
	}
	public DossierService getDossierService() {
		return dossierService;
	}
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
