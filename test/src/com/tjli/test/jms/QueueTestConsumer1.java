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

public class QueueTestConsumer1 {
	public static void main(String[] args) throws Exception { 
		
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");  
     
        Connection connection = factory.createConnection();  
        connection.start();  
         
  
        //创建一个Queue  
        Queue queue = new ActiveMQQueue("testQueue");  
        //创建一个Session  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
         
  
        //注册消费者1  
        MessageConsumer comsumer1 = session.createConsumer(queue);  
        comsumer1.setMessageListener(new MessageListener(){  
            public void onMessage(Message m) {  
                try {  
                    System.out.println("Consumer1 get " + ((TextMessage)m).getText());  
                    try
                    {
                    	Thread.sleep(1000);
                    }
                    catch(Exception e)
                    {
                    	
                    }
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }  
            }  
        });  

    }  
}
