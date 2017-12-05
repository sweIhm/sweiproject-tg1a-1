package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Post;

public class FakeEmailController extends EmailController {
  @Override
  public boolean sendEmail(Post post, String activationKey) {
    return true;
  }

  @Override
  public String generateKey() {
    return "1234";
  }
}
