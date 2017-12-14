package edu.hm.cs.se.activitymeter.model.dto;

import static org.junit.Assert.*;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import org.junit.Test;


public class CommentDTOTest {
  @Test
  public void commentDTOTest() throws Exception {
    Post p = new Post();
    p.setId(3L);
    Comment comment = new Comment("text", "author", "email", false, p);
    comment.setId(3L);
    CommentDTO commentDTO = new CommentDTO(comment);
    assertEquals("author", commentDTO.getAuthor());
    assertEquals("text", commentDTO.getText());
    assertEquals((Long)3L, commentDTO.getId());
    commentDTO.setAuthor("Hansi Hinterseer");
    commentDTO.setText("Hände zum Himmel, Hände zur Hölle");
    assertEquals("Hansi Hinterseer", commentDTO.getAuthor());
    assertEquals("Hände zum Himmel, Hände zur Hölle", commentDTO.getText());
    assertNotNull(new CommentDTO());
  }
}