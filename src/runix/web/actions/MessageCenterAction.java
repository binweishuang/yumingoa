package runix.web.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseData;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Dept;
import runix.persistent.model.MessageCenter;
import runix.persistent.model.UserMessage;
import runix.service.BaseDataService;
import runix.service.BaseUserService;
import runix.service.DeptService;
import runix.service.MessageCenterService;

public class MessageCenterAction extends BaseAction {

	private boolean readonly;
	private String currentPage;
	private int pages = 15;
	private List messageCenterList;
	private String messageId_q;
	private String messageId;
	private String messagesort;
	private String title;// 标题
	private Date publishtime;// 发布时间
	private String type;// 信息类型
	private Date starttime;// 开始时间
	private Date endtime;// 终止时间
	private String scope;// 发布范围
	private String publisher;// 发布人
	private String dept;
	private String keywords;
	private String content;
	private String status;
	private String flag;
	private MessageCenterService messageCenterService;
	private BaseDataService baseDataService;
	private BaseUserService baseUserService;
	private DeptService deptService;
	// output
	private List<BaseData> types;// 信息类型列表
	private BaseData baseData;
	private List<Dept> depts;// 部门列表
	private List<MessageCenter> messages;// 信息列表
	private List<Map<String, String>> mgs;// 信息列表
	private MessageCenter mage;
	private String start;// 开始时间
	private String end;// 终止时间
	private final String NEWS_TYPE = "NEWS";// 新闻类型
	private final String NOTICE_TYPE = "NOTICE";// 通知类型
	private final String RULES_TYPE = "RULES";// 规章制度类型
	private boolean result;// 校验结果
	
	private String userRole;
	
	private String readstatus;
	private String umId;

	/**
	 * 格式化时间
	 */
	private void dateFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (starttime != null && !"".equals(starttime)) {
			start = format.format(starttime);
		}
		if (endtime != null && !"".equals(endtime)) {
			end = format.format(endtime);
		}
	}

	/**
	 * 信息中心
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQuery() {
		try {
			types = messageCenterService.findTypes("");
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
			e.printStackTrace();
		}
		dateFormat();
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
			condition.put("type", "-1".equals(type) ? "" : type);
			condition.put("starttime", starttime);
			condition.put("endtime", endtime);
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				condition.put("userId", user.getUserId());
			}
			int record = messageCenterService.getCountMessageCenter(condition);
			List<Map<String, String>> lst = (List<Map<String, String>>) messageCenterService
					.getMessageCenter(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			mgs = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 新闻列表页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQueryByNews() {
		try {
			types = messageCenterService.findTypes(NEWS_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		depts = messageCenterService.findAllDepts();

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

			Map<String, String> condition = new HashMap<String, String>();
			condition.put("title", title);
			condition.put("type", "-1".equals(type) ? "" : type);
			condition.put("dept", "-1".equals(dept) ? "" : dept);
			condition.put("init", "1");
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("bm2") < 0){
					readonly = true;
				}
			}
			List lst = null;
			if(pages==6){
				lst =  messageCenterService
				.getMessageByNews2(condition, offset, limit);
				this.getRequest().setAttribute("news", lst);
			}else{
				int record = messageCenterService.getCountMessageByNews(condition);
				lst =  messageCenterService
						.getMessageByNews(condition, offset, limit);
			
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly", readonly);
			mgs = pg.getResult();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 通知列表页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQueryByNotice() {
		try {
			types = messageCenterService.findTypes(NOTICE_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		depts = messageCenterService.findAllDepts();
		dateFormat();
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
			condition.put("title", title);
			condition.put("type", "-1".equals(type) ? "" : type);
			condition.put("dept", "-1".equals(dept) ? "" : dept);
			condition.put("starttime", starttime);
			condition.put("endtime", endtime);
			condition.put("init", "1");
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("bm3") < 0){
					readonly = true;
				}
			}
			int record = messageCenterService
					.getCountMessageByNotice(condition);
			List lst =  messageCenterService
					.getMessageByNotice(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly", readonly);
			mgs = pg.getResult();
			this.getRequest().setAttribute("notices", mgs);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 规章制度列表页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQueryByRules() {
		try {
			types = messageCenterService.findTypes(RULES_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		depts = messageCenterService.findAllDepts();

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

			Map<String, String> condition = new HashMap<String, String>();
			condition.put("title", title);
			condition.put("type", "-1".equals(type) ? "" : type);
			condition.put("dept", "-1".equals(dept) ? "" : dept);
			condition.put("init", "1");
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				if(userRole.indexOf("bm3") < 0){
					readonly = true;
				}
			}
			int record = messageCenterService.getCountMessageByRules(condition);
			List lst =  messageCenterService
					.getMessageByRules(condition, offset, limit);

			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			ServletActionContext.getRequest().setAttribute("readonly", readonly);
			mgs = pg.getResult();
			this.getRequest().setAttribute("rules", mgs);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 校验信息类型
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doCheckmesg() {
		try {
			types = messageCenterService.findTypes(type);
			if (types != null && !types.isEmpty()) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 新闻---添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doCheckdept() {
		depts = messageCenterService.findAllDepts();
		if (depts != null && !depts.isEmpty()) {
			result = true;
		} else {
			result = false;
		}
		return SUCCESS;
	}

	/**
	 * 新闻---添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doPersistIndexByNews() {
		
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				dept=user.getDeptId();
			}
			depts = messageCenterService.findAllDepts();
			types = messageCenterService.findTypes(NEWS_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 通知---添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doPersistIndexByNotice() {
		depts = messageCenterService.findAllDepts();
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				dept=user.getDeptId();
			}
			types = messageCenterService.findTypes(NOTICE_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 规章制度---添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doPersistIndexByRules() {
		depts = messageCenterService.findAllDepts();
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				dept=user.getDeptId();
			}
			types = messageCenterService.findTypes(RULES_TYPE);
		} catch (Exception e) {
			System.out.println("Exception===============>" + e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 新闻、通知、规章制度---添加操作
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doPersist() {
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				publisher=user.getName();
			}
			//mage.setMessageId(baseDataService.getSequence());
			mage.setPublisher(publisher);
			messageCenterService.persist(mage);
			Map condition = new HashMap();
			if(!"-1".equals(mage.getScope())){
				condition.put("deptId", mage.getScope());
			}
			List userList = baseUserService.getUsersByDept(condition);
			for(int i=0;i< userList.size();i++){
				UserMessage um = new UserMessage();
				//um.setUmId(baseDataService.getSequence());
				um.setMessageId(mage.getMessageId());
				um.setUserId(((BaseUser)userList.get(i)).getUserId());
				um.setReadstatus("0");
				messageCenterService.saveUM(um);
			}
			
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 在“添加页面”中发布新闻、通知、规章制度
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doReleaseInAdd() {
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				publisher=user.getName();
			}
			//mage.setMessageId(baseDataService.getSequence());
			mage.setPublisher(publisher);
			messageCenterService.release(mage);
			Map condition = new HashMap();
			if(!"-1".equals(mage.getScope())){
				condition.put("deptId", mage.getScope());
			}
			List userList = baseUserService.getUsersByDept(condition);
			for(int i=0;i< userList.size();i++){
				UserMessage um = new UserMessage();
				//um.setUmId(baseDataService.getSequence());
				um.setMessageId(mage.getMessageId());
				um.setUserId(((BaseUser)userList.get(i)).getUserId());
				um.setReadstatus("0");
				messageCenterService.saveUM(um);
			}
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 新闻---修改页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doUpdateIndexByNews() {
		depts = messageCenterService.findAllDepts();
		try {
			mage = messageCenterService.getMessageCenterById(messageId);
			types = messageCenterService.findTypes(NEWS_TYPE);
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 通知---修改页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doUpdateIndexByNotice() {
		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		depts = messageCenterService.findAllDepts();
		try {
			mage = messageCenterService.getMessageCenterById(messageId);
//			Date s = mage.getStarttime();
//			if (s != null && !"".equals(s)) {
//				start = foo.format(s);
//			}
//			Date e = mage.getEndtime();
//			if (e != null && !"".equals(e)) {
//				end = foo.format(e);
//			}
			types = messageCenterService.findTypes(NOTICE_TYPE);
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 规章制度---修改页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doUpdateIndexByRules() {
		depts = messageCenterService.findAllDepts();
		try {
			mage = messageCenterService.getMessageCenterById(messageId);
			types = messageCenterService.findTypes(RULES_TYPE);
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 新闻、通知、规章制度---修改操作
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doUpdate() {
		try {
			messageCenterService.modify(mage);
			messageCenterService.deleteUM(mage.getMessageId());
			Map condition = new HashMap();
			if(!"-1".equals(mage.getScope())){
				condition.put("deptId", mage.getScope());
			}
			List userList = baseUserService.getUsersByDept(condition);
			for(int i=0;i< userList.size();i++){
				UserMessage um = new UserMessage();
				//um.setUmId(baseDataService.getSequence());
				um.setMessageId(mage.getMessageId());
				um.setUserId(((BaseUser)userList.get(i)).getUserId());
				um.setReadstatus("0");
				messageCenterService.saveUM(um);
			}
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 在“修改页面”中发布新闻、通知、规章制度
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doReleaseInUpdate() {
		try {
			messageCenterService.rel(mage);
			messageCenterService.deleteUM(mage.getMessageId());
			Map condition = new HashMap();
			if(!"-1".equals(mage.getScope())){
				condition.put("deptId", mage.getScope());
			}
			List userList = baseUserService.getUsersByDept(condition);
			for(int i=0;i< userList.size();i++){
				UserMessage um = new UserMessage();
				//um.setUmId(baseDataService.getSequence());
				um.setMessageId(mage.getMessageId());
				um.setUserId(((BaseUser)userList.get(i)).getUserId());
				um.setReadstatus("0");
				messageCenterService.saveUM(um);
			}
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 删除新闻、通知、规章制度
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDelete() {
		try {
			messageCenterService.deleteUM(mage.getMessageId());
			messageCenterService.delete(mage);
			
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 查看新闻、通知、规章制度
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doDetail() {
//		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mage = messageCenterService.getMessageCenterById(messageId);
			if(!"".equals(type) && null != type){
				if(!type.equals(new String(type.getBytes("utf-8")))){
					type = new String(type.getBytes("iso-8859-1"),"utf-8");
				}
			}
			if("-1".equals(mage.getScope())){
				scope="所有部门";
			}else{
				scope = deptService.getDeptById(mage.getScope()).getDeptname();
			}
			
			if("1".equals(readstatus)){
				UserMessage um = messageCenterService.getUserMessageById(umId);
				um.setReadstatus("1");
				messageCenterService.modifyUserMessage(um);
			}
//			Date s = mage.getStarttime();
//			if (s != null && !"".equals(s)) {
//				start = foo.format(s);
//			}
//			Date e = mage.getEndtime();
//			if (e != null && !"".equals(e)) {
//				end = foo.format(e);
//			}
		} catch (Exception e) {
			System.out.println("Exception=====================>"
					+ e.getMessage());
			e.getStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * all getter & setter method
	 * 
	 */
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

	public void setMessageCenterList(List messageCenterList) {
		this.messageCenterList = messageCenterList;
	}

	public List getMessageCenterList() {
		return messageCenterList;
	}

	public void setMessageId_q(String messageId_q) {
		this.messageId_q = messageId_q;
	}

	public String getMessageId_q() {
		return messageId_q;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessagesort(String messagesort) {
		this.messagesort = messagesort;
	}

	public String getMessagesort() {
		return messagesort;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return scope;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setMessageCenterService(
			MessageCenterService messageCenterService) {
		this.messageCenterService = messageCenterService;
	}

	public MessageCenterService getMessageCenterService() {
		return messageCenterService;
	}

	public List<BaseData> getTypes() {
		return types;
	}

	public void setTypes(List<BaseData> types) {
		this.types = types;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public List<MessageCenter> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageCenter> messages) {
		this.messages = messages;
	}

	public List<Map<String, String>> getMgs() {
		return mgs;
	}

	public void setMgs(List<Map<String, String>> mgs) {
		this.mgs = mgs;
	}

	public MessageCenter getMage() {
		return mage;
	}

	public void setMage(MessageCenter mage) {
		this.mage = mage;
	}

	public BaseData getBaseData() {
		return baseData;
	}

	public void setBaseData(BaseData baseData) {
		this.baseData = baseData;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
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

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

	public String getUmId() {
		return umId;
	}

	public void setUmId(String umId) {
		this.umId = umId;
	}
	
}
