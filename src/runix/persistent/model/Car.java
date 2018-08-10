package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class Car  implements BaseModel {
	private String carId;
	private String cartype;
	private String capacity;
	private String license;
	private String passengernum;
	private Date checktime;
	private Date insuretime;
	private Date buytime;
	private String drivenkm;
	private String driver;
	private String usable;
	private String attachname;
	private String attachpath;
	private String description;
	private String photoname;
	private String photopath;
	private String flag;


	public Map toMap(){
		return null;
	}
	public void setCarId(String carId){
		this.carId=carId;
	}
	public String getCarId(){
		return carId;
	}
	public void setCartype(String cartype){
		this.cartype=cartype;
	}
	public String getCartype(){
		return cartype;
	}
	public void setCapacity(String capacity){
		this.capacity=capacity;
	}
	public String getCapacity(){
		return capacity;
	}
	public void setLicense(String license){
		this.license=license;
	}
	public String getLicense(){
		return license;
	}
	public void setPassengernum(String passengernum){
		this.passengernum=passengernum;
	}
	public String getPassengernum(){
		return passengernum;
	}
	public void setChecktime(Date checktime){
		this.checktime=checktime;
	}
	public Date getChecktime(){
		return checktime;
	}
	public void setInsuretime(Date insuretime){
		this.insuretime=insuretime;
	}
	public Date getInsuretime(){
		return insuretime;
	}
	public void setBuytime(Date buytime){
		this.buytime=buytime;
	}
	public Date getBuytime(){
		return buytime;
	}
	public void setDrivenkm(String drivenkm){
		this.drivenkm=drivenkm;
	}
	public String getDrivenkm(){
		return drivenkm;
	}
	public void setDriver(String driver){
		this.driver=driver;
	}
	public String getDriver(){
		return driver;
	}
	public void setUsable(String usable){
		this.usable=usable;
	}
	public String getUsable(){
		return usable;
	}
	public void setAttachname(String attachname){
		this.attachname=attachname;
	}
	public String getAttachname(){
		return attachname;
	}
	public void setAttachpath(String attachpath){
		this.attachpath=attachpath;
	}
	public String getAttachpath(){
		return attachpath;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setPhotoname(String photoname){
		this.photoname=photoname;
	}
	public String getPhotoname(){
		return photoname;
	}
	public void setPhotopath(String photopath){
		this.photopath=photopath;
	}
	public String getPhotopath(){
		return photopath;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
}
