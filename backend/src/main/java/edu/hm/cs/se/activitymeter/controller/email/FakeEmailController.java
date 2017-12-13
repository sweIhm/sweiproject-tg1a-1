package edu.hm.cs.se.activitymeter.controller.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class FakeEmailController extends AbstractEmailController {

  @Override
  protected boolean sendMail(String recipient, String subject, String text) {
    return true;
  }

  @Override
  protected String getHost() {
    return "http://localhost:8080";
  }

  @Override
  public String generateKey() {
    return "1234";
  }
}