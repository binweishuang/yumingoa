package base.web.interceptor;

import java.util.Map;

import kdf.constant.SystemConfig;
import kdf.tools.DateUtil;
import kdf.tools.GenerateUUID;
import kdf.web.listener.ContextListener;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BizInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map session = arg0.getInvocationContext().getSession();
//		BaseUser user = (BaseUser) session.get("user");
//		BaseBizlogService service = new BaseBizlogServiceImpl();
		String actionName = arg0.getProxy().getActionName();
		String namespace = arg0.getProxy().getNamespace();
		String method = arg0.getProxy().getMethod();
		String url = namespace + "/" + actionName + ".action";
//		service.insertBaseBizlog(GenerateUUID.getInstance().getUUID(), user.getUserid(), DateUtil.getSystemTimestamp(), "页面： "+url+";方法："+method, "");
//		service.insertBaseBizlog(new Object[]{GenerateUUID.getInstance().getUUID(),user.getUserid(),DateUtil.getSystemTimestamp(),"页面： "+url+";方法："+method,""});
		return arg0.invoke();
	}

}
