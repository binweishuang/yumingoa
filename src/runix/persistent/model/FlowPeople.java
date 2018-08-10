package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class FlowPeople  implements BaseModel {

	private String fpId;
	private String relateId;
	private String personId;
	private String sortnum;
	private String handlestatus;
	private String readstatus;
	private String opinion;


	public Map toMap(){
		return null;
	}


	public String getFpId() {
		return fpId;
	}


	public void setFpId(String fpId) {
		this.fpId = fpId;
	}


	public String getRelateId() {
		return relateId;
	}


	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}


	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
	}


	public String getSortnum() {
		return sortnum;
	}


	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}


	public String getHandlestatus() {
		return handlestatus;
	}


	public void setHandlestatus(String handlestatus) {
		this.handlestatus = handlestatus;
	}


	public String getReadstatus() {
		return readstatus;
	}


	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	

}
