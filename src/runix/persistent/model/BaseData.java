package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class BaseData  implements BaseModel {
	private String dataId;
	private String datatype;
	private String dataname;
	private String datacode;
	private String sortnum;
	private String remark;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setDataId(String dataId){
		this.dataId=dataId;
	}
	public String getDataId(){
		return dataId;
	}
	public void setDatatype(String datatype){
		this.datatype=datatype;
	}
	public String getDatatype(){
		return datatype;
	}
	public void setDataname(String dataname){
		this.dataname=dataname;
	}
	public String getDataname(){
		return dataname;
	}
	public void setDatacode(String datacode){
		this.datacode=datacode;
	}
	public String getDatacode(){
		return datacode;
	}
	public void setSortnum(String sortnum){
		this.sortnum=sortnum;
	}
	public String getSortnum(){
		return sortnum;
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
}
