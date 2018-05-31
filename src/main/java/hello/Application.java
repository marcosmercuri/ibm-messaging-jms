
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    // Launch the application
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    Sender sender = context.getBean(Sender.class);

    // Send a message with a POJO - the template reuse the message converter
    sender.sendMessage(new Email("info@example.com", "Hello"));
  }

}
