package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Dossier;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.DossierService;

public class DossierAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List dossierList;
	private String dossierId_q;
	private String dossierId;
	private String titlle;
	private String boxnum;
	private String annual;
	private String recgroupnum;
	private String keeplimit;
	private String archivesnum;
	private String docroom;
	private String author;
	private String category;
	private String pagenum;
	private String secret;
	private Date starttime;
	private Date endtime;
	private String remark;
	private String flag;
	private String status;
	private List docroomList;
	private List secretList;
	private DossierService dossierService;
	private BaseDataService baseDataService;
	private String userRole;
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
			if (!"".equals(status) && null != status) {
			  condition.put("status", status);
			}
			if (!"".equals(annual) && null != annual) {
				  condition.put("annual", annual);
		    }
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			userRole = user.getTitle();
			if(userRole.indexOf("em3") < 0){
				readonly = true;
			}
			int record = dossierService.getCountDossiers(condition);
			List lst =dossierService.getDossiers(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			dossierList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			docroomList= baseDataService.getqBaseByDatatype("FILEROOM");
			Dossier dossier =dossierService.getDossierById(dossierId_q);
			dossierId = dossier.getDossierId();
			titlle = dossier.getTitlle();
			boxnum = dossier.getBoxnum();
			annual = dossier.getAnnual();
			recgroupnum = dossier.getRecgroupnum();
			keeplimit = dossier.getKeeplimit();
			archivesnum = dossier.getArchivesnum();
			docroom = dossier.getDocroom();
			author = dossier.getAuthor();
			category = dossier.getCategory();
			pagenum = dossier.getPagenum();
			secret = dossier.getSecret();
			starttime = dossier.getStarttime();
			endtime = dossier.getEndtime();
			remark = dossier.getRemark();
			flag = dossier.getFlag();
			status = dossier.getStatus();
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
			Dossier dossier =dossierService.getDossierById(dossierId_q);
			dossier.setDossierId(dossierId);
			dossier.setTitlle(titlle);
			dossier.setBoxnum(boxnum);
			dossier.setAnnual(annual);
			dossier.setRecgroupnum(recgroupnum);
			dossier.setKeeplimit(keeplimit);
			dossier.setArchivesnum(archivesnum);
			dossier.setDocroom(docroom);
//			dossier.setAuthor(author);
			dossier.setCategory(category);
			dossier.setPagenum(pagenum);
			dossier.setSecret(secret);
			dossier.setStarttime(starttime);
			dossier.setEndtime(endtime);
			dossier.setRemark(remark);
			dossier.setFlag(flag);
			dossier.setStatus(status);
			dossierService.modifyDossier(dossier);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			dossierService.removeArchiveByDossierId(dossierId_q);
			dossierService.removeDossierById(dossierId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			secretList = baseDataService.getqBaseByDatatype("SECRET");
			docroomList= baseDataService.getqBaseByDatatype("FILEROOM");
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
				Dossier dossier = new Dossier();
				//dossierId = baseDataService.getSequence();
				//dossier.setDossierId(dossierId);
				dossier.setTitlle(titlle);
				dossier.setBoxnum(boxnum);
				dossier.setAnnual(annual);
				dossier.setRecgroupnum(recgroupnum);
				dossier.setKeeplimit(keeplimit);
				dossier.setArchivesnum(archivesnum);
				dossier.setDocroom(docroom);
				dossier.setAuthor(user.getName());
				dossier.setCategory(category);
				dossier.setPagenum(pagenum);
				dossier.setSecret(secret);
				dossier.setStarttime(starttime);
				dossier.setEndtime(endtime);
				dossier.setRemark(remark);
				dossier.setFlag(flag);
				dossier.setStatus(status);
				dossierService.saveDossier(dossier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView(){
		try {
			Dossier dossier =dossierService.getDossierByIdForView(dossierId_q);
			dossierId = dossier.getDossierId();
			titlle = dossier.getTitlle();
			boxnum = dossier.getBoxnum();
			annual = dossier.getAnnual();
			recgroupnum = dossier.getRecgroupnum();
			keeplimit = dossier.getKeeplimit();
			archivesnum = dossier.getArchivesnum();
			docroom = dossier.getDocroom();
			author = dossier.getAuthor();
			category = dossier.getCategory();
			pagenum = dossier.getPagenum();
			secret = dossier.getSecret();
			starttime = dossier.getStarttime();
			endtime = dossier.getEndtime();
			remark = dossier.getRemark();
			flag = dossier.getFlag();
			status = dossier.getStatus();
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

	public void setDossierList(List dossierList){
		this.dossierList=dossierList;
	}
	public List getDossierList(){
		return dossierList;
	}

	public void setDossierId_q(String dossierId_q){
		this.dossierId_q=dossierId_q;
	}
	public String getDossierId_q(){
		return dossierId_q;
	}

	public void setDossierId(String dossierId){
		this.dossierId=dossierId;
	}
	public String getDossierId(){
		return dossierId;
	}

	public void setTitlle(String titlle){
		this.titlle=titlle;
	}
	public String getTitlle(){
		return titlle;
	}

	public void setBoxnum(String boxnum){
		this.boxnum=boxnum;
	}
	public String getBoxnum(){
		return boxnum;
	}

	public void setAnnual(String annual){
		this.annual=annual;
	}
	public String getAnnual(){
		return annual;
	}

	public void setRecgroupnum(String recgroupnum){
		this.recgroupnum=recgroupnum;
	}
	public String getRecgroupnum(){
		return recgroupnum;
	}

	public void setKeeplimit(String keeplimit){
		this.keeplimit=keeplimit;
	}
	public String getKeeplimit(){
		return keeplimit;
	}

	public void setArchivesnum(String archivesnum){
		this.archivesnum=archivesnum;
	}
	public String getArchivesnum(){
		return archivesnum;
	}

	public void setDocroom(String docroom){
		this.docroom=docroom;
	}
	public String getDocroom(){
		return docroom;
	}

	public void setAuthor(String author){
		this.author=author;
	}
	public String getAuthor(){
		return author;
	}

	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return category;
	}

	public void setPagenum(String pagenum){
		this.pagenum=pagenum;
	}
	public String getPagenum(){
		return pagenum;
	}

	public void setSecret(String secret){
		this.secret=secret;
	}
	public String getSecret(){
		return secret;
	}

	public void setStarttime(Date starttime){
		this.starttime=starttime;
	}
	public Date getStarttime(){
		return starttime;
	}

	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public Date getEndtime(){
		return endtime;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setDossierService(DossierService dossierService){
		this.dossierService=dossierService;
	}
	public DossierService getDossierService(){
		return dossierService;
	}

	public List getDocroomList() {
		return docroomList;
	}

	public void setDocroomList(List docroomList) {
		this.docroomList = docroomList;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
