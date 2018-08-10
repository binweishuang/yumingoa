package kdf.ee.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.AfterReturningAdvice;

public class SqlAdvice implements MethodBeforeAdvice,AfterReturningAdvice {

	private Log log = LogFactory.getLog("SQL");

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
//		log.info("Class : " + target.getClass().getName());
//		log.info("Method : " + method.getName());
		if (args != null) {
//			for (int i = 0; i < args.length; i++) {
//				log.info("arg" + i + " : " + args[i].toString());
//			}
			log.info("执行的SQL语句 : " + this.getPreparedSQL((String)args[0],(Object[])args[1]));
		}

	}
	
	public void afterReturning(Object ret, Method method, Object[] args, Object target) throws Throwable {
		log.info("执行SQL后得到的结果 : " + ret);
		
	}
	

	private String getPreparedSQL(String sql, Object[] params) throws Exception{
		// 1 如果没有参数，说明是不是动态SQL语句
		int paramNum = 0;
		if (null != params)
			paramNum = params.length;
		if (1 > paramNum)
			return sql;
		// 2 如果有参数，则是动态SQL语句
		StringBuffer returnSQL = new StringBuffer();
		String[] subSQL = sql.split("\\?");
		for (int i = 0; i < paramNum; i++) {
			if (params[i] instanceof Date) {
				returnSQL.append(subSQL[i]).append(" '").append(
						(java.util.Date) params[i]).append("' ");
			} else {
				returnSQL.append(subSQL[i]).append(" '").append(params[i])
						.append("' ");
			}
		}

		if (subSQL.length > params.length) {
			returnSQL.append(subSQL[subSQL.length - 1]);
		}
		return returnSQL.toString();
	}

}
