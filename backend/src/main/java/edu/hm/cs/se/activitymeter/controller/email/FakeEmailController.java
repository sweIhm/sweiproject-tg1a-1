package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;

public class FakeEmailController extends EmailController {
  @Override
  public boolean sendActivationMail(Post post, String activationKey) {
    return true;
  }

  @Override
  public boolean sendActivationMail(Comment comment, String activationKey) {
    return true;
  }

  @Override
  public boolean sendNotificationMail(Post post) {
    return true;
  }

  @Override
  public boolean sendDeleteMail(Post post, String activationKey) {
    return true;
  }

  @Override
  public boolean sendDeleteMail(Comment comment, String activationKey) {
    return true;
  }

  @Override
  public String generateKey() {
    return "1234";
  }
}