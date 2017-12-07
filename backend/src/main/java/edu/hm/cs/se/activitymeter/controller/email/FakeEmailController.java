package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;

public class FakeEmailController extends EmailController {
  @Override
  public boolean sendEmail(Post post, String activationKey) {
    System.out.println(post.getId() + "||" + activationKey);
    return true;
  }

  @Override
  public boolean sendEmail(Comment comment, String activationKey) {
    System.out.println(comment.getId() + "||" + activationKey);
    return true;
  }

  @Override
  public boolean sendNotification(Post post) {
    return true;
  }

  @Override
  public String generateKey() {
    return "1234";
  }
}
