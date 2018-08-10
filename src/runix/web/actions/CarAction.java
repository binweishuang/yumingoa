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

import runix.persistent.model.BaseUser;
import runix.persistent.model.Car;
import runix.service.BaseDataService;
import runix.service.CarService;

public class CarAction extends BaseAction {

	private boolean readonly;
	private String currentPage;
	private int pages = 15;
	private List carList;
	private String carId_q;
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
	private CarService carService;
	private BaseDataService baseDataService;
	private List<Map<String, String>> cars;
	private Car car;
	private String userRole;
	
	public String doQueryM(){
		return SUCCESS;
	}

	/**
	 * 列表页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQuery() {
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
			int offset = (currentPageInt - 1) * pages;
			int limit = pages;
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cartype", "".equals(cartype) ? "" : cartype);
			condition.put("license", "".equals(license) ? "" : license);
			condition.put("checktime", "".equals(checktime) ? "" : checktime);
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("gm2") < 0){
					readonly = true;
				}
			}
			int record = carService.getCountCars(condition);
			List<Map<String, String>> lst = (List<Map<String, String>>) carService
					.getCars(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			setCars(pg.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}


	public String doUpdateInit() {
		try {
			Car car = carService.getCarById(carId_q);
			carId = car.getCarId();
			cartype = car.getCartype();
			capacity = car.getCapacity();
			license = car.getLicense();
			passengernum = car.getPassengernum();
			checktime = car.getChecktime();
			insuretime = car.getInsuretime();
			buytime = car.getBuytime();
			drivenkm = car.getDrivenkm();
			driver = car.getDriver();
			usable = car.getUsable();
			attachname = car.getAttachname();
			attachpath = car.getAttachpath();
			description = car.getDescription();
			photoname = car.getPhotoname();
			photopath = car.getPhotopath();
			flag = car.getFlag();
			readonly = true;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate() {
		try {
			Car car = carService.getCarById(carId_q);
			car.setCartype(cartype);
			car.setCapacity(capacity);
			car.setLicense(license);
			car.setPassengernum(passengernum);
			car.setChecktime(checktime);
			car.setInsuretime(insuretime);
			car.setBuytime(buytime);
			car.setDrivenkm(drivenkm);
			car.setDriver(driver);
			car.setUsable(usable);
			car.setAttachname(attachname);
			car.setAttachpath(attachpath);
			car.setDescription(description);
			car.setPhotoname(photoname);
			car.setPhotopath(photopath);
			car.setFlag(flag);
			carService.modifyCar(car);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete() {
		try {
			carService.removeCarById(carId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit() {
		try {
			readonly = false;
			ServletActionContext.getRequest()
					.setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert() {
		try {
			Car car = new Car();
		//	carId = baseDataService.getSequence();
		//	car.setCarId(carId);
			car.setCartype(cartype);
			car.setCapacity(capacity);
			car.setLicense(license);
			car.setPassengernum(passengernum);
			car.setChecktime(checktime);
			car.setInsuretime(insuretime);
			car.setBuytime(buytime);
			car.setDrivenkm(drivenkm);
			car.setDriver(driver);
			car.setUsable(usable);
			car.setAttachname(attachname);
			car.setAttachpath(attachpath);
			car.setDescription(description);
			car.setPhotoname(photoname);
			car.setPhotopath(photopath);
			car.setFlag(flag);
			carService.saveCar(car);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	
	public String doView() {
		try {
			Car car = carService.getCarById(carId_q);
			carId = car.getCarId();
			cartype = car.getCartype();
			capacity = car.getCapacity();
			license = car.getLicense();
			passengernum = car.getPassengernum();
			checktime = car.getChecktime();
			insuretime = car.getInsuretime();
			buytime = car.getBuytime();
			drivenkm = car.getDrivenkm();
			driver = car.getDriver();
			usable = car.getUsable();
			attachname = car.getAttachname();
			attachpath = car.getAttachpath();
			description = car.getDescription();
			photoname = car.getPhotoname();
			photopath = car.getPhotopath();
			flag = car.getFlag();
			readonly = true;
			ServletActionContext.getRequest().setAttribute("readonly", readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean getReadonly() {
		return readonly;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	public void setCarList(List carList) {
		this.carList = carList;
	}

	public List getCarList() {
		return carList;
	}

	public void setCarId_q(String carId_q) {
		this.carId_q = carId_q;
	}

	public String getCarId_q() {
		return carId_q;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicense() {
		return license;
	}

	public void setPassengernum(String passengernum) {
		this.passengernum = passengernum;
	}

	public String getPassengernum() {
		return passengernum;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setInsuretime(Date insuretime) {
		this.insuretime = insuretime;
	}

	public Date getInsuretime() {
		return insuretime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setDrivenkm(String drivenkm) {
		this.drivenkm = drivenkm;
	}

	public String getDrivenkm() {
		return drivenkm;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public String getUsable() {
		return usable;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public String getAttachname() {
		return attachname;
	}

	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}

	public String getAttachpath() {
		return attachpath;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public CarService getCarService() {
		return carService;
	}

	public List<Map<String, String>> getCars() {
		return cars;
	}

	public void setCars(List<Map<String, String>> cars) {
		this.cars = cars;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
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
