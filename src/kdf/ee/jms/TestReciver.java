package kdf.ee.jms;

public class TestReciver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		kdf.ee.jms.activemq.ConsumerTool activemq = new kdf.ee.jms.activemq.ConsumerTool();
		activemq.setTopic(true);
		activemq.setTransacted(true);
		activemq.setDurable(true);
		activemq.setClientId("Tom");
		activemq.setConsumerName("Tom");
		activemq.consume();
		
//		base.ee.jms.openjms.ConsumerTool openjms = new base.ee.jms.openjms.ConsumerTool();
//		openjms.setTopic(true);
//		openjms.setDurable(true);
//		openjms.setTransacted(true);
//		openjms.setSubject("topic1");
//		openjms.setConsumerName("sub1");
//		openjms.consume();
	}

}
