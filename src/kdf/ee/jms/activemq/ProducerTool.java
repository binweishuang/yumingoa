package kdf.ee.jms.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTool{

	private Destination destination;
	private String subject = "TOOL.DEFAULT";
	private boolean verbose = true;
    private boolean topic;
    private boolean transacted;
    private boolean persistent;
    private long timeToLive;
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
	public void produce(String message) {
		Connection connection = null;
		try {
			System.out.println("Connecting to URL: " + url);
			System.out.println("Using " + (persistent ? "persistent" : "non-persistent") + " messages");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
			connection.start();

            // Create the session
            Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
            if (topic) {
                destination = session.createTopic(subject);
            } else {
                destination = session.createQueue(subject);
            }
            
            // Create the producer.
            MessageProducer producer = session.createProducer(destination);
            if (persistent) {
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            } else {
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            }
            if (timeToLive != 0) {
                producer.setTimeToLive(timeToLive);
            }

            TextMessage msg = session.createTextMessage((String)message);
            if (verbose) {
                String strmsg = msg.getText();
                if (strmsg.length() > 50) {
                	strmsg = strmsg.substring(0, 50) + "...";
                }
                System.out.println("Sending message: " + strmsg);
            }
            producer.send(msg);
            if (transacted) {
                session.commit();
            }
            System.out.println("���ͳɹ���");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            try {
            	connection.close();
            } catch (Throwable ignore) {
            }
        }
	}
	
	public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(boolean topic) {
        this.topic = topic;
    }

    public void setQueue(boolean queue) {
        this.topic = !queue;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
    
}
