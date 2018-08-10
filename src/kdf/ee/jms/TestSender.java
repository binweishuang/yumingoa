package kdf.ee.jms;

public class TestSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		kdf.ee.jms.activemq.ProducerTool activemq = new kdf.ee.jms.activemq.ProducerTool();
		activemq.setTopic(true);
		activemq.setTransacted(true);
		activemq.setPersistent(true);
		activemq.produce("activemq,hello!!!!");
		
//		base.ee.jms.openjms.ProducerTool openjms = new base.ee.jms.openjms.ProducerTool();
//		openjms.setTopic(true);
//		activemq.setTransacted(true);
//		openjms.setSubject("topic1");
//		openjms.produce("openjms,hello!");
	}

}
