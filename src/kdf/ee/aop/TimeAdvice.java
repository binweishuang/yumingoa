package kdf.ee.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeAdvice implements MethodInterceptor {
	
	private Log log = LogFactory.getLog("SQL");
	
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		long procTime = System.currentTimeMillis();
		log.info("开始执行 " + methodInvocation.getMethod() + " 方法");  

		try {
			Object result = methodInvocation.proceed(); 
			return result;
		} finally {
			//计算执行时间
			procTime = System.currentTimeMillis() - procTime;
			log.info("执行 " + methodInvocation.getMethod() + " 方法结束");
			log.info("执行 " + methodInvocation.getMethod().getName() + " 方法共用了 " + procTime + "毫秒");
		}	
	}

}
