package runix.web.actions;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Date;

import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.tools.IbatisPage;
import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseData;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Suggestion;
import kdf.web.action.BaseAction;
import runix.service.SuggestionService;

public class SuggestionAction extends BaseAction {

	private boolean readonly;
	private String currentPage;
	private int pages = 15;
	private List suggestionList;
	private String suggestionId_q;
	private String suggestionId;
	private String title;
	private String suggesttype;// 建议类型
	private String publicity;// 是否公开
	private String toperson;
	private String sugester;
	private String content;
	private String flag;
	private SuggestionService suggestionService;
	private List<Map<String, String>> suggestions;
	private List<BaseData> types;
	private Suggestion suggestion;
	private boolean result;
	private Map<String, String> map;
	private String names;
	private String userRole;
	private String userid;
	/**
	 * 个人建议列表
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doQuery() {
		try {
			types = suggestionService.findTypesBySuggestion();// 获取建议类型
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
			condition.put("suggesttype", "-1".equals(suggesttype) ? ""
					: suggesttype);
			condition.put("publicity", "-1".equals(publicity) ? "" : publicity);
			readonly = false;
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				userRole = user.getTitle();
				userid = user.getUserId();
				if(userRole.indexOf("fm3") < 0){
					readonly = true;
				}
				int record = suggestionService.getCountSuggestions(condition);
				List<Map<String, String>> lst = (List<Map<String, String>>) suggestionService
						.getSuggestions(condition, offset, limit);
				lst=suggestionService.format(lst);
				Pageable pg = null;
				try {
					pg = new IbatisPage(lst, record, currentPageInt, pages);
				} catch (PageException e) {
					pg = null;
				}
				ServletActionContext.getRequest().setAttribute("pages", pg);
				ServletActionContext.getRequest().setAttribute("readonly",readonly);
				setSuggestions(pg.getResult());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	/**
	 * 添加页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doAddIndex() {
		try {
			types = suggestionService.findTypesBySuggestion();// 获取建议类型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doModifyIndex() {
		try {
			types = suggestionService.findTypesBySuggestion();// 获取建议类型
			suggestion = suggestionService.findSuggById(suggestionId);
			setNames(suggestionService.findNamesById(suggestion));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 修改
	 * 
	 * @author wangfq
	 * @return
	 */
	public String doModify() {
		try {
			suggestionService.update(suggestion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看页面
	 * 
	 * @author wangfq
	 * @return
	 */
	public String detail() {
		suggestion = suggestionService.findSuggById(suggestionId);
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @author wangfq
	 * @return
	 */
	public String delete() {
		suggestionService.delete(suggestionId);
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
			result = suggestionService.isExist(suggesttype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 持久化
	 * 
	 * @author wangfq
	 * @return
	 */
	public String persist() {
		try {
			BaseUser user = (BaseUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			suggestion.setSugester(user.getUserId());
			suggestionService.persist(suggestion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String doUpdateInit() {
		try {
			Suggestion suggestion = suggestionService
					.getSuggestionById(suggestionId_q);
			suggestionId = suggestion.getSuggestionId();
			title = suggestion.getTitle();
			suggesttype = suggestion.getSuggesttype();
			publicity = suggestion.getPublicity();
			toperson = suggestion.getToperson();
			sugester = suggestion.getSugester();
			content = suggestion.getContent();
			flag = suggestion.getFlag();
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
			Suggestion suggestion = suggestionService
					.getSuggestionById(suggestionId_q);
			suggestion.setSuggestionId(suggestionId);
			suggestion.setTitle(title);
			suggestion.setSuggesttype(suggesttype);
			suggestion.setPublicity(publicity);
			suggestion.setToperson(toperson);
			//suggestion.setSugester(sugester);
			suggestion.setContent(content);
			suggestion.setFlag(flag);
			suggestionService.modifySuggestion(suggestion);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete() {
		try {
			suggestionService.removeSuggestionById(suggestionId_q);
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
			Suggestion suggestion = new Suggestion();
			suggestion.setSuggestionId(suggestionId);
			suggestion.setTitle(title);
			suggestion.setSuggesttype(suggesttype);
			suggestion.setPublicity(publicity);
			suggestion.setToperson(toperson);
			suggestion.setSugester(sugester);
			suggestion.setContent(content);
			suggestion.setFlag(flag);
			suggestionService.saveSuggestion(suggestion);
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

	public void setSuggestionList(List suggestionList) {
		this.suggestionList = suggestionList;
	}

	public List getSuggestionList() {
		return suggestionList;
	}

	public void setSuggestionId_q(String suggestionId_q) {
		this.suggestionId_q = suggestionId_q;
	}

	public String getSuggestionId_q() {
		return suggestionId_q;
	}

	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}

	public String getSuggestionId() {
		return suggestionId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setSuggesttype(String suggesttype) {
		this.suggesttype = suggesttype;
	}

	public String getSuggesttype() {
		return suggesttype;
	}

	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}

	public String getPublicity() {
		return publicity;
	}

	public void setToperson(String toperson) {
		this.toperson = toperson;
	}

	public String getToperson() {
		return toperson;
	}

	public void setSugester(String sugester) {
		this.sugester = sugester;
	}

	public String getSugester() {
		return sugester;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setSuggestionService(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	public SuggestionService getSuggestionService() {
		return suggestionService;
	}

	public List<BaseData> getTypes() {
		return types;
	}

	public void setTypes(List<BaseData> types) {
		this.types = types;
	}

	public List<Map<String, String>> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Map<String, String>> suggestions) {
		this.suggestions = suggestions;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
