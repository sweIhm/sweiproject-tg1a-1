package edu.hm.cs.se.activitymeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.hm.cs.se.activitymeter.ActivityMeter;
import edu.hm.cs.se.activitymeter.model.Post;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ActivityMeter.class})
@AutoConfigureMockMvc
public class ActivityControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate db;

  private Post p;

  @Before
  public void setUp() throws Exception {
    db.execute("DROP TABLE Activity;");
    db.execute("CREATE TABLE Activity(" +
        "id INTEGER PRIMARY KEY," +
        "title VARCHAR(255) NOT NULL," +
        "text VARCHAR(255) NOT NULL," +
        "author VARCHAR(255) NOT NULL," +
        "email VARCHAR(1000) NOT NULL," +
        "published BOOLEAN NOT NULL);");
    db.execute("DROP SEQUENCE activity_id_seq;");
    db.execute("CREATE SEQUENCE activity_id_seq START WITH 1 INCREMENT BY 1;");
    p = new Post("testText", "testTitel", "testAuthor", "testEmail", true);
    p.setId(1L);
  }

  @Test
  public void listAll() throws Exception {
    addPostToDB(p);
    Post p2 = new Post("testText", "testTitel", "testAuthor", "testEmail", false);
    p2.setId(2L);
    addPostToDB(p2);
    Post p3 = new Post("testText", "testTitel", "testAuthor", "testEmail", true);
    p3.setId(3L);
    addPostToDB(p3);
    mvc.perform(MockMvcRequestBuilders.get("/activity"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json("[" + postToJson(new PostDTO(p)) + "," + postToJson(new PostDTO(p3)) + "]"));
  }

  @Test
  public void find() throws Exception {
    p.setId(2L);
    addPostToDB(p);
    mvc.perform(MockMvcRequestBuilders.get("/activity/2"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
  }

  @Test
  public void update() throws Exception {
    addPostToDB(p);
    p.setAuthor("gerste");
    p.setText("gerstenmeier");
    p.setTitle("hopfen und malz verloren");
    mvc.perform(MockMvcRequestBuilders.put("/activity/1")
        .content(postToJson(new PostDTO(p))).contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
  }

  @Test
  public void create() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/activity").contentType("application/json")
        .content(postToJson(p)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
    mvc.perform(MockMvcRequestBuilders.get("/activity"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[]"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/view/1;alert=activationsucceeded"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=activationfailed"));
    mvc.perform(MockMvcRequestBuilders.get("/activity"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[" + postToJson(new PostDTO(p)) + "]"));
  }

  @Test
  public void delete() throws Exception {
    addPostToDB(p);
    mvc.perform(MockMvcRequestBuilders.delete("/activity/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateNonExisting() throws Exception {
    mvc.perform(MockMvcRequestBuilders.put("/activity/1")
        .contentType("application/json")
        .content(postToJson(p)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }

  private void addPostToDB(Post p) {
    db.execute(String.format("INSERT INTO Activity VALUES(%d,'%s','%s','%s','%s',%s);",
        p.getId(), p.getTitle(), p.getText(), p.getAuthor(), p.getEmail(), p.isPublished()));
  }

  private String postToJson(PostDTO p) throws Exception {
    ObjectWriter w = new ObjectMapper().writer();
    return w.writeValueAsString(p);
  }

  private String postToJson(Post p) throws Exception {
    ObjectWriter w = new ObjectMapper().writer();
    return w.writeValueAsString(p);
  }
}