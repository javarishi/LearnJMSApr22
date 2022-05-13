package com.h2kfinfosys.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageListernerExample {
	
	private static String brokerURL = "tcp://localhost:61616";

	public static void main(String[] args) {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
		try {
			Connection conn = factory.createConnection();
			conn.start();
			Session session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Queue queue = session.createQueue("TEST.H2K.NAME");

			MessageConsumer consumer = session.createConsumer(queue);
			TestMessageListener listener = new TestMessageListener(session);
			consumer.setMessageListener(listener);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
				
	}
}
