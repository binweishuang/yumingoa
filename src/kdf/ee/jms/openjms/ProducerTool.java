package kdf.ee.jms.openjms;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProducerTool{

	private Destination destination;
	private String subject = "topic1";
	private boolean verbose = true;
    private boolean topic;
    private boolean transacted;
    private boolean persistent;
    private long timeToLive;
    private Context context;
    private String url;
    
    public ProducerTool() {
    	try {
    		init();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void init() throws Exception {
    	Properties p = null;
    	try {
	    	InputStream in = new BufferedInputStream(new FileInputStream("D:\\workspace\\baseweb\\src\\net\\baseweb\\jms\\openjms\\jndi.properties"));
	        p = new Properties();
	        p.load(in);	 
	        
    	} catch(FileNotFoundException e) {
    		e.printStackTrace();
    		throw new Exception("jndi.properties file is not found."+e);
    	} catch(IOException e) {
    		e.printStackTrace();
    		throw new Exception(e);
    	}
    	
    	if(null == url) url = p.getProperty(Context.PROVIDER_URL);
    	
    	try {
    		context = new InitialContext(p);
    	} catch(NamingException e) {
    		e.printStackTrace();
    		throw new Exception(e);
    	}
    	
    	if(!topic) this.subject = "queue1";
    }
    
	public void produce(String message) {		
		Connection connection = null;
		try {
			System.out.println("Connecting to URL: " + url);
			System.out.println("Using " + (persistent ? "persistent" : "non-persistent") + " messages");
			ConnectionFactory connectionFactory = topic ? (ConnectionFactory) context.lookup("JmsTopicConnectionFactory") : (ConnectionFactory) context.lookup("JmsQueueConnectionFactory");
			
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
            System.out.println("发送成功！");
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

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
    
}
