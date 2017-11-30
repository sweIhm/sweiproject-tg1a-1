package edu.hm.cs.se.activitymeter.model;

import org.junit.Test;

import javax.validation.constraints.AssertFalse;

import static org.junit.Assert.*;


public class PostTest {

	@Test
	public void createTest() throws Exception {
		Post p = new Post("text", "title", "author", "email", true);
		assertEquals("text", p.getText());
		assertEquals("title", p.getTitle());
		assertEquals("author", p.getAuthor());
		assertEquals("email", p.getEmail());
		assertEquals(true, p.isPublished());
	}

	@Test
	public void getterAndSetter() throws Exception {
		Post p = new Post("text", "title", "author", "email", true);
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