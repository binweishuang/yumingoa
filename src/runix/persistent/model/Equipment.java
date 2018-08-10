package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Equipment  implements BaseModel {
	private String equipmentId;
	private String cateogory;
	private String storage;
	private String buier;
	private String goodsname;
	private String goodsnum;
	private String unitprice;
	private String repairnum;
	private String scrapnum;
	private String measureunit;
	private String remark;
	private String registrant;
	private Date registtime;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setEquipmentId(String equipmentId){
		this.equipmentId=equipmentId;
	}
	public String getEquipmentId(){
		return equipmentId;
	}
	public void setCateogory(String cateogory){
		this.cateogory=cateogory;
	}
	public String getCateogory(){
		return cateogory;
	}
	public void setStorage(String storage){
		this.storage=storage;
	}
	public String getStorage(){
		return storage;
	}
	public void setBuier(String buier){
		this.buier=buier;
	}
	public String getBuier(){
		return buier;
	}
	public void setGoodsname(String goodsname){
		this.goodsname=goodsname;
	}
	public String getGoodsname(){
		return goodsname;
	}
	public void setGoodsnum(String goodsnum){
		this.goodsnum=goodsnum;
	}
	public String getGoodsnum(){
		return goodsnum;
	}
	public void setUnitprice(String unitprice){
		this.unitprice=unitprice;
	}
	public String getUnitprice(){
		return unitprice;
	}
	public void setRepairnum(String repairnum){
		this.repairnum=repairnum;
	}
	public String getRepairnum(){
		return repairnum;
	}
	public void setScrapnum(String scrapnum){
		this.scrapnum=scrapnum;
	}
	public String getScrapnum(){
		return scrapnum;
	}
	public void setMeasureunit(String measureunit){
		this.measureunit=measureunit;
	}
	public String getMeasureunit(){
		return measureunit;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setRegistrant(String registrant){
		this.registrant=registrant;
	}
	public String getRegistrant(){
		return registrant;
	}
	public void setRegisttime(Date registtime){
		this.registtime=registtime;
	}
	public Date getRegisttime(){
		return registtime;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
