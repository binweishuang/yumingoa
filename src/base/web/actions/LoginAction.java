package base.web.actions;

import javax.servlet.http.Cookie;

import kdf.constant.SystemConfig;
import kdf.tools.DateUtil;
import kdf.tools.GenerateUUID;
import kdf.tools.MD5;
import kdf.web.action.BaseAction;
import kdf.web.listener.OnLineUserListener;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseUser;
import runix.service.BaseUserService;



public class LoginAction extends BaseAction {

	private String username;
	private String validateCode;

	private BaseUserService baseUserService;

	private String password;
	private int msg;



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public BaseUserService getBaseUserService() {
		return baseUserService;
	}


	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}


	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String init() throws Exception {
		
		return INPUT;
	}
	

	public String login() {
		try {
			if(this.baseUserService.getBaseUserNum(username)==0) {
				System.out.println("此用户不存在.");
				this.addFieldError("username", "此用户不存在.");
				return INPUT;
			}
			password = new MD5().getMD5ofStr(password);
			BaseUser user =  baseUserService.getBaseUserByUsername(username);
			if(!password.equals(user.getPassword())) {
				System.out.println("密码错误.");
				this.addFieldError("pwd", "密码错误.");
				return INPUT;
			}
	//		if(SystemConfig.IS_STARTUP_VALIDATE) {
			String code = (String)ServletActionContext.getContext().getSession().get("validateCode");
				if(!code.toLowerCase().equals(this.validateCode.toLowerCase())) {
					System.out.println("验证码错误.");
					this.addFieldError("validateCode", "验证码错误.");
					return INPUT;
				}
	//		}
			
			if("0".equals( user.getState())) {
				this.addActionMessage("此用户已经离职.");
				return INPUT;
			}
			
		//	this.baseLoginlogService.insertBaseLoginlog(GenerateUUID.getInstance().getUUID(),username,ServletActionContext.getRequest().getRemoteAddr(),DateUtil.getSystemTimestamp());
			ServletActionContext.getRequest().getSession().setAttribute("user",user);
			//删除原来已经存在的cookie
			Cookie delcookie = new Cookie("cookieusername", null); 
			delcookie.setMaxAge(0);
			this.getResponse().addCookie(delcookie); 
			//设置新的cookie
			Cookie cookie = new Cookie("cookieusername",username);
			cookie.setMaxAge(24*60*60);//设置有效期为24小时
			this.getResponse().addCookie(cookie);
		//	System.out.println(user.getTitle());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("出现异常.");
			this.addActionMessage(this.getText("exception"));
			return ERROR;
		}
		System.out.println("success login.");
		return SUCCESS;
	} 
	
	public String checkLogin(){
		try{
			if(this.baseUserService.getBaseUserNum(username)==0) {
				System.out.println("此用户不存在.");
				msg = 1;
				return "checkLogin";
			}
			password = new MD5().getMD5ofStr(password);
			BaseUser user =  baseUserService.getBaseUserByUsername(username);
			if(!password.equals(user.getPassword())) {
				System.out.println("密码错误.");
				msg = 2;
				return "checkLogin";
			}
		//	if(SystemConfig.IS_STARTUP_VALIDATE) {
			String code = (String)ServletActionContext.getContext().getSession().get("validateCode");
				if(!code.toLowerCase().equals(this.validateCode.toLowerCase())) {
					System.out.println("验证码错误.");
					msg = 3;
					return "checkLogin";
				}
		//	}
			if("0".equals( user.getState())) {
				msg = 4;
				return "checkLogin";
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("出现异常.");
			this.addActionMessage(this.getText("exception"));
			return ERROR;
		}
		msg = 5;
		return "checkLogin";
	}
	
	public String logout() {

		return "logout";
	}
	
	public String exit() {

		return "exit";
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public int getMsg() {
		return msg;
	}


	public void setMsg(int msg) {
		this.msg = msg;
	}

	
	
}
