package edu.hm.cs.se.activitymeter.configuration;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.controller.email.FakeEmailController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default", "test"})
public class TestConfiguration {
  public TestConfiguration() {
    // Empty constructor for spring
  }

  @Bean
  @Profile("default")
  public static EmailController newFakeEmailController() {
    return new FakeEmailController();
  }

  @Bean
  @Profile("test")
  @Primary
  public static EmailController newTestEmailControllen() {
    return new EmailController();
  }
}
