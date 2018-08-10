package base.web.interceptor;

import java.util.Map;

import runix.persistent.model.BaseUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private String target;
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map session = arg0.getInvocationContext().getSession();
		BaseUser user = (BaseUser) session.get("user");
		if(null != user) {
			return arg0.invoke();
		} else {
////			return Action.LOGIN;
//			String url = "";   
//	        String namespace = arg0.getProxy().getNamespace();   
//	        if (!"".equals(namespace) && !namespace.equals("/")){   
//	            url = url + namespace;   
//	        }   
//	        String actionName = arg0.getProxy().getActionName();   
//	        if (!"".equals(actionName)){   
//	            url = url + "/" + actionName + ".action";   
//	        }   
//	        
//	        Map paramMap = arg0.getInvocationContext().getParameters();
//	        Set set = paramMap.entrySet();
//	        String queryString = "?";
//	        for(Iterator iter= set.iterator();iter.hasNext();) {
//	        	Map.Entry entry = (Map.Entry)iter.next();
//	        	queryString+=entry.getKey()+"=";
//	        	Object obj = entry.getValue();
//	        	String val = "";
//	        	if (obj instanceof String[]){
//	        	   String[] strs = (String[])obj;
//	        	   val = Arrays.toString(strs);//jdk1.5以上才支持，1.4的话就自己循环
//	        	}else{
//	        	   val = obj.toString();
//	        	}
//	        	val = val.substring(val.indexOf("[")+1, val.indexOf("]"));
//
//	        	queryString += val+"&";
//	        	
//	        	
//	        }
//	        
//
//			target = url+queryString;
//			target = target.substring(target.indexOf("/")+1);
//			session.put("autologintarget", target);
////			ValueStack stack = arg0.getStack();   
////			stack.set("target", target);
//			return "autologin";
			return "sessionlost";
		}
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
}
