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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ActivityMeter.class})
@AutoConfigureMockMvc
public class LookupControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JdbcTemplate db;

  private Post p;

  private ArrayList<Keyword> k;

  private static final String URL = "/api/lookup/";

  @Before
  public void setUp() throws Exception {
    db.execute("DELETE FROM Comment");
    db.execute("DELETE FROM POST_KEYWORD;");
    db.execute("DELETE FROM Keyword;");
    db.execute("DELETE FROM Post;");
    db.execute("DROP SEQUENCE keyword_id_seq;");
    db.execute("CREATE SEQUENCE keyword_id_seq START WITH 1 INCREMENT BY 1;");
    db.execute("DROP SEQUENCE post_id_seq;");
    db.execute("CREATE SEQUENCE post_id_seq START WITH 1 INCREMENT BY 1;");
    db.execute("INSERT INTO Keyword(keyword_id, content) VALUES(1,'testKeyword1');");
    k = new ArrayList<Keyword>();
    Keyword k1 = new Keyword("testKeyword1");
    k1.setId(1L);
    k.add(k1);
    p = new Post("testText", "testTitel", "testAuthor", "testEmail", true, k);
    p.setId(1L);
  }

  @Test
  public void getTags() throws Exception {
    db.execute("INSERT INTO Keyword VALUES(2,'testKeyword2');");
    db.execute("INSERT INTO Keyword VALUES(3,'testKeyword3');");
    mvc.perform(MockMvcRequestBuilders.get(URL + "keywords"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .json("[{\"content\":\"testKeyword3\"},{\"content\":\"testKeyword2\"},{\"content\":\"testKeyword1\"}]"));
  }

  @Test
  public void testSearch() throws Exception {
    addPostToDB(p);
    Post p2 = new Post("testText", "testTitel", "testAuthor", "testEmail", false, new ArrayList<>());
    p2.setId(2L);
    addPostToDB(p2);
    System.out.println("[" + postToJson(p) +"]");
    mvc.perform(MockMvcRequestBuilders.get(URL + "search?keywords=testKeyword1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[" + postToJson(new PostDTO(p)) +"]"));
    mvc.perform(MockMvcRequestBuilders.get(URL + "search?keywords=uh"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[]"));

  }

  private void addPostToDB(Post p) {
    db.execute(String.format("INSERT INTO Post(post_id, title, text, author, email, published, views) VALUES(%d,'%s','%s','%s','%s',%s,0);",
        p.getId(), p.getTitle(), p.getText(), p.getAuthor(), p.getEmail(), p.isPublished()));
    for (Keyword k : p.getKeywords()) {
      db.execute(String.format("INSERT INTO post_keyword(post_id, keyword_id) VALUES(%d, %d);",
          p.getId(), k.getId()));
    }
  }

  private String postToJson(Object p) throws Exception {
    ObjectWriter w = new ObjectMapper().writer();
    System.out.println(w.writeValueAsString(p));
    return w.writeValueAsString(p);
  }
}
