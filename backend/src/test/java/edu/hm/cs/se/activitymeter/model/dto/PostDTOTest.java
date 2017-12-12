package edu.hm.cs.se.activitymeter.model.dto;

import static org.junit.Assert.assertEquals;

import edu.hm.cs.se.activitymeter.model.Post;
import org.junit.Test;

public class PostDTOTest {

  @Test
  public void copyFromPost() throws Exception {
    Post p = new Post("Tester", "Test-Post", "Dies ist ein TestPost",
         "tester@hm.edu", false);
    p.setId(1L);
    PostDTO dto = new PostDTO(p);
    assertEquals("Tester", dto.getAuthor());
    assertEquals("Dies ist ein TestPost", dto.getText());
    assertEquals("Test-Post", dto.getTitle());
    assertEquals(1L, (long) dto.getId());
  }

  @Test
  public void getterAndSetter() throws Exception {
    PostDTO p = new PostDTO(new Post("a", "b", "c", "e", true));
    p.setAuthor("herbert");
    p.setText("textus");
    p.setTitle("titlus");
    p.setId(1000L);

    assertEquals("textus", p.getText());
    assertEquals("titlus", p.getTitle());
    assertEquals("herbert", p.getAuthor());
    assertEquals(1000L, (long)p.getId());
  }

}