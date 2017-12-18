package edu.hm.cs.se.activitymeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.hm.cs.se.activitymeter.ActivityMeter;
import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.Post;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import java.util.ArrayList;
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

  private ArrayList<Keyword> k;

  private static final String URL = "/api/activity/";

  @Before
  public void setUp() throws Exception {
    db.execute("DROP TABLE Keyword;");
    db.execute("CREATE TABLE Keyword(" +
        "keyword_id INTEGER PRIMARY KEY," +
        "content VARCHAR(255) NOT NULL);");
    db.execute("DROP SEQUENCE keyword_id_seq;");
    db.execute("CREATE SEQUENCE keyword_id_seq START WITH 1 INCREMENT BY 1;");
    db.execute("DROP TABLE Post;");
    db.execute("CREATE TABLE Post(" +
        "post_id INTEGER PRIMARY KEY," +
        "title VARCHAR(255) NOT NULL," +
        "text VARCHAR(255) NOT NULL," +
        "author VARCHAR(255) NOT NULL," +
        "email VARCHAR(1000) NOT NULL," +
        "published BOOLEAN NOT NULL);");
    db.execute("DROP SEQUENCE post_id_seq;");
    db.execute("CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;");
    db.execute("DELETE FROM POST_KEYWORD;");
    db.execute("INSERT INTO Keyword VALUES(1,'testKeyword1');");
    k = new ArrayList<Keyword>();
    Keyword k1 = new Keyword("testKeyword1");
    k1.setId(1L);
    k.add(k1);
    p = new Post("testText", "testTitel", "testAuthor", "testEmail", true, k);
    p.setId(1L);
  }

  @Test
  public void listAll() throws Exception {
    addPostToDB(p);
    Post p2 = new Post("testText", "testTitel", "testAuthor", "testEmail", false, k);
    p2.setId(2L);
    addPostToDB(p2);
    Post p3 = new Post("testText", "testTitel", "testAuthor", "testEmail", true, k);
    p3.setId(3L);
    addPostToDB(p3);
    mvc.perform(MockMvcRequestBuilders.get(URL))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json("[" + postToJson(new PostDTO(p)) + "," + postToJson(new PostDTO(p3)) + "]"));
  }

  @Test
  public void find() throws Exception {
    p.setId(2L);
    addPostToDB(p);
    mvc.perform(MockMvcRequestBuilders.get(URL + "2"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
  }

  @Test
  public void update() throws Exception {
    addPostToDB(p);
    p.setAuthor("gerste");
    p.setText("gerstenmeier");
    p.setTitle("hopfen und malz verloren");
    mvc.perform(MockMvcRequestBuilders.put(URL + "1")
        .content(postToJson(new PostDTO(p))).contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
  }

  @Test
  public void create() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post(URL).contentType("application/json")
        .content(postToJson(p)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(postToJson(new PostDTO(p))));
    mvc.perform(MockMvcRequestBuilders.get(URL))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[]"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=124"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=activationfailed"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/view/1;alert=activationsucceeded"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1?key=1234"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=activationfailed"));
    mvc.perform(MockMvcRequestBuilders.get(URL))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[" + postToJson(new PostDTO(p)) + "]"));
  }

  @Test
  public void delete() throws Exception {
    addPostToDB(p);
    mvc.perform(MockMvcRequestBuilders.delete(URL + "1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mvc.perform(MockMvcRequestBuilders.get("/activation/1/delete?key=124"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=deletefailed"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1/delete?key=1234"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=deletesucceeded"));
    mvc.perform(MockMvcRequestBuilders.get(URL))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[]"));
    mvc.perform(MockMvcRequestBuilders.get("/activation/1/delete?key=1234"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard;alert=deletefailed"));
  }

  @Test
  public void updateNonExisting() throws Exception {
    mvc.perform(MockMvcRequestBuilders.put(URL + "1")
        .contentType("application/json")
        .content(postToJson(p)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }

  @Test
  public void getTags() throws Exception {
    db.execute("INSERT INTO Keyword VALUES(2,'testKeyword2');");
    db.execute("INSERT INTO Keyword VALUES(3,'testKeyword3');");
    mvc.perform(MockMvcRequestBuilders.get("/api/activity/keywords"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json("[{\"content\":\"testKeyword3\"},{\"content\":\"testKeyword2\"},{\"content\":\"testKeyword1\"}]"));
  }

  @Test
  public void getSearch() throws Exception {
    addPostToDB(p);
    Post p2 = new Post("testText", "testTitel", "testAuthor", "testEmail", false, new ArrayList<>());
    p2.setId(2L);
    addPostToDB(p2);
    System.out.println("[" + postToJson(p) +"]");
    mvc.perform(MockMvcRequestBuilders.get("/api/activity/keywords/search?keywords=testKeyword1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[" + postToJson(new PostDTO(p)) +"]"));
    mvc.perform(MockMvcRequestBuilders.get("/api/activity/keywords/search?keywords=uh"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[]"));

  }

  private void addPostToDB(Post p) {
    db.execute(String.format("INSERT INTO Post VALUES(%d,'%s','%s','%s','%s',%s);",
        p.getId(), p.getTitle(), p.getText(), p.getAuthor(), p.getEmail(), p.isPublished()));
    for (Keyword k : p.getKeywords()) {
      db.execute(String.format("INSERT INTO post_keyword VALUES(%d, %d);",
          p.getId(), k.getId()));
    }
  }

  private String postToJson(Object p) throws Exception {
    ObjectWriter w = new ObjectMapper().writer();
    System.out.println(w.writeValueAsString(p));
    return w.writeValueAsString(p);
  }
}