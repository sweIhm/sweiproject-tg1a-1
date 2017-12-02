package edu.hm.cs.se.activitymeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.hm.cs.se.activitymeter.ActivityMeter;
import edu.hm.cs.se.activitymeter.model.Post;
import org.junit.After;
import org.junit.AfterClass;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.hm.cs.se.activitymeter.model.PostRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ActivityMeter.class})
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    JdbcTemplate db;

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
        Post p = new Post("tet", "3id.b", "imdronei", "ioeir", true);
        String j = postToJson(p);
        System.out.println(j);
        System.out.println(jsonToPost(j));
    }

    @Test
    public void listAll() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    private void addPostToDB(Post p) {
        db.execute(String.format("INSERT INTO VALUES(%d,%s,%s,%s,%s%s);",
                p.getId(), p.getTitle(), p.getText(), p.getAuthor(), p.getEmail(), p.isPublished()));
    }

    private String postToJson(Post p) throws Exception {
        ObjectWriter w = new ObjectMapper().writer();
        return w.writeValueAsString(p);
    }

    private Post jsonToPost(String p) throws Exception {
        return new ObjectMapper().reader(Post.class).readValue(p);
    }


}