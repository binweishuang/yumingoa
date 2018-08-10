package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Archive  implements BaseModel {
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


	public Map toMap(){
		return null;
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
}
