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

}