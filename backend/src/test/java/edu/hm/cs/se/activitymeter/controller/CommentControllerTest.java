package edu.hm.cs.se.activitymeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.hm.cs.se.activitymeter.ActivityMeter;
import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import edu.hm.cs.se.activitymeter.model.dto.CommentDTO;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ActivityMeter.class})
@AutoConfigureMockMvc
public class CommentControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate db;

  private Post p;

  private Comment c;

  private static final String URL = "/api/activity/";

  @Before
  public void setUp() throws Exception {
    db.execute("DELETE FROM POST_KEYWORD;");
    db.execute("DELETE FROM Comment;");
    db.execute("DELETE FROM Post;");
    db.execute("DROP SEQUENCE post_id_seq;");
    db.execute("CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;");
    db.execute("DROP SEQUENCE post_id_seq;");
    db.execute("CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;");
    p = new Post("testText", "testTitel", "testAuthor", "testEmail", true, new ArrayList<>());
    p.setId(1L);
    c = new Comment("testText",  "testAuthor", "testEmail", true,p);
    c.setId(1L);
  }

  @Test
  public void listAll() throws Exception {
    addPostToDB(p);
    addCommentToDB(c);
    Comment c2 = new Comment("testText",  "testAuthor", "testEmail", true,p);
    c2.setId(2L);
    addCommentToDB(c2);

    mvc.perform(MockMvcRequestBuilders.get(URL + "1/comment"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json("[" + toJson(new CommentDTO(c)) + "," + toJson(new CommentDTO(c2)) + "]"));
  }

  @Test
  public void create() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post(URL).contentType("application/json")
            .content(toJson(p)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(toJson(new PostDTO(p))));

    mvc.perform(MockMvcRequestBuilders.get(URL))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[]"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/view/1;alert=activationsucceeded"));

    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=activationfailed"));
    mvc.perform(MockMvcRequestBuilders.get(URL))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[" + toJson(new PostDTO(p)) + "]"));
    c.setPost(null);
    mvc.perform(MockMvcRequestBuilders.post(URL+"1/comment").contentType("application/json")
            .content(toJson(c)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(toJson(new CommentDTO(c))));
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1?key=124"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=commentactivationfailed"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1?key=1234"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/view/1;alert=commentactivationsucceeded"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1?key=1234"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=commentactivationfailed"));
    mvc.perform(MockMvcRequestBuilders.get(URL+"1/comment"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[" + toJson(new CommentDTO(c)) + "]"));
  }

  @Test
  public void find() throws Exception {
    c.setId(2L);
    addPostToDB(p);
    addCommentToDB(c);
    mvc.perform(MockMvcRequestBuilders.get(URL + "1/comment/2"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(toJson(new CommentDTO(c))));
  }

  @Test
  public void delete() throws Exception {
    addPostToDB(p);
    addCommentToDB(c);
    mvc.perform(MockMvcRequestBuilders.delete(URL + "1/comment/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1/delete?key=124"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=commentdeletefailed"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1/delete?key=1234"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/view/1;alert=commentdeletesucceeded"));
    mvc.perform(MockMvcRequestBuilders.get(URL + "/1/comment/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[]"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/comment/1/delete?key=1234"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=commentdeletefailed"));
  }




  @Test
  public void update() throws Exception {
    addPostToDB(p);
    c.setPost(p);
    addCommentToDB(c);
    c.setAuthor("gerste");
    c.setText("gerstenmeier");

    mvc.perform(MockMvcRequestBuilders.put(URL + "1/comment/1")
        .content(toJson(c)).contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(toJson(new CommentDTO(c))));
  }


  @Test
  public void updateNonExisting() throws Exception {
    mvc.perform(MockMvcRequestBuilders.put(URL + "1/comment/1")
        .contentType("application/json")
        .content(toJson(p)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }

  private void addPostToDB(Post p) {
    db.execute(String.format("INSERT INTO Post(post_id, title, text, author, email, published, views) VALUES(%d,'%s','%s','%s','%s',%s, 0);",
        p.getId(), p.getTitle(), p.getText(), p.getAuthor(), p.getEmail(), p.isPublished()));
  }

  private void addCommentToDB(Comment c) {
    db.execute(String.format("INSERT INTO Comment(comment_id, text, author, email, published, post_id) VALUES(%d,'%s','%s','%s','%s',%d);",
            c.getId(), c.getText(),  c.getAuthor(), c.getEmail(), c.isPublished(), c.getPost().getId()));
  }


  private String toJson(Object p) throws Exception {
    ObjectWriter w = new ObjectMapper().writer();
    System.out.println(w.writeValueAsString(p));
    return w.writeValueAsString(p);
  }
}