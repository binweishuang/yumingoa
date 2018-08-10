package kdf.ee.jms.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerTool implements MessageListener, ExceptionListener {

	private boolean running;

    private Session session;
    private Destination destination;
    private MessageProducer replyProducer;
    private boolean pauseBeforeShutdown;
    private int maxiumMessages;
    private String subject = "TOOL.DEFAULT";
    private boolean topic;
    private boolean transacted;
    private boolean durable;
    private String clientId;
    private int ackMode = Session.AUTO_ACKNOWLEDGE;
    private String consumerName = "James";;
    private long sleepTime;
    private long receiveTimeOut;
    private String retMessage;
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public String consume() {
		Connection connection = null;
		try {
			System.out.println("Connecting to URL: " + url);
			System.out.println("Consuming " + (topic ? "topic" : "queue") + ": " + subject);
            System.out.println("Using a " + (durable ? "durable" : "non-durable") + " subscription");
            
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            if (durable && clientId != null && clientId.length() > 0 && !"null".equals(clientId)) {
                connection.setClientID(clientId);
            }
            connection.setExceptionListener(this);
            connection.start();
            System.out.println("接收开始...");
            
            session = connection.createSession(transacted, ackMode);
            if (topic) {
                destination = session.createTopic(subject);
            } else {
                destination = session.createQueue(subject);
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

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
