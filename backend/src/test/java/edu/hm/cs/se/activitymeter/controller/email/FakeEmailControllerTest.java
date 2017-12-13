package edu.hm.cs.se.activitymeter.controller.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FakeEmailControllerTest {

  FakeEmailController controller;

  @Before
  public void beforeTest() {
    controller = new FakeEmailController();
  }

  @Test
  public void generateKey() throws Exception {
    assertEquals("1234", controller.generateKey());
  }

  @Test
  public void testActivationMail() {
    String key = controller.generateKey();
    Post p1 = new Post();
    Post p2 = new Post();
    Post p3 = new Post();
    p1.setAuthor("testAuthor");
    p1.setEmail("test@hm.edu");
    p1.setId(1L);
    p2.setAuthor("testAuthor2");
    p2.setEmail("test2@hm.edu");
    p2.setId(2L);
    p3.setAuthor("testAuthor2");
    p3.setEmail("test2@hm.edu");
    p3.setId(3L);

    assertTrue(controller.getInboxFor(p1.getEmail()).isEmpty());
    controller.sendActivationMail(p1, key);
    List<FakeEmailController.FakeMail> inbox = controller.getInboxFor(p1.getEmail());
    assertEquals(1, inbox.size());
    FakeEmailController.FakeMail mail = inbox.get(0);
    String expectedSubject = EmailTexts.ACTIVITY_ACTIVATION_SUBJECT;
    String expectedText = String.format(EmailTexts.ACTIVITY_ACTIVATION_TEXT, p1.getAuthor(),
        controller.getHost(), p1.getId(), key);
    assertEquals(expectedSubject, mail.getSubject());
    assertEquals(expectedText, mail.getText());

    assertTrue(controller.getInboxFor(p2.getEmail()).isEmpty());
    controller.sendActivationMail(p2, key);
    controller.sendActivationMail(p3, key);
    assertEquals(controller.getInboxFor(p2.getEmail()), controller.getInboxFor(p3.getEmail()));
    inbox = controller.getInboxFor(p2.getEmail());
    assertEquals(2, inbox.size());
  }

  @Test
  public void testCommentActivationMail() {
    Post p = new Post();
    p.setId(1L);
    Comment c = new Comment();
    c.setId(1L);
    c.setEmail("testEmail@hm.edu");
    c.setPost(p);
    String key = controller.generateKey();

    assertTrue(controller.getInboxFor(c.getEmail()).isEmpty());
    controller.sendActivationMail(c, key);
    assertEquals(1, controller.getInboxFor(c.getEmail()).size());
    FakeEmailController.FakeMail mail = controller.getInboxFor(c.getEmail()).get(0);

    String expectedSubject = EmailTexts.COMMENT_ACTIVATION_SUBJECT;
    String expectedText = String.format(EmailTexts.COMMENT_ACTIVATION_TEXT, c.getAuthor(),
        controller.getHost(), c.getId(), key);
    assertEquals(expectedSubject, mail.getSubject());
    assertEquals(expectedText, mail.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmailValidation_IllegalFormat1() {
    controller.isValidAddress("test@test@hm.edu");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmailValidation_IllegalFormat2() {
    controller.isValidAddress("testtesthm.edu");
  }

  @Test
  public void testEmailValidation() {
    assertTrue(controller.isValidAddress("test@hm.edu"));
    assertTrue(controller.isValidAddress("test@calpoly.edu"));
    assertFalse(controller.isValidAddress("test@gmail.com"));
  }


}