package kdf.ee.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

public class ExceptionAdvice implements ThrowsAdvice {
	
	private Log log = LogFactory.getLog("SQL");
	
	public void afterThrowing(Method method, Object[] args, Object target, Throwable ex) throws Throwable {
		log.info("执行 " + method.getName() + " 时有异常抛出...." + ex);
	}
}
