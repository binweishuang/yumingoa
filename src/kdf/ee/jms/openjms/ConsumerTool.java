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
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerTool implements MessageListener, ExceptionListener {

	private boolean running;

    private Session session;
    private Destination destination;
    private MessageProducer replyProducer;

    private boolean pauseBeforeShutdown;
    private int maxiumMessages;
    private String subject = "topic1";
    private boolean topic;
    private boolean transacted;
    private boolean durable;
    private String clientId;
    private int ackMode = Session.AUTO_ACKNOWLEDGE;
    private String consumerName = "sub1";
    private long sleepTime;
    private long receiveTimeOut;
    
    private String retMessage;
    private Context context;
    private String url;
    
    public ConsumerTool() {
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
    
	public String consume() {
		Connection connection = null;		
		try {
			System.out.println("Connecting to URL: " + url);
			System.out.println("Consuming " + (topic ? "topic" : "queue") + ": " + subject);
            System.out.println("Using a " + (durable ? "durable" : "non-durable") + " subscription");
            
            ConnectionFactory connectionFactory = topic ? (ConnectionFactory) context.lookup("JmsTopicConnectionFactory") : (ConnectionFactory) context.lookup("JmsQueueConnectionFactory");
            connection = connectionFactory.createConnection();
            if (durable && clientId != null && clientId.length() > 0 && !"null".equals(clientId)) {
                connection.setClientID(clientId);
            }
            connection.setExceptionListener(this);
            connection.start();
            System.out.println("接收开始...");
            
            session = connection.createSession(transacted, ackMode);
            if (topic) {
                destination = (Topic) context.lookup(subject);
            } else {
                destination = (Queue) context.lookup(subject);
            }

            replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer consumer = null;
            if (durable && topic) {
                consumer = session.createDurableSubscriber((Topic)destination, consumerName);
            } else {
                consumer = session.createConsumer(destination);
            }            

            if (maxiumMessages > 0) {
                consumeMessagesAndClose(connection, session, consumer);
            } else {
                if (receiveTimeOut == 0) {
                    consumer.setMessageListener(this);
                } else {
                    consumeMessagesAndClose(connection, session, consumer, receiveTimeOut);
                }
            }
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		return retMessage;
	}
	
	public void onMessage(Message message) {
		try {
			TextMessage txtMsg = (TextMessage)message;
			retMessage = txtMsg.getText();
			
            if (message.getJMSReplyTo() != null) {
                replyProducer.send(message.getJMSReplyTo(), session.createTextMessage("Reply: " + message.getJMSMessageID()));
            }

            if (transacted) {
                session.commit();
            } else if (ackMode == Session.CLIENT_ACKNOWLEDGE) {
                message.acknowledge();
            }
            System.out.println("接收成功:"+this.retMessage);
        } catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        } finally {
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
        }
		
	}
	public synchronized void onException(JMSException arg0) {
		System.out.println("JMS Exception occured.  Shutting down client.");
        running = false;
	}
	
	synchronized boolean isRunning() {
	    return running;
	}
	
	protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer) throws JMSException, IOException {
        System.out.println("We are about to wait until we consume: " + maxiumMessages + " message(s) then we will shutdown");

        for (int i = 0; i < maxiumMessages && isRunning();) {
            Message message = consumer.receive(1000);
            if (message != null) {
                i++;
                onMessage(message);
            }
        }
        System.out.println("Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            System.out.println("Press return to shut down");
            System.in.read();
        }
    }

    protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer, long timeout) throws JMSException, IOException {
        System.out.println("We will consume messages while they continue to be delivered within: " + timeout + " ms, and then we will shutdown");

        Message message;
        while ((message = consumer.receive(timeout)) != null) {
            onMessage(message);
        }

        System.out.println("Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            System.out.println("Press return to shut down");
            System.in.read();
        }
    }
    
    public void setAckMode(String ackMode) {
        if ("CLIENT_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.CLIENT_ACKNOWLEDGE;
        }
        if ("AUTO_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.AUTO_ACKNOWLEDGE;
        }
        if ("DUPS_OK_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.DUPS_OK_ACKNOWLEDGE;
        }
        if ("SESSION_TRANSACTED".equals(ackMode)) {
            this.ackMode = Session.SESSION_TRANSACTED;
        }
    }

    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public void setMaxiumMessages(int maxiumMessages) {
        this.maxiumMessages = maxiumMessages;
    }

    public void setPauseBeforeShutdown(boolean pauseBeforeShutdown) {
        this.pauseBeforeShutdown = pauseBeforeShutdown;
    }

    public void setReceiveTimeOut(long receiveTimeOut) {
        this.receiveTimeOut = receiveTimeOut;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
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

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void setReplyProducer(MessageProducer replyProducer) {
		this.replyProducer = replyProducer;
	}

	public void setAckMode(int ackMode) {
		this.ackMode = ackMode;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
    
}
