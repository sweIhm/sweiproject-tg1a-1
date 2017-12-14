package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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
        controller.getHost(), p.getId(), key);
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

  @Test
  public void testNoNotification_OpOnOp() {
    Post p = new Post();
    p.setEmail("test@hm.edu");
    p.setId(1L);
    Comment c = new Comment();
    c.setEmail("test@hm.edu");
    c.setId(1L);
    c.setAuthor("testAuthor");
    c.setPublished(true);
    c.setPost(p);
    p.setComments(Arrays.asList(c));

    assertTrue(controller.getInboxFor(p.getEmail()).isEmpty());
    controller.sendNotificationMails(p, c);
    assertEquals(controller.getInboxFor(p.getEmail()), controller.getInboxFor(c.getEmail()));
    assertTrue(controller.getInboxFor(p.getEmail()).isEmpty());
  }

  @Test
  public void testNoNotification_ExceptOP() {
    Post p = new Post();
    p.setEmail("test@hm.edu");
    p.setAuthor("testAuthor");
    p.setId(1L);
    Comment c_unpublished = new Comment();
    c_unpublished.setEmail("unpublished@hm.edu");
    c_unpublished.setId(1L);
    c_unpublished.setAuthor("unpublishedAuthor");
    c_unpublished.setPublished(false);
    c_unpublished.setPost(p);
    Comment c_trigger = new Comment();
    c_trigger.setEmail("comment@hm.edu");
    c_trigger.setId(2L);
    c_trigger.setAuthor("triggerAuthor");
    c_trigger.setPublished(true);
    c_trigger.setPost(p);
    p.setComments(Arrays.asList(c_unpublished, c_trigger));
    // All inboxes should be empty
    assertTrue(controller.getInboxFor(p.getEmail()).isEmpty());
    assertTrue(controller.getInboxFor(c_trigger.getEmail()).isEmpty());
    assertTrue(controller.getInboxFor(c_unpublished.getEmail()).isEmpty());
    controller.sendNotificationMails(p, c_trigger);
    // Op should receive email
    assertEquals(1, controller.getInboxFor(p.getEmail()).size());
    FakeEmailController.FakeMail mail = controller.getInboxFor(p.getEmail()).get(0);
    String expectedSubject = EmailTexts.NOTIFICATION_SUBJECT;
    String expectedText = String.format(EmailTexts.NOTIFICATION_TEXT, p.getAuthor(),
        controller.getHost(), p.getId());
    assertEquals(expectedSubject, mail.getSubject());
    assertEquals(expectedText, mail.getText());
    // Unpublished and trigger must not receive mail
    assertTrue(controller.getInboxFor(c_unpublished.getEmail()).isEmpty());
    assertTrue(controller.getInboxFor(c_trigger.getEmail()).isEmpty());
  }

  @Test
  public void testNoMultipleNotifications() {
    Post p = new Post();
    p.setEmail("test@hm.edu");
    p.setAuthor("testAuthor");
    p.setId(1L);
    Comment c_multiple1 = new Comment();
    c_multiple1.setEmail("multiple@hm.edu");
    c_multiple1.setId(1L);
    c_multiple1.setAuthor("multipleAuthor");
    c_multiple1.setPublished(true);
    c_multiple1.setPost(p);
    Comment c_multiple2 = new Comment();
    c_multiple2.setEmail("multiple@hm.edu");
    c_multiple2.setId(2L);
    c_multiple2.setAuthor("multipleAuthor");
    c_multiple2.setPublished(true);
    c_multiple2.setPost(p);
    Comment c_multiple3 = new Comment();
    c_multiple3.setEmail("multiple@hm.edu");
    c_multiple3.setId(3L);
    c_multiple3.setAuthor("pseudoMultipleAuthor");
    c_multiple3.setPublished(true);
    Comment c_trigger1 = new Comment();
    c_trigger1.setEmail("comment@hm.edu");
    c_trigger1.setId(4L);
    c_trigger1.setAuthor("triggerAuthor");
    c_trigger1.setPublished(true);
    c_trigger1.setPost(p);
    Comment c_trigger2 = new Comment();
    c_trigger2.setEmail("test@hm.edu");
    c_trigger2.setId(4L);
    c_trigger2.setAuthor("testAuthor");
    c_trigger2.setPublished(true);
    p.setComments(Arrays.asList(c_multiple1, c_multiple2, c_trigger1));

    // All inboxes should be empty
    assertTrue(controller.getInboxFor(p.getEmail()).isEmpty());
    assertTrue(controller.getInboxFor(c_trigger1.getEmail()).isEmpty());
    assertTrue(controller.getInboxFor(c_multiple1.getEmail()).isEmpty());
    controller.sendNotificationMails(p, c_trigger1);
    // multiple should only receive one email
    assertEquals(controller.getInboxFor(c_multiple1.getEmail()),
        controller.getInboxFor(c_multiple2.getEmail()));
    assertEquals(1, controller.getInboxFor(c_multiple1.getEmail()).size());
    FakeEmailController.FakeMail mail = controller.getInboxFor(c_multiple1.getEmail()).get(0);
    String expectedSubject = EmailTexts.NOTIFICATION_COMMENT_SUBJECT;
    String expectedText = String.format(EmailTexts.NOTIFICATION_COMMENT_TEXT, c_multiple1.getAuthor(),
        controller.getHost(), p.getId());
    assertEquals(expectedSubject, mail.getSubject());
    assertEquals(expectedText, mail.getText());
    assertFalse(mail.getText().contains("a.k.a."));
    // op also should receive an email
    assertEquals(1, controller.getInboxFor(p.getEmail()).size());
    // trigger1 must not receive an email
    assertTrue(controller.getInboxFor(c_trigger1.getEmail()).isEmpty());

    c_trigger2.setPost(p);
    c_multiple3.setPost(p);
    p.setComments(Arrays.asList(c_multiple1, c_multiple2, c_trigger1, c_multiple3, c_trigger2));

    controller.sendNotificationMails(p, c_trigger2);
    // multiple should now have one more email in his inbox
    assertEquals(controller.getInboxFor(c_multiple1.getEmail()),
        controller.getInboxFor(c_multiple2.getEmail()));
    assertEquals(2, controller.getInboxFor(c_multiple3.getEmail()).size());
    mail = controller.getInboxFor(c_multiple3.getEmail()).get(1);
    assertTrue(mail.getText().contains(c_multiple1.getAuthor()));
    assertTrue(mail.getText().contains(c_multiple3.getAuthor()));
    assertTrue(mail.getText().contains("a.k.a."));
    // op must not have received an additional email
    assertEquals(controller.getInboxFor(p.getEmail()),
        controller.getInboxFor(c_trigger2.getEmail()));
    assertEquals(1, controller.getInboxFor(c_trigger2.getEmail()).size());
    // trigger1 must have received an additional email
    assertEquals(1, controller.getInboxFor(c_trigger1.getEmail()).size());
  }
}