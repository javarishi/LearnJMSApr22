package com.h2kfinfosys.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQMessageSender {
	
	private static String brokerURL = "tcp://localhost:61616";

	public static void main(String[] args) {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
		try {
			Connection conn = factory.createConnection();
			conn.start();
			
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("TEST.H2K.NAME");
			MessageProducer producer = session.createProducer(queue);
			Message message = session.createTextMessage("This is my First Text Message");
			for(int i=1; i < 10; i++) {
				message = session.createTextMessage("This is my First Text Message " + i);
				producer.send(message);
				
			}
			System.out.println("Done! ");
			session.close();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
				
	}
}
