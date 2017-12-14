package edu.hm.cs.se.activitymeter.controller.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("default")
public class FakeEmailController extends AbstractEmailController {

  private Map<String, List<FakeMail>> inboxes = new HashMap<>();

  @Override
  protected boolean sendMail(String recipient, String subject, String text) {
    getInboxFor(recipient).add(new FakeMail(subject, text));
    log.info("{} received fakeemail", recipient);
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

  public List<FakeMail> getInboxFor(String recipient) {
    inboxes.putIfAbsent(recipient, new ArrayList<>());
    return inboxes.get(recipient);
  }

  public class FakeMail {

    private String subject;
    private String text;

    public FakeMail(String subject, String text) {
      this.subject = subject;
      this.text = text;
    }

    public String getSubject() {
      return subject;
    }

    public String getText() {
      return text;
    }
  }

}