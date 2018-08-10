package kdf.ee.aop;

public class BeanImpl implements Bean {

	public void theMethod() {
		System.out.println(this.getClass().getName() 
		        + "." + new Exception().getStackTrace()[0].getMethodName() 
		        + "()" 
		        + " says HELLO!"); 

	}

}
