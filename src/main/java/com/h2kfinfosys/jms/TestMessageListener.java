package com.h2kfinfosys.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class TestMessageListener implements MessageListener {
	
	Session session = null;
	
	public TestMessageListener(Session session) {
		this.session = session;
	}

	public void onMessage(Message message) {
		if(message != null) {
			try {
				TextMessage txtMessage = (TextMessage) message;
				System.out.println(txtMessage.getText());
				message.acknowledge();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
