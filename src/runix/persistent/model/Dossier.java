package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Dossier  implements BaseModel {
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


	public Map toMap(){
		return null;
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
}
