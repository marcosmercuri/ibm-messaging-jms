package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
  private final String queue;

  private final JmsTemplate jmsTemplate;

  public Sender(@Value("${ibm.mq.queue}") String queue, JmsTemplate jmsTemplate) {
    this.queue = queue;
    this.jmsTemplate = jmsTemplate;
  }

  public void sendMessage(Email email) {
    System.out.println("Sending an email message.");
    jmsTemplate.convertAndSend(queue, email);
  }
}
