package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class FakeEmailController extends EmailController {
  @Override
  public void sendActivationMail(Post post, String activationKey) {
    // Do nothing
  }

  @Override
  public void sendActivationMail(Comment comment, String activationKey) {
    // Do nothing
  }

  @Override
  public void sendNotificationMails(Post post, Comment triggger) {
    // Do nothing
  }

  @Override
  public void sendDeleteMail(Post post, String activationKey) {
    // Do nothing
  }

  @Override
  public void sendDeleteMail(Comment comment, String activationKey) {
    // Do nothing
  }

  @Override
  public String generateKey() {
    return "1234";
  }
}