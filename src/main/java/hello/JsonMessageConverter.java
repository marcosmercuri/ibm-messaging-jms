package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class JsonMessageConverter implements MessageConverter {

  @Autowired
  private ObjectMapper mapper;

  /**
   * Converts message to JSON. Used mostly by {@link org.springframework.jms.core.JmsTemplate}
   */
  @Override
  public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
    String json;

    try {
      json = mapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new MessageConversionException("Message cannot be parsed. ", e);
    }

    TextMessage message = session.createTextMessage();
    message.setText(json);

    return message;
  }

  /**
   * Extracts JSON payload for further processing by JacksonMapper.
   */
  @Override
  public Object fromMessage(javax.jms.Message message) throws JMSException, MessageConversionException {
    return ((TextMessage) message).getText();
  }
}
