package edu.hm.cs.se.activitymeter.controller.email;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import edu.hm.cs.se.activitymeter.model.Post;
import org.junit.Test;

public class FakeEmailControllerTest {

  @Test
  public void sendEmail() throws Exception {
    EmailController ec = new EmailController();
    assertTrue(ec.sendEmail(new Post(), ""));
  }

  @Test
  public void generateKey() throws Exception {
    assertEquals("1234", new FakeEmailController().generateKey());
  }

}