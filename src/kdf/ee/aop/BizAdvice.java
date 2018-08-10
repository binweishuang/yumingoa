package kdf.ee.aop;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kdf.constant.SystemConfig;
import kdf.tools.DateUtil;
import kdf.tools.GenerateUUID;
import kdf.web.listener.ContextListener;


//import base.service.BaseBizlogService;
//import base.service.impl.BaseBizlogServiceImpl;


import org.springframework.aop.AfterReturningAdvice;

public class BizAdvice implements AfterReturningAdvice {

	public void afterReturning(Object ret, Method method, Object[] arg, Object target)
			throws Throwable {
		System.out.println(method.getName());
		System.out.println(arg);
		if(arg.length>3) {
			if(arg[2] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest)arg[2];
				Map data = request.getParameterMap();
				
//				String userid = (String)((BaseUser)request.getSession().getAttribute("user")).getUserid();
				//BaseBizlogService service = (BaseBizlogService)SystemConfig.CONTEXT.getBean("baseBizlogService");
//				BaseBizlogService service = new BaseBizlogServiceImpl();
//				service.insertBaseBizlog(GenerateUUID.getInstance().getUUID(),userid,DateUtil.getDate(),"页面： "+request.getRequestURI(),"");
//				service.insertBaseBizlog(GenerateUUID.getInstance().getUUID(), userid, DateUtil.getDate(), "页面： "+request.getRequestURI(), "");
			}
		}
	}

}
