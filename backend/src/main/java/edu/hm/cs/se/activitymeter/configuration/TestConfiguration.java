package edu.hm.cs.se.activitymeter.configuration;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.controller.email.FakeEmailController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class TestConfiguration {
	@Bean
	public static EmailController newFakeEmailController() {
		return new FakeEmailController();
	}
}
