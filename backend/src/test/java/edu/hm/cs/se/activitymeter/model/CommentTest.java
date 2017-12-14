package edu.hm.cs.se.activitymeter.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {
  @Test
  public void commentTest() throws Exception {
    Post p = new Post();
    p.setId(3L);
    Comment comment = new Comment("text", "author", "email", false, p);
    comment.setId(3L);
    assertEquals("author", comment.getAuthor());
    assertEquals("text", comment.getText());
    assertEquals((Long)3L, comment.getId());
    assertEquals("email", comment.getEmail());
    assertEquals(false, comment.isPublished());
    comment.setAuthor("Hansi Hinterseer");
    comment.setText("Hände zum Himmel, Hände zur Hölle");
    comment.setEmail("hansi@hinterse.er");
    comment.setPublished(true);
    assertEquals("Hansi Hinterseer", comment.getText());
    assertEquals("Hände zum Himmel, Hände zur Hölle", comment.getText());
    assertEquals("hansi@hinterse.er", comment.getEmail());
    assertEquals(true, comment.isPublished());
  }
}