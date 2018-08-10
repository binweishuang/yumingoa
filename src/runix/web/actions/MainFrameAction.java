package runix.web.actions;

import java.util.List;

import kdf.web.action.BaseAction;
import runix.service.BaseUserService;



public class MainFrameAction extends BaseAction{
	private String signin;
	private List birthPeoples;
	private BaseUserService baseUserService;
	public String doQuery(){
		try {
			birthPeoples = baseUserService.getBirthPeoples();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getSignin() {
		return signin;
	}
	public void setSignin(String signin) {
		this.signin = signin;
	}
	public List getBirthPeoples() {
		return birthPeoples;
	}
	public void setBirthPeoples(List birthPeoples) {
		this.birthPeoples = birthPeoples;
	}
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	
	

}
