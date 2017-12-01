package edu.hm.cs.se.activitymeter.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PostDTOTest {

    @Test
    public void copyFromPost() throws Exception {
        Post p = new Post("Dies ist ein TestPost", "Test-Post", "Tester", "tester@hm.edu", false);
        PostDTO dto = new PostDTO(p);
        assertEquals(dto.getAuthor(), p.getAuthor());
        assertEquals(dto.getText(), p.getText());
        assertEquals(dto.getTitle(), p.getTitle());
        assertEquals(dto.getId(), p.getId());
    }

    @Test
    public void createDTOPost() throws Exception {
        PostDTO p = new PostDTO("text", "title", "author");
        assertEquals("text", p.getText());
        assertEquals("title", p.getTitle());
        assertEquals("author", p.getAuthor());
    }

    @Test
    public void getterAndSetter() throws Exception {
        PostDTO p = new PostDTO("text", "title", "author");
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