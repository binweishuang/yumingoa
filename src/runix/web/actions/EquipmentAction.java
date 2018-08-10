package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Equipment;
import kdf.web.action.BaseAction;
import runix.service.BaseDataService;
import runix.service.EquipmentService;

public class EquipmentAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List equipmentList;
	private String equipmentId_q;
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
	private List storageList;//仓库列表
	private List categoryList;//物品类别列表
	private String userid;//登录用户id
	private EquipmentService equipmentService;
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
			if (!"".equals(storage) && null != storage) {
				condition.put("storage", storage);
			}
			if (!"".equals(cateogory) && null != cateogory) {
				condition.put("cateogory", cateogory);
			}
			if (!"".equals(goodsname) && null != goodsname) {
				condition.put("goodsname", "%"+goodsname+"%");
			}
			
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
			
			int record = equipmentService.getCountEquipments(condition);
			List lst =equipmentService.getEquipments(condition, offset, limit);
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userid = user.getUserId();
				userRole = user.getTitle();
				if(userRole.indexOf("gm4") < 0){
					readonly = true;
				}
			}
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
			equipmentList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
			Equipment equipment =equipmentService.getEquipmentById(equipmentId_q);
			equipmentId = equipment.getEquipmentId();
			cateogory = equipment.getCateogory();
			storage = equipment.getStorage();
			buier = equipment.getBuier();
			goodsname = equipment.getGoodsname();
			goodsnum = equipment.getGoodsnum();
			unitprice = equipment.getUnitprice();
			repairnum = equipment.getRepairnum();
			scrapnum = equipment.getScrapnum();
			measureunit = equipment.getMeasureunit();
			remark = equipment.getRemark();
			registrant = equipment.getRegistrant();
			registtime = equipment.getRegisttime();
			flag = equipment.getFlag();
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
			Equipment equipment =equipmentService.getEquipmentById(equipmentId_q);
			equipment.setEquipmentId(equipmentId);
			equipment.setCateogory(cateogory);
			equipment.setStorage(storage);
			equipment.setBuier(buier);
			equipment.setGoodsname(goodsname);
			equipment.setGoodsnum(goodsnum);
			equipment.setUnitprice(unitprice);
			equipment.setRepairnum(repairnum);
			equipment.setScrapnum(scrapnum);
			equipment.setMeasureunit(measureunit);
			equipment.setRemark(remark);
			equipment.setFlag(flag);
			equipmentService.modifyEquipment(equipment);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			equipmentService.removeEquipmentById(equipmentId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			storageList = baseDataService.getqBaseByDatatype("STORAGE");
			categoryList = baseDataService.getqBaseByDatatype("EQUIPMENT");
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
				Equipment equipment = new Equipment();
				//equipmentId = baseDataService.getSequence();
				//equipment.setEquipmentId(equipmentId);
				equipment.setCateogory(cateogory);
				equipment.setStorage(storage);
				equipment.setBuier(buier);
				equipment.setGoodsname(goodsname);
				equipment.setGoodsnum(goodsnum);
				equipment.setUnitprice(unitprice);
				equipment.setRepairnum(repairnum);
				equipment.setScrapnum(scrapnum);
				equipment.setMeasureunit(measureunit);
				equipment.setRemark(remark);
				equipment.setRegistrant(user.getUserId());
				equipment.setRegisttime(new Date());
				equipment.setFlag(flag);
				equipmentService.saveEquipment(equipment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doView(){
		try {
			Equipment equipment =equipmentService.getEquipmentByIdForView(equipmentId_q);
			equipmentId = equipment.getEquipmentId();
			cateogory = equipment.getCateogory();
			storage = equipment.getStorage();
			buier = equipment.getBuier();
			goodsname = equipment.getGoodsname();
			goodsnum = equipment.getGoodsnum();
			unitprice = equipment.getUnitprice();
			repairnum = equipment.getRepairnum();
			scrapnum = equipment.getScrapnum();
			measureunit = equipment.getMeasureunit();
			remark = equipment.getRemark();
			registrant = equipment.getRegistrant();
			registtime = equipment.getRegisttime();
			flag = equipment.getFlag();
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

	public void setEquipmentList(List equipmentList){
		this.equipmentList=equipmentList;
	}
	public List getEquipmentList(){
		return equipmentList;
	}

	public void setEquipmentId_q(String equipmentId_q){
		this.equipmentId_q=equipmentId_q;
	}
	public String getEquipmentId_q(){
		return equipmentId_q;
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

	public void setEquipmentService(EquipmentService equipmentService){
		this.equipmentService=equipmentService;
	}
	public EquipmentService getEquipmentService(){
		return equipmentService;
	}

	public List getStorageList() {
		return storageList;
	}

	public void setStorageList(List storageList) {
		this.storageList = storageList;
	}

	public List getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
