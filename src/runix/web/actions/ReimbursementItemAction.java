package runix.web.actions;

import java.util.List;
import java.util.Map;import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.ReimbursementItem;
import kdf.web.action.BaseAction;
import runix.service.ReimbursementItemService;

public class ReimbursementItemAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=10;
	private List reimbursementItemList;
	private String itemId_q;
	private String itemId;
	private String itemname;
	private Date time;
	private String reason;
	private Date reimdate;
	private String docpoll;
	private String reimmoney;
	private String otheritem;
	private String otherpoll;
	private String othermoney;
	private String flag;
	private String reimId;
	private ReimbursementItemService reimbursementItemService;

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
			condition.put("itemId",  "".equals(itemId)?"":"%"+itemId+"%");
			condition.put("itemname",  "".equals(itemname)?"":"%"+itemname+"%");
			condition.put("time",  "".equals(time)?"":"%"+time+"%");
			condition.put("reason",  "".equals(reason)?"":"%"+reason+"%");
			condition.put("reimdate",  "".equals(reimdate)?"":"%"+reimdate+"%");
			condition.put("docpoll",  "".equals(docpoll)?"":"%"+docpoll+"%");
			condition.put("reimmoney",  "".equals(reimmoney)?"":"%"+reimmoney+"%");
			condition.put("otheritem",  "".equals(otheritem)?"":"%"+otheritem+"%");
			condition.put("otherpoll",  "".equals(otherpoll)?"":"%"+otherpoll+"%");
			condition.put("othermoney",  "".equals(othermoney)?"":"%"+othermoney+"%");
			condition.put("flag",  "".equals(flag)?"":"%"+flag+"%");
			condition.put("reimId",  "".equals(reimId)?"":"%"+reimId+"%");

			int record = reimbursementItemService.getCountReimbursementItems(condition);
			List lst =reimbursementItemService.getReimbursementItems(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			reimbursementItemList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			ReimbursementItem reimbursementItem =reimbursementItemService.getReimbursementItemById(itemId_q);
			itemId = reimbursementItem.getItemId();
			itemname = reimbursementItem.getItemname();
			time = reimbursementItem.getTime();
			reason = reimbursementItem.getReason();
			reimdate = reimbursementItem.getReimdate();
			docpoll = reimbursementItem.getDocpoll();
			reimmoney = reimbursementItem.getReimmoney();
			otheritem = reimbursementItem.getOtheritem();
			otherpoll = reimbursementItem.getOtherpoll();
			othermoney = reimbursementItem.getOthermoney();
			flag = reimbursementItem.getFlag();
			reimId = reimbursementItem.getReimId();
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
			ReimbursementItem reimbursementItem =reimbursementItemService.getReimbursementItemById(itemId_q);
			reimbursementItem.setItemId(itemId);
			reimbursementItem.setItemname(itemname);
			reimbursementItem.setTime(time);
			reimbursementItem.setReason(reason);
			reimbursementItem.setReimdate(reimdate);
			reimbursementItem.setDocpoll(docpoll);
			reimbursementItem.setReimmoney(reimmoney);
			reimbursementItem.setOtheritem(otheritem);
			reimbursementItem.setOtherpoll(otherpoll);
			reimbursementItem.setOthermoney(othermoney);
			reimbursementItem.setFlag(flag);
			reimbursementItem.setReimId(reimId);
			reimbursementItemService.modifyReimbursementItem(reimbursementItem);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			reimbursementItemService.removeReimbursementItemById(itemId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
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
			ReimbursementItem reimbursementItem = new ReimbursementItem();
			reimbursementItem.setItemId(itemId);
			reimbursementItem.setItemname(itemname);
			reimbursementItem.setTime(time);
			reimbursementItem.setReason(reason);
			reimbursementItem.setReimdate(reimdate);
			reimbursementItem.setDocpoll(docpoll);
			reimbursementItem.setReimmoney(reimmoney);
			reimbursementItem.setOtheritem(otheritem);
			reimbursementItem.setOtherpoll(otherpoll);
			reimbursementItem.setOthermoney(othermoney);
			reimbursementItem.setFlag(flag);
			reimbursementItem.setReimId(reimId);
			reimbursementItemService.saveReimbursementItem(reimbursementItem);
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

	public void setReimbursementItemList(List reimbursementItemList){
		this.reimbursementItemList=reimbursementItemList;
	}
	public List getReimbursementItemList(){
		return reimbursementItemList;
	}

	public void setItemId_q(String itemId_q){
		this.itemId_q=itemId_q;
	}
	public String getItemId_q(){
		return itemId_q;
	}

	public void setItemId(String itemId){
		this.itemId=itemId;
	}
	public String getItemId(){
		return itemId;
	}

	public void setItemname(String itemname){
		this.itemname=itemname;
	}
	public String getItemname(){
		return itemname;
	}

	public void setTime(Date time){
		this.time=time;
	}
	public Date getTime(){
		return time;
	}

	public void setReason(String reason){
		this.reason=reason;
	}
	public String getReason(){
		return reason;
	}

	public void setReimdate(Date reimdate){
		this.reimdate=reimdate;
	}
	public Date getReimdate(){
		return reimdate;
	}

	public void setDocpoll(String docpoll){
		this.docpoll=docpoll;
	}
	public String getDocpoll(){
		return docpoll;
	}

	public void setReimmoney(String reimmoney){
		this.reimmoney=reimmoney;
	}
	public String getReimmoney(){
		return reimmoney;
	}

	public void setOtheritem(String otheritem){
		this.otheritem=otheritem;
	}
	public String getOtheritem(){
		return otheritem;
	}

	public void setOtherpoll(String otherpoll){
		this.otherpoll=otherpoll;
	}
	public String getOtherpoll(){
		return otherpoll;
	}

	public void setOthermoney(String othermoney){
		this.othermoney=othermoney;
	}
	public String getOthermoney(){
		return othermoney;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}

	public void setReimId(String reimId){
		this.reimId=reimId;
	}
	public String getReimId(){
		return reimId;
	}

	public void setReimbursementItemService(ReimbursementItemService reimbursementItemService){
		this.reimbursementItemService=reimbursementItemService;
	}
	public ReimbursementItemService getReimbursementItemService(){
		return reimbursementItemService;
	}

}
