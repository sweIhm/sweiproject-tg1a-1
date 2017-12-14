package edu.hm.cs.se.activitymeter.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivationKeyCommentTest {

  @Test
  public void activationKeyCommentTest() throws Exception {
   ActivationKeyComment activationKeyComment = new ActivationKeyComment(3L, "1234");
   assertEquals(3L, activationKeyComment.getCommentId());
   assertEquals("1234", activationKeyComment.getKey());
   activationKeyComment.setCommentId(4L);
   activationKeyComment.setKey("12345");
    assertEquals(5L, activationKeyComment.getCommentId());
    assertEquals("12345", activationKeyComment.getKey());
  }
}