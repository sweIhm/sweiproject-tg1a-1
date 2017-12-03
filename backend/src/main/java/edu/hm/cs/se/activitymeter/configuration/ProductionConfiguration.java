package edu.hm.cs.se.activitymeter.configuration;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"prod", "test"})
public class ProductionConfiguration {

  private ProductionConfiguration() {}

  @Bean
  public static EmailController newEmailController() {
    return new EmailController();
  }
}
