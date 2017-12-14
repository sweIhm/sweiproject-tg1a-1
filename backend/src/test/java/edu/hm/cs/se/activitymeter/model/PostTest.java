package edu.hm.cs.se.activitymeter.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.Test;

public class PostTest {

  @Test
  public void createTest() throws Exception {
    Post p = new Post("author","title", "text", "email", true, new ArrayList<>());
    assertEquals("text", p.getText());
    assertEquals("title", p.getTitle());
    assertEquals("author", p.getAuthor());
    assertEquals("email", p.getEmail());
    assertEquals(true, p.isPublished());
  }

  @Test
  public void getterAndSetter() throws Exception {
    Post p = new Post("author", "text", "title", "email", true, new ArrayList<>());
    p.setAuthor("herbert");
    p.setText("textus");
    p.setTitle("titlus");
    p.setId(1000L);
    p.setEmail("emailus");
    p.setPublished(false);

    assertEquals("textus", p.getText());
    assertEquals("titlus", p.getTitle());
    assertEquals("herbert", p.getAuthor());
    assertEquals(1000L, (long) p.getId());
    assertEquals("emailus", p.getEmail());
    assertFalse(p.isPublished());
  }
}