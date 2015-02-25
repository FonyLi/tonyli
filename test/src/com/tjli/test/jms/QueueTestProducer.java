package com.tjli.test.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class QueueTestProducer {
	public static void main(String[] args) throws Exception { 
		
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");  
     
        Connection connection = factory.createConnection();  
        connection.start();           
  
        //创建一个Queue  
        Queue queue = new ActiveMQQueue("testQueue");  
        //创建一个Session  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);           
  
        //创建一个生产者，然后发送多个消息。  
        MessageProducer producer = session.createProducer(queue);  
        for(int i=0; i<10; i++){  
            producer.send(session.createTextMessage("Message:" + i));  
        }  
    }  
}
